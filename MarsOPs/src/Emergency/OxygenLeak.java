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
    
    public OxygenLeak(Habitat affectedHabitat, int oxygenLoss, ColonyMemebers responders) {
        super("Habitat: " + affectedHabitat.getLocation(), false, responders);
        this.affectedHabitat = affectedHabitat;
        this.oxygenLoss = oxygenLoss;
        detectOxygenLoss();
    }
    
    private void detectOxygenLoss(){
        affectedHabitat.reduceOxygen(this.oxygenLoss);
    }

    public Habitat getAffectedHabitat() {
        return affectedHabitat;
    }

    public int getOxygenLoss() {
        return oxygenLoss;
    }

    public int totalOxygenLoss(){
        if(super.isResolvedState())
            return oxygenLoss;
        else 
        {
            detectOxygenLoss();
            return 2*oxygenLoss;
        }
    }

    

    
    
    
    
    
}
