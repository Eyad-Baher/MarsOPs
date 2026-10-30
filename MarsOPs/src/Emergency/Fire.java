/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Emergency;

import ColonyMemebers.ColonyMemebers;
import CrewMemebers.CrewMember;
import CrewMemebers.Engineer;
import CrewMemebers.MedicalOfficer;
import Facility.Laboratory;
import MyResources.Equipment;
import Robots.RepairRobot;
import java.util.ArrayList;

/**
 *
 * @author abdel
 */
public class Fire extends Emergency {
    private Laboratory affectedLaboratory;
    private boolean triggered;

    public Fire(Laboratory affectedLaboratory, ArrayList<ColonyMemebers> responders) {
        super("Laboratory: " + affectedLaboratory.getLocation(), "UNRESOLVED", responders);
        this.triggered=false;
        
    }

     public void damageAllEquipment(){
        for(Equipment e:affectedLaboratory.getEquipment())
            e.setBroken(true);
        System.out.println("All lab equipment got damaged!!");   
     }
       
     public void injurePersonnel() {
    for (CrewMember p : affectedLaboratory.getPersonnelPresent()) {
        p.setHealthStatus("INJURED");
    }
    System.out.println("All Lab personnel are injured and require medical assistance.");
}
    
    public void triggerFire(int oxygenConsumption){
        if(!triggered){
        affectedLaboratory.setavailable(false);
        damageAllEquipment();
        increaseOxygenConsumption(oxygenConsumption);
        injurePersonnel();
            System.out.println("!! EMERGECY:FIRE ALARM !!");
        
        }
        
        
    } 
    public void increaseOxygenConsumption(int oxygen){
        affectedLaboratory.getResources().setOxygen(affectedLaboratory.getResources().getOxygen()-oxygen);
        
    }
    
    @Override
           public ColonyMemebers[] findResponders()
    {
        int numResponders=0;
        boolean foundEngineer=false; 
        boolean foundRepairRobot=false;
        boolean foundMedicalOfficer=false;
        setSuitableResponders(new ColonyMemebers[3]);
        for(int i=0;i<getResponders().size();i++)
        {
            if(getResponders().get(i).canHandleEmergency(this) && getResponders().get(i).isIsAvaialble() && getResponders().get(i).isUseable())
            {
                if(getResponders().get(i) instanceof Engineer && foundEngineer==false)
                {
                    foundEngineer=true;
                    getSuitableResponders()[numResponders]=getResponders().get(i);
                    numResponders++;
                }
                if(getResponders().get(i) instanceof RepairRobot && foundRepairRobot==false)
                {
                    foundRepairRobot=true;
                    getSuitableResponders()[numResponders]=getResponders().get(i);
                    numResponders++;
                }
                if(getResponders().get(i) instanceof MedicalOfficer && foundMedicalOfficer==false)
                {
                    foundMedicalOfficer=true;
                    getSuitableResponders()[numResponders]=getResponders().get(i);
                    numResponders++;
                }
                
            }
            if(foundEngineer&&foundRepairRobot&&foundMedicalOfficer)
            {
                return getSuitableResponders();
            }
            
            
        }
        setFailureReason("There are no suitable responders.");
        return null;
        
    }
           
     public void resolveFire(){
        if(getResolvedState().equals("UNRESOLVED"))
        {
        if(getSuitableResponders()==null ){
            System.out.println("No responders, Leak can't be repaired");
            
        }
        else 
        {
            for(ColonyMemebers r:getSuitableResponders())
            { if(r instanceof Engineer){// engineer rapairs laboratory , stopping fire
              ((Engineer)r).repair(affectedLaboratory);
            }
            else
                if(r instanceof RepairRobot){ // robot repairs laboratory with engineer and fixes damaged equipment
                ((RepairRobot)r).repair(affectedLaboratory);
                for(int i=0; i<affectedLaboratory.getEquipment().size();i++)
                        {
                            ((RepairRobot)r).repair(affectedLaboratory.getEquipment().get(i), affectedLaboratory.getEquipment().get(i).getTotalAmount());
                        }
                }
                else
                    if(r instanceof MedicalOfficer) // medical officer heals lab personnel
                    {
                        for(int i=0; i<affectedLaboratory.getPersonnelPresent().size();i++)
                        {
                            ((MedicalOfficer) r).heal(affectedLaboratory.getPersonnelPresent().get(i));
                        }
                    }
             } 
            // resources consumption (colony-wide),add after colony class is done
            setResolvedState("RESOLVED");
            System.out.println("Fire got repaired successfully");
            System.out.println("All personnel in the Laboratory are healed");
            
        }
        
        } 
        else 
        System.out.println("No fire to be resolved.");
    }
    
    
}


    