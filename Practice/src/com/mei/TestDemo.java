package com.mei;

//生产者消费者模式
class Info {
	private String title;
	private String content;
	private boolean flag;
	//flag=true表示可以生产，但是不可以取走
	//flag=false表示可以取走，但是不可以生产
	public synchronized void set(String title,String content) {
		//重复进入到set()方法里，发现不能生产，所以要等待
		if(flag==false) {
			try {
				super.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.title=title;
		try {
			Thread.sleep(200);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		this.content=content;
		this.flag=false;            //修改生产标记
		super.notify();             //唤醒其他等待线程
	}
	public synchronized void get() {
		if(this.flag==true) {             //说明还没生产
			try {
				super.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(100);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(this.title+"----"+this.content);
		this.flag=true;
		super.notify();
	}
	
}

class Productor implements Runnable {
	private Info info;
	public Productor(Info info) {
		this.info=info;
	}
	
	public void run() {
		for(int i=0;i<100;i++) {
			if(i%2==0) {
				this.info.set("王","好学生");
			} else {
				this.info.set("萌萌哒","草泥马");
			}
		}
	}
}

class Customer implements Runnable{
	private Info info;
	public Customer(Info info){
		this.info=info;
	}
	public void run() {
		for(int i=0;i<100;i++) {
			this.info.get();
		}
	}
}

public class TestDemo {         //主类
	public static void main(String[] args) throws Exception{
		Info info=new Info();
		new Thread(new Productor(info)).start();
		new Thread(new Productor(info)).start();
		new Thread(new Customer(info)).start();
	}
}
