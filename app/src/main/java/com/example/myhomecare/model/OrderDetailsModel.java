package com.example.myhomecare.model;

public class OrderDetailsModel {
    int odid;
    String odImage;
    String odpname;
    String odpbrandName;
    String odpcolor;
    String odpsize;
    int odpquantity;
    Float odpprice;


    public OrderDetailsModel() {
    }

    public OrderDetailsModel(int odid, String odImage, String odpname, String odpbrandName, String odpcolor, String odpsize, int odpquantity, Float odpprice) {
        this.odid = odid;
        this.odImage = odImage;
        this.odpname = odpname;
        this.odpbrandName = odpbrandName;
        this.odpcolor = odpcolor;
        this.odpsize = odpsize;
        this.odpquantity = odpquantity;
        this.odpprice = odpprice;
    }

    public int getOdid() {
        return odid;
    }

    public void setOdid(int odid) {
        this.odid = odid;
    }

    public String getOdImage() {
        return odImage;
    }

    public void setOdImage(String odImage) {
        this.odImage = odImage;
    }

    public String getOdpname() {
        return odpname;
    }

    public void setOdpname(String odpname) {
        this.odpname = odpname;
    }

    public String getOdpbrandName() {
        return odpbrandName;
    }

    public void setOdpbrandName(String odpbrandName) {
        this.odpbrandName = odpbrandName;
    }

    public String getOdpcolor() {
        return odpcolor;
    }

    public void setOdpcolor(String odpcolor) {
        this.odpcolor = odpcolor;
    }

    public String getOdpsize() {
        return odpsize;
    }

    public void setOdpsize(String odpsize) {
        this.odpsize = odpsize;
    }

    public int getOdpquantity() {
        return odpquantity;
    }

    public void setOdpquantity(int odpquantity) {
        this.odpquantity = odpquantity;
    }

    public Float getOdpprice() {
        return odpprice;
    }

    public void setOdpprice(Float odpprice) {
        this.odpprice = odpprice;
    }
}
