package com.sample;

import java.util.Scanner;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;

/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

    public static final void main(String[] args) {
        try {
            // load up the knowledge base
            KnowledgeBase kbase = readKnowledgeBase();
            StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
            KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "test");
            // go !
            Check f = new Check(); 
            f.setData();
            ksession.insert(f);
            ksession.fireAllRules();
            logger.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private static KnowledgeBase readKnowledgeBase() throws Exception {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("Sample.drl"), ResourceType.DRL);
        KnowledgeBuilderErrors errors = kbuilder.getErrors();
        if (errors.size() > 0) {
            for (KnowledgeBuilderError error: errors) {
                System.err.println(error);
            }
            throw new IllegalArgumentException("Could not parse knowledge.");
        }
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        return kbase;
    }

    public static class Message {

        public static final int HELLO = 0;
        public static final int GOODBYE = 1;

        private String message;

        private int status;

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

    }

public static class Check {
    Scanner in = new Scanner (System.in);
    public int s3 = 0, s4 = 0, s5 = 0, o = 0;
  
    public int s1 = 0, s2 = 0;
    public String s;
    public String inp = "";
    public String d1 = "1235";
    public String d2 = "23";
    public String d3 = "145";
    public String d4 = "345";
    public String d5 = "123";
    public void setData()
    {
        System.out.println ("Enter symptoms");
        s = in.nextLine();
        for (int i = 1; i < s.length(); i += 2)
        {
            if (s.charAt(i) == '1') {
                s1 = 1;
                inp = inp + '1';
            } else if ((s.charAt(i) == '2')) {
                s2 = 1;
                inp = inp + '2';
            } else if (s.charAt(i) == '3') {
                s3 = 1;
                inp = inp + '3';
            } else if (s.charAt(i) == '4') {
                s4 = 1;
                inp = inp + '4';
            } else if (s.charAt(i) == '5') {
                s5 = 1;
                inp = inp + '5';
            } else {
                o = 1;
                inp = inp + "-1";
            }
        }
        System.out.println(inp);
    }
    
  /*  public int ret()
    {
        return s1;
    }
    public int ret1()
    {
        return s2;
    }
    public int ret3()
    {
        return s3;
    }public int ret4()
    {
        return s4;
    }public int ret5()
    {
        return s5;
    }public int reto()
    {
        return o;
    }
*/
}

}
