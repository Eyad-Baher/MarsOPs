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

import Robots.RoboticUnit;
import CrewMemebers.CrewMember;

/**
 *
 * @author HP
 */
public class RepairMission extends Missions {

    private Equipment targetEquipment;
    private ColonyFacility targetFacility;

    public RepairMission(Equipment targetEquipment, String missionID, int duration, Resources missionResources, Equipment missionEquipment) {
        super(missionID, duration, missionResources, missionEquipment);
        this.targetEquipment = targetEquipment;
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

}
