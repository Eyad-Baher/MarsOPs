/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marsops;

/**
 *
 * @author HP
 */
public abstract class CrewMember {

    private String name;
    private String idNo;
    private String healthStatus;
    private String currentLocation;
    private String[] skills;
    private int fatigueLevel;
    private boolean isavailable;

    public CrewMember(String name, String idNo, String healthStatus, String currentLocation, String[] skills, int fatigueLevel, boolean isavailable) {
        this.name = name;
        this.idNo = idNo;
        this.healthStatus = healthStatus;
        this.currentLocation = currentLocation;
        this.skills = skills;
        this.fatigueLevel = fatigueLevel;
        this.isavailable = isavailable;
    }
//GETTERS

    public String getName() {
        return name;
    }

    public String getIdNo() {
        return idNo;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public String[] getSkills() {
        return skills;
    }

    public int getFatigueLevel() {
        return fatigueLevel;
    }

    public boolean isIsavailable() {
        return isavailable;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    public void setFatigueLevel(int fatigueLevel) {
        this.fatigueLevel = fatigueLevel;
    }

    public void setIsavailable(boolean isavailable) {
        this.isavailable = isavailable;
    }
    
    public abstract void performDuty();
    
    
    

}
