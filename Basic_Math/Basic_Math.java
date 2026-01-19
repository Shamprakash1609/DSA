package Basic_Math;

import java.util.ArrayList;
import java.util.Collections;

public class Basic_Math {

    public static int addDigitsB(int num){
        while(num >= 10){
            int sum = 0;
            while(num > 0){
                sum += num % 10;
                num /= 10;
            }
            num = sum;
        }

        return num;
    }

    public int addDigits(int num) {
        if(num <= 9) return num;
        if((num % 9) == 0 )return 9;
        return num % 9;
    }

    static void countOfDigits(int N){
        int count = 0 ;
        int originalN = N;
        
        while (N > 0) {
            count++;
            N = N/10;
        }

        System.out.println("The number of digits in the numbers is: " + count);

        System.out.println("Another way using log based method");
        int cnt = (int) (Math.log10(originalN) + 1);

        System.out.println("The number of digits in the numbers through optimal approach is: " + cnt + "\n");


    }

    static int reverseNum(int N){

        int revN = 0;

        while (N > 0) {
            int lastDigit = N % 10;
            N = N/10;

            revN = (revN*10) + lastDigit;

        }

        return revN;
    }

    static void checkPalindrome(int N){
        int num = N;
        int revN = 0;

        while (N > 0) {
            int lastDigit = N%10 ;

            N = N/10;

            revN = (revN*10) + lastDigit;
        }

        if (num == revN) {
            System.out.println("The number is a palindrome");
        } else {
            System.out.println("The number is not a palindrome");
        }
    }

    static void checkAmstrong(int N){
        int num = N;
        int sum = 0;

        while (N > 0) {
            int lastDigit = N %10;

            int digits = (int) (Math.log10(num) + 1);
            sum += (Math.pow(lastDigit,digits));

            N /= 10;

        }

        if (sum == num) {
            System.out.println("The given number is a Amstrong number." );
        }else{
            System.out.println("The given number is not and Amstrong number.");
        }
    }

    static ArrayList<Integer> findDivisors(int N){
        ArrayList<Integer> divisors = new ArrayList<>();
        int sqrtN = (int) Math.sqrt(N);
        for (int i = 1; i <= sqrtN; ++i){
            if (N % i == 0){
                divisors.add(i);
                
                if(i != N /i ){
                    divisors.add(N / i);
                }
            }
        }

        Collections.sort(divisors);

        return divisors;
    }

    static void isPrime(int N){
        int sqrtN = (int) Math.sqrt(N);
        int cnt = 0;
        for(int i = 1 ; i <= sqrtN ;  i++){
            if(N % i == 0){
                cnt++;

                if(i != N/i){
                    cnt++;
                }
            }
        }
        if(cnt == 2){
            System.out.println("\nThe given number : " + N + " is a prime number");
        } else{
            System.out.println("\nThe given number : " + N + " is a  not a prime  number");
        }
    }

    /*
    static void isPrime(int N){
        int sqrtN = (int) Math.sqrt(N);
        int cnt = 0;
        for(int i = 1 ; i <= sqrtN ; i++){
            if(N % i == 0){
                cnt++;
                if(i != N/i) cnt++;
            }
            if(cnt > 2) { // Stop early if we find more than 2 divisors
                System.out.println(N + " is NOT a prime number");
                return;
            }
        }
        System.out.println(N + " is a prime number");
    }
    */


    static void gcd(int N1 , int N2){

        int gcd = 1;
        
        for(int i = Math.min(N1, N2); i >= 1; i--){
            if( N1 % i == 0  && N2 % i == 0){
                gcd = i;
                break;
            }
        }

        System.out.println("The GCD or HCF of the numbers " + N1 + "," +N2 + " is " + gcd );
    }

    static int gcdE(int a, int b) {
        while (a > 0 && b > 0) {
            if (a > b)
                a = a % b;
            else
                b = b % a;
        }

        if (a == 0) return b;
        return a;
    }


    // LCM - Least Common Multiple
    static int lcm(int a, int b) {
        if (a == 0 || b == 0) return 0;
        return a * b / gcdE(a, b); // safer order
    }


