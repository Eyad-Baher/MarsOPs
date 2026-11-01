package marsops;

import CrewMemebers.CrewMember;
import CrewMemebers.Engineer;
import CrewMemebers.MedicalOfficer;
import CrewMemebers.Scientist;
import Emergency.OxygenLeak;
import EnvironmentConditions.DustStorm;
import EnvironmentConditions.Normal;
import Facility.Habitat;
import Facility.Laboratory;
import Facility.PowerStation;
import Missions.ExplorationMission;
import Missions.RepairMission;
import Missions.RescueMission;
import Missions.ResearchMission;
import MyResources.Equipment;
import MyResources.Resources;
import RegionToExplore.Location;
import Robots.Drone;
import Robots.ExplorationRover;
import Robots.RepairRobot;
import TheColony.Colony;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class MarsOPs {

    // Scenario 1: successful exploration
    public static void scenarioONE() {
        System.out.println("---------- Scenario 1: Successful Exploration ----------");

        Colony colony = new Colony(new Resources(100, 100, 100, 100, 100, 100, 100));

        Scientist sara = new Scientist("Dr. Sara", "HEALTHY", "Geology", 10, "S-01", "Habitat", true);
        ExplorationRover rover = new ExplorationRover("R-01", "Habitat", true, 100, true);
        colony.addAgent(sara);
        colony.addAgent(rover);

        Location ridge = new Location("Olympus Ridge");
        Equipment drill = new Equipment("Sample Drill", 5, false);
        Resources needed = new Resources(10, 10, 10, 10, 10, 0, 0);
        ExplorationMission mission = new ExplorationMission(ridge, "Geology", "EXP-01", 3, needed, drill);

        System.out.println("\nBefore:");
        colony.showState();
        System.out.println("Olympus Ridge explored? " + ridge.isExplored());

        System.out.println("\nStarting mission...");
        colony.startMission(mission);

        System.out.println("\nAfter:");
        colony.showState();
        System.out.println("Olympus Ridge explored? " + ridge.isExplored());
        System.out.println();
    }

    // Scenario 2: mission rejected because the agent can't do it
    public static void scenarioTWO() {
        System.out.println("---------- Scenario 2: Mission Rejection ----------");

        Colony colony = new Colony(new Resources(100, 100, 100, 100, 100, 100, 100));

        // an engineer can't do exploration
        Engineer omar = new Engineer("Eng. Omar", "HEALTHY", 10, "E-01", "Habitat", true);
        colony.addAgent(omar);

        Location marineris = new Location("Valles Marineris");
        Equipment drill = new Equipment("Sample Drill", 5, false);
        Resources needed = new Resources(10, 10, 10, 10, 10, 0, 0);
        ExplorationMission mission = new ExplorationMission(marineris, "Geology", "EXP-02", 3, needed, drill);

        System.out.println("\nBefore:");
        colony.showState();

        System.out.println("\nSubmitting mission...");
        colony.startMission(mission);

        System.out.println("\nAfter:");
        colony.showState();
        System.out.println("Mission state: " + mission.getState());
        System.out.println();
    }

    // Scenario 3: mission rejected because the colony doesn't have enough resources
    public static void scenarioTHREE() {
        System.out.println("---------- Scenario 3: Resource Shortage ----------");

        Colony colony = new Colony(new Resources(10, 10, 10, 10, 10, 10, 10));

        Engineer ahmed = new Engineer("Eng. Ahmed", "HEALTHY", 4, "E-01", "Arcadia Outpost", true);
        Scientist mohamed = new Scientist("Scientist Mohamed", "HEALTHY", "Soil analysis", 5, "S-01", "Arcadia Outpost", true);
        colony.addAgent(ahmed);
        colony.addAgent(mohamed);

        Equipment seismometer = new Equipment("Seismometer", 3, false);
        Equipment microscope = new Equipment("Microscope", 10, false);
        ArrayList<Equipment> labEquipment = new ArrayList<>();
        labEquipment.add(seismometer);
        labEquipment.add(microscope);

        ArrayList<CrewMember> labPersonnel = new ArrayList<>();
        labPersonnel.add(ahmed);
        labPersonnel.add(mohamed);

        Laboratory lab = new Laboratory(true, 5, labEquipment, labPersonnel, "Sector 04", new Resources(10, 10, 10, 10, 10, 10, 10));
        colony.addFacility(lab);

        // the mission needs far more than the colony has (50 vs 10)
        Resources needed = new Resources(50, 50, 0, 0, 50, 0, 0);
        ResearchMission mission = new ResearchMission("Soil analysis", lab, "Marsquake Monitoring", "RES-03", 3, needed, seismometer);

        System.out.println("\nBefore:");
        colony.showState();
        System.out.println("Mission state: " + mission.getState());

        System.out.println("\nSubmitting mission...");
        colony.startMission(mission);

        System.out.println("\nAfter:");
        colony.showState();
        System.out.println("Mission state: " + mission.getState());
        System.out.println();
    }

    // Scenario 4: oxygen leak in a habitat, handled by an engineer and a repair robot
    public static void scenarioFOUR() {
        System.out.println("---------- Scenario 4: Emergency Handling (Oxygen Leak) ----------");

        Colony colony = new Colony(new Resources(100, 100, 100, 100, 100, 100, 100));

        Engineer alia = new Engineer("Eng. Alia", "HEALTHY", 5, "E-01", "Hellas Basin", true);
        RepairRobot robot = new RepairRobot("R-01", "Hellas Basin", true, 100, true);
        colony.addAgent(alia);
        colony.addAgent(robot);

        Habitat habitat = new Habitat("Sector 01", 400, "OPERATIONAL", new Resources(50, 50, 50, 50, 50, 50, 50));
        colony.addFacility(habitat);

        OxygenLeak leak = new OxygenLeak(habitat, 5, colony.getResponders());
        colony.addEmergency(leak);

        System.out.println("\nBefore:");
        colony.showState();
        System.out.println("Habitat oxygen: " + habitat.getOxygen());

        System.out.println("\nStarting oxygen leak emergency...");
        colony.startEmergency();

        System.out.println("\nAfter:");
        colony.showState();
        System.out.println("Habitat oxygen: " + habitat.getOxygen());
        System.out.println("Leak state: " + leak.getResolvedState());
        System.out.println();
    }

    // Scenario 5: dust storm
    public static void scenarioFIVE() {
        System.out.println("---------- Scenario 5: Environmental Effect ----------");

        Colony colony = new Colony(new Resources(100, 100, 100, 100, 100, 100, 100));

        Scientist sara = new Scientist("Dr. Sara", "HEALTHY", "Geology", 10, "S-05", "Habitat", true);
        ExplorationRover rover = new ExplorationRover("R-05", "Habitat", true, 100, true);
        colony.addAgent(sara);
        colony.addAgent(rover);

        Location gale = new Location("Gale Crater");
        Equipment drill = new Equipment("Sample Drill", 5, false);
        Resources needed = new Resources(10, 10, 10, 10, 10, 0, 0);
        ExplorationMission mission = new ExplorationMission(gale, "Geology", "EXP-05", 3, needed, drill);

        System.out.println("\nA dust storm hits the colony.");
        colony.setCurrentCondition(new DustStorm());

        System.out.println("\nBefore:");
        colony.showState();

        System.out.println("\nSubmitting mission during the storm...");
        colony.startMission(mission);

        System.out.println("\nAfter:");
        colony.showState();
        System.out.println("Gale Crater explored? " + gale.isExplored());

        // storm is over, try again with a new mission
        System.out.println("\nThe storm clears.");
        System.out.println("\nMission can't be retried. Creating new mission.");
        colony.setCurrentCondition(new Normal());

        Location jezero = new Location("Jezero Crater");
        ExplorationMission retry = new ExplorationMission(jezero, "Geology", "EXP-05B", 3, needed, drill);
        colony.startMission(retry);

        colony.showState();
        System.out.println("Jezero Crater explored? " + jezero.isExplored());
        System.out.println();
    }

    // Research mission in the lab
    public static void scenarioResearch() {
        System.out.println("---------- Research Mission ----------");

        Colony colony = new Colony(new Resources(100, 100, 100, 100, 100, 100, 100));

        Scientist nadia = new Scientist("Dr. Nadia", "HEALTHY", "Biology", 5, "S-02", "Lab-1", true);
        colony.addAgent(nadia);

        Laboratory lab = new Laboratory(true, 4, null, null, "Lab-1", new Resources(50, 50, 50, 50, 50, 50, 50));
        Equipment microscope = new Equipment("Microscope", 2, false);
        Resources needed = new Resources(5, 10, 0, 0, 10, 0, 0);
        ResearchMission mission = new ResearchMission("Biology", lab, "Microbe Growth Test", "RES-01", 2, needed, microscope);

        System.out.println("\nBefore:");
        colony.showState();
        System.out.println("Lab status: " + lab.isOperationalStatus());

        System.out.println("\nStarting mission...");
        colony.startMission(mission);

        System.out.println("\nAfter:");
        colony.showState();
        System.out.println();
    }

    // Repair mission, fixing the power station
    public static void scenarioRepair() {
        System.out.println("---------- Repair Mission ----------");

        Colony colony = new Colony(new Resources(100, 100, 100, 100, 100, 100, 100));

        Engineer omar = new Engineer("Eng. Omar", "HEALTHY", 15, "E-02", "Sector-B", true);
        RepairRobot robot = new RepairRobot("RR-01", "Sector-B", true, 80, true);
        colony.addAgent(omar);
        colony.addAgent(robot);

        PowerStation station = new PowerStation(500, "Sector-B", 10, "NON-OPERATIONAL", new Resources(20, 20, 20, 20, 20, 20, 20));
        Equipment kit = new Equipment("Repair Kit", 3, false);
        Resources needed = new Resources(0, 0, 0, 5, 10, 0, 10);
        RepairMission mission = new RepairMission(station, "REP-01", 2, needed, kit);

        System.out.println("\nBefore:");
        colony.showState();
        System.out.println("Power station: " + station.isOperationalStatus());

        System.out.println("\nStarting mission...");
        colony.startMission(mission);

        System.out.println("\nAfter:");
        colony.showState();
        System.out.println("Power station: " + station.isOperationalStatus());
        System.out.println();
    }

    // Rescue mission, an injured scientist is stuck outside
    public static void scenarioRescue() {
        System.out.println("---------- Rescue Mission ----------");

        Colony colony = new Colony(new Resources(100, 100, 100, 100, 100, 100, 100));

        MedicalOfficer lina = new MedicalOfficer("Dr. Lina", "HEALTHY", 5, "M-01", "Habitat", true);
        Drone drone = new Drone("D-01", "Habitat", true, 90, true);
        colony.addAgent(lina);
        colony.addAgent(drone);

        // karim is outside the colony so he isn't added to it
        Scientist karim = new Scientist("Dr. Karim", "INJURED", "Geology", 40, "S-03", "Crater Rim", true);

        Equipment medKit = new Equipment("Medical Kit", 1, false);
        Resources needed = new Resources(10, 5, 0, 10, 10, 5, 0);
        RescueMission mission = new RescueMission(karim, "Crater Rim", "RSC-01", 3, needed, medKit);

        System.out.println("\nBefore:");
        colony.showState();
        System.out.println("Karim's health: " + karim.getHealthStatus());

        System.out.println("\nStarting mission...");
        colony.startMission(mission);

        System.out.println("\nAfter:");
        colony.showState();
        System.out.println("Karim's health: " + karim.getHealthStatus());
        System.out.println();
    }

    public static void main(String[] args) {
        scenarioONE();
        scenarioTWO();
        scenarioTHREE();
        scenarioFOUR();
        scenarioFIVE();
        scenarioResearch();
        scenarioRepair();
        scenarioRescue();
    }
}