/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Missions;

import ColonyMemebers.ColonyMemebers;
import MyResources.Equipment;
import MyResources.Resources;
import java.util.ArrayList;
import Facility.ColonyFacility;
import Robots.RepairRobot;
import Robots.RoboticUnit;
import CrewMemebers.CrewMember;
import CrewMemebers.Engineer;

/**
 *
 * @author HP
 */
public class RepairMission extends Missions {

    private Equipment targetEquipment;
    private int numOfBroken;
    private ColonyFacility targetFacility = null;

    public RepairMission(Equipment targetEquipment, int numOfBroken, String missionID, int duration, Resources missionResources, Equipment missionEquipment) {
        super(missionID, duration, missionResources, missionEquipment);
        this.targetEquipment = targetEquipment;
        this.numOfBroken = numOfBroken;
    }

        

    public RepairMission(ColonyFacility targetFacility, String missionID, int duration, Resources missionResources, Equipment missionEquipment) {
        super(missionID, duration, missionResources, missionEquipment);
        this.targetFacility = targetFacility;
    }

    @Override
    public boolean acceptMission(ArrayList<ColonyMemebers> candidates, boolean anotherMissionActive, boolean enoughResources) {
        if (candidates == null) {
            fail("not enough agents.");
            return false;
        }

        if (anotherMissionActive == true) {
            fail("Another active mission");
            return false;
        }
        if (enoughResources == false) {
            fail("Not enough Resources");
            return false;

        }
        ArrayList<ColonyMemebers> readyAgents = new ArrayList<>();

        int numRobots = 0;
        int numCrew = 0;

        for (ColonyMemebers candidate : candidates) {

            if (candidate.isIsAvaialble() && candidate.isUseable() && candidate.canPerformMission(this)) {
                if (candidate instanceof CrewMember && numCrew != 1) {
                    numCrew++;
                    readyAgents.add(candidate);
                } else if (candidate instanceof RoboticUnit && numRobots != 1) {
                    numRobots++;
                    readyAgents.add(candidate);
                }
            }
            
            if(numCrew>0 && numRobots>0)
            {
                assignAgents(readyAgents);
                return true;
            }
            
        }
        return false;

    }
    
    @Override
    public void advanceTime()
    {
        boolean object_break = true;
        
        if (this.targetEquipment == null)
        {
         object_break = false;
        }
        for (ColonyMemebers missionAgent : this.agents) {
            
            if(object_break == true)
            {
               if(missionAgent instanceof CrewMember)
               {
                   ((Engineer)missionAgent).repair(targetEquipment,numOfBroken);
                   ((CrewMember) missionAgent).incFatigue((int) (numOfBroken*1.5));
               }else
               {
                   ((RepairRobot)missionAgent).repair(targetEquipment,numOfBroken);
                   ((RoboticUnit)missionAgent).drainBattery((int) (numOfBroken*1.5));
                   
               }
            }else{
                if(missionAgent instanceof CrewMember)
               {
                   ((Engineer)missionAgent).repair(targetFacility);
                   ((CrewMember) missionAgent).incFatigue((int) (20));

               }else
               {
                   ((RepairRobot)missionAgent).repair(targetFacility);
                   ((RoboticUnit)missionAgent).drainBattery((int) (20));

               }

            }
                
                        
        }
        
    }

}
