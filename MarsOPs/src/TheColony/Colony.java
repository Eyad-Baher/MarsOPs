/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TheColony;

import ColonyMemebers.ColonyMemebers;
import Emergency.Emergency;
import Emergency.Fire;
import Emergency.Medical;
import Emergency.OxygenLeak;
import Emergency.RoverBreakdown;
import EnvironmentConditions.Environment;
import EnvironmentConditions.Normal;
import Facility.ColonyFacility;
import Facility.CommunicationStation;
import Facility.Habitat;
import Missions.Missions;
import MyResources.Equipment;
import MyResources.LifeSupportSystem;
import MyResources.Resources;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;

public class Colony {

    private ArrayList<ColonyFacility> allFacility = new ArrayList<>();
    private ArrayList<ColonyMemebers> allAgents = new ArrayList<>();
    private ArrayList<Missions> allMissions = new ArrayList<>();
    private Environment currentCondition = new Normal();
    private ArrayList<Emergency> emergencies = new ArrayList<>();
    private ArrayList<ColonyMemebers> responders = new ArrayList<>();
    private int numEmergencyResources;
    private Resources totalResources;
    private boolean activeMission;
    private Resources colonyResources;

    public Colony(Resources totalResources) {
        this.totalResources = totalResources;
        colonyResources=totalResources;
    }

    public void startEmergency() {
        for (Emergency e : emergencies) {
            if (e instanceof OxygenLeak) {
                ((OxygenLeak) e).triggerOxygenLeak();
                ((OxygenLeak) e).handleOxygenLeak(this);
            } else if (e instanceof Fire) {
                ((Fire) e).triggerFire(numEmergencyResources);
                ((Fire) e).handleFire(this);
            } else if (e instanceof Medical) {
                ((Medical) e).triggerMedical();
                ((Medical) e).handleMedical(this);
            } else {
                ((RoverBreakdown) e).triggerRoverBreakdown();
                ((RoverBreakdown) e).handleRoverBreakdown(this);
            }
        }

    }

    public void deductTotalResource(String name, int amount) {
        totalResources.deductResource(name, amount);
    }

    public void distributeTotalResources() {
        int numOfFacilities = allFacility.size();
        for (ColonyFacility f : allFacility) {
            f.getResources().setOxygen(totalResources.getOxygen() / numOfFacilities);
            f.getResources().setFood(totalResources.getFuel() / numOfFacilities);
            f.getResources().setFuel(totalResources.getFuel() / numOfFacilities);
            f.getResources().setMedicalSupplies(totalResources.getMedicalSupplies() / numOfFacilities);
            f.getResources().setSpareParts(totalResources.getSpareParts() / numOfFacilities);
            f.getResources().setWater(totalResources.getWater() / numOfFacilities);
            f.getResources().setBatteryPower(totalResources.getBatteryPower() / numOfFacilities);
        }

    }

    public ArrayList<ColonyMemebers> getResponders() {
        return responders;
    }

    public Resources getTotalResources() {
        return totalResources;
    }

    public void distributeOxygen() {
        int numOfFacilities = allFacility.size();
        if (numOfFacilities == 0) {
            // avoids division by zero
            System.out.println("0 facilities");
            return;

        }
        for (ColonyFacility f : allFacility) {
            f.getResources().setOxygen(totalResources.getOxygen() / numOfFacilities);
        }
    }

    public void distributeSpareParts() {
        int numOfFacilities = allFacility.size();
        if (numOfFacilities == 0) {
            // avoids division by zero
            System.out.println("0 facilities");
            return;

        }
        for (ColonyFacility f : allFacility) {
            f.getResources().setSpareParts(totalResources.getSpareParts() / numOfFacilities);
        }
    }

    public void distributeMedicalSupplies() {
        int numOfFacilities = allFacility.size();
        if (numOfFacilities == 0) {
            // avoids division by zero
            System.out.println("0 facilities");
            return;

        }
        for (ColonyFacility f : allFacility) {
            f.getResources().setMedicalSupplies(totalResources.getMedicalSupplies() / numOfFacilities);
        }
    }

    public void addAgent(ColonyMemebers agent) {
        if (agent != null) {
            allAgents.add(agent);
        }
    }
    
    public void addFacility(ColonyFacility facility)
    {
        if(facility!=null)
        {
            allFacility.add(facility);
            distributeTotalResources();
        }
    }
    
    public void addEmergency(Emergency emergency)
    {
        if(emergency!=null)
            emergencies.add(emergency);
    }

    public void showState() {
        System.out.println("Resources: " + colonyResources);
        System.out.println("Active mission: " + activeMission);
        System.out.println("Agents (" + allAgents.size() + "):");
        for (ColonyMemebers agent : allAgents) {
            agent.getStatusSummary();
            System.out.println();
        }
        System.out.println("Missions (" + allMissions.size() + "):");
        for (Missions m : allMissions) {
            System.out.println("  " + m.getMissionID() + " -> " + m.getState()
                    + " (" + m.getElapsedTime() + "/" + m.getDuration() + ")"
                    + (m.getFailureReason() != null ? " reason: " + m.getFailureReason() : ""));
        }
    }

    public void startMission(Missions currentMission) {
        if (currentMission == null) {
            return;
        }

        allMissions.add(currentMission);
        currentMission.setCurrentCondition(currentCondition);

        boolean enoughResources = hasEnoughResources(currentMission.getMissionResources());

        if (!currentMission.acceptMission(allAgents, activeMission, enoughResources)) {
            System.out.println("Mission " + currentMission.getMissionID() + " cannot be assigned.");
            System.out.println("Reason: " + currentMission.getFailureReason());
            return;
        }

        deductResources(currentMission.getMissionResources());
        activeMission = true;

        currentMission.start();

        for (int time = 0; time < currentMission.getDuration(); time++) {
            if (!currentMission.getState().equals("IN PROGRESS")) {
                break;
            }
            currentMission.updateProgress();
        }

        activeMission = false;

    }

    private boolean hasEnoughResources(Resources needed) {
        if (needed == null) {
            return true;
        }
        return colonyResources.getOxygen() >= needed.getOxygen()
                && colonyResources.getWater() >= needed.getWater()
                && colonyResources.getFood() >= needed.getFood()
                && colonyResources.getFuel() >= needed.getFuel()
                && colonyResources.getBatteryPower() >= needed.getBatteryPower()
                && colonyResources.getMedicalSupplies() >= needed.getMedicalSupplies()
                && colonyResources.getSpareParts() >= needed.getSpareParts();
    }

    private void deductResources(Resources used) {
        if (used == null) {
            return;
        }
        colonyResources.deductResource(Resources.OXYGEN, used.getOxygen());
        colonyResources.deductResource(Resources.WATER, used.getWater());
        colonyResources.deductResource(Resources.FOOD, used.getFood());
        colonyResources.deductResource(Resources.FUEL, used.getFuel());
        colonyResources.deductResource(Resources.BATTERYPOWER, used.getBatteryPower());
        colonyResources.deductResource(Resources.MEDICALSUPPLIES, used.getMedicalSupplies());
        colonyResources.deductResource(Resources.SPAREPARTS, used.getSpareParts());
    }

}
