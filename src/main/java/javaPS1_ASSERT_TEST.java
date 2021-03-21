import java.io.Console;
import java.math.BigDecimal;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class javaPS1_ASSERT_TEST {
    BankImpl b;
    Console cons;
    Scanner scan = new Scanner(System.in);
    private final static Logger log = getLogger();

    public static Logger getLogger()
    {
        return Logger.getLogger(javaPS1_ASSERT_TEST.class.getName());

    }

    public void logError(String msg, Exception e)
    {
        log.log(Level.SEVERE,msg, e);
    }
    public void logInfo(String msg)
    {
        log.log(Level.INFO,msg);
    }
    public String readLine()
    {
        return scan.nextLine();
    }

    public void deposit()
    {
        try
        {
            String string_id=readLine();
            Long long_id = Long.parseLong(string_id);

            try
            {
                String string_amount=readLine();
                Float float_amount = Float.parseFloat(string_amount);
                BigDecimal bd_amount = new BigDecimal(float_amount);
                try
                {
                    b.deposit(long_id,bd_amount);
                    logInfo("deposit: zdeponowano "+string_amount+" złotych");
                }
                catch(IBank.AccountIdException aie)
                {

                    logError("deposit:nie znaleziono konta-depozyt",aie);
                }
            }
            catch (NumberFormatException nfe)
            {
                logError("deposit: błąd parsowania kwoty-depozyt",nfe);
            }
        }
        catch(NumberFormatException nfe)
        {
            logError("deposit: błąd parsowania identyfikatora-depozyt",nfe);
        }
    }
    public void addAcc()
    {
        String nametocreate = readLine();
        String addrtocreate = readLine();
        b.createAccount(nametocreate,addrtocreate);
        logInfo("dodano konto:"+nametocreate+" "+addrtocreate);
    }
    public void findAcc()
    {
        String nametofind = readLine();
        String addrtofind = readLine();
        try
        {
            long x = b.findAccount(nametofind,addrtofind);
            logInfo("wydano nr konta:"+ x);
            System.out.println(x);
        }
        catch(IBank.AccountIdException aie)
        {
            logError("nie znaleziono konta-próba znalezienia konta",aie);
        }

    }
    public void getbalance()
    {
        try
        {
            String string_id=readLine();
            logInfo("próba parsowania id-informacja o stanie konta"+string_id);
            Long long_id = Long.parseLong(string_id);
            try
            {
                logInfo("próba pobrania stanu konta"+string_id);
                BigDecimal balance =b.getBalance(long_id);
                System.out.println(balance);
                logInfo("sprawdzono stan konta.nr konta:("+
                        long_id+")"+
                        "kwota: "+balance.toString());
            }
            catch (IBank.AccountIdException aie)
            {
                logError("nie znaleziono konta: id-informacja o stanie konta"+long_id,aie);
            }
        }
        catch (NumberFormatException nfe)
        {
            logError("błąd parsowania id:-informacja o stanie konta",nfe);
        }
    }
    public void wd()
    {
        try
        {
            String string_id=readLine();
            logInfo("próba parsowania id - wypłata"+string_id);
            Long long_id = Long.parseLong(string_id);
            try
            {

                String string_amount=readLine();
                logInfo("próba pobrania kwoty do wyplacenia"+string_amount);
                Float float_amount = Float.parseFloat(string_amount);
                BigDecimal bd_amount = new BigDecimal(float_amount);
                try
                {
                    logInfo("próba wypłaty:id"+long_id+"kwota"+bd_amount.toString()+string_id);
                    b.withdraw(long_id,bd_amount);
                }
                catch(IBank.AccountIdException aie)
                {
                    logError("podano złe id konta- wypłata",aie);
                }
            catch (IBank.InsufficientFundsException ife)
            {
                logError("brak środków na koncie- wypłata",ife);
            }
            }
            catch (NumberFormatException nfe)
            {
                logError("podano nieprawidłową kwotę - wypłata",nfe);
            }
        }
        catch(NumberFormatException nfe)
        {
            logError("podano id w złym formacie- wypłata",nfe);
        }
    }
    public void transfer()
    {
        try
        {
            String string_id=readLine();
            Long long_id = Long.parseLong(string_id);
            try
            {
                String string_id2=readLine();
                Long float_id2 = Long.parseLong(string_id2);

                try
                {
                    String string_amount =readLine();
                    Float float_amount = Float.parseFloat(string_amount);
                    BigDecimal bd_amount = new BigDecimal(float_amount);
                    try
                    {

                        b.transfer(long_id,float_id2,bd_amount);
                        logInfo("przekazano pieniądze z konta na konto");
                    }
                    catch(IBank.AccountIdException aie)
                    {
                       logError("nie znaleziono konta - transfer",aie);
                    }
                    catch (IBank.InsufficientFundsException ife)
                    {
                        logError("brak środków na koncie - transfer",ife);
                    }
                }
            catch (NumberFormatException nfe)
            {
                logError("nieprawidłowe dane - transfer",nfe);
            }

            }
            catch (NumberFormatException nfe)
            {
                logError("nieprawidłowe dane - transfer",nfe);
            }
        }
        catch(NumberFormatException nfe)
        {
            logError("nieprawidłowe dane - transfer",nfe);
        }
    }

    public void run()
    {

       b= new BankImpl();
        //cons=System.console();
        while(true)
        {
            System.out.println("addacc (name|address)\n"+"findacc(name|address)\n"
                    +"deposit(id,amount)\n"+"getbalance(id)"
                    + "wd(id,amount)\n"+"transfer(idsrc,iddst,amount)\n");
            String line = readLine();
            switch (line)
            {
                case "addacc":
                    addAcc();
                    break;
                case "findacc":
                    findAcc();
                    break;
                case "deposit":
                    deposit();
                    break;
                case "getbalance":
                    getbalance();
                    break;
                case "wd":
                    wd();
                    break;
                case "transfer":
                    transfer();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("podano złą instrukcję");
                    break;

            }
        }
    }
    public static void main(String[] args){
        javaPS1_ASSERT_TEST session = new javaPS1_ASSERT_TEST();
        session.run();




    }
}
