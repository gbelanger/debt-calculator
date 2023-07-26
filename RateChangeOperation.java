import java.time.LocalDate;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public class RateChangeOperation extends Operation {

//    private static final Logger logger = LogManager.getLogger(RateChangeOperation.class);

    public RateChangeOperation(LocalDate date, String desc, double interestRate) {super(date, desc, interestRate);}

}