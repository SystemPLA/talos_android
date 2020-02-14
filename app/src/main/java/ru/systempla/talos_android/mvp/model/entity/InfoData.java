package ru.systempla.talos_android.mvp.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InfoData {

    @SerializedName("infoId")
    @Expose
    private Integer infoId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("weight")
    @Expose
    private Double weight;
    @SerializedName("priceSv")
    @Expose
    private Double priceSv;
    @SerializedName("priceRin")
    @Expose
    private Double priceRin;
    @SerializedName("priceDzsl")
    @Expose
    private Double priceDzsl;

    public InfoData(Integer infoId, String name, Double weight, Double priceSv, Double priceRin, Double priceDzsl) {
        this.infoId = infoId;
        this.name = name;
        this.weight = weight;
        this.priceSv = priceSv;
        this.priceRin = priceRin;
        this.priceDzsl = priceDzsl;
    }

    public InfoData(String name, Double weight, Double priceSv, Double priceRin, Double priceDzsl) {
        this.name = name;
        this.weight = weight;
        this.priceSv = priceSv;
        this.priceRin = priceRin;
        this.priceDzsl = priceDzsl;
    }

    @Override
    public String toString() {
        return "InfoData{" +
                "infoId=" + infoId +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", priceSv=" + priceSv +
                ", priceRin=" + priceRin +
                ", priceDzsl=" + priceDzsl +
                '}';
    }

    public Integer getInfoId() {
        return infoId;
    }

    public String getName() {
        return name;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getPriceSv() {
        return priceSv;
    }

    public Double getPriceRin() {
        return priceRin;
    }

    public Double getPriceDzsl() {
        return priceDzsl;
    }
}