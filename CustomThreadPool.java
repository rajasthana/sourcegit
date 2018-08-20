package com;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class CustomThreadPool {

	public static void main(String[] args) {
		ThreadPool tp = new ThreadPool(5);
		for(int i =0;i<10;i++)
		{
			Task t = new Task(i+"");
			tp.execute(t);
		}
	}
}

class Task implements Runnable
{

	private String name;
	
	public Task(String name) {
		// TODO Auto-generated constructor stub
		this.name=name;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Worker Thread Executed " + Thread.currentThread().getName() +" name " + name);
	}
	
}

class ThreadPool{
	
	private int noOfThreads;
	private WorkerThread[] worker;
	private LinkedBlockingQueue<Task> queue;
	
	public ThreadPool(int noOfThreads) {
		// TODO Auto-generated constructor stub
		this.noOfThreads = noOfThreads;
		worker = new WorkerThread[noOfThreads];
		queue = new LinkedBlockingQueue<>();
		
		for(int i=0;i<noOfThreads;i++)
		{
			worker[i] = new WorkerThread();
			worker[i].start();
		}
	}
	
	
	private class WorkerThread extends Thread
	{
		Runnable task;
		public void run()
		{
			while(true)
			{
				synchronized (queue) {
					while(queue.isEmpty())
					{
						try {
							queue.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					task=queue.poll();
				}
				task.run();
			}
		}
	}
	
	public void execute(Runnable task)
	{
		synchronized (queue) {
			queue.add((Task) task);
			queue.notify();
		}
	}
}