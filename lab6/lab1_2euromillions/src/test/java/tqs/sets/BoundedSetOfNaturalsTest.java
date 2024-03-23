/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tqs.sets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import tqs.sets.BoundedSetOfNaturals;

/**
 * @author ico0
 */
class BoundedSetOfNaturalsTest {
    private BoundedSetOfNaturals setA;
    private BoundedSetOfNaturals setB;
    private BoundedSetOfNaturals setC;


    @BeforeEach
    public void setUp() {
        setA = new BoundedSetOfNaturals(1);
        setB = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});
        setC = BoundedSetOfNaturals.fromArray(new int[]{50, 60});
    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = null;
    }

    // @Disabled("TODO revise test logic")
    @Test
    public void testAddElement() {

        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(1, setA.size());
    }

    // @Disabled("TODO revise to test the construction from invalid arrays")
    @Test
    public void testAddFromBadArray() {
        int[] elems = new int[]{10, -20, -30};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setA.add(elems));
    }

    @Test
    public void testMaxAdd() {
        BoundedSetOfNaturals set = new BoundedSetOfNaturals(5);

        // Test adding elements
        for (int i = 1; i <= 5; i++) {
            set.add(i);
            assertTrue(set.contains(i));
        }

        // Test adding more elements than max size
        assertThrows(IllegalArgumentException.class, () -> {
            set.add(6);
        });

    }

    @Test
    public void testDuplicateAdd() {
        BoundedSetOfNaturals set = new BoundedSetOfNaturals(5);

        set.add(1);
        assertThrows(IllegalArgumentException.class, () -> {
            set.add(1);
        });
    }

    @Test
    public void testNonNaturalAdd() {
        BoundedSetOfNaturals set = new BoundedSetOfNaturals(5);

        assertThrows(IllegalArgumentException.class, () -> {
            set.add(-1);
        });
    }

    @Test
    public void testFromArray() {
        int[] values = {1, 2, 3, 4, 5};
        BoundedSetOfNaturals set = BoundedSetOfNaturals.fromArray(values);

        for (int value : values) {
            assertTrue(set.contains(value));
        }

        assertEquals(values.length, set.size());
    }


    @Test
    public void testIntersects() {
        
        assertTrue(setB.intersects(setC)); 
        assertFalse(setA.intersects(setB));
    }

    @Test
    public void testHashCode() {

        BoundedSetOfNaturals set1b = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});

        assertEquals(setB.hashCode(), set1b.hashCode()); // Same content, so hash codes should be equal
        assertNotEquals(setA.hashCode(), set1b.hashCode()); // Different content, so hash codes should not be equal
    }

    @Test
    public void testEquals() {
        // Test when this == obj
        assertTrue(setA.equals(setA)); 

        // Test when obj == null
        assertFalse(setA.equals(null)); 

        // Test when getClass() != obj.getClass()
        assertFalse(setA.equals(new Object())); 

        // Test when collections are not equal
        assertFalse(setA.equals(setB)); 

        // Test when collections are equal
        BoundedSetOfNaturals setD = BoundedSetOfNaturals.fromArray(new int[]{50, 60});
        assertEquals(setC, setD); 
    }
}
