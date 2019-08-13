import java.util.concurrent.*;

public class MyThread implements Callable<String> {



    String v1;

    public MyThread(String str){
        v1 = str;
    }



    @Override
    public String call() throws Exception{
//        System.out.println(Thread.currentThread().getName());
        for(int i = 0; i < 10; i++){
            v1 += "#";
        }
        return this.v1;
    }


    public static void main(String[] args) throws InterruptedException{

        ExecutorService pool = Executors.newCachedThreadPool();

        MyThread task = new MyThread("value");

        Future<String> future = pool.submit(task);
        while(future.isDone()){

        }

        try {
            System.out.println(future.get());

        }catch (Exception e){

        }

    }
}
