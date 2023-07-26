import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DebtCalculatorTest {
    public static void main(String[] args) {
        // Generate operations
        List<Operation> operationList = new ArrayList<>();
        LocalDate date1 = LocalDate.parse("2013-01-01");
        String desc = "Initial withdrawal";
        double rate = 3;
        double amount = 10000;
        TransactionOperation seedOperation = new TransactionOperation(date1,desc,rate,amount);
        operationList.add(seedOperation);
        LocalDate date2 = LocalDate.parse("2023-01-01");
        desc = "Final operation";
        RateChangeOperation finalOperation = new RateChangeOperation(date2,desc,rate);
        operationList.add(finalOperation);

        // Compute debt
        DebtCalculator calculator = new DebtCalculator(operationList);
        double balance = calculator.getBalanceOwed();
        double capital = calculator.getTotalCapital();
        double interest = calculator.getTotalInterest();
        System.out.println("FINAL TOTALS:");
        System.out.println(" - capital = " + capital);
        System.out.println(" - interest = " + interest);
        System.out.println(" - balance = " + balance);
    }
}
