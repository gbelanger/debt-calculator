import java.text.DecimalFormat;
import java.util.List;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public class DebtCalculator {

//    private static final Logger logger = LogManager.getLogger(DebtCalculator.class);

    static final DecimalFormat df = new DecimalFormat("0.00");

    private double totalCapital = 0;
    private double totalInterest = 0;
    private double balanceOwed = 0;

    public DebtCalculator(List<Operation> operationList) {
        processOperations(operationList);
    }

    private void processOperations(List<Operation> operationList) {
        Operation firstOperation = operationList.get(0);
        LocalDate previousDate = firstOperation.getDate();
        double annualRate = firstOperation.getAnnualRate();
        for (Operation operation : operationList) {
            LocalDate date = operation.getDate();
            double days = ChronoUnit.DAYS.between(previousDate, date);
            if (operation instanceof TransactionOperation) {
                double amount = ((TransactionOperation) operation).getAmount();
                if (amount < 0) {
                    System.out.println("** Deposit ("+date.toString()+"): "+ df.format(amount) +" **");
//                    System.out.println(" - balance was: " + df.format(this.balanceOwed));
                }
                else {System.out.println("Withdrawal ("+date.toString()+"): " + df.format(amount));}
                this.totalCapital += amount;
            }
            else {
                System.out.println("Rate change ("+date.toString()+"): " + df.format(annualRate));
            }
            if (days > 0) {
//                System.out.println(" - computing interest over past "+days+" days since "+previousDate.toString());
                double sum = 0;
                for (int i = 0; i < days; i++) {
                    double interest = this.balanceOwed * annualRate / 100 / 365;
                    sum += interest;
                    this.totalInterest += interest;
                    this.balanceOwed = this.totalCapital + this.totalInterest;
                }
                System.out.println(" - interest added: " + df.format(sum)+ " ("+days+" days at "+annualRate+"% per year since "+previousDate.toString()+")");
            }
            else {
                System.out.println(" - same day: no added interest");
                this.balanceOwed = this.totalCapital + this.totalInterest;
            }
            System.out.println(" - balance owed: " + df.format(this.balanceOwed) + " (" + df.format(this.totalCapital) + " + " + df.format(this.totalInterest) + ")");
            annualRate = operation.getAnnualRate();
            previousDate = date;
        }
    }

    public double getBalanceOwed() {
        return balanceOwed;
    }

    public double getTotalCapital() {
        return totalCapital;
    }

    public double getTotalInterest() {
        return totalInterest;
    }
}