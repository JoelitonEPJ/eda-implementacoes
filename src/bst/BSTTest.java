package bst;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;  

public class BSTTest {

    BST bst;
    ArrayList<Integer> expectedPreOrder;

    @BeforeEach
    public void setUp(){
        bst = new BST();
        expectedPreOrder = new ArrayList<>();
    }

    @Test
    public void testAdd(){
        bst = new BST();
        assertTrue(bst.isEmpty());

        bst.add(50);
        ArrayList<Integer> expectedPreOrder = new ArrayList<>();
        expectedPreOrder.add(50);
        assertEquals(bst.preOrder(), expectedPreOrder);

        bst.add(30);
        bst.add(90);
        bst.add(10);
        bst.add(40);
        bst.add(80);
        bst.add(120);
        expectedPreOrder = new ArrayList<>();
        expectedPreOrder.add(50);
        expectedPreOrder.add(30);
        expectedPreOrder.add(10);
        expectedPreOrder.add(40);
        expectedPreOrder.add(90);
        expectedPreOrder.add(80);
        expectedPreOrder.add(120);
        assertEquals(bst.preOrder(), expectedPreOrder);

        bst = new BST();
        bst.add(50);
        bst.add(30);
        bst.add(10);
        expectedPreOrder = new ArrayList<>();
        expectedPreOrder.add(50);
        expectedPreOrder.add(30);
        expectedPreOrder.add(10);
        assertEquals(bst.preOrder(), expectedPreOrder);
    }

    @Test
    public void testRemove(){
        BST bst = new BST();

        bst.add(30);
        assertFalse(bst.isEmpty());
        bst.remove(30);
        assertTrue(bst.isEmpty());

        assertFalse(bst.remove(120));

        bst.add(50);
        bst.add(30);
        expectedPreOrder = new ArrayList<>();
        expectedPreOrder.add(50);
        assertTrue(bst.remove(30));
        assertEquals(bst.preOrder(), expectedPreOrder);

        bst = new BST();
        bst.add(50);
        bst.add(30);
        bst.add(10);
        bst.remove(30);
        expectedPreOrder = new ArrayList<>();
        expectedPreOrder.add(50);
        expectedPreOrder.add(10);
        assertEquals(bst.preOrder(), expectedPreOrder);

        bst = new BST();
        bst.add(50);
        bst.add(30);
        bst.add(10);
        bst.add(35);
        bst.remove(30);
        expectedPreOrder = new ArrayList<>();
        expectedPreOrder.add(50);
        expectedPreOrder.add(35);
        expectedPreOrder.add(10);
        assertEquals(bst.preOrder(), expectedPreOrder);

        bst = new BST();
        bst.add(50);
        bst.add(40);
        bst.add(30);
        bst.add(20);
        bst.remove(30);
        expectedPreOrder = new ArrayList<>();
        expectedPreOrder.add(50);
        expectedPreOrder.add(40);
        expectedPreOrder.add(20);
        assertEquals(bst.preOrder(), expectedPreOrder);
    }
}
