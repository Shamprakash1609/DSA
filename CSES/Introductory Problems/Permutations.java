import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Permutations {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        if(n == 1) System.out.println("1");
        else if(n <= 3) System.out.println("NO SOLUTION");
        else{
            StringBuilder sb = new StringBuilder();

            for(int i = 2 ; i <= n ; i += 2){
                sb.append(i).append(" ");
                // System.out.print(i + " ");
            }
            for(int i = 1 ; i <= n ; i += 2){
                sb.append(i).append(" ");
                // System.out.print(i + " ");
            }

            System.out.println(sb.toString());
        }
    }
}
