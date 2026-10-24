
package MyResources;


public class Resources {
  protected double oxygen;
  protected double water;
  protected int food;
  protected double fuel;
  protected int batterPower;
  protected int medicalSupplies;
  protected int spareParts;

    public Resources(double oxygen, double water, int food, double fuel, int batterPower, int medicalSupplies, int spareParts) {
        this.oxygen = oxygen;
        this.water = water;
        this.food = food;
        this.fuel = fuel;
        this.batterPower = batterPower;
        this.medicalSupplies = medicalSupplies;
        this.spareParts = spareParts;
    }

    public double getOxygen() {
        return oxygen;
    }

    public void setOxygen(double oxygen) {
        this.oxygen = oxygen;
    }

    public double getWater() {
        return water;
    }

    public void setWater(double water) {
        this.water = water;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public int getBatterPower() {
        return batterPower;
    }

    public void setBatterPower(int batterPower) {
        this.batterPower = batterPower;
    }

    public int getMedicalSupplies() {
        return medicalSupplies;
    }

    public void setMedicalSupplies(int medicalSupplies) {
        this.medicalSupplies = medicalSupplies;
    }

    public int getSpareParts() {
        return spareParts;
    }

    public void setSpareParts(int spareParts) {
        this.spareParts = spareParts;
    }
  
  
    
}
