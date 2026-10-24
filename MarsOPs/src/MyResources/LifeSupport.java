
package MyResources;
import MyResources.Resources;
/**
 *
 * @author abdel
 */
public class LifeSupportSystem {
    private Resources resources;
    private boolean AirFiltrationStatus;

    public LifeSupportSystem(Resources resources, boolean filtrationStatus) {
        this.resources = resources;
        this.AirFiltrationStatus = filtrationStatus;
    }

    public void setAirFiltrationStatus(boolean AirFiltrationStatus) {
        this.AirFiltrationStatus = AirFiltrationStatus;
    }
    
    public double getOxygen(){
        return resources.getOxygen();
    }
    
    public void reduceOxygen(double oxygen){
        resources.oxygen-=oxygen;
        
    }
    
    public void addOxygen(double oxygen){
        resources.oxygen+=oxygen;
    }
    
    
}
