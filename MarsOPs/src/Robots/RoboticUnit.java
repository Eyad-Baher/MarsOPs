

package Robots;

import ColonyMemebers.ColonyMemebers;


public abstract class RoboticUnit extends ColonyMemebers{
    private int batteryLevel;
    private boolean healthStatus;

    public RoboticUnit(String IdNo, String currentLocation, boolean isAvaialble, int batteryLevel, boolean healthStatus) {
        super(IdNo, currentLocation, isAvaialble);
        this.batteryLevel = batteryLevel;
        this.healthStatus = healthStatus;
    }

    
    //getters
    public double getBatteryLevel() {
        return batteryLevel;
    }
    public boolean getHealthStatus() {
        return healthStatus;
    }
    
    //setters
    public void setHealthStatus(boolean healthStatus) {
        this.healthStatus = healthStatus;
    }
    
    public void setBatteryLevel(int batteryLevel) {
        if (batteryLevel>=0)
        this.batteryLevel = batteryLevel;
    }
    
    
    
    @Override
    public boolean isUseable()
    {
        if(batteryLevel>0 && healthStatus)
            return true;
        return false;
    }
     
    @Override
    public void advanceTime()
    {
        batteryLevel-=1;
    }
    
    @Override
    public void getStatusSummary()
    {
        System.out.println("ID: "+ getIdNo());
        System.out.println("Current Location: "+getCurrentLocation());
        if (isIsAvaialble())
            System.out.println("Availability: available.");
        else
            System.out.println("Availability: unavailable");
        System.out.println("Battery Level: "+batteryLevel);
        if(healthStatus)
            System.out.println("Health Status: Not damaged");
        else
            System.out.println("Health Status: Damaged");
    }
    
    public void drainBattery(int amount )
    {
        batteryLevel-=amount;
    }
}

