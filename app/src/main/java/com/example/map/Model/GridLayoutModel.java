package com.example.map.Model;

public class GridLayoutModel {
    String gridName;
    String imageLink;

    public GridLayoutModel(String gridName, String imageLink) {
        this.gridName = gridName;
        this.imageLink = imageLink;
    }

    public String getGridName() {
        return gridName;
    }

    public void setGridName(String gridName) {
        this.gridName = gridName;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
