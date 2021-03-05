import javax.management.BadAttributeValueExpException;
import java.math.BigDecimal;
import java.util.HashMap;

public class BankImpl implements IBank {
    private HashMap<Long,Account> accounts = new HashMap<Long,Account>();
    /**
     * Tworzy nowe lub zwraca id istniejącego konta.
     *
     * @param name imie i nazwisko własciciela
     * @param address adres własciciela
     * @return id utworzonego lub istniejacego konta.
     */
    public Long createAccount(String name, String address)
    {
        Long a=0L;
        for(Long x : accounts.keySet())
        {
            if(x>a)a=x;
        }
        //System.out.println("new id: "+a);
        accounts.put(a+1,new Account(name,address));

        return a;
    }
    public int getAccountCount()
    {
        return accounts.size();
    }

    /**
     * Znajduje identyfikator konta.
     *
     * @param name imię i nazwisko właściciela
     * @param address adres właściciela
     * @return id konta lub null, gdy brak konta o podanych parametrach
     */
    public Long findAccount(String name, String address)
    {
        for(Long x : accounts.keySet())
        {
            Account a  = accounts.get(x);
            if(a.getAddress().equals(address) && a.getName().equals(name))

                {
                    return x;
                }

        }
        throw new AccountIdException("no such account exception");
    }

    /**
     * Dodaje srodki do konta.
     *
     * @param id
     * @param amount srodki
     * @throws AccountIdException, gdy id konta jest nieprawidlowe
     */
    public void deposit(Long id, BigDecimal amount)
    {
        try
        {
            Account a = accounts.get(id);
            System.out.println(a.getName());
            a.addMoney(amount);
            //System.out.println(a.getAmount());
        }
        catch(NullPointerException e)
        {
            throw new AccountIdException("no such account exception");
        }

    }

    /**
     * Zwraca ilosc srodkow na koncie.
     *
     * @param id
     * @return srodki
     * @throws AccountIdException, gdy id konta jest nieprawidlowe
     */
    public BigDecimal getBalance(Long id)
    {
        try
        {
            return accounts.get(id).getAmount();
        }
        catch(NullPointerException e)
        {
            throw new AccountIdException("no such account exception");
        }

    }

    /**
     * Pobiera srodki z konta.
     *
     * @param id
     * @param amount srodki
     * @throws AccountIdException, gdy id konta jest nieprawidlowe
     * @throws InsufficientFundsException, gdy srodki na koncie nie sa
     * wystarczajace do wykonania operacji
     */
    public void withdraw(Long id, BigDecimal amount)
    {
        try
        {
            accounts.get(id).removeMoney(amount);
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

    /**
     * Przelewa srodki miedzy kontami.
     *
     * @param idSource
     * @param idDestination
     * @param amount srodki
     * @throws AccountIdException, gdy id konta jest nieprawidlowe
     * @throws InsufficientFundsException, gdy srodki na koncie nie sa
     * wystarczajace do wykonania operacji
     */
    public void transfer(Long idSource, Long idDestination, BigDecimal amount)
    {
        try
        {
            accounts.get(idSource).removeMoney(amount);
            accounts.get(idDestination).addMoney(amount);
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

    class InsufficientFundsException extends RuntimeException {
        public InsufficientFundsException(String errorMessage) {
            super(errorMessage);
        }
    };

    class AccountIdException extends RuntimeException {
        public AccountIdException(String errorMessage) {
            super(errorMessage);
        }
    };

}