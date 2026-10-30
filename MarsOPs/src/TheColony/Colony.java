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
    private boolean activeMission;
    private Resources colonyResources;  
    
    public Colony(Resources colonyResources) {
        this.colonyResources = colonyResources;
    }

    public void startMission(Missions currentMission) {
        if (currentMission == null) {
            return;
        }

        allMissions.add(currentMission);
        currentMission.setCurrentCondition(currentCondition);   

        boolean enoughResources = hasEnoughResources(currentMission.getMissionResources());

        if (!currentMission.acceptMission(allAgents, activeMission, enoughResources)) {
            System.out.println("Mission " + currentMission.getMissionID() + " rejected: " + currentMission.getFailureReason());
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
