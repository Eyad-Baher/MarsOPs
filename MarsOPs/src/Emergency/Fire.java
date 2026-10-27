/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Emergency;

import ColonyMemebers.ColonyMemebers;

/**
 *
 * @author abdel
 */
public class Fire extends Emergency {
    private boolean availability;

    public Fire(ColonyMemebers responders) {
        super("Laboratory", false, responders);
        this.availability = false;
        
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
    
    
    
    
}
