public class IsprInterview {	
	private static int iNum1 = 10;
	public static int iNum2 = 20; 
	public int GetNum1(){
		return iNum1;
	}
}
class IsprTest{
public static void main(String[] args) {		
		IsprInterview oIspr = new IsprInterview();
		System.out.println(IsprInterview.iNum2);		
	}
}	
