// TreeNode 
class TreeNode<T extends Comparable<T>>{

	TreeNode<T> parent;
	TreeNode<T> leftChild; 
	TreeNode<T> rightChild;

	public static final int BLACK = 0;
	public static final int RED = 1 ; 
	public T value; 
	

	public int color; 
	public int lcount;
	public int rcount; 

	TreeNode(){
		this.color = BLACK;
		this.parent = null;
		this.leftChild = null;
		this.rightChild = null;
		this.lcount = 0;
		this.rcount = 0;
	}

	TreeNode(T val){
		super(); 
		this.value = val; 
	}

}