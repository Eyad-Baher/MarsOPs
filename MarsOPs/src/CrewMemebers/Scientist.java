/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CrewMemebers;

import Missions.ExplorationMission;
import Emergency.Emergency;
import Missions.Missions;
import Missions.ResearchMission;

/**
 *
 * @author HP
 */
public class Scientist extends CrewMember {

    private String skill;

    public Scientist(String name, String healthStatus, String skill, int fatigueLevel, String IdNo, String currentLocation, boolean isAvaialble) {
        super(name, healthStatus, fatigueLevel, IdNo, currentLocation, isAvaialble);
        this.skill = skill;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        if(skill!=null && !skill.isBlank())
        this.skill = skill;
    }
    
    

    public void research() {
        System.out.println(getName() + " is researching.");
    }

    @Override
    public boolean canPerformMission(Missions mission) {
        if (isIsAvaialble() && isUseable()) {
            if (mission instanceof ExplorationMission) {
                if (this.skill.equals(((ExplorationMission) mission).getRequiredSkill())) {
                    return true;
                }
            }
            else if (mission instanceof ResearchMission){
                if(this.skill.equals(((ResearchMission) mission).getScientificSkill()))
                    return true;
                
            }
        }
        return false;
    }

    @Override
    public boolean canHandleEmergency(Emergency emergency) {
        if(isUseable()&&isIsAvaialble())
            return true;
        else
            return false;
    }

}
