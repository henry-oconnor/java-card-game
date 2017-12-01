/*
 * Henry O'Connor
 */
package texasholdem;

import java.util.Stack;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author henoc
 */
public class BankTest {
    
    public BankTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of popChip method, of class Bank.
     */
    @Test
    public void testPopChip() {
        System.out.println("popChip");
        Chip.Color c = null;
        Bank instance = new BankImpl();
        Chip expResult = null;
        Chip result = instance.popChip(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of put method, of class Bank.
     */
    @Test
    public void testPut() {
        System.out.println("put");
        Stack<Chip> newChips = null;
        Bank instance = new BankImpl();
        instance.put(newChips);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of popAll method, of class Bank.
     */
    @Test
    public void testPopAll() {
        System.out.println("popAll");
        Bank instance = new BankImpl();
        Stack<Chip> expResult = null;
        Stack<Chip> result = instance.popAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotal method, of class Bank.
     */
    @Test
    public void testGetTotal() {
        System.out.println("getTotal");
        Bank instance = new BankImpl();
        Integer expResult = null;
        Integer result = instance.getTotal();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class BankImpl extends Bank {
    }
    
}
