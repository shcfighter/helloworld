import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private Object object = new Object();

    public static void main(String[] args) {

        /*try {
            lock.lock();
            System.out.println("lock before wait");
            condition.await(1000L, TimeUnit.MILLISECONDS);
            System.out.println("lock after wait");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }*/

        Test test = new Test();
        test.park();



    }

    public void park(){
        /*System.out.println("lock before parkNanos");
        LockSupport.parkNanos(10000000000L);
        //LockSupport.park();
        System.out.println("lock after parkNanos");*/

        Thread thread = new Thread(() -> {
            synchronized (object) {
                System.out.println("lock before parkNanos");
                try {
                    object.wait(10000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("lock after parkNanos");
            }
        });
        thread.start();

    }


}