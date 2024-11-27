package PropertyMgmt;

import java.util.List;
import java.util.stream.Collectors;

public class PropertyDAO {
    public void addProperty(PropertyManager loggedInManager, Property property) {
        PropertyMgmt.properties.add(property);
        loggedInManager.setProperty(property.getId());
    }

    public void updateProperty(int id, String street, String city, String suburbs) {
        PropertyMgmt.properties.replaceAll(property -> {
            if (property.getId() == id) {
                property.setAddress(street, city, suburbs);
            }
            return property;
        });
    }

    public Property getOneProperty(int id) {
        for (Property property : PropertyMgmt.properties) {
            if (property.getId() == id)
                return property;
        }
        return null;

    }

    public void deleteProperty(List<Integer> managersProperties, int propertyId) {
        Property property = getOneProperty(propertyId);
        if (property != null) {
            if (managersProperties.contains(propertyId)) {
                PropertyMgmt.properties.remove(property);
                System.out.println("Property Deleted successfully");
            } else {
                System.out.println("Access Denied. You are not authorized to delete this property");

            }

        } else {
            System.out.println("Property not found with id: " + propertyId);
        }
    }

    public void listProperties(List<Integer> managersProperties){
             List<Property> filteredProperties = PropertyMgmt.properties.stream()
        .filter(property -> managersProperties.contains(property.getId())) 
        .collect(Collectors.toList());       
         System.out.println("\n Your Property Listings");
        System.out.println("id   street   city   suburbs");
        for(Property property: filteredProperties){
            System.out.println(property.getId() + "    " + property.getAddress().get("streetName") + "    " + property.getAddress().get("city") + "    " + property.getAddress().get("suburbs"));
        }

    }
}
