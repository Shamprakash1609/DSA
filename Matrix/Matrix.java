package Matrix;

import java.util.*;

public class Matrix {

    public static ArrayList<Integer> snakePattern(int matrix[][]){

        int n = matrix.length;
        int m = matrix[0].length;

        ArrayList<Integer> ans =  new ArrayList<>();

        for(int i = 0 ; i<n ;i++){

            for(int j =0 ; j<m ; j++){

                if(i % 2 == 0){

                    ans.add(matrix[i][j]);
                } else{
                    ans.add(matrix[i][n-1-j]);
                }
            }
        }

        return ans;
    }

    public static void markRow(ArrayList<ArrayList<Integer>> matrix , int i){
        int n = matrix.size();
        int m = matrix.get(0).size();

        for(int j = 0 ; j<m ; j++){
            if(matrix.get(i).get(j)!= 0){
                matrix.get(i).set(j,-1);
            }
        }
    }

    public static void markCol(ArrayList<ArrayList<Integer>> matrix , int j){
        int n = matrix.size();
        int m = matrix.get(0).size();

        for(int i = 0 ; i<n ; i++){
            if(matrix.get(i).get(j) != 0){
                matrix.get(i).set(j,-1);
            }
        }
    }

    public static ArrayList<ArrayList<Integer>> zeroMatrixBr (ArrayList<ArrayList<Integer>> matrix){
        int n = matrix.size();
        int m = matrix.get(0).size();

        for(int i = 0 ; i<n ; i++){
            for(int j = 0 ; j < m ; j++){
              if( matrix.get(i).get(j) == 0){
                markRow(matrix,i);
                markCol(matrix,j);
              }
            }
        }

        for(int i = 0 ; i<n ; i++){
            for(int j = 0 ; j<m ; j++){
                if(matrix.get(i).get(j) == -1){
                    matrix.get(i).set(j , 0);
                }
            }
        }

        return matrix;
    }

    public static ArrayList<ArrayList<Integer>> zeroMatrixB(ArrayList<ArrayList<Integer>> matrix ){
        int n = matrix.size();
        int m = matrix.get(0).size();

        int row[] = new int[n];
        int col[] = new int[m];

        for(int i = 0 ; i<n ; i++){
            for(int j = 0 ; j<m ; j++){
                if(matrix.get(i).get(j) == 0){
                    row[i] = 1;
                    col[j] = 1; 
                }
            }
        }

        for(int i = 0 ; i<n ; i++){
            for(int j=0 ; j<m ; j++){
                if(row[i] == 1 || col[j] == 1 ){
                    matrix.get(i).set(j , 0);
                }
            }
        }

        return matrix;

        // TC: O(n^2)
        
    }

    public static ArrayList<ArrayList<Integer>> zeroMatrixO(ArrayList<ArrayList<Integer>> matrix){
        // row -> [0][..]
        // col -> [..][0]
        int n = matrix.size();
        int m = matrix.get(0).size();

        int col0 = 1;

        for(int i = 0 ; i<n ; i++){
            for(int j = 0 ; j<m ; j++){
                    if(matrix.get(i).get(j) == 0){
                        // mark rows
                    matrix.get(i).set(0,0); // for each row[0-n] mark zeros in j[0](1st col)

                    if(j != 0){
                        // mark cols 
                        matrix.get(0).set(j,0); // for each col[0-n] mark zeros in i[0](1st row)
                    }else{
                        col0 = 0;
                    }
                }
            }
        }

        for(int i = 1 ; i<n ; i++){
            for(int j = 1 ; j<m ; j++){
                if(matrix.get(i).get(j) != 0){
                    if(matrix.get(i).get(0) == 0 || matrix.get(0).get(j) == 0){
                        matrix.get(i).set(j,0);
                    }
                }
            }
        }

        if(matrix.get(0).get(0) == 0){
            for(int j = 0; j<m ; j++){
                matrix.get(0).set(j,0);
            }
        }

        if(col0 == 0){
            for(int i = 0 ; i<n ; i++){
                matrix.get(i).set(0,0);
            }
        }

        return matrix;
    }

