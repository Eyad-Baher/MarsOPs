
package Robots;
import Emergency.Emergency;
import Missions.Missions;


public class Drone extends RoboticUnit {
    
    
    public Drone(String IdNo, String currentLocation, boolean isAvaialble, double batteryLevel, boolean healthStatus) {
        super(IdNo, currentLocation, isAvaialble, batteryLevel, healthStatus);
        
    }
    
    
    
    @Override
    //problem: it's not directly mentioned that drones perform missions
    //Maybe it's part of the exploration mission?
    public boolean canPerformMission(Missions mission)
    {
        if(isUseable()&&isIsAvaialble())
        {
            return true;
        }
        else
            return false;
    }
    
    
    @Override
    //problem: drones do not handle emergencies.
    public boolean canHandleEmergency(Emergency emergency)
    {
        return false;
    }
    
    
    @Override
    public void performDuty()
    {
        System.out.println("Drone "+getIdNo()+ " is performing its duty...");
    }
    
    
    @Override
    public void getStatusSummary()
    {
        System.out.println("---Drone Information--- ");
        super.getStatusSummary();
    }
    
    
}
