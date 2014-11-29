package com.sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
	public static KnowledgeBase kbase;
	public static StatefulKnowledgeSession ksession;
	public static KnowledgeRuntimeLogger logger;
    public static final void main(String[] args) {
        try {
            // load up the knowledge base
            kbase = readKnowledgeBase();
            ksession = kbase.newStatefulKnowledgeSession();
            logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "test");
            
            // go !
            haha();
            System.out.println("finish");
            logger.close();
            
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private static void haha() throws IOException {
    	Message message = new Message();
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        message.s = br.readLine();
        
        ksession.insert(message);
        ksession.fireAllRules();
		
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

        public String s;

        
        public void replace (String s1, String s2) {
    		int idx = s.indexOf(s1);
    		String s3 = s.substring(0, idx);
    		String s4 = s.substring(idx + s1.length(),s.length());
    		s = s3 + s2 + s4;
    		
    	}
    }

}
