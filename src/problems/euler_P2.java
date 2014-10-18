/**
 * Sum of the even fibbonaci numbers below n
 * **/
package problems;

import java.util.ArrayList;

public class euler_P2 {
	ArrayList<Integer> fibList = new ArrayList<Integer>();
	euler_P2()
	{
		
	}
	
	private ArrayList<Integer> fib(int n, ArrayList<Integer> fibList)
	{
		if(n == 1 || n==0)
		{
			fibList.add(1);
			return fibList;
		}
		else
		{
			fibList.add(fib(n-1, fibList).get(n-1) + fib(n-2, fibList).get(n-3));
			return fibList;
		}
	}
	
	public void excute(int number)
	{
		System.out.println(fib(number, new ArrayList<Integer>()).get(number));
	}
}
