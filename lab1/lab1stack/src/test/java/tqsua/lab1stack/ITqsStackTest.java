package tqsua.lab1stack;

import java.util.logging.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;


public class ITqsStackTest {
    private Logger log = Logger.getLogger("ITqsSTack");
    private ITqsStack<Double> testStack;


    //to make sure that stack "gets back to default"
    @BeforeEach
    void setUp() {
        testStack = new ITqsStack<>();
    }

    @DisplayName("Stack is empty on construction")
    @Test
    void emptyOnConstruction() {
        log.info("Testing if stack is empty on construction");
        assertTrue(testStack.isEmpty());

        System.out.println("Test 1 passed");
    }

    @DisplayName("Stack has size 0 on construction")
    @Test
    void sizeOnConstruction() {
        log.info("Testing if stack has size 0 on construction");
        assertEquals(0, testStack.size());

        System.out.println("Test 2 passed");
    }

    @DisplayName("Size after n pushes is not empty and size n")
    @Test
    void sizeAfterPush() {
        log.info("Testing if size after n pushes is not empty and has size n");
        testStack.push(2.0);
        testStack.push(2.4);

        assertFalse(testStack.isEmpty());
        assertEquals(2, testStack.size());

        System.out.println("Test 3 passed");
    }


    @DisplayName("Push x pop x")
    @Test
    void pushThenPop() {
        log.info("Testing if when one pushes x then pops, value popped is x");

        //Push element to Stack
        testStack.push(10.0);
        assertEquals(10.0, testStack.pop());

        System.out.println("Test 4 passed");
    }

    @DisplayName("Push peek check size")
    @Test
    void valSizeAfterPushPeek() {
        log.info("Testing if value pushed then immediately popped is the same, but the size stays the same");

        //Push element to Stack
        testStack.push(10.0);

        assertEquals(10.0, testStack.peek());
        assertEquals(1, testStack.size());

        System.out.println("Test 5 passed");
    }

    @DisplayName("Pop from empty stack")
    @Test
    void popEmpty() {
        log.info("Testing if exception is thrown when popping an empty stack");

        assertThrows(NoSuchElementException.class, () -> {
            testStack.pop();
        });

        System.out.println("Test 6 passed");
    }

    @DisplayName("Peek from empty stack")
    @Test
    void peekEmpty() {
        log.info("Testing if exception is thrown when peeking empty stack");

        assertThrows(NoSuchElementException.class, () -> {
            testStack.peek();
        });

        System.out.println("Test 7 passed");
    }

    @DisplayName("Push to full - for bounded stacks")
    @Test
    void pushFull() {
        log.info("Testing if, for bounded stacks only, exception is thrown when pushing into full stack");

        testStack = new ITqsStack<>(1);
        testStack.push(1.0);

        assertThrows(IllegalStateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                testStack.push(1.0);
            }
        });

        System.out.println("Test 8 passed");
    }
}
