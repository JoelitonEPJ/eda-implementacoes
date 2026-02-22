package questoes_tst.bst;

import java.util.Scanner;

public class ContaNosInternos {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        String[] entrada = scanner.nextLine().split(" ");
        BST bst = new BST();

        for(int i = 0; i < entrada.length; i++)
            bst.add(Integer.parseInt(entrada[i]));

        System.out.println(contaNos(bst.root));
        scanner.close();
    }

    public static int contaNos(Node node){
        if(node.isLeaf()) return 0;
        if(node.left != null && node.right == null) return 1 + contaNos(node.left);
        if(node.left == null && node.right != null) return 1 + contaNos(node.right);
        return 1 + contaNos(node.left) + contaNos(node.right);
    }
}
