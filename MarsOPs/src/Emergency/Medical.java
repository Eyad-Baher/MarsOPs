/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    
    
    
    public Medical(ColonyFacility facility, boolean resolvedState, ArrayList<ColonyMemebers> responders, CrewMember patient) {
        super("Facility: "+facility.getLocation(), resolvedState, responders);
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
            setResolvedState(false);
        }
        //if facility is not operational
        if(!facility.isOperationalStatus())
        {
            setFailureReason("Facility is not operational.");
            System.out.println(getFailureReason());
            setResolvedState(false);
        }
        //if there isn't enough medical supplies
        if(resources.areMedicalSuppliesSufficient(numOfMedicalSupplies))
        {
            setFailureReason("Insufficient medical supplies.");
            System.out.println(getFailureReason());
            setResolvedState(false);
        }
        //if medic's fatigue level is too low.
        //needs a method in crew member
        
        if(!isResolvedState())
        {
            System.out.println("__FAILED TO HANDLE EMERGENCY. EMERGENCY UNRESOLVED__");
            return;
        }
        
        //emergency can be handled
        changeRespondersLocation();
        resources.useMedicalSupplies(numOfMedicalSupplies);
        changeResponderAvailability(false);
        responderHealsPatient();
        setResolvedState(true);
        releaseResponder();
        
        if(isResolvedState())
            System.out.println("__MEDICAL EMERGENCY IS SUCCESSFULLY RESOLVED__");
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
        ((MedicalOfficer)availableResponder[0]).heal();
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
