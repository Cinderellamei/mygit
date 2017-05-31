package com.mei;

//头插法创建单链表
import java.util.Scanner;

class Node {
	int data;
	Node next;
	Node (int data) {
		this.data=data;
		this.next=null;
	}
}

public class LinkList {
	
	//采用头插法向链表插入数据
		public static Node insert (Node head,int data) {
			//System.out.println(head);
			Node n=new Node(data);
			n.next=head;
			head=n;
			return head;
		}
		
		//打印链表的data
		public static void print(Node head) {
			Node start=head;
			System.out.println("dd");
			while(start!=null) {
				System.out.println("dd");
				System.out.println(start.data+" ");
				start=start.next;
			}
		}
	public static void main(String [] args) {
		System.out.println("nihao");
		Scanner sc=new Scanner(System.in);
		Node head=null;
		int N=sc.nextInt();   //输入链表的长度
		System.out.println(N);
		
		//逐个输入链表元素
		while(N>0) {
			System.out.println(N);
			int ele=sc.nextInt();
			System.out.println(ele);
	     	head=LinkList.insert(head,ele);
			--N;
		}
		System.out.println(head);
		LinkList.print(head);
		sc.close();
	}
}
