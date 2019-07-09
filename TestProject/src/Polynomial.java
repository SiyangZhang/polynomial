import java.util.*;

public class Polynomial {


    Map<Integer,Integer> pow_coe;

    public Polynomial(Map<Integer,Integer> pow_coe) {
        this.pow_coe = pow_coe;
    }

    @Override
    public String toString(){
        String result = "";
        ArrayList<Integer> powers = new ArrayList<>();
        for(int pow : pow_coe.keySet()){
            powers.add(pow);
        }

        Collections.sort(powers);


        for(int i = powers.size() - 1; i >= 0; i--){
            int pow = powers.get(i);
            int coe = pow_coe.get(pow);
            Term t = new Term(coe,pow);
            if(coe < 0){
                result += t.toString();
            }else if(coe > 0){
                result += "+"+t.toString();

            }
        }
        if(result.charAt(0)=='+'){
            result = result.substring(1);
        }
        return result;

    }

    public Polynomial add(Term another){
        if(this.pow_coe.containsKey(another.getPower())){
            int pow = another.getPower();
            int coe = another.getCoefficient() + pow_coe.get(pow);
            this.pow_coe.put(pow,coe);
            return this;
        }else{
            this.pow_coe.put(another.getPower(),another.getCoefficient());
            return this;
        }
    }

    public Polynomial add(Polynomial poly){
        for(int pow  : poly.pow_coe.keySet()){
            Term t = new Term(poly.pow_coe.get(pow),pow);
            this.add(t);
        }
        return this;
    }

    public Polynomial times(Term t){
        return t.times(this);
    }

    public Polynomial times(Polynomial poly){
        Map<Integer,Integer> map = new HashMap<>();
        Polynomial product = new Polynomial(map);
        for(int thispow : this.pow_coe.keySet()){
            int thiscoe = this.pow_coe.get(thispow);
            Term t = new Term(thiscoe,thispow);
            product.add(t.times(poly));
        }

        return product;

    }

    public static void main(String[] args){
        Term t0 = new Term(1,0);
        Term t1 = new Term(-1,1);

        System.out.println(t1);

        Polynomial p1 = t0.add(t1);

        for(int i = 0; i < 5; i++){
            System.out.println(p1);
            p1 = p1.times(p1);
        }

    }

}
