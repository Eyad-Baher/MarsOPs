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
        super("Habitat: " + affectedHabitat.getLocation(), false, responders);
        this.affectedHabitat = affectedHabitat;
        this.oxygenLoss = oxygenLoss;
        this.detected=false;
    }
    
    private void detectOxygenLoss(){
        detected = true;
        affectedHabitat.reduceOxygen(this.oxygenLoss);
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
         if(!isResolvedState() && !penaltyApplied)
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
        ColonyMemebers[] availableResponders=new ColonyMemebers[2];
        for(int i=0;i<getResponders().size();i++)
        {
            if(getResponders().get(i).canHandleEmergency(this) && getResponders().get(i).isIsAvaialble() && getResponders().get(i).isUseable())
            {
                if(getResponders().get(i) instanceof Engineer && foundEngineer==false)
                {
                    foundEngineer=true;
                    availableResponders[numResponders]=getResponders().get(i);
                    numResponders++;
                }
                if(getResponders().get(i) instanceof RepairRobot && foundRepairRobot==false)
                {
                    foundRepairRobot=true;
                    availableResponders[numResponders]=getResponders().get(i);
                    numResponders++;
                }
                
            }
            if(foundEngineer&&foundRepairRobot)
            {
                return availableResponders;
            }
            
            
        }
        setFailureReason("There are no suitable responders.");
        return null;
        
    }
    
    
    
}
