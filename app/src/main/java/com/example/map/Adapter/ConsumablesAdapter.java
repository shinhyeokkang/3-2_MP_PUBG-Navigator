package com.example.map.Adapter;

public class ConsumablesAdapter {
    public String getConsumableName() {
        return consumableName;
    }

    public void setConsumableName(String consumableName) {
        this.consumableName = consumableName;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }

    public String getConsumableDesc() {
        return consumableDesc;
    }

    public void setConsumableDesc(String consumableDesc) {
        this.consumableDesc = consumableDesc;
    }

    private String consumableName, imagelink, consumableDesc;

    public ConsumablesAdapter(String consumableName, String imagelink, String consumableDesc) {
        this.consumableName = consumableName;
        this.imagelink = imagelink;
        this.consumableDesc = consumableDesc;

    }


}
