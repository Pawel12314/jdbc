import javax.management.BadAttributeValueExpException;
import java.math.BigDecimal;

public class Account {
    private String name;
    private String address;
    private BigDecimal amount;
    private Long id;
    public Account(String name,String address)
    {
        this.name=name;
        this.address=address;
        amount = new BigDecimal(0);
    }
    public Account(Long id, String name,String address,BigDecimal amount)
    {
        this.name=name;
        this.address=address;
        this.id = id;
        this.amount = amount;
        amount = new BigDecimal(0);
    }
    public Account()
    {

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

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
