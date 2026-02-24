package bst;

import java.util.ArrayList;

/**
 * Representa uma Árvore Binária de Busca (Binary Search Tree - BST).
 * Uma árvore binária de busca é uma estrutura de dados baseada em nós
 * onde a subárvore à esquerda de um nó contém apenas nós com valores menores
 * que o valor do nó, e a subárvore à direita contém apenas valores maiores.
 */
public class BST {
 
    private Node root;
    private int size;

    public BST(){
    }

    /**
     * Verifica se a árvore está vazia.
     * @return true se a árvore estiver vazia, false caso contenha pelo menos um nó.
     */
    public boolean isEmpty(){
        return this.root == null;
    }

    /**
     * Adiciona um novo valor à BST.
     * Os valores são inseridos de forma a manter as propriedades da BST.
     * @param value O valor inteiro a ser adicionado na árvore.
     */
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

    /**
     * Realiza a busca de um valor específico na árvore.
     * @param value O valor a ser buscado.
     * @return O Node que contém o valor buscado, ou null se não for encontrado.
     */
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

    /**
     * Remove um nó com o valor especificado da árvore.
     * @param value O valor a ser removido.
     * @return true se o elemento foi encontrado e removido, false caso contrário.
     */
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
                toRemove.parent.right = toRemove.left;
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
    /**
     * Percorre a árvore em pré-ordem (Raiz, Esquerda, Direita).
     * @return Um ArrayList contendo os valores dos nós em pré-ordem.
     */
    public ArrayList<Integer> preOrder(){
        ArrayList<Integer> out = new ArrayList<>();
        preOrder(this.root, out);
        return out;
    }

    /**
     * Método auxiliar recursivo para o pré-ordem.
     * @param node O nó atual sendo visitado.
     * @param out A lista onde os valores visitados estão sendo armazenados.
     */
    private void preOrder(Node node, ArrayList<Integer> out){
        if(node != null){
            out.add(node.value);
            preOrder(node.left, out);
            preOrder(node.right, out);
        }
    }

    /**
     * Encontra o nó com o menor valor em uma subárvore.
     * @param node A raiz da subárvore a ser explorada.
     * @return O nó contendo o menor valor da subárvore.
     */
    private Node min(Node node){
        Node aux = node;
        while(aux != null && aux.left != null){
            aux = aux.left;
        }
        return aux;
    }

    /**
     * Verifica se o nó é o filho à esquerda do seu pai.
     * @param node O nó a ser verificado.
     * @return true se o nó for filho à esquerda, false caso contrário.
     */
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

    /**
     * Verifica se o nó é uma folha (não possui filhos).
     * @return true se não tiver filhos, false caso contrário.
     */
    public boolean isLeaf(){
        return this.left == null && this.right == null;
    }

    /**
     * Verifica se o nó possui apenas o filho à esquerda.
     * @return true se possuir apenas o filho à esquerda, false caso contrário.
     */
    public boolean hasOnlyLeftChild(){
        return this.left != null && this.right == null;
    }

    /**
     * Verifica se o nó possui apenas o filho à direita.
     * @return true se possuir apenas o filho à direita, false caso contrário.
     */
    public boolean hasOnlyRightChild(){
        return this.left == null && this.right != null;
    }
}
