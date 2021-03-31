import java.math.BigDecimal;
import java.util.Optional;

public interface IBank {
    /**
     * Tworzy nowe lub zwraca id istniejącego konta.
     *
     * @param name imie i nazwisko własciciela
     * @param address adres własciciela
     * @return id utworzonego lub istniejacego konta.
     */

    Long createAccount(String name, String address);
    Optional<Account> getById(Long id);
    /**
     * Znajduje identyfikator konta.
     *
     * @param name imię i nazwisko właściciela
     * @param address adres właściciela
     * @return id konta lub null, gdy brak konta o podanych parametrach
     */
    Long findAccount(String name, String address);

    /**
     * Dodaje srodki do konta.
     *
     * @param id id konta
     * @param amount srodki
     * @throws IBank.AccountIdException gdy id konta jest nieprawidlowe
     */

    void deposit(Long id, BigDecimal amount);
    void deleteAll();
    /**
     * Zwraca ilosc srodkow na koncie.
     *
     * @param id id konta
     * @return srodki
     * @throws IBank.AccountIdException gdy id konta jest nieprawidlowe
     */

    BigDecimal getBalance(Long id);
    int getAccountCount();
    /**
     * Pobiera srodki z konta.
     *
     * @param id id konta
     * @param amount srodki
     * @throws IBank.AccountIdException gdy id konta jest nieprawidlowe
     * @throws IBank.InsufficientFundsException gdy srodki na koncie nie sa
     * wystarczajace do wykonania operacji
     */
    void withdraw(Long id, BigDecimal amount);

    /**
     * Przelewa srodki miedzy kontami.
     *
     * @param idSource id konta
     * @param idDestination id konta
     * @param amount srodki
     * @throws IBank.AccountIdException gdy id konta jest nieprawidlowe
     * @throws IBank.InsufficientFundsException gdy srodki na koncie nie sa
     * wystarczajace do wykonania operacji
     */
    void transfer(Long idSource, Long idDestination, BigDecimal amount);

    class InsufficientFundsException extends RuntimeException {
        public InsufficientFundsException(String msg)
        {
            super(msg);
        }
    };

    class AccountIdException extends RuntimeException {
        public AccountIdException(String msg)
        {
            super(msg);
        }
    };
}
