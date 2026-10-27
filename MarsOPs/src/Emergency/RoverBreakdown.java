/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Emergency;

import ColonyMemebers.ColonyMemebers;
import CrewMemebers.Engineer;
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

    ExplorationRover brokenRover;
    Resources repairCosts;
    
    //constructor
    public RoverBreakdown(String emergencyLocation, boolean resolvedState, ArrayList<ColonyMemebers> responders, ExplorationRover brokenRover, Resources repairCosts) {
        super(emergencyLocation, resolvedState, responders);
        this.brokenRover=brokenRover;
        this.repairCosts=repairCosts;
    }
    
    public void TriggerRoverBreakdown()
    {
        brokenRover.setHealthStatus(false);
        for (int i=0;i<getResponders().size() ; i++)
        {
            if(!getResponders().get(i).canHandleEmergency(this))
            {
                System.out.println("The responder is not suitable for handling this type of emergency.");
                System.out.println("---FAILED TO RESOLVE ROVER BREAKDOWN EMERGENCY---");
                return;
            }
            else if(!getResponders().get(i).isUseable())
            {
                if(getResponders().get(i) instanceof RepairRobot)
                {
                    System.out.println("The rapair robot is out of battery.");
                }
                if(getResponders().get(i) instanceof Engineer)
                {
                    System.out.println("The engineer is dead..."); //SOUNDS TOO HARSH
                }
                System.out.println("---FAILED TO RESOLVE ROVER BREAKDOWN EMERGENCY---");
                return;
                    
            }
            else if(!getResponders().get(i).isIsAvaialble())
            {
                System.out.println("The responder is not available(It is currently part of an active mission)");
                System.out.println("---FAILED TO RESOLVE ROVER BREAKDOWN EMERGENCY---");
                return;
            }
            else
            {
                //resolve the emergency
            }
            
        }
        
    }
    
    private void damageRover(ExplorationRover brokenRover)
    {
        brokenRover.setHealthStatus(false);
    }
    
    private void dispatchEngineer(Engineer e)
    {
        e.setCurrentLocation(this.getEmergencyLocation());
    }
    private void dispatchRepairRobot(RepairRobot r)
    {
        r.setCurrentLocation(this.getEmergencyLocation());
    }
    
    
    //loop on responders to find suitable engineer & robot
   /* public void ResolveEmergency()
    {
        for(int i=0;i<responders.length;i++)
        {
            if(responders[i] instanceof Engineer)
            {
                if(responders[i].canHandleEmergency)
                    
            }
        }
    }*/
    
    @Override
    public ColonyMemebers[] findResponders()
    {
        int numResponders=0;
        boolean foundEngineer=false; 
        boolean foundRepairRobot=false;
        ColonyMemebers[] availableResponders=new ColonyMemebers[2];
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
