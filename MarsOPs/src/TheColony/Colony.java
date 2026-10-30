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
    private ArrayList<ColonyMemebers> responders;
    private int numEmergencyResources;
    
    
    public void startEmergency(){
        for(Emergency e:emergencies){
            if (e instanceof OxygenLeak){
                 ((OxygenLeak)e).triggerOxygenLeak();
                 ((OxygenLeak)e).handleOxygenLeak();   
            }
            else if (e instanceof Fire){
                 ((Fire)e).triggerFire(numEmergencyResources);
                 ((Fire)e).handleFire();   
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
    
}
