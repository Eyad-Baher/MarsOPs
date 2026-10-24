
package Robots;

import Emergency.Emergency;
import Missions.Missions;


public class ExplorationRover extends RoboticUnit{
    
    public ExplorationRover(String IdNo, String currentLocation, boolean isAvaialble, double batteryLevel, boolean healthStatus) {
        super(IdNo, currentLocation, isAvaialble ,batteryLevel, healthStatus);
    }
    
    @Override
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
    public boolean canHandleEmergency(Emergency emergency)
    {
        return false;
    }
    
    @Override
    public void performDuty()
    {
        System.out.println("Exploration rover "+getIdNo()+" is performing its duty...");
    }
    
    @Override
    public void getStatusSummary()
    {
        System.out.println("---Exploration Rover Information--- ");
        super.getStatusSummary();
    }
    
    
}
