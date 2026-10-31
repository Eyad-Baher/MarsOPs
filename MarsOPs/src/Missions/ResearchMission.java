package Missions;

import Facility.ColonyFacility;
import CrewMemebers.CrewMember;
import ColonyMemebers.ColonyMemebers;
import Facility.Laboratory;
import MyResources.Equipment;
import MyResources.Resources;
import java.util.ArrayList;

public class ResearchMission extends Missions {

    private String scientificSkill;
    private Laboratory experimentLab;
    private String experimentName;
    private boolean experimentStatus;

    public ResearchMission(String scientificSkill, Laboratory experimentLab, String experimentName, String missionID, int duration, Resources missionResources, Equipment missionEquipment) {
        super(missionID, duration, missionResources, missionEquipment);
        this.scientificSkill = scientificSkill;
        this.experimentLab = experimentLab;
        this.experimentName = experimentName;
        this.experimentStatus = false;
    }

    public String getScientificSkill() {
        return scientificSkill;
    }

    public void setScientificSkill(String scientificSkill) {
        this.scientificSkill = scientificSkill;
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
        if (missionEquipment == null) {
            fail("Not enough equipment");
            return false;
        }
        if (experimentLab == null || !experimentLab.isOperationalStatus().equals("OPERATIONAL")) {
            fail("No Available Laboratory");
            return false;
        }

        ArrayList<ColonyMemebers> readyAgents = new ArrayList<>();
        int numAgents = 0;
        for (ColonyMemebers candidate : candidates) {
            if (candidate.canPerformMission(this)) {
                if (candidate instanceof CrewMember && numAgents != 2) {
                    numAgents++;
                    readyAgents.add(candidate);
                }
            }

        }
        if (numAgents > 0) {
            assignAgents(readyAgents);
            return true;
        }
        else{
                    fail("Not Enough Qualified Agents.");
                }
        return false;
    }

    @Override
    public void advanceTime() {
        boolean experiment = true;

        if (this.experimentLab == null) {
            experiment = false;
        }
        if (experiment == true) {
            for (ColonyMemebers missionAgent : this.agents) {
                if (missionAgent instanceof CrewMember) {
                    missionAgent.advanceTime();
                }

            }
            this.experimentStatus = true;
        } else {
            System.out.println("Experiment not conducted");
        }

    }
}
