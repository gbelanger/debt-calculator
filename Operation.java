import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

abstract class Operation {

//    private static final Logger logger = LogManager.getLogger(Operation.class);

    protected LocalDate date;
    protected String desc;
    protected double annualRate;

    public Operation(LocalDate date, String desc, double annualRate) {
        this.date = date;
        this.desc = desc;
        this.annualRate = annualRate;
    }

    public LocalDate getDate() {return this.date;}

//    public String getDateLong() {
//        return this.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
//    }
//
//    public String getDateShort() {
//        return this.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
//    }

    public String getDesc() {return this.desc;}

    public double getAnnualRate() {return this.annualRate;}
}