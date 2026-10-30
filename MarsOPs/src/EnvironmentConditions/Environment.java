/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package EnvironmentConditions;
import Missions.Missions;
import Missions.ExplorationMission;
import Missions.RescueMission;

/**
 *
 * @author HP
 */
public interface Environment {
    public abstract boolean canShutDownLocation();
    public abstract boolean canDisruptMission(Missions mission);
    
    
}
