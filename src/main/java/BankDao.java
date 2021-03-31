import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BankDao {
/*
    void addAccount(String name,String addr);
    void findAccount(String name,String addr);
    void deposit(Long id, BigDecimal amount);
    BigDecimal getBalance(Long id);
    int getAccountCount();
    void widhdraw(Long id,BigDecimal amount);
    void transfer(Long src,Long dest,BigDecimal amount);*/
    Long addAccount(Account a);
    Optional<Account> findAccount(Long id);
    void update(Account a);
    Long findAccount(String name,String addr);
    List<Account>  getAllAccounts();
    void removeall();

}
