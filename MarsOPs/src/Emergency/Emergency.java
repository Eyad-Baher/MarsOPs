
package Emergency;
import ColonyMemebers.ColonyMemebers;
import java.util.ArrayList;
/**
 *
 * @author HP
 */
public abstract class Emergency {
    private String emergencyLocation;
    private boolean resolvedState;
    private ArrayList<ColonyMemebers> responders;
    private String failureReason;

    public Emergency(String emergencyLocation, boolean resolvedState, ArrayList<ColonyMemebers> responders) {
        this.emergencyLocation = emergencyLocation;
        this.resolvedState = resolvedState;
        this.responders = responders;
    }
    
    
    public void setEmergencyLocation(String emergencyLocation) {
        this.emergencyLocation = emergencyLocation;
    }

    public void setResolvedState(boolean resolvedState) {
        this.resolvedState = resolvedState;
    }

    public void setResponders(ArrayList<ColonyMemebers> responders) {
        this.responders = responders;
    }

    public String getEmergencyLocation() {
        return emergencyLocation;
    }

    public boolean isResolvedState() {
        return resolvedState;
    }

    public ArrayList<ColonyMemebers> getResponders() {
        return responders;
    }
    
}
