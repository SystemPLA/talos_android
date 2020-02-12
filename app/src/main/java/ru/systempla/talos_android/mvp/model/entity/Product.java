package ru.systempla.talos_android.mvp.model.entity;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("product_id") private Integer id;
    @SerializedName("product_name") private String name;
    @SerializedName("product_source") private String source;
    @SerializedName("product_status") private String status;
    @SerializedName("product_count") private long count;

    public Product() {
    }

    public Product(String name, String source, String status, long count) {
        this.name = name;
        this.source = source;
        this.status = status;
        this.count = count;
    }
}
