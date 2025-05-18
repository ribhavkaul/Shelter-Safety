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
        System.out.println("What is your age at buying your home?");
        int age = input.nextInt();
        int years = 62 - age;
        System.out.println("You can only do property tax postponement if you are at least 62 years old.");
        System.out.println("You have: " + years + " years remaining before you can get full home ownership security.");
        System.out.println("What is your home value at the time of purchase?");
        double value = input.nextDouble();
        System.out.println("Enter your property tax rate as a percentage (1.25 for 1.25%).");
        double taxRate = input.nextDouble();
        double minTax = value * taxRate * 0.01;
        double maxTax = minTax * Math.pow(1.02, years);
        double avgTax = (minTax + maxTax) / 2;
        System.out.println("Your home value is $" + value + ",");
        System.out.println("so you must pay average yearly property taxes of $" + (avgTax) + ".");
        System.out.println("Your safety home price is $" + (value + years*avgTax));
        System.out.println("You are encouraged to try out this example here:");
        System.out.println("https://www.redfin.com/CA/Sunnyvale/1220-Tasman-Dr-94089/unit-328/home/1801098");
        input.close();
    }
}