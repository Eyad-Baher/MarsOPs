package Missions;

import CrewMemebers.CrewMember;
import ColonyMemebers.ColonyMemebers;
import MyResources.Resources;
import java.util.ArrayList;
import jdk.jfr.FlightRecorder;
import Robots.RoboticUnit;

/**
 *
 * @author abdel
 */
public class ExplorationMission extends Missions {

    private String targetLocation;
    private String requiredSkill;

    public ExplorationMission(String missionID, int duration, Resources missionResources, String targetLocation, String requiredSkill) {
        super(missionID, duration, missionResources);
        this.targetLocation = targetLocation;
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
            fail("Another mission A ctive");
            return false;
        }

        ArrayList<ColonyMemebers> readyAgents = new ArrayList<>();
        int numAgents = 0;
        int numRobots = 0;

        for (ColonyMemebers candidate : candidates) {
            if (candidate.isIsAvaialble() && candidate.isUseable() && candidate.canPerformMission(this)) {
                if (candidate instanceof CrewMember && numAgents!=1) {
                    numAgents++;
                    readyAgents.add(candidate);
                }
            }
            if (candidate instanceof RoboticUnit && numAgents!=1) {
                numRobots++;
                readyAgents.add(candidate);
            }
        if (numAgents>0 && numRobots>0){
            assignAgents(readyAgents);
          return true;
        }
        }
return false;
    }

}
