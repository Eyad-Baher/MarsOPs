package Missions;

import ColonyMemebers.ColonyMemebers;
import MyResources.Equipment;
import java.util.ArrayList;
import MyResources.Resources;

public abstract class Missions {

    private final String missionID;
    private String missionState = "CREATED"; 
    private String failureReason;
    private final int duration;
    private int elapsedTime = 0;
    protected ArrayList<ColonyMemebers> agents = new ArrayList<>();
    protected Resources missionResources;
    protected Equipment missionEquipment;

    public Missions(String missionID, int duration, Resources missionResources,Equipment missionEquipment) {
        this.missionID = missionID;
        this.duration = duration;
        this.missionResources = missionResources;
        this.missionEquipment = missionEquipment;
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
}