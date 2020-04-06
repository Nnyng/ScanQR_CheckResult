package com.example.scanqr_checkresult;

public class MainActivity_GetSetDB {

    private String id;
    private String CreatedDate;
    private String Scan1;
    private String Scan2;
    private String Status;
    private String Machine;
    private String IMEI;


    public MainActivity_GetSetDB() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getScan1() {
        return Scan1;
    }

    public void setScan1(String scan1) {
        Scan1 = scan1;
    }

    public String getScan2() {
        return Scan2;
    }

    public void setScan2(String scan2) {
        Scan2 = scan2;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMachine() {
        return Machine;
    }

    public void setMachine(String machine) {
        Machine = machine;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

}
