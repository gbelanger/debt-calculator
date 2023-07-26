import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public class ComputeDebt {

//    private static final Logger logger = LogManager.getLogger(ComputeDebt.class);

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java ComputeDebt path/to/filename");
            System.exit(0);
        }
        String filename = args[0];
        OperationFileReader reader = new OperationFileReader(filename);
        List<Operation> operationList = reader.getOperationList();
        DeptCalculator calculator = new DeptCalculator(operationList);
        double balance = calculator.getBalanceOwed();
        double capital = calculator.getTotalCapital();
        double interest = calculator.getTotalInterest();
        System.out.println("FINAL TOTALS:");
        System.out.println(" - capital = " +  DeptCalculator.df.format(capital));
        System.out.println(" - interest = " + DeptCalculator.df.format(interest));
        System.out.println(" - balance = " + DeptCalculator.df.format(balance));
    }

}