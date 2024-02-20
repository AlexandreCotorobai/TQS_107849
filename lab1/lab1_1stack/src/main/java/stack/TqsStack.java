package stack;

import java.util.NoSuchElementException;
import java.util.LinkedList;

public class TqsStack<T> implements Stack<T> {
    private LinkedList<T> stack;
    private int bound = -1;

    public TqsStack() {
        stack = new LinkedList<>();
    }

    public TqsStack(int bound) {
        stack = new LinkedList<>();
        this.bound = bound;
    }

    @Override
    public void push(T element) {
        if (bound != -1 && stack.size() >= bound) {
            throw new IllegalStateException("Stack is full!");
        }
        stack.addFirst(element);
    }

    @Override
    public T pop() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException();
        }
        return stack.pollFirst();
    }

    @Override
    public T peek() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException();
        }
        return stack.peekFirst();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }
    
    @Override
    public int size() {
        return stack.size();
    }
    
}
