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
import TheColony.Colony;
import java.util.ArrayList;

/**
 *
 * @author abdel
 */
public class Fire extends Emergency {
    private Laboratory affectedLaboratory;
    private boolean triggered;

    public Fire(Laboratory affectedLaboratory, ArrayList<ColonyMemebers> responders) {
        super("Laboratory: " + affectedLaboratory.getLocation(), null, responders);
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
        setResolvedState("UNRESOLVED");
        affectedLaboratory.setOperationalStatus("NON-OPERATIONAL");
        damageAllEquipment();
        increaseOxygenConsumption(oxygenConsumption);
        injurePersonnel();
            System.out.println("!! EMERGECY:FIRE ALARM !!");
        
        }
    } 
    
    public void handleFire(Colony colony){
        findResponders();
        dispatchResponders();
        resolveFire(colony);
        releaseResponders();   
    }
    
    public void increaseOxygenConsumption(int oxygen){
        affectedLaboratory.getResources().setOxygen(affectedLaboratory.getResources().getOxygen()-oxygen);
        
    }
    
    private void dispatchResponders() {
    if (getSuitableResponders() == null) {
        System.out.println("No responders.");
        return;    
    }
    for (ColonyMemebers r : getSuitableResponders()) {
        if (r != null) {
            r.setCurrentLocation(getEmergencyLocation());  
            r.setIsAvaialble(false);                     
        }
    }
    affectedLaboratory.setCapacity(affectedLaboratory.getCapacity()+3);
        System.out.println("Responders are dispatched.");
}
     public void releaseResponders(){
        if (getSuitableResponders() == null) {
            System.out.println("No responders");
        return;
        }
    for (ColonyMemebers r : getSuitableResponders()) {
        if (r != null)  
            r.setIsAvaialble(true);                     
    }
    affectedLaboratory.setCapacity(affectedLaboratory.getCapacity()-2);
      System.out.println("Responders are now available");  
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
            if(getResponders().get(i).canHandleEmergency(this) )
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
           
     public void resolveFire(Colony colony){
        if(getResolvedState().equals("UNRESOLVED"))
        {
        if(getSuitableResponders()==null ){
            System.out.println("No responders, Leak can't be repaired");
            return;
        }
        if(!colony.getTotalResources().areSparePartsSufficient(3))
        {
            System.out.println("Insufficient spareparts, leak can't be repaired.");
            setFailureReason("Insufficient spareparts");
            return;
        }
        else {
            colony.deductTotalResource("spareparts", 2);
            colony.distributeSpareParts();
        }
        if(!colony.getTotalResources().areMedicalSuppliesSufficient(2))
            {
            System.out.println("Insufficient medical supplies, leak can't be repaired.");
            setFailureReason("Insufficient medical supplies");
            return;
        }
        else {
            colony.deductTotalResource("medicalSupplies", 2);
            colony.distributeMedicalSupplies();
        }
            for(ColonyMemebers r:getSuitableResponders())
            { if(r instanceof Engineer){// engineer rapairs laboratory , stopping fire
              ((Engineer)r).repair(affectedLaboratory);
              ((Engineer) r).incFatigue(20);
            }
            else
                if(r instanceof RepairRobot){ // robot repairs laboratory with engineer and fixes damaged equipment
                    ((RepairRobot)r).repair(affectedLaboratory);
                for(int i=0; i<affectedLaboratory.getEquipment().size();i++)
                        {
                            ((RepairRobot)r).repair(affectedLaboratory.getEquipment().get(i), affectedLaboratory.getEquipment().get(i).getTotalAmount());
                            
                        }
                    ((RepairRobot)r).drainBattery(10);
                }
                else
                    if(r instanceof MedicalOfficer) // medical officer heals lab personnel
                    {
                        for(int i=0; i<affectedLaboratory.getPersonnelPresent().size();i++)
                        {
                            ((MedicalOfficer) r).heal(affectedLaboratory.getPersonnelPresent().get(i));
                            ((MedicalOfficer) r).incFatigue(20);
                        }
                    }
             } 
            // resources consumption (colony-wide),add after colony class is done
            setResolvedState("RESOLVED");
            System.out.println("Fire got repaired successfully");
            System.out.println("All personnel in the Laboratory are healed");
            
        
        
        } 
        else 
        System.out.println("No fire to be resolved.");
    }
    
    
}


    