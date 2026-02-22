package questoes_tst.heap;

import java.util.Scanner;

public class EhHeap {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        String[] entrada = scanner.nextLine().split(" ");
        int[] entradaInt = new int[entrada.length];

        for(int i = 0; i < entrada.length; i++)
            entradaInt[i] = Integer.parseInt(entrada[i]);

        System.out.println(ehHeap(entradaInt));
    }

    public static boolean ehHeap(int[] heap){
        for(int i = parent(heap.length - 1); i >= 0; i--){
            if(!obedeceRegra(heap, i)){
                return false;
            }
        }
        return true;
    }

    public static boolean obedeceRegra(int[] heap, int index){
        if(isValidIndex(heap, left(index)))
            if(!(heap[left(index)] < heap[index]))
                return false;
        if(isValidIndex(heap, right(index)))
            if(!(heap[right(index)] < heap[index]))
                return false;
        return true;
    }

    public static boolean isValidIndex(int[] heap, int index){
        return index >= 0 && index <= heap.length - 1;
    }

    public static int parent(int index){
        return index - 1 / 2;
    }

    public static int left(int index){
        return index * 2 + 1;
    }

    public static int right(int index){
        return 2 * (index + 1);
    }
}