    // prime factor calculation
    public static boolean isPrimeNumber(int num) {
        if (num < 2) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    public static ArrayList<Integer> primeFactorOfNumberB(int N){
        // TC : O(√N * 2 * √N)
        int sqrtN = (int) Math.sqrt(N);

        ArrayList<Integer> ans = new ArrayList<>();

        for(int i = 1 ; i<= sqrtN ;i++ ){
            if( N % i == 0 ){
                if(isPrimeNumber(i)){
                    ans.add(i);
                }

                if(N/i != i){
                    if(isPrimeNumber(N/i)){
                        ans.add(N/i);
                    }
                }
            }
        }

        return ans;
    }

    public static ArrayList<Integer> primeFactorOfNumberO(int N){

        // int sqrtN = (int) Math.sqrt(N);

        ArrayList<Integer> ans = new ArrayList<>();

        for(int i = 2 ; i*i <= N ; i++){

            if(N % i == 0){
                ans.add(i);

                while(N % i == 0){
                    N = N / i;
                }
 
            }
        }

        if( N != 1) ans.add(N); 

        return  ans;
    }


    // Sieve of Eratosthenes
    public static ArrayList<Integer> findAllPrimesSeive(int N){

        ArrayList<Integer> ans = new ArrayList<>();

        int prime[] = new int[N + 1];

        for(int i = 2 ; i <= N ; i++){
            prime[i] = 1;
        }

        // for(int i = 2 ; i< N; i++){
        for(int i = 2 ; i*i <= N; i++){ 

            // for(int j = 2 * i ; j <= N ; j += i){
            for(int j = i * i ; j <= N ; j += i){

                prime[j] = 0;
            }
        }
        

        for(int i = 2 ; i<= N ; i++){
            if(prime[i] == 1){
                ans.add(i);
            } 
        }

        return ans;
    }

    public static ArrayList<Integer> getSeive(int n){
        ArrayList<Integer> prime = new ArrayList<>(n + 1);

        for(int i = 0; i <= n; i++) {
            prime.add(1);
        }

        prime.set(0, 0);
        prime.set(1, 0);

        for(int i = 2 ; i <= Math.sqrt(n) ; i++){

            if(prime.get(i) == 1){

                for(int j = i * i ; j <= n ; j += i){
                    prime.set(j , 0);
                }

            }
        }

        return prime;
    }

    public static ArrayList<Integer>SPF (int n){

        int maxN = Math.max(n, 100000); 
        int[] spf = new int[maxN + 1];

        for(int i = 1 ; i < maxN ; i++){
            spf[i] = i;
        }

        for(int i = 2 ; i< maxN ; i++){
            if(spf[i] == i){

                for(long j = (long) i*i ; j <= maxN ; j += i){
                    if(spf[(int) j] == j){ // previously not marked.
                        spf[(int) j] = i;
                    }
                }
            } 
        }

        // implementation

        ArrayList<Integer> ans = new ArrayList<>();

        while( n != 1){
            ans.add(spf[n]);
            n = n/spf[n];
        }

        return  ans;

    }


    // Power Exponentiation
    public static int powerExp(int num , int pow){
        // TC : log2N
        int m = pow;
        int ans = 1;

        while( pow > 0){
            if(pow % 2 == 1){
                ans *= num;
                pow--;
            }
            else{
                pow = pow / 2;
                num = num * num;
            }
        }

        if(m < 0) ans = 1/ans;

        return ans;
    }
    
    public static void main(String[] args) {
        // int num = 9831;
        // int num = 121;
        int num = 1634;
        System.out.println("The Input number is : " + num);
        countOfDigits(num);
        int resReverseNum = reverseNum(num);
        System.out.println("The reversed digit is : " + resReverseNum + '\n');

        checkPalindrome(num);
        checkAmstrong(num);
        ArrayList<Integer> divisors = findDivisors(36); 
        System.out.print("Divisors of " + 36  + " are: ");
        for (int divisor : divisors) {
            System.out.print(divisor + " ");
        }

        isPrime(37);

        gcd(20,15);
        // gcdE(20,15);

        int lcmAns =lcm(2 , 7);
        System.out.println("The LCM of (2, 7) is : " + lcmAns);


        ArrayList<Integer> primeFactorsN = primeFactorOfNumberO(780);
        System.out.println("The prime factors of the number is : " + primeFactorsN);

        System.out.println("The prime numbers till the number by (Sieve of Eratosthenes) algo is  :"+findAllPrimesSeive(37));
        
        System.out.println("The Shortest prime factors of the numbers are : "+SPF(30));

        int base = 2;
        int exponent = 10;
        int powerResult = powerExp(base, exponent);
        System.out.println("Power Exponentiation: " + base + "^" + exponent + " = " + powerResult);

    }
    
}
