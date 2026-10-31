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
        super(affectedLaboratory != null ? "Laboratory: " + affectedLaboratory.getLocation() : "Unknown Laboratory", null, responders);
        this.triggered = false;
        this.affectedLaboratory = affectedLaboratory;
    }

    public void damageAllEquipment() {
        if (affectedLaboratory != null && affectedLaboratory.getEquipment() != null) {
            for (Equipment e : affectedLaboratory.getEquipment()) {
                if (e != null) {
                    e.damage(e.getTotalAmount());
                }
            }
        }
        System.out.println("All lab equipment got damaged!!");   
    }
       
    public void injurePersonnel() {
        if (affectedLaboratory != null && affectedLaboratory.getPersonnelPresent() != null) {
            for (CrewMember p : affectedLaboratory.getPersonnelPresent()) {
                if (p != null) {
                    p.setHealthStatus("INJURED");
                }
            }
        }
        System.out.println("All Lab personnel are injured and require medical assistance.");
    }
    
    public void triggerFire(int oxygenConsumption) {
        if (!triggered) {
            this.triggered = true;
            if (affectedLaboratory != null) {
                affectedLaboratory.setavailable(false);
                affectedLaboratory.setOperationalStatus("NON-OPERATIONAL");
            }
            setResolvedState("UNRESOLVED");
            damageAllEquipment();
            increaseOxygenConsumption(oxygenConsumption);
            injurePersonnel();
            System.out.println("!! EMERGENCY: FIRE ALARM !!");
        } else {
            System.out.println("Fire emergency already triggered.");
        }
    } 
    
    public void handleFire(Colony colony) {
        findResponders();
        dispatchResponders();
        resolveFire(colony);
        releaseResponders();   
    }
    
    public void increaseOxygenConsumption(int oxygen) {
        if (oxygen > 0 && affectedLaboratory != null && affectedLaboratory.getResources() != null) {
            affectedLaboratory.getResources().setOxygen(affectedLaboratory.getResources().getOxygen() - oxygen);
        }
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
        if (affectedLaboratory != null) {
            affectedLaboratory.setCapacity(affectedLaboratory.getCapacity() + 3);
        }
        System.out.println("Responders are dispatched.");
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
        if (affectedLaboratory != null) {
            affectedLaboratory.setCapacity(affectedLaboratory.getCapacity() - 3);
        }
        System.out.println("Responders are now available");  
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
        boolean foundMedicalOfficer = false;
        setSuitableResponders(new ColonyMemebers[3]);

        for (int i = 0; i < getResponders().size(); i++) {
            ColonyMemebers candidate = getResponders().get(i);
            if (candidate != null && candidate.canHandleEmergency(this)) {
                if (candidate instanceof Engineer && !foundEngineer) {
                    foundEngineer = true;
                    getSuitableResponders()[numResponders] = candidate;
                    numResponders++;
                } else if (candidate instanceof RepairRobot && !foundRepairRobot) {
                    foundRepairRobot = true;
                    getSuitableResponders()[numResponders] = candidate;
                    numResponders++;
                } else if (candidate instanceof MedicalOfficer && !foundMedicalOfficer) {
                    foundMedicalOfficer = true;
                    getSuitableResponders()[numResponders] = candidate;
                    numResponders++;
                }
            }
            if (foundEngineer && foundRepairRobot && foundMedicalOfficer) {
                return getSuitableResponders();
            }
        }
        setFailureReason("There are no suitable responders.");
        setSuitableResponders(null);
        return null;
    }
           
    public void resolveFire(Colony colony) {
        if (getResolvedState() != null && getResolvedState().equals("UNRESOLVED")) {
            if (getSuitableResponders() == null) {
                System.out.println("No responders, Fire can't be resolved.");
                return;
            }

            if (colony != null && colony.getTotalResources() != null) {
                if (!colony.getTotalResources().areSparePartsSufficient(3)) {
                    System.out.println("Insufficient spare parts, fire can't be resolved.");
                    setFailureReason("Insufficient spareparts");
                    return;
                }
                
                if (!colony.getTotalResources().areMedicalSuppliesSufficient(2)) {
                    System.out.println("Insufficient medical supplies, fire can't be resolved.");
                    setFailureReason("Insufficient medical supplies");
                    return;
                } else {
                    colony.deductTotalResource("spareparts", 3);
                    colony.distributeSpareParts();
                    colony.deductTotalResource("medicalSupplies", 2);
                    colony.distributeMedicalSupplies();
                }
            } else if (colony != null && colony.getTotalResources() == null) {
                System.out.println("Colony total resources unavailable, fire can't be resolved.");
                setFailureReason("Colony resources unavailable");
                return;
            }

            for (ColonyMemebers r : getSuitableResponders()) {
                if (r instanceof Engineer) {
                    // engineer repairs laboratory, stopping fire
                    if (affectedLaboratory != null) {
                        ((Engineer) r).repair(affectedLaboratory);
                    }
                    ((Engineer) r).incFatigue(20);
                } else if (r instanceof RepairRobot) {
                    // robot repairs laboratory with engineer and fixes damaged equipment
                    if (affectedLaboratory != null) {
                        ((RepairRobot) r).repair(affectedLaboratory);
                        if (affectedLaboratory.getEquipment() != null) {
                            for (int i = 0; i < affectedLaboratory.getEquipment().size(); i++) {
                                Equipment eq = affectedLaboratory.getEquipment().get(i);
                                if (eq != null) {
                                    ((RepairRobot) r).repair(eq, eq.getTotalAmount());
                                }
                            }
                        }
                    }
                    ((RepairRobot) r).drainBattery(10);
                } else if (r instanceof MedicalOfficer) {
                    // medical officer heals lab personnel
                    if (affectedLaboratory != null && affectedLaboratory.getPersonnelPresent() != null) {
                        for (int i = 0; i < affectedLaboratory.getPersonnelPresent().size(); i++) {
                            CrewMember cm = affectedLaboratory.getPersonnelPresent().get(i);
                            if (cm != null) {
                                ((MedicalOfficer) r).heal(cm);
                                ((MedicalOfficer) r).incFatigue(20);
                            }
                        }
                    }
                }
            } 
           
            if (affectedLaboratory != null) {
                affectedLaboratory.setOperationalStatus("OPERATIONAL");
                affectedLaboratory.setavailable(true);
            }
            setResolvedState("RESOLVED");
            setFailureReason(null);
            System.out.println("Fire got repaired successfully");
            System.out.println("All personnel in the Laboratory are healed");
        } else {
            System.out.println("No fire to be resolved.");
        }
    }
}
