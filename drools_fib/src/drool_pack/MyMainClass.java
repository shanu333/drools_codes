/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package drool_pack;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.StatefulSession;
import org.drools.compiler.DroolsParserException;
import org.drools.compiler.PackageBuilder;

/**
 *
 * @author shaan
 */
public class MyMainClass {
    private static RuleBase rbase = RuleBaseFactory.newRuleBase();
    private static PackageBuilder pbuilder =  new PackageBuilder();
    private static StatefulSession sessionObject;
    private static String DRL_FILE = "/drool_pack/myDRL.drl";
    public static void main(String[] args) {
        intialiseDrools();
        intialiseMessageObject();
        runRules();
        
    }

    private static void intialiseDrools() {
        try {
            Reader reader = new InputStreamReader(MyMainClass.class.getResourceAsStream(DRL_FILE));
            pbuilder.addPackageFromDrl(reader);
            rbase.addPackage(pbuilder.getPackage());            
        } catch (DroolsParserException | IOException e) {
            System.out.println("Exception " + e.getMessage());
        }
    }

    private static void intialiseMessageObject() {
        Fibonacci f = new Fibonacci(5);
        
        sessionObject = rbase.newStatefulSession();
        sessionObject.insert(f);
    }

    private static void runRules() {
        sessionObject.fireAllRules();
    }
    
}