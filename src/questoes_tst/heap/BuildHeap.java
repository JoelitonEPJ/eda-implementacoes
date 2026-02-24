package questoes_tst.heap;

import java.util.Scanner;
import java.util.Arrays;

public class BuildHeap {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] entrada = scanner.nextLine().split(" ");
        int[] entradaInt = new int[entrada.length];

        for(int i = 0; i < entrada.length; i++)
            entradaInt[i] = Integer.parseInt(entrada[i]);

        Heap heap = new Heap(entradaInt);
        
        System.out.println(Arrays.toString(heap.heap));
        scanner.close();
    }
}

class Heap {
    
    int[] heap;
    int tail;

    public Heap(int[] array){
        this.heap = array;
        this.tail = array.length - 1;
        buildHeap();
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
