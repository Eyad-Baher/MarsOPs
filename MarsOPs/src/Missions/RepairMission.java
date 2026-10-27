/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Missions;

import ColonyMemebers.ColonyMemebers;
import MyResources.Resources;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class RepairMission extends Missions{

    public RepairMission(String missionID, int duration, Resources missionResources) {
        super(missionID, duration, missionResources);
    }

    @Override
    public boolean acceptMission(ArrayList<ColonyMemebers> candidates, boolean anotherMissionActive, boolean enoughResources) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
