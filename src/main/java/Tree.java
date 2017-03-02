public class Tree<T extends Comparable<T>>{
	
	TreeNode<T> leaf = new TreeNode<T>();
	TreeNode<T> root =  leaf; 


	public Tree(){

		root.leftChild = leaf;
		root.rightChild = leaf;
		root.parent = leaf;

	}

	public void add(T value){
			add(new TreeNode<T>(value));

		}

	public void add(TreeNode<T> t){

		TreeNode<T> temp1 = root;
		TreeNode<T> temp2 = leaf; 

		while(!isNull(temp1)){

			temp2 = temp1;

			if(t.value.compareTo(temp1.value) < 0 ){

				t.lcount++;
				temp1 = temp1.leftChild;

			}
			else{
				t.rcount++;
				temp1 = temp1.rightChild;
			}

		}


		t.parent = temp2; 

		if (isNull(temp2)){
				 root = t;
		}
		else if(t.value.compareTo(temp2.value) < 0 ){
			temp2.leftChild = t;
		}
		else
			temp2.rightChild = t;


		t.leftChild = leaf;
		t.rightChild = leaf; 
		t.color = TreeNode.RED;

		insertFixup(t);

	}


	public void remove(T t){

		TreeNode<T> z = find(t);	

		TreeNode<T> x = leaf;
		TreeNode<T> y = leaf;

		if(isNull(z.leftChild) || isNull(z.rightChild))
			y = z;

		else y = next(z);

		if(!isNull(y.leftChild))
			x=y.leftChild;
		else
			x=y.rightChild;

		x.parent = y.parent;

		if(isNull(y.parent))
			root =x;


		else if(!isNull(y.parent.leftChild) && y.parent.leftChild == y)
			y.parent.leftChild =x;
		else if(!isNull(y.parent.rightChild) && y.parent.rightChild == y)
			y.parent.rightChild = x;

		if(y!=z){

			z.value = y.value	;
		}


		TreeNode<T> node1=leaf;
		TreeNode<T> node2=leaf;

		if (isNull(x)){
			node1 = y.parent;
			node2 = y;
		}
		else{
			node1 = x.parent;
			node2 = x;
		}

		while (!isNull(node1)){
            
            if (y.value != node1.value) {

                if ( (y.value).compareTo(node1.value) > 0)
                    node1.rcount--;

                if (y.value.compareTo(node1.value) < 0)
                    node1.lcount--;
            }

            else{
                if (isNull(node1.leftChild))
                    node1.lcount--;
                else if (isNull(node1.rightChild))
                    node1.rcount--;

                else if (node2 == node1.rightChild)
                    node1.rcount--;
                else if (node2 == node1.leftChild)
                    node1.lcount--;
            }

            node2 = node1;
            node1 = node1.parent;

        } 



        if (y.color == TreeNode.BLACK)
        	removeFixup(x);

	}

	public void removeFixup(TreeNode<T> t){

		TreeNode<T> node;

		while (t != root && t.color == TreeNode.BLACK){
            if (t == t.parent.leftChild){

                node = t.parent.rightChild;

                if (node.color == TreeNode.RED){
                    node.color = TreeNode.BLACK;
                    t.parent.color = TreeNode.RED;
                    leftRotate(t.parent);
                    node = t.parent.rightChild;
                }

                if (node.leftChild.color == TreeNode.BLACK && node.rightChild.color == TreeNode.BLACK){
                    node.color = TreeNode.RED;
                    t = t.parent;
                }
                
                else{

                    if (node.rightChild.color == TreeNode.BLACK){
                        node.leftChild.color = TreeNode.BLACK;
                        node.color = TreeNode.RED;
                        rightRotate(node);
                        node = t.parent.rightChild;
                    }

                    node.color = t.parent.color;
                    t.parent.color = TreeNode.BLACK;
                    node.rightChild.color = TreeNode.BLACK;
                    leftRotate(t.parent);
                    t = root;
                }
            }
            
            else{

          
                node = t.parent.leftChild;

                if (node.color == TreeNode.RED){
                    node.color = TreeNode.BLACK;
                    t.parent.color = TreeNode.RED;
                    rightRotate(t.parent);
                    node = t.parent.leftChild;
                }

                if (node.rightChild.color == TreeNode.BLACK && node.leftChild.color == TreeNode.BLACK){
                    node.color = TreeNode.RED;
                    t = t.parent;
                }

                else{
      
                    if (node.leftChild.color == TreeNode.BLACK){
                        node.rightChild.color = TreeNode.BLACK;
                        node.color = TreeNode.RED;
                        leftRotate(node);
                        node = t.parent.leftChild;
                    }

               
                    node.color = node.parent.color;
                    node.parent.color = TreeNode.BLACK;
                    node.leftChild.color = TreeNode.BLACK;
                    rightRotate(t.parent);
                    t = root;
                }
            }
        }

        t.color = TreeNode.BLACK;

	}


	public TreeNode next(TreeNode<T> z){

		if (!isNull(z.leftChild))
			return treeMinimum(z.rightChild);

		TreeNode<T> y = z.parent;

		while(!isNull(y) && z == y.rightChild){

			z=y;
			y=y.parent;
		}
		return y;
	}

	public TreeNode<T> find(T value){

		TreeNode<T> node =root;

		while(!isNull(node)){

			if(node.value.equals(value))
				return node;

			else if (node.value.compareTo(value) < 0)
				node = node.rightChild;

			else 
				node = node.leftChild;

		}

		return null;
	}


	public void insertFixup(TreeNode<T> t){

		TreeNode<T> temp;

		while(t.parent.color == TreeNode.RED){

			if(t.parent == t.parent.parent.leftChild){
				
				temp = t.parent.parent.rightChild;

				if(temp.color == TreeNode.RED){

					t.parent.color = TreeNode.BLACK;
					temp.color = TreeNode.BLACK;
					t.parent.parent.color = TreeNode.RED;
					t = t.parent.parent;
				}
				else if (t == t.parent.rightChild){

					t = t.parent;
					leftRotate(t);
				} 
				else{

					t.parent.color = TreeNode.BLACK;
					t.parent.parent.color = TreeNode.RED;
					rightRotate(t.parent.parent);

				}


			}
			else{

				temp = t.parent.parent.leftChild;

				if(temp.color == TreeNode.RED){

					t.parent.color = TreeNode.BLACK;
					temp.color = TreeNode.BLACK;
					t.parent.parent.color = TreeNode.RED;
					t = t.parent.parent;
				}
				else if (t == t.parent.leftChild){

					t = t.parent;
					rightRotate(t);
				} 
				else{

					t.parent.color = TreeNode.BLACK;
					t.parent.parent.color = TreeNode.RED;
					leftRotate(t.parent.parent);

				}

			}

		}

		root.color = TreeNode.BLACK;
	}

	public void leftRotate(TreeNode<T> t){

		if (isNull(t.leftChild) && isNull(t.rightChild.leftChild)){
			t.lcount = 0;
			t.rcount = 0;
			t.rightChild.lcount = 1;
		}
		else if(isNull(t.leftChild) && !isNull(t.rightChild.leftChild)){
			t.lcount = 0;
			t.rcount = 1 + t.rightChild.leftChild.lcount + t.rightChild.leftChild.rcount;
			t.rcount = 2 + t.rightChild.leftChild.lcount + t.rightChild.leftChild.rcount;

		}
		else if(!isNull(t.leftChild) && isNull(t.rightChild.leftChild)){
			t.rcount = 0;
			t.rightChild.lcount = 2 + t.leftChild.lcount + t.leftChild.rcount;
		}
		else{

			t.rcount =  1+ t.rightChild.leftChild.lcount+ t.rightChild.leftChild.rcount;
			t.rightChild.lcount = 3 + t.leftChild.lcount+t.leftChild.rcount + t.rightChild.leftChild.lcount + t.rightChild.leftChild.rcount;

		}


		TreeNode temp;
		temp = t.rightChild;
		t.rightChild = temp.leftChild;

		if(!isNull(temp.leftChild))
			temp.leftChild.parent = t;

		temp.parent= t.parent;


		if(isNull(t.parent))
			root = temp;
		else if (t.parent.leftChild == t)
			t.parent.leftChild=temp;
		else
			t.parent.rightChild = temp;

		temp.leftChild = t;
		t.parent = temp;
	}

	public void rightRotate(TreeNode<T> t){

		if (isNull(t.rightChild) && isNull(t.leftChild.rightChild)){
			t.rcount = 0;
			t.lcount = 0;
			t.leftChild.rcount = 1;
		}
		else if(isNull(t.rightChild) && !isNull(t.leftChild.rightChild)){
			t.rcount = 0;
			t.lcount = 1 + t.leftChild.rightChild.rcount + t.leftChild.rightChild.lcount;
			t.rcount = 2 + t.leftChild.rightChild.rcount + t.leftChild.rightChild.lcount;

		}
		else if(!isNull(t.rightChild) && isNull(t.leftChild.rightChild)){
			t.lcount = 0;
			t.leftChild.rcount = 2 + t.rightChild.rcount + t.rightChild.lcount;
		}
		else{

			t.lcount =  1+ t.leftChild.rightChild.rcount+ t.leftChild.rightChild.lcount;
			t.leftChild.rcount = 3 + t.rightChild.rcount+t.rightChild.lcount + t.leftChild.rightChild.rcount + t.rightChild.leftChild.rcount;

		}


		TreeNode temp;
		temp = t.leftChild;
		t.leftChild = temp.rightChild;

		if(!isNull(temp.rightChild))
			temp.rightChild.parent = t;

		temp.parent= t.parent;


		if(isNull(t.parent))
			root = temp;
		else if (t.parent.rightChild == t)
			t.parent.rightChild=temp;
		else
			t.parent.leftChild = temp;

		temp.rightChild = t;
		t.parent = temp;
	}


	public boolean isNull(TreeNode<T> x){

		return x == leaf;
		}


	// public int size(){
	// 	return 1 + root.lcount + root.rcount;
	// }

	public TreeNode<T> treeMinimum(TreeNode<T> node){

        while (!isNull(node.leftChild))
            node = node.leftChild;
        return node;
    }

    public void iterator_inorder(TreeNode<T> x){

    	if(!isNull(x)){
    		iterator_inorder(x.leftChild);
    		System.out.print((Integer)x.value + ", ");
    		iterator_inorder(x.rightChild);
    	}

    }

    public void iterator_preorder(TreeNode<T> x){

    	if(!isNull(x)){
    		System.out.print((Integer)x.value + ", ");
    		iterator_preorder(x.leftChild);
    		iterator_preorder(x.rightChild);
    	}

    }

    public void iterator_postorder(TreeNode<T> x){

    	if(!isNull(x)){
    		
    		iterator_postorder(x.leftChild);
    		iterator_postorder(x.rightChild);
    		System.out.print((Integer)x.value + ", ");
    	}

    }
	

}