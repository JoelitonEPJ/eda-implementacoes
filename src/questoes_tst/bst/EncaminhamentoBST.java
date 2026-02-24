package questoes_tst.bst;

import java.util.Scanner;
import java.util.ArrayList;

public class EncaminhamentoBST {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        String[] entrada = scanner.nextLine().split(" ");
        EncaminhamentoBSTBST bst = new EncaminhamentoBSTBST();
        for(int i = 0;i < entrada.length; i++)
            bst.add(Integer.parseInt(entrada[i]));
        
        System.out.println(bst.preOrder());
        System.out.println(bst.order());
        System.out.println(bst.posOrder());

        scanner.close();
    }
}

class EncaminhamentoBSTBST extends BST {

    public EncaminhamentoBSTBST() {
        super();
    }

    public ArrayList<Integer> order(){
        ArrayList<Integer> out = new ArrayList<>();
        order(this.root, out);
        return out;
    }

    public ArrayList<Integer> posOrder(){
        ArrayList<Integer> out = new ArrayList<>();
        posOrder(this.root, out);
        return out;
    }

    private void order(Node node, ArrayList<Integer> out){
        if(node != null){
            order(node.left, out);
            out.add(node.value);
            order(node.right, out);
        }
    }

    private void posOrder(Node node, ArrayList<Integer> out){
        if(node != null){
            posOrder(node.left, out);
            posOrder(node.right, out);
            out.add(node.value);
        }
    }
}
