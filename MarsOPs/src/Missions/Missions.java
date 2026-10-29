package Missions;

import ColonyMemebers.ColonyMemebers;
import MyResources.Equipment;
import java.util.ArrayList;
import MyResources.Resources;
import EnvironmentConditions.Environment;
import EnvironmentConditions.Normal;
public abstract class Missions {

    private final String missionID;
    private String missionState = "CREATED"; 
    private String failureReason;
    private final int duration;
    private int elapsedTime = 0;
    protected ArrayList<ColonyMemebers> agents = new ArrayList<>();
    protected Resources missionResources;
    protected Equipment missionEquipment;
    protected Environment currentCondition;

    public Missions(String missionID, int duration, Resources missionResources,Equipment missionEquipment) {
        this.missionID = missionID;
        this.duration = duration;
        this.missionResources = missionResources;
        this.missionEquipment = missionEquipment;
        this.currentCondition = new Normal();
    }

    public String getMissionID() {
        return missionID;
    }

    public String getMissionState() {
        return missionState;
    }

    public int getDuration() {
        return duration;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public ArrayList<ColonyMemebers> getAgents() {
        return agents;
    }

    public Resources getMissionResources() {
        return missionResources;
    }

    public Equipment getMissionEquipment() {
        return missionEquipment;
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
    public abstract void advanceTime();

    public void start(){
    
        if (missionState.equals("ASSIGNED"))
            missionState="IN PROGRESS";
        for (ColonyMemebers agent : agents) {
            
            agent.setIsAvaialble(false);
            
        }

       
       
   }
    
    public void updateProgress() {
        if (!missionState.equals("In Progress")) {
            return;
        }

        advanceTime(); 

        elapsedTime++;

        if (elapsedTime >= duration) {
            endMission();
        }
    }
    
    public void endMission()
    {
        for (ColonyMemebers agent : agents) {
            agent.setIsAvaialble(true);
            
        }
        
        missionState = "COMPLETED";
        System.out.println("Mission Completed.");
    }
}
