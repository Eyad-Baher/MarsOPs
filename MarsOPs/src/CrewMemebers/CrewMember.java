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
    private String healthStatus;
    private String[] skills;
    private int fatigueLevel;

    public CrewMember(String name, String healthStatus, String[] skills, int fatigueLevel, String IdNo, String currentLocation, boolean isAvaialble) {
        super(IdNo, currentLocation, isAvaialble);
        this.name = name;
        this.healthStatus = healthStatus;
        this.skills = skills;
        this.fatigueLevel = fatigueLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    public int getFatigueLevel() {
        return fatigueLevel;
    }

    public void setFatigueLevel(int fatigueLevel) {
        this.fatigueLevel = fatigueLevel;
    }
    
    
    @Override
    public boolean isUseable()
    {
        if(fatigueLevel>0 && healthStatus != "INJURED")
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
    

    
    
    
    
    

}
