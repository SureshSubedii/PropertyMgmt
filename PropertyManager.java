package PropertyMgmt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PropertyManager {
    private static int idCounter = 0;
    private final int id;
    private final String name;
    private final String password;
    private final List<Integer> propertyIds; 

    public PropertyManager(String name, String password, Integer... propertyIds) {
        this.name = name;
        this.id = ++idCounter;
        this.password = password;
        this.propertyIds = new ArrayList<>();
           Collections.addAll(this.propertyIds, propertyIds);

    }
    public String getName(){
        return name;
    }
    public String getPassword(){
        return password;
    }
    public int getId(){
        return id;
    }
    public List<Integer> getProperties(){
        return propertyIds;
    }
    public void setProperty(int propetyId){
        this.propertyIds.add(propetyId);
    }

}
