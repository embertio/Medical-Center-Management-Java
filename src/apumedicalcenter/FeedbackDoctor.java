/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apumedicalcenter;


public class FeedbackDoctor {
    private String CusID;
    private String cusName;
    private String docID;
    private String docName;
    private String date;
    private String comment;
    
    public FeedbackDoctor(String CusID, String cusName, String docID, String docName, String date, String comment){
        this.CusID = CusID;
        this.cusName = cusName;
        this.docID = docID;
        this.docName = docName;
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

    public String getDocID() {
        return docID;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
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
        return CusID + "|" + cusName + "|" + docID + "|" + docName + "|" + date + "|" + comment;
    }
}
