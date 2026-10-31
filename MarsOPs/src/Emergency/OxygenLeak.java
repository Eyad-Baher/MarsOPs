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
        super(affectedHabitat != null ? "Habitat: " + affectedHabitat.getLocation() : "Unknown Habitat", null, responders);
        this.affectedHabitat = affectedHabitat;
        this.oxygenLoss = oxygenLoss;
        this.detected = false;
        this.penaltyApplied = false;
    }
    
    private void detectOxygenLoss() {
        if (!detected) {
            detected = true;
            if (affectedHabitat != null) {
                affectedHabitat.reduceOxygen(oxygenLoss);
            }
        } else {
            System.out.println("Oxygen loss already detected.");
        }
    }

    public Habitat getAffectedHabitat() {
        return affectedHabitat;
    }

    public int getOxygenLoss() {
        return oxygenLoss;
    }

    public void triggerOxygenLeak() {
        detectOxygenLoss();
        setResolvedState("UNRESOLVED");
        if (affectedHabitat != null) {
            affectedHabitat.setOperationalStatus("NON-OPERATIONAL");
        }
        System.out.println("!! EMERGENCY: OXYGEN LEAK !!");
    }
    
    public void handleOxygenLeak(Colony colony) {
        
        findResponders();

        
        if (getSuitableResponders() == null) {
            System.out.println("No responders.");
            applyUnresolvedLoss(colony);
            return;
        }

        if (colony != null && colony.getTotalResources() != null) {
            if (!colony.getTotalResources().areSparePartsSufficient(2)) {
                System.out.println("Insufficient spareparts, leak can't be repaired.");
                setFailureReason("Insufficient spareparts");
                applyUnresolvedLoss(colony);
                return;
            }
        } else if (colony != null && colony.getTotalResources() == null) {
            System.out.println("Colony resources are not initialized. Cannot verify spare parts.");
            setFailureReason("Colony resources unavailable");
            applyUnresolvedLoss(colony);
            return;
        }

        dispatchResponders(colony);
        repairLeak(colony);
        releaseResponders();   
    }
    
    public int totalOxygenLoss() {
        return "RESOLVED".equals(getResolvedState()) ? oxygenLoss : 2 * oxygenLoss;
    }

    private void applyUnresolvedLoss(Colony colony) {
        if (!penaltyApplied) {
            penaltyApplied = true;
            if (affectedHabitat != null) {
                affectedHabitat.reduceOxygen(oxygenLoss);
            }
            if (colony != null && colony.getTotalResources() != null) {
                colony.deductTotalResource("oxygen", oxygenLoss);
            }
            setResolvedState("UNRESOLVED");
        }
    }

    @Override
    public ColonyMemebers[] findResponders() {
        if (getResponders() == null) {
            setFailureReason("There are no suitable responders.");
            setSuitableResponders(null);
            return null;
        }

        int numResponders = 0;
        boolean foundEngineer = false; 
        boolean foundRepairRobot = false;
        setSuitableResponders(new ColonyMemebers[2]);

        for (int i = 0; i < getResponders().size(); i++) {
            ColonyMemebers candidate = getResponders().get(i);
            if (candidate != null && candidate.canHandleEmergency(this)) {
                if (candidate instanceof Engineer && !foundEngineer) {
                    foundEngineer = true;
                    getSuitableResponders()[numResponders] = candidate;
                    numResponders++;
                }
                if (candidate instanceof RepairRobot && !foundRepairRobot) {
                    foundRepairRobot = true;
                    getSuitableResponders()[numResponders] = candidate;
                    numResponders++;
                }
            }
            if (foundEngineer && foundRepairRobot) {
                return getSuitableResponders();
            }
        }
        setFailureReason("There are no suitable responders.");
        setSuitableResponders(null);
        return null;
    }

    private void dispatchResponders(Colony colony) {
        if (getSuitableResponders() == null) {
            System.out.println("No responders.");
            applyUnresolvedLoss(colony); 
            return;
        }
        for (ColonyMemebers r : getSuitableResponders()) {
            if (r != null) {
                r.setCurrentLocation(getEmergencyLocation());  
                r.setIsAvaialble(false);                     
            }
        }
        
        System.out.println("Dispatch successful");
        if (affectedHabitat != null) {
            affectedHabitat.setCapacity(affectedHabitat.getCapacity() + 2);
        }
    }

    public void releaseResponders() {
        if (getSuitableResponders() == null) {
            System.out.println("No responders");
            return;
        }
        for (ColonyMemebers r : getSuitableResponders()) {
            if (r != null) {
                r.setIsAvaialble(true);                     
            }
        }
        System.out.println("Responders are now available");
        if (affectedHabitat != null) {
            affectedHabitat.setCapacity(affectedHabitat.getCapacity() - 2);
        }
    }
    
    public void repairLeak(Colony colony) {
        if (getResolvedState() != null && getResolvedState().equals("UNRESOLVED")) {
            if (getSuitableResponders() == null) {
                System.out.println("No responders, Leak can't be repaired");
                return;
            }
            
            if (colony != null && colony.getTotalResources() != null) {
                if (!colony.getTotalResources().areSparePartsSufficient(2)) {
                    System.out.println("Insufficient spareparts, leak can't be repaired.");
                    setFailureReason("Insufficient spareparts");
                    applyUnresolvedLoss(colony);
                    return;
                } else {
                    colony.deductTotalResource("spareparts", 2);
                    colony.distributeSpareParts();
                }    
            }
            
            for (ColonyMemebers r : getSuitableResponders()) {
                if (r instanceof Engineer) {
                    if (affectedHabitat != null) {
                        ((Engineer) r).repair(affectedHabitat);
                    }
                    ((Engineer) r).incFatigue(20);
                } else if (r instanceof RepairRobot) {
                    if (affectedHabitat != null) {
                        ((RepairRobot) r).repair(affectedHabitat);
                    }
                    ((RepairRobot) r).drainBattery(10);
                }
            } 
            
            
            if (affectedHabitat != null) {
                affectedHabitat.setOperationalStatus("OPERATIONAL");
            }
            setResolvedState("RESOLVED");
            setFailureReason(null);
            System.out.println("Leak got repaired successfully");
        } else {
            System.out.println("No leak to be resolved.");
        }
    }
}
