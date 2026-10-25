
package Emergency;
import ColonyMemebers.ColonyMemebers;
/**
 *
 * @author HP
 */
public abstract class Emergency {
    private String emergencyLocation;
    private boolean resolvedState;
    private ColonyMemebers responders;

    public void setEmergencyLocation(String emergencyLocation) {
        this.emergencyLocation = emergencyLocation;
    }

    public void setResolvedState(boolean resolvedState) {
        this.resolvedState = resolvedState;
    }

    public void setResponders(ColonyMemebers responders) {
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
