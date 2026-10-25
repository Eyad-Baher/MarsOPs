
package MyResources;


public class Resources {
  protected int oxygen;
  protected int water;
  protected int food;
  protected int fuel;
  protected int batteryPower;
  protected int medicalSupplies;
  protected int spareParts;

    public Resources(int oxygen, int water, int food, int fuel, int batteryPower, int medicalSupplies, int spareParts) {
        this.oxygen = oxygen;
        this.water = water;
        this.food = food;
        this.fuel = fuel;
        this.batteryPower = batteryPower;
        this.medicalSupplies = medicalSupplies;
        this.spareParts = spareParts;
    }

    public int getOxygen() {
        return oxygen;
    }

    public void setOxygen(int oxygen) {
        if (oxygen>0)
        this.oxygen = oxygen;
        else
            this.oxygen=0;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        if (water>0)
        this.water = water;
        else
            this.water=0;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
       if (food>0)
        this.food = food;
       else
           this.food=0;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        if(fuel>0)
        this.fuel = fuel;
        else 
            this.fuel=0;
    }

    public int getBatteryPower() {
        return batteryPower;
    }

    public void setBatteryPower(int batteryPower) {
        if (batteryPower>0)
        this.batteryPower = batteryPower;
        else
            this.batteryPower=0;
    }

    public int getMedicalSupplies() {
        return medicalSupplies;
    }

    public void setMedicalSupplies(int medicalSupplies) {
        if (medicalSupplies>0)
        this.medicalSupplies = medicalSupplies;
        else
            this.medicalSupplies=0;
    }

    public int getSpareParts() {
        return spareParts;
    }

    public void setSpareParts(int spareParts) {
        if (spareParts>0)
        this.spareParts = spareParts;
        else
            this.spareParts=0;
    }
  
  
    
}
