package Emergency;

import ColonyMemebers.ColonyMemebers;
import CrewMemebers.CrewMember;
import CrewMemebers.Engineer;
import CrewMemebers.MedicalOfficer;
import Facility.ColonyFacility;
import MyResources.Equipment;
import MyResources.Resources;
import Robots.RepairRobot;
import TheColony.Colony;
import java.util.ArrayList;

/**
 *
 * @author abdel
 */
public class Medical extends Emergency{

    private CrewMember patient;
    private ColonyFacility facility;
    private ColonyMemebers[] availableResponder= new ColonyMemebers[1];
    private Resources resources;
    private final int fatigueAmount=8;
    private final int numOfMedicalSupplies=10;
    private final int oxygenNeeded=5;
    
    
    
    public Medical(ColonyFacility facility, ArrayList<ColonyMemebers> responders, CrewMember patient) {
        super("Facility: "+facility.getLocation(), "UNRESOLVED", responders);
        this.patient=patient;
        this.facility=facility;
    }
    
    public void triggerMedical()
    {
        patient.setHealthStatus("INJURED");
        resources.setMedicalSupplies(numOfMedicalSupplies);
        resources.setOxygen(oxygenNeeded);
        System.out.println("---!!MEDICAL EMERGENCY: PATIENT "+patient.getIdNo()+" IS INJURED!!---");
    }
    
    public void handleMedical(Colony colony)
    {
        if(getResolvedState().equals("RESOLVED"))
        {
            System.out.println("__MEDICAL EMERGENCY IS RESOLVED__");
            return;
        }
        availableResponder=findResponders();
        //WHAT COULD MAKE HANDLING EMERGENCY FAIL
        //if there are no responders:
        if(availableResponder==null)
        {
            setFailureReason("There are no suitable medical officers.");
            System.out.println(getFailureReason());
            setResolvedState("UNRESOLVED");
        }
        //if facility is not operational
        if(facility.isOperationalStatus().equals("NON-OPERATIONAL"))
        {
            setFailureReason("Facility is not operational.");
            System.out.println(getFailureReason());
            setResolvedState("UNRESOLVED");
        }
        //if there aren't enough medical supplies
        if(colony.getTotalResources().areMedicalSuppliesSufficient(numOfMedicalSupplies))
        {
            setFailureReason("Insufficient medical supplies.");
            System.out.println(getFailureReason());
            setResolvedState("UNRESOLVED");
        }
        //if medic's fatigue level is too high.
        if(!isResponderEenergySufficient())
        {
            setFailureReason("Responder's fatigue level is too high and cannot heal patient.");
            System.out.println(getFailureReason());
            setResolvedState("UNRESOLVED");
        }
        
        if(getResolvedState().equals("UNRESOLVED"))
        {
            System.out.println("__FAILED TO HANDLE EMERGENCY. EMERGENCY UNRESOLVED__");
            return;
        }
        
        //emergency can be handled
        dispatchResponder();
        consumeResources(colony);
        changeResponderAvailability(false);
        responderHealsPatient();
        setResolvedState("RESOLVED");
        releaseResponder();
        
        if(getResolvedState().equals("RESOLVED"))
            System.out.println("__MEDICAL EMERGENCY IS SUCCESSFULLY RESOLVED__");
    }
    
    private boolean isResponderEenergySufficient()
    {
        if(( ((MedicalOfficer)availableResponder[0]).getFatigueLevel() + fatigueAmount )<= 100)
            return true;
        return false;
    }
    
    private void dispatchResponder()
    {
        ((MedicalOfficer)availableResponder[0]).setCurrentLocation("Facility: "+facility.getLocation());
    }
    
    private void changeResponderAvailability(boolean a)
    {
        ((MedicalOfficer)availableResponder[0]).setIsAvaialble(a);
    }
    
    private void responderHealsPatient()
    {
        ((MedicalOfficer)availableResponder[0]).heal(patient);
        ((MedicalOfficer)availableResponder[0]).incFatigue(fatigueAmount);
    }
    
    private void consumeResources(Colony colony)
    {
        colony.getTotalResources().useMedicalSupplies(numOfMedicalSupplies);
        colony.distributeMedicalSupplies();
        colony.deductTotalResource("oxygen",oxygenNeeded);
        colony.distributeOxygen();
    }
    
    private void releaseResponder()
    {
        changeResponderAvailability(true);
    }

    @Override
    public ColonyMemebers[] findResponders()
    {
        boolean foundMedic=false; 
        for(int i=0;i<getResponders().size();i++)
        {
            if(! (getResponders().get(i).getIdNo().equals(patient.getIdNo())) && getResponders().get(i).canHandleEmergency(this))
            {
                if(getResponders().get(i) instanceof MedicalOfficer && foundMedic==false)
                {
                    foundMedic=true;
                    availableResponder[0]=getResponders().get(i);
                    return availableResponder;
                }
                
            }   
        }
        
        return null;
    }
    
    
}