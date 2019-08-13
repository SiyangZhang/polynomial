import java.util.*;

public class Node {

    List<Node> childrens;
    String name;


    public Node() {
        name = "unknown";
//        childrens = new ArrayList<>();
    }

    public Node(String name) {
        this.name = name;
//        childrens = new ArrayList<>();
    }

    public void addChild(Node x) {
        if (childrens == null) {
            childrens = new ArrayList<>();
        }
        childrens.add(x);
    }


    public boolean hasChild(Node x) {
        if (name.equals(x.name)) {
            return true;
        } else {
            if (childrens.size() > 0) {
                for (int i = 0; i < childrens.size(); i++) {
                    if (childrens.get(i).hasChild(x)) {
                        return true;
                    }
                }
                return false;
            } else {
                return false;
            }
        }
    }


    public void dump() {
        dumpWithBuffer("");
    }

    public void dumpWithBuffer(String bufferString) {

        System.out.println(bufferString + this.name);
        if (this.childrens != null) {
            for (int i = 0; i < this.childrens.size(); i++) {
                String buffer = "\t";

                this.childrens.get(i).dumpWithBuffer(bufferString + buffer);
            }
        }

    }


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        if (len1 > len2) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        len1 = nums1.length;
        len2 = nums2.length;


        int length = len1 + len2;

        //odd: find one value
        int mid1 = len1 / 2;
        int mid2 = len2 / 2;


        while (nums1[mid1] < nums2[mid2] && mid1 < len1 - 2) {
            mid1 = (mid1 + len1 - 1) / 2;

        }

        while (nums1[mid1] > nums2[mid2] && mid1 >= 0) {
            mid1 = mid1 / 2;
        }

        System.out.println(mid1 + ": " + nums1[mid1]);

        return 0;
    }


    public boolean isOdd(int n){

        while(n >= 2){
            n -= 2;
        }


        try{
            int s = 1/n;
            return true;
        }catch(Exception e){
            return false;
        }
    }



    public String add(String num1, String num2){
        String small = "";
        String big = "";
        String res = "";
        if(num1.length() > num2.length()){
            big = num1;
            small = num2;
        }else{
            big = num2;
            small = num1;
        }

        StringBuilder sb1 = new StringBuilder(big);
        StringBuilder sb2 = new StringBuilder(small);
        sb1.reverse();
        sb2.reverse();

        int carryIn = 0;
        for(int i = 0; i < sb2.length(); i++){
            int term1 = sb1.charAt(i) - '0';
            int term2 = sb2.charAt(i) - '0';
            int sum = term1 + term2 + carryIn;
            res = (sum%10) + res;
            carryIn = sum/10;
        }

        for(int i = sb2.length(); i < sb1.length(); i++){
            int term = sb1.charAt(i) - '0';
            int sum = term + carryIn;
            res = (sum%10) + res;
            carryIn = sum/10;
        }

        if(carryIn > 0){
            res = carryIn + res;
        }

        return res;
    }

    public String multiply(String num1, String num2) {
        String small = "";
        String big = "";
        String res = "0";
        if(num1.length() > num2.length()){
            big = num1;
            small = num2;
        }else{
            big = num2;
            small = num1;
        }

        StringBuilder sb1 = new StringBuilder(big);
        StringBuilder sb2 = new StringBuilder(small);
        sb1.reverse();
        sb2.reverse();
        int carryIn = 0;

        for(int i = 0; i < sb2.length(); i++){
            carryIn = 0;
            String ndigit = "";
            int baseDigit = sb2.charAt(i) - '0';
            if(baseDigit == 0){
                continue;
            }


            for(int j = 0; j < sb1.length(); j++){

                int upper = sb1.charAt(j) - '0';
                if(upper == 0){
//                    ndigit = ndigit + "0";
//                    continue;
                }
                int product = baseDigit * upper + carryIn;
//                System.out.println(product);
                int buffer1 = (product%10);
//                if(buffer1 > 10){
//
//                }
                String thisDigit = buffer1 + "";

                System.out.println(thisDigit);
//


                carryIn = product/10;
//                System.out.println(carryIn  + "     " + thisDigit+"\n");
                ndigit = thisDigit + ndigit;
            }
            if(carryIn > 0){
                ndigit = carryIn + ndigit;
            }

            for(int k = 0; k < i; k++){
                ndigit = ndigit + "0";
            }

//            System.out.println(ndigit);
//            System.out.println(ndigit);

            res = add(res,ndigit);

        }


//        if(carryIn > 0){
//            res = carryIn + res;
//        }



        return res;
    }


    public static String longestCommonSubsequence(String s1, String s2){
        //dp[i,j] means:
        String[][] dp = new String[s1.length() + 1][s2.length() + 1];

        for(int i = 0; i <= s1.length(); i++){
            for(int j = 0; j <= s2.length(); j++){
                if(i == 0 || j == 0){
                    dp[i][j] = "";
                }else if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + s1.charAt(i-1);
                }else{
                    dp[i][j] = (dp[i][j-1].length() > dp[i-1][j].length()) ? dp[i][j-1] : dp[i-1][j];
                }
            }
        }


        return dp[s1.length()][s2.length()];

    }


    public static void main(String[] args) {

        Node node = new Node();

        String a = "I love apples. You love bananas, and he loves oranges. How about Jessica? That's kind of weird.";



//        map.put(p1,"asd");
//        System.out.println(map.get(p2));
//        for(Method f : functions){
//            System.out.println(f.getName());
//        }



    }
}