    public static int[][] zeroMatrixOp(int matrix[][]){
        int n = matrix.length;
        int m = matrix[0].length;

        int col0 = 1;

        for(int i = 0 ; i<n ; i++){
            for(int j = 0 ; j<m ; j++){
                if(matrix[i][j] == 0){
                    // mark rows
                    matrix[i][0] = 0;

                    if(j != 0){
                        // mark cols
                        matrix[0][j] = 0;
                    }
                    else{
                        col0 = 0;
                    }
                }
            }
        }

        for(int i = 1 ;i<n ; i++){
            for(int j = 1 ; j<m ; j++){
                if(matrix[i][j] != 0){
                    if(matrix[i][0] == 0 || matrix[0][j] == 0 ){
                        matrix[i][j] = 0;
                    }
                }
            }
        }

        if(matrix[0][0] == 0){
            for(int j = 0 ; j<m ; j++){
                matrix[0][j] = 0;
            }
        }

        if(col0 == 0){
            for(int i = 0 ; i<n ; i++){
                matrix[i][0] = 0;
            }
        }

        return  matrix;
    }

    public static int[][] rotateMatrixBr(int matrix[][]){
        int n = matrix.length;
        int m = matrix[0].length;

        int rotated[][]  = new int[n][n];

        for(int i = 0 ; i<n ; i++){
            for(int j = 0 ; j<m ; j++){
               rotated[j][n-i-1] = matrix[i][j];
            }
        }
        return  rotated;
    }

