package ning.geek.concurrency;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * 一个简单的代码参考：
 * 
 * 方法二：synchronized 
 */
public class Homework01 {
	
	private static int RESULT = 0;
	private static Object OBJ_LOCK = new Object();
    
    public static void main(String[] args) {
        
        long start=System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        Thread sumTead = new Thread(new SumThread());
        // 异步执行 下面方法
        sumTead.start();
        try {
            synchronized (OBJ_LOCK) {
            	OBJ_LOCK.wait();//主线程等待
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        int result = sum(); //这是得到的返回值
        int result = RESULT;
        
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);
         
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        
        // 然后退出main线程
        System.exit(0);
        
    }
    
    /**
     * 
     * 子线程
     * @author zimi
     *
     */
    private static class SumThread implements Runnable{

		@Override
		public void run() {
			RESULT = sum();
			synchronized (OBJ_LOCK) {//获取对象锁
				OBJ_LOCK.notify();//子线程唤醒
		    }
		}
    	
    }
    
    private static int sum() {
        return fibo(36);
    }
    
    private static int fibo(int a) {
        if ( a < 2) 
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
