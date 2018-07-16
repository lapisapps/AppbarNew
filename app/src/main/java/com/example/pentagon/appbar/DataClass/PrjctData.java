package com.example.pentagon.appbar.DataClass;

public class PrjctData {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrjct() {
        return prjct;
    }

    public void setPrjct(String prjct) {
        this.prjct = prjct;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String id,prjct,descr;
    @Override
    public String toString() {
        return prjct;
    }
}
