package questoes_tst.bst;

import java.util.Scanner;
import java.util.ArrayList;

public class BalanceBST {
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        String[] entrada = scanner.nextLine().split(" ");
        BST bst = new BST();
        for(int i = 0;i < entrada.length; i++)
            bst.add(Integer.parseInt(entrada[i]));
        
        System.out.println(bst.printarComBalance());

        scanner.close();
    }
}

class BST {

    Node root;
    int size;

    BST(){
    }

    public boolean isEmpty(){
        return this.root == null;
    }

    public void add(int value){
        
        this.size++;
        if(isEmpty()){
            this.root = new Node(value);
            return;
        }
        
        Node aux = this.root;
        while(aux != null){
            if(value < aux.value){
                if(aux.left == null){
                    Node newNode = new Node(value);
                    aux.left = newNode;
                    newNode.parent = aux;
                    recalculaAltura(newNode);
                    return;
                }
                aux = aux.left;
            } else {
                if(aux.right == null){
                    Node newNode = new Node(value);
                    aux.right = newNode;
                    newNode.parent = aux;
                    recalculaAltura(newNode);
                    return;
                }
                aux = aux.right;
            }
        }
    }

    private void recalculaAltura(Node node){
        while(node != null){
            node.balance = height(node.left) - height(node.right);
            node = node.parent;
        }
    }

    private int height(Node node){
        if(node == null) return -1;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public ArrayList<String> preOrder(){
        ArrayList<String> out = new ArrayList<>();
        preOrder(this.root, out);
        return out;
    }

    private void preOrder(Node node, ArrayList<String> out){
        if(node != null){
            out.add(node.value + "," + node.balance);
            preOrder(node.left, out);
            preOrder(node.right, out);
        }
    }

    public String printarComBalance(){
        String out = "";
        ArrayList<String> preOrder = preOrder();

        for(int i = 0; i < preOrder.size(); i++)
            out += preOrder.get(i) + " ";

        return out.trim();
    }
}

class Node {

    Node parent;
    Node left;
    Node right;
    int value;
    int balance;

    Node(int value){
        this.value = value;
    }

    public boolean isLeaf(){
        return this.left == null && this.right == null;
    }

    public boolean hasOnlyLeftChild(){
        return this.left != null && this.right == null;
    }

    public boolean hasOnlyRightChild(){
        return this.left != null && this.right == null;
    }
}