import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class MissingNumber {
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        long xor = 0;

        for(int i = 1 ; i <= n ; i++){
            xor ^= i;
        }

        for(int i = 0 ; i < n - 1 ; i++){
            xor ^= Long.parseLong(st.nextToken());
        }

        System.out.println(xor);
    }
}
