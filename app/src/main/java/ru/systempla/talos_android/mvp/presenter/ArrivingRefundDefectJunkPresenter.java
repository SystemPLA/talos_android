package ru.systempla.talos_android.mvp.presenter;

import androidx.annotation.CheckResult;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.systempla.talos_android.mvp.model.MyModel;
import ru.systempla.talos_android.mvp.model.entity.StorageOperation;
import ru.systempla.talos_android.mvp.view.ArrivingRefundDefectJunkView;

@InjectViewState
public class ArrivingRefundDefectJunkPresenter extends MvpPresenter<ArrivingRefundDefectJunkView> {
    private MyModel myModel = new MyModel();

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

    }

    public void clickSend(String date, String client, String stairsFrames, String passFrames, String diagonalConnections, String horizontalConnections,
                          String crossbars, String decks, String supports, String type) {
        getViewState().showLoading();

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

    public void clickSendBad(String date, String client, String stairsFrames, String passFrames, String diagonalConnections, String horizontalConnections,
                          String crossbars, String decks, String supports, String type) {
        getViewState().showLoading();

        if (myModel.sendStorageOperation(new StorageOperation(date, client,
                type, false,0,0,0,0,0,0,0, Integer.parseInt(stairsFrames),
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

    public void clickSendFull(String date, String client, String stairsFrames, String passFrames, String diagonalConnections,
                              String horizontalConnections, String crossbars, String decks, String supports,
                              String stairsFramesBad, String passFramesBad, String diagonalConnectionsBad, String horizontalConnectionsBad,
                              String crossbarsBad, String decksBad, String supportsBad, String type ) {
        getViewState().showLoading();

        if (myModel.sendStorageOperation(new StorageOperation(date, client,
                type, false,Integer.parseInt(stairsFrames),
                Integer.parseInt(passFrames),
                Integer.parseInt(diagonalConnections),
                Integer.parseInt(horizontalConnections),
                Integer.parseInt(crossbars),
                Integer.parseInt(decks),
                Integer.parseInt(supports),
                Integer.parseInt(stairsFramesBad),
                Integer.parseInt(passFramesBad),
                Integer.parseInt(diagonalConnectionsBad),
                Integer.parseInt(horizontalConnectionsBad),
                Integer.parseInt(crossbarsBad),
                Integer.parseInt(decksBad),
                Integer.parseInt(supportsBad)))) {
            getViewState().showSuccess();
            getViewState().hideLoading();
        } else {
            getViewState().showFailure();
            getViewState().hideLoading();
        }

    }
    @CheckResult
    public List<String> getClients() {
        //getViewState().showLoading();
        myModel = new MyModel();
        List<StorageOperation> ls;
        ls = myModel.getStorageOperations();

        List<String> clientList = new ArrayList<>();
        clientList.add("Error");
        if (ls != null) {
            for (int i = 0; i < ls.size(); i++) {
                clientList.add(ls.get(i).getCustomerName());
            }
            //getViewState().hideLoading();
            clearListDublicates(clientList);
            return clientList;
        }
        // getViewState().hideLoading();
        clearListDublicates(clientList);
        return clientList;


    }

    private void clearListDublicates(List<String> listToDedup){
        Set<String> set = new HashSet<>(listToDedup);
        listToDedup.clear();
        listToDedup.addAll(set);

    }


}

