package questoes_tst.bst;

import java.util.Scanner;

public class ClossestToK {
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        String[] entrada = scanner.nextLine().split(" ");
        int alvo = scanner.nextInt();
        scanner.nextLine();

        ClossestToKBST bst =  new ClossestToKBST();
        for(int i = 0; i < entrada.length; i++)
            bst.add(Integer.parseInt(entrada[i]));

        System.out.println(bst.preOrder());
        System.out.println(bst.maisProximoK(alvo));

        scanner.close();
    }
}

class ClossestToKBST extends BST {

    public ClossestToKBST(){
        super();
    }

    public int maisProximoK(int k){
        if(isEmpty()) return -1;
        
        Node aux = this.root;
        int maisProximo = aux.value;

        while(aux != null){

            if(Math.abs(k - aux.value) < Math.abs(k - maisProximo)){
                maisProximo = aux.value;
            }

            if(k < aux.value)
                aux = aux.left;
            else if(k > aux.value)
                aux = aux.right;
            else
                break;
        }
        return maisProximo;
    }
}
