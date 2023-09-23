package watch;

import javafx.beans.property.SimpleStringProperty;

public class Watch {
	private final SimpleStringProperty watchId;
    private final SimpleStringProperty watchName;
    private final SimpleStringProperty watchStock;
    private final SimpleStringProperty watchPrice;
    private SimpleStringProperty categoryId;

    public Watch(String watchId, String watchName, String watchStock, String watchPrice, String categoryId) {
        this.categoryId = new SimpleStringProperty(categoryId);
        this.watchId = new SimpleStringProperty(watchId);
		this.watchName = new SimpleStringProperty(watchName);
        this.watchStock = new SimpleStringProperty(watchStock);
        this.watchPrice = new SimpleStringProperty(watchPrice);
    }

    public String getWatchName() {
        return watchName.get();
    }

    public void setWatchName(String name) {
        this.watchName.set(name);
    }

    public String getBrandId() {
        return categoryId.get();
    }

    public void setBrandId(String id) {
        this.categoryId.set(id);
    }

    public String getWatchId() {
        return watchId.get();
    }

    public void setWatchId(String id) {
        this.watchId.set(id);
    }

    public String getWatchStock() {
        return watchStock.get();
    }

    public void setWatchStock(String id) {
        this.watchStock.set(id);
    }

    public String getWatchPrice() {
        return watchPrice.get();
    }

    public void setWatchPrice(String id) {
        this.watchPrice.set(id);
    }
}

