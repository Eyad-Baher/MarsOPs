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
import Facility.ColonyFacility;
import Facility.Habitat;
import Missions.Missions;
import MyResources.Equipment;
import MyResources.LifeSupportSystem;
import MyResources.Resources;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;



public class Colony {
    
    private   ArrayList<ColonyFacility> allFacility = new ArrayList<>(); 
    private  ArrayList<ColonyMemebers> allAgents = new ArrayList<>();
    private  ArrayList<Missions> allMissions = new ArrayList<>();
    private Environment currentCondition;
    private ArrayList<Emergency> emergencies= new ArrayList<>();
    private ArrayList<ColonyMemebers> responders= new ArrayList<>();
    private int numEmergencyResources;
    private Resources totalResources;
    
    public void startEmergency(){
        for(Emergency e:emergencies){
            if (e instanceof OxygenLeak){
                 ((OxygenLeak)e).triggerOxygenLeak();
                 ((OxygenLeak)e).handleOxygenLeak(this);   
            }
            else if (e instanceof Fire){
                 ((Fire)e).triggerFire(numEmergencyResources);
                 ((Fire)e).handleFire(this);   
            }
            else if (e instanceof Medical){
                 ((Medical)e).triggerMedical();
                 ((Medical)e).handleMedical();   
            }
            else 
            {
                ((RoverBreakdown)e).triggerRoverBreakdown();
                 ((RoverBreakdown)e).handleRoverBreakdown();
            }
        }
        
    }
    
    public void deductTotalResource(String name, int amount){
        totalResources.deductResource(name, amount);
}
    public void distributeTotalResources(){
        int numOfFacilities = allFacility.size();
        for(ColonyFacility f: allFacility){
            f.getResources().setOxygen(totalResources.getOxygen()/numOfFacilities);
            f.getResources().setFood(totalResources.getFuel()/numOfFacilities);
            f.getResources().setFuel(totalResources.getFuel()/numOfFacilities);
            f.getResources().setMedicalSupplies(totalResources.getMedicalSupplies()/numOfFacilities);
            f.getResources().setSpareParts(totalResources.getSpareParts()/numOfFacilities);
            f.getResources().setWater(totalResources.getWater()/numOfFacilities);
            f.getResources().setBatteryPower(totalResources.getBatteryPower()/numOfFacilities);
        }
        
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
}
