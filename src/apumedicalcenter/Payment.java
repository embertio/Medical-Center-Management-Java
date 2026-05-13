package apumedicalcenter;

public class Payment {
    private String receiptNo;
    private String customerID;
    private String customerName;
    private String payDate;
    private double amount;
    private String method;

    //Constructor
    public Payment(String receiptNo, String customerID, String customerName, String payDate, double amount, String method) {
        this.receiptNo = receiptNo;
        this.customerID = customerID;
        this.customerName = customerName;
        this.payDate = payDate;
        this.amount = amount;
        this.method = method;
    }

    //Getter Setter
    public String getReceiptNo() { 
        return receiptNo;
    }
    public String getCustomerID() { 
        return customerID;
    }
    public String getCustomerName() { 
        return customerName;
    }
    public String getPayDate() { 
        return payDate;
    }
    public double getAmount() { 
        return amount;
     }
    public String getMethod() { 
        return method;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    
    //toString
    public String toFileString() {
        return receiptNo + "|" + customerID + "|" + customerName + "|" + payDate + "|" + String.format("%.2f", amount) + "|" + method;
    }
}
