/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CrewMemebers;

import Emergency.Emergency;
import Missions.Missions;
import Missions.RescueMission;

/**
 *
 * @author HP
 */
public class MedicalOfficer extends CrewMember{

    public MedicalOfficer(String name, String healthStatus, int fatigueLevel, String IdNo, String currentLocation, boolean isAvaialble) {
        super(name, healthStatus, fatigueLevel, IdNo, currentLocation, isAvaialble);
    }



       
    public void heal(CrewMember injuredMember)
    {
        injuredMember.setHealthStatus("HEALTHY");
        
    }
    
        @Override
    public boolean canPerformMission(Missions mission)
    {
        if( isIsAvaialble()&& isUseable() && mission instanceof RescueMission)
        {
            return true;
        }
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
    
}
