/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package marsops;

import CrewMemebers.CrewMember;
import CrewMemebers.Engineer;
import CrewMemebers.Scientist;
import Emergency.OxygenLeak;
import Facility.Habitat;
import Facility.Laboratory;
import Missions.ExplorationMission;
import Missions.ResearchMission;
import MyResources.Equipment;
import MyResources.Resources;
import RegionToExplore.Location;
import Robots.ExplorationRover;
import Robots.RepairRobot;
import TheColony.Colony;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
 


public class MarsOPs {

    /**
     */
    
    // Scenario 1: Successful Exploration
    public static void scenarioONE()
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
        Location  target = new Location("Olympus Ridge");

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

    // Scenario 2: Mission Rejection
public static void scenarioTWO()
{
    System.out.println("\n===== Scenario 2: Mission Rejection =====");

    Resources colonyResources = new Resources(100, 100, 100, 100, 100, 100, 100);
    Colony colonyTWO = new Colony(colonyResources);

    Engineer engineer = new Engineer("Eng. Omar", "HEALTHY", 10, "E-01", "Habitat", true);
    colonyTWO.addAgent(engineer);

    Equipment equipment = new Equipment("Sample Drill", 5, false);
    Resources missionResources = new Resources(10, 10, 10, 10, 10, 0, 0);
    Location target = new Location("Valles Marineris");
    ExplorationMission mission = new ExplorationMission(target, "Geology", "EXP-02", 3, missionResources, equipment);

    System.out.println("\n--- System state BEFORE submission ---");
    colonyTWO.showState();
    System.out.println("Mission " + mission.getMissionID() + ": " + mission.getState());

    System.out.println("\n--- Submitting mission ---");
    colonyTWO.startMission(mission);

    System.out.println("\n--- System state AFTER submission ---");
    colonyTWO.showState();
    System.out.println("Mission " + mission.getMissionID() + ": " + mission.getState());
    System.out.println("Mission ever active: " + (mission.getElapsedTime() > 0 || mission.getState().equals("IN PROGRESS")));
    System.out.println("Location " + target.getLocationName() + " explored: " + target.isExplored());
}
    

    public static void scenarioTHREE()
    {
        System.out.println("\n===== Scenario 3: Resource Shortage =====");
        Resources colonyResources = new Resources(10, 10, 10, 10, 10, 10, 10);
        Colony colonyTHREE = new Colony(colonyResources);
        
        ArrayList<Equipment> labEquipment= new ArrayList<>();
        Equipment equipment1 = new Equipment("Seismometer", 3, false);
        Equipment equipment2 = new Equipment("Microscope", 10, false);
        labEquipment.add(equipment1); labEquipment.add(equipment2);
        ArrayList<CrewMember> labPersonnel = new ArrayList<>();
        Engineer e1= new Engineer("Eng. Ahmed", "HEALTHY", 4, "E-01", "Arcadia Outpost", true);
        Scientist s1= new Scientist("Scientist Mohamed", "HEALTHY", "Soil analysis", 5, "S-01", "Arcadia Outpost", true);
        labPersonnel.add(e1); labPersonnel.add(s1);
        Resources labResources = new Resources(50, 50, 50, 50, 50, 50, 50);
        Laboratory lab=new Laboratory(true, 5, labEquipment, labPersonnel, "Sector 04", labResources);
        colonyTHREE.addFacility(lab);
        
        //ResearchMission researchMission= new ResearchMission("Soil analysis", lab, "Marsquake Monitoring", "M-01", 3, )
        
    }
    
    public static void scenarioFOUR()
    {
        System.out.println("\n===== Scenario 4: Emergency Handling =====");
        
        Resources colonyResources = new Resources(100, 100, 100, 100, 100, 100, 100);
        Colony colonyFOUR = new Colony(colonyResources);
        
        Engineer engineer= new Engineer("Eng. Alia", "HEALTHY", 5, "E-01", "Hellas Basin", true);
        RepairRobot repairRobot= new RepairRobot("R-01", "Hellas Basin", true, 100, true);
        colonyFOUR.addAgent(engineer);
        colonyFOUR.addAgent(repairRobot);
        
        Resources habitatResources= new Resources(50,50,50,50,50,50,50);
        Habitat habitat= new Habitat("Sector 01", 400, "OPERATIONAL", habitatResources);
        colonyFOUR.addFacility(habitat);
        OxygenLeak oxygenLeak = new OxygenLeak(habitat, 5, colonyFOUR.getResponders());
        colonyFOUR.startEmergency();
        
        
    }
    


    public static void main(String[] args) {
       // new MarsOPs().scenarioONE();
       scenarioTWO();
    }
    
}