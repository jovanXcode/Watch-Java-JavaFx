package watch;

import javafx.beans.property.SimpleStringProperty;

public class Cart {
	private final SimpleStringProperty watchId;
	private final SimpleStringProperty watchName;
	private final SimpleStringProperty watchPrice;
	private final SimpleStringProperty userId;
	private final SimpleStringProperty quantity;

    public Cart(String watchId, String userId, String quantity, String watchName, String watchPrice) {
        this.userId = new SimpleStringProperty(userId);
        this.watchId = new SimpleStringProperty(watchId);
        this.quantity = new SimpleStringProperty(quantity);
        this.watchName = new SimpleStringProperty(watchName);
        this.watchPrice = new SimpleStringProperty(watchPrice);
    }


    public String getUserId() {
        return userId.get();
    }

    public void setUserId(String id) {
        this.userId.set(id);
    }

    public String getWatchId() {
        return watchId.get();
    }

    public void setWatchId(String id) {
        this.watchId.set(id);
    }

    public String getQuantity() {
        return quantity.get();
    }

    public void setQuantity(String id) {
        this.quantity.set(id);
    }

    public String getWatchName() {
        return watchName.get();
    }

    public void setWatchName(String id) {
        this.watchName.set(id);
    }

    public String getWatchPrice() {
        return watchPrice.get();
    }

    public void setWatchPrice(String id) {
        this.watchPrice.set(id);
    }

}

