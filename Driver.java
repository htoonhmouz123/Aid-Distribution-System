package fxProj;


import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) throws Exception {
        AidManager m = new AidManager();
        Scanner input = new Scanner(System.in);

        int number = -1;
        do { // print the menu first
            System.out.println("  Main Menue");
            System.out.println("1. Register Beneficiary ");
            System.out.println("2. Add Aid Item");
            System.out.println("3. Record Aid Distribution ");
            System.out.println("4. Display All Beneficiaries");
            System.out.println("5. Display All Aid Items");
            System.out.println("6. Display All Distribution Events");
            System.out.println("7. Generate Statistical Reports");
            System.out.println("8. Save to Text File");
            System.out.println("9. Save to Binary File");
            System.out.println("10. Load from Text File ");
            System.out.println("11. Load from Binary File ");
            System.out.println("0. Exit");
            System.out.println("Enter a number: ");

            try {
                number = input.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("enter a valid number");
                input.nextLine();
                continue;
            }
            switch (number) {

                case 0:
                    System.out.println("Exit");
                    System.exit(0);
                    break;

                case 1:
                    try {
                        System.out.print("choose family or individual :");
                        String typeBen = input.next();
                        if (!typeBen.equalsIgnoreCase("family") && !typeBen.equalsIgnoreCase("individual")) {
                            System.out.println("invalid type");
                            input.nextLine();
                            continue;
                        }

                        System.out.print("Enter Id:");
                        int id = input.nextInt();
                        System.out.print("Enter name:");
                        String name = input.next();
                        System.out.print("Enter City:");
                        String city = input.next();

                        if (typeBen.equalsIgnoreCase("family")) {
                            System.out.println("enter membersCount: ");
                            int membersCount = input.nextInt();
                            if (membersCount <= 0) {
                                System.out.println("members count should be above zero");
                            }
                            m.RegisterBeneficiary(new Family(id, name, city, membersCount));
                        } else if (typeBen.equalsIgnoreCase("individual")) {
                            System.out.println("enter status: ");
                            String status = input.next();
                            m.RegisterBeneficiary(new Individual(id, name, city, status));
                        }
                    } catch (DuplicateRegistrationException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    } catch (InputMismatchException ex) {
                        System.out.println("invalid input");
                    }
                    break;

                case 2:
                    try {
                        System.out.print("enter item code:");
                        String code = input.next();
                        System.out.print("Enter description::");
                        String description = input.next();
                        System.out.print("Enter categorys(foodPackage - medicalKit - emergency - winterBag):");
                        String categorys = input.next();
                        if (categorys.equalsIgnoreCase("MedicalKit")) {
                            m.AddAidItem(new Medicalkit(code, description));
                        } else if (categorys.equalsIgnoreCase("FoodPackage")) {
                            m.AddAidItem(new FoodPackage(code, description));
                        } else if (categorys.equalsIgnoreCase("EmergencyKit")) {
                            m.AddAidItem(new EmergencyKit(code, description));
                        } else if (categorys.equalsIgnoreCase("WinterBag")) {
                            m.AddAidItem(new WinterBag(code, description));
                        } else {
                            System.out.println("invalid category");
                        }
                    } catch (DuplicateRegistrationException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    } catch (InputMismatchException ex) {
                        System.out.println("invalid input");
                    }
                    break;

                case 3:
                    try {
                        System.out.println("enter beneficiary Id:");
                        int benificiaryID = input.nextInt();
                        Beneficiary b = m.SearchingForByID(benificiaryID);
                        System.out.println("enter Item code:");
                        String itemCode = input.next();
                        AidItem a = m.SearchingForByCode(itemCode);
                        System.out.println("enter year: ");
                        int year = input.nextInt();
                        if (year <= 0) {
                            System.out.println("invalid year");
                        }
                        System.out.println("enter month: ");
                        int month = input.nextInt();
                        if (month < 1 && month > 12) {
                            System.out.println("invalid month");
                        }
                        System.out.println("enter day: ");
                        int day = input.nextInt();
                        if (day < 1 && day > 31) {
                            System.out.println("invalid day");
                        }
                        m.RecordAidDistribution(benificiaryID, itemCode, year, month, day);
                    } catch (ItemNotFoundException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    } catch (InputMismatchException ex) {
                        System.out.println("invalid input");
                    } catch (Exception ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                    break;

                case 4:
                    m.DisplayBeneficiaries();
                    break;
                case 5:
                    m.DisplayAidItems();
                    break;
                case 6:
                    m.DisplayDistributionEvents();
                    break;
                case 7:
                    int num2 = -1;
                    do {
                        System.out.println("Statistical Reports");
                        System.out.println("1. Number of families served in a city ");
                        System.out.println("2. Total aid items distributed");
                        System.out.println("3. Count aid items by category");
                        System.out.println("4. Beneficiaries served between two dates");
                        System.out.println("5. Most served city");
                        System.out.println("0. Back to main menu ");
                        try {
                            num2 = input.nextInt();
                        } catch (InputMismatchException ex) {
                            System.out.println("enter valid number");
                            input.nextLine();
                            continue;
                        }
                        switch (num2) {
                            case 0:
                                System.out.println("back to the main menue");
                                break;
                            case 1:
                                System.out.println("enter city:");
                                String citys = input.next();
                                System.out.println("numOfFamilyServer = " + m.numFamilyServed(citys));
                                break;
                            case 2:
                                System.out.println("enter city:");
                                String city2 = input.next();
                                System.out.println("TotalNumAidItemDistributed = " + m.TotalNumAidItemDistributed(city2));
                                break;
                            case 3:
                                try {
                                    System.out.println("enter city:");
                                    String city3 = input.next();
                                    m.CountDistributedItemCategory(city3);
                                } catch (ItemNotFoundException ex) {
                                    System.out.println("Error: " + ex.getMessage());
                                }
                                break;
                            case 4:
                                System.out.println("enter from year: ");
                                int y1 = 0;
                                try {
                                    y1 = input.nextInt();
                                } catch (InputMismatchException ex) {
                                    System.out.println("enter valid number");
                                }
                                if (y1 <= 0) {
                                    System.out.println("invalid year");
                                }
                                System.out.println("enter from month: ");
                                int m1 = 0;
                                try {
                                    m1 = input.nextInt();
                                } catch (InputMismatchException ex) {
                                    System.out.println("enter valid number");
                                }
                                if (m1 < 1 && m1 > 12) {
                                    System.out.println("invalid month");
                                }
                                System.out.println("enter from day: ");
                                int d1 = 0;
                                try {
                                    d1 = input.nextInt();
                                } catch (InputMismatchException ex) {
                                    System.out.println("enter valid number");
                                }
                                if (d1 < 1 && d1 > 31) {
                                    System.out.println("invalid day");
                                }
                                GregorianCalendar from = new GregorianCalendar(y1, m1 - 1, d1);

                                System.out.println("enter to year: ");
                                int y2 = input.nextInt();
                                System.out.println("enter to month: ");
                                int m2 = input.nextInt();
                                System.out.println("enter to day: ");
                                int d2 = input.nextInt();
                                GregorianCalendar to = new GregorianCalendar(y2, m2 - 1, d2);
                                ArrayList<Beneficiary> list = m.beneficiariesServedBtw2dates(from, to);

                                System.out.println("beneficiariesServedBtw2dates are:");
                                for (int i = 0; i < list.size(); i++) {
                                    System.out.println(list.get(i));
                                }
                                break;
                            case 5:
                                System.out.println("most served city / cities : " + m.mostServedCity());
                                break;
                            default:
                                System.out.println("invalid number");
                        }
                    } while (num2 != 0);
                    break;
                case 8:
                    input.nextLine();
                    System.out.println("enter file name: ");
                    String tfName = input.nextLine();
                    try {
                        m.SaveToTextFile(tfName);

                    } catch (IOException ex) {
                        System.out.println("error saving to txtFile");
                    }
                    break;
                case 9:
                    input.nextLine();

                    System.out.println("enter file name: ");
                    String SBfName = input.nextLine();
                    try {
                        m.SaveToBinaryFile(SBfName);

                    } catch (IOException ex) {
                        System.out.println("error saving to binaryFile");
                    } catch (ClassNotFoundException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                    break;

                case 10:

                    input.nextLine();

                    System.out.println("enter file name: ");
                    String lfName = input.nextLine();
                    try {
                        m.LoadFromTextFile(lfName);

                    } catch (IOException ex) {
                        System.out.println("error loading to txtFile");
                    }
                    break;
                case 11:
                    input.nextLine();

                    System.out.println("enter file name: ");
                    String LBfName = input.nextLine();
                    try {
                        m.LoadFromBinaryFile(LBfName);

                    } catch (IOException ex) {
                        System.out.println("error loading to binaryFile");
                    } catch (ClassNotFoundException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                    break;
                default:
                    System.out.println("invalid number");
            }
        } while (number != 0);
        input.close();
    }
}