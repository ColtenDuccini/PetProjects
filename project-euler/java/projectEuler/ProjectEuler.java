package projectEuler;

import java.util.*;
import java.lang.*;
import java.io.*;

public class ProjectEuler {

    /* the solutions to the problems */
    static int problem1(){
	int max = 1000;
	int sum = 0;
	for (int i = 1; i < max; i++){
	    if (i % 3 == 0 || i % 5 == 0){
		sum += i;
	    }
	}
	return sum;
    }
    
    static int problem2(){
	int max = 4000000;
	int sum = 0;
	int a = 0;
	int b = 1;
	while (b < max){
	    int c = b;
	    b = a + b;
	    a = c;
	    if (b % 2 == 0){
		sum += b;
	    }
	}
	return sum;
    }

    static ArrayList<Integer> problem3(){
	long num = 600_851_475_143L;
	return HelperFunctions.prime_factors(num);
    }
    
    static int problem4(){
	int max = 1001;
	for (int i = 100; i < 1000; i++){
	    for (int j = 100; j < 1000; j++){
		int product = i * j;
		if (product > max && HelperFunctions.is_palindrome(Integer.toString(product))) 
		    {
			max = product;
		    }
	    }
	}
	return max;
    }

    static long problem5(){
	long n = 2520;
	int[] divs = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
	while (true){
		if (HelperFunctions.divisible_by_list(n, divs)){
			break;
		    }
		n += 2520;
	    }
	return n;
    }

    static long problem6(){
	long i = 0, j = 0;
	for (int n = 1; n <= 100; n++){
	    i += n;
	    j += (n * n);
	}
	return (i * i) - j;
    }

    static long problem7(){
	return HelperFunctions.bad_primes_lister(10001).get(10000);
    }

    static long problem8(){
	String number = "7316717653133062491922511967442657474235534919493496983520312774506326239578318016984801869478851843858615607891129494954595017379583319528532088055111254069874715852386305071569329096329522744304355766896648950445244523161731856403098711121722383113622298934233803081353362766142828064444866452387493035890729629049156044077239071381051585930796086670172427121883998797908792274921901699720888093776657273330010533678812202354218097512545405947522435258490771167055601360483958644670632441572215539753697817977846174064955149290862569321978468622482839722413756570560574902614079729686524145351004748216637048440319989000889524345065854122758866688116427171479924442928230863465674813919123162824586178664583591245665294765456828489128831426076900422421902267105562632111110937054421750694165896040807198403850962455444362981230987879927244284909188845801561660979191338754992005240636899125607176060588611646710940507754100225698315520005593572972571636269561882670428252483600823257530420752963450";
	int len = number.length();
	long maximum = 0;
	for (int i = 0; i < 1000-13; i++){
	    String s = number.substring(i, i+13);
	    long product = 1;
	    for (int j = 0; j < 13; j++){
		char d = s.charAt(j);
		int digit = Integer.parseInt(Character.toString(d));
		product *= digit;
	    }
	    if (product > maximum){
		maximum = product;
	    }
	}	    
	return maximum;
    }

    static long problem9(){
	for (int a = 1; a < 1000; a++){
	    for (int b = 1; b < 1000 - a; b++){
		int c = 1000 - a - b;
		if (c * c == ((a * a) + (b * b))){
		    return a * b * c;
		}
	    }
	}
	return 0;
    }

    static long problem10(){
	long sum = 0;
	for (Integer i: HelperFunctions.sieve_of_eratosthenes(2000000)){
	    sum += i;
	}
	return sum;
    }

    static long problem11(){
	return 0;
    }

    static long problem12(){
	return 0;
    }

    public static void main(String []args){
	System.out.println(HelperFunctions.sieve_of_eratosthenes(1000)); 
	System.out.println(problem10());
    }
}
