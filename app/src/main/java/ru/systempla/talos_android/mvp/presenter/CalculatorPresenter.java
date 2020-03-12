package ru.systempla.talos_android.mvp.presenter;

import android.util.Log;

import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.systempla.talos_android.mvp.model.MyModel;
import ru.systempla.talos_android.mvp.model.entity.StorageOperation;
import ru.systempla.talos_android.mvp.view.CalculatorView;

import static java.lang.Thread.sleep;

@InjectViewState
public class CalculatorPresenter extends MvpPresenter<CalculatorView> {
    private MyModel myModel;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

    }

    private double squareMeterCost;
    private static final String type = "Отгрузка";

    /*Переменные:
    Высота - H;
    Длина - L;
    Количество ярусов - F = H/2;
    Количество секция - S = L/3;
    Количество подъемов - R (1 подъем на 30 м длины);
    Количество ярусов с настилами - D = F - 1;*/


    private int stairsFrameCount;   //Рама с лестницей = (F - 1) * R;
    private int passFrameCount;     //Рама проходная = F * ((S + 1) - D) + D;
    private int diagonalConnectionCount;    // Связь диагональная = (H/3 * L/2)/2;
    private int horizontalConnectionCount;  //Связь горизонтальная = Связь диагональная * 3;
    private int crossbarCount;  //Ригель настила = (D * S) * 2;
    private int deckCount;  //Настил деревянный = (S * D) * 3;
    private int supportsCount;  //Опоры (пятки) = ((L/3) + 1) * 2;
    private double costPerDay;  //стоимость в день
    private double costPerMonth;
    private double credit;
    private double buyPrice;
    private double sellPriceNew;
    private double sellPriceUsed;
    //цены
    private static final double stairsFramePrice = 767.0d;
    private static final double passFramePrice = 667.0d;
    private static final double diagonalConnectionPrice = 368.0d;
    private static final double horizontalConnectionPrice = 189.0d;
    private static final double crossbarPrice = 578.0d;
    private static final double deckPrice = 210.0d;
    private static final double supportsPrice = 77.0d;


    public void calculatorStart(String h, String l, String squareMeterCost) {
        if (!h.equals("") && !l.equals("") && !squareMeterCost.equals("")) {
            int height = Integer.parseInt(h);
            int length = Integer.parseInt(l);
            this.squareMeterCost = Double.parseDouble(squareMeterCost);


            calculate(height, length);
            getViewState().showResult(stairsFrameCount, passFrameCount, diagonalConnectionCount,
                    horizontalConnectionCount, crossbarCount, deckCount,
                    supportsCount, costPerDay, costPerMonth, credit, sellPriceNew, sellPriceUsed);
        }
    }

    //код Ильи обновленный
    private void calculate(int height, int length) {

        if (height % 2 == 1) height++;
        if (length % 3 == 2) length++;
        if (length % 3 == 1) length--;
        int levelCount = height / 2;
        int sectionCount = length / 3;
        int liftCount = 1 + (length / 30);
        int deckLevelCount = 1;
        if (levelCount - 1 > 0) deckLevelCount = levelCount - 1;

        stairsFrameCount = (levelCount - 1) * liftCount;
        passFrameCount = levelCount * ((sectionCount + 1) - liftCount) + liftCount;
        diagonalConnectionCount = ((height / 3) * (length / 2)) / 2;
        horizontalConnectionCount = diagonalConnectionCount * 3;
        crossbarCount = deckLevelCount * sectionCount * 2;
        deckCount = sectionCount * deckLevelCount * 3;
        supportsCount = (sectionCount + 1) * 2;
        costPerDay = height * length * squareMeterCost;

        costPerMonth = costPerDay * 30;
        buyPrice = stairsFrameCount * stairsFramePrice + passFrameCount * passFramePrice + diagonalConnectionCount * diagonalConnectionPrice +
                horizontalConnectionCount * horizontalConnectionPrice + crossbarCount * crossbarPrice + deckCount * deckPrice + supportsCount * supportsPrice;
        sellPriceNew = 1.15 * buyPrice;
        credit = 0.4 * sellPriceNew;
        sellPriceUsed = 0.7 * sellPriceNew;
    }



    public void clickSend(String date, String client, String stairsFrames, String passFrames, String diagonalConnections, String horizontalConnections,
                          String crossbars, String decks, String supports) {

        getViewState().showLoading();


        myModel = new MyModel();

        if (myModel.sendStorageOperation(new StorageOperation(date, client,
                type, false, Integer.parseInt(stairsFrames),
                Integer.parseInt(passFrames),
                Integer.parseInt(diagonalConnections),
                Integer.parseInt(horizontalConnections),
                Integer.parseInt(crossbars),
                Integer.parseInt(decks),
                Integer.parseInt(supports)))) {

            getViewState().showSuccess();

            getViewState().hideLoading();

        } else {

            getViewState().showFailure();

            getViewState().hideLoading();
        }


    }
}



