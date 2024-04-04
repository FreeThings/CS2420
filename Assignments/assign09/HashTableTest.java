package assign09;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HashTableTest {
    @Test
    void testEntriesEmpty() {
        HashTable<Integer, String> table = new HashTable<>();
        assertEquals(0, table.entries().size());
    }

    @Test
    void testContainsKey(){
        HashTable<String, String> table = new HashTable<>();
        table.put("A", "A");
        assertTrue(table.containsKey("A"));
    }

    @Test
    void testPut(){
        HashTable<String, String> table = new HashTable<>();
        assertNull(table.put("A", "A"));
        assertEquals("A", table.put("A", "B"));
        assertEquals(1, table.size());
    }


    @Test
    void testContainsValue() {
        HashTable<String, String> table = new HashTable<>();
        table.put("A", "B");
        table.put("A", "A");
        assertTrue(table.containsValue("A"));
        assertEquals(1, table.size());
    }

    @Test
    void testPutRehash(){
        HashTable<Integer, String> table = new HashTable<>();
        for (int i = 0; i < 2000; i++){
            table.put(i, "A");
        }
        assertEquals(2000, table.size());
        table.put(1, "A");
        assertEquals(2000, table.size());
        table.put(2001, "A");
        assertEquals(2001, table.size());
    }

    @Test
    void testRemove(){
        HashTable<String, String> table = new HashTable<>();
        table.put("A", "B");
        table.put("B", "C");
        table.put("C", "D");
        assertEquals("B", table.remove("A"));
    }
}
