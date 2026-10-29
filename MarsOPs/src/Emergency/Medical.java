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
    
    
    
    public Medical(String emergencyLocation, boolean resolvedState, ArrayList<ColonyMemebers> responders, CrewMember patient, ColonyFacility facility) {
        super(emergencyLocation, resolvedState, responders);
        this.patient=patient;
        this.facility=facility;
    }
    
    public void triggerEmergency()
    {
        patient.setHealthStatus("INJURED");
        
        
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
                    availableResponder[1]=getResponders().get(i);
                    return availableResponder;
                }
                
            }   
        }
        
        setFailureReason("There are no suitable responders");
        return null;
    }
    
    
}
