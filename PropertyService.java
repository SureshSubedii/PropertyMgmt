package PropertyMgmt;

import java.util.List;

public class PropertyService {
    PropertyDAO propertyDAO;

    public PropertyService(PropertyDAO propertyDAO) {
        this.propertyDAO = propertyDAO;
    }

    public PropertyManager login(List<PropertyManager> propertyManagers, String name, String password) {
        for (PropertyManager manager : propertyManagers) {
            if (manager.getName().equals(name) && manager.getPassword().equals(password)) {
                return manager;
            }
        }
        throw new BadCredentialException("Access Denied: Invalid username or password. \n");
    }

    public void addProperty(PropertyManager loggedInManager, Property property) {
        this.propertyDAO.addProperty(loggedInManager, property);
        System.out.println("Property Added Successfully");
    }

    public void updateExistingProperty(int id, String street, String city, String suburbs) {
        this.propertyDAO.updateProperty(id, street, city, suburbs);
        System.out.println("Update Successfull");

    }

    public void getOneProperty(int id) {
        Property property = this.propertyDAO.getOneProperty(id);
        if (property != null) {
            System.out.println(property);
        } else {
            System.out.println("No property with the given id found");
        }
    }

    public void deleteProperty(List<Integer> properties, int propertyId) {
        this.propertyDAO.deleteProperty(properties, propertyId);
    }
    public void listProperties(List<Integer> managersProperties){
        this.propertyDAO.listProperties(managersProperties);
        
    }

}
