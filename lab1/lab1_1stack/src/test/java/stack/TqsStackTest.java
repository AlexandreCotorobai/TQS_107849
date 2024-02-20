package stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TqsStackTest {
    private TqsStack<Integer> stack;

    @BeforeEach
    public void setUp() {
        stack = new TqsStack<>();
    }

    // a) A stack is empty on construction. 
    @Test
    @DisplayName("New stack is empty")
    public void newStackIsEmpty() {
        assertTrue(stack.isEmpty());
    }

    // b) A stack has size 0 on construction.
    @Test
    @DisplayName("New stack has size 0")
    public void newStackHasSize0() {
        assertEquals(0, stack.size());
    }

    // c) After n pushes to an empty stack, n > 0, the stack is not empty and its size is n
    @Test
    @DisplayName("Stack is not empty after n pushes")
    public void stackIsNotEmptyAfterNPushes() {
        int n = 5;
        for (int i = 0; i < n; i++) {
            stack.push(i);
        }
        assertFalse(stack.isEmpty());
        assertEquals(n, stack.size());
    }

    // d) If one pushes x then pops, the value popped is x.
    @Test
    @DisplayName("Popped value is the same as pushed value")
    public void poppedValueIsTheSameAsPushedValue() {
        int x = 42;
        stack.push(11);
        stack.push(x);
        assertEquals(x, stack.pop());
    }

    // e) If one pushes x then peeks, the value returned is x, but the size stays the same
    @Test
    @DisplayName("Peeked value is the same as pushed value")
    public void peekedValueIsTheSameAsPushedValue() {
        int x = 42;
        stack.push(x);
        assertEquals(x, stack.peek());
        assertEquals(1, stack.size());
    }

    // f) If the size is n, then after n pops, the stack is empty and has a size 0
    @Test
    @DisplayName("Stack is empty after n pops")
    public void stackIsEmptyAfterNPops() {
        int n = 5;
        for (int i = 0; i < n; i++) {
            stack.push(i);
        }
        for (int i = 0; i < n; i++) {
            stack.pop();
        }
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    // g) Popping from an empty stack does throw a NoSuchElementException
    @Test
    @DisplayName("Popping from an empty stack throws NoSuchElementException")
    public void poppingFromEmptyStackThrowsNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> {stack.pop();});
    }

    // h) Peeking into an empty stack does throw a NoSuchElementException
    @Test
    @DisplayName("Peeking into an empty stack throws NoSuchElementException")
    public void peekingIntoEmptyStackThrowsNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> {stack.peek();});
    }

    // i) For bounded stacks only: pushing onto a full stack does throw an IllegalStateException
    @Test
    @DisplayName("Pushing onto a full stack throws IllegalStateException")
    public void pushingOntoFullStackThrowsIllegalStateException() {
        int bound = 5;
        stack = new TqsStack<>(bound);
        for (int i = 0; i < bound; i++) {
            stack.push(i);
        }
        assertThrows(IllegalStateException.class, () -> {stack.push(42);});
    }
}   