    public static void rotateMatrixO(int matrix[][]){
        int n = matrix.length;
        int m = matrix[0].length;

        for(int i = 0 ; i<n ; i++){
            for(int j = i ; j<m ; j++){
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for(int i = 0 ; i<n ; i++){
            for(int j = 0 ; j < n/2 ; j++){
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][n-1-j];
                matrix[i][matrix.length - 1 - j] = temp;
            }
        }
    }

    static void rotateby90Anti(int mat[][]) {
        int n = mat.length;
        int m = mat[0].length;
        
        for(int i = 0 ; i<n ; i++){
            for(int j = i ; j<m ; j++){
                int temp = 0;
                temp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }
        
        for(int j = 0 ; j<n ;j++){
            for(int i = 0 ; i<n/2 ; i++){
                int temp = 0;
                temp = mat[i][j];
                mat[i][j] = mat[n-1-i][j];
                mat[n-1-i][j] = temp;
            }
        }
        
    }

    public static ArrayList<Integer> spiralMatrix(int matrix[][]){

        // right -> bottom -> left -> top

        ArrayList<Integer> ans = new ArrayList<>();

        int n = matrix.length;
        int m = matrix[0].length;

        int top = 0 , left = 0 ,bottom = n-1, right = m-1 ;

        while(top <= bottom && left <= right){

            for(int i = left ; i<= right ; i++){
                ans.add(matrix[top][i]);
            }
            top++;

            for(int i = top ; i<= bottom ; i++){
                ans.add(matrix[i][right]);
            }
            right--;

            if(top <= bottom){
                for(int i = right ; i >= left ; i--){
                    ans.add(matrix[bottom][i]);
                }
                bottom--;
            }
            
            if(left <= right){
                for(int i = bottom; i >= top ; i--){
                    ans.add(matrix[i][left]);
    
                }
                left++;
            }
        }

        return ans;

    }

    public static  int[][] generateSpiralMatrix(int n) {
        int ans[][] = new int[n][n];

        int counter = 1;

        int top = 0 , left = 0 , right = n - 1, bottom = n-1;

        while(top <= bottom && left <= right){

            for(int i = left ; i <= right ; i++){
                ans[top][i] = counter;
                counter++;
            }
            top++;

            for(int i = top ; i <= bottom ; i++){
                ans[i][right] = counter;
                counter++;
            }
            right--;

            if(top <= bottom){
                for(int i = right ; i >= left ; i--){
                    ans[bottom][i] = counter;
                    counter++;
                }
                bottom--;
            }

            if(left <= right){
                for(int i = bottom ; i >= top ; i--){
                    ans[i][left] = counter;
                    counter++;
                }
                left++;
            }
        }

        return ans;
    }

    // Spiral Matrix III
    public static int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        int directions[][] = {{0 , 1} , {1 , 0} , {0 , -1} , {-1 , 0}};

        List<int[]> ans = new ArrayList<>();
        ans.add(new int[]{rStart , cStart});

        int r = rStart , c = cStart;
        int steps = 1;
        int idx = 0;

        while(ans.size() < (rows * cols)){
            for(int i = 0 ; i < 2 ; i++){
                int dr = directions[idx][0];
                int dc = directions[idx][1];

                for(int j = 0 ; j < steps ; j++){
                    r += dr;
                    c += dc;

                    if(r >= 0 && r < rows && c >= 0 && c < cols){
                        ans.add(new int[]{r , c});
                    }
                }

                idx = (idx + 1) % 4;
            }

            steps++;
        }

        int res[][] = new int[ans.size()][2];
        for(int i = 0 ; i < ans.size() ; i++){
            res[i] = ans.get(i);
        }

        return res;
        
    }


    // Pascal's Triangle

    // Given row and column print the element at that index 

   public static long funcNcR(int N , int R){

    // TC : O(R)

    long res = 1;

    for(int i = 0 ; i<R ; i++){
        res = res * (N - i);
        res = res/(i+1);
    
    }
    return  res;
   }

    // Print Nth row of the pascal's triangle
    // Current element = prevElement * (rowNumber - colIndex) / colIndex

    public static void pascalTriangle(int n){

        // TC : O(N);

        System.out.print("\n\nThe " + n + " row of the Pascal's Triangle is : ");

        long ans = 1;

        System.out.print(ans + " ");

        for(int i = 1 ; i<n ; i++){
            ans = ans * (n-i);
            ans = ans / i;
            System.out.print(ans + " ");
        }

        System.out.println();
    }


    // Given Row number print the entire Pascal's triangle till that row number.
    public static ArrayList<ArrayList<Long>> printPascalTriangleBr(int n){
        
        ArrayList<ArrayList<Long>> ans = new ArrayList<>();

        for(int row = 1 ; row <n ; row ++){
            ArrayList<Long> tempList = new ArrayList<>();

            for(int col = 1 ; col <= row ; col ++){

                tempList.add(funcNcR(row-1, col-1));
            }
            ans.add(tempList);

        }
        return ans;
    }


    // optimal approach
    public static ArrayList<Long> generateRow(int row) {
        long ans = 1;
        ArrayList<Long> ansRow = new ArrayList<>();
        ansRow.add(1L); 

        //calculate the rest of the elements:
        for (int col = 1; col < row; col++) {
            ans = ans * (row - col);
            ans = ans / col;
            ansRow.add((long)ans);
        }
        return ansRow;
    }
    public static ArrayList<ArrayList<Long>> printPascalTriangleO(int n){
        
        ArrayList<ArrayList<Long>> ans = new ArrayList<>();

        for(int row = 1; row<= n ;row++){
            ans.add(generateRow(row));
        }

        return ans;
    }


    public static ArrayList<Integer> majorityElementby3(int arr[]){
        int n = arr.length;

        ArrayList<Integer> ans = new ArrayList<>();

        for(int i = 0 ; i< n ; i++){
            int cnt = 0;

            if(ans.size() == 0 || ans.get(0) != arr[i]){
                for(int j = 0 ; j < n ; j++){
                    if(arr[j] == arr[i]){
                        cnt++;
                    }
                }

                if(cnt > n/3){
                    ans.add(arr[i]);
                }
    
            }

            if(ans.size() == 2) break;
            
        }

        return ans ;
    }

    public static ArrayList<Integer> majorityElementby3H(int arr[]){

        // TC:  O(N*logN) SC: O(N)

        int n = arr.length;

        int min = (n/3) + 1;

        ArrayList<Integer> ans = new ArrayList<>();

        HashMap<Integer, Integer> mpp = new HashMap<>();

        for(int i = 0 ; i<n ; i++){
            int val = mpp.getOrDefault(arr[i], 0);
            mpp.put(arr[i] , val+1);

            if(mpp.get(arr[i]) == min){
                ans.add(arr[i]);
            }

            if(ans.size() == 2) break;

        }

        Collections.sort(ans); // optional causes (NlogN)

        return ans;

        
    }

    public static ArrayList<Integer> majorityElementby3_O(int arr[]){
        // TC : O(n) + O(n) SC: O(1)
        int n = arr.length;

        ArrayList<Integer> ans = new ArrayList<>();

        int cnt1= 0 , cnt2= 0;

        int ele1 = 0 , ele2 = 0;

        for(int i = 0 ; i<n ; i++){
            if(cnt1 == 0 && arr[i] != ele2){
                ele1 = arr[i]; 
                cnt1++;
            }

            else if(cnt2 == 0 && arr[i] != ele1){
                ele2 = arr[i];
                cnt2++;
            }

            else if(arr[i] == ele1){
                cnt1++;
            }

            else if(arr[i] == ele2){
                cnt2++;
            }

            else{
                cnt1--;
                cnt2--;
            }
        }

        cnt1 = 0; 
        cnt2 = 0;

        for(int i = 0 ; i<n ; i++){
            if(arr[i] == ele1) cnt1++;
            if(arr[i] == ele2) cnt2++;
        }

        int mini = (n/3) + 1;

        if(cnt1 >= mini) ans.add(ele1);
        if(cnt2 >= mini) ans.add(ele2);

        return ans;

    }


    public static List<List<Integer>> threeSumB(int arr[] ){

        int n = arr.length;

        Set<List<Integer>> st = new HashSet<>();

        for(int i = 0 ; i<n ; i++){
            for(int j = i+1 ; j<n ; j++){
                for(int k = j+1 ; k<n ; k++){
                    if((arr[i] + arr[j] + arr[k]) == 0 ){
                       List<Integer> temp = Arrays.asList(arr[i],arr[j],arr[k]);
                       temp.sort(null);
                       st.add(temp);
                    }
                }   
            }
        }

        List<List<Integer>> ans = new ArrayList<>(st);
        return ans ;
    }

    public static List<List<Integer>> threeSumH(int arr[]){

        int n = arr.length;

        Set<List<Integer>> st = new HashSet<>();

        for(int i = 0 ; i<n ; i++){
             Set<Integer> checkSet = new HashSet<>(); // initalize hash set need to be emptied in every interation 
            for(int j = i + 1 ; j < n ; j++){
                int third = -(arr[i] + arr[j]);

                if(checkSet.contains(third)){
                    List<Integer> temp = Arrays.asList(arr[i], arr[j] , third);
                    temp.sort(null);
                    st.add(temp);
                }

                // if the element is not present 
                checkSet.add(arr[j]);

            }
        }

        List<List<Integer>> ans = new ArrayList<>(st);
        
        return ans;

    }

    public static List<List<Integer>> threeSumO(int arr[]){
        int n = arr.length;

        List<List<Integer>> ans = new ArrayList<>();

        Arrays.sort(arr);

        for(int i = 0 ; i<n ; i++){

            // if the i is not in the 0th position and also the element is same do i++ by continue to next iteration
            if(i > 0 && arr[i] == arr[i-1]) continue; 

            int j = i+1;
            int k = n-1;

            while(j < k){
                int sum = arr[i] + arr[j] + arr[k];
                if(sum < 0){ // value of sum is smaller so increase the pointer j

                    j++;

                }
                else if(sum > 0){

                    k--;
                }
                else{
                    List<Integer> temp = Arrays.asList(arr[i],arr[j],arr[k]);
                    ans.add(temp);

                    j++;
                    k--;

                    while(j<k && arr[j] == arr[j-1]) j++; // duplicate check for "j"
                    while(j<k && arr[k] == arr[k+1]) k--; // duplicate check for "k"

                }
            }
        }

        return ans;
    }

    // FourSum
    public static List<List<Integer>> fourSumB(int arr[] , int target){

        int n = arr.length;

        Set<List<Integer>> st = new HashSet<>();

        List<List<Integer>> ans = new ArrayList<>();

        for(int i = 0 ; i< n ;i++){
            for(int j = i+1 ; j < n ; j++){
                for(int k = j+1 ; k < n ; k++){
                    for(int l = k+1 ; l<n ; l++){
                        long sum = (long) arr[i] + arr[j];
                        sum += arr[k];
                        sum += arr[l];

                        if(sum == target){
                            List<Integer> temp = Arrays.asList(arr[i] , arr[j] , arr[k], arr[l]);
                            temp.sort(null);
                            st.add(temp);
                        }
                    }
                }
            }
        }

        ans.addAll(st);

        return ans;
    }

    public static List<List<Integer>> fourSumH(int arr[] , int target){

        // TC: O(n^3)*(log m)  SC: O(n)

        int n = arr.length;

        Set<List<Integer>> st = new HashSet<>();

        for(int i = 0 ; i<n ; i++){
            for(int j = i+1 ; j<n ; j++){

                Set<Long> checkSet = new HashSet<>();

                for(int k = j+1 ; k<n ; k++){
                
                    long sum = arr[i] + arr[j];
                    sum += arr[k];

                    long fourth = target - sum;
                    if(checkSet.contains(fourth)){

                        List<Integer> temp = new ArrayList<>();

                        temp.add(arr[i]);
                        temp.add(arr[j]);
                        temp.add(arr[k]);
                        temp.add((int)fourth);
                        
                        temp.sort(Integer::compareTo);

                        st.add(temp);
                    }

                    checkSet.add((long) arr[k]);
                }
            }
        }

        List<List<Integer>> ans = new ArrayList<>(st);

        return ans;


    }

    public static List<List<Integer>> fourSumO(int[] nums, int target) {
        int n = nums.length;

        Arrays.sort(nums);

        List<List<Integer>> ans = new ArrayList<>();

        for(int i = 0 ; i<n ; i++){
            if(i>0 && nums[i-1] == nums[i]) continue;

            for(int j = i+1 ; j < n ; j++){
                if(j > i+1 && nums[j-1] == nums[j]) continue;

                int k = j+1;
                int l = n-1;

                while(k < l){

                long sum = (long) nums[i] + nums[j] + nums[k] + nums[l];

                if(sum < target){
                        k++;
                }
                else if(sum > target){
                        l--;
                }
                else{

                    List<Integer> temp = Arrays.asList(nums[i] , nums[j] , nums[k] , nums[l]);
                    ans.add(temp);
                    
                    k++;
                    l--;

                    while(k<l && nums[k] == nums[k-1]) k++;
                    while(k<l && nums[l] == nums[l+1]) l--;

                }
                }
            }

            
        }

        return ans;
    }

    // maximum product Subarray.
    public static long maxProdSubarrayB(int arr[]){

        int n = arr.length;

        long maxProd = Long.MIN_VALUE;

        for(int i = 0 ; i<n ; i++){
            long prod = 1;
            for(int j = i ; j<n ; j++){
                
                maxProd = Math.max(prod , maxProd);
                prod *= arr[j];
            }

            maxProd = Math.max(prod , maxProd);
        }

        return maxProd;
    }

    public static long maxProdSubarrayO(int arr[]){

        int n = arr.length;

        long maxProd = Long.MIN_VALUE;

        int prefixSum = 1 , suffixSum = 1;

        for(int i = 0 ; i < n ; i++){

            prefixSum *= arr[i];
            suffixSum *= arr[n - i -1];

            maxProd = Math.max(maxProd , Math.max(prefixSum , suffixSum));

            if(prefixSum == 0) prefixSum = 1;
            if(suffixSum == 0) suffixSum = 1;

        }

        return  maxProd;

    }

    // merge to sorted subarray without extra space.
    public static void sortedSubarraysMerge(long arr1[] , long arr2[]){   
        int n = arr1.length;
        int m = arr2.length;

        int left = n-1;
        int right = 0;

        while(left >= 0 && right < m){
            if(arr1[left] >= arr2[right]){
                long temp = 0 ;
                temp = arr1[left];
                arr1[left] = arr2[right]; 
                arr2[right] = temp;
                left--;
                right++;
            } else {
                break;
            }
        }

        Arrays.sort(arr1);
        Arrays.sort(arr2);
    }

    // missing repeating number
    public static int[] repeatedMissingNumsB(int arr[]){

        int n = arr.length;

        int missing = -1 , repeating = -1;

        for(int i = 1 ; i<= n ; i++){
            int cnt = 0;
            
            for(int j = 0 ; j<n ; j++){
                if(arr[j] == i){
                    cnt++;
                }
            }

            if(cnt == 0) missing = i;
            else if(cnt == 2) repeating = i;

            if (repeating != -1 && missing != -1)
                break;
            
        }

        int[] ans = {repeating, missing};

        return  ans;

    }
    
    public static int[] repeatedMissingNumsH(int arr[]){
        // TC: O(2N) SC: O(N) 

        int n = arr.length;

        int hash[] = new int[n+1];

        for(int i = 0 ; i< n ; i++){
            hash[arr[i]]++;
        }

        int repeating = -1, missing = -1;

        for(int i = 1 ; i <= n ; i++){
            if(hash[i] == 2) repeating = i;
            else if(hash[i] == 0) missing = i; 

            if(repeating != -1 && missing != -1) break;
        }

        int ans[] = {repeating , missing};

        return  ans;

    }

    public static int[] repeatedMissingNumsO(int arr[]){
        // TC: O(N) SC: O(1)

        // S - Sn -> x-y
        // S2 - S2N -> x+y

        long n = arr.length;

        long SN = (n * (n+1))/2;

        long S2N = (n * (n+1) * (2*n + 1))/6;

        long s1= 0 , s2 = 0;

        for(int i = 0 ; i<n ; i++){
            s1 += arr[i];
            s2 += arr[i] * arr[i];
        }

        long val1 = s1 - SN; // x-y
        long val2 = s2 - S2N; // (x^2 - y ^2)

        // x^2 - y^2 = (x+y) (x-y)
 
        val2 = val2 / val1; // val2 = s2 - s2n / (x - y) -> val1 -> give (x + y)

        /*
         * x - y = val1
         * x + y = val2
         * 
         * 2x = val1 + val2
         * x = (val1 + val2)/2 -> repeating
         * 
         * y = x - val1 -> missing 
         */

         long x = (val1 + val2) / 2;
         long y = x - val1;

         int ans[] = {(int) x , (int) y};

         return  ans;

    }

    public static ArrayList<Integer> findTwoElement(int arr[]) {
        long n = (long) arr.length;
        
        long s1N = (long) (n * (n+1))/2;
        long s2N = (long) (n * (n+1) * (2L*n+1))/6;
        
        long s1 = 0 , s2 = 0;
        
        for(int i = 0 ; i<n ; i++){
            s1 += arr[i];
            s2 += (long) arr[i] * arr[i];
        }
        
        long val1 = s1N - s1;
        long val2 = s2N - s2;
        val2 = val2/val1;
        
        long x = (val1 + val2)/2; // repeating
        long y = (x - val1); // missing
        
        ArrayList<Integer> ans  = new ArrayList<>();
        
        ans.add((int)y);
        ans.add((int)x);
        
        return ans;
    }

    public static ArrayList<Integer> findTwoElement_O(int nums[]) {
        int n = nums.length;
        
        ArrayList<Integer> ans = new ArrayList<>();
        
        for(int i = 0; i < n ; i++){
            int idx = Math.abs(nums[i]) - 1;

            if(nums[idx] < 0){
                // already visited → duplicate found
                ans.add(Math.abs(nums[i]));
            }
            else nums[idx] = -nums[idx];
        }

        // Find missing number
        for(int i = 0 ; i < n ; i++){
            if(nums[i] > 0) ans.add(i + 1);
        }

        return ans;

    }

    
    // Next Permutation
    public static void nextPermutation(int[] nums) {
        int n = nums.length;

        int ind = -1;

        for(int i = n-2 ; i >= 0 ; i--){
            if(nums[i] < nums[i+1]){
                ind = i;
                break;
            }
        }

        if(ind == -1){
            for(int i = 0 ; i < n/2 ; i++){
                int temp = nums[i];
                nums[i] = nums[n-i-1];
                nums[n-i-1] = temp;
            }
            
            return;
        }

        for(int i = n-1 ; i >= ind ; i--){
            if(nums[i] > nums[ind]){
                int temp = nums[ind];
                nums[ind] = nums[i];
                nums[i] = temp;
                break;
            }
        }

        int start = ind + 1 , end = n-1;

        while(start < end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;

            start++;
            end--;
        }
    }

    // Merge Overlapping subintervals 
    public static int[][] mergeOverlappingIntervalsO(int[][] arr) {
        int n = arr.length;

        Arrays.sort(arr , (a , b) -> Integer.compare(a[0] , b[0]));

        List<List<Integer>> ans = new ArrayList<>();

        for(int i = 0 ; i<n ; i++){

            if(ans.isEmpty() || arr[i][0] > ans.get(ans.size() -1).get(1)){
                ans.add(Arrays.asList(arr[i][0] , arr[i][1]));
            }
            else{
                ans.get(ans.size()-1).set( 1 , Math.max(ans.get(ans.size()-1).get(1) , arr[i][1]));
            }
        }

        int[][] merged = new int[ans.size()][2];
        for(int i = 0 ; i< ans.size() ; i++){
            merged[i][0] = ans.get(i).get(0);
            merged[i][1] = ans.get(i).get(1);
        }

        return merged;
    }

    // Count Subarrays with given XOR 
    public static long subarrayXor(int arr[], int k) {
        int n = arr.length;
        
        Map<Integer,Integer> mpp = new HashMap<>();
        mpp.put(0,1);
        
        int xr = 0;
        
        int cnt = 0;
        
        for(int i = 0 ; i<n ; i++){
            
            xr = xr ^ arr[i];
            
            int  x = xr ^ k;
            
            if(mpp.containsKey(x)){
                
                cnt += mpp.get(x);
            }
            
            mpp.put( xr , mpp.getOrDefault(xr , 0) + 1);
        }
        
        return cnt;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();

        matrix.add(new ArrayList<>(Arrays.asList(1,1,1,1)));
        matrix.add(new ArrayList<>(Arrays.asList(1,0,1,1)));
        matrix.add(new ArrayList<>(Arrays.asList(1,1,0,1)));
        matrix.add(new ArrayList<>(Arrays.asList(0,1,1,1)));

        int[][] mat = {
            {1, 1, 1, 1},
            {1, 0, 1, 1},
            {1, 1, 0, 1},
            {0, 1, 1, 1}
        };

        int [][] mat2 = {
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 0},
            // {0, 1, 1}
        };

        ArrayList<ArrayList<Integer>> Zres =  zeroMatrixO(matrix);

        for(ArrayList<Integer> it : Zres){
            System.out.print(it);
            System.out.println();
        }

        int[][] matZ = zeroMatrixOp(mat2);

        for (int i = 0; i < matZ.length; i++) {
            for (int j = 0; j < matZ[0].length; j++) {
                System.out.print(matZ[i][j] + " ");
            }
            System.out.println();
        }


        int rot[][] = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
        };

