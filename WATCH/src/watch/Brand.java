package watch;

import javafx.beans.property.SimpleStringProperty;

public class Brand {
	private final SimpleStringProperty brandId;
    private final SimpleStringProperty brandName;

    public Brand(String id, String name) {
        this.brandId = new SimpleStringProperty(id);
        this.brandName = new SimpleStringProperty(name);
    }

    public String getBrandName() {
        return brandName.get();
    }

    public void setBrandName(String name) {
        this.brandName.set(name);
    }

    public String getBrandId() {
        return brandId.get();
    }

    public void setBrandId(String id) {
        this.brandId.set(id);
    }
}

