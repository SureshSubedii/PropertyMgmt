package PropertyMgmt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class PropertyMgmt {
    private String userName;
    private String password;
    private PropertyService propertyService;
    public static List<PropertyManager> propertyManagers = new ArrayList<>();
    public static List<Property> properties = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public PropertyMgmt(PropertyService propertyService) {
        this.propertyService = propertyService;

    }

    private void Home() {

        System.out.print("\n Welcome to the Property Management System \n Enter Your credentials \n");

        System.out.print("Username:");
        userName = sc.nextLine();

        System.out.print("Password:");
        password = sc.nextLine();

        PropertyManager loggedInManager = this.propertyService.login(propertyManagers, userName, password);
        System.out.println("Login Successfull");

        operations(loggedInManager);

    }

    private void operations(PropertyManager loggedIManager) {
        System.out.println("\nSelect one of the operations");
        System.out.println(
                "1.Add new property \n2.Update Existing Property \n3.Delete property \n4.Get Property details \n5.List Properties \n6.Back (Logout)");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                addPropertyOptions(loggedIManager);
                break;
            case 2:
                updateExisting(loggedIManager);
                break;
            case 3:
                deleteOneProperty(loggedIManager.getProperties());
                break;
            case 4:
                getOneProperty();
                break;
            case 5:
                listProperties(loggedIManager.getProperties());
                break;
            case 6:
                Home();
                break;
            default:
                operations(loggedIManager);
        }
        operations(loggedIManager);
    }

    private void addPropertyOptions(PropertyManager loggedIManager) {
        System.out.println("Enter property street name:");
        String street = sc.nextLine();

        System.out.println("Enter city name :");
        String city = sc.nextLine();

        System.out.println("Enter suburbs name :");
        String suburbs = sc.nextLine();

        Property property = new Property(new HashMap<String, String>() {
            {
                put("streetName", street);
                put("city", city);
                put("suburbs", suburbs);
            }
        });
        this.propertyService.addProperty(loggedIManager, property);
    }

    private void updateExisting(PropertyManager loggedIManager) {

        System.out.println("Enter the id of the property");
        int id = sc.nextInt();
        sc.nextLine();

        for (Integer propertyId : loggedIManager.getProperties()) {
            if (id == propertyId) {

                System.out.println("Enter new  street name for property with id " + id + ":");
                String street = sc.nextLine();

                System.out.println("Enter city name :");
                String city = sc.nextLine();

                System.out.println("Enter suburbs name :");
                String suburbs = sc.nextLine();
                this.propertyService.updateExistingProperty(id, street, city, suburbs);
                return;

            }
        }
        System.out.println("Access Denied. You are not authorized to update this property");

    }

    public void getOneProperty() {

        System.out.println("Enter the Id of the property");
        int id = sc.nextInt();
        this.propertyService.getOneProperty(id);
    }

    public void deleteOneProperty(List<Integer> managersProperties) {
        System.out.println("\nEnter the Id of the property");
        this.propertyService.deleteProperty(managersProperties, sc.nextInt());

    }

    public void listProperties(List<Integer> managersProperties) {
        this.propertyService.listProperties(managersProperties);

    }

    public static void main(String[] args) {

        Property property1 = new Property(new HashMap<String, String>() {
            {
                put("streetName", "123 st");
                put("city", "KTM");
                put("suburbs", "unknown");
            }
        });
        Property property2 = new Property(new HashMap<String, String>() {
            {
                put("streetName", "456 st");
                put("city", "BKT");
                put("suburbs", "unknown");
            }
        });
        Property property3 = new Property(new HashMap<String, String>() {
            {
                put("streetName", "789 st");
                put("city", "Panauti");
                put("suburbs", "unknown");
            }
        });

        properties.add(property1);
        properties.add(property2);
        properties.add(property3);
        propertyManagers.add(new PropertyManager("Johnny", "johnny1234", property1.getId(), property2.getId()));
        propertyManagers.add(new PropertyManager("Jackie", "jackie1234", property3.getId()));

        PropertyMgmt pm = new PropertyMgmt(new PropertyService(new PropertyDAO()));

        while (true) {
            try {
                pm.Home();

            } catch (BadCredentialException e) {
                System.out.println(e);
            }

        }

    }
}