import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws Exception{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n  = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        long prefix[] = new long[n + 1];

        st = new StringTokenizer(br.readLine());

        for(int i = 1 ; i <= n ; i++){
            long x = Long.parseLong(st.nextToken());
            prefix[i] = prefix[i - 1] + x;
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < q ; i++){
            st = new StringTokenizer(br.readLine());
            int qsi = Integer.parseInt(st.nextToken());
            int qei = Integer.parseInt(st.nextToken());

            long ans = prefix[qei] - prefix[qsi - 1];

            sb.append(ans).append('\n');
        }

        System.out.println(sb.toString());


    }
    
}
