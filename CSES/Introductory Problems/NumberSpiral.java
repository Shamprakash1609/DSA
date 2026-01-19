import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NumberSpiral {
    private static long findXY(long row , long col){
        long maxi = Math.max(row , col);

        if(maxi == row){
            if(row % 2 == 0){
                long start = (row * row);
                return start - (col - 1);
            }
            else{
                long start = ((row - 1) * (row - 1)) + 1;
                return start + (col - 1);
            }
        }else{
            if(col % 2 == 0){
                long start = ((col - 1) * (col - 1)) + 1;
                return start + (row - 1);
            }
            else{
                long start = (col * col);
                return start - (row - 1);
            }
        }
    }


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int t = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < t ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());

            System.out.println(findXY(a , b));
        }
    }
}
