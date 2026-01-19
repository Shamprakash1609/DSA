import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TwoSets {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());

        long sum = n * (n + 1) / 2;

        if(sum % 2 == 1) System.out.println("NO");
        else {
            System.out.println("YES");
            StringBuilder set1 = new StringBuilder();
            StringBuilder set2 = new StringBuilder();

            int cnt1 = 0 , cnt2 = 0;

            long targetSum = sum / 2;

            for(long i = n ; i >= 1 ; i--){
                if(i <= targetSum){
                    set1.append(i).append(" ");
                    targetSum -= i;
                    cnt1++;
                }
                else{
                    set2.append(i).append(" ");
                    cnt2++;
                }
            }

            System.out.println(cnt1);
            System.out.println(set1.toString().trim());

            System.out.println(cnt2);
            System.out.println(set2.toString().trim());
        }
    }    
}
