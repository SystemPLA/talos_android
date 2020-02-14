package ru.systempla.talos_android.mvp.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;

public class Order {

    @SerializedName("orderId")
    @Expose
    private Integer orderId;
    @SerializedName("clientName")
    @Expose
    private String clientName;
    @SerializedName("creationDate")
    @Expose
    private String creationDate;
    @SerializedName("acceptionDate")
    @Expose
    private String acceptionDate;
    @SerializedName("returnDate")
    @Expose
    private String returnDate;
    @SerializedName("typeOneCount")
    @Expose
    private long typeOneCount;
    @SerializedName("typeTwoCount")
    @Expose
    private long typeTwoCount;
    @SerializedName("typeThreeCount")
    @Expose
    private long typeThreeCount;
    @SerializedName("typeFourCount")
    @Expose
    private long typeFourCount;
    @SerializedName("typeFiveCount")
    @Expose
    private long typeFiveCount;
    @SerializedName("typeSixCount")
    @Expose
    private long typeSixCount;
    @SerializedName("typeSevenCount")
    @Expose
    private long typeSevenCount;
    @SerializedName("orderStatus")
    @Expose
    private String orderStatus;

    public Order(Integer orderId, String clientName, String creationDate,
                 String acceptionDate, String returnDate, long typeOneCount,
                 long typeTwoCount, long typeThreeCount, long typeFourCount, long typeFiveCount,
                 long typeSixCount, long typeSevenCount, String orderStatus) {
        this.orderId = orderId;
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

    public Order(String clientName, String creationDate, String acceptionDate,
                 String returnDate, long typeOneCount, long typeTwoCount, long typeThreeCount,
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

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", clientName='" + clientName + '\'' +
                ", creationDate=" + creationDate +
                ", acceptionDate=" + acceptionDate +
                ", returnDate=" + returnDate +
                ", typeOneCount=" + typeOneCount +
                ", typeTwoCount=" + typeTwoCount +
                ", typeThreeCount=" + typeThreeCount +
                ", typeFourCount=" + typeFourCount +
                ", typeFiveCount=" + typeFiveCount +
                ", typeSixCount=" + typeSixCount +
                ", typeSevenCount=" + typeSevenCount +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }

    public Integer getOrderId() {
        return orderId;
    }

    public String getClientName() {
        return clientName;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getAcceptionDate() {
        return acceptionDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public long getTypeOneCount() {
        return typeOneCount;
    }

    public long getTypeTwoCount() {
        return typeTwoCount;
    }

    public long getTypeThreeCount() {
        return typeThreeCount;
    }

    public long getTypeFourCount() {
        return typeFourCount;
    }

    public long getTypeFiveCount() {
        return typeFiveCount;
    }

    public long getTypeSixCount() {
        return typeSixCount;
    }

    public long getTypeSevenCount() {
        return typeSevenCount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }
}
