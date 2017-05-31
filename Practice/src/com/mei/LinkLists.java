package com.mei;

class Nodees {
	public int val;
	public Nodees next;
	public Nodees(int val) {
		this.val=val;
	}
}

public class LinkLists {
	//��������ĳ���
	public static int getLen(Nodees head) {
		if(head==null) return 0;
		int count=0;
		while(head.next!=null) {
			count++;
			head=head.next;
		}
		return count;
	}
	
	//��ӡ������
	public static void print(Nodees head) {
		if(head==null) return ;
		Nodees p=head.next;
		while(p!=null) {
			if(p.next!=null) 
				System.out.println(p.val+"-->");
			else 
				System.out.println(p.val+" ");
			p=p.next;
		}
	}
	
	//ͷ�巨����������
	public static Nodees initHead(int [] arr,int len) {
		if(len<=0) return null;
		Nodees head=new Nodees(0);
		Nodees temp;
		for(int i=0;i<len;i++) {
			temp=new Nodees(arr[i]);
			if(head==null) 
				head.next=temp;
			else {
				temp.next=head.next;
				head.next=temp;
			}
		}
		return head;
	}
	
	//β�巨����������
	public static Nodees initTail(int [] arr,int len) {
		if(len<=0) return null;
		Nodees head=new Nodees(0);
		Nodees tail=head;
		Nodees temp;
		for(int i=0;i<len;i++) {
			temp=new Nodees(arr[i]);
			if(head==null) {
				tail=temp;
				head.next=temp;
			} else {
				tail.next=temp;
				tail=temp;
			}
		}
		return head;
	}
	
	//������Ų��ҽڵ�
	public static Nodees getElem(Nodees head,int i) {
		int j=1;
		Nodees p=head.next;
		if(i==0) return head;
		if(i<1) return null;
		while(p!=null&&j<i) {
			p=p.next;
			j++;
		}
		return p;
	}
	
	//��ֵ���ҽڵ�
	public static Nodees locateElem(Nodees head,int val) {
		Nodees p=head.next;
		while(p!=null&&p.val!=val) {
			p=p.next;
		}
		return p;
	}
	
	//����ڵ����
	public static void insert(Nodees head,int i,int val) {
		Nodees p=getElem(head,i-1);
		Nodees temp=new Nodees(val);
		if(head==null) {
			head.next=temp;
		} else {
			temp.next=p.next;
			p.next=temp;
		}
	}
	
	//ɾ���ڵ�Ĳ���
	public static void delete(Nodees head,int i) {
		Nodees p=getElem(head,i-1);
		Nodees q;
		if(head==null) return ;
		else {
			q=p.next;
			p.next=q.next;
		    q=null;
		}
	}
	
	public static void main(String [] args) {
		
	}
}
