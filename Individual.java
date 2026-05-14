package fxProj;


import java.io.Serializable;

public class Individual extends Beneficiary implements  Serializable, Comparable<Individual> {
    private String status;

    public Individual(int id, String name, String city, String status) {
        super(id, name, city);
        this.status = status;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int compareTo(Individual I) {
        if (this.status.compareToIgnoreCase(I.getStatus()) > 0) {
            return 1;
        } else if (this.status.compareToIgnoreCase(I.getStatus()) < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String getType() {
        return "Individual";
    }

    @Override
    public String toString() {
        return "Individual , "+ getId() + "," + getName() + "," + getCity() + "," + status;
    }

}
