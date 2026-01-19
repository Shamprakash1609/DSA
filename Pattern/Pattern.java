package Pattern;
class Pattern {

    static void pattern1(int N)
    {  
        System.out.println("Pattern 1");
        
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                System.out.print("* ");
            }
            System.out.println();
        }

        System.out.println();
    }

    static void pattern2(int N){

        System.out.println("Pattern 2");

        for (int i = 0 ; i< N ; i++){
            for (int j = 0 ; j<= i ; j++){
                System.out.print("* ");
            }
            System.out.println();
        }

        System.out.println();
    }

    static void pattern3(int N){

        System.out.println("Pattern 3");

        for (int i = 0 ; i< N ; i++){
            for (int j = 0 ; j<= i ; j++){
                System.out.print(j + 1);
            }
            System.out.println();
        }

        System.out.println();
    }

    static void pattern4(int N){

        System.out.println("Pattern 4");

        for(int i = 0 ;i< N ; i++){
            for(int j = 0 ; j <= i ; j++){
                System.out.print(i+1);
            }
            System.out.println();
        }
        System.out.println();
    }

    static void pattern5(int N){

        System.out.println("Pattern 5");

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j <= N-i-1 ; j++){
                System.out.print("* ");
            }
            System.out.println();
        }

        System.out.println();
    }

    static void pattern6(int N){

        System.out.println("Pattern 6");

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j <= N-i-1 ; j++){
                System.out.print(j+1);
            }
            System.out.println();
        }

        System.out.println();
    }
    
    static void pattern7(int N){

        System.out.println("Pattern 7");

        for(int i= 0 ; i< N ; i++){
            for(int j = 0 ; j < N-i-1 ; j++){
                System.out.print(" ");
            }
            for(int j=0;j< 2*i+1;j++){
                System.out.print("*");
            }
            for(int j = 0 ; j < N-i-1 ; j++){
                System.out.print(" ");
            }
            System.out.println();
            
        }
        System.out.println();
    }

    static void pattern8(int N){
         
        System.out.println("Pattern 8");

        for(int i= 0 ; i< N ; i++){
            for(int j = 0 ; j < i; j++){
                System.out.print(" ");
            }
            for(int j=0;j< 2*N-(2*i+1);j++){
                System.out.print("*");
            }
            for(int j = 0 ; j < i; j++){
                System.out.print(" ");
            }
            System.out.println();
            
        }
         System.out.println();
    }

    static void pattern9(int N){

        System.out.println("Pattern 9");

        for(int i= 0 ; i< N ; i++){
            for(int j = 0 ; j < N-i-1 ; j++){
                System.out.print(" ");
            }
            for(int j=0;j< 2*i+1;j++){
                System.out.print("*");
            }
            for(int j = 0 ; j < N-i-1 ; j++){
                System.out.print(" ");
            }
            System.out.println();
        }
        for(int i= 0 ; i< N ; i++){
            for(int j = 0 ; j < i; j++){
                System.out.print(" ");
            }
            for(int j=0;j< 2*N-(2*i+1);j++){
                System.out.print("*");
            }
            for(int j = 0 ; j < i; j++){
                System.out.print(" ");
            }
            System.out.println();
            
        }

        System.out.println();

    }

    static void pattern10(int N){

        System.out.println("Pattern 10");

        for (int i = 0; i < 2 * N - 1; i++){
            int stars = i+1 ;
            if(i >= N) stars = 2*N-1 - i;
            for(int j = 0 ; j < stars ; j++){
                System.out.print("*");
            }
            System.out.println();
        }

        System.out.println("\nAnother way with index start from 1\n");
        

        for(int i=1;i<=2*N-1;i++){
        
            int stars = i;
            
            if(i>N) stars = 2*N-i;
            
            for(int j=1;j<=stars;j++){
                System.out.print("*");
            }
            
            System.out.println();
        }

        System.out.println();
    }

    static void pattern11(int N){
        System.out.println("Pattern 11");
        int start = 1;
        for(int i=0 ; i<N ; i++){
            if(i % 2 == 0) start = 1;
            else start = 0;
            for(int j = 0 ; j <= i ; j++){
                System.out.print(start);
                start = 1 - start;
            }
            System.out.println();
        }

        System.out.println();
        
    }

    static void pattern12(int N){
        System.out.println("Pattern 12");

        int space = 2*(N-1);

        // numbers
        for(int i = 0 ; i<N ; i++){
            // numbers 

            for(int j=0 ; j<=i ; j++){
                System.out.print(j+1);
            }
            
            // spaces

            for(int j = 0 ; j < space ; j++){
                System.out.print(" ");
            }

            // numbers

            for(int j = i ; j>=0 ; j--){
                System.out.print(j+1);
            }

            System.out.println();

            space -= 2;

            
        }

        System.out.println();

        for(int i = 1 ; i <= N ; i++){
            for(int j = 0 ; j<i ; j++){
                System.out.print(j+1);
            }
            for(int j = 0 ; j< 2*N-1 ; j++){
                System.out.print(" ");
            }
            for(int j = i ; j>0 ; j--){
                System.out.print(j);
            }
            System.out.println();
        }

        
    }

    static void pattern13(int N){
        System.out.println("Pattern 13");
        int num = 1;
        for(int i = 0 ; i < N ; i++){
            for (int j = 0 ; j <= i ; j++){
                System.out.print(num + " ");
                num += 1;
            }
            System.out.println();
        }

        System.out.println();
    }

    static void pattern14(int N){
        System.out.println("Pattern 14");

        for(int i = 0 ; i < N ; i++){
            for(char j = 'A' ; j <= 'A'+i ; j++){
                System.out.print(j + " ");
            }

            System.out.println();
        }

        System.out.println();

        for(int i = 0 ; i<N ; i++){
            for(int j = 0 ; j<= i ; j++){
                char ch = (char)('A' + j);
                System.out.print(ch + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    static void pattern15(int N){
        System.out.println("Pattern 15");
        for(int i = 0 ; i < N ; i++){
            for(char j = 'A' ; j <=  'A' +(N - i - 1) ; j++){
                System.out.print(j + " ");
            }

            System.out.println();
        }
        System.out.println();
    }

    static void pattern16(int N){
        System.out.println("Pattern 16");
        for(int i = 0 ; i< N ; i++){
            for(char j = 'A' ; j <= 'A' + i ; j++){
                System.out.print((char)('A' + i));
            }
            System.out.println();
        }

         System.out.println("\nAnother way without using j as 'A' \n");

         for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j <= i ; j++){
                System.out.print((char) ('A' + i));
            }
            System.out.println();
         }

         System.out.println();
    }

    static void pattern17(int N){
        System.out.println("Pattern 17");
        for(int i = 0 ; i < N ; i++){

            for(int j = 0 ; j <= N-i-1 ; j++){
                System.out.print("*");
            }

            char ch = 'A';
            int breakpoint = (2 * i + 1) / 2;
            for (int j = 0; j < 2 * i + 1; j++) { 

                System.out.print(ch);

                if (j < breakpoint) ch++; 
                else ch--; 
            }
            
            for(int j = 0 ; j <= N-i-1 ; j++){
                System.out.print("*");
            }

            System.out.println();
        }

        System.out.println();
    }

    static void pattern18(int N){
        System.out.println("Pattern 18");
        for(int i = 0 ; i< N ; i++){
            for(char j = (char)('E' - i ); j<= 'E' ; j++ ){
                System.out.print(j);
            }
            System.out.println();
        }

        System.out.println("Another Way");
        for(int i = 0 ; i<N ; i++){
            char ch = (char) ('A' + (N-1));
            for(int j = 0 ; j <= i ; j++){
                System.out.print(ch + " ");
                ch--;
            }
            System.out.println();
        }

        System.out.println();
    }

    static void pattern19(int N){
        System.out.println("Pattern 19");
        for(int i = 0 ; i< N ; i++){
            for(int j = 0 ; j < N-i ; j++){
                System.out.print("*");
            }
            
            for(int j = 0 ; j < 2*i ; j++){
                System.out.print(" ");
            }

            for(int j = 0 ; j < N-i ; j++){
                System.out.print("*");
            }

            System.out.println();
        }

        for( int i = 0 ; i<N ; i++){
            for(int j = 0 ; j <= i; j++){
                System.out.print("*");
            }

            for(int j = 0 ; j < 2*(N-i-1) ; j++){
                System.out.print(" ");
            }

            for(int j = 0 ; j <= i; j++){
                System.out.print("*");
            }

            System.out.println();
        }

        System.out.println("\nAnother Way with variable invloved \n");
        int iniS = 0;
        for(int i = 0 ; i< N ; i++ ){
            for(int j = 0 ; j< N-i ; j++ ){
                System.out.print("*");
            }

            for(int j = 0 ; j< iniS ; j++){
                System.out.print(" ");
            }

            for(int j = 0 ; j< N-i ; j++ ){
                System.out.print("*");
            }
            System.out.println();

            iniS += 2;
        }

        // iniS = 8;
        iniS = 2*N-2;
        for(int i = 0 ; i< N ; i++ ){
            for(int j = 0 ; j<= i ; j++ ){
                System.out.print("*");
            }

            for(int j = 0 ; j< iniS ; j++){
                System.out.print(" ");
            }

            for(int j = 0 ; j<= i ; j++ ){
                System.out.print("*");
            }
            System.out.println();

            iniS -= 2;
        }


        System.out.println();
    }
      
    static void pattern20(int N){
        System.out.println("Pattern 20");
        int spaces = 2*N-2;
        for(int i = 1 ; i<= 2*N-1 ; i++){
            int stars = i;
            if(i > N) stars = 2*N - i;

            for( int j = 1 ; j <= stars ; j++){
                System.out.print("*");
            }

            for(int j = 1 ; j <= spaces ; j++){
                System.out.print(" ");
            }

            for(int j = 1 ; j <= stars ; j++){
                System.out.print("*");
            }
            System.out.println();

            if(i < N) spaces -= 2;
            else spaces += 2; 
        }

        System.out.println();
    }

    static void pattern21(int N){
        System.out.println("Pattern 21");
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                if(i == 0 || j == 0 || i == N-1 || j == N -1){
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }

            System.out.println();
        }

        System.out.println();
    }

    static void pattern22(int N){

        System.out.println("\nPattern 22\n");

        for(int i=0 ; i< 2*N-1 ; i++){
            for(int j = 0 ; j< 2*N-1 ; j++){
                int top = i;
                int left = j;
                int right = (2 * N - 2) - j;
                int bottom = (2 * N - 2) - i;

                System.out.print(N -  Math.min(Math.min(top,bottom), Math.min(left,right)) + " ");
               
            }
            System.out.println();
        }

        System.out.println("\n");
    }

    static void pattern23(String S){
        /*
            i = 0 → left = 0
            i = 1 → left = 1
            i = 2 → left = 2
            i = 3 → left = 3  ← center
            i = 4 → left = 2
            i = 5 → left = 1
            i = 6 → left = 0
        */
        System.out.println("Pattern 23");
        int n = S.length();

        int left ;
        int right ;

        for(int i = 0; i<n ;i++){
            
            if(i < n/2){
                left = i;
                right = n-1-i;
            }else{
                left = n-1-i;
                right = i;
            }

            for(int j = 0; j < left; j++){
                System.out.print(" ");
            }

            System.out.print(S.charAt(left));

            for(int j = 0 ; j < right - left ; j++){
                System.out.print(" ");
            }

            if(left != n/2){
                System.out.print(S.charAt(right));
            }

            System.out.println();

            left++;
            right--;
        }

        System.out.println("\n");
    }
    
    static void pattern24(int N){
        System.out.println("Pattern 24");

        for(int i = 0 ; i < N; i++){
            int temp = i+1;
            for(int j = 0 ; j <= i ; j++){
                System.out.print(temp + " ");

                temp += N - j - 1;
            }

            System.out.println();
        }
    }

    static void pattern25(String S){
    int n = S.length();

    System.out.println("\nPattern 25");

    int left = 0;
    int right = n - 1;
    boolean flag = false;

    for(int i = 0 ; i < n ;i++){

        for(int j = 0; j < left; j++)
        {
            System.out.print(" ");
        }

        System.out.print(S.charAt(left));

        for(int j = 0; j < right - left - 1; j++)
        {
            System.out.print(" ");
        }

        if(left != right){
            System.out.print(S.charAt(right));
        }

        System.out.println();

        if(left == right)
        {
            flag = true;
        }

        if(!flag)
        {
            left++;
            right--;
        }
        else
        {
            left--;
            right++;
        }
    }
}

    // Pattern Printing Through Recursion
    static void recurTriangle_1(int R , int C){
        if(R == 0){
            return;
        }

        if(C < R){
            System.out.print("*");
            recurTriangle_1(R , C + 1);
        }
        else{
            System.out.println();
            recurTriangle_1(R - 1 , 0);
        }
    }

    static void recurTriangle_2(int R , int C){
        if(R == 0){
            return;
        }
        if(C < R){
            recurTriangle_2(R , C + 1);
            System.out.print("*");
        }
        else{
            recurTriangle_2(R - 1 , 0);
            System.out.println();
        }
    }




 public static void main(String[] args) {
        
         int N = 5;
         pattern1(N);
         pattern2(N);
         pattern3(N);
         pattern4(N);
         pattern5(N);
         pattern6(N);
         pattern7(N);
         pattern8(N);
         pattern9(N);
         pattern10(N);
         pattern11(N);
         pattern12(N);
         pattern13(N);
         pattern14(N);
         pattern15(N);
         pattern16(N);
         pattern17(N);
         pattern18(N);
         pattern19(N);
         pattern20(N);
         pattern21(N);
         pattern22(N);
        //  pattern23("IRONMAN");
         pattern23("12345");
         pattern24(N);
         pattern25("12345");

        // recurTriangle_1(4, 0);
        // recurTriangle_2(4,0);

         
    }

 }