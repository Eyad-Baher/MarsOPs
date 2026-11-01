package MyResources;


public class Resources {
  protected int oxygen;
  protected int water;
  protected int food;
  protected int fuel;
  protected int batteryPower;
  protected int medicalSupplies;
  protected int spareParts;
  
  public static final String OXYGEN="oxygen";
  public static final String WATER="water";
  public static final String FOOD="food";
  public static final String FUEL="fuel";
  public static final String BATTERYPOWER="batteryPower";
  public static final String MEDICALSUPPLIES="medicalSupplies";
  public static final String SPAREPARTS="spareParts";

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
  
    public int getTotalResources()
    {
        return oxygen+water+food+fuel+batteryPower+medicalSupplies+spareParts;
    }
    
    public int getAmount(String name)
    {
        if(!isValidName(name))
            return -1;
        if(OXYGEN.equalsIgnoreCase(name))
            return oxygen;
        if(WATER.equalsIgnoreCase(name))
            return water;
        if(FOOD.equalsIgnoreCase(name))
            return food;
        if(FUEL.equalsIgnoreCase(name))
            return fuel;
        if(BATTERYPOWER.equalsIgnoreCase(name))
            return batteryPower;
        if(MEDICALSUPPLIES.equalsIgnoreCase(name))
            return medicalSupplies;
        if(SPAREPARTS.equalsIgnoreCase(name))
            return spareParts;
        return -1;
    }
    
    public boolean deductResource(String name, int amount)
    {
        if(!isValidName(name) || amount<0 )
            return false;
        if(amount> getAmount(name))
            return false;
        if(OXYGEN.equalsIgnoreCase(name))
            oxygen-=amount;
        else if(WATER.equalsIgnoreCase(name))
            water-=amount;
        else if(FOOD.equalsIgnoreCase(name))
            food-=amount;
        else if(FUEL.equalsIgnoreCase(name))
            fuel-=amount;
        else if(BATTERYPOWER.equalsIgnoreCase(name))
            batteryPower-=amount;
        else if(MEDICALSUPPLIES.equalsIgnoreCase(name))
            medicalSupplies-=amount;
        else if(SPAREPARTS.equalsIgnoreCase(name))
            spareParts-=amount;
        else
            return false;
        return true;
    }
    
    public boolean addResource(String name, int amount)
    {
        if(!isValidName(name) || amount<0 )
            return false;
        if(OXYGEN.equalsIgnoreCase(name))
            oxygen+=amount;
        else if(WATER.equalsIgnoreCase(name))
            water+=amount;
        else if(FOOD.equalsIgnoreCase(name))
            food+=amount;
        else if(FUEL.equalsIgnoreCase(name))
            fuel+=amount;
        else if(BATTERYPOWER.equalsIgnoreCase(name))
            batteryPower+=amount;
        else if(MEDICALSUPPLIES.equalsIgnoreCase(name))
            medicalSupplies+=amount;
        else if(SPAREPARTS.equalsIgnoreCase(name))
            spareParts+=amount;
        else
            return false;
        return true;
    }
    
    public boolean areSparePartsSufficient(int numOfSpareParts)
    {
        if( numOfSpareParts>this.spareParts || numOfSpareParts<0)
        {
            //cannot use up more spare parts than available & cannot use up a -ve number of spare parts
            return false;
        }
        return true;
    }
    public boolean useSpareParts(int numOfSpareParts)
    {
        if(!areSparePartsSufficient(numOfSpareParts))
        {
            return false;
        }
        this.spareParts-=numOfSpareParts;
        return true;
    }
    
    public boolean areMedicalSuppliesSufficient(int numOfMedicalSupplies)
    {
        if(numOfMedicalSupplies>this.medicalSupplies || numOfMedicalSupplies<0)
            return false;
        return true;
    }
    public boolean useMedicalSupplies(int numOfMedicalSupplies)
    {
        if(!areMedicalSuppliesSufficient(numOfMedicalSupplies))
            return false;
        this.medicalSupplies-=numOfMedicalSupplies;
        return true;
        
    }
    
  @Override
    public String toString()
    {
        return "oxygen "+oxygen+", water "+water+", food "+food+", fuel "+fuel+", battery power "+batteryPower+", medical supplies "+medicalSupplies+", spare parts "+spareParts;
    }
  
    //helper method
    private boolean isValidName(String name)
    {
        if(name!= null && (OXYGEN.equalsIgnoreCase(name) || WATER.equalsIgnoreCase(name) || FOOD.equalsIgnoreCase(name) || FUEL.equalsIgnoreCase(name) || BATTERYPOWER.equalsIgnoreCase(name) || MEDICALSUPPLIES.equalsIgnoreCase(name) || SPAREPARTS.equalsIgnoreCase(name) ))
            return true;
        return false;
    }
    
    
}