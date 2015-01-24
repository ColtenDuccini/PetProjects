package projectEuler;

import java.util.*;

public class HelperFunctions{
     public static ArrayList<Integer> prime_factors(long n){
	ArrayList<Integer> factors = new ArrayList<Integer>();
	while (n > 1){
	    int d = 2;
	    while (n % d != 0){
		d += 1;
	    }
	    factors.add(d);
	    n = n / d;
	}
	return factors;
    }

    public static ArrayList<Integer> get_proper_divisors(long n){
	ArrayList<Integer> divisors = new ArrayList<Integer>();
	int d = 1;
	while (d < n / 2 + 1){
	    if (n % d == 0){
		divisors.add(d);
	    }
	    d++;
	}
	return divisors;
    }

    public static ArrayList<Integer> list_n_primes(long n){
	ArrayList<Integer> primes = new ArrayList<Integer>();
	int i = 2;
	while (primes.size() < n){
	    if (is_prime(i)){
		primes.add(i);
	    }
	}
	return primes;
    }
    
    public static boolean is_prime(long n){
	if (n == 1){
	    return false;
	}
	else if (n < 4){
	    return true;
	}
	else if (n % 2 == 0){
	    return false;
	}
	else {
	    for (int i = 3; i <= Math.floor(Math.sqrt(n) + 1); i += 2){
		if (n % i == 0){
		    return false;
		}
	    }
	}
	return true;
    }

    public static boolean is_palindrome(String s){
	/* checks if a whitespace-free string is a palindrome */
	s = s.toLowerCase();
	int len = s.length();
	int i = 0;
	    for (int j = len - 1; j >= 0; j--){
		if (s.charAt(j) != s.charAt(i)){
		    return false;
		}
		i++;
	    }
	return true;
    }

    public static boolean divisible_by_list(long n, int[] ds){
	for (int i = 0; i < ds.length; i++){
	    if (n % ds[i] != 0){
		return false;
	    }
	}
	return true;
    }

    public static ArrayList<Integer> bad_primes_lister(int n){
	/* returns a list of the first n primes */
	ArrayList<Integer> primes = new ArrayList<Integer>();
	int i = 2;
	while (primes.size() < n){
	    if (is_prime(i)){
		primes.add(i);
	    }
	    i++;
	}
	return primes;
    }
    
    public static ArrayList<Integer> sieve_of_eratosthenes(int n){
	ArrayList<Boolean> sieve = new ArrayList<Boolean>();
	ArrayList<Integer> primes = new ArrayList<Integer>();

	sieve.add(false);
	sieve.add(false);

	for (int i = 2; i < n + 1; i++){
	    sieve.add(true);
	}

	for (int i = 2; i < n + 1; i++){
	    for (int j = i + i; j < n + 1; j += i){
		try{
		    sieve.set(j, false);
		   } catch (ArrayIndexOutOfBoundsException e) {
		}
	    }
	}
	for (int i = 0; i < n + 1; i++){
	    if (sieve.get(i) == true){
		primes.add(i);
	    }
	}
	return primes;
    }
}
