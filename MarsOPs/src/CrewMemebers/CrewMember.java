/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CrewMemebers;

/**
 *
 * @author HP
 */
public abstract class CrewMember extends ColonyMemebers.ColonyMemebers{

    private String name;
    private String healthStatus; // INJURED  or HEALTHY
    private int fatigueLevel;

    public CrewMember(String name, String healthStatus, int fatigueLevel, String IdNo, String currentLocation, boolean isAvaialble) {
        super(IdNo, currentLocation, isAvaialble);
        setName(name);
        setHealthStatus(healthStatus);
        setFatigueLevel(fatigueLevel); 
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        if (healthStatus != null && (healthStatus.equals("INJURED") || healthStatus.equals("HEALTHY"))) {
            this.healthStatus = healthStatus;
        }
    }

    
    public int getFatigueLevel() {
        return fatigueLevel;
    }

    public void setFatigueLevel(int fatigueLevel) {
        if (fatigueLevel >= 0) {
            this.fatigueLevel = fatigueLevel;
        }
    }
    
    
    @Override
    public boolean isUseable()
    {
        if(fatigueLevel<100 && !healthStatus.equals("INJURED"))
        {
            return true;
        }
        else{
            return false;
        }
            
    }
    
    @Override
    public void advanceTime()
    {
        fatigueLevel+=1;
    }
    
    @Override
    public void getStatusSummary()
    {
        System.out.println("Name: "+ name);
        System.out.println("Id: "+super.getIdNo());
        System.out.println("Health status: "+healthStatus);
        System.out.println("Availablity: "+super.isIsAvaialble());
        System.out.println("Fatigue level: "+fatigueLevel);
    }
    

    public void incFatigue(int amount)
    {
        this.fatigueLevel+=amount;
    }

}
