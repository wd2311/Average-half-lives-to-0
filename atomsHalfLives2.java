
package projectHalf;

import java.math.BigDecimal;

public class atomsHalfLives2 {

	double[] knowns;
	int n;
	
	public atomsHalfLives2(){
		n = 4;
		knowns = new double[1000];
	}//Constructor
	
	public static void main(String[] a){
		atomsHalfLives2 classObject = new atomsHalfLives2();
		classObject.go();
	}//main
	
	private void go(){
		System.out.println(avgHalfLivesTo0(n));
		/*
		for(int i = 1; i <= 13; i ++){
			System.out.println(i + "\t" + avgHalfLivesTo0(i));
		}
		*/
	}//go
		
	private double waoo(int n){
		double waoo = 0; //weighted average of outcomes
		for(int i = 0; i <= (n-1); i ++){
			waoo = waoo + (combinations(n, i)*(1 + avgHalfLivesTo0(i)));//weighted probability in nths
		}//for i
		return waoo;
	}//waoo
	
	private double avgHalfLivesTo0(int n){
		if((n == 0) || ((n > 0) && (knowns[n] != 0))){
			return knowns[n];
		}//if it already has done it and therefore has saved it, just use it
		
		double waoo = 0; //weighted average of outcomes
		for(int i = 0; i <= (n-1); i ++){
			waoo = waoo + (combinations(n, i)*(1 + avgHalfLivesTo0(i)));//weighted probability in nths
		}//for i
		
		double subTotal = 0;//keeps track of the partial sum for each j value
		for(int j = 1; j < (1001); j ++){
			subTotal = subTotal + ((waoo + ((Math.pow(2, n) - 1) * (j - 1))) / (Math.pow(Math.pow(2, n), j)));//what it goes up by for each j value... (2^n -1) * (j-1)
		}//for j
		
		knowns[n] = subTotal;
		
		return subTotal;
	}//avgHalfLivesTo0
		
	private int combinations(int n, int i){
		if(i <= 0){
			return 1;
		}//if it's finding how many 0s, return 1. It will return 0 otherwise, which is wrong
		int nFac = factorial(n);
		int iFac = factorial(i);
		int nmiFac = factorial(n - i);
		return nFac/(iFac*nmiFac);
	}//combinations
		
	private int factorial(int x){
		if(x >= 13){
			return (int) (Math.sqrt(2 * Math.PI * x) * Math.pow(x/Math.E, x));//this doesn't work, it maxes out at 2^31 -1
		}else{
			int subTotal = 1;
			for(int i = 1; i <= x; i ++){
				subTotal = subTotal * i;
			}//for i
			return subTotal;
		}//else
		/*
		if((x == 1) || (x == 0)){
			return 1;
		}else{
			return x * factorial(x - 1);
		}
		*/
	}//factorial
}//class