
package Emergency;

import ColonyMemebers.ColonyMemebers;

/**
 *
 * @author HP
 */
public class Emergency {
    private String emergencyLocation;
    private boolean resolvedState;
    protected ColonyMemebers[] responders;

    public Emergency(String emergencyLocation, ColonyMemebers[] responders) {
        this.emergencyLocation = emergencyLocation;
        this.responders = responders;
    }
    
    
    

    public void setEmergencyLocation(String emergencyLocation) {
        this.emergencyLocation = emergencyLocation;
    }

    public void setResolvedState(boolean resolvedState) {
        this.resolvedState = resolvedState;
    }

    public void setResponders(ColonyMemebers[] responders) {
        this.responders = responders;
    }

    public String getEmergencyLocation() {
        return emergencyLocation;
    }

    public boolean isResolvedState() {
        return resolvedState;
    }

    public ColonyMemebers getResponders() {
        return responders;
    }
}
