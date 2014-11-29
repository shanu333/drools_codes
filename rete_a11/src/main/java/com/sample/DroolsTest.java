package com.sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



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
import org.mvel2.sh.command.basic.Set;


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
        message.rhs = br.readLine();
        System.out.println(message.s);
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
        public int sss;
        public String rhs;
        public int kk;
        public int ff;
        Vector v1;
        Map mMap, mMap2;
        Message() {
        	 kk = 0;
        	 sss=0;
        	 ff = 0;
        	 v1 = new Vector();
        	 mMap = new HashMap();
        	 mMap2 = new HashMap();
        }

        public boolean match()
        {
        	if (rhs.equals(s)) {
        		ff = 1;
        		return true;
        	}
        	return false;
        }
        public void fofo()
        {
        	sss = 1;
        }
        public void replace (String s1, String s2) {
    		int idx = s.indexOf(s1);
    		String s3 = s.substring(0, idx);
    		String s4 = s.substring(idx + s1.length(),s.length());
    		s = s3 + s2 + s4;
    		
    	}
        public boolean sortit2() {
        	SORT();
        	//System.out.println("Start sort");
        	if (mMap.containsKey(s.toString())) {
        		return false;
        	}
        	mMap.put(s, "1");
        	String exp = s;
            int sz = exp.length();         
            
            sz = v1.size();
            for (Object o : v1) {
            //	System.out.println(o);
            }
            for (int i = 0; i < sz; i++) {
                String sn = "";
                sn = v1.get(i).toString();
                exp = exp.replaceAll(sn + "''", sn);
                //System.out.println(exp);
                exp = exp.replaceAll(sn + "\\.1", sn);
                exp = exp.replaceAll(sn + "\\+1", "1");
            }
            
            for (int i = 0; i < sz; i++) {
                for (int j = i+1; j < sz; j++) {
                    String sn1 = "", sn2 = "";
                    sn1 = v1.get(i).toString();
                    sn2 = v1.get(j).toString();
                    exp = exp.replaceAll("\\(" + sn1 + "\\+" + sn2 + "\\)'", sn1 + "'" + "\\." + sn2 + "'");
                    exp = exp.replaceAll("\\(" + sn1 + "\\." + sn2 + "\\)'", sn1 + "'" + "\\+" + sn2 + "'");
                    
                }
            }
            System.out.println(exp);
            for (int i = 0; i < sz; i++) {
                for (int j = 0; j < sz; j++) {
                    for (int k = 0; k < sz; k++) {
                    	
                        String sn1 = "", sn2 = "", sn3 = "", s4 = exp;
                        sn1 = v1.get(i).toString();
                        sn2 = v1.get(j).toString();
                        sn3 = v1.get(k).toString();
                        
                        
                        sn1 = add_haha(sn1);
                        sn2 = add_haha(sn2);
                        sn3 = add_haha(sn3);
                        
                        //System.out.println("sn1 = "+ sn1 + " sn2 = " + sn2 + " sn3 = " + sn3 );
                        s4 = exp;
                        exp = exp.replaceAll(sn1 + "\\+\\(" + sn2 + "\\." + sn3 + "\\)", "\\(" + sn1 + "\\+" + sn2 + "\\)\\.\\(" + sn1 + "\\+" + sn3 + "\\)");
                        if (!s4.equals(exp)) {
                        	if (sn1.equals(sn2) || sn1.equals(sn3)) {
                        		System.out.println("A+(A+B) = A");
                        		exp = exp.replaceAll( "\\(" + sn1 + "\\+" + sn2 + "\\)\\.\\(" + sn1 + "\\+" + sn3 + "\\)", sn1);
                        	}
                        }
                        s4 = exp;
                        exp = exp.replaceAll(sn1 + "\\.\\(" + sn2 + "\\+" + sn3 + "\\)", "\\(" + sn1 + "\\." + sn2 + "\\)\\+\\(" + sn1 + "\\." + sn3 + "\\)");
                        if (!s4.equals(exp)) {
                        	if (sn1.equals(sn2) || sn1.equals(sn3)) {
                        		System.out.println("A.(A+B)");
                        		exp = exp.replaceAll( "\\(" + sn1 + "\\." + sn2 + "\\)\\+\\(" + sn1 + "\\." + sn3 + "\\)", sn1);
                        	}
                        }
                        
                       
                    }
                }
            }
            
            System.out.println("s = " + s +" exp = " + exp);
            
            if (!s.equals(exp)) {
            	s = exp;
            	//mMap.put(s, "1");
            	return true;
            } else {
            	return false;
            }
            
        }
        public boolean sortit3() {
        	
        	SORT();
        	//return false;
        	//System.out.println("Start sort1");
        	if (mMap.containsKey(s.toString())) {
        		return false;
        	}
        	mMap.put(s, "1");
        	String exp = s;
            int sz = exp.length();         
            
            sz = v1.size();
            for (Object o : v1) {
            //	System.out.println(o);
            }
            
            System.out.println("exp = " + exp);
            for (int i = 0; i < sz; i++) {
                for (int j = 0; j < sz; j++) {
                    for (int k = 0; k < sz; k++) {
                    	
                        String sn1 = "", sn2 = "", sn3 = "", s4 = exp;
                        sn1 = v1.get(i).toString();
                        sn2 = v1.get(j).toString();
                        sn3 = v1.get(k).toString();
                        
                        
                        sn1 = add_haha(sn1);
                        sn2 = add_haha(sn2);
                        sn3 = add_haha(sn3);
                        //System.out.println("sn1 = "+ sn1 + " sn2 = " + sn2 + " sn3 = " + sn3 );
                       // exp = exp.replaceAll(sn1 + "\\+\\(" + sn2 + "\\." + sn3 + "\\)", "\\(" + sn1 + "\\+" + sn2 + "\\)\\.\\(" + sn1 + "\\+" + sn3 + "\\)");
                        //exp = exp.replaceAll(sn1 + "\\.\\(" + sn2 + "\\+" + sn3 + "\\)", "\\(" + sn1 + "\\." + sn2 + "\\)\\+\\(" + sn1 + "\\." + sn3 + "\\)");
                        s4 = exp;
                        exp = exp.replaceAll("\\(" + sn1 + "\\+" + sn2 + "\\)\\." + sn3 , "\\(" + sn1 + "\\." + sn3 + "\\)\\+\\(" + sn2 + "\\." + sn3 + "\\)");
                        if(!s4.equals(exp)) {
                        	if (sn1.equals(sn3) || sn2.equals(sn3) ) {
                        		System.out.println("(A+B).A = A");
                        		exp = exp.replaceAll( "\\(" + sn1 + "\\." + sn3 + "\\)\\+\\(" + sn2 + "\\." + sn3 + "\\)" , sn3);
                        	}
                        }
                        s4 = exp;
                        exp = exp.replaceAll("\\(" + sn1 + "\\." + sn2 + "\\)\\+" + sn3 , "\\(" + sn1 + "\\+" + sn2 + "\\)\\.\\(" + sn1 + "\\+" + sn3 + "\\)");
                        if(!s4.equals(exp)) {
                        	if (sn1.equals(sn3) || sn2.equals(sn3) ) {
                        		System.out.println("(A.B)+A = A");
                        		exp = exp.replaceAll( "\\(" + sn1 + "\\+" + sn2 + "\\)\\.\\(" + sn1 + "\\+" + sn3 + "\\)" , sn3);
                        	}
                        }
                        //System.out.println(exp);
                    }
                }
            }
            
         // System.out.println("s = " + s +" exp = " + exp);
            
            if (!s.equals(exp)) {
            	s = exp;
            	mMap.put(s, "1");
            	return true;
            } else {
            	return false;
            }
            
        }

        public boolean aonea() {
        	SORT();
        	String exp = s;
            int sz1 = exp.length();
            Vector v2 = new Vector();
            int sz7 = exp.length();
            Vector vn = new Vector();
            for (int i = 0; i < sz7; i++) {
                if (alpha(exp.charAt(i))) {
                    vn.add(exp.charAt(i));
                }
            }
           
            int l1 = vn.size();
            for (int i = 0; i < l1; i++) {
                String sn = "";
                sn += (vn.get(i).toString());
                exp = exp.replaceAll("\\(" + sn + "\\)" + "\\.1", sn);
                exp = exp.replaceAll(sn + "\\.1", sn);
                exp = exp.replaceAll("\\(" + sn + "\\)" + "\\+1", "1");
                exp = exp.replaceAll(sn + "\\+1", "1");
            }

            sz7 = exp.length();
            for (int i = 0; i < sz7; i++) {
                if (exp.charAt(i) == '(') {
                    v2.add(i);
                }
            }
            int k4 = v2.size();
            Vector v5 = new Vector();
            for (int i = 0; i < k4; i++) {
                int b = 1;
                String s3 = "";
                char ex = 92;
                for (int j = Integer.parseInt(v2.get(i).toString()); ; j++) {
                        if (exp.charAt(j) == ')') {
                            b--;
                            s3 += ex;
                            s3 += exp.charAt(j);
                        } else if (exp.charAt(j) == '(') {
                            b++;
                            s3 += ex;
                            s3 += exp.charAt(j);
                        } else if (exp.charAt(j) == '+' || exp.charAt(j) == '-') {
                            s3 += ex;
                            s3 += exp.charAt(j);
                        } else {
                            s3 += exp.charAt(j);
                        } 
                        if (b == 1) {
                            break;
                        }
               }
               
                v5.add(s3);
            }
            
            int kn = v5.size();
            for (int i = 0; i < kn; i++) {
                String sm = v5.get(i).toString();
                exp = exp.replaceAll(sm + "\\.1", sm);
                exp = exp.replaceAll(sm + "\\+1", "1");
            }
            
            
            int sz2 = exp.length();
            if (sz1 == sz2) {
                return false;
            } else {
                System.out.println(exp);
                s = exp;
                return true;
            }
        }
        
        public String add_haha(String s)
        {
        	String s1 = "";
        	char c = '\\';
        	for (int i = 0; i < s.length(); i++) {
        		if (s.charAt(i) == '(' || s.charAt(i) == ')' || s.charAt(i) == '+' || s.charAt(i) == '.' || s.charAt(i) == '\'') {
        			s1 = s1 +c+s.charAt(i);
        		} else {
        			s1 = s1 + s.charAt(i);
        		}
        	}
        	return s1;
        }
        
        //(A.B.C) == 
        public boolean dotdot() {
        	SORT();
        	
        	String exp = s;
          //  System.out.println("I'm DotDot");
            Vector v5 = new Vector();
           v5 = v1;
            int sz1 = exp.length();
            for (int i = 0; i < sz1; i++) {
                if (alpha(exp.charAt(i))) {
                    v5.add(exp.charAt(i));
                }
            }
            int n = v5.size();
            boolean flag = false;
            for (int i = 0; i < n; i++) {
                for (int j = i+1; j < n; j++) {
                    for (int k = j+1; k < n; k++) {
                        String sn1 = "";
                        String sn2 = "";
                        String sn3 = "";
                        sn1 += v5.get(i).toString();
                        sn2 += v5.get(j).toString();
                        sn3 += v5.get(k).toString();
                        sn1 = add_haha(sn1);
                        sn2 = add_haha(sn2);
                        sn3 = add_haha(sn3);
                        Pattern pattern = Pattern.compile(sn1 + "\\.\\(" + sn2 + "\\." + sn3 + "\\)");
                        Matcher m = pattern.matcher(exp);
                        if (m.find()) {
                            int k1 = m.start();
                            int k2 = m.end();
                            HashSet h = new HashSet();
                            for (int mn = k1; mn < k2; mn++) {
                                if (alpha(exp.charAt(mn))) {
                                    h.add(exp.charAt(mn));
                                }
                            }
                            if (h.size() == 1) {
                                Iterator it = h.iterator();
                                String sn11 = "";
                                sn11 += it.next();
                                exp = exp.replaceAll(sn1 + "\\.\\(" + sn2 + "\\." + sn3 + "\\)", sn11);
                            } else if (h.size() == 2) {
                                Iterator it = h.iterator();
                                String sn11 = "";
                                sn11 += it.next();
                                String sn12 = "";
                                sn12 += it.next();
                                char c1 = sn11.charAt(0);
                                char c2 = sn12.charAt(0);
                                if (c1 > c2) {
                                    sn11 = String.valueOf(c2);
                                    sn12 = String.valueOf(c1);
                                }
                                exp = exp.replaceAll(sn1 + "\\.\\(" + sn2 + "\\." + sn3 + "\\)", sn11 + "." + sn12);
                            } else if (h.size()==3) {
                                Iterator it = h.iterator();
                                String sn11 = "";
                                String sn12 = "";
                                String sn13 = "";
                                sn13 += it.next();

                                sn12 += it.next();

                                sn11 += it.next();
                                String sp = sortit(sn11 + sn12 + sn13);
                                String s4 = exp;
                                System.out.println("s4 " + s4);
                                exp = exp.replaceAll(sn1 + "\\.\\(" + sn2 + "\\." + sn3 + "\\)", ""+sp.charAt(0) + ".(" + "" + sp.charAt(1) + "." + sp.charAt(2) + ")");
                                
                                if (! exp.equals(s4.toString())) {
                                    flag = true;
                                }
                            }
                        }
                    }
                }
                
                int sz2 = exp.length();
                if (sz1 == sz2) {
                    return false;
                } else {
                    System.out.println(exp);
                    s = exp;
                    return true;
                }
            }
            
            int sz2 = exp.length();
            return true;
        }
        // sorts three char
        public String sortit(String s) {
        	SORT();
            String sn = "";
            char a = s.charAt(0);
            char b = s.charAt(1);
            char c = s.charAt(2);
            if (a <= b && a <= c) {
                sn += a;
                if (b <= c) {
                    sn += b;
                    sn += c;
                } else {
                    sn += c;
                    sn += b;
                }
            } else if (b <= a && b <= c) {
                sn += b;
                if (a <= c) {
                    sn += a;
                    sn += c;
                } else {
                    sn += c;
                    sn += a;
                }
            } else if (c <= a && c <= b) {
                sn += c;
                if (a <= b) {
                    sn += a;
                    sn += b;
                } else {
                    sn += b;
                    sn += a;
                }
            }
            
            return sn;
        }
        
        //(a+b+c)
        public boolean plusplus() {
        	SORT();
        //	System.out.println("I am plus plus");
        	String exp = s;
            Vector v5 = v1;
            int sz1 = exp.length();
            for (int i = 0; i < sz1; i++) {
                if (alpha(exp.charAt(i))) {
                    v5.add(exp.charAt(i));
                }
            }
                       
            int n = v5.size();
            boolean flag = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        
                        String sn1 = "";
                        String sn2 = "";
                        String sn3 = "";
                        
                        sn1 += v5.get(i).toString();
                        sn2 += v5.get(j).toString();
                        sn3 += v5.get(k).toString();
                        sn1 = add_haha(sn1);
                        sn2 = add_haha(sn2);
                        sn2 = add_haha(sn3);
                        Pattern pattern = Pattern.compile(sn1 + "\\+\\(" + sn2 + "\\+" + sn3 + "\\)");
                        Matcher m = pattern.matcher(exp);
                        if (m.find()) {
                            int k1 = m.start();
                            int k2 = m.end();
                            HashSet h = new HashSet();
                            for (int mn = k1; mn < k2; mn++) {
                                if (alpha(exp.charAt(mn))) {
                                    h.add(exp.charAt(mn));
                                }
                            }
                            
                            if (h.size() == 1) {
                                Iterator it = h.iterator();
                                String sn11 = "";
                                sn11 += it.next();
                                exp = exp.replaceAll(sn1 + "\\+\\(" + sn2 + "\\+" + sn3 + "\\)", sn11);
                            } else if (h.size() == 2) {
                                Iterator it = h.iterator();
                                String sn11 = "";
                                sn11 += it.next();
                                String sn12 = "";
                                sn12 += it.next();
                                char c1 = sn11.charAt(0);
                                char c2 = sn12.charAt(0);
                                if (c1 > c2) {
                                    sn11 = String.valueOf(c2);
                                    sn12 = String.valueOf(c1);
                                }
                                exp = exp.replaceAll(sn1 + "\\+\\(" + sn2 + "\\+" + sn3 + "\\)", sn11 + "+" + sn12);
                               
                            } else if (h.size()==3) {
                                Iterator it = h.iterator();
                                String sn11 = "";
                                String sn12 = "";
                                String sn13 = "";
                                sn13 += it.next();

                                sn12 += it.next();

                                sn11 += it.next();
                                String sp = sortit(sn11 + sn12 + sn13);
                                String s4 = exp;
                                System.out.println("s4 " + s4);
                                exp = exp.replaceAll(sn1 + "\\+\\(" + sn2 + "\\+" + sn3 + "\\)", ""+sp.charAt(0) + "+(" + "" + sp.charAt(1) + "+" + sp.charAt(2) + ")");
                                
                                if (! exp.equals(s4.toString())) {
                                    flag = true;
                                }
                            }
                        }
                    }
                }
               
                
            }
            if (flag) {
                System.out.println(exp);
                return true;
            }
          
            int sz2 = exp.length();
            if (sz1 == sz2) {
                    return false;
            } else {
            	s = exp;
                System.out.println(exp);
                return true;
           }
        }
        //((a+b) + c)
        public boolean minusminus() {
        	SORT();
        	//System.out.println("I am minusminus");
        	String exp  = s;
            Vector v5 = new Vector();
            v5 = v1;
            int sz1 = exp.length();
            for (int i = 0; i < sz1; i++) {
                if (alpha(exp.charAt(i))) {
                    v5.add(exp.charAt(i));
                }
            }
                       
            int n = v5.size();
            boolean flag = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        
                        String sn1 = "";
                        String sn2 = "";
                        String sn3 = "";
                        
                        sn1 += v5.get(i).toString();
                        sn2 += v5.get(j).toString();
                        sn3 += v5.get(k).toString();
                        sn1 = add_haha(sn1);
                        sn2 = add_haha(sn2);
                        sn2 = add_haha(sn3);
                        Pattern pattern = Pattern.compile("\\(" + sn1 + "\\+" + sn2 + "\\)\\+" + sn3 );
                        Matcher m = pattern.matcher(exp);
                        if (m.find()) {
                            int k1 = m.start();
                            int k2 = m.end();
                            HashSet h = new HashSet();
                            for (int mn = k1; mn < k2; mn++) {
                                if (alpha(exp.charAt(mn))) {
                                    h.add(exp.charAt(mn));
                                }
                            }
                            
                            if (h.size() == 1) {
                                Iterator it = h.iterator();
                                String sn11 = "";
                                sn11 += it.next();
                                exp = exp.replaceAll("\\(" + sn1 + "\\+" + sn2 + "\\)\\+" + sn3 , sn11);
                            } else if (h.size() == 2) {
                                Iterator it = h.iterator();
                                String sn11 = "";
                                sn11 += it.next();
                                String sn12 = "";
                                sn12 += it.next();
                                char c1 = sn11.charAt(0);
                                char c2 = sn12.charAt(0);
                                if (c1 > c2) {
                                    sn11 = String.valueOf(c2);
                                    sn12 = String.valueOf(c1);
                                }
                                exp = exp.replaceAll("\\(" + sn1 + "\\+" + sn2 + "\\)\\+" + sn3 , sn11 + "+" + sn12);
                               
                            } else if (h.size()==3) {
                                Iterator it = h.iterator();
                                String sn11 = "";
                                String sn12 = "";
                                String sn13 = "";
                                sn13 += it.next();

                                sn12 += it.next();

                                sn11 += it.next();
                                String sp = sortit(sn11 + sn12 + sn13);
                                String s4 = exp;
                                System.out.println("s4 " + s4);
                                exp = exp.replaceAll("\\(" + sn1 + "\\+" + sn2 + "\\)\\+" + sn3 , ""+sp.charAt(0) + "+(" + "" + sp.charAt(1) + "+" + sp.charAt(2) + ")");
                                
                                if (! exp.equals(s4.toString())) {
                                    flag = true;
                                }
                            }
                        }
                    }
                }
               
                
            }
            if (flag) {
                System.out.println(exp);
                return true;
            }
          
            int sz2 = exp.length();
            if (sz1 == sz2) {
                    return false;
            } else {
            	s = exp;
                System.out.println(exp);
                return true;
           }
            
       
        }
        
        //((a.b).c)
        public boolean dotdota() {
        	SORT();
        	
        	String exp = s;
            //System.out.println("I'm Dota");
            Vector v5 = new Vector();
            v5 = v1;
            int sz1 = exp.length();
            for (int i = 0; i < sz1; i++) {
                if (alpha(exp.charAt(i))) {
                    v5.add(exp.charAt(i));
                }
            }
            int n = v5.size();
            
            //System.out.println(n);
            boolean flag = false;
            for (int i = 0; i < n; i++) {
                for (int j = i+1; j < n; j++) {
                    for (int k = j+1; k < n; k++) {
                        String sn1 = "";
                        String sn2 = "";
                        String sn3 = "";
                        sn1 += v5.get(i).toString();
                        sn2 += v5.get(j).toString();
                        sn3 += v5.get(k).toString();
                        sn1 = add_haha(sn1);
                        sn2 = add_haha(sn2);
                        sn2 = add_haha(sn3);
                        Pattern pattern = Pattern.compile("\\(" + sn1 + "\\." + sn2 + "\\)\\." + sn3);
                        Matcher m = pattern.matcher(exp);
                        if (m.find()) {
                            int k1 = m.start();
                            int k2 = m.end();
                            HashSet h = new HashSet();
                            for (int mn = k1; mn < k2; mn++) {
                                if (alpha(exp.charAt(mn))) {
                                    h.add(exp.charAt(mn));
                                }
                            }
                            if (h.size() == 1) {
                                Iterator it = h.iterator();
                                String sn11 = "";
                                sn11 += it.next();
                                exp = exp.replaceAll("\\(" + sn1 + "\\." + sn2 + "\\)\\." + sn3, sn11);
                            } else if (h.size() == 2) {
                                Iterator it = h.iterator();
                                String sn11 = "";
                                sn11 += it.next();
                                String sn12 = "";
                                sn12 += it.next();
                                char c1 = sn11.charAt(0);
                                char c2 = sn12.charAt(0);
                                if (c1 > c2) {
                                    sn11 = String.valueOf(c2);
                                    sn12 = String.valueOf(c1);
                                }
                                exp = exp.replaceAll("\\(" + sn1 + "\\." + sn2 + "\\)\\." + sn3, sn11 + "." + sn12);
                            } else if (h.size()==3) {
                                Iterator it = h.iterator();
                                String sn11 = "";
                                String sn12 = "";
                                String sn13 = "";
                                sn13 += it.next();

                                sn12 += it.next();

                                sn11 += it.next();
                                String sp = sortit(sn11 + sn12 + sn13);
                                String s4 = exp;
                                System.out.println("s4 " + s4);
                                exp = exp.replaceAll("\\(" + sn1 + "\\." + sn2 + "\\)\\." + sn3 , ""+sp.charAt(0) + ".(" + "" + sp.charAt(1) + "." + sp.charAt(2) + ")");
                                
                                if (! exp.equals(s4.toString())) {
                                    flag = true;
                                }
                            }
                        }
                    }
                }
                
                int sz2 = exp.length();
                if (sz1 == sz2) {
                    return false;
                } else {
                	s = exp;
                    System.out.println(exp);
                    return true;
                }
            }
            
            int sz2 = exp.length();
            return true;
        }
        
        public boolean hihi()
        {
        	SORT();
        	System.out.println("BEGIN : with " + s);
        	for (int i = 0; i < s.length(); i++){ 
        		
        		// 1. (a + a) = a
    			// 2. (a.a) = a
        		if(s.charAt(i) == '+' || s.charAt(i) == '.') {
        			int k = 0, l = s.length();
        			String one = "";
        			int count = 0;
        			for (int j = i - 1; j >= 0; j --) {
        				if (s.charAt(j) == '(') {
        					count --;
        					one = s.charAt(j) + one;
        					if (count == -1) {
        						k = j;
        						break;
        					}
        				} else {
        					if (s.charAt(j) == ')') {
        						count++;
        					}
        					one = s.charAt(j) + one;
        				}
        			}
        			String two = "";
        			count = 0;
        			for (int j = i + 1; j < s.length(); j++) {
        				if (s.charAt(j) == ')') {
        					count--;
        					two += s.charAt(j);
        					if (count == -1) {
        						l = j;
        						break;
        					}
        				} else {
        					if (s.charAt(j) == '(') {
        						count++;
        					}
        					two += s.charAt(j);
        				}
        			}
        			//System.out.println("string = " +  one + " " + s.charAt(i) +  " " + two + " ");
        			v1.add(one.substring(1, one.length()));
        			v1.add(two.substring(0, two.length() - 1));
        			v1.add(one + s.charAt(i) + two);
        			
        			one = one.substring(1);
        			two = two.substring(0, two.length() -1);
        			
        			if(one.equals(two)) {
        				String s2 = s.substring(0, k);
        				String s3 = s.substring(l+1, s.length());
        				//System.out.println(s2);
        				//System.out.println(s3);
        				s = s2 + one + s3;
        				System.out.println("HAHA : " + s);
        				return true;
        			}  else if (s.charAt(i) == '.') {
        				if (one.equals(rhs)) {
        					System.out.println("A.B = A");
        					s = one;
        					return true;
        				} else if(two.equals(rhs)) {
        					System.out.println("A.B = B.A");
        					System.out.println("A.B = A");
        					s = two;
        					return true;
        				} else {
        					int x = function(one, two, rhs);
        					//System.out.println("x = " + k);
        					if (x == 1) {
        						System.out.println("A.B = A");
        						String s2 = s.substring(0, k);
        						
                				String s3 = s.substring(l+1, s.length());
                				//System.out.println(s2 + " - > " + s3 );
                				s = s2 + one + s3;
                				System.out.println("HAHA " + s);
        					} else {
        						System.out.println("A.B = B.A");
        						System.out.println("A.B = A");
        						String s2 = s.substring(0, k);
        						
                				String s3 = s.substring(l+1, s.length());
                				s = s2 + two + s3;
                				System.out.println("HAHA " + s);
        						
        					}
        					return true;
        				}
        			}
          		}
        		
        		
        	}
        	
        	
        	System.out.println("");
			return false;
        }
        public int function(String one, String two , String rhs)
        {
        	int x = 0;
        	Vector vv = new Vector();
        	Map m1 = new HashMap();
        	Map m2 = new HashMap();
        	Map m3 = new HashMap();
            for (int i = 0; i < one.length(); i++) {
                if (alpha(one.charAt(i))) {
                	String ab = "";
                	if(m1.containsKey(one.charAt(i))) {
                		ab = m1.get(one.charAt(i)).toString();
                	}
                    m1.put(one.charAt(i) + "", ab+"1");
                }
            }
            
            for (int i = 0; i < two.length(); i++) {
                if (alpha(two.charAt(i))) {
                	String ab = "";
                	if(m2.containsKey(two.charAt(i))) {
                		ab = m2.get(two.charAt(i)).toString();
                	}
                    m2.put(two.charAt(i) + "", ab+"1");
                }
            }
            
            for (int i = 0; i < rhs.length(); i++) {
                if (alpha(rhs.charAt(i))) {
                	String ab = "";
                	if(m3.containsKey(rhs.charAt(i))) {
                		ab = m3.get(rhs.charAt(i)).toString();
                	}
                    m3.put(rhs.charAt(i) + "", ab+"1");
                    vv.add(rhs.charAt(i));
                }
            }
            int c1 = 0, c2 = 0;
            for (Object o : vv) {
            	if (m1.containsKey(o.toString()) && m2.containsKey(o.toString())) {
            		if (m1.get(o.toString()).toString().length() > m2.get(o.toString()).toString().length()) {
            			c1++;
            		} else {
            			c2++;
            		}
            	} else if ((m1.containsKey(o.toString()) == false) && m2.containsKey(o.toString())) {
            		c2++;
            		c1 --;
            	} else {
            		c1++;
            	}
            }
            
            if(c1 > c2) {
            	return 1;
            } else {
            	return 2;
            }
            
        }
        public void hoho(){
        	String ss = rhs;
        	rhs = s;
        	s = ss;
        }
        public boolean SORT()
        {
        	//System.out.println("SS = " + s);
        	boolean flag = false;
        	String s1 = "";
        	int  i = 0;
        	
        	for (i = 0; i < s.length()-2; i++) {
        		if (s.charAt(i+1) == '+' || s.charAt(i+1) == '.') {
        			if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
        				if (s.charAt(i+2) >= 'a' && s.charAt(i+2) <= 'z') {
        					if (s.charAt(i) > s.charAt(i+2)) {
        						s1 += s.charAt(i+2);
        						s1 += s.charAt(i+1);
        						s1 += s.charAt(i);
        						i += 2;
        						flag = true;
        					} else {
            					s1 += s.charAt(i);
            					s1 += s.charAt(i+1);
            					s1 += s.charAt(i+2);
            					i += 2;
            				}
        				} else {
        					s1 += s.charAt(i);
        					s1 += s.charAt(i+1);
        					s1 += s.charAt(i+2);
        					i += 2;
        				}
        			} else {
        				s1 += s.charAt(i);
        			}
        		} else {
        			s1 += s.charAt(i);
        		}
        	}
        	
        	//System.out.println(" i = "  + i);
        	if (i < s.length()) {
        		for (int j = i; j < s.length(); j++) {
        			s1 += s.charAt(j);
        		}
        	}
        	s = s1;
        	
        	//System.out.println("SAS is: " + s);
        	if (flag == true) {
        		return true;
        	} else {
        		return false;
        	}
        	
        }
        public boolean alpha(char c) {
            if (c <= 'z' && c >= 'a') {
                return true;
            }
            
            return false;
        }
        
 }
}