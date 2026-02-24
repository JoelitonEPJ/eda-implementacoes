package questoes_tst.bst;

import java.util.Scanner;

public class EqualsBST {
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        String[] entrada = scanner.nextLine().split(" ");
        String[] entrada2 = scanner.nextLine().split(" ");

        BSTEquals bst = new BSTEquals();
        BSTEquals bst2 = new BSTEquals();

        for(int i = 0;i < entrada.length; i++)
            bst.add(Integer.parseInt(entrada[i]));

        for(int i = 0;i < entrada2.length; i++)
            bst2.add(Integer.parseInt(entrada2[i]));
        
        System.out.println(bst.equalsBST(bst2));

        scanner.close();
    }
}

class BSTEquals extends BST {

    public BSTEquals(){
        super();
    }

    public boolean equalsBST(BST other) {
        if(other == null) return false;
        return equalsBST(this.root, other.root);
    }

    private boolean equalsBST(Node node1, Node node2){
        if(node1 == null && node2 == null){
            return true;
        }

        if(node1 == null || node2 == null){
            return false;
        }

        return (node1.value == node2.value) && equalsBST(node1.left, node2.left) && equalsBST(node1.right, node2.right);
    }
}


