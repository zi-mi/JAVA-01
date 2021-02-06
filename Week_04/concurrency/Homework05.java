package ning.geek.concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * 一个简单的代码参考：
 * 
 * 方法六：BlockingQueue
 */
public class Homework05 {
	
	static BlockingQueue BLOCKING_QUEUE = new ArrayBlockingQueue(1);//数组型队列，长度为1
	
    public static void main(String[] args) {
    	
        long start=System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        Thread sumThread = new Thread(new SumThread());
        // 异步执行 下面方法
        sumThread.start();
        
        //等待子线程执行完成
        int result = 0;
        try {
        	result = (int) BLOCKING_QUEUE.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//        int result = sum(); //这是得到的返回值
        
        
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
			try {
				BLOCKING_QUEUE.put(sum());
			} catch (InterruptedException e) {
				e.printStackTrace();
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
