public class IntRoot {
    int coefficient;
    int root;
    int base;

    public static int power(int base, int power){
        int result = 1;
        for(int i = 0; i < power; i++){
            result *= base;
        }
        return result;
    }

    public void cancellation(){
        int i = 2;
        Rational q = new Rational(i);
        while(power(i,root) <= base/2){
            int factor = power(i,root);
            if(base%factor == 0){
                base /= factor;
                coefficient *= i;
            }
            i++;
        }
    }



    public IntRoot(int coefficient, int root, int base) {
        this.coefficient = coefficient;
        this.root = root;
        this.base = base;
        cancellation();
    }

    public IntRoot(){
        coefficient = 0;
        root = 1;
        base = 1;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public int getRoot() {
        return root;
    }

    public void setRoot(int root) {
        this.root = root;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }


    @Override
    public String toString(){
        String res = "√"+base;
        int len = res.length()-1;

        if(coefficient != 1){
            res = " "+coefficient+res;
        }else{
            res = " "+res;
        }
        int buffer = res.indexOf("√");
        int l2 = (root+"").length();

        buffer -= l2;

        String head = "";
        for(int i = 0; i < buffer+1; i++){
            head += " ";
        }
        head += root;
        for(int i = 0; i < len; i++){
            head += "_";
        }
        head += "\n";

        if(head.contains(" 2_")){
            head = head.replace("2"," ");
        }
        if(head.contains(" 1_")){
            head = "";
            return coefficient*base+"";
        }

        res = head + res;
        return res;
    }

    public IntRoot times(IntRoot r){
        IntRoot result = new IntRoot();
        if(this.root == r.root){
            result.setCoefficient(this.getCoefficient() * r.getCoefficient());
            result.setBase(this.getBase() * r.getBase());
        }else{

        }


        result.cancellation();
        return result;
    }


    public static void main(String[] args){
        IntRoot rt = new IntRoot(2,3,54);
        System.out.println(rt);
    }
}
