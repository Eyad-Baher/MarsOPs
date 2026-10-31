/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Missions;

/**
 *
 * @author HP
 */
import ColonyMemebers.ColonyMemebers;
import MyResources.Equipment;
import MyResources.Resources;
import java.util.ArrayList;
import CrewMemebers.CrewMember;
import CrewMemebers.Engineer;
import CrewMemebers.MedicalOfficer;
import Robots.RoboticUnit;

public class RescueMission extends Missions {

    private ColonyMemebers strandedEntity;
    private String missingLocation;
    private boolean entityFound = false;

    public RescueMission(ColonyMemebers strandedEntity, String missingLocation, String missionID, int duration, Resources missionResources, Equipment missionEquipment) {
        super(missionID, duration, missionResources, missionEquipment);
        this.strandedEntity = strandedEntity;
        this.missingLocation = missingLocation;
    }

    public ColonyMemebers getStrandedEntity() {
        return strandedEntity;
    }

    public void setStrandedEntity(ColonyMemebers strandedEntity) {
        if (strandedEntity!=null)
        this.strandedEntity = strandedEntity;
    }

    public String getMissingLocation() {
        return missingLocation;
    }

    public void setMissingLocation(String missingLocation) {
        if (missingLocation!=null && !missingLocation.isBlank())
        this.missingLocation = missingLocation;
    }

    public boolean isEntityFound() {
        return entityFound;
    }

    public void setEntityFound(boolean entityFound) {
        this.entityFound = entityFound;
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
        if (strandedEntity == null) {
            fail("No missing Entity");
            return false;
        }
        if (missingLocation == null || missingLocation.isBlank()) {
            fail("No Location Listed");
            return false;
        }
        ArrayList<ColonyMemebers> readyAgents = new ArrayList<>();

        int numcrew = 0;
        int numRobot = 0;
        for (ColonyMemebers candidate : candidates) {

            if (candidate.canPerformMission(this)) {
                if (candidate instanceof CrewMember && numcrew <= 5) {

                    numcrew++;
                    readyAgents.add(candidate);

                } else if (candidate instanceof RoboticUnit && numRobot <= 5) {
                    numRobot++;
                    readyAgents.add(candidate);
                }
            }

        }
        if (numcrew > 0 && numRobot > 0) {
            if (currentCondition.canDisruptMission(this) == true) {
                fail("Unsuitable Conditions for mission");
                return false;
            }
            assignAgents(readyAgents);

            return true;

        }
        
                    fail("Not Enough Qualified Agents.");
               
        return false;
    }

    @Override
    public void advanceTime() {
        if (strandedEntity != null) {
            for (ColonyMemebers agent : agents) {

                if (strandedEntity instanceof RoboticUnit && agent instanceof Engineer) {
                    ((Engineer) agent).repair((RoboticUnit) strandedEntity);
                    entityFound = true;
                    agent.advanceTime();
                } else if (strandedEntity instanceof CrewMember && agent instanceof MedicalOfficer) {
                    ((MedicalOfficer) agent).heal((CrewMember) strandedEntity);
                    entityFound = true;
                    agent.advanceTime();

                } else if (agent instanceof RoboticUnit) {
                    agent.advanceTime();
                    entityFound = true;
                }

            }
        }
    }

}
