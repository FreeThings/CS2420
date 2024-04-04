package assign09;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

public class HashTable<K, V> implements Map<K, V> {


    private int capacity;
    private int size;
    private ArrayList<LinkedList<MapEntry<K, V>>> table;

    public HashTable() {
        capacity = 200;
        size = 0;
        table = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            table.add(new LinkedList<MapEntry<K, V>>());
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            table.get(i).clear();
        }
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (size == 0)
            return false;

        for (MapEntry<K, V> entry : table.get(toHash(key))) {
            if (entry.getKey().equals(key))
                return true;
        }

        return false;
    }


    @Override
    public boolean containsValue(V value) {
        if (size == 0)
            return false;

        for (int i = 0; i < capacity; i++){
            for (MapEntry<K, V> entry : table.get(i)) {
                if (entry.getValue().equals(value))
                    return true;
            }
        }


        return false;
    }

    @Override
    public List<MapEntry<K, V>> entries() {
        List<MapEntry<K, V>> entries = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            entries.addAll(table.get(i));
        }
        return entries;
    }

    @Override
    public V get(K key) {
        if (!this.containsKey(key))
            return null;
        else {
            int code = toHash(key);
            for (MapEntry<K, V> entry : table.get(code)) {
                if (entry.getKey().equals(key))
                    return entry.getValue();
            }
        }
        return null;
    }

        @Override
        public boolean isEmpty () {
            return size <= 0;
        }

        @Override
        public V put (K key, V value){

            int code = toHash(key);
            if (table.get(code).isEmpty()) {
                if (size / capacity >= 10)
                    rehash();
                table.get(code).add(new MapEntry<>(key, value));
                size++;
                return null;
            } else {
                V prev = null;
                if (this.containsKey(key)) {
                    prev = this.get(key);
                    table.get(code).set(table.get(code).indexOf(new MapEntry<>(key, prev)), new MapEntry<>(key, value));
                } else {
                    if (size / capacity >= 10)
                        rehash();
                    table.get(code).add(new MapEntry<>(key, value));
                    size++;
                }
                return prev;
            }
        }

        @Override
        public V remove (K key){

            if (!this.containsKey(key))
                return null;

            int code = toHash(key);
            for (MapEntry<K, V> entry: table.get(code)){
                if (entry.getKey().equals(key)){
                    V value = entry.getValue();
                    table.get(code).remove(entry);
                    size--;
                    return value;
                }
            }
            return null;
        }

        @Override
        public int size () {
            return size;
        }


        private void rehash () {
            int newCapacity = capacity * 2;
            ArrayList<LinkedList<MapEntry<K, V>>> temp = new ArrayList<>(newCapacity);

            for (int i = 0; i < newCapacity; i++) {
                temp.add(new LinkedList<MapEntry<K, V>>());
            }

            for (int i = 0; i < capacity; i++) {
                if (!table.get(i).isEmpty()) {
                    for (MapEntry<K, V> entry : table.get(i)) {
                        int code = toHash(entry.getKey());
                        temp.get(code).add(entry);
                    }
                }
            }



            table = temp;
            capacity = newCapacity;
        }

        private int toHash(K key){
            return Math.abs(key.hashCode() % capacity);
        }

    }