        // int rotRes[][] = rotateMatrixBr(rot);
        rotateMatrixO(rot);

        System.out.println("Rotated Matrix");

        for (int i = 0; i < rot.length; i++) {
            for (int j = 0; j < rot[0].length; j++) {
                System.out.print(rot[i][j] + " ");
            }
            System.out.println();
        }


        // Spiral Matrix
        ArrayList<Integer> spiralMat = spiralMatrix(new int[][]{
            {1,2,3},
            {8,9,4},
            {7,6,5},
        });
        System.out.println("The Spiral through the provided matrix is : " + spiralMat);


        int n = 5;
        pascalTriangle(n);


        System.out.println("\nThe Pascals triangle for the give row value : \n");
        // ArrayList<ArrayList<Long>> ans = printPascalTriangleBr(6);
        ArrayList<ArrayList<Long>> ans = printPascalTriangleO(6);
        for (List<Long> it : ans) {
            for (long ele : it) {
                System.out.print(ele + " ");
            }
            System.out.println();
        }


        ArrayList<Integer> maj3Res = majorityElementby3_O(new int[]{11, 33, 33, 11, 33, 11});
        System.out.println("The majority element greater then n/3 are : " + maj3Res);



        // Three sum

        List<List<Integer>> ansTriplet = threeSumO(new int[]{ -1, 0, 1, 2, -1, -4});

