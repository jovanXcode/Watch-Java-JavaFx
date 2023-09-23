package watch;

import javafx.beans.property.SimpleStringProperty;

public class Transaction {
	private final SimpleStringProperty TransactionID;
	private final SimpleStringProperty UserID;
	private final SimpleStringProperty TransactionDate;
	private final SimpleStringProperty WatchName;
	private final SimpleStringProperty WatchId;
	private final SimpleStringProperty WatchSize;
	private final SimpleStringProperty WatchBrand;
	private final SimpleStringProperty WatchQuantity;
	private final SimpleStringProperty subTotal;

    public Transaction(String TransactionID, String UserID, String TransactionDate, String s) {
        this.TransactionID = new SimpleStringProperty(TransactionID);
        this.UserID = new SimpleStringProperty(UserID);
        this.TransactionDate = new SimpleStringProperty(TransactionDate);
        this.WatchId = new SimpleStringProperty("");
        this.WatchName = new SimpleStringProperty("");
        this.WatchSize = new SimpleStringProperty("");
        this.WatchQuantity = new SimpleStringProperty("");
        this.WatchBrand = new SimpleStringProperty("");
        this.subTotal = new SimpleStringProperty("");
    }

    public Transaction(String WatchName, String WatchSize, String WatchQuantity) {
        this.TransactionID = new SimpleStringProperty("");
        this.UserID = new SimpleStringProperty("");
        this.TransactionDate = new SimpleStringProperty("");
        this.WatchId = new SimpleStringProperty("");
        this.WatchBrand = new SimpleStringProperty("");
        this.WatchName = new SimpleStringProperty(WatchName);
        this.WatchSize = new SimpleStringProperty(WatchSize);
        this.WatchQuantity = new SimpleStringProperty(WatchQuantity);
        this.subTotal = new SimpleStringProperty("");
    }

    public Transaction(String transactionId, String watchId, String watchName, String watchBrand, String watchPrice, String watchQuantity) {
        this.TransactionID = new SimpleStringProperty(transactionId);
        this.UserID = new SimpleStringProperty("");
        this.TransactionDate = new SimpleStringProperty("");
        this.WatchId = new SimpleStringProperty(watchId);
        this.WatchName = new SimpleStringProperty(watchName);
        this.WatchBrand = new SimpleStringProperty(watchBrand);
        this.WatchSize = new SimpleStringProperty(watchPrice);
        this.WatchQuantity = new SimpleStringProperty(watchQuantity);
        this.subTotal = new SimpleStringProperty(String.valueOf(Integer.valueOf(watchQuantity)*Integer.valueOf(watchPrice)));
    }


    public String getTransactionID() {
        return TransactionID.get();
    }

    public void setTransactionID(String id) {
        this.TransactionID.set(id);
    }

    public String getUserID() {
        return UserID.get();
    }

    public void setUserID(String id) {
        this.UserID.set(id);
    }

    public String getTransactionDate() {
        return TransactionDate.get();
    }

    public void setTransactionDate(String id) {
        this.TransactionDate.set(id);
    }

    public String getWatchName() {
        return WatchName.get();
    }

    public void setWatchName(String id) {
        this.WatchName.set(id);
    }

    public String getWatchPrice() {
        return WatchSize.get();
    }

    public void setWatchPrice(String id) {
        this.WatchSize.set(id);
    }

    public String getWatchId() {
        return WatchId.get();
    }

    public void setWatchId(String id) {
        this.WatchId.set(id);
    }

    public String getWatchQuantity() {
        return WatchQuantity.get();
    }

    public void setWatchQuantity(String id) {
        this.WatchQuantity.set(id);
    }

    public String getWatchBrand() {
        return WatchBrand.get();
    }

    public void setWatchBrand(String id) {
        this.WatchBrand.set(id);
    }

    public String getSubTotal() {
        return subTotal.get();
    }

    public void setSubTotal(String id) {
        this.subTotal.set(id);
    }

}

