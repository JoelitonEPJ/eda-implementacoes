package arrayList;

import java.util.Arrays;

public class ArrayList {
    
    private int[] array;
    private int tail;

    public ArrayList() {
        this.array = new int[20];
        this.tail = -1;
    }

    public ArrayList(int capacidade) {
        if(capacidade <= 0) throw new IllegalArgumentException();
        this.array = new int[capacidade];
        this.tail = -1;
    }

    public int size() {
        return this.tail + 1;
    }

    public boolean isEmpty(){
        return this.tail == -1;
    }

    public void add(int value) {
        if(this.tail + 1 == this.array.length) resize();
        this.tail++;
        this.array[this.tail] = value;
    }

    public void add(int index, int value) {
        if(index < 0 || index > this.tail + 1) throw new IndexOutOfBoundsException();
        if(this.tail + 1 == this.array.length) resize();
        shiftDireita(index);
        this.array[index] = value;
        this.tail++;
    }

    public void set(int index, int value) { 
        if(index < 0 || index > this.tail) throw new IndexOutOfBoundsException();
        this.array[index] = value;
    }

    public int remove(int index) {
        if(index < 0 || index > this.tail) throw new IndexOutOfBoundsException();
        int toRemove = this.array[index];
        shiftEsquerda(index + 1);
        this.tail--;
        return toRemove;
    }

    public boolean removeElement(int value){
        int indexRemove = indexOf(value);
        if(indexRemove == -1) return false;
        remove(indexRemove);
        return true;
    }

    public int get(int index) {
        if(index < 0 || index > this.tail) throw new IndexOutOfBoundsException();
        return this.array[index];
    } 

    public int indexOf(int value) {
        for(int i = 0; i <= this.tail; i++)
            if(this.array[i] == value)
                return i;
        return -1;
    }

    public boolean contains(int value) {
        return indexOf(value) != -1;
    }

    public String toString(){
        return Arrays.toString(Arrays.copyOf(this.array, this.tail + 1));
    }

    private void shiftEsquerda(int index) {
        for(int i = index; i <= this.tail; i++){
            this.array[i - 1] = this.array[i];
        }
    }

    private void shiftDireita(int index) {
        for(int i = this.tail; i >= index; i--){
            this.array[i + 1] = this.array[i];
        }
    }

    private void resize() {
        int[] newArray = new int[this.array.length * 2];
        for(int i = 0; i < this.array.length; i++)
            newArray[i] = this.array[i];
        this.array = newArray;
    }
}