package PropertyMgmt;

import java.util.HashMap;
import java.util.Map;

public class Property {
    private static int idCounter = 0;
    private int id;
    private Map<String, String> address;

    public Property(Map<String, String> address) {
        this.address = address;
        this.id = ++idCounter;
    }
    public Integer getId(){
        return id;
    }
    public Map<String, String> getAddress(){
        return address;
    }
    public void setAddress(String street, String city, String suburbs){
        this.address =  new HashMap<>();
        this.address.put("streetName", street);
        this.address.put("city", city);
        this.address.put("suburbs", city);


    }
    @Override
    public String toString(){
        String managerName = "";
        for(PropertyManager manager: PropertyMgmt.propertyManagers){
            if(manager.getProperties().contains(getId())) managerName = manager.getName();
        }
        return "\nThe property details is \n" + "id = " + getId() + " \nstreet = " + getAddress().get("streetName") + ", \ncity = " + getAddress().get("city") + ", \nsuburbs = " + getAddress().get("suburbs") + "\nManaged By = " + managerName;
    }

}
