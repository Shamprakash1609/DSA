package Sorting;

import Sorting.Sorting.MergeSort;
import Sorting.Sorting.QuickSort;
import java.util.*;

public class Sorting {

    // Selection sort -> Select the minimum element and place in the minimum position.
    static void selectionSort(int arr[]){
        // Time complexity: O(N^2)
        System.out.println("Selection Sort");
        int n = arr.length;

        for(int i = 0 ; i< n-1 ; i++){
            int min = i;
            for(int j = i+1 ; j< n ; j++){
                if(arr[j] < arr[min]){
                    min = j;
                }
            }

            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }

        for(int i = 0 ; i < n ; i++){
            System.out.print(arr[i] + " ");
        }
    }

    static void selectionSortR(int arr[] , int R , int C , int maxi){
        if(R == 0){
            return;
        }

        if(C < R){
           if(arr[C] > arr[maxi]){
            selectionSortR(arr, R, C + 1, C);
           }
           else{
            selectionSortR(arr, R, C + 1, maxi);
           }
        }
        else{
            int temp = arr[maxi];
            arr[maxi] = arr[R - 1];
            arr[R - 1] = temp;

            selectionSortR(arr, R - 1, 0, 0);
        }
    }

    // Bubble Sort -> Push the maximum elements to the last by adjacent swaps.
    static void bubbleSort(int arr[]){
        // TC :: Worst case : O(N^2) Best case : O(N)
        System.out.println("\n\nBubble Sort");
        int n = arr.length;

        for(int i =  n-1 ; i >= 1 ; i-- ){
            int didSwap = 0;
            for(int j = 0 ; j <= i-1 ; j++ ){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;

                    didSwap = 1;
                }

            }
            if(didSwap == 0){
                break;
            }

            // System.out.println("runs");
        }

