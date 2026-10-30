package Facility;

import MyResources.Resources;


/**
 *
 * @author Lina Islam
 */
public abstract class ColonyFacility {
    private String location;
    private int capacity;
    private String operationalStatus; //change to string
    private Resources resources;

    public ColonyFacility(String location, int capacity, String operationalStatus, Resources resources) {
        this.location = location;
        if(capacity<=0)
            this.capacity=0;
        else
            this.capacity = capacity;
        this.operationalStatus = operationalStatus;
        this.resources = resources;
    }
    
    
    
    public void setLocation(String location) {
        this.location = location;
    }

    public void setCapacity(int capacity) {
        if(capacity<=0)
            this.capacity=0;
        else
            this.capacity = capacity;
    }

    public void setOperationalStatus(String operationalStatus) {
        this.operationalStatus = operationalStatus;
    }

    public String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

    public String isOperationalStatus() {
        return operationalStatus;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        if(resources==null)
            return;
        this.resources = resources;
    }
    
    
    public int getStoredResource(String name)
    {
        if(isOperationalStatus().equals("NON-OPERATIONAL") || resources.getAmount(name)==-1)
            return 0;
        return getResources().getAmount(name);
    }
    
    public boolean withdrawResource(String name, int amount)
    {
        if(isOperationalStatus().equals("NON-OPERATIONAL"))
            return false;
        return resources.deductResource(name, amount);
    }
    
    public void getStatusSummary()
    {
        System.out.println("Location: "+location);
        System.out.println("Status: "+operationalStatus);
        System.out.println("Capacity: "+capacity);
        System.out.println("Resources: "+resources.toString());
    }
}