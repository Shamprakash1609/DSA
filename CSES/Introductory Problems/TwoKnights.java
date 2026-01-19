import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TwoKnights {
    public static void main(String[] args) throws  Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        long n = Long.parseLong(br.readLine());


        long totalCombinations = 0;
        long attackingKnights = 0;

        /* 
        All Combinations -> nCr 
        n -> matrix size
        r -> (2) - no of Knights

        Attacking Knights -> Invalid Scenario eg:- (2 x 3) or (3 x 2)
        (2 x 3) in horizontal -> appears (n - 2) times , vertical -> (n - 1) times : Total (n - 2) * (n - 1)
                                                    +
        (3 x 2) in vertical -> appears (n - 2) times , horizontal -> (n - 1) times : Total (n - 1) * (n - 2)

        grand total : 2((n - 1) * (n - 2))

        invalid Combinations = 2 x 2((n - 1) * (n - 2)) -> 4((n - 1) * (n - 2)) - There are total two invalid combinations on each (2 x 3) and (3 x 2)
        */

        for(long i = 1 ; i <= n ; i++){
            totalCombinations = ((i * i) * ((i * i) - 1)) / 2;
            attackingKnights = 4 * (i - 1) * (i - 2);
            
            System.out.println(totalCombinations - attackingKnights);
        }
    }
}
