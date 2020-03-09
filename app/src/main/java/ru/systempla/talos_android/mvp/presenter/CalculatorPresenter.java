package ru.systempla.talos_android.mvp.presenter;

import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.systempla.talos_android.mvp.model.MyModel;
import ru.systempla.talos_android.mvp.model.entity.StorageOperation;
import ru.systempla.talos_android.mvp.view.CalculatorView;

@InjectViewState
public class CalculatorPresenter extends MvpPresenter<CalculatorView> {
    private MyModel myModel;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

    }

    private double squareMeterCost;

    /*Переменные:
    Высота - H;
    Длина - L;
    Количество ярусов - F = H/2;
    Количество секция - S = L/3;
    Количество подъемов - R (1 подъем на 30 м длины);
    Количество ярусов с настилами - D = F - 1;*/

    //код Ильи
    private int stairsFrameCount;   //Рама с лестницей = (F - 1) * R;
    private int passFrameCount;     //Рама проходная = F * ((S + 1) - D) + D;
    private int diagonalConnectionCount;    // Связь диагональная = (H/3 * L/2)/2;
    private int horizontalConnectionCount;  //Связь горизонтальная = Связь диагональная * 3;
    private int crossbarCount;  //Ригель настила = (D * S) * 2;
    private int deckCount;  //Настил деревянный = (S * D) * 3;
    private int supportsCount;  //Опоры (пятки) = ((L/3) + 1) * 2;
    private double costPerDay;  //стоимость в день
    private double credit; //TODO добавить расчет залога


    public void calculatorStart(String h, String l, String squareMeterCost) {
        if (!h.equals("") && !l.equals("") && !squareMeterCost.equals("")) {
            int height = Integer.parseInt(h);
            int length = Integer.parseInt(l);
            this.squareMeterCost = Double.parseDouble(squareMeterCost);


            calculate(height, length);
            getViewState().showResult(stairsFrameCount, passFrameCount, diagonalConnectionCount,
                    horizontalConnectionCount, crossbarCount, deckCount,
                    supportsCount, costPerDay, credit);
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
        credit = 0;
    }

    public void clickSend(String date, String client, String stairsFrames, String passFrames, String diagonalConnections, String horizontalConnections,
                          String crossbars, String decks, String supports) {


        myModel = new MyModel();

        if (myModel.sendStorageOperation(new StorageOperation(date, client,
                "Отгрузка", false, Integer.parseInt(stairsFrames),
                Integer.parseInt(passFrames),
                Integer.parseInt(diagonalConnections),
                Integer.parseInt(horizontalConnections),
                Integer.parseInt(crossbars),
                Integer.parseInt(decks),
                Integer.parseInt(supports)))) {
            getViewState().showSuccess();
        } else {
            getViewState().showFailure();
        }

    }


}
