package fxProj;


import java.io.Serializable;

public class Family extends Beneficiary implements Serializable, Comparable<Family> {
    private int membersCount;

    public Family(int id, String name, String city, int membersCount) {
        super(id, name, city);
        this.membersCount = membersCount;
    }

    public int getMembersCount() {
        return membersCount;
    }

    public void setMembersCount(int membersCount) {
        this.membersCount = membersCount;
    }

    @Override
    public int compareTo(Family f) {
        if (this.membersCount > f.membersCount) {
            return 1;
        }else if (this.membersCount < f.membersCount) {
            return -1;
        }else {
            return 0;
        }
    }

    @Override
    public String getType() {
        return "family ";
    }

    @Override
    public String toString() {
        return "Family , "+ getId() + "," + getName() + "," + getCity() + ","+ membersCount ;
    }


}

