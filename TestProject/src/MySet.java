import java.util.HashSet;
import java.util.Set;

public class MySet<T> extends HashSet<T> {

    public MySet<MySet<T>> powerSet(){
        MySet<MySet<T>> powerset = new MySet<>();

        if(this.isEmpty()){
            powerset = new MySet<>();
            powerset.add(new MySet<>());
            return powerset;
        }else if(this.size() == 1){
            powerset = new MySet<>();
            powerset.add(new MySet<>());
            powerset.add(this);
            return powerset;
        }else{
            T element = null;
            for(T e : this){
                element = e;
                break;
            }

            powerset = new MySet<>();
            powerset.add(new MySet<>());
//            powerset.add(this);

            this.remove(element);

            MySet<MySet<T>> smallpower = this.powerSet();



            for(MySet<T> set : smallpower){
                powerset.add(set);
                MySet<T> set2 = set.copy();
                set2.add(element);
                powerset.add(set2);
            }

            return powerset;
        }

    }

    public MySet<T> copy(){
        MySet<T> set = new MySet<>();
        for(T data : this){
            set.add(data);
        }

        return set;
    }


    public static void main(String[] args){
        MySet<String> set = new MySet<>();
//        set.add("1");
//        set.add("2");
//        set.add("3");
//        set.add("4");

//        MySet<MySet<String>> power = set.powerSet();

        MySet power = new MySet();
        power = power.powerSet().powerSet().powerSet().powerSet().powerSet();
        System.out.println("\n" + power.size());



    }
}
