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

    public Laboratory(boolean isAvailable,int capacity, ArrayList<Equipment> equipment, ArrayList<ColonyMemebers> PersonnelPresent, String location, Resources resources) {
        super(location, capacity, "OPERATIONAL", resources);
        this.available = true;
        this.equipment = equipment;
        this.personnelPresent = personnelPresent;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setavailable(boolean available) {
        this.available = available;
    }

    public ArrayList<Equipment> getEquipment() {
        return equipment;
    }

    public void addEquipment(Equipment equipment) {
        if (equipment==null )
            System.out.println("Invalid equipment (no equipment).");   
        else
        {
          this.equipment.add(equipment);
            System.out.println("Equipment is added successfully.");
        }
    }
    
    public void removeEquipment(Equipment equipment){
        
        if (equipment==null )
            System.out.println("Invalid equipment (no equipment).");   
        else if (this.equipment.contains(equipment))
        {
          this.equipment.remove(equipment);
            System.out.println("Equipment is removed successfully.");
        }
            
    }

    public ArrayList<CrewMember> getPersonnelPresent() {
        return personnelPresent;
    }

    public void addMember(CrewMember member){
        if (member==null )
            System.out.println("Invalid member(no member).");
        else if (personnelPresent.contains(member))
            System.out.println("Member is already in this laboratory.");
        else
        {
           personnelPresent.add(member);
            System.out.println("Member is added successfully.");
        }
    
    }
    
    public void removeMember(CrewMember member){
        
        if (member==null )
            System.out.println("Invalid member(no member).");
        else if (!personnelPresent.contains(member))
            System.out.println("Member is already not in this laboratory.");
        else
        {
           personnelPresent.remove(member);
            System.out.println("Member is removed successfully.");
        }
            
    }
    
    
        
    }
    
    
    

    
    
    
