package com.sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br= new BufferedReader(isr);
            
            String s = "", s1;
            String flag = "0";
            while (flag.equals("0")) {
                System.out.println("Enter new Symptom");
                try {
                    s1 = br.readLine();
                    s += s1;
                    System.out.println("do you have more symptoms ? y/n");
                    flag = br.readLine();
                    if (flag.equals("y")) {
                        flag = "0";
                    } 
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            conditions f = new conditions(s);        
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

    
    public static class conditions {
        public int s1, s2, s3, s4, s5, other;
        public String d1, d2, d3, d4, d5;
        public String s, str;
        public conditions (String in) {
            s1 = s2 = s3 = s4 = s5 = other = 0;
            d1 = "1235";
            d2 = "23";
            d3 = "145";
            d4  ="345";
            d5 = "123";
            s = in;
            str = "";
        }
        
        public void setData() {
            
            for (int i = 1; i < s.length(); i = i + 2) {
                if (s.charAt(i) == '1') {
                    s1  = 1;
                    str += '1';
                } else if (s.charAt(i) == '2') {
                    s2 = 1;
                    str += '2';
                } else if (s.charAt(i) == '3') {
                    s3 = 1;
                    str += '3';
                } else if (s.charAt(i) == '4') {
                    s4 = 1;
                    str += '4';
                } else if (s.charAt(i) == '5') {
                    s5 = 1;
                    str += '5';
                } else {
                    other = 1;
                    str += '0';
                }
            }
            System.out.println(str);
        }
    }

}
