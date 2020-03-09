package ru.systempla.talos_android.mvp.presenter;

import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.systempla.talos_android.mvp.model.MyModel;
import ru.systempla.talos_android.mvp.model.entity.StorageOperation;
import ru.systempla.talos_android.mvp.view.ArrivingAndRefundView;

@InjectViewState
public class ArrivingAndRefundPresenter extends MvpPresenter<ArrivingAndRefundView> {
    private MyModel myModel = new MyModel();

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

    }

    public void clickSend(String date, String client, String stairsFrames, String passFrames, String diagonalConnections, String horizontalConnections,
                          String crossbars, String decks, String supports, String type) {

        if (myModel.sendStorageOperation(new StorageOperation(date, client,
                type, false, Integer.parseInt(stairsFrames),
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

