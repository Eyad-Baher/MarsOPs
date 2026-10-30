package Facility;

import MyResources.Resources;

/**
 *
 * @author Lina Islam
 */
public class StorageFacility extends ColonyFacility{

    public StorageFacility(String location, int capacity, String operationalStatus, Resources resources) {
        super(location, capacity, operationalStatus, resources);
        if( resources==null || resources.getTotalResources() > getCapacity() )
        {
            throw new IllegalArgumentException("Rejected. (Initial stock exceeds max capacity)");
        }    
    }
    

    public int getTotalStored()
    {
        return getResources().getTotalResources();
    }
    
    public int getFreeSpace()
    {
        return getCapacity()-getTotalStored();
    }
    
    public boolean depositResource(String name, int amount)
    {
        if(isOperationalStatus().equals("NON-OPERAIONAL") || amount<0 || amount>getFreeSpace())
            return false;
        return getResources().addResource(name, amount);
    }

    @Override
    public void getStatusSummary() {
        System.out.println("---Storage Facility---");
        super.getStatusSummary();
        System.out.println("Storage:  ");
        System.out.println("--Total used units: "+getTotalStored()+"   --Free units: "+getFreeSpace());
    }
    
    
    
}