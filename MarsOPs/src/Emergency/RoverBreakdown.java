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

/**
 *
 * @author abdel
 */
public class RoverBreakdown extends Emergency {

    ExplorationRover brokenRover;
    Resources repairCosts;
    
    //constructor
    public RoverBreakdown(String emergencyLocation, ColonyMemebers[] responders, ExplorationRover brokenRover, Resources repairCosts) {
        super(emergencyLocation, responders);
        this.brokenRover=brokenRover;
        this.repairCosts=repairCosts;
    }
    
    public void TriggerRoverBreakdown()
    {
        brokenRover.setHealthStatus(false);
        for (int i=0;i<responders.length ; i++)
        {
            if(!responders[i].canHandleEmergency(this))
            {
                System.out.println("The responder is not suitable for handling this type of emergency.");
                System.out.println("---FAILED TO RESOLVE ROVER BREAKDOWN EMERGENCY---");
                return;
            }
            else if(!responders[i].isUseable())
            {
                if(responders[i] instanceof RepairRobot)
                {
                    System.out.println("The rapair robot is out of battery.");
                }
                if(responders[i] instanceof Engineer)
                {
                    System.out.println("The engineer is dead..."); //SOUNDS TOO HARSH
                }
                System.out.println("---FAILED TO RESOLVE ROVER BREAKDOWN EMERGENCY---");
                return;
                    
            }
            else if(!responders[i].isIsAvaialble())
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
    
    
}
