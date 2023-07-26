import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.opencsv.*;
import com.opencsv.exceptions.CsvException;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public class OperationFileReader {
//    private static final Logger logger = LogManager.getLogger(OperationReader.class);

    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM uuuu");
    String filename;
    ArrayList<Operation> operationList;

    public OperationFileReader(String filename) throws IOException {
        this.filename = filename;
        readFile(filename);
    }

    private void readFile(String filename) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(filename));

        CSVParser parser = new CSVParserBuilder()
                .withSeparator(',')
                // .withIgnoreQuotations(false)
                .build();

        CSVReader csvReader = new CSVReaderBuilder(reader)
                .withSkipLines(0)
                .withCSVParser(parser)
                .build();

        // Read all the data into ArrayList
        List<String[]> lines;
        try {
            lines = csvReader.readAll();
        } catch (CsvException e) {
            throw new IOException("failed to load CSV data: " + e.getMessage());
        }
        reader.close();
        csvReader.close();

        this.operationList = new ArrayList<Operation>();

        // Read the data
        int lineNumber = 1;
        boolean debug = true;
        for (int lineIdx = 1; lineIdx < lines.size(); lineIdx++) {
            String[] dataline = lines.get(lineIdx);
//            if (debug) logger.info("Reading line number " + lineNumber);

            int l = 0;
            String dateAsString = dataline[l++].trim();
//            if (debug) logger.info("date = " + dateAsString);
            LocalDate date = LocalDate.parse(dateAsString, format);

            String desc = dataline[l++].trim();
//            if (debug) {logger.info("desc = " + desc);}

            double annualRate = Double.parseDouble(dataline[l++].trim());
//            if (debug) {logger.info("annualRate = " + annualRate);}

            try {
                double amount = Double.parseDouble(dataline[l++].trim());
                TransactionOperation operation = new TransactionOperation(date, desc, annualRate, amount);
//            if (debug) {logger.info("Adding transaction: amount = " + amount);}
                this.operationList.add(operation);
            } catch (java.lang.NumberFormatException e) {
                RateChangeOperation operation = new RateChangeOperation(date, desc, annualRate);
//            if (debug) {logger.info("Adding rate change: new rate = " + annualRate);}
                this.operationList.add(operation);
            }
            lineNumber++;
//            if (debug) System.out.println();
        }
        this.operationList.trimToSize();
    }

    public List<Operation> getOperationList() {return operationList;}
}