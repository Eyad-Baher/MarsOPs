
package Emergency;
import ColonyMemebers.ColonyMemebers;
import java.util.ArrayList;
/**
 *
 * @author HP
 */
public abstract class Emergency {
    private String emergencyLocation;
    private String resolvedState;
    private ArrayList<ColonyMemebers> responders;
    private ColonyMemebers[] suitableResponders;
    private String failureReason;

    public Emergency(String emergencyLocation, String resolvedState, ArrayList<ColonyMemebers> responders) {
        this.emergencyLocation = emergencyLocation;
        this.resolvedState = resolvedState;
        this.responders = responders;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }
    
    public void setEmergencyLocation(String emergencyLocation) {
        this.emergencyLocation = emergencyLocation;
    }

    public void setResolvedState(String resolvedState) {
        this.resolvedState = resolvedState;
    }

    public void setResponders(ArrayList<ColonyMemebers> responders) {
        this.responders = responders;
    }

    public ColonyMemebers[] getSuitableResponders() {
        return suitableResponders;
    }

    public void setSuitableResponders(ColonyMemebers[] suitableResponders) {
        this.suitableResponders = suitableResponders;
    }
    

    public String getEmergencyLocation() {
        return emergencyLocation;
    }

    public String getResolvedState() {
        return resolvedState;
    }

    public ArrayList<ColonyMemebers> getResponders() {
        return responders;
    }
    
    public abstract ColonyMemebers[] findResponders();
    
}
