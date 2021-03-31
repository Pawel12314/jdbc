//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;

//import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.testng.Assert.fail;

public class BankTest {


    private  IBank b;
    private String name;
    private String address;
    private BigDecimal amount;
    @AfterMethod
    public void after()
    {
        b.deleteAll();
    }
    @BeforeMethod
    public  void before()
    {

        b = new BankImpl_DB();
        b.deleteAll();
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

    //@Parameters({ "initial" })

    @Test
    public void testWD()
    {
        BigDecimal initBD = new BigDecimal(1000);
        Long x = b.findAccount(name,address);
        b.deposit(x,initBD);
        BigDecimal wd = new BigDecimal(500);

        b.withdraw(x,wd);



        assert b.getBalance(x).compareTo(amount.subtract(wd))==0;
    }
    //@Parameters({ "tosmall" })
    @Test(expectedExceptions = BankImpl.InsufficientFundsException.class)
    public void testWDminus()
    {
        BigDecimal initBD = new BigDecimal(200);
        Long x = b.findAccount(name,address);
        b.deposit(x,initBD);
        BigDecimal wd = new BigDecimal(500);
        b.withdraw(x,wd);
        //Throwable thrown = assertThrows(BankImpl.InsufficientFundsException, () -> foo.doStuff());
        //assertDoesNotThrow(()->b.getBalance(x));

       // Throwable thrown = assertThrows(BankImpl.InsufficientFundsException.class,()->b.withdraw(x,wd));
        //BigDecimal amount

       // assert (thrown instanceof BankImpl.InsufficientFundsException);



        //assert(thrown instanceof BankImpl.InsufficientFundsException);
        //assert(thrown instanceof BankImpl.AccountIdException);
        //BigDecimal res = b.getBalance(x);
        assert b.getBalance(x).compareTo(initBD)==0;
    }

}
