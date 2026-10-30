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
public class Habitat extends ColonyFacility{
    private final LifeSupportSystem lifeSupport;

    public Habitat( String location, int capacity, String operationalStatus, Resources resources) {
        super(location, capacity, operationalStatus, resources);
        this.lifeSupport = new LifeSupportSystem(resources,true);
    }
   
    public void reduceOxygen(int amount) {
    lifeSupport.reduceOxygen(amount);
}
    public void addOxygen(int amount) {
    lifeSupport.addOxygen(amount);
}
    public void setAirFiltrationStatus(boolean airFiltrationStatus) {
        lifeSupport.setAirFiltrationStatus(airFiltrationStatus);
    }
    
    public int getOxygen(){
        return lifeSupport.getOxygen();
    }
    
}
