package com;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

//How can one ensure that N thread can access N resources without deadlock
//Solution Is--> Key point here is order, 
//that is if one acquires resources in a particular order 
//and release resources in reverse order one can easily prevent deadlock.

public class NThreadNResource {

	public static LinkedBlockingQueue<String> q1 = new LinkedBlockingQueue<>();
	public static LinkedBlockingQueue<String> q2 = new LinkedBlockingQueue<>();
	public static void main(String[] args) {
		MyThread t1 = new MyThread(q1, q2);
		MyThread t2 = new MyThread(q1, q2);
		t1.start();
		t2.start();
	}
	
}

class MyThread extends Thread
{
	LinkedBlockingQueue<String> q1;
	LinkedBlockingQueue<String> q2;
	MyThread(BlockingQueue<String> q1, BlockingQueue<String> q2)
	{
		this.q1=(LinkedBlockingQueue<String>) q1;
		this.q2=(LinkedBlockingQueue<String>) q2;
	}
	
	public void run()
	{
		synchronized (q1) {
			synchronized (q2) {
				System.out.println("Hi This is N Thread N Resource Example"+"name of thread" + Thread.currentThread().getName());
				q2.notify();
			}
			q1.notify();
		}
	}
}