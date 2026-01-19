
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class IncreasingArray {

    public static void main(String[] args) throws  Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        Long maxi = Long.parseLong(st.nextToken());

        long sum = 0; 

        for(int i = 1 ; i < n ; i++){
            long num =  Long.parseLong(st.nextToken());
            maxi = Math.max(maxi , num);
            sum += maxi - num;
        }
        
        System.out.println(sum);
    }
}