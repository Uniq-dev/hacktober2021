import java.util.*;

class AbsoluteValue
{
	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter any number:");
		int n=sc.nextInt();
		if(n<0)
			System.out.println(n-(2*n));
		else
			System.out.println(n);
	}
}