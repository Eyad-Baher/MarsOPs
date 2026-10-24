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

    public Engineer(String name, String healthStatus, String[] skills, int fatigueLevel, String IdNo, String currentLocation, boolean isAvaialble) {
        super(name, healthStatus, skills, fatigueLevel, IdNo, currentLocation, isAvaialble);
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
