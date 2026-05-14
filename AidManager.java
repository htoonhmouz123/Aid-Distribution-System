package fxProj;

import java.io.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class AidManager implements FileOperations, Serializable {
    private ArrayList<Beneficiary> beneficiaries;
    private ArrayList<AidItem> items;
    private ArrayList<DistributionEvent> distribution;

    public AidManager() {
        beneficiaries = new ArrayList<>();
        items = new ArrayList<>();
        distribution = new ArrayList<>();
    }

    public void RegisterBeneficiary(Beneficiary b) throws DuplicateRegistrationException {
        for (Beneficiary ben : beneficiaries) {
            if (ben.getId() == b.getId()) {
                throw new DuplicateRegistrationException("Duplicate beneficiary");
            }
        }
        beneficiaries.add(b);
    }

    public void AddAidItem(AidItem a) throws DuplicateRegistrationException {
        for (AidItem item : items) {
            if (item.getCode().equals(a.getCode())) {
                throw new DuplicateRegistrationException("Duplicate aid item");
            }
        }
        items.add(a);
    }

    public void RecordAidDistribution(int beneficiaryID, String itemCode, int year, int month, int day)
            throws Exception {
        Beneficiary b = SearchingForByID(beneficiaryID);
        if (b == null) throw new Exception("Beneficiary not found");
        AidItem a = SearchingForByCode(itemCode);
        if (a == null) throw new ItemNotFoundException("Item not found");
        distribution.add(new DistributionEvent(b, a, year, month, day));
    }

    public void DisplayBeneficiaries() {
        for (Beneficiary b : beneficiaries) System.out.println(b);
    }

    public void DisplayAidItems() {
        for (AidItem a : items) System.out.println(a);
    }

    public void DisplayDistributionEvents() {
        for (DistributionEvent d : distribution) System.out.println(d);
    }

    @Override
    public void SaveToTextFile(String fName) throws IOException {
        try (PrintWriter p = new PrintWriter(new File(fName))) {
            for (Beneficiary b : beneficiaries) p.println(b);
            for (AidItem a : items) p.println(a);
            for (DistributionEvent d : distribution) p.println(d);
        }
    }

    @Override
    public void LoadFromTextFile(String fName) throws IOException {
        beneficiaries.clear();
        items.clear();
        distribution.clear();

        try (Scanner input = new Scanner(new File(fName))) {
            while (input.hasNextLine()) {
                String line = input.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(",");
                String type = parts[0].trim();

                if (type.equalsIgnoreCase("family")) {
                    int id = Integer.parseInt(parts[1].trim());
                    String name = parts[2].trim();
                    String city = parts[3].trim();
                    int membersCount = Integer.parseInt(parts[4].trim());
                    beneficiaries.add(new Family(id, name, city, membersCount));
                } else if (type.equalsIgnoreCase("individual")) {
                    int id = Integer.parseInt(parts[1].trim());
                    String name = parts[2].trim();
                    String city = parts[3].trim();
                    String status = parts[4].trim();
                    beneficiaries.add(new Individual(id, name, city, status));
                } else if (type.equalsIgnoreCase("FoodPackage")) {
                    items.add(new FoodPackage(parts[1].trim(), parts[2].trim()));
                } else if (type.equalsIgnoreCase("WinterBag")) {
                    items.add(new WinterBag(parts[1].trim(), parts[2].trim()));
                } else if (type.equalsIgnoreCase("MedicalKit")) {
                    items.add(new Medicalkit(parts[1].trim(), parts[2].trim()));
                } else if (type.equalsIgnoreCase("EmergencyKit")) {
                    items.add(new EmergencyKit(parts[1].trim(), parts[2].trim()));
                } else if (type.equalsIgnoreCase("DistributionEvent")) {
                    int benId = Integer.parseInt(parts[1].trim());
                    String itemCode = parts[2].trim();
                    int year = Integer.parseInt(parts[3].trim());
                    int month = Integer.parseInt(parts[4].trim());
                    int day = Integer.parseInt(parts[5].trim());
                    AidItem item = SearchingForByCode(itemCode);
                    Beneficiary ben = SearchingForByID(benId);
                    if (item != null && ben != null) {
                        distribution.add(new DistributionEvent(ben, item, year, month, day));
                    }
                }
            }
        }
    }

    public AidItem SearchingForByCode(String itemCode) {
        for (AidItem a : items) {
            if (a.getCode().equals(itemCode)) return a;
        }
        return null;
    }

    public Beneficiary SearchingForByID(int id) {
        for (Beneficiary b : beneficiaries) {
            if (b.getId() == id) return b;
        }
        return null;
    }

    @Override
    public void SaveToBinaryFile(String fName) throws IOException, ClassNotFoundException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fName))) {
            out.writeObject(beneficiaries);
            out.writeObject(items);
            out.writeObject(distribution);
        }
    }

    @Override
    public void LoadFromBinaryFile(String fName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fName))) {
            beneficiaries = (ArrayList<Beneficiary>) in.readObject();
            items = (ArrayList<AidItem>) in.readObject();
            distribution = (ArrayList<DistributionEvent>) in.readObject();
        }
    }

    public int numFamilyServed(String city) {
        int counter = 0;
        for (DistributionEvent d : distribution) {
            Beneficiary b = d.getBeneficiary();
            if (b instanceof Family && b.getCity().equalsIgnoreCase(city)) {
                counter++;
            }
        }
        return counter;
    }

    public int TotalNumAidItemDistributed(String city) {
        if (city == null || city.isEmpty()) return distribution.size();
        int count = 0;
        for (DistributionEvent d : distribution) {
            if (d.getBeneficiary().getCity().equalsIgnoreCase(city)) count++;
        }
        return count;
    }

    public int CountDistributedItemCategory(String category) throws ItemNotFoundException {
        int count = 0;
        for (DistributionEvent d : distribution) {
            if (d.getItem().getCategory().equalsIgnoreCase(category)) count++;
        }
        return count;
    }

    public ArrayList<Beneficiary> beneficiariesServedBtw2dates(GregorianCalendar from, GregorianCalendar to) {
        ArrayList<Beneficiary> result = new ArrayList<>();
        for (DistributionEvent d : distribution) {

            GregorianCalendar eventDate = d.date;
            if (!eventDate.before(from) && !eventDate.after(to)) {
                Beneficiary b = d.getBeneficiary();
                if (!result.contains(b)) result.add(b);
            }
        }
        return result;
    }

    public String mostServedCity() {
        if (distribution.isEmpty()) return "No city served";

        ArrayList<String> cities = new ArrayList<>();
        ArrayList<Integer> counts = new ArrayList<>();

        for (DistributionEvent d : distribution) {
            String city = d.getBeneficiary().getCity();
            boolean found = false;
            for (int i = 0; i < cities.size(); i++) {
                if (cities.get(i).equalsIgnoreCase(city)) {
                    counts.set(i, counts.get(i) + 1);
                    found = true;
                    break;
                }
            }
            if (!found) {
                cities.add(city);
                counts.add(1);
            }
        }

        int max = 0;
        for (int c : counts) if (c > max) max = c;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cities.size(); i++) {
            if (counts.get(i) == max) {
                if (sb.length() > 0) sb.append(", ");
                sb.append(cities.get(i));
            }
        }
        return sb.toString();
    }

    public ArrayList<Beneficiary> getBeneficiaries() { return beneficiaries; }
    public ArrayList<DistributionEvent> getDistribution() { return distribution; }
    public ArrayList<AidItem> getItems() { return items; }
}