package com.mei;

//������������ģʽ
class Info {
	private String title;
	private String content;
	private boolean flag;
	//flag=true��ʾ�������������ǲ�����ȡ��
	//flag=false��ʾ����ȡ�ߣ����ǲ���������
	public synchronized void set(String title,String content) {
		//�ظ����뵽set()��������ֲ�������������Ҫ�ȴ�
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
		this.flag=false;            //�޸��������
		super.notify();             //���������ȴ��߳�
	}
	public synchronized void get() {
		if(this.flag==true) {             //˵����û����
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
				this.info.set("��","��ѧ��");
			} else {
				this.info.set("������","������");
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

public class TestDemo {         //����
	public static void main(String[] args) throws Exception{
		Info info=new Info();
		new Thread(new Productor(info)).start();
		new Thread(new Productor(info)).start();
		new Thread(new Customer(info)).start();
	}
}
