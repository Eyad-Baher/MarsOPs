/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Emergency;

import ColonyMemebers.ColonyMemebers;
import CrewMemebers.Engineer;
import Facility.Habitat;
import Robots.RepairRobot;
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
        super("Habitat: " + affectedHabitat.getLocation(), "UNRESOLVED", responders);
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
                
            }
            if(foundEngineer&&foundRepairRobot)
            {
                return getSuitableResponders();
            }
            
            
        }
        setFailureReason("There are no suitable responders.");
        return null;
        
    }
    
    public void repairLeak(){
        if(getResolvedState().equals("UNRESOLVED"))
        {
        if(getSuitableResponders()==null )
            System.out.println("No responders, Leak can't be repaired");
        else 
        {
            for(ColonyMemebers r:getSuitableResponders())
            {if(r instanceof Engineer)
              ((Engineer)r).repair(affectedHabitat);
            else
                ((RepairRobot)r).repair(affectedHabitat);   
             } 
            // resources consumption (colony-wide)add after colony class is done
            setResolvedState("RESOLVED");
            System.out.println("Leak got repaired successfully");
            
        }
        
        } 
        else 
        System.out.println("No leak to be resolved.");
    }
    
    
    
}
