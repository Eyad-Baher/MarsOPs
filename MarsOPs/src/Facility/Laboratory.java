/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facility;

import ColonyMemebers.ColonyMemebers;
import CrewMemebers.CrewMember;
import MyResources.Equipment;
import MyResources.Resources;
import java.util.ArrayList;

/**
 *
 * @author Lina Islam
 */
public class Laboratory extends ColonyFacility {
    private boolean available;
    private ArrayList<Equipment> equipment;
    private ArrayList<CrewMember> personnelPresent; // scientist or engineer or medical officer

    public Laboratory(boolean isAvailable, int capacity, ArrayList<Equipment> equipment, ArrayList<CrewMember> personnelPresent, String location, Resources resources) {
        super(location, capacity, "OPERATIONAL", resources);
        this.available = isAvailable;
        this.equipment = (equipment != null) ? equipment : new ArrayList<>();
        this.personnelPresent = (personnelPresent != null) ? personnelPresent : new ArrayList<>();
    }

    public boolean isAvailable() {
        return available;
    }

    

    public void setavailable(boolean available) {
        this.available=available;
    }

    public ArrayList<Equipment> getEquipment() {
        return equipment;
    }

    public void addEquipment(Equipment equipment) {
        if (equipment == null) {
            System.out.println("Invalid equipment (no equipment).");   
        } else {
            if (this.equipment == null) {
                this.equipment = new ArrayList<>();
            }
            this.equipment.add(equipment);
            System.out.println("Equipment is added successfully.");
        }
    }
    
    public void removeEquipment(Equipment equipment) {
        if (equipment == null) {
            System.out.println("Invalid equipment (no equipment).");   
        } else if (this.equipment != null && this.equipment.contains(equipment)) {
            this.equipment.remove(equipment);
            System.out.println("Equipment is removed successfully.");
        } else {
            System.out.println("Equipment not found in this laboratory.");
        }
    }

    public ArrayList<CrewMember> getPersonnelPresent() {
        return personnelPresent;
    }

    public void addMember(CrewMember member) {
        if (member == null) {
            System.out.println("Invalid member(no member).");
        } else if (personnelPresent != null && personnelPresent.contains(member)) {
            System.out.println("Member is already in this laboratory.");
        
        } else if (personnelPresent != null && personnelPresent.size() >= getCapacity()) {
            System.out.println("Cannot add member: Laboratory capacity reached (" + getCapacity() + ").");
        } else {
            if (personnelPresent == null) {
                personnelPresent = new ArrayList<>();
            }
            personnelPresent.add(member);
            System.out.println("Member is added successfully.");
        }
    }
    
    public void removeMember(CrewMember member) {
        if (member == null) {
            System.out.println("Invalid member(no member).");
        } else if (personnelPresent == null || !personnelPresent.contains(member)) {
            System.out.println("Member is already not in this laboratory.");
        } else {
            personnelPresent.remove(member);
            System.out.println("Member is removed successfully.");
        }
    }

    
    @Override
    public void getStatusSummary() {
        System.out.println("--- Laboratory Summary ---");
        super.getStatusSummary();
        System.out.println("Available: " + available);
        System.out.println("Equipment count: " + (equipment != null ? equipment.size() : 0));
        if (equipment != null) {
            for (Equipment eq : equipment) {
                if (eq != null) {
                    System.out.println("  - Equipment: " + eq.getName() + " (Broken: " + eq.isBroken() + ")");
                }
            }
        }
        System.out.println("Personnel Present (" + (personnelPresent != null ? personnelPresent.size() : 0) + "/" + getCapacity() + "):");
        if (personnelPresent != null) {
            for (CrewMember cm : personnelPresent) {
                if (cm != null) {
                    System.out.println("  - " + cm.getName() + " [Health: " + cm.getHealthStatus() + "]");
                }
            }
        }
    }
}
