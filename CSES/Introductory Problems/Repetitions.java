
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Repetitions {
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String dna = br.readLine();

        int n = dna.length();

        
        int cnt = 1;
        int maxi = 1;

        for(int i = 1 ; i < n ; i++){
            if(dna.charAt(i) == dna.charAt(i - 1)) cnt++;
            else cnt = 1;
            maxi = Math.max(cnt , maxi);
        }

        System.out.println(maxi);
    }
}
