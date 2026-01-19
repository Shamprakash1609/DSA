
import java.util.*;

class WeirdAlgorithm {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long num = sc.nextLong();

        while(num > 1){
            System.out.print(num + " ");
            if(num % 2 == 1){
                num = (3 * num) + 1;
            }else{
                num /= 2;
            }
        }

        System.out.print(1);
    }
}
