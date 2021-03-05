//import org.junit.jupiter.api.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class AccountTest {

    String name;
    String address;
    Account a;
    @BeforeMethod
    public void mybefore()
    {
        name = "Dariusz";
        address = "ulica1";
        a = new Account(name,address);
        //address="xxx";
    }
    @Test
    public void testNull() {

        assert a !=null;

    }
    @Test
    public void testName() {

        assert a.getName().equals(name);
    }

    @Test
    public void testAddress() {

        assert a.getAddress().equals(address);
    }

    @Parameters({"addit"})
    @Test(dependsOnMethods = { "testNull","testName","testAddress" })
    public void testAddingMoney(long addit)
    {

        BigDecimal b = new BigDecimal(addit);
        a.addMoney(b);

        assert a.getAmount().compareTo(b)==0;
    }



}