        for(int i = 0 ; i < n ; i++){
            System.out.print(arr[i] + " ");
        }
    }

    static void bubbleSortR(int arr[] , int R , int C){
        if(R == 0){
            return;
        }

        if(C < R){
            if(arr[C] > arr[C + 1]){
                int temp = arr[C];
                arr[C] = arr[C + 1];
                arr[C + 1] = temp;
            }
            bubbleSortR(arr , R , C + 1);
        }
        else{
            bubbleSortR(arr, R - 1, 0);
        }
    }

    // Insertion sort -> Takes an element and place it in its correct position.
    static void insertionSort(int arr[]){
        // TC : O(N^2)
        System.out.println("\n\nInsertion Sort");
        int n = arr.length;

        for(int i =0 ; i <= n - 1 ; i++){
            int j  = i;
            while(j > 0 && arr[j - 1] > arr[j]){
                int temp = arr[j - 1];
                arr[j - 1] = arr[j];
                arr[j] = temp;

                j--;
            }
        }

        for(int i = 0 ; i< n ; i++){
            System.out.print(arr[i] +" ");
        }
        System.out.println();
    }

    // Merge Sort
    static class MergeSort{

        // TC : O(Nlog₂N )

        // static void merge(int arr[] , int lb , int mid , int ub){

        //     int i = lb;
        //     int j = mid+1;
        //     int k = lb;

        //     int b[] = new int[arr.length];

        //     while (i <= mid && j <= ub) {
        //         if(arr[i] <= arr[j]){
        //             b[k] = arr[i];
        //             i++;
        //         }else{
        //             b[k] = arr[j];
        //             j++;
        //         }

        //         k++;
        //     }

        //     if(i > mid){
        //         while (j <= ub) {
        //             b[k] = arr[j];
        //             j++;
        //             k++;
        //         }
        //     } else{
        //         while(i <= mid){
        //             b[k] = arr[i];
        //             i++;
        //             k++;
        //         }
        //     }

        //     // copying everthing to arr[] itself

        //     for(int m = lb ; m <= ub ; m++){
        //         arr[m] = b[m];
        //     }


        // }


        static void merge(int arr[] , int low , int mid , int high){
            List<Integer> temp = new ArrayList<>();
            int l = low;
            int r = mid + 1;

            while(l <= mid && r <= high){
                if(arr[l] <= arr[r]){
                    temp.add(arr[l]);
                    l++;
                }else{
                    temp.add(arr[r]);
                    r++;
                }
            }

            while(l <= mid){
                temp.add(arr[l]);
                l++;
            }

            while(r <= high){
                temp.add(arr[r]);
                r++;
            }

            for(int i = low ; i <= high ; i++){
                arr[i] = temp.get(i - low);
            }

        }

        static void mergeSort(int arr[] , int lb , int ub){
            if(lb >= ub) return;

            int mid = (lb+ub)/2;
    
            mergeSort(arr, lb, mid);
            mergeSort(arr, mid+1, ub);

            merge(arr, lb , mid , ub);

            // if( lb < ub){}
    
        }


        
       static void printArray(int arr[]) {
            System.out.println("\nMergeSort");
            for (int num : arr) {
                System.out.print(num + " ");
            }
            System.out.println();
        }

    }
    
    // Quick Sort
    static class QuickSort{   
        // TC : θ(nlogn) worstCase: O(n^2) 
        
        static int partition(int arr[] , int start , int end){
            int pivot = arr[start];
            int left = start + 1;
            int right = end;

            /* Intution Place Pivot at the correct position and place smaller element in leftside and larger elements in rigthside of it
                left -> scans towards right until element >= pivot
                right -> scans towards left until element < pivot

                Swap if both element are found and (left <= right) - So the largest goes right - smaller comes left

                if left and right crossed eachother -> (left > right) - stop scanning -> swap(high , pivot) elements 
                 
            */

            while(left <= right){
                while(left <= end && arr[left] <= pivot) left++;
                while(right >= start && arr[right] > pivot) right--; // comparisons expect pivot

                if(left < right){
                    int temp = arr[left];
                    arr[left] = arr[right];
                    arr[right] = temp;
                }
            }
            // put pivot inPlace
            int temp = arr[start];
            arr[start] = arr[right];
            arr[right] = temp;

            return right;

        }

        static void quickSort(int arr[] , int lb , int ub){
            if(lb >= ub){
                return;
            }
 
            int pivot = partition(arr, lb, ub);
            quickSort(arr, lb, pivot - 1);
            quickSort(arr, pivot + 1, ub);

        }

        static void printArray(int arr[]) {
            System.out.println("\nQuickSort");
            for (int num : arr) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
 
    }

    // Counting Sort -> Limited Range sorting
    public static void countingSort(int arr[]){
        // TC : O(1) SC : O(N)
        int n = arr.length;

        int maxi = Integer.MIN_VALUE;
        int mini = Integer.MAX_VALUE;

        for(int it : arr){
            maxi = Math.max(maxi , it);
            mini = Math.min(mini , it);
        }

        int range = maxi - mini + 1;

        int count[] = new int[range];

        int output[] = new int[n]; 

        for(int j : arr){
            count[j - mini]++;
        }

        // Cummulative sum
        for(int i = 1 ; i < n ; i++){
            count[i] += count[i - 1];
        }

        for(int i = n - 1 ; i >= 0 ; i++){
            output[count[arr[i] - mini] - 1] = arr[i]; // element of the index placed at crt index in output array;
            count[arr[i] - mini]--;
        }


    }

    // Radix Sort
    private static  void countingSort_RDX(int[] arr, int place) {
        int size = arr.length;
        int max = Arrays.stream(arr).max().getAsInt();
        int[] output = new int[size + 1];
        int[] count = new int[max + 1];

        // Calculate count of elements
        for (int j : arr)
        count[(j / place) % 10]++;

        // Calculate cumulative count
        for (int i = 1; i < 10; i++)
        count[i] += count[i - 1];

        // Place the elements in sorted order
        for (int i = size - 1; i >= 0; i--) {
        output[count[(arr[i] / place) % 10] - 1] = arr[i];
        count[(arr[i] / place) % 10]--;
        }

        System.arraycopy(output, 0, arr, 0, size);
    }

    public static void radixSort(int [] arr){
        // TC : O(N * K) 
        int n = arr.length;

        boolean isNeg = false;
        for(int i : arr){
            if(i < 0){
                isNeg = true;
                break;
            } 
        }

        int mini = Integer.MAX_VALUE;

        if(isNeg){
            for(int it : arr){
                mini = Math.min(mini , it);
            }

            for(int i = 0 ; i < n ; i++){
                arr[i] = arr[i] - mini; // '-' because eg mini = -9 then arr[i] - (-9) === arr[i] + 9
            }
        }

        int maxi = Integer.MIN_VALUE;

        for(int i : arr){
            maxi = Math.max(i , maxi);
        }

        for(int place = 1 ; maxi / place > 0 ; place *= 10){
            countingSort_RDX(arr, place);
        }

        for(int i = 0 ; i < arr.length; i++){
            arr[i] = arr[i] + mini;
        }


    }

    
    // Count Inversion
    static int inversionCount(int arr[]) {
        int n = arr.length;
        
        return mergeSort_In(arr , 0 , n - 1);
    }
    
    static int mergeSort_In(int arr[] , int lb , int ub){
        int cnt = 0;
        if(lb >= ub) return cnt;
        
        int mid = (lb + ub) / 2;
        
        cnt += mergeSort_In(arr , lb , mid);
        cnt += mergeSort_In(arr , mid + 1 , ub);
        cnt += countPairs_In(arr , lb , mid , ub);
        
        merge_In(arr , lb , mid , ub);
        return cnt;
    }

    public static int countPairs_In(int arr[] , int low , int mid , int high){
        int right = mid + 1;
        int cnt = 0;
        
        for(int i = low ; i <= mid ; i++){
            while( right <= high && (long) arr[i] > (long) arr[right]){
                right++;
            }
            
            cnt += (right - (mid + 1));
        }
        
        return cnt;
    }
    
    public static void merge_In(int arr[] , int low , int mid , int high){
        
        List<Integer> temp = new ArrayList<>();
        
        int l = low;
        int r = mid + 1;
        
        while(l <= mid && r <= high){
            if(arr[l] <= arr[r]){
                temp.add(arr[l]);
                l++;
            }
            else{
                temp.add(arr[r]);
                r++;
            }
        }
        
        while(l <= mid){
            temp.add(arr[l]);
            l++;
        }
        
        while(r <= high){
            temp.add(arr[r]);
            r++;
        }
        
        for(int i = low ; i <= high ; i++){
            arr[i] = temp.get(i - low);
        }
    }

    // Reverse Pairs
    public static  int reversePairs(int[] nums) {
        int n = nums.length;
        return mergeSort_Rev(nums , 0 , n - 1);
    }

    public static int countPairs_Rev(int[] arr, int low, int mid, int high) {
        int right = mid + 1;
        int cnt = 0;
        for (int i = low; i <= mid; i++) {
            while (right <= high && (long) arr[i] > 2L * (long) arr[right]) {
                right++;
            }
            cnt += (right - (mid + 1));
        }
        return cnt;
    }

    static void merge_Rev(int arr[] , int low , int mid , int high){
            List<Integer> temp = new ArrayList<>();
            int l = low;
            int r = mid + 1;

            while(l <= mid && r <= high){
                if(arr[l] <= arr[r]){
                    temp.add(arr[l]);
                    l++;
                }else{
                    temp.add(arr[r]);
                    r++;
                }
            }

            while(l <= mid){
                temp.add(arr[l]);
                l++;
            }

            while(r <= high){
                temp.add(arr[r]);
                r++;
            }

            for(int i = low ; i <= high ; i++){
                arr[i] = temp.get(i - low);
            }

        }
    
    public static int mergeSort_Rev(int[] arr , int low , int high){
        int cnt = 0;
        if(low >= high) return cnt;
        int mid = (low + high) / 2;
        cnt += mergeSort_Rev(arr , low , mid);
        cnt += mergeSort_Rev(arr , mid + 1 , high);
        cnt += countPairs_Rev(arr , low , mid , high);
        merge_Rev(arr , low , mid , high);
        return cnt;
    }

    // Largest Number
    public static String largestNumber_B(int[] nums) {
        int n = nums.length;
        String arr[] = new String[n];

        for(int i = 0 ; i < n ; i++){
            arr[i] = String.valueOf(nums[i]);
        }

        for(int i = 0 ; i < n ; i++){
            for(int j = i + 1; j < n ; j++){
                String ab = arr[i] + arr[j];
                String ba = arr[j] + arr[i];

                if(ba.compareTo(ab) > 0){
                    String temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        if(arr[0].equals("0")) return "0";

        StringBuilder sb = new StringBuilder();

        for(String str : arr){
            sb.append(str);
        }

        return sb.toString();
    }
    
    public static String largestNumber_O(int[] nums) {
        int n = nums.length;

        mergeSort(nums , 0 , n - 1);

        if(nums[0] == 0) return "0";

        StringBuilder sb = new StringBuilder();

        for(int num : nums){
            sb.append(String.valueOf(num));
        }

        return sb.toString();
    }

    private static void mergeSort(int arr[] , int lb , int ub){
        if(lb >= ub) return;

        int mid = lb + (ub - lb) / 2;

        mergeSort(arr , lb , mid);
        mergeSort(arr , mid + 1 , ub);

        merge(arr , lb , mid , ub);
    }

    private static void merge(int arr[] , int lb , int mid , int ub){
        List<Integer> temp = new ArrayList<>();

        int l = lb;
        int r = mid + 1;

        while(l <= mid && r <= ub){
            String ab = String.valueOf(arr[l]) + String.valueOf(arr[r]);
            String ba = String.valueOf(arr[r]) + String.valueOf(arr[l]);

            if(ab.compareTo(ba) > 0){
                temp.add(arr[l]);
                l++;
            }
            else{
                temp.add(arr[r]);
                r++;
            }
        }

        while(l <= mid){
            temp.add(arr[l]);
            l++;
        }

        while(r <= ub){
            temp.add(arr[r]);
            r++;
        }

        for(int i = lb ; i <= ub ; i++){
            arr[i] = temp.get(i - lb);
        }
    }

    public static String largestNumber_C(int[] nums) {
        int n = nums.length;

        String arr[] = new String[n];

        for(int i = 0 ; i < n ; i++){
            arr[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(arr , (a , b) -> (b + a).compareTo(a + b));

        if(arr[0].equals("0")) return "0";

        StringBuilder sb = new StringBuilder();

        for(String str : arr){
            sb.append(str);
        }

        return sb.toString();
    }



    

    public static void main(String[] args) {

        int [] arrS = {31,3124,3434,11,34,112,6,9};
        int [] arrB = {1,2,3,4,5,0};
        int [] arrI = {12,233,433,3,34,1,23};
        int [] arrM = {38, 27, 43, 3, 9, 82, 10};
        int [] arrQ = {45, 27,23,43, 3,1,23};
 
        selectionSort(arrS);
        // selectionSortR(arrS, arrS.length, 0 , 0);
        // System.out.println("\nThe Selection sort using Recursion : " + Arrays.toString(arrS));

        bubbleSort(arrB);
        // bubbleSortR(arrB , arrB.length - 1 , 0);
        // System.out.println("\nThe Bubble sort using Recursion : " + Arrays.toString(arrB));

        insertionSort(arrI);

        int n = arrM.length;
        MergeSort m = new MergeSort();

        m.mergeSort(arrM, 0, n-1);
        m.printArray(arrM);

        
        QuickSort q = new QuickSort();
        q.quickSort(arrQ, 0 , arrQ.length-1);

        q.printArray(arrQ);


        // Count Inversions
        int[] arrInv = {2, 4, 1, 3, 5};
        int invCount = inversionCount(arrInv);
        System.out.println("\nInversion Count: " + invCount); // The sequence 2, 4, 1, 3, 5 has three inversions (2, 1), (4, 1), (4, 3).

        // Reverse Pairs
        int[] arrRev = {1, 3, 2, 3, 1}; // Reverse pairs : [(1, 4) , (3, 4)]
        int revPairs = reversePairs(arrRev);
        System.out.println("Reverse Pairs: " + revPairs);

        int[] arrLargest = {3, 30, 34, 5, 9};
        String largest = largestNumber_B(arrLargest);
        System.out.println("Largest Number: " + largest);

        
    }
    
}
