/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Emergency;

import ColonyMemebers.ColonyMemebers;
import CrewMemebers.Engineer;
import Facility.Habitat;
import Robots.RepairRobot;
import TheColony.Colony;
import java.util.ArrayList;

/**
 *
 * @author abdel
 */
public class OxygenLeak extends Emergency {
    private Habitat affectedHabitat;
    private int oxygenLoss;
    private boolean detected;
    private boolean penaltyApplied;
    
    public OxygenLeak(Habitat affectedHabitat, int oxygenLoss, ArrayList<ColonyMemebers> responders) {
        super("Habitat: " + affectedHabitat.getLocation(), null, responders);
        this.affectedHabitat = affectedHabitat;
        this.oxygenLoss = oxygenLoss;
        detected=false;
        penaltyApplied=false;
    }
    
    private void detectOxygenLoss(){
        if(detected==false)
        {
        detected = true;
        affectedHabitat.reduceOxygen(oxygenLoss);
        }
        else 
            System.out.println("Oxygen loss already detected.");
    }

    public Habitat getAffectedHabitat() {
        return affectedHabitat;
    }

    public int getOxygenLoss() {
        return oxygenLoss;
    }
    public void triggerOxygenLeak(){
        detectOxygenLoss();
        setResolvedState("UNRESOLVED");
        affectedHabitat.setOperationalStatus("NON-OPERATIONAL");
        System.out.println("!! EMERGECY:OXYGEN LEAK !!");
    }
    
    public void handleOxygenLeak(Colony colony){
        findResponders();
        dispatchResponders();
        repairLeak(colony);
        releaseResponders();   
    }
    
    
    
    public int totalOxygenLoss(){
         if(getResolvedState().equals("UNRESOLVED") && !penaltyApplied)
        {
            detectOxygenLoss();
            penaltyApplied=true;
            oxygenLoss=2*oxygenLoss;
        }
         return oxygenLoss;
    }

    public ColonyMemebers[] findResponders()
    {
        int numResponders=0;
        boolean foundEngineer=false; 
        boolean foundRepairRobot=false;
        setSuitableResponders(new ColonyMemebers[2]);
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
                
            }
            if(foundEngineer&&foundRepairRobot)
            {
                return getSuitableResponders();
            }
            
            
        }
        setFailureReason("There are no suitable responders.");
        return null;
        
    }
    // responders will go to the emergency location
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
        System.out.println("Dispatch successful");
    }
    affectedHabitat.setCapacity(affectedHabitat.getCapacity()+2);
    
}
    public void releaseResponders(){
        if (getSuitableResponders() == null) 
        {
            System.out.println("No responders");
            return;
        }
    for (ColonyMemebers r : getSuitableResponders()) {
        if (r != null)  
            r.setIsAvaialble(true);                     
    }
        System.out.println("Responders are now available");
        affectedHabitat.setCapacity(affectedHabitat.getCapacity()-2);
        
    }
    
    public void repairLeak(Colony colony){
        if(getResolvedState().equals("UNRESOLVED"))
        {
        if(getSuitableResponders()==null ){
            System.out.println("No responders, Leak can't be repaired");
            colony.deductTotalResource("oxygen", totalOxygenLoss());
            return;
        }
        
        if(colony.getTotalResources().areSparePartsSufficient(2))
        {
            System.out.println("Insufficient spareparts, leak can't be repaired.");
            setFailureReason("Insufficient spareparts");
            return;
        }
        else 
            colony.deductTotalResource("spareparts", 2);
             
        
            for(ColonyMemebers r:getSuitableResponders())
            {if(r instanceof Engineer){
              ((Engineer)r).repair(affectedHabitat);
              ((Engineer) r).incFatigue(20);
            }
            else{
                ((RepairRobot)r).repair(affectedHabitat);
                ((RepairRobot)r).drainBattery(10);
            }
             } 
            // resources consumption (colony-wide)add after colony class is done
            colony.deductTotalResource("oxygen", totalOxygenLoss());
            colony.distributeOxygen();
            setResolvedState("RESOLVED");
            System.out.println("Leak got repaired successfully");
            
        
        
        } 
        else 
        System.out.println("No leak to be resolved.");
    }
    
    
    
}
