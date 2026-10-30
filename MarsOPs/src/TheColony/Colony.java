/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TheColony;

import ColonyMemebers.ColonyMemebers;
import EnvironmentConditions.Environment;
import Facility.ColonyFacility;
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
    
    
    
    
}
