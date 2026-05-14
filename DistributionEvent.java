package fxProj;



import java.io.Serializable;
import java.util.GregorianCalendar;

public class DistributionEvent implements Serializable {
    AidItem item;
    Beneficiary beneficiary;
    GregorianCalendar date;

    public DistributionEvent(Beneficiary beneficiary, AidItem item, int year, int month , int day) {
        this.item = item;
        this.beneficiary = beneficiary;
        this.date = new GregorianCalendar(year, month-1, day);
    }


    public AidItem getItem() {
        return item;
    }

    public Integer getBeneficiaryId() {
        return beneficiary.getId();
    }
    public String getItemCode() {
        return item.getCode();
    }
    public void setItem(AidItem item) {
        this.item = item;
    }

    public Beneficiary getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(Beneficiary beneficiary) {
        this.beneficiary = beneficiary;
    }

    public String getDate() {
        int y = date.get(GregorianCalendar.YEAR);
        int m = date.get(GregorianCalendar.MONTH)+1;
        int d = date.get(GregorianCalendar.DAY_OF_MONTH);
        return d+ "-"+m+"-"+y;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DistributionEvent, " + item + "," + beneficiary + "," + date.get(GregorianCalendar.YEAR) + "," + (date.get(GregorianCalendar.MONTH)+1) + "," + date.get(GregorianCalendar.DAY_OF_MONTH) ;
    }

}
