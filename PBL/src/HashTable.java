public class HashTable {
    public class Entry { // Inner class Entry representing key-value pairs in a linked list manner
        Object key, value; // Key and value of the entry
        Entry next; // Reference to the next entry in the linked list

        Entry(Object k, Object v, Entry n) { // Constructor for Entry class
            key = k; // Initialize key
            value = v; // Initialize value
            next = n; // Initialize next reference
        }

        public String toString() { // Overrides toString method to represent Entry as "key=value"
            return key + "=" + value; // Returns key=value pair
        }
    }

    private Entry[] entries; // Array to hold the entries
    private int size; // Number of entries in the table
    private float loadFactor; // Load factor for determining when to rehash

    public HashTable(int capacity, float loadFactor) { // Constructor with initial capacity and load factor
        entries = new Entry[capacity]; // Initialize entries array with given capacity
        this.loadFactor = loadFactor; // Initialize load factor
    }

    public HashTable(int capacity) { // Constructor with only capacity, defaults load factor to 0.75
        this(capacity, 0.75F); // Calls the other constructor with default load factor
    }

    public HashTable() { // Default constructor, initializes with default capacity and load factor
        this(101); // Calls the constructor with default capacity
    }

    public Object get(Object key) { // Retrieves value associated with given key
        int h = hash(key); // Calculate hash for the key
        for (Entry e = entries[h]; e != null; e = e.next) { // Iterate through the linked list at the hashed index
            if (e.key.equals(key)) // If the key matches
                return e.value; // Return the associated value
        }
        return null; // Return null if key not found
    }

    public Object put(Object key, Object value) { // Inserts or updates a key-value pair
        int h = hash(key); // Calculate hash for the key
        for (Entry e = entries[h]; e != null; e = e.next) { // Iterate through the linked list at the hashed index
            if (e.key.equals(key)) { // If key already exists
                Object oldValue = e.value; // Save the old value
                e.value = value; // Update the value
                return oldValue; // Return the old value
            }
        }
        entries[h] = new Entry(key, value, entries[h]); // Create a new entry and link it to the existing list
        ++size; // Increment the size
        if (size > loadFactor * entries.length) // Check if rehashing is needed
            rehash(); // Rehash if necessary
        return null; // Return null as there was no previous value
    }

    public Object remove(Object key) { // Removes the entry associated with the given key
        int h = hash(key); // Calculate hash for the key
        for (Entry e = entries[h], prev = null; e != null; prev = e, e = e.next) { // Iterate through the linked list
            if (e.key.equals(key)) { // If key is found
                Object oldValue = e.value; // Save the old value
                if (prev == null) // If it's the first entry
                    entries[h] = e.next; // Update the head of the linked list
                else
                    prev.next = e.next; // Skip the current entry
                --size; // Decrement the size
                return oldValue; // Return the old value
            }
        }
        return null; // Return null if key not found
    }

    public int size() { // Returns the number of entries in the table
        return size; // Returns the size
    }

    public int hash(Object key) { // Hash function for given key
        if (key == null) // If key is null
            throw new IllegalArgumentException(); // Throw exception
        return (key.hashCode() & 0X7FFFFFFF) % entries.length; // Calculate hash code and apply modulo to fit within array bounds
    }

    private void rehash() { // Rehashing method
        Entry[] oldEntries = entries; // Save the current entries
        entries = new Entry[2 * oldEntries.length + 1]; // Double the array size plus one for odd
        for (int k = 0; k < oldEntries.length; k++) { // Iterate through the old entries
            for (Entry old = oldEntries[k]; old != null; ) { // Iterate through the linked list of each entry
                Entry e = old; // Save the current entry
                old = old.next; // Move to the next entry
                int h = hash(e.key); // Calculate new hash for the entry
                e.next = entries[h]; // Link the entry to the new list
                entries[h] = e; // Update the list head
            }
        }
    }
}
