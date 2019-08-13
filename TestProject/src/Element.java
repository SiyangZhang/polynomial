public class Element<T> {
    T data;

    public Element(T data){
        this.data = data;
    }

    public T getData(){
        return data;
    }

    public void setData(T data){
        this.data = data;
    }

    @Override
    public String toString(){
        return data.toString();
    }


    public Class<?> getDataClass(){
        return data.getClass();
    }


}
