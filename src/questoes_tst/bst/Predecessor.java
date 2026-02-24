package questoes_tst.bst;

import java.util.ArrayList;
import java.util.Scanner;

public class Predecessor {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] entrada = scanner.nextLine().split(" ");
        int alvo = scanner.nextInt();
        scanner.nextLine();

        PredecessorBST bst = new PredecessorBST();
        for(int i = 0; i < entrada.length; i++)
            bst.add(Integer.parseInt(entrada[i]));

        System.out.println(bst.predecessor(alvo));
        scanner.close();
    }
}

class PredecessorBST extends BST {

    public PredecessorBST(){
        super();
    }

    public ArrayList<Integer> predecessor(int valor){
        ArrayList<Integer> out = new ArrayList<>();
        Node node = search(valor);
        out.add(valor);
        if(node.left != null){
            Node aux = node.left;
            while(aux != null){
                out.add(aux.value);
                aux = aux.right;
            }
        } else {
            Node aux = node.parent;
            while(aux != null && valor < aux.value){
                out.add(aux.value);
                aux = aux.parent;
            }
            if(aux != null)
                out.add(aux.value);
        }
        return out;
    }
}
