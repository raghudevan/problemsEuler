/**
 * Sum of multiples of 3 or 5 below a number n
 * **/
package problems;

public class euler_P1 {
	euler_P1()
	{
		
	}
	
	public void excute(int number)
	{
		int sum = 0;
		for(int i=1; i<number; i++)
		{
			if(i%3 == 0 || i%5 == 0)
			{
				sum = sum+i;
			}
		}
		System.out.println("Sum of all multiples below " + number + " is " + sum);
	}
}
