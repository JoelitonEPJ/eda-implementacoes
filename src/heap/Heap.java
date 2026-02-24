package heap;

import java.util.NoSuchElementException;


/**
 * Representa uma Max-Heap, implementada utilizando um array.
 * Em um Max-Heap, o valor de um nó pai
 * é sempre maior ou igual aos valores de seus filhos.
 */
public class Heap {
    
    private int[] heap;
    private int tail;

    public Heap(int capacidade){
        this.heap = new int[capacidade];
        this.tail = -1;
    }

    public Heap(int[] array){
        this.heap = array;
        this.tail = array.length - 1;
        buildHeap();
    }

    /**
     * Verifica se o Heap está vazio.
     * @return true se o Heap não contiver elementos, false caso contrário.
     */
    public boolean isEmpty(){
        return this.tail == -1;
    }

    /**
     * Calcula o índice do nó pai de um determinado nó.
     * @param index O índice do nó filho.
     * @return O índice do nó pai.
     */
    public int parent(int index){
        return index - 1 / 2;
    }

    /**
     * Calcula o índice do filho à esquerda de um determinado nó.
     * @param index O índice do nó pai.
     * @return O índice do filho à esquerda.
     */
    public int left(int index){
        return index * 2 + 1;
    }

    /**
     * Calcula o índice do filho à direita de um determinado nó.
     * @param index O índice do nó pai.
     * @return O índice do filho à direita.
     */
    public int right(int index){
        return 2 * (index + 1);
    }

    /**
     * Verifica se um nó em um determinado índice é uma folha (não possui filhos).
     * @param index O índice a ser verificado.
     * @return true se o nó for uma folha, false caso contrário.
     */
    public boolean isLeaf(int index){
        return index > parent(this.tail) && index <= this.tail;
    }

    /**
     * Verifica se um índice esta dentro dos limites atuais do Heap.
     * @param index O índice a ser verificado.
     * @return true se o índice estiver entre 0 e o tail, false caso contrário.
     */
    public boolean isValidIndex(int index){
        return index >= 0 && index <= this.tail;
    }

    /**
     * Adiciona um novo valor ao Max-Heap.
     * Redimensiona o array se estiver cheio.
     * @param value O valor a ser adicionado.
     */
    public void add(int value){
        if(this.tail == this.heap.length - 1){
            resize();
        }

        this.tail++;
        this.heap[this.tail] = value;
        int i = this.tail;
        while(i > 0 && this.heap[parent(i)] < this.heap[i]){
            swap(i, parent(i));
            i = parent(i);
        }
    }

    /**
     * Remove e retorna o elemento no topo do Max-Heap (o maior valor).
     * @return O maior valor presente no Heap.
     * @throws NoSuchElementException se o Heap estiver vazio.
     */
    public int removeTop(){
        if(isEmpty()) throw new NoSuchElementException();

        int toRemove = this.heap[0];
        this.heap[0] = this.heap[this.tail];
        this.tail--;
        heapify(0);
        return toRemove;
    }

    /**
     * Redimensiona o array interno, dobrando sua capacidade.
     * Usado quando o array atual fica cheio.
     */
    private void resize(){
        int[] newArray = new int[this.heap.length * 2];
        for(int i = 0; i < this.heap.length; i++)
            newArray[i] = this.heap[i];
        this.heap = newArray;
    }

    /**
     * Troca a posição de dois elementos no array do Heap.
     * @param i O índice do primeiro elemento.
     * @param j O índice do segundo elemento.
     */
    private void swap(int i, int j){
        int temp = this.heap[i];
        this.heap[i] = this.heap[j];
        this.heap[j] = temp;
    }

    /**
     * Compara o valor em três índices (um pai e seus dois filhos) e retorna
     * o índice que contém o maior valor.
     * @param i O índice do nó pai.
     * @param j O índice do filho à esquerda.
     * @param k O índice do filho à direita.
     * @return O índice que armazena o maior valor entre os três.
     */
    private int max_index(int i, int j, int k){
        int indexMax = i;

        if(isValidIndex(j)){
            if(this.heap[j] > this.heap[indexMax])
                indexMax = j;
        }

        if(isValidIndex(k)){
            if(this.heap[k] > this.heap[indexMax])
                indexMax = k;
        }

        return indexMax;
    }

    /**
     * Restaura a propriedade do Max-Heap descendo um nó para a posição correta.
     * @param index O índice do nó que possivelmente está violando a regra do Max-Heap.
     */
    private void heapify(int index){
        if(!isValidIndex(index) || isLeaf(index)) return;

        int indexMax = max_index(index, left(index), right(index));

        if(indexMax != index){
            swap(index, indexMax);
            heapify(indexMax);
        }
    }

    /**
     * Constrói o Max-Heap garantindo a propriedade estrutural de baixo para cima.
     * Percorre os nós do último pai até a raiz aplicando o heapify.
     */
    private void buildHeap(){
        for(int i = parent(this.tail); i >= 0; i--){
            heapify(i);
        }
    }
}
