package questoes_tst.bst;

import java.util.Scanner;

public class MaioresQueK {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] entrada = scanner.nextLine().split(" ");
        int alvo = scanner.nextInt();
        scanner.nextLine();

        MaioresQueKBST bst =  new MaioresQueKBST();
        for(int i = 0; i < entrada.length; i++)
            bst.add(Integer.parseInt(entrada[i]));

        System.out.println(bst.preOrder());
        System.out.println(bst.maioresQueK(alvo));

        scanner.close();
    }
}

class MaioresQueKBST extends BST {

    public MaioresQueKBST(){
        super();
    }

    public int maioresQueK(int k){
        return maioresQueK(k, this.root);
    }

    private int maioresQueK(int k, Node node){
        if(node == null) return 0;
        if(k >= node.value) return maioresQueK(k, node.right);
        return 1 + maioresQueK(k, node.left) + maioresQueK(k, node.right);
    }
}
