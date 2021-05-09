
public class BST {
	
	private Node root;
	
	private static class Node{
		int element;
		Node leftChild;
		Node rightChild;
		
		Node(int element){
			this.element = element;
			leftChild = null;
			rightChild = null;
		}
	}
	
	public BST() {
		this.root = null;
	}
	
	public boolean add(int element) {
		if(root == null)
			root = new Node(element);
		
		if(contains(element)) {
			return false;
		}
		else {
			add(this.root, element);
		}
		
		return true;
	}
	
	private void add(Node current, int element) {
		
		if(element < current.element) {
			if(current.leftChild == null) {
				Node newest = new Node(element);
				current.leftChild = newest;
			}
			else {
				add(current.leftChild, element);
			}
		}
		else{
			if(current.rightChild == null) {
				Node newest = new Node(element);
				current.rightChild = newest;
			}
			else {
				add(current.rightChild, element);
			}
		}
	}
	
	public boolean remove(int elementToRemove) {
		if(!contains(elementToRemove)) {
			return false;
		}
		this.root = remove(this.root, elementToRemove);
		return true;
	}
	
	private Node remove(Node current, int removeNode) {
		if(current == null) {
			return current;
		}
		if(removeNode < current.element) {
			current.leftChild = remove(current.leftChild, removeNode);
		}
		else if(removeNode > current.element) {
			current.rightChild = remove(current.rightChild, removeNode);
		}
		else { //we have found the element to remove
			//case where node has only 1 child
			if(current.rightChild == null) {
				return current.leftChild;
			}
			else if(current.leftChild == null) {
				return current.rightChild;
			}
			else {	//Node has two children
				Node successor  = inorderSuccessor(current.rightChild); //finds the smallest value in the node's right subtree
				current.element = successor.element;
				current.rightChild = remove(current.rightChild, successor.element);
			}
		}
		return current;
	}
	
	private Node inorderSuccessor(Node current) {
		while (current != null && current.leftChild != null) { //finds the min node
			current = current.leftChild;
	     }
		 return current;
	}

	public boolean contains(int element) {
		if(root == null)
			return false;
		
		Node current = this.root;
		while(current != null) {
			if(element < current.element) {
				current = current.leftChild;
			}
			else if(element > current.element) {
				current = current.rightChild;
			}
			else {
				return true;
			}
		}
		return false;
	}
	
	public void printInorder() {
		inorder(this.root);
	}
	private void inorder(Node root)
	{
		if (root == null) {
			System.out.println("");
			return;
		}

		inorder(root.leftChild);
		System.out.println("Node: " + root.element + " CODE: " + root);
		System.out.println("Node: " + root.element +  " Node left: " + root.leftChild + " Node right: " + root.rightChild);
		inorder(root.rightChild);
	}
	
	public static void main(String[] args) {
		BST tree = new BST();
		tree.add(5);
		tree.add(3);
		tree.add(10);
		tree.add(1);
		tree.add(4);
		tree.add(12);
		tree.add(9);
		tree.printInorder();
		tree.remove(5);
		tree.printInorder();
	}
}
