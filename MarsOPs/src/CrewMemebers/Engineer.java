/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CrewMemebers;


import Emergency.Emergency;
import Missions.Missions;
import Missions.RepairMission;
import MyResources.Equipment;
import Facility.ColonyFacility;

/**
 *
 * @author HP
 */
public class Engineer extends CrewMember{

    public Engineer(String name, String healthStatus, int fatigueLevel, String IdNo, String currentLocation, boolean isAvaialble) {
        super(name, healthStatus, fatigueLevel, IdNo, currentLocation, isAvaialble);
    }


    @Override
    public void performDuty() {
        
    }
    public void repair(ColonyFacility damagedFacility)
    {
        damagedFacility.setOperationalStatus(true);
    }
    public void repair(Equipment equipment,int num)
    {
        equipment.repair(num);
        
    }
    
    @Override
    public boolean canPerformMission(Missions mission)
    {
        if( mission instanceof RepairMission)
        {
            return true;
            
        }else return false;
    }
    
    @Override
    public boolean canHandleEmergency(Emergency emergency)
    {
        return false;
    }
    
    
    
}
