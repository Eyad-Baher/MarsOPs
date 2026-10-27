package Missions;

import ColonyMemebers.ColonyMemebers;
import java.util.ArrayList;
import MyResources.Resources;

public abstract class Missions {

    private final String missionID;
    private String missionState = "CREATED"; 
    private String failureReason;
    private final int duration;
    private int elapsedTime = 0;
    private ArrayList<ColonyMemebers> agents = new ArrayList<>();
    private Resources missionResources;

    public Missions(String missionID, int duration, Resources missionResources) {
        this.missionID = missionID;
        this.duration = duration;
        this.missionResources = missionResources;
    }

    public abstract boolean acceptMission(ArrayList<ColonyMemebers> candidates,
            boolean anotherMissionActive, boolean enoughResources);

    protected void assignAgents(ArrayList<ColonyMemebers> selected) {
        agents = new ArrayList<>(selected);
        missionState = "ASSIGNED";
    }

    public void fail(String reason) {
        if (missionState.equals("COMPLETED") || missionState.equals("FAILED")) {
            return;
        }
        missionState = "FAILED";
        failureReason = reason;
    }

    public String getState() { return missionState; }
    public String getFailureReason() { return failureReason; }

    public void start(){
    
        if (missionState.equals("Assigned"))
            missionState="In Progress";
        
       
   }
}