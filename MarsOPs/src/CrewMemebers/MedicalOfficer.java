/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CrewMemebers;

import Emergency.Emergency;
import Missions.Missions;

/**
 *
 * @author HP
 */
public class MedicalOfficer extends CrewMember{

    public MedicalOfficer(String name, String healthStatus, String[] skills, int fatigueLevel, String IdNo, String currentLocation, boolean isAvaialble) {
        super(name, healthStatus, skills, fatigueLevel, IdNo, currentLocation, isAvaialble);
    }



    @Override
    public void performDuty() {
        System.out.println(getName()+" is performing their duty.");
    }
    
    public void heal()
    {
        System.out.println(getName()+ " is healing.");
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
