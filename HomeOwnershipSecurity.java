import java.util.Scanner;
public class HomeOwnershipSecurity {
    public static void main(String[] args) {
        System.out.println("Welcome to Home Ownership Security!");
        System.out.println("https://www.usa.gov/eviction-and-foreclosure:");
        System.out.println("Governmental support can help with avoiding eviction and foreclosure,");
        System.out.println("as well as providing supportive housing and utilities for those in need.");
        System.out.println("For avoiding foreclosure, governmental support");
        System.out.println("redirects to free local foreclosure prevention services.");
        System.out.println("https://www.sccassessor.org/faq/understanding-proposition-13:");
        System.out.println("Property taxes can be up to 1% of assessed value and can increase by up to 2% each year.");
        System.out.println("https://sco.ca.gov/ardtax_prop_tax_postponement.html:");
        System.out.println("By age 62, home ownership security becomes more income dependent.");
        Scanner input = new Scanner(System.in);

        // User inputs
        System.out.println("What is your age at buying your home?");
        int age = input.nextInt();
        System.out.println("What is your home value at the time of purchase?");
        double value = input.nextDouble();
        System.out.println("Enter your property tax rate as a percentage (1.25 for 1.25%).");
        double taxRate = input.nextDouble();

        // Original calculation
        int years = 62 - age;
        System.out.println("You can only do property tax postponement if you are at least 62 years old.");
        System.out.println("You have: " + years + " years remaining before you can get full home ownership security.");
        double minTax = value * taxRate * 0.01;
        double maxTax = minTax * Math.pow(1.02, years);
        double avgTax = (minTax + maxTax) / 2;

        // Equity simulation after age 62
        final double MAX_ASSESSMENT_GROWTH = 0.02;
        final double PTP_INTEREST_RATE = 0.05;
        final double HOME_APPRECIATION = 0.021;
        final int SIM_YEARS = 2000; // years after eligibility

        // Determine starting values at age 62
        double assessedAt62 = value;
        double currentAt62 = value;
        int yearsTo62 = Math.max(0, 62 - age);
        for (int i = 0; i < yearsTo62; i++) {
            assessedAt62 *= (1 + MAX_ASSESSMENT_GROWTH);
            currentAt62 *= (1 + HOME_APPRECIATION);
        }

        System.out.println("\nEquity projection once you reach age 62 (postponing all taxes):");
        System.out.println("Year | HomeValue | Assessed | DeferredLien | Equity%");
        double deferredLien = 0.0;
        double assessedValue = assessedAt62;
        double currentValue = currentAt62;
        boolean trouble = false;

        for (int y = 1; y <= SIM_YEARS; y++) {
            assessedValue *= (1 + MAX_ASSESSMENT_GROWTH);
            double annualTax = assessedValue * (taxRate * 0.01);
            deferredLien += annualTax * (1 + PTP_INTEREST_RATE);
            currentValue *= (1 + HOME_APPRECIATION);
            double equityPct = (currentValue - deferredLien) / currentValue * 100;
            System.out.printf("%3d | $%,9.0f | $%,7.0f | $%,11.0f | %5.2f%%\n",
                y, currentValue, assessedValue, deferredLien, equityPct);
            if (equityPct < 40) {
                trouble = true;
                System.out.println("This may not work out.");
                break;
            }
        }
        if (!trouble) {
            System.out.println("Even under conservative assumptions, your equity never dips below 40% over "
                + SIM_YEARS + " years post-62.");
            System.out.println("This means that you are safe.");
            System.out.println("Your home value is $" + value + ",");
            System.out.println("so you must pay average yearly property taxes of $" + avgTax + ".");
            System.out.println("Your safety home price is $" + (value + years * avgTax));
            System.out.println("You are encouraged to try out this example here:");
            System.out.println("https://www.redfin.com/CA/San-Jose/2151-Oakland-Rd-95131/unit-330/home/63745251");
            System.out.println("This is conservative as home values tend to appreciate at an average rate of 5.6%");
            System.out.println("annually, which is more than the 2.1% estimation that was put in this program:");
            System.out.println("https://siliconvalleymls.com");
        }
        input.close();
    }
}