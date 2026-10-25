/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Emergency;

import ColonyMemebers.ColonyMemebers;
import CrewMemebers.Engineer;
import MyResources.Resources;
import Robots.ExplorationRover;

/**
 *
 * @author abdel
 */
public class RoverBreakdown extends Emergency {

    ExplorationRover brokenRover;
    Resources repairCosts;
    
    //constructor
    public RoverBreakdown(String emergencyLocation, ColonyMemebers responders, ExplorationRover brokenRover, Resources repairCosts) {
        super(emergencyLocation, responders);
        this.brokenRover=brokenRover;
        this.repairCosts=repairCosts;
    }
    
    public void TriggerRoverBreakdown()
    {
        brokenRover.setHealthStatus(false);
        
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
