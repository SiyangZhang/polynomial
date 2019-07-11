import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Term {
    int coefficient;
    int power;


    public Term(int coefficient, int power) {
        this.coefficient = coefficient;
        this.power = power;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }



    @Override
    public String toString(){
        String res = "";

        if(coefficient == 0){
            return "";
        }

        if(power == 0){
            return coefficient+"";
        }

        if(this.coefficient != 1 && this.coefficient != -1){
            res += coefficient;
        }
        if(this.coefficient == -1){
            res += "-";
        }
        res += "X";
        if(power == 0){
            return res;
        }
        if(power != 1){
            res += "^"+power;
        }

        return res;

    }

    public Term times(Term another){
        Term t = new Term(coefficient*another.coefficient,power+another.power);
        return t;
    }


    public Polynomial add(Term another){
        Map<Integer, Integer> pow_coe = new HashMap<>();
        Polynomial poly = new Polynomial(pow_coe);
        poly = poly.add(this);
        poly = poly.add(another);
        return poly;
    }


    public Polynomial add(Polynomial poly){
        return poly.add(this);
    }

    public Polynomial times(Polynomial poly){
        Map<Integer,Integer> map = new HashMap<>();
        for(int pow : poly.pow_coe.keySet()){
            int coe = poly.pow_coe.get(pow);
            Term t = new Term(coe,pow);
            t = this.times(t);
            map.put(t.getPower(),t.getCoefficient());
        }
        return new Polynomial(map);
    }

    public static void main(String[] args){




    }
}
