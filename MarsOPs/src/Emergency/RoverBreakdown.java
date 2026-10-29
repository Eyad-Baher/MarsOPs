/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Emergency;

import ColonyMemebers.ColonyMemebers;
import CrewMemebers.Engineer;
import Missions.Missions;
import MyResources.Resources;
import Robots.ExplorationRover;
import Robots.RepairRobot;
import Robots.RoboticUnit;
import java.util.ArrayList;

/**
 *
 * @author abdel
 */
public class RoverBreakdown extends Emergency {

    private ExplorationRover brokenRover;
    private Resources repairCosts;
    private Missions mission;
    private ColonyMemebers[] availableResponders=new ColonyMemebers[2];
    private final int fatigueAmount=5;
    private final int batteryDrainage=3;
    
    //constructor
    public RoverBreakdown(String emergencyLocation, boolean resolvedState, ArrayList<ColonyMemebers> responders, ExplorationRover brokenRover, Missions mission, Resources repairCosts) {
        super(emergencyLocation, resolvedState, responders);
        this.brokenRover=brokenRover;
        this.repairCosts=repairCosts;
        this.mission=mission;
    }
    
    public void triggerEmergency()
    {
        brokenRover.setHealthStatus(false);
        //change mission status
        brokenRover.setCurrentLocation(getEmergencyLocation());
        System.out.println("----!!ROVER BREAKDOWN EMERGENCY!!----");
        
    }
    
    public void handleEmergency()
    {
        //ColonyMemebers[] availableResponders= new ColonyMemebers[2];
        availableResponders=findResponders();
        //SCENARIOS TO MAKE HANDLING EMERGENCY FAIL
        //if there are no responders:
        if(availableResponders==null)
        {
            System.out.println(getFailureReason());
            setResolvedState(false);
        }
        //if spare parts are insufficient 
        if(!repairCosts.areSparePartsSufficient(3))
        {
            setFailureReason("Insufficient spare parts to repair the broken rover.");
            System.out.println(getFailureReason());
            setResolvedState(false);
        }
        
        //if respponders are out of battery
        for (ColonyMemebers availableResponder : availableResponders) 
        {
            if (!isResponderEnergySufficient(availableResponder))
            {
                if(availableResponder instanceof Engineer)
                    System.out.println("Engineer does not have sufficient energy to repair rover.");
                if(availableResponder instanceof RepairRobot)
                    System.out.println("Repair robot does not have sufficient battery to repair rover.");
                setResolvedState(false);
            }
        }
        if(!isResolvedState())
        {
            System.out.println("__FAILED TO HANDLE EMERGENCY. EMERGENCY UNRESOLVED__");
            return;
        }
        
        //emergency can be handled.
        
        //change locations of engineer and repair robot
        for(ColonyMemebers availableResponder : availableResponders)
        {
            availableResponder.setCurrentLocation(getEmergencyLocation());
            
        }
        
        
    }
    
    private boolean isResponderEnergySufficient(ColonyMemebers r)
    {
        if(r instanceof Engineer)
        {
            if ( (((Engineer)r).getFatigueLevel()+fatigueAmount) <=100)
            {
                //((Engineer)r).incFatigue(fatigueAmount);
                return true;
            }
            return false;
        }
        if (r instanceof RepairRobot)
        {
            if ( (((RepairRobot)r).getBatteryLevel()-batteryDrainage) >= ((RepairRobot)r).getBatteryLevel())
            {
                //((RepairRobot)r).drainBattery(batteryDrainage);
                return true;
            }
            return false;
        }
        return false;
    }
    
    @Override
    public ColonyMemebers[] findResponders()
    {
        int numResponders=0;
        boolean foundEngineer=false; 
        boolean foundRepairRobot=false;
        //ColonyMemebers[] availableResponders=new ColonyMemebers[2];
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
        setFailureReason("There are no suitable responders");
        return null;
    }
    
}
