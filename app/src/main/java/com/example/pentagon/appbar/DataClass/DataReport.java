package com.example.pentagon.appbar.DataClass;

public class DataReport implements java.io.Serializable {
    public String getPrjct() {
        return prjct;
    }

    public void setPrjct(String prjct) {
        this.prjct = prjct;
    }

    public String getReportname() {
        return reportname;
    }

    public void setReportname(String reportname) {
        this.reportname = reportname;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }



    public String getDescrpotion() {
        return descrpotion;
    }

    public void setDescrpotion(String descrpotion) {
        this.descrpotion = descrpotion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String prjct="";
    public String reportname;
    public String files="";
    public String descrpotion="";
    public String id;
    public String lastfile="";
    public String type="";

    public String getLastfile() {
        return lastfile;
    }

    public void setLastfile(String lastfile) {
        this.lastfile = lastfile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getUdate() {
        return udate;
    }

    public void setUdate(String udate) {
        this.udate = udate;
    }

    public String cdate="";
    public String udate="";

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String summary="";

    public String getPrjctname() {
        return prjctname;
    }

    public void setPrjctname(String prjctname) {
        this.prjctname = prjctname;
    }

    public String prjctname="";

    public String getPrjctdescr() {
        return prjctdescr;
    }

    public void setPrjctdescr(String prjctdescr) {
        this.prjctdescr = prjctdescr;
    }

    public String prjctdescr="";

    public String getPrepared() {
        return prepared;
    }

    public void setPrepared(String prepared) {
        this.prepared = prepared;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public  String prepared="",checked="",approved="";

    public boolean isNewreport() {
        return newreport;
    }

    public void setNewreport(boolean newreport) {
        this.newreport = newreport;
    }

    public  boolean newreport;
}
