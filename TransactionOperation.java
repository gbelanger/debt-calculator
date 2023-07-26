import java.time.LocalDate;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public class TransactionOperation extends Operation {

//    private static final Logger logger = LogManager.getLogger(TransactionOperation.class);
    protected double amount;

    public TransactionOperation(LocalDate date, String desc, double interestRate, double amount) {
        super(date, desc, interestRate);
        this.amount = amount;
    }

    public double getAmount() {return amount;}

}