package bst;

import java.util.ArrayList;

public class BST {
 
    private Node root;
    private int size;

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
                    return;
                }
                aux = aux.left;
            } else {
                if(aux.right == null){
                    Node newNode = new Node(value);
                    aux.right = newNode;
                    newNode.parent = aux;
                    return;
                }
                aux = aux.right;
            }
        }
    }

    public Node search(int value){
        Node aux = this.root;
        while(aux != null && aux.value != value){
            if(value < aux.value){
                aux = aux.left;
            } else {
                aux = aux.right;
            }
        }
        return aux;
    }

    public boolean remove(int value){

        Node toRemove = search(value);
        if(toRemove == null){
            return false;
        }

        if(toRemove.isLeaf()){
            if(toRemove == this.root){
                this.root = null;
            } else if(isLeftChild(toRemove)){
                toRemove.parent.left = null;
            } else {
                toRemove.parent.right = null;
            }
        } else if(toRemove.hasOnlyLeftChild()){
            if(toRemove == this.root){
                this.root = toRemove.left;
                toRemove.left.parent = null;
            } else if(isLeftChild(toRemove)){
                toRemove.parent.left = toRemove.left;
            } else {
                toRemove.parent = toRemove.left;
            }
            toRemove.left.parent = toRemove.parent;
        } else if(toRemove.hasOnlyRightChild()){
            if(toRemove == this.root){
                this.root = toRemove.right;
                toRemove.right.parent = null;
            } else if(isLeftChild(toRemove)){
                toRemove.parent.left = toRemove.right;
            } else {
                toRemove.parent.right = toRemove.right;
            }
            toRemove.right.parent = toRemove.parent;
        } else {
            Node sucessor = min(toRemove.right);
            remove(sucessor.value);
            toRemove.value = sucessor.value;
            this.size--;
            return true;
        }
        this.size--;
        return true;
    }

    // Mudar para a própia implementação de ArrayList
    public ArrayList<Integer> preOrder(){
        ArrayList<Integer> out = new ArrayList<>();
        preOrder(this.root, out);
        return out;
    }

    private void preOrder(Node node, ArrayList<Integer> out){
        if(node != null){
            out.add(node.value);
            preOrder(node.left, out);
            preOrder(node.right, out);
        }
    }

    private Node min(Node node){
        Node aux = node;
        while(aux != null && aux.left != null){
            aux = aux.left;
        }
        return aux;
    }

    private boolean isLeftChild(Node node){
        return node.parent.left == node;
    }
}

class Node {

    Node parent;
    Node left;
    Node right;
    int value;

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
