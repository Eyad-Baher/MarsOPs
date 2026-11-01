/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facility;
import MyResources.LifeSupportSystem;
import MyResources.Resources;

/**
 *
 * @author Lina Islam
 */
public class Habitat extends ColonyFacility {
    private final LifeSupportSystem lifeSupport;
    private boolean airFiltrationStatus;

    public Habitat(String location, int capacity, String operationalStatus, Resources resources) {
        super(location, capacity, operationalStatus, resources);
        Resources safeResources = (resources != null) ? resources : new Resources(0, 0, 0, 0, 0, 0, 0);
        this.lifeSupport = new LifeSupportSystem(safeResources, true);
        this.airFiltrationStatus = true; 
    }
   
    public void reduceOxygen(int amount) {
        
        if (amount <= 0) {
            return;
        }
        lifeSupport.reduceOxygen(amount);
    }

    public void addOxygen(int amount) {
        
        if (amount <= 0) {
            return;
        }
        lifeSupport.addOxygen(amount);
    }

    public void setAirFiltrationStatus(boolean airFiltrationStatus) {
        this.airFiltrationStatus = airFiltrationStatus;
        lifeSupport.setAirFiltrationStatus(airFiltrationStatus);
    }

    
    public boolean isAirFiltrationStatus() {
        return airFiltrationStatus;
    }

    public boolean getAirFiltrationStatus() {
        return airFiltrationStatus;
    }

    
    public LifeSupportSystem getLifeSupport() {
        return lifeSupport;
    }
    
    public int getOxygen() {
        return lifeSupport.getOxygen();
    }

    
    @Override
    public void getStatusSummary() {
        System.out.println("--- Habitat Summary ---");
        super.getStatusSummary();
        System.out.println("Oxygen Level: " + getOxygen());
        System.out.println("Air Filtration: " + (airFiltrationStatus ? "ACTIVE" : "INACTIVE"));
    }
}
