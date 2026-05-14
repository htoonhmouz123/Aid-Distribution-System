package fxProj;


import java.io.Serializable;

public abstract class Beneficiary implements Serializable {
    private int id;
    private String name, city;

    public Beneficiary(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) throws CityNotServedException {
        if (!city.equalsIgnoreCase("Gaza") && !city.equalsIgnoreCase("Rafah") && !city.equalsIgnoreCase("Jenin")
                && !city.equalsIgnoreCase("Tulkarm") && !city.equalsIgnoreCase("Hebron")
                && !city.equalsIgnoreCase("Ramallah") && !city.equalsIgnoreCase("Jerusalem")
                && !city.equalsIgnoreCase("Khan younis")) {
            throw new CityNotServedException(" City is not served");
        } else {
            this.city = city;
        }
    }

    public abstract String getType(); // an abstract method

    public boolean equals(Object obj) {
        if (obj instanceof Beneficiary) {
            Beneficiary b = (Beneficiary) obj;
            if (this.id == b.getId()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Beneficiary ,"+ id + "," + name + "," + city;
    }

}