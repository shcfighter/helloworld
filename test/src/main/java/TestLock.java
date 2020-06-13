
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2015-10-23 下午01:47:03 类说明
 */
public class TestLock
{
    // @Test

    public void test() throws Exception
    {
        final Lock lock = new ReentrantLock();
        lock.lock();

        Thread t1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try {
                    while (true) {
                        System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().isInterrupted());
                        TimeUnit.SECONDS.sleep(2);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "child thread -1");

        t1.start();
        Thread.sleep(10000);

        t1.interrupt();
        System.out.println("mian");

        while (true) {
            Thread.sleep(1000);
            System.out.println(t1.isAlive());
        }
    }
    
    public static void main(String[] args) throws Exception
    {
        new TestLock().test();
    }
}