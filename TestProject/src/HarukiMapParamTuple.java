import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HarukiMapParamTuple {
    String name;
    int age;
    int id;
    static int count;

    public HarukiMapParamTuple(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o){

        if(o instanceof HarukiMapParamTuple){
            HarukiMapParamTuple tuple = (HarukiMapParamTuple)o;
            Method[] functions = tuple.getClass().getDeclaredMethods();
            try {

                for(Method p : functions){
                    if(p.getName().startsWith("get")){
                        String v1 = p.invoke(this, null).toString();
                        String v2 = p.invoke(tuple,null).toString();
                        if(!v1.equals(v2)){
                            return false;
                        }
                    }
                }

                return true;

            }catch(Exception e){
                System.out.println(e.getMessage());
                return false;
            }



        }else{
            return super.equals(o);
        }
    }


    @Override
    public int hashCode(){
        return Objects.hash(name, age, id);
    }


}
