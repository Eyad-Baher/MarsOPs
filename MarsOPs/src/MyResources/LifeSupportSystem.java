
package MyResources;
import MyResources.Resources;
/**
 *
 * @author abdel
 */
public class LifeSupportSystem {
    private Resources resources;
    private boolean airFiltrationStatus;

    public LifeSupportSystem(Resources resources, boolean filtrationStatus) {
        this.resources = resources;
        this.airFiltrationStatus = filtrationStatus;
    }

    public void setAirFiltrationStatus(boolean airFiltrationStatus) {
        this.airFiltrationStatus = airFiltrationStatus;
    }
    
    public int getOxygen(){
        return resources.getOxygen();
    }
    
    public void reduceOxygen(int oxygen){
        resources.setOxygen(((resources.getOxygen())- oxygen));
        
    }
    
    public void addOxygen(int oxygen){
        resources.setOxygen(((resources.getOxygen())- oxygen));
    }
    
    
}
