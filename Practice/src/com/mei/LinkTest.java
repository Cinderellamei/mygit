package com.mei;

//链表的基本操作
class Book {
	private String title;
	private double price;
	public Book(String title,double price) {
		this.title=title;
		this.price=price;
	}
	public String toString() {
		return "图书名称："+this.title+"图书价格："+this.price;
	}
	
	//对象比较
	public boolean compare(Book book) {
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
}

class Link {   //链表类，外部能够看见
	
	//定义在内部是为了让其为Link类服务
	class Node {          //定义的节点类
		private Book data;     //保存数据
		private Node next;        //引用关系
		public Node(Book data) {
			this.data=data;
		}
		public void addNode(Node newNode) {
			if(this.next==null) {            //当前的下一个节点为空
				this.next=newNode;
			} else {                     //向后继续保存
				this.next.addNode(newNode);
			}
		}
		//第一次调用（Link）：this=Link.root
		//第二次调用（Node）：this=Link.root.next
		public boolean containsNode(Book data) { 
			if(data.compare(this.data)) {         //当前节点数据为要查询的数据
				return true;                      //后面不再查询了
			} else {
				if(this.next!=null) {        //有后续节点
					return this.next.containsNode(data);
				} else {              //没有后续节点了
					return false;         //没有能查的了
				}
			}
		}
		
		public Book getNode(int index) {
			//使用当前的foot内容与要查询的索引进行比较
			//随后将foot的内容自增，目的是为了下次查询方便
			if(Link.this.foot ++ ==index) {       //索引为要查询的对象
				return this.data;              //返回当前节点数据
			} else {                          //否则继续向后查询
				return this.next.getNode(index);
			}
		}
		
		public void setNode(int index,Book data) {
			if(Link.this.foot++==index) {
				this.data=data;        //进行内容的设置
			} else {
				this.next.setNode(index, data);
			}
		}
		
		//第一次调用（Link），previous=Link.root。this=Link.root.next
		//第二次调用（Node），previous=Link.root.next,this=Link.root.next.next
		//要传递上一个节点以及要删除的数据
		public void removeNode(Node previous,Book data) {
			if(data.compare(this.data)) {       //当前节点为要删除节点
				previous.next=this.next;         //空出当前节点
			} else {                      //如果不是。则应该向后继续查询
				this.next.removeNode(this,data);
			}
		}
		
		//第一次调用（Link）,this=Link.root
		//第二次调用（Node），this=Link.root.next
		public void toArrayNode() {
			Link.this.retArray[Link.this.foot++]=this.data; 
			if(this.next!=null) {           //有后续元素
				this.next.toArrayNode();
			}
		}
	}
	
	
	//=======================以上为内部类=========================
	private Node root;     //根节点
	private int count=0;   //记录节点个数
	private int foot=0;      //Node的编号
	private Book [] retArray;
	
	//增加数据
	public void add(Book data) {
		if(data==null) {              //假设不允许有null
			return ;
		}
		Node newNode=new Node(data);       //要保存的数据
		if(this.root==null) {           //当前没有根节点
			this.root=newNode;          //将新节点保存为根节点
		}else {                        //根节点存在，其他节点交给Node类处理
			this.root.addNode(newNode);
		}
		this.count++;           //每一次保存完成后数据量加1
	}
	
	//取得链表中保存的数据量
	public int size() {
		return this.count;
	}
	
	//判断是否是空链表
	public boolean isEmpty() {
		return this.count==0;
	}
	
	//根据索引取得数据
	public Book get(int index) {
		if(index>this.count) {         //超过了查询范围
			return null;                //没有数据
		}
		this.foot=0;         //开始查，表示从前向后查询
		return this.root.getNode(index);         //查的过程交给Node类处理
	}
	
	//进行数据查询，判断某一个数据是否存在
	public boolean contains(Book data) {
		//现在没有要查询的数据，根节点也不保存数据
		if(data==null||this.root==null) {
			return false;          //没有查询结果
		}
		return this.root.containsNode(data);  //如果有查询数据，则查询过程交给Node去做，从根元素开始查
	}
	
	//使用新的内容替换掉指定索引的旧的内容
	public void set(int index,Book data) {
		if(index>this.count) {
			return ;       //结束方法调用
		}
		this.foot=0;   //重新设置foot属性的内容
		this.root.setNode(index,data);     //交给Node类设置数据内容
	}
	
	//将链表转换成数组对象
	public Book [] toArray() {
		if(this.root==null) {
			return null;
		}
		this.foot=0;          //需要脚标控制
		this.retArray=new Book [this.count];
		this.root.toArrayNode();      //交给Node类处理
		return this.retArray;
	}
	
	//删除指定数据
	public void remove(Book data) {
		if(this.contains(data)) {          //判断数据是否存在
			//判断要删除数据是否是根节点数据
			//root是Node类对象，此处直接访问内部类的私有操作
			if(data.compare(this.root.data)) {     //root为要删除的节点
				 this.root=this.root.next;     //空出当前根节点
			} else {                         //不是根元素
				//此时根元素已经判断过了，从第二个元素开始判断
				this.root.next.removeNode(this.root,data);
			}
		}
	}
}

public class LinkTest {
	public static void main(String [] args) {
		Link all=new Link();
		System.out.println(all.isEmpty());
		all.add(new Book("Java开发",20));
		all.add(new Book("C++",50));
		all.add(new Book("编程思想",90));
		Book [] data=all.toArray();
		for(int i=0;i<data.length;i++) {
			System.out.println(data[i]);
		}
	}
}
