import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BitStrings {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int MOD = 1_000_000_007;

        long ways = 1;

        for(int i = 1 ; i <= n ; i++){
            ways = (ways * 2) % MOD;
        }

        System.out.println(ways);
    }    
}
