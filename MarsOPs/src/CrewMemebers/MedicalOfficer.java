/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CrewMemebers;

/**
 *
 * @author HP
 */
public class MedicalOfficer extends CrewMember{

    public MedicalOfficer(String name, String idNo, String healthStatus, String currentLocation, String[] skills, int fatigueLevel, boolean isavailable) {
        super(name, idNo, healthStatus, currentLocation, skills, fatigueLevel, isavailable);
    }

    @Override
    public void performDuty() {
        System.out.println(getName()+" is performing their duty.");
    }
    
    public void heal()
    {
        System.out.println(getName()+ " is healing.");
    }
    
    
    
}
