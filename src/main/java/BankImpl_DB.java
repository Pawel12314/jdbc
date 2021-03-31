import javax.management.BadAttributeValueExpException;
import java.math.BigDecimal;
import java.util.Optional;

public class BankImpl_DB implements  IBank{
    private BankDao bankDAO = new BankDAOImpl();

    @Override
    public Long createAccount(String name, String address) {
        Long id =bankDAO.addAccount(new Account(name,address));
        return id;
    }

    @Override
    public Optional<Account> getById(Long id) {
        Optional<Account> a = bankDAO.findAccount(id);
        return a;
    }

    @Override
    public Long findAccount(String name, String address) {
        return bankDAO.findAccount(name,address);
    }

    @Override
    public void deposit(Long id, BigDecimal amount) {
        try
        {
            Account a = bankDAO.findAccount(id).get();
            System.out.println(a.getName());
            a.addMoney(amount);
            bankDAO.update(a);
            //System.out.println(a.getAmount());
        }
        catch(NullPointerException e)
        {
            throw new AccountIdException("no such account exception");
        }
    }

    @Override
    public void deleteAll() {
        bankDAO.removeall();
    }

    @Override
    public BigDecimal getBalance(Long id) {
        return bankDAO.findAccount(id).get().getAmount();
    }

    @Override
    public int getAccountCount() {
        return bankDAO.getAllAccounts().size();
    }

    @Override
    public void withdraw(Long id, BigDecimal amount) {
        try
        {
            //accounts.get(id).removeMoney(amount);
            Account a = bankDAO.findAccount(id).get();
            a.removeMoney(amount);
            bankDAO.update(a);
        }
        catch(NullPointerException e)
        {
            throw new AccountIdException("no such account exception");
        }
        catch(BadAttributeValueExpException e)
        {
            throw new InsufficientFundsException("to less money");
        }
    }

    @Override
    public void transfer(Long idSource, Long idDestination, BigDecimal amount) {
        try
        {
            Account asrc = bankDAO.findAccount(idSource).get();
            Account bsrc = bankDAO.findAccount(idDestination).get();
            asrc.removeMoney(amount);
            bsrc.addMoney(amount);
            bankDAO.update(asrc);
            bankDAO.update(bsrc);

        }
        catch(NullPointerException e)
        {
            throw new AccountIdException("no such element excption");
        }
        catch(BadAttributeValueExpException e)
        {
            throw new InsufficientFundsException("to less money");
        }

    }
}
