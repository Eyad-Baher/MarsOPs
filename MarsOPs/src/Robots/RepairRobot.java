/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Robots;

import Emergency.Emergency;
import Facility.ColonyFacility;
import Missions.Missions;
import MyResources.Equipment;


public class RepairRobot extends RoboticUnit {
    
    public RepairRobot(String IdNo, String currentLocation, boolean isAvaialble, int batteryLevel, boolean healthStatus) {
        super(IdNo, currentLocation, isAvaialble ,batteryLevel, healthStatus);
    }
    
    @Override
    public boolean canPerformMission(Missions mission)
    {
        if(isUseable()&&isIsAvaialble())
            return true;
        else
            return false;
    }
    
    @Override
    public boolean canHandleEmergency(Emergency emergency)
    {
        if(isUseable()&&isIsAvaialble())
            return true;
        else
            return false;
    }
    

    
    @Override
    public void getStatusSummary()
    {
        System.out.println("---Repair Robot Information--- ");
        super.getStatusSummary();
    }
        public void repair(ColonyFacility damagedFacility)
    {
        damagedFacility.setOperationalStatus("OPERATIONAL");
    }
    public void repair(RoboticUnit damagedRobot)
    {
        damagedRobot.setHealthStatus(true);
    }
    public void repair(Equipment equipment,int num)
    {
        equipment.repair(num);
        
    }
    
}
