package com.mei;

//����Ļ�������
class Book {
	private String title;
	private double price;
	public Book(String title,double price) {
		this.title=title;
		this.price=price;
	}
	public String toString() {
		return "ͼ�����ƣ�"+this.title+"ͼ��۸�"+this.price;
	}
	
	//����Ƚ�
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

class Link {   //�����࣬�ⲿ�ܹ�����
	
	//�������ڲ���Ϊ������ΪLink�����
	class Node {          //����Ľڵ���
		private Book data;     //��������
		private Node next;        //���ù�ϵ
		public Node(Book data) {
			this.data=data;
		}
		public void addNode(Node newNode) {
			if(this.next==null) {            //��ǰ����һ���ڵ�Ϊ��
				this.next=newNode;
			} else {                     //����������
				this.next.addNode(newNode);
			}
		}
		//��һ�ε��ã�Link����this=Link.root
		//�ڶ��ε��ã�Node����this=Link.root.next
		public boolean containsNode(Book data) { 
			if(data.compare(this.data)) {         //��ǰ�ڵ�����ΪҪ��ѯ������
				return true;                      //���治�ٲ�ѯ��
			} else {
				if(this.next!=null) {        //�к����ڵ�
					return this.next.containsNode(data);
				} else {              //û�к����ڵ���
					return false;         //û���ܲ����
				}
			}
		}
		
		public Book getNode(int index) {
			//ʹ�õ�ǰ��foot������Ҫ��ѯ���������бȽ�
			//���foot������������Ŀ����Ϊ���´β�ѯ����
			if(Link.this.foot ++ ==index) {       //����ΪҪ��ѯ�Ķ���
				return this.data;              //���ص�ǰ�ڵ�����
			} else {                          //�����������ѯ
				return this.next.getNode(index);
			}
		}
		
		public void setNode(int index,Book data) {
			if(Link.this.foot++==index) {
				this.data=data;        //�������ݵ�����
			} else {
				this.next.setNode(index, data);
			}
		}
		
		//��һ�ε��ã�Link����previous=Link.root��this=Link.root.next
		//�ڶ��ε��ã�Node����previous=Link.root.next,this=Link.root.next.next
		//Ҫ������һ���ڵ��Լ�Ҫɾ��������
		public void removeNode(Node previous,Book data) {
			if(data.compare(this.data)) {       //��ǰ�ڵ�ΪҪɾ���ڵ�
				previous.next=this.next;         //�ճ���ǰ�ڵ�
			} else {                      //������ǡ���Ӧ����������ѯ
				this.next.removeNode(this,data);
			}
		}
		
		//��һ�ε��ã�Link��,this=Link.root
		//�ڶ��ε��ã�Node����this=Link.root.next
		public void toArrayNode() {
			Link.this.retArray[Link.this.foot++]=this.data; 
			if(this.next!=null) {           //�к���Ԫ��
				this.next.toArrayNode();
			}
		}
	}
	
	
	//=======================����Ϊ�ڲ���=========================
	private Node root;     //���ڵ�
	private int count=0;   //��¼�ڵ����
	private int foot=0;      //Node�ı��
	private Book [] retArray;
	
	//��������
	public void add(Book data) {
		if(data==null) {              //���費������null
			return ;
		}
		Node newNode=new Node(data);       //Ҫ���������
		if(this.root==null) {           //��ǰû�и��ڵ�
			this.root=newNode;          //���½ڵ㱣��Ϊ���ڵ�
		}else {                        //���ڵ���ڣ������ڵ㽻��Node�ദ��
			this.root.addNode(newNode);
		}
		this.count++;           //ÿһ�α�����ɺ���������1
	}
	
	//ȡ�������б����������
	public int size() {
		return this.count;
	}
	
	//�ж��Ƿ��ǿ�����
	public boolean isEmpty() {
		return this.count==0;
	}
	
	//��������ȡ������
	public Book get(int index) {
		if(index>this.count) {         //�����˲�ѯ��Χ
			return null;                //û������
		}
		this.foot=0;         //��ʼ�飬��ʾ��ǰ����ѯ
		return this.root.getNode(index);         //��Ĺ��̽���Node�ദ��
	}
	
	//�������ݲ�ѯ���ж�ĳһ�������Ƿ����
	public boolean contains(Book data) {
		//����û��Ҫ��ѯ�����ݣ����ڵ�Ҳ����������
		if(data==null||this.root==null) {
			return false;          //û�в�ѯ���
		}
		return this.root.containsNode(data);  //����в�ѯ���ݣ����ѯ���̽���Nodeȥ�����Ӹ�Ԫ�ؿ�ʼ��
	}
	
	//ʹ���µ������滻��ָ�������ľɵ�����
	public void set(int index,Book data) {
		if(index>this.count) {
			return ;       //������������
		}
		this.foot=0;   //��������foot���Ե�����
		this.root.setNode(index,data);     //����Node��������������
	}
	
	//������ת�����������
	public Book [] toArray() {
		if(this.root==null) {
			return null;
		}
		this.foot=0;          //��Ҫ�ű����
		this.retArray=new Book [this.count];
		this.root.toArrayNode();      //����Node�ദ��
		return this.retArray;
	}
	
	//ɾ��ָ������
	public void remove(Book data) {
		if(this.contains(data)) {          //�ж������Ƿ����
			//�ж�Ҫɾ�������Ƿ��Ǹ��ڵ�����
			//root��Node����󣬴˴�ֱ�ӷ����ڲ����˽�в���
			if(data.compare(this.root.data)) {     //rootΪҪɾ���Ľڵ�
				 this.root=this.root.next;     //�ճ���ǰ���ڵ�
			} else {                         //���Ǹ�Ԫ��
				//��ʱ��Ԫ���Ѿ��жϹ��ˣ��ӵڶ���Ԫ�ؿ�ʼ�ж�
				this.root.next.removeNode(this.root,data);
			}
		}
	}
}

public class LinkTest {
	public static void main(String [] args) {
		Link all=new Link();
		System.out.println(all.isEmpty());
		all.add(new Book("Java����",20));
		all.add(new Book("C++",50));
		all.add(new Book("���˼��",90));
		Book [] data=all.toArray();
		for(int i=0;i<data.length;i++) {
			System.out.println(data[i]);
		}
	}
}
