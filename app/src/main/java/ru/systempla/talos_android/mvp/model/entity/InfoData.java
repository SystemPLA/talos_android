package ru.systempla.talos_android.mvp.model.entity;

import com.google.gson.annotations.SerializedName;

public class InfoData {

    @SerializedName("info_id") private Integer infoId;
    @SerializedName("info_product_name") private String name;
    @SerializedName("info_weight") private float weight;
    @SerializedName("price_sv") private float priceSv;
    @SerializedName("price_rin") private float priceRin;
    @SerializedName("price_dzsl") private float priceDzsl;

    public InfoData() {
    }

    public InfoData(String name, float weight, float priceSv, float priceRin, float priceDzsl) {
        this.name = name;
        this.weight = weight;
        this.priceSv = priceSv;
        this.priceRin = priceRin;
        this.priceDzsl = priceDzsl;
    }
}