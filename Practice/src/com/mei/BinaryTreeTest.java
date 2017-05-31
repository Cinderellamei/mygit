package com.mei;

import java.util.Stack;

class Bnode {
	int value;
	Bnode leftChild;
	Bnode rightChild;
	
	Bnode(int value) {
		this.value=value;
	}
	
	public void print() {
		System.out.println(this.value+"\t");
	}
	
	public String toString() {
		return String.valueOf(value);
	}
}

class BinaryTree {
	private Bnode root;
	
	BinaryTree(int value) {
		this.root=new Bnode(value);
		this.root.leftChild=null;
		this.root.rightChild=null;
	}
	
	//����ֵ���в���
	public Bnode findByKey(int value) {
		Bnode current=root;
		while(true) {
			if(value==current.value) {
				return current;
			} else if(value<current.value) {
				current=current.leftChild;
			} else if(value>current.value) {
				current=current.rightChild;
			}
			
			if(current==null) {
				return null;
			}
		}
	}
	
	//��������
	public String insert(int value) {
		String error=null;
		Bnode node=new Bnode(value);
		
		if(root==null) {
			root=node;
			root.leftChild=null;
			root.rightChild=null;
		} else {
			Bnode current=root;
			Bnode parent=null;
			while(true) {
				if(value<current.value) {
					parent=current;
					current=current.leftChild;
					if(current==null) {
						parent.leftChild=node;
						break;
					}
				} else if(value>current.value) {
					parent=current;
					current=current.rightChild;
					if(current==null) {
						parent.rightChild=node;
						break;
					}		
				} else {
					error="having same value in binary tree";
				}
			}
		}      return error;
	}
	
	//�������(�ݹ�)
	public void inOrderTraverse() {
		System.out.println("�������");
		inOrderTraverse(root);
		System.out.println();
	}
	
	private void inOrderTraverse(Bnode node) {
		if(node==null) return ;
		inOrderTraverse(node.leftChild);
		node.print();
		inOrderTraverse(node.rightChild);
	}
	
	//�������(�ǵݹ�)
	public void inOrderByStack() {
		System.out.println("�ǵݹ��������");
		Stack <Bnode> stack=new Stack<Bnode>();
	}
}

public class BinaryTreeTest {
	public static void main(String[] args) {
		
	}
}
