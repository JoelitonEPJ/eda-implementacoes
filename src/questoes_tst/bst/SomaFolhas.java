package questoes_tst.bst;

import java.util.Scanner;

public class SomaFolhas {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        String[] entrada = scanner.nextLine().split(" ");
        SomaFolhasBST bst = new SomaFolhasBST();
        for(int i = 0; i < entrada.length; i++)
            bst.add(Integer.parseInt(entrada[i]));

        System.out.println(bst.somaFolhas());
        scanner.close();
    }
}

class SomaFolhasBST extends BST {

    public SomaFolhasBST(){
        super();
    }

    public int somaFolhas(){
        return somaFolhas(this.root);
    }

    public int somaFolhas(Node node){
        if(node.left != null & node.right != null) return somaFolhas(node.left) + somaFolhas(node.right);
        if(node.left != null) return somaFolhas(node.left);
        if(node.right != null) return somaFolhas(node.right);
        return node.value;
    }
}