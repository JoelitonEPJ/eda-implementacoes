package questoes_tst.bst;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class BFSDireita {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        String[] entrada = scanner.nextLine().split(" ");
        BFSDireitaBST bst = new BFSDireitaBST();
        for(int i = 0;i < entrada.length; i++)
            bst.add(Integer.parseInt(entrada[i]));
        
        System.out.println(bst.bfsdireita());

        scanner.close();
    }
}

class BFSDireitaBST extends BST {

    public BFSDireitaBST(){
        super();
    }

    public String bfsdireita(){

        String out = "";
        Deque<Node> fila = new LinkedList<>();

        if(this.root != null){
            fila.addLast(this.root);
            while(!fila.isEmpty()){
                Node current = fila.removeFirst();
                out += current.value + " ";
                if(current.right != null) fila.addLast(current.right);
                if(current.left != null) fila.addLast(current.left);
            }
        }
        return out.trim();
    }
}
