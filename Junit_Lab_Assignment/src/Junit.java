
public class Junit {
	
	public static int factorial(int n) {
		if(n==0) {
			return 1;
		}
		
		else 
			return(n*factorial(n-1));
	}
	
	public static int sumOdds(int n) {
		int sum=0;
		for(int i=1;i<n;i++) {
			if(i%2!=0) {
				sum=sum+i;
			}
			
		}
		
		return sum;
		
	}
	
	public static String concateText(String x,String y) {
		
		String s= x+y;
		s=s.concat("testingisgood");
		return s;
	}

	public static void main(String[] args) {
		
		System.out.println(concateText("berk","serhat"));

	}

}
