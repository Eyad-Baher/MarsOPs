
package MyResources;

/**
 *
 * @author abdel
 */
public class Equipment {
    private String name;
    private int TotalAmount;
    private boolean broken;
    private int numBroken;

    public Equipment(String name, int TotalAmount, boolean broken) {
        if(name==null || name.equals(""))
            this.name="Unknown equipment";
        else
            this.name = name;
        
        if (TotalAmount>0)
            this.TotalAmount = TotalAmount;
        else
            this.TotalAmount=0;
        this.broken = broken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name==null || name.equals(""))
            this.name="Unknown equipment";
        else
            this.name = name;
    }

    public int getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(int TotalAmount) {
        if (TotalAmount>0)
        this.TotalAmount = TotalAmount;
        else
            this.TotalAmount=0;
    }

    public boolean isBroken() {
        return broken;
    }

    public void setBroken(boolean broken) {
        this.broken = broken;
    }
    
    public void repair(int num)
    {
        numBroken -= num;
        if(numBroken==0)
        {
            broken = false;
        }else
            broken = true;
    }
    
    public void damage(int num)
    {
        numBroken+=num;
        broken = true;
    }
    
    
}
