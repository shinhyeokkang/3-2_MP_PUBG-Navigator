package com.example.map.Adapter;

public class EquipmentAdapter {

    private String equipmentname;
    private String imagelink;
    private String equipmentDesc;
    public String getEquipmentname() {
        return equipmentname;
    }

    public void setEquipmentname(String equipmentname) {
        this.equipmentname = equipmentname;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }

    public String getEquipmentDesc() {
        return equipmentDesc;
    }

    public void setEquipmentDesc(String equipmentDesc) {
        this.equipmentDesc = equipmentDesc;
    }
    public EquipmentAdapter(String equipmentname, String imagelink, String equipmentDesc) {
        this.equipmentname = equipmentname;
        this.imagelink = imagelink;
        this.equipmentDesc = equipmentDesc;
    }
}
