/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Emergency;

import ColonyMemebers.ColonyMemebers;
import Facility.Habitat;
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
    
    public OxygenLeak(Habitat affectedHabitat, int oxygenLoss, ColonyMemebers responders) {
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

    public int totalOxygenLoss(){
         if(!isResolvedState() && !penaltyApplied)
        {
            detectOxygenLoss();
            penaltyApplied=true;
            oxygenLoss=2*oxygenLoss;
        }
         return oxygenLoss;
    }

    

    
    
    
    
    
}
