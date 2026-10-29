/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EnvironmentConditions;

import Missions.ExplorationMission;
import Missions.Missions;
import Missions.RescueMission;

/**
 *
 * @author HP
 */
public class CommunicationDisruption implements Environment{

    @Override
    public boolean canDisruptMission(Missions mission) {
        if (mission instanceof RescueMission || mission instanceof ExplorationMission)
        {
            return true;
        }
        
        return false;
    }


    
}
