package com.mei;

class Books {
	private String title;
	private double price;
	public Books(String title,double price) {
		this.title=title;
		this.price=price;
	}
	public boolean compare(Books book) {
		if(this==book) {
			return true;
		}
		if(book==null) {
			return false;
		}
		if(this.title.equals(book.title)&&this.price==book.price) {
			return true;
		}
		return false;
	}
	
	public String toString(String title,double price) {
		return this.title+this.price;
	}
}

class Links {              //Links类只负责根节点
	class Node {
		private Books data;
		private Node next;
		public Node(Books data) {
			this.data=data;
		}
		
		public void addNode(Node newNode) {
			if(this.next==null) {
				this.next=newNode;
			} else {
				this.next.addNode(newNode);
			}
		}
		
		public boolean containsNode(Books data) {
			if(data.compare(this.data)) {
				return true;
			} else {
				if(this.next!=null) {
					return this.next.containsNode(data);
				} else { 
					return false;
				}
			}
		}
		
		public Books getNode(int index) {
			if(Links.this.foot++==index) {
				return this.data;
			} else {
			return this.next.getNode(index);
			}
		}
		
		public void setNode(int index,Books data) {
			if(Links.this.foot++==index) {
				this.data=data;
			} else {
				this.next.setNode(index,data);
			}
		}
		
		public void removeNode(Node previous,Books data) {
			if(data.compare(this.data)) {
				previous.next=this.next;
			} else {
				this.next.removeNode(this, data);
			}
		}
		
		public void toArrayNode() {
			Links.this.retArray[Links.this.foot++]=this.data;
			if(this.next!=null) {
				this.next.toArrayNode();
			}
		}
	}
	//============以上是内部类
	private Node root;
	private int count=0;
	private int foot=0;
	private Books [] retArray;
	
	public void add(Books data) {
		if(data==null) {
			return;
		}
		Node newNode=new Node(data);
		if(this.root==null) {
			this.root=newNode;
		} else {
			this.root.addNode(newNode);
		}
		count++;
	}
	
	public int size() {
		return this.count;
	}
	
	public boolean isEmpty() {
		return this.count==0;
	} 
	
	public boolean contains(Books data) {
		if(data==null||this.root==null) {
			return false;
		} else {
			return this.root.containsNode(data);
		}
	}
	
	public Books get(int index) {
		if(index>this.count) {
			return null;
		}
		this.foot=0;
		return this.root.getNode(index);
	}
	
	public void set(int index,Books data) {
		if(index>this.count) {
			return ;
		} 
		this.foot=0;
		this.root.setNode(index,data);
	}
	
	public void remove(Books data) {
		if(this.contains(data)) {
			if(data.compare(this.root.data)) {
				this.root=this.root.next;
			} else {
				this.root.next.removeNode(this.root,data);
			}
		}
	}
	
	public Books [] toArray() {
		if(this.root==null) {
			return null;
		} 
		this.foot=0;
		this.retArray=new Books[this.count];
		this.root.toArrayNode();
		return retArray;
	}
}

public class Test {
	public static void main(String [] args) {
		Links all=new Links();
		System.out.println(all.isEmpty());
		all.add(new Books("java",10));
		all.add(new Books("kkk",98));
		all.add(new Books("khg",20));
		Books [] books=all.toArray();
		for(int i=0;i<books.length;i++) {
			System.out.println(books[i].toString());
		}
	}
}
