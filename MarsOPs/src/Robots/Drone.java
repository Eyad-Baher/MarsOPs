
package Robots;
import Emergency.Emergency;
import Missions.Missions;
import Missions.RescueMission;

public class Drone extends RoboticUnit {
    
    
    public Drone(String IdNo, String currentLocation, boolean isAvaialble, int batteryLevel, boolean healthStatus) {
        super(IdNo, currentLocation, isAvaialble, batteryLevel, healthStatus);
        
    }
    
    
    
    @Override
    
    public boolean canPerformMission(Missions mission)
    {
        if(isUseable()&&isIsAvaialble()&& mission instanceof RescueMission)
        {
            return true;
        }
        else
            return false;
    }
    
    
    @Override
    
    public boolean canHandleEmergency(Emergency emergency)
    {
        return false;
    }
    
    
        
    
    @Override
    public void getStatusSummary()
    {
        System.out.println("---Drone Information--- ");
        super.getStatusSummary();
    }
    
    
}
