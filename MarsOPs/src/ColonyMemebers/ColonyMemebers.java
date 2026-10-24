package ColonyMemebers;

import Missions.Missions;
import Emergency.Emergency;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
public abstract class ColonyMemebers {
    private  String IdNo;
    private String currentLocation;
    private boolean isAvaialble;

    public ColonyMemebers(String IdNo, String currentLocation, boolean isAvaialble) {
        this.IdNo = IdNo;
        this.currentLocation = currentLocation;
        this.isAvaialble = isAvaialble;
    }

    public String getIdNo() {
        return IdNo;
    }



    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public boolean isIsAvaialble() {
        return isAvaialble;
    }

    public void setIsAvaialble(boolean isAvaialble) {
        this.isAvaialble = isAvaialble;
    }
    
    public abstract boolean isUseable();
    public abstract boolean canPerformMission(Missions mission);
    public abstract boolean canHandleEmergency(Emergency emergency);
    public abstract void advanceTime();
    public abstract void getStatusSummary();
    public abstract void performDuty();

    
}
