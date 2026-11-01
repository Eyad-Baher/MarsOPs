/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RegionToExplore;

/**
 *
 * @author abdel
 */
public class Location {

    private String locationName;
    private boolean explored;
    
    public Location(String locationName) {
        setLocationName(locationName);
        this.explored = false;
    }
    
    public String getLocationName() {
        return locationName;
    }
    
    public void setLocationName(String locationName) {
        if (locationName != null && !locationName.isBlank()) {
            this.locationName = locationName;
        }
    }
    
    public boolean isExplored() {
        return explored;
    }
    
    public void Explore() {
        this.explored = true;
    }
    
}
