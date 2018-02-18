package Q1;

import java.util.Random;

public class Worker implements Runnable {
	static int threads_num=8;
	Object lock = new Object();
	static int  points=300000000;
	static int k=0;
	int count;
	 
	 @Override
		public  void run() {
			Random random = new Random();
			int c = 0;
			String id=Thread.currentThread().getName();
			int p = points/threads_num;
				if((Thread.currentThread().getId()==threads_num-1) &&
						(points%threads_num!=0))
				p+=points%threads_num;
			
			for(int i=0;i<p;i++){
				double x,y,d;
				x=random.nextDouble();
				y=random.nextDouble();
				x=x*2-1;
				y=y*2-1;
				d=Math.sqrt(x*x+y*y);
				if(d<1)
					c++;
			}
			synchronized(lock){
				k+=c;
			}
			System.out.println(id+" "+"points inside circle "+k+" out of "+points);
			
		}

	public static void main(String[] args) throws InterruptedException {
		Thread[] threads = new Thread[threads_num];
		for (int i = 0; i < threads_num; i++)
		threads[i] = new Thread(new Worker());
		
		for (int i = 0; i < threads_num; i++)
		      threads[i].start();
		
	for (int i = 0; i < threads_num; i++)
		      threads[i].join();	
		    
		    double pi=(double)k/(double)points*4;
		    System.out.println("PI estimation: "+pi);
	}
}
