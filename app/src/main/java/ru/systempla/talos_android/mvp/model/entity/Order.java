package ru.systempla.talos_android.mvp.model.entity;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class Order {

    @SerializedName("order_id") private Integer orderId;
    @SerializedName("client_name") private String clientName;
    @SerializedName("creation_date") private LocalDate creationDate;
    @SerializedName("acception_date") private LocalDate acceptionDate;
    @SerializedName("return_date") private LocalDate returnDate;
    @SerializedName("count_one") private long typeOneCount;
    @SerializedName("count_two") private long typeTwoCount;
    @SerializedName("count_three") private long typeThreeCount;
    @SerializedName("count_four") private long typeFourCount;
    @SerializedName("count_five") private long typeFiveCount;
    @SerializedName("count_six") private long typeSixCount;
    @SerializedName("count_seven") private long typeSevenCount;
    @SerializedName("order_status") private String orderStatus;

    public Order() {
    }

    public Order(String clientName, LocalDate creationDate, LocalDate acceptionDate,
                 LocalDate returnDate, long typeOneCount, long typeTwoCount, long typeThreeCount,
                 long typeFourCount, long typeFiveCount, long typeSixCount, long typeSevenCount,
                 String orderStatus) {

        this.clientName = clientName;
        this.creationDate = creationDate;
        this.acceptionDate = acceptionDate;
        this.returnDate = returnDate;
        this.typeOneCount = typeOneCount;
        this.typeTwoCount = typeTwoCount;
        this.typeThreeCount = typeThreeCount;
        this.typeFourCount = typeFourCount;
        this.typeFiveCount = typeFiveCount;
        this.typeSixCount = typeSixCount;
        this.typeSevenCount = typeSevenCount;
        this.orderStatus = orderStatus;
    }
}
