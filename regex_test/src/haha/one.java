package haha;
import java.io.*;


public class one {
	public static StringMatcher m = new StringMatcher();
	public static void haha() {
		 String EXAMPLE_TEST = "This is my small example "
			      + "string which I'm going to " + "use for pattern matching.";
		 System.out.println(EXAMPLE_TEST.matches("\\w.*"));
		 String[] splitString = (EXAMPLE_TEST.split("\\s+"));
		 System.out.println(splitString.length);// Should be 14
		 for (String string : splitString) {
		      System.out.println(string);
		 }
		 // Replace all whitespace with tabs
		 System.out.println(EXAMPLE_TEST.replaceAll("\\s+", "\t"));
	}
	public static void hihi(){
	//	testIsTrue();
		//testIsTrueVersion2();
		//testIsTrueOrYes() ;
	    //testContainsTrue();
		//testIsThreeLetters();
	//testisNoNumberAtBeginning();
		//testisIntersection() ;
		testLessThenThreeHundret() ;
		
		
	}
	public static void main(String[] args) {
		//haha();
		//hoho();
		hihi();
	}
	
	public static void hoho()
	{
		String s = "";
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		try {
			s = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("hello");
		System.out.println(s);
		// '.' regex
		System.out.println(s.matches("."));
		// ^abc
		System.out.println(s.matches("^abc"));
		// abc$
		System.out.println(s.matches("abc$"));
	}
	public static void testIsTrue() {
	    System.out.println(m.isTrue("true"));
	    System.out.println(m.isTrue("true2"));
	    System.out.println(m.isTrue("True"));
	  }

	   
	  public static void testIsTrueVersion2() {
	    System.out.println(m.isTrueVersion2("true"));
	    System.out.println(m.isTrueVersion2("true2"));
	    System.out.println(m.isTrueVersion2("True"));;
	  }

	   
	  public static void testIsTrueOrYes() {
	    System.out.println(m.isTrueOrYes("true"));
	    System.out.println(m.isTrueOrYes("yes"));
	    System.out.println(m.isTrueOrYes("Yes"));
	    System.out.println(m.isTrueOrYes("no"));
	  }

	   
	  public static void testContainsTrue() {
	    System.out.println(m.containsTrue("thetruewithin"));
	  }

	   
	  public static void testIsThreeLetters() {
	    System.out.println(m.isThreeLetters("abc"));
	    System.out.println(m.isThreeLetters("abcd"));
	  }
	  
	   
	  public static void testisNoNumberAtBeginning() {
	    System.out.println(m.isNoNumberAtBeginning("abc"));
	    System.out.println(m.isNoNumberAtBeginning("1abcd"));
	    System.out.println(m.isNoNumberAtBeginning("a1bcd"));
	    System.out.println(m.isNoNumberAtBeginning("asdfdsf"));
	  }
	  
	   
	  public static void testisIntersection() {
	    System.out.println(m.isIntersection("1"));
	    System.out.println(m.isIntersection("abcksdfkdskfsdfdsf"));
	    System.out.println(m.isIntersection("skdskfjsmcnxmvjwque484242"));
	  }
	  

	   
	  public static void testLessThenThreeHundret() {
	    System.out.println(m.isLessThenThreeHundret("288"));
	    System.out.println(m.isLessThenThreeHundret("3288"));
	    System.out.println(m.isLessThenThreeHundret("328 8"));
	    System.out.println(m.isLessThenThreeHundret("1"));
	    System.out.println(m.isLessThenThreeHundret("99"));
	    System.out.println(m.isLessThenThreeHundret("300"));
	  }
}
