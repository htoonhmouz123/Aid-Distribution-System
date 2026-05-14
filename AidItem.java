package fxProj;

import java.io.Serializable;

public abstract class AidItem implements Serializable{
    private String code;
    private String description;

    public AidItem(String code, String description) {
        super();
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract String getCategory();

    @Override
    public String toString() {
        return "AidItem ," + code + ", " + description;
    }

}
