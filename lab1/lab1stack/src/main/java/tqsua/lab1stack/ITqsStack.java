package tqsua.lab1stack;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ITqsStack<T> {

    private ArrayList<T> stack;
    private Integer maxSize; //wrap to Integer to handle comparisons with null


    public ITqsStack() {
        this.stack = new ArrayList<>();
    }

    public ITqsStack(Integer maxSize) {
        this.stack = new ArrayList<>();
        this.maxSize = maxSize;
    }

    //add an item to the top
    public void push (T value) {
        if (maxSize != null && maxSize == this.stack.size()) {
            throw new IllegalStateException();
        }
        stack.add(value);
    }
    
    //remove the item at the top
    public T pop() {
        if (this.stack.size() == 0) {
            throw new NoSuchElementException();
        }
        else {
            return this.stack.remove(this.stack.size() - 1);
        }
    }

    //return the item at the top (without removing it)
    public T peek() {
        if (this.stack.size() == 0) {
            throw new NoSuchElementException();
        }
        else {
            return this.stack.get(this.stack.size() - 1);
        }
    }

    //return the number of items in the stack
    public Integer size() {
        return this.stack.size();
    }

    //return whether the stack has no items
    public boolean isEmpty() {
        return this.stack.size() == 0;
    }
}
