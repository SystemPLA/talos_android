package ru.systempla.talos_android.mvp.model.entity;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class StorageOperation implements Serializable {

    @Expose
    private Integer id;
    @Expose
    private String date;
    @Expose
    private String customerName;
    @Expose
    private String type;
    @Expose
    private Integer stairsFrameCount;
    @Expose
    private Integer passFrameCount;
    @Expose
    private Integer diagonalConnectionCount;
    @Expose
    private Integer horizontalConnectionCount;
    @Expose
    private Integer crossbarCount;
    @Expose
    private Integer deckCount;
    @Expose
    private Integer supportsCount;
    @Expose
    private Integer stairsFrameBadCount;
    @Expose
    private Integer passFrameBadCount;
    @Expose
    private Integer diagonalConnectionBadCount;
    @Expose
    private Integer horizontalConnectionBadCount;
    @Expose
    private Integer crossbarBadCount;
    @Expose
    private Integer deckBadCount;
    @Expose
    private Integer supportsBadCount;
    @Expose
    private Boolean performed;

    public StorageOperation(String date, String customerName, String type, Integer stairsFrameCount,
                            Integer passFrameCount, Integer diagonalConnectionCount, Integer horizontalConnectionCount,
                            Integer crossbarCount, Integer deckCount, Integer supportsCount, Integer stairsFrameBadCount,
                            Integer passFrameBadCount, Integer diagonalConnectionBadCount,
                            Integer horizontalConnectionBadCount, Integer crossbarBadCount, Integer deckBadCount,
                            Integer supportsBadCount, Boolean performed) {
        this.date = date;
        this.customerName = customerName;
        this.type = type;
        this.stairsFrameCount = stairsFrameCount;
        this.passFrameCount = passFrameCount;
        this.diagonalConnectionCount = diagonalConnectionCount;
        this.horizontalConnectionCount = horizontalConnectionCount;
        this.crossbarCount = crossbarCount;
        this.deckCount = deckCount;
        this.supportsCount = supportsCount;
        this.stairsFrameBadCount = stairsFrameBadCount;
        this.passFrameBadCount = passFrameBadCount;
        this.diagonalConnectionBadCount = diagonalConnectionBadCount;
        this.horizontalConnectionBadCount = horizontalConnectionBadCount;
        this.crossbarBadCount = crossbarBadCount;
        this.deckBadCount = deckBadCount;
        this.supportsBadCount = supportsBadCount;
        this.performed = performed;
    }

    public StorageOperation(Integer id, String date, String customerName, String type, Integer stairsFrameCount,
                            Integer passFrameCount, Integer diagonalConnectionCount, Integer horizontalConnectionCount,
                            Integer crossbarCount, Integer deckCount, Integer supportsCount, Integer stairsFrameBadCount,
                            Integer passFrameBadCount, Integer diagonalConnectionBadCount,
                            Integer horizontalConnectionBadCount, Integer crossbarBadCount, Integer deckBadCount,
                            Integer supportsBadCount, Boolean performed) {
        this.id = id;
        this.date = date;
        this.customerName = customerName;
        this.type = type;
        this.stairsFrameCount = stairsFrameCount;
        this.passFrameCount = passFrameCount;
        this.diagonalConnectionCount = diagonalConnectionCount;
        this.horizontalConnectionCount = horizontalConnectionCount;
        this.crossbarCount = crossbarCount;
        this.deckCount = deckCount;
        this.supportsCount = supportsCount;
        this.stairsFrameBadCount = stairsFrameBadCount;
        this.passFrameBadCount = passFrameBadCount;
        this.diagonalConnectionBadCount = diagonalConnectionBadCount;
        this.horizontalConnectionBadCount = horizontalConnectionBadCount;
        this.crossbarBadCount = crossbarBadCount;
        this.deckBadCount = deckBadCount;
        this.supportsBadCount = supportsBadCount;
        this.performed = performed;
    }

    @Override
    public String toString() {
        return "StorageOperation{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", customerName='" + customerName + '\'' +
                ", type='" + type + '\'' +
                ", stairsFrameCount=" + stairsFrameCount +
                ", passFrameCount=" + passFrameCount +
                ", diagonalConnectionCount=" + diagonalConnectionCount +
                ", horizontalConnectionCount=" + horizontalConnectionCount +
                ", crossbarCount=" + crossbarCount +
                ", deckCount=" + deckCount +
                ", supportsCount=" + supportsCount +
                ", stairsFrameBadCount=" + stairsFrameBadCount +
                ", passFrameBadCount=" + passFrameBadCount +
                ", diagonalConnectionBadCount=" + diagonalConnectionBadCount +
                ", horizontalConnectionBadCount=" + horizontalConnectionBadCount +
                ", crossbarBadCount=" + crossbarBadCount +
                ", deckBadCount=" + deckBadCount +
                ", supportsBadCount=" + supportsBadCount +
                ", performed=" + performed +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getType() {
        return type;
    }

    public Integer getStairsFrameCount() {
        return stairsFrameCount;
    }

    public Integer getPassFrameCount() {
        return passFrameCount;
    }

    public Integer getDiagonalConnectionCount() {
        return diagonalConnectionCount;
    }

    public Integer getHorizontalConnectionCount() {
        return horizontalConnectionCount;
    }

    public Integer getCrossbarCount() {
        return crossbarCount;
    }

    public Integer getDeckCount() {
        return deckCount;
    }

    public Integer getSupportsCount() {
        return supportsCount;
    }

    public Integer getStairsFrameBadCount() {
        return stairsFrameBadCount;
    }

    public Integer getPassFrameBadCount() {
        return passFrameBadCount;
    }

    public Integer getDiagonalConnectionBadCount() {
        return diagonalConnectionBadCount;
    }

    public Integer getHorizontalConnectionBadCount() {
        return horizontalConnectionBadCount;
    }

    public Integer getCrossbarBadCount() {
        return crossbarBadCount;
    }

    public Integer getDeckBadCount() {
        return deckBadCount;
    }

    public Integer getSupportsBadCount() {
        return supportsBadCount;
    }

    public Boolean getPerformed() {
        return performed;
    }
}

