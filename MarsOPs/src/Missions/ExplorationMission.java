package Missions;

import CrewMemebers.CrewMember;
import ColonyMemebers.ColonyMemebers;
import MyResources.Equipment;
import MyResources.Resources;
import java.util.ArrayList;
import Robots.RoboticUnit;
import RegionToExplore.Location;
/**
 *
 * @author abdel
 */
public class ExplorationMission extends Missions {

    private Location targetLocation;
    private String requiredSkill;

    public ExplorationMission(Location targetLocation, String requiredSkill, String missionID, int duration, Resources missionResources, Equipment missionEquipment) {
        super(missionID, duration, missionResources, missionEquipment);
        this.targetLocation = targetLocation;
        this.requiredSkill = requiredSkill;
    }

    public void setTargetLocation(Location targetLocation) {
        this.targetLocation = targetLocation;
    }

    public Location getTargetLocation() {
        return targetLocation;
    }

    

    public String getRequiredSkill() {
        return requiredSkill;
    }

    public void setRequiredSkill(String requiredSkill) {
        this.requiredSkill = requiredSkill;
    }

    
    @Override
    public boolean acceptMission(ArrayList<ColonyMemebers> candidates, boolean anotherMissionActive, boolean enoughResources) {
        if (candidates == null) {
            fail("Not enough Agents");
            return false;
        } else if (enoughResources == false) {
            fail("Not enough resources");
            return false;
        } else if (anotherMissionActive == true) {
            fail("Another mission Active");
            return false;
        }

        ArrayList<ColonyMemebers> readyAgents = new ArrayList<>();
        int numAgents = 0;
        int numRobots = 0;

        for (ColonyMemebers candidate : candidates) {
            if (candidate.canPerformMission(this)) {
                if (candidate instanceof CrewMember && numAgents != 1) {
                    numAgents++;
                    readyAgents.add(candidate);
                }
            }
            if (candidate instanceof RoboticUnit && numAgents != 1) {
                numRobots++;
                readyAgents.add(candidate);
            }
            if (numAgents > 0 && numRobots > 0) {
                assignAgents(readyAgents);
                return true;
            }
        }
        return false;
    }

    @Override
    public void advanceTime() {
        
        
    }

    
    
    
}
