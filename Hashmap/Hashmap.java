package Hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Hashmap {

    static List<Integer> frequencyCount(int[] arr) {
        HashMap<Integer, Integer> num = new HashMap<>();
        int n = arr.length;

        
        for (int i = 1; i <= n; i++) {  
            num.put(i, 0);
        }

        
        for (int i = 0; i < n; i++) {
            if (num.containsKey(arr[i])) {
                num.put(arr[i], num.get(arr[i]) + 1);
            }
        }

       
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            nums.add(num.get(i));  
        }

        return nums;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 2, 3, 5};

        List<Integer> result = frequencyCount(arr);
        System.out.println(result); 
    }


    
}
