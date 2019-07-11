public class Rational implements Comparable<Rational>{

    int numerator;
    int denominator;


    public void cancellation(){

        int sign = 1;
        try {
            sign = numerator*denominator;
            if(sign > 0){
                sign = 1;
            }else if(sign < 0){
                sign = -1;
            }else{
                sign = 0;
            }
        }catch(Exception e){

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

    public static void main(String[] atgs){
        Rational q1 = new Rational(14,35);
        Rational q2 = new Rational(41,26);

        System.out.println(q1.add(q2));

        System.out.println(q1.compareTo(q2));
    }
}
