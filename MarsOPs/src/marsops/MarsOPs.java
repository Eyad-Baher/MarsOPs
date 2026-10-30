/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package marsops;

import CrewMemebers.Scientist;
import Missions.ExplorationMission;
import MyResources.Equipment;
import MyResources.Resources;
import RegionToExplore.Location;
import Robots.ExplorationRover;
import TheColony.Colony;

/**
 *
 * @author HP
 */
 


public class MarsOPs {

    /**
     */
    
    // Scenario 1: Successful Exploration
    public void scenarioONE()
    {
        System.out.println("===== Scenario 1: Successful Exploration =====");

        Resources colonyResources = new Resources(100, 100, 100, 100, 100, 100, 100);
        Colony colonyONE = new Colony(colonyResources);

        Scientist scientist = new Scientist("Dr. Sara", "HEALTHY", "Geology", 10, "S-01", "Habitat", true);
        ExplorationRover rover = new ExplorationRover("R-01", "Habitat", true, 100, true);
        colonyONE.addAgent(scientist);
        colonyONE.addAgent(rover);

        Equipment equipment = new Equipment("Sample Drill", 5, false);
        Resources missionResources = new Resources(10, 10, 10, 10, 10, 0, 0);
        Location target = new Location("Olympus Ridge");

        ExplorationMission mission = new ExplorationMission(target, "Geology", "EXP-01", 3, missionResources, equipment);

        System.out.println("\n--- System state BEFORE execution ---");
        colonyONE.showState();
        System.out.println("Mission " + mission.getMissionID() + ": " + mission.getState());
        System.out.println("Location " + target.getLocationName() + " explored: " + target.isExplored());

        System.out.println("\n--- Executing mission ---");
        colonyONE.startMission(mission);

        System.out.println("\n--- System state AFTER execution ---");
        colonyONE.showState();
        System.out.println("Location " + target.getLocationName() + " explored: " + target.isExplored());
    }

    public static void main(String[] args) {
        new MarsOPs().scenarioONE();
    }
    
}