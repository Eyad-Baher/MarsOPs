/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Emergency;

import ColonyMemebers.ColonyMemebers;
import CrewMemebers.Engineer;
import Missions.ExplorationMission;
import Missions.Missions;
import MyResources.Resources;
import Robots.ExplorationRover;
import Robots.RepairRobot;
import Robots.RoboticUnit;
import TheColony.Colony;
import java.util.ArrayList;

/**
 *
 * @author abdel
 */
public class RoverBreakdown extends Emergency {

    private ExplorationRover brokenRover;
    private Resources repairCosts;
    private ExplorationMission mission;
    private ColonyMemebers[] availableResponders=new ColonyMemebers[2];
    private final int fatigueAmount=5;
    private final int batteryDrainage=3;
    private final int numOfSpareParts=5;
    
    //constructor
    public RoverBreakdown( ExplorationRover brokenRover, ExplorationMission mission, ArrayList<ColonyMemebers> responders) {
        super(mission.getTargetLocation().getLocationName(), "UNRESOLVED", responders);
        this.brokenRover=brokenRover;
        this.mission=mission;
        
    }
    
    public void triggerRoverBreakdown()
    {
        brokenRover.setHealthStatus(false);
        //change mission status
        mission.fail("Rover breakdwown");
        brokenRover.setCurrentLocation(mission.getTargetLocation().getLocationName());
        System.out.println("----!!EMERGENCY: ROVER "+ brokenRover.getIdNo()+" BROKE DOWN!!----");
        this.repairCosts.setBatteryPower(batteryDrainage);
        this.repairCosts.setBatteryPower(numOfSpareParts);
        
    }
    
    public void handleRoverBreakdown(Colony colony)
    {
        if(getResolvedState().equals("RESOLVED"))
        {
            System.out.println("__ROVER BREAKDOWN EMERGENCY IS RESOLVED__");
            return;
        }
        //ColonyMemebers[] availableResponders= new ColonyMemebers[2];
        availableResponders=findResponders();
        //SCENARIOS TO MAKE HANDLING EMERGENCY FAIL
        //if there are no responders:
        if(availableResponders==null)
        {
            setFailureReason("There are no suitable responders.");
            System.out.println(getFailureReason());
            setResolvedState("UNRESOLVED");
        }
        //if spare parts are insufficient 
        if(!colony.getTotalResources().areSparePartsSufficient(numOfSpareParts))
        {
            setFailureReason("Insufficient spare parts to repair the broken rover.");
            System.out.println(getFailureReason());
            setResolvedState("UNRESOLVED");
        }
        
        //if respponders are out of energy
        for (ColonyMemebers availableResponder : availableResponders) 
        {
            if (!isResponderEnergySufficient(availableResponder))
            {
                if(availableResponder instanceof Engineer)
                    System.out.println("Engineer does not have sufficient energy to repair rover.");
                if(availableResponder instanceof RepairRobot)
                    System.out.println("Repair robot does not have sufficient battery to repair rover.");
                setResolvedState("UNRESOLVED");
            }
        }
        if(getResolvedState().equals("UNRESOLVED"))
        {
            System.out.println("__FAILED TO HANDLE EMERGENCY. EMERGENCY UNRESOLVED__");
            return;
        }
        
        //emergency can be handled. resolving the emergency
        this.dispatchResponders();
        this.makeRespondersUnavailable();
        this.respondersRepair();
        colony.getTotalResources().useSpareParts(numOfSpareParts);
        colony.distributeSpareParts();
        releaseResponders();
        setResolvedState("RESOLVED");
        
        if(getResolvedState().equals("RESOLVED"))
            System.out.println("__ROVER BREAKDOWN EMERGENCY IS SUCCESSFULLY RESOLVED__");
            
    }
    
    
    private void dispatchResponders()
    {
        for(ColonyMemebers availableResponder : availableResponders)
        {
            availableResponder.setCurrentLocation(getEmergencyLocation());
            
        }
    }
    
    private void respondersRepair()
    {
        for(ColonyMemebers availableResponder : availableResponders)
        {
            if(availableResponder instanceof Engineer)
            {
                ((Engineer)availableResponder).repair(brokenRover);
                ((Engineer)availableResponder).incFatigue(fatigueAmount);
            }
            if(availableResponder instanceof RepairRobot)
            {
                ((RepairRobot)availableResponder).repair(brokenRover);
                ((RepairRobot)availableResponder).drainBattery(batteryDrainage);
            }
            
        }
    }
    
    
    private void makeRespondersUnavailable()
    {
        for(ColonyMemebers availableResponder : availableResponders)
        {
            availableResponder.setIsAvaialble(false);
        }
    }
    
    private void releaseResponders()
    {
        for(ColonyMemebers availableResponder: availableResponders)
        {
            availableResponder.setIsAvaialble(true);
        }
    }
    
    private boolean isResponderEnergySufficient(ColonyMemebers r)
    {
        if(r instanceof Engineer)
        {
            if ( (((Engineer)r).getFatigueLevel()+fatigueAmount) <=100)
            {
                return true;
            }
            return false;
        }
        if (r instanceof RepairRobot)
        {
            if ( (((RepairRobot)r).getBatteryLevel()-batteryDrainage) >= 0)
            {
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
        return null;
    }
    
}