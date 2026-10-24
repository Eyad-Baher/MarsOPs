/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CrewMemebers;

/**
 *
 * @author HP
 */
public class Engineer extends CrewMember{

    public Engineer(String name, String idNo, String healthStatus, String currentLocation, String[] skills, int fatigueLevel, boolean isavailable) {
        super(name, idNo, healthStatus, currentLocation, skills, fatigueLevel, isavailable);
    }

    @Override
    public void performDuty() {
        System.out.println(getName()+" is performing their duty.");
    }
    
    public void repair()
    {
        System.out.println(getName()+" is repairing.");
    }
    
    
    
    
}
