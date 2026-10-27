/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Emergency;

import ColonyMemebers.ColonyMemebers;
import java.util.ArrayList;

/**
 *
 * @author abdel
 */
public class Medical extends Emergency{

    public Medical(String emergencyLocation, boolean resolvedState, ArrayList<ColonyMemebers> responders) {
        super(emergencyLocation, resolvedState, responders);
    }
    
    

    @Override
    public ColonyMemebers[] findResponders() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
