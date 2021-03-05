import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testng.Assert.fail;

public class BankTest {


    private  IBank b;
    private String name;
    private String address;
    private BigDecimal amount;
    @BeforeMethod
    public  void before()
    {

        b = new BankImpl();
        name="Adam";
        address="Ulica1";
        b.createAccount(name,address);
        assert b!=null;

        amount = new BigDecimal(1000);
    }


    @Test
    public void testfind() {
        //Throwable thrown = assertThrows(BankImpl.AccountIdException.class, () -> b.findAccount(name,address));
        b.findAccount(name,address);
        //assert b.findAccount(name,address)!=null;
       // assert(thrown instanceof BankImpl.AccountIdException);

    }

    @Test
    public void testdeposit() {
        //Long x ;//= b.findAccount(name,address);

        //Throwable thrown = assertThrows(Exception.class, () ->{
            Long x = b.findAccount(name,address);


            b.deposit(x,amount);

            assert b.getBalance(x).compareTo(amount)==0;



        //assert b.findAccount(name,address)!=null;
        //assert(thrown instanceof BankImpl.AccountIdException);


    }
    @Test
    public void testSizeBank()
    {
        assert b.getAccountCount()>0;
    }

    @Parameters({ "initial" })

    @Test
    public void testWD(long initial)
    {
        BigDecimal initBD = new BigDecimal(initial);
        Long x = b.findAccount(name,address);
        b.deposit(x,initBD);
        BigDecimal wd = new BigDecimal(500);

        b.withdraw(x,wd);



        assert b.getBalance(x).compareTo(amount.subtract(wd))==0;
    }
    @Parameters({ "tosmall" })
    @Test
    public void testWDminus(long tosmall)
    {
        BigDecimal initBD = new BigDecimal(tosmall);
        Long x = b.findAccount(name,address);
        b.deposit(x,initBD);
        BigDecimal wd = new BigDecimal(500);

        //Throwable thrown = assertThrows(BankImpl.InsufficientFundsException, () -> foo.doStuff());
        //assertDoesNotThrow(()->b.getBalance(x));

        Throwable thrown = assertThrows(BankImpl.InsufficientFundsException.class,()->b.withdraw(x,wd));
        //BigDecimal amount

        assert (thrown instanceof BankImpl.InsufficientFundsException);



        //assert(thrown instanceof BankImpl.InsufficientFundsException);
        //assert(thrown instanceof BankImpl.AccountIdException);
        //BigDecimal res = b.getBalance(x);
        assert b.getBalance(x).compareTo(initBD)==0;
    }

}
