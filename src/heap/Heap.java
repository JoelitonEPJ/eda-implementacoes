package heap;

import java.util.NoSuchElementException;

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

    public boolean isEmpty(){
        return this.tail == -1;
    }

    public int parent(int index){
        return index - 1 / 2;
    }

    public int left(int index){
        return index * 2 + 1;
    }

    public int right(int index){
        return 2 * (index + 1);
    }

    public boolean isLeaf(int index){
        return index > parent(this.tail) && index <= this.tail;
    }

    public boolean isValidIndex(int index){
        return index >= 0 && index <= this.tail;
    }

    public void add(int value){
        if(this.tail == this.heap.length - 1){
            resize();
        }

        this.tail++;
        this.heap[this.tail] = value;
        int i = this.tail;
        while(i > 0 && this.heap[parent(i)] < this.heap[i]){
            swap(i, parent(i));
        }
    }

    public int removeTop(){
        if(isEmpty()) throw new NoSuchElementException();

        int toRemove = this.heap[0];
        this.heap[0] = this.heap[this.tail];
        this.tail--;
        heapify(0);
        return toRemove;
    }

    private void resize(){
        int[] newArray = new int[this.heap.length * 2];
        for(int i = 0; i < this.heap.length; i++)
            newArray[i] = this.heap[i];
        this.heap = newArray;
    }

    private void swap(int i, int j){
        int temp = this.heap[i];
        this.heap[i] = this.heap[j];
        this.heap[j] = temp;
    }

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

    private void heapify(int index){
        if(!isValidIndex(index) || isLeaf(index)) return;

        int indexMax = max_index(index, left(index), right(index));

        if(indexMax != index){
            swap(index, indexMax);
            heapify(indexMax);
        }
    }

    private void buildHeap(){
        for(int i = parent(this.tail); i >= 0; i--){
            heapify(i);
        }
    }
}
