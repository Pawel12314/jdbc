import javax.management.BadAttributeValueExpException;
import java.math.BigDecimal;

public class Account {
    private String name;
    private String address;
    private BigDecimal amount;
    public Account(String name,String address)
    {
        this.name=name;
        this.address=address;
        amount = new BigDecimal(0);
    }
    public void addMoney(BigDecimal x)
    {
        this.amount = this.amount.add(x);
    }
    public void removeMoney(BigDecimal x) throws BadAttributeValueExpException {
        if(amount.compareTo(x)==-1)throw new BadAttributeValueExpException("not enough value");
        this.amount = this.amount.subtract(x);
    }
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
