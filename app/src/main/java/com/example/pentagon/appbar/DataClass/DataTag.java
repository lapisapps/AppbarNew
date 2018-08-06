package com.example.pentagon.appbar.DataClass;

public class DataTag {
String prjctid;
    String id;
    String tag;

    public String getExist() {
        return exist;
    }

    public void setExist(String exist) {
        this.exist = exist;
    }

    public boolean isSelected() {
        return selected;
    }

    String exist;

    public String getPrjctid() {
        return prjctid;
    }

    public void setPrjctid(String prjctid) {
        this.prjctid = prjctid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTagid() {
        return tagid;
    }

    public void setTagid(String tagid) {
        this.tagid = tagid;
    }

    String tagid;

    public boolean getSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean selected;

    @Override
    public String toString() {
        return tag;
    }
}
