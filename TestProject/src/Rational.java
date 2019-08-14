import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Rational extends Number implements Comparable<Rational>{


    int numerator;
    int denominator;

    public static final Rational ZERO = new Rational(0);
    public static final Rational ONE = new Rational(1,1);

    public void cancellation(){

        int sign = 1;

        if(numerator == 0 || denominator == 0){
            sign = 0;
        }else if(numerator > 0 && denominator > 0){
            sign = 1;
        }else if(numerator < 0 && denominator < 0){
            sign = 1;
        }else{
            sign = -1;
        }

        denominator = Math.abs(denominator);
        numerator = Math.abs(numerator);


        int y = Math.max(numerator, denominator);
        int x = Math.min(numerator, denominator);

        while(x > 0){
            y = y%x;
            int temp = y;
            y = x;
            x = temp;
        }

        numerator/=y;
        denominator/=y;

        numerator *= sign;
//        System.out.println(numerator + "   " + denominator);
    }

    @Override
    public int compareTo(Rational q){
        cancellation();
        q.cancellation();

        Rational diff = this.minus(q);
        diff.cancellation();
        if(diff.numerator > 0){
            return 1;
        }else if(diff.numerator == 0){
            return 0;
        }else{
            return -1;
        }
    }

    public Rational pow(int n){

        Rational res = new Rational(1,1);

        if(n == 0){
            return res;
        }

        if(n > 0){

            for(int i = 0; i < n; i++){
                res = res.times(this);
            }


        }else{
            for(int i = 0; i > n; i--){
                res = res.divide(this);
            }
        }

        res.cancellation();

        return res;
    }

    public Rational times(Rational q){
        cancellation();
        q.cancellation();

        Rational result = new Rational();
        result.setNumerator(this.numerator * q.numerator);
        result.setDenominator(this.denominator * q.denominator);

        result.cancellation();
        return result;
    }

    public Rational add(Rational q){
        Rational result = new Rational();
        cancellation();
        q.cancellation();

        result.setDenominator(this.denominator * q.denominator);
        result.setNumerator(this.numerator * q.denominator + this.denominator * q.numerator);
        result.cancellation();
        return result;
    }

    public Rational inverse(){
        return new Rational(0-this.numerator,this.denominator);
    }

    public Rational reciprocal(){
        return new Rational(this.denominator,this.numerator);
    }

    public Rational minus(Rational q){
        return this.add(q.inverse());
    }

    public Rational divide(Rational q){
        return this.times(q.reciprocal());
    }

    public Rational(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        cancellation();
    }

    public Rational(){
        numerator = 0;
        denominator = 1;
    }

    @Override
    public int intValue() {
        return 0;
    }

    @Override
    public long longValue() {
        return 0;
    }

    @Override
    public float floatValue() {
        return 0;
    }

    @Override
    public double doubleValue() {
        return 0;
    }

    public Rational(int n){
        numerator = n;
        denominator = 1;
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    @Override
    public String toString(){
        if(denominator == 0){
            return null;
        }else if(denominator == 1){
            return numerator+"";
        }else{
            return numerator+"/"+denominator;
        }
    }

    static Map<String,String> map;

    static{
        map = new HashMap<>();
        map.put("asd","111");
        map.put("zxc","222");
    }


    public static String combine(String appkey, String timestamp, String ver, String from, String lattitude, String longtitude){
        String res = SECRET + ("appkey" + appkey +
                "from" + from +
                "gpsTypebaidu" +
                "latitude" + lattitude +
                "longitude" + longtitude +
                "timestamp" + timestamp +
                "udid123456" +
                "ver" + ver + SECRET);
        return res + "\n" + res.substring(0,30);
    }

    public static final String APPKEY = "61000158";
    public static final String TIMESTAMP = "2019-07-24 18:13:00";
    public static final String SECRET = "0031186e-5cc6-45a6-a090-3e88ec220452";
    public static final String FROM = "01012345";
    public static final String VER = "3.4.3";
    public static final String LONGTITUDE = "116.476561";
    public static final String LATITUDE = "40.018707";

    public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        //确定计算方法
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr=base64en.encode(md5.digest(str.getBytes()));
        return newstr;
    }

    public static String getMD5String(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            //一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void testIdleList() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Map<String, String> treeMap = new TreeMap<>();

        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String dateString = ft.format(date) + ":00";

        final String token = "908f6dbf895346f7a581d034d98ecad5";

        String bookingId = "92137ed1a9f55790a5e6ef69f850945c";

        treeMap.put("appkey", "61000158");
        treeMap.put("timestamp", dateString);
        treeMap.put("ver", "3.4.3");
        treeMap.put("from", "01012345");

        treeMap.put("orderId",bookingId);
        treeMap.put("amount","100");
//        treeMap.put("token",token);
//        treeMap.put("pollingCount","1");
//        treeMap.put("startLat","40.02000000000");
//        treeMap.put("endLat","40.017767805898");
//        treeMap.put("startLng","116,4710000000");
//        treeMap.put("endLng","116,47457986168");

//        treeMap.put("driverID","BJ9093");
//        treeMap.put("pageNo","0");
//        treeMap.put("pageSize","10");
        //        treeMap.put("token", "123456");
//        treeMap.put("phone", "13609615024");
//        treeMap.put("contactPhone","13609615024");
//        treeMap.put("address","广东省深圳市南山区科技南路");
//        treeMap.put("gpsType", "baidu");
//        treeMap.put("os","android");
//        treeMap.put("longitude", "113.950093");
//        treeMap.put("latitude", "22.529775");
//        treeMap.put("number","1");
//        treeMap.put("udid", "123456");

        StringBuilder sb = new StringBuilder();
        for (String key : treeMap.keySet()) {
 //           System.out.println(key);
            String value = treeMap.get(key);
            if (!key.equals("gpsstring") && !key.equals("callback") && !key.equals("_") && !key.equals("sig")) {
                sb.append(key);
                sb.append(value);
            }
        }
        String str = sb.toString();
        str = SECRET + str + SECRET;

        System.out.println("before md5:\n" + str);

        String after = getMD5String(str);

        System.out.println(after);
        String sig = after.substring(0,30);
        treeMap.put("sig",sig);

        String base = "http://open.d.api.edaijia.cn/";
        String coupon = "customer/coupon/list?";
        String idleDriver = "driver/idle/list?";
        String url = "order/payNotify?";
        String getToken  =      "customer/getAuthenToken?";


        String params = "";

//        System.out.println(treeMap.keySet());

        int i = 0;
        for(String key : treeMap.keySet()){
            String value = treeMap.get(key);
            if (!key.equals("gpsstring") && !key.equals("callback") && !key.equals("_")) {
                params += key;
                params += "=";
                params += value;
                if(i < treeMap.size() - 1){
                    params += "&";
                }
                i++;

            }
        }

        System.out.println(base+url+params);


//        String newstr = EncoderByMd5(str);
//        System.out.println(newstr);

    }



    public static boolean overwork(int[] workdays){
        boolean result = false;

        int buff1 = 0;
        int i;
        for(i = 0; i < 7; i++){
            buff1 += workdays[i];
        }

        if(buff1 > 3){
            System.out.println("overworked, at day " + (i) + ".");
            return true;
        }

        int buff2 = 0;
        for(i = 7; i < workdays.length; i++){
            buff1 += workdays[i];
            buff2 += workdays[i-7];

            int count = buff1 - buff2;
            if(count > 3){
                System.out.println("overworked, at day " + (i) + ".");
                return true;
            }
        }



        System.out.println("Safe.");
        return result;
    }





    public static Set<String> readAsSet(String path) throws IOException{
        Path address = Paths.get(path);
        List<String> docs = Files.readAllLines(address);
        Set<String> set = new HashSet<>();
        set.addAll(docs);
        return set;
    }


    public static String countSay(int n){
        if(n == 0){
            return "";
        }
        String[] dp = new String[n];
        dp[0] = "1";

        for(int i = 1; i < n; i++){

            String word = dp[i-1] + "#";

            String result = "";
            int count = 1;
            for(int j = 0; j < word.length() - 1; j++){
                if(word.charAt(j) == word.charAt(j+1)){
                    count ++;
                }else{

                    result += count;
                    result += word.charAt(j);

                    count = 1;
                }
            }
            dp[i] = result;


        }

        for(int i = 0; i < n; i++){
            System.out.println((i+1)+": " + dp[i]);
        }

        return dp[n-1];
    }


    public static boolean isAnagram(String s1, String s2){
        List<List<String>> result = new ArrayList<>();
        if(s1.length() != s2.length()){
            return false;
        }

        LinkedList<String> set1 = new LinkedList<>();
        LinkedList<String> set2 = new LinkedList<>();

        for(int i = 0; i < s1.length(); i++){
            set1.add(s1.substring(i,i+1));
            set2.add(s2.substring(i,i+1));
        }

        Collections.sort(set1);
        Collections.sort(set2);



        return set1.equals(set2);

    }





    public List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> result = new ArrayList<>();

        if(strs.length == 0){
            return result;
        }

        Map<String,List> ans = new HashMap<>();
        int[] count = new int[26];

        for(String s : strs){

            Arrays.fill(count,0);

            for(char c : s.toCharArray()){
                count[c - 'a'] ++;
            }

            StringBuilder builder = new StringBuilder("");
            for(int i = 0; i < 26; i++){
                builder.append("#");
                builder.append(count[i]);
            }

            String key = builder.toString();

            if(!ans.containsKey(key)){
                ans.put(key, new ArrayList());
            }

            ans.get(key).add(s);


        }


        return new ArrayList(ans.values());
    }


    public void run(){
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < 100; i++){
            map.put(i,2*i);
        }
    }

    public Map<Integer,Integer> run1(){
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < 100; i++){
            map.put(i,2*i);
        }
        return map;
    }


    public void rotate(int[][] matrix){

        int n = matrix.length;
        for(int i = 0; i < Math.ceil(n/2.0); i++){

            for(int j = 0; j < n/2; j++){
                int e1 = matrix[i][j];
                int e2 = matrix[j][n-1-i];
                int e3 = matrix[n-1-i][n-1-j];
                int e4 = matrix[n-1-j][i];

                matrix[i][j] = e4;
                matrix[n-1-j][i] = e3;
                matrix[j][n-1-i] = e1;
                matrix[n-1-i][n-1-j] = e2;

            }

        }

    }

    public List<List<Integer>> permute(int[] nums) {
        Random ran = new Random();
        if(nums.length == 0){
            return new ArrayList<>();
        }else if(nums.length == 1){
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> first = new ArrayList<>();
            first.add(nums[0]);
            result.add(first);
            return result;

        }else{
            Set<List<Integer>> set = new HashSet<>();
            List<List<Integer>> result = new ArrayList<>();
            int[] prev = new int[nums.length - 1];
            for(int i = 0; i <prev.length; i++){
                prev[i] = nums[i];
            }

            List<List<Integer>> prevlist = permute(prev);

            int num = nums[nums.length-1];
            for(int i = 0; i < prevlist.size(); i++){
                List<Integer> oldpermutation = prevlist.get(i);

                for(int j = 0; j <= prevlist.get(i).size(); j++){
                    List<Integer> old1 = new ArrayList<>();

                    for(int k = 0; k < oldpermutation.size(); k++){
                        old1.add(oldpermutation.get(k));
                    }

                    if(j < old1.size()){
                        old1.add(j,num);
                    }else{
                        old1.add(num);
                    }

                    set.add(old1);

                }
            }
            result.addAll(set);
            return result;
        }

    }

    public void printMatrix(int[][] matrix){
        System.out.println();
        for(int i = 0; i < matrix.length; i++){
            System.out.print("[");
            for(int j = 0; j < matrix[i].length; j++){
                System.out.print(matrix[i][j]);
                if(j < matrix[i].length - 1){
                    System.out.print(", ");
                }
            }
            System.out.print("]\n");
        }
    }


    public int divide(int dividend, int divisor) {
        if(dividend == Integer.MIN_VALUE){
            if(divisor == -1){
                return Integer.MAX_VALUE;
            }else if(divisor == 1){
                return Integer.MIN_VALUE;
            }
        }
        boolean positive = true;
        long dividendLong = dividend;
        long divisorLong = divisor;
        if(dividendLong < 0){
            dividendLong = -dividendLong;
            positive = !positive;
        }

        if(divisorLong < 0){
            divisorLong = -divisorLong;
            positive = !positive;
        }

        int res = divideRecursion(dividendLong,divisorLong,1);
        if(positive){
            return res;
        }else{
            return -res;
        }
    }

    public int divideRecursion(long dividend, long divisor, int count){
        if(dividend <= 0 || count <= 0){
            return 0;
        }
        if(divisor > dividend){
            return divideRecursion(dividend, divisor >>> 1, count >>> 1);
        }else{
            return divideRecursion(dividend - divisor, divisor + divisor, count << 1) + count;
        }

    }


    public List<Integer> findSubstring(String s, String[] words) {

        int count = 0;
        String sentence = s;
        List<Integer> result = new ArrayList<>();
        List<String> wordlist = new ArrayList<>();
        if(s.length() == 0 || words.length == 0){
            return result;
        }

        int size = words[0].length();
        for(int i = 0; i < words.length; i++){
            wordlist.add(words[i]);
        }

        while(sentence.length() >= size){
            List<String> wordset = new ArrayList<>();

            wordset.addAll(wordlist);

            String first = sentence.substring(0,size);
            int cur = count;


            while(wordset.contains(first)){
                System.out.println("contains " + first + "    at " + cur);
                sentence = sentence.substring(size);
                wordset.remove(first);
                count += size;
                if(sentence.length() < size){
                    break;
                }
                first = sentence.substring(0,size);

            }

            System.out.println(wordset);
            if(wordset.isEmpty()){
                result.add(cur);
            }else{
                wordset.clear();
                wordset.addAll(wordlist);
                if(sentence.length() >= 1){
                    sentence = sentence.substring(1);
                    count++;
                }else{
                    break;
                }
            }

        }

        return result;
    }


    public int[] searchRange(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;
        int mid = (left + right)/2;
        while(left < right){
            mid = (left + right)/2;
            if(nums[mid] <= target){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        System.out.println(mid + "   " + nums[mid]);

        return null;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Rational){
            Rational r = this.minus((Rational)o);
            r.cancellation();
            if(r.getNumerator() == 0){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }




    public static void main(String[] args) throws IOException {

        Rational a = new Rational(124206,124206);


        System.out.println(a);



    }
}
