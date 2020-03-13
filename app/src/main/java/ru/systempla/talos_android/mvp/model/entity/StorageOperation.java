package ru.systempla.talos_android.mvp.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StorageOperation {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("performed")
    @Expose
    private boolean isPerformed;
    @SerializedName("stairsFrameCount")
    @Expose
    private int stairsFrameCount;
    @SerializedName("passFrameCount")
    @Expose
    private int passFrameCount;
    @SerializedName("diagonalConnectionCount")
    @Expose
    private int diagonalConnectionCount;
    @SerializedName("horizontalConnectionCount")
    @Expose
    private int horizontalConnectionCount;
    @SerializedName("crossbarCount")
    @Expose
    private int crossbarCount;
    @SerializedName("deckCount")
    @Expose
    private int deckCount;
    @SerializedName("supportsCount")
    @Expose
    private int supportsCount;
    @SerializedName("stairsFrameBadCount")
    @Expose
    private int stairsFrameBadCount;
    @SerializedName("passFrameBadCount")
    @Expose
    private int passFrameBadCount;
    @SerializedName("diagonalConnectionBadCount")
    @Expose
    private int diagonalConnectionBadCount;
    @SerializedName("horizontalConnectionBadCount")
    @Expose
    private int horizontalConnectionBadCount;
    @SerializedName("crossbarBadCount")
    @Expose
    private int crossbarBadCount;
    @SerializedName("deckBadCount")
    @Expose
    private int deckBadCount;
    @SerializedName("supportsBadCount")
    @Expose
    private int supportsBadCount;

    /*@Expose
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
    private Boolean isPerformed;*/

    public StorageOperation(String date, String customerName, String type, boolean isPerformed, int stairsFrameCount,
                            int passFrameCount, int diagonalConnectionCount, int horizontalConnectionCount, int crossbarCount,
                            int deckCount, int supportsCount, int stairsFrameBadCount, int passFrameBadCount,
                            int diagonalConnectionBadCount, int horizontalConnectionBadCount, int crossbarBadCount,
                            int deckBadCount, int supportsBadCount) {

        this.date = date;
        this.customerName = customerName;
        this.type = type;
        this.isPerformed = isPerformed;
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
    }

    public StorageOperation(String date, String customerName, String type, boolean isPerformed, int stairsFrameCount,
                            int passFrameCount, int diagonalConnectionCount, int horizontalConnectionCount, int crossbarCount,
                            int deckCount, int supportsCount) {
        this.date = date;
        this.customerName = customerName;
        this.type = type;
        this.isPerformed = isPerformed;
        this.stairsFrameCount = stairsFrameCount;
        this.passFrameCount = passFrameCount;
        this.diagonalConnectionCount = diagonalConnectionCount;
        this.horizontalConnectionCount = horizontalConnectionCount;
        this.crossbarCount = crossbarCount;
        this.deckCount = deckCount;
        this.supportsCount = supportsCount;
    }



    public int getId() {
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

    public boolean isPerformed() {
        return isPerformed;
    }

    public int getStairsFrameCount() {
        return stairsFrameCount;
    }

    public int getPassFrameCount() {
        return passFrameCount;
    }

    public int getDiagonalConnectionCount() {
        return diagonalConnectionCount;
    }

    public int getHorizontalConnectionCount() {
        return horizontalConnectionCount;
    }

    public int getCrossbarCount() {
        return crossbarCount;
    }

    public int getDeckCount() {
        return deckCount;
    }

    public int getSupportsCount() {
        return supportsCount;
    }

    public int getStairsFrameBadCount() {
        return stairsFrameBadCount;
    }

    public int getPassFrameBadCount() {
        return passFrameBadCount;
    }

    public int getDiagonalConnectionBadCount() {
        return diagonalConnectionBadCount;
    }

    public int getHorizontalConnectionBadCount() {
        return horizontalConnectionBadCount;
    }

    public int getCrossbarBadCount() {
        return crossbarBadCount;
    }

    public int getDeckBadCount() {
        return deckBadCount;
    }

    public int getSupportsBadCount() {
        return supportsBadCount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPerformed(boolean performed) {
        isPerformed = performed;
    }

    public void setStairsFrameCount(int stairsFrameCount) {
        this.stairsFrameCount = stairsFrameCount;
    }

    public void setPassFrameCount(int passFrameCount) {
        this.passFrameCount = passFrameCount;
    }

    public void setDiagonalConnectionCount(int diagonalConnectionCount) {
        this.diagonalConnectionCount = diagonalConnectionCount;
    }

    public void setHorizontalConnectionCount(int horizontalConnectionCount) {
        this.horizontalConnectionCount = horizontalConnectionCount;
    }

    public void setCrossbarCount(int crossbarCount) {
        this.crossbarCount = crossbarCount;
    }

    public void setDeckCount(int deckCount) {
        this.deckCount = deckCount;
    }

    public void setSupportsCount(int supportsCount) {
        this.supportsCount = supportsCount;
    }

    public void setStairsFrameBadCount(int stairsFrameBadCount) {
        this.stairsFrameBadCount = stairsFrameBadCount;
    }

    public void setPassFrameBadCount(int passFrameBadCount) {
        this.passFrameBadCount = passFrameBadCount;
    }

    public void setDiagonalConnectionBadCount(int diagonalConnectionBadCount) {
        this.diagonalConnectionBadCount = diagonalConnectionBadCount;
    }

    public void setHorizontalConnectionBadCount(int horizontalConnectionBadCount) {
        this.horizontalConnectionBadCount = horizontalConnectionBadCount;
    }

    public void setCrossbarBadCount(int crossbarBadCount) {
        this.crossbarBadCount = crossbarBadCount;
    }

    public void setDeckBadCount(int deckBadCount) {
        this.deckBadCount = deckBadCount;
    }

    public void setSupportsBadCount(int supportsBadCount) {
        this.supportsBadCount = supportsBadCount;
    }
}
