/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marsops;

/**
 *
 * @author HP
 */
public class Scientist extends CrewMember{

    public Scientist(String name, String idNo, String healthStatus, String currentLocation, String[] skills, int fatigueLevel, boolean isavailable) {
        super(name, idNo, healthStatus, currentLocation, skills, fatigueLevel, isavailable);
    }

    @Override
    public void performDuty() {
        System.out.println(getName()+" is performing their duty.");
    }
    
    public void research()
    {
        System.out.println(getName()+ " is researching.");
    }
    
    
    
    
}
