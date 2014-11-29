package hellodrools;

import java.io.InputStreamReader;
import java.io.Reader;
import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.StatefulSession;
import org.drools.compiler.PackageBuilder;
import org.drools.compiler.PackageBuilderErrors;

public class HelloDrools {

    private static RuleBase rbase = RuleBaseFactory.newRuleBase();
    private static PackageBuilder pbuilder = new PackageBuilder();
    private static StatefulSession sessionObject;
    private static String DRL_FILE = "/hellodrools/testrules.drl";

    public static void main(String[] args) {

        initialiseDrools();
        initiliseMessageObject();
        runRules();
    }

    private static void initialiseDrools() {
        //1. Read the DRL File and add to package builder
        try {
            Reader reader = new InputStreamReader(HelloDrools.class.getResourceAsStream(DRL_FILE));
            pbuilder.addPackageFromDrl(reader);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        //2. Check for any errors
        PackageBuilderErrors errors = pbuilder.getErrors();

        if (errors.getErrors().length > 0) {
            System.out.println("Some errors exists in packageBuilder");
            for (int i = 0; i < errors.getErrors().length; i++) {
                System.out.println(errors.getErrors()[i]);
            }
            throw new IllegalArgumentException("Could not parse knowledge.");
        }

        //3. Add package to rule base
        try {
            rbase.addPackage(pbuilder.getPackage());
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    // Method to fire all rules
    private static void runRules() {
        sessionObject.fireAllRules();
    }

    // Method to insert message object in session
    private static void initiliseMessageObject() {
        Message msg = new Message();
        msg.setType("Hello");
        sessionObject = rbase.newStatefulSession();
        sessionObject.insert(msg);
    }
}
