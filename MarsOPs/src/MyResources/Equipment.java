
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
        this.name = name;
        this.TotalAmount = TotalAmount;
        this.broken = broken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
