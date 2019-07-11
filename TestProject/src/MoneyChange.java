import java.util.ArrayList;
import java.util.List;

public class MoneyChange {

    public static int moneyChangeDP(int amount, int[]cashType){
        int size = Math.max(amount + 1, cashType[cashType.length-1]+1);
        int[] dp = new int[size];
        dp[0] = 0;
        for(int i = 1; i < dp.length; i++){
            dp[i] = Integer.MAX_VALUE;
        }
        for(int i = 0; i < cashType.length; i++){
            dp[cashType[i]] = 1;
        }

        for(int price = 0; price <= amount; price++){
            List<Integer> backup = new ArrayList<>();
            for(int i = 0; i < cashType.length; i++){
                int cash = cashType[i];
                if(price - cash >= 0){
                    backup.add(dp[price-cash] + 1);
                }
            }

            for(int i = 0; i < backup.size();i++){
                dp[price] = Math.min(dp[price], backup.get(i));
            }
        }


        return dp[amount];

    }

    public static int moneyChangeRecursion(int amount, int[] cashType){
        if(amount == 0){
            return 0;
        }
        for(int i = 0; i < cashType.length; i++){
            if(amount == cashType[i]){
                return 1;
            }
        }

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < cashType.length; i++){
            if(amount < cashType[i]){
                break;
            }else{
                min = Math.min(min, 1 + moneyChangeRecursion(amount - cashType[i], cashType));
            }
        }

        return min;
    }

    public static void test(){
        int[] cashType = {1,3,7,11,15};
        for(int price = 1; price <= 100; price++){
            double t1 = System.currentTimeMillis()/1000.0;
            System.out.print("       DP("+price+"): " + moneyChangeDP(price,cashType));
            double t2 = System.currentTimeMillis()/1000.0;
            System.out.println("   time:" + (t2-t1));

            double t3 = System.currentTimeMillis()/1000.0;
            System.out.print("Recursion("+price+"): " + moneyChangeRecursion(price,cashType));
            double t4 = System.currentTimeMillis()/1000.0;
            System.out.println("   time:" + (t4-t3));
            System.out.println();
        }
    }

    public static void main(String[] args){

        test();
//        int[] cashType = {1,3,7,11,15};
//        System.out.println(moneyChangeDP(1,cashType));
    }

}
