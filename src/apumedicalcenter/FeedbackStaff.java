/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apumedicalcenter;


public class FeedbackStaff {
    private String CusID;
    private String cusName;
    private String staffID;
    private String staffName;
    private String date;
    private String comment;
    
    public FeedbackStaff(String CusID, String cusName, String staffID, String staffName, String date, String comment){
        this.CusID = CusID;
        this.cusName = cusName;
        this.staffID = staffID;
        this.staffName = staffName;
        this.date = date;
        this.comment = comment;              
    }

    public String getCusID() {
        return CusID;
    }

    public void setCusID(String CusID) {
        this.CusID = CusID;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    @Override
    public String toString() {
        return CusID + "|" + cusName + "|" + staffID + "|" + staffName + "|" + date + "|" + comment;
    }
}
