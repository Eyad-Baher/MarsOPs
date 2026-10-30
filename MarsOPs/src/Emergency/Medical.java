package Emergency;

import ColonyMemebers.ColonyMemebers;
import CrewMemebers.CrewMember;
import CrewMemebers.Engineer;
import CrewMemebers.MedicalOfficer;
import Facility.ColonyFacility;
import MyResources.Equipment;
import MyResources.Resources;
import Robots.RepairRobot;
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
    
    
    
    public Medical(ColonyFacility facility, String resolvedState, ArrayList<ColonyMemebers> responders, CrewMember patient) {
        super("Facility: "+facility.getLocation(), "UNRESOLVED", responders);
        this.patient=patient;
        this.facility=facility;
    }
    
    public void triggerEmergency()
    {
        patient.setHealthStatus("INJURED");
    }
    
    public void handleEmergency()
    {
        availableResponder=findResponders();
        //SCENARIOS TO MAKE HANDLING EMERGENCY FAIL
        //if there are no responders:
        if(availableResponder==null)
        {
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
        //if there isn't enough medical supplies
        if(resources.areMedicalSuppliesSufficient(numOfMedicalSupplies))
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
        changeRespondersLocation();
        resources.useMedicalSupplies(numOfMedicalSupplies);
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
    
    private void changeRespondersLocation()
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
            if(getResponders().get(i).canHandleEmergency(this) && getResponders().get(i).isIsAvaialble() && getResponders().get(i).isUseable())
            {
                if(getResponders().get(i) instanceof MedicalOfficer && foundMedic==false)
                {
                    foundMedic=true;
                    availableResponder[0]=getResponders().get(i);
                    return availableResponder;
                }
                
            }   
        }
        
        setFailureReason("There are no suitable medical officers");
        return null;
    }
    
    
}