package fxProj;

import java.io.Serializable;

public class Medicalkit extends AidItem implements Serializable {

    public Medicalkit(String code, String description) {
        super(code, description);
    }

    @Override
    public String getCategory() {
        return "MedicalKit";
    }

    @Override
    public String toString() {
        return "MedicalKit, " + getCode() + ", " + getDescription();
    }



}
