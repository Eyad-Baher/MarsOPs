
package EnvironmentConditions;

import ColonyMemebers.ColonyMemebers;
import Missions.ExplorationMission;
import Missions.Missions;
import Robots.ExplorationRover;

/**
 *
 * @author abdel
 */
public class dustStorms implements Environment{

    @Override
    public boolean canDisruptMission(Missions mission) {
        if  (mission instanceof ExplorationMission){
            return true;
      }
        for (ColonyMemebers agent : mission.getAgents()) {
            if (agent instanceof ExplorationRover)
                return true;
            
        }
        return false;
    }

    
    
    
}