        System.out.println("The triplets of the given array is : " + ansTriplet);

        // Four Sum

        List<List<Integer>> ansFourSum = fourSumO(new int[]{4, 3, 3, 4, 4, 2, 1, 2, 1, 1}, 9);


        System.out.println("The quadruplets of the given array is : " + ansFourSum);



        ArrayList<Integer> Snake = snakePattern(new int[][]{
            {1,2,3},
            {4,5,6},
            {7,8,9},
        });

        // Maximum product subarray

        long maxProd = maxProdSubarrayO(new int[]{1,2,0,3,4,5,0});

        System.out.println("The Maximum product form the sub array is : " + maxProd);

        // merge elements of two sorted subarrays

        long[] larr = {1, 4, 8, 10}; 
        long[] rarr = {2, 3, 9};

        sortedSubarraysMerge(larr , rarr);
        
        System.out.println("\n\nThe merged arrays are:");

        System.out.print("arr1[] = ");

        for (int i = 0; i < larr.length; i++) {
            System.out.print(larr[i] + " ");
        }

        // for(long it : larr){
        //     System.out.print(it + " ");
        // }

        System.out.print("\narr2[] = ");

        for (int i = 0; i < rarr.length ; i++) {
            System.out.print(rarr[i] + " ");
        }

        System.out.println("\n\n");

        // Snake pattern
        System.out.println("The answer printed in Snake pattern is : " + Snake);

        // Repeating and missing numbers in the array

       int[] repMis = repeatedMissingNumsO(new int[]{3,1,2,5,4,6,7,5});

        System.out.println("The Repeating and missing element in the array is : " + repMis[0] +" "+repMis[1]);

        int[] perm = {1, 2, 3};
        nextPermutation(perm);
        System.out.print("Next permutation: ");
        for (int num : perm) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Merge operlapping interval 
        int[][] mOarr = {{1, 3}, {8, 10}, {2, 6}, {15, 18} ,{2,4}};
        int[][] mergedIntervals = mergeOverlappingIntervalsO(mOarr);
        System.out.print("The merged intervals are: \n");
        for (int[] it : mergedIntervals) {
            System.out.print("[" + it[0] + ", " + it[1] + "] ");
        }
        System.out.println();

        int[] xorArr = {5, 6, 7, 8, 9};
        int xorK = 5;
        long xorCount = subarrayXor(xorArr, xorK);
        System.out.println("Number of subarrays with XOR " + xorK + " is: " + xorCount);

        
    }
}
