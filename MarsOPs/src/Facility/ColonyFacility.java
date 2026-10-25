/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facility;

import MyResources.Resources;


/**
 *
 * @author Lina Islam
 */
public abstract class ColonyFacility {
    private String location;
    private int capacity;
    private boolean operationalStatus;
    private Resources resources;

    public ColonyFacility(String location, int capacity, boolean operationalStatus, Resources resources) {
        this.location = location;
        this.capacity = capacity;
        this.operationalStatus = operationalStatus;
        this.resources = resources;
    }
    
    
    
    public void setLocation(String location) {
        this.location = location;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setOperationalStatus(boolean operationalStatus) {
        this.operationalStatus = operationalStatus;
    }

    public String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isOperationalStatus() {
        return operationalStatus;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }
    
    
}
