package ru.systempla.talos_android.mvp.model.utils;

import java.util.ArrayList;
import java.util.List;

import ru.systempla.talos_android.mvp.model.entity.Product;

public class DataListOrganiser {

    public static <T> List<T> reverseOrder (List<T> dataList){
        ArrayList<T> result = new ArrayList<T>();
        for (int i = dataList.size()-1; i >=0 ; i--) {
            result.add(dataList.get(i));
        }
        return result;
    }

    public static List<Product> sortWarehouseById (List<Product> dataList){
        ArrayList<Product> result = new ArrayList<Product>();
        Product tempData = dataList.get(0);
        for (int i = 0; i <dataList.size() ; i++) {
            for (int j = 0; j <dataList.size() ; j++) {
                if (dataList.get(j).getId()==i+1) {
                    tempData = dataList.get(j);
                }
            }
            result.add(tempData);
        }
        return result;
    }
}
