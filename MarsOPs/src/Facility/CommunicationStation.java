/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facility;

import MyResources.Resources;



/**
 *
 * @author Lina Islam
 */
public class CommunicationStation extends ColonyFacility {

    private boolean channelOpen;
    
    public CommunicationStation(String location, int capacity, boolean operationalStatus, Resources resources) {
        super(location, capacity, operationalStatus, resources);
        channelOpen=true;
    }
    
    public boolean isChannelOpen() {
        return channelOpen;
    }

    public void disruptChannel() {
        this.channelOpen = false;
    }
    
    public void restoreChannel()
    {
        this.channelOpen=true;
    }

    public boolean isAvailable()
    {
        if(isOperationalStatus()&&isChannelOpen())
            return true;
        else
            return false;
    }
            

    
    
    
}
