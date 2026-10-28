
package Robots;

import Emergency.Emergency;
import Missions.ExplorationMission;
import Missions.Missions;


public class ExplorationRover extends RoboticUnit{
    
    public ExplorationRover(String IdNo, String currentLocation, boolean isAvaialble, int batteryLevel, boolean healthStatus) {
        super(IdNo, currentLocation, isAvaialble ,batteryLevel, healthStatus);
    }
    
    @Override
    public boolean canPerformMission(Missions mission)
    {
        if(isUseable()&&isIsAvaialble()&& mission instanceof ExplorationMission )
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
        System.out.println("---Exploration Rover Information--- ");
        super.getStatusSummary();
    }
    
    
}
