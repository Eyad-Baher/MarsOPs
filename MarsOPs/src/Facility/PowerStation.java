/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facility;

import MyResources.Resources;
import Robots.RoboticUnit;


/**
 *
 * @author Lina Islam
 */
public class PowerStation extends ColonyFacility {
    private int powerOutput;//efficiency of powerstation
    private static final int MAX_BATTERY= 100;

    public PowerStation(int powerOutput, String location, int capacity, String operationalStatus, Resources resources) {
        super(location, capacity, operationalStatus, resources);
        this.powerOutput = powerOutput;
    }

    public int getPowerOutput() {
        return powerOutput;
    }

    public void setPowerOutput(int powerOutput) {
        this.powerOutput = powerOutput;
    }

    public void rechargeBattery(RoboticUnit robot){
        if(!robot.isIsAvaialble())
            System.out.println("Robot is unavailabe, can't be recharged.");
        else if(robot.getBatteryLevel()==MAX_BATTERY)
            System.out.println("Robot is already charged.");
        else
        robot.setBatteryLevel(MAX_BATTERY);
    }
      
    
}
