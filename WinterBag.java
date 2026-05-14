package fxProj;

import java.io.Serializable;

public class WinterBag extends AidItem implements Serializable {

    public WinterBag(String code, String description) {
        super(code, description);
    }

    @Override
    public String getCategory() {
        return "WinterBag";
    }

    @Override
    public String toString() {
        return "WinterBag , " + getCode() + ", " + getDescription();
    }






}
