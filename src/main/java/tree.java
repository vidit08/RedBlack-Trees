/**
 * Created by vidit on 2/21/17.
 */

import java.util.Scanner; 
public class tree{

    public static void main(String[] args){
        Tree t = new Tree();

        
        // t.add(1);
        // t.add(4);
        // t.add(6);
        // t.add(8);       
        // t.add(13);
        // t.add(15);
        // t.add(22);
        // // System.out.println(t.size());
        // t.iterator_inorder(t.root);
        // // System.out.println();
        // t.iterator_preorder(t.root);
        // //System.out.println(t.root.lcount);
        // //System.out.println(t.root.rcount);
        // t.remove(6);
        // System.out.println();
        // t.iterator_preorder(t.root);

        //System.out.println(t.size());

        // for(int i=1;i<=16;i++){
        // 	t.add(i);
        // }

        // t.iterator_preorder(t.root);

        int m;
        Scanner reader = new Scanner(System.in);  
        int n;
        do{
            System.out.println("\nWhat do you need to perform (Enter the associated number):\n1. Insert \n2. Remove \n3. Inorder traversal \n4. Preorder traversal \n5. Postorder traversal 9. exit");
            n = reader.nextInt(); 

            switch(n){
                case 1:     System.out.println("\nEnter Value to insert\n");
                            m = reader.nextInt();
                            t.add(m);
                            System.out.println(m + " inserted to tree\n");
                            break;

                case 2:     System.out.println("\nEnter Value to remove\n");
                            m = reader.nextInt();
                            t.remove(m);
                            System.out.println(m + " removed from the tree\n");
                            break;

                case 3:     t.iterator_inorder(t.root);
                            break;
                case 4:     t.iterator_preorder(t.root);
                            break;

                case 5:     t.iterator_postorder(t.root);
                            break;

                case 9:     System.out.println("Thank You!");
                            break;
                default:    System.out.println("Please enter proper value\n"); 
            }

        }
        while(n!=9);


    }
}