import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class MortgageCalculator {
    // Read the file
    public ArrayList<String> readFile(String path) {
        ArrayList<String> lines = new ArrayList<>();

        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            String line;

            while((line = reader.readLine()) != null){
                lines.add(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    // Take the file and create mortgage objects of each line (that fits)
    public ArrayList<Mortgage> createMortgages(ArrayList<String> file) {
        ArrayList<Mortgage> mortgages = new ArrayList<>();

        String[] headers = file.remove(0).split(",");

        // Loop over each line in the file
        for(String line : file) {
            // Split up the line in different parts by comma (except when in "")
            String[] parts = line.split("(?!\".*)(,)(?!.*\")");

            // Remove all lines that aren't complete
            if(parts.length < 4) {
                continue;
            }

            // Use fixCustomer() to strip all non-letter characters from the name
            // (and in this case replace the comma with whitespace between first and
            // last name
            String customer = fixCustomer(parts[0]);
            float totalLoan = Float.parseFloat(parts[1]);
            float interest = Float.parseFloat(parts[2]);
            int years = Integer.parseInt(parts[3]);

            Mortgage mortgage = new Mortgage(customer, totalLoan, interest, years);
            mortgages.add(mortgage);
        }
        return mortgages;
    }

    // Clean up customer name
    public String fixCustomer(String name) {
         name = name.replace(",", " ");
         name = name.replaceAll("[^a-öA-Ö\s]", "");
         return name;
    }

    // Prints the desired output for a mortgage object
    public void printOutput(Mortgage mortgage, int i) {
            System.out.println("Prospect " + i + ": " +
                    mortgage.getCustomer() + " wants to borrow " +
                    mortgage.getTotalLoan() + " € for a period of " +
                    mortgage.getYears() + " years and pay " +
                    String.format("%.2f", mortgage.getMonthlyPayment()) + "€ each month");
    }

    public MortgageCalculator(String path   ) {
        ArrayList<String> file = readFile(path);
        ArrayList<Mortgage> mortgages = createMortgages(file);

        System.out.println("****************************************************************************************************");
        for(Mortgage mortgage : mortgages) {
            mortgage.calculateMonthlyPayment();
            System.out.println(mortgage.getCustomer() + " wants to borrow " +
                    mortgage.getTotalLoan() + " € for a period of " +
                    mortgage.getYears() + " years and pay " +
                    mortgage.getMonthlyPayment() + "€ each month");
        }
        System.out.println("****************************************************************************************************");
    }

}
