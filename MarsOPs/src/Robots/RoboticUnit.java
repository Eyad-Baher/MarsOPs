
package Robots;


public class RoboticUnit {
    private double batteryLevel;
    private String healthStatus;
    private String currentLocation;
    private boolean availability;

    public RoboticUnit(double batteryLevel, String healthStatus, String currentLocation, boolean availability) {
        this.batteryLevel = batteryLevel;
        this.healthStatus = healthStatus;
        this.currentLocation = currentLocation;
        this.availability = availability;
    }

    
    //getters
    public double getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(double batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    
    //setters
    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
