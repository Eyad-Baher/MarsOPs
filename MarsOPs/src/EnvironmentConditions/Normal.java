/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EnvironmentConditions;

import Missions.Missions;

/**
 *
 * @author HP
 */
public class Normal implements Environment{

    @Override
    public boolean canDisruptMission(Missions mission) {
        return false;
    }


    
    
    
    
}
