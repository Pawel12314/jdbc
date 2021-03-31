import java.math.BigDecimal;

public class AccountOperation {

    private Account source;
    private Account destination;
    private BigDecimal amount;
    private OperationType type;
    private Long id;

    public Account getSource() {
        return source;
    }

    public Account getDestination() {
        return destination;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public OperationType getType() {
        return type;
    }

    public Long getId() {
        return id;
    }

    public void setSource(Account source) {
        this.source = source;
    }

    public void setDestination(Account destination) {
        this.destination = destination;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
