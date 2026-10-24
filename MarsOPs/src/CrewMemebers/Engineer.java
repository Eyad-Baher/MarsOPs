/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CrewMemebers;


import Emergency.Emergency;
import Missions.Missions;
import MyResources.Equipment;

/**
 *
 * @author HP
 */
public class Engineer extends CrewMember{

    public Engineer(String name, String healthStatus, String[] skills, int fatigueLevel, String IdNo, String currentLocation, boolean isAvaialble) {
        super(name, healthStatus, skills, fatigueLevel, IdNo, currentLocation, isAvaialble);
    }


    @Override
    public void performDuty() {
        
    }
    
    public void repair(Equipment equipment,int num)
    {
        equipment.repair(num);
    }
    
    @Override
    public boolean canPerformMission(Missions mission)
    {
        return false;
    }
    
    @Override
    public boolean canHandleEmergency(Emergency emergency)
    {
        return false;
    }
    
    
    
}
