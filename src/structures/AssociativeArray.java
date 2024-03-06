package structures;

import static java.lang.reflect.Array.newInstance;

/**
 * A basic implementation of Associative Arrays with keys of type K
 * and values of type V. Associative Arrays store key/value pairs
 * and permit you to look up values by key.
 *
 * @author Marina Ananias
 * @author Samuel A. Rebelsky
 */
public class AssociativeArray<K, V> {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * The default capacity of the initial array.
   */
  static final int DEFAULT_CAPACITY = 16;

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The size of the associative array (the number of key/value pairs).
   */
  int size;

  /**
   * The array of key/value pairs.
   */
  KVPair<K, V> pairs[];

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new, empty associative array.
   */
  @SuppressWarnings({ "unchecked" })
  public AssociativeArray() {
    // Creating new arrays is sometimes a PITN.
    this.pairs = (KVPair<K, V>[]) newInstance((new KVPair<K, V>()).getClass(),
        DEFAULT_CAPACITY);
    this.size = 0;
  } // AssociativeArray()

  // +------------------+--------------------------------------------
  // | Standard Methods |
  // +------------------+

  /**
   * Create a copy of this AssociativeArray.
   */
  public AssociativeArray<K, V> clone() {
    AssociativeArray<K, V> newAssociativeArray = new AssociativeArray<K, V>();
    return newAssociativeArray;
  } // clone()

  /**
   * Convert the array to a string.
   */
  public String toString() {
    
    // If array has no elements, is empty
    if (this.size == 0) {
      return "{}";
    }

    else {
      // Initializing strings 
      String pAll = "";
      String p = "";

      for (int i = 0; i < this.size; i++) {
        p = pairs[i].key + ": " + pairs[i].value;

        if (i == this.size - 1) {
          pAll += p;
        }

        else {
          pAll += p + ", ";
        }

      } // for
      return ("{ " + pAll + " }");
    } // else
  } // toString()

  // +----------------+----------------------------------------------
  // | Public Methods |
  // +----------------+

  /**
   * Set the value associated with key to value. Future calls to
   * get(key) will return value.
   */
  public void set(K key, V value) throws NullKeyException {
    // Handles null keys
    if (key == null) {
      throw new NullKeyException();
    }

    // Expands array capacity if needed
    if (this.size >= pairs.length) {
      this.expand();
    }

    // If key already exists, changes to new value
    try {
      (this.pairs[this.find(key)]).value = value;
    } 
    
    // If key does not already exist
    catch(Exception e) { 
      int i;
      boolean n = false;

      for (i = 0; i < this.size; i++) {
        
        // If first pair to be set
        if (this.size == 0) {
          this.pairs[this.size++] = new KVPair<K,V>(key,value);
        }

        // If there are already a/multiple null(s) - to be continued on next "if"
        else if (pairs[i] == null) {
          n = true;
          break;
        } // if
      } // for

      // If there are already a/multiple null(s), sets new pair to be the last on the left, before every null
      if (n) { 
        this.pairs[i] = new KVPair<K,V>(key,value);
        this.pairs[this.size++] = null;
      }

      // If there are not any null(s), just set new pair at the end of the array
      else {
        this.pairs[this.size++] = new KVPair<K,V>(key,value);
      }

    } // catch
  } // set(K,V)

  /**
   * Get the value associated with key.
   *
   * @throws KeyNotFoundException
   * when the key is null or does not 
   * appear in the associative array.
   */
  public V get(K key) throws KeyNotFoundException {
    // Handles null keys
    if (key == null) {
      throw new KeyNotFoundException();
    } // if

    // If key exists
    try {
      return (this.pairs[this.find(key)].value);
    }

    // If key does not exist
    catch (Exception e) {
      throw new KeyNotFoundException();
    }

  } // get(K)

  /**
   * Determine if key appears in the associative array. Should
   * return false for the null key.
   */
  public boolean hasKey(K key) {
    // Handles null keys
    if (key == null) {
      return false;
    } // if

    // If key exists
    try {
      if (this.find(key) >= 0) {
        return true;
      }
    }
    // If key does not exist
    catch (KeyNotFoundException e) { }
    
    return false;
  } // hasKey(K)

  /**
   * Remove the key/value pair associated with a key. Future calls
   * to get(key) will throw an exception. If the key does not appear
   * in the associative array, does nothing.
   * @throws KeyNotFoundException 
   */
  public void remove(K key) {

    // If key already exists
    try {
      for (int i = this.find(key); i < this.size; i++) {
        if (i >= (this.size - 1)) {
          this.pairs[i] = null;
          break;
        } // if
        this.pairs[i] = pairs[i+1];
      } // for
      this.size--;
    } // try
    // If key does not exist, do nothing.
    catch(Exception e) { }
  } // remove(K)

  /**
   * Determine how many key/value pairs are in the associative array.
   */
  public int size() {
    return this.size;
  } // size()

  // +-----------------+---------------------------------------------
  // | Private Methods |
  // +-----------------+

  /**
   * Expand the underlying array.
   */
  public void expand() {
    this.pairs = java.util.Arrays.copyOf(this.pairs, this.pairs.length * 2); // should we use that when we are updating size?
  } // expand()

  /**
   * Find the index of the first entry in `pairs` that contains key.
   * If no such entry is found, throws an exception.
   */
  public int find(K key) throws KeyNotFoundException {
    // Handles null keys
    if (key == null) {
      throw new KeyNotFoundException();
    } 

    // Iterate through array looking for key
    for (int i = 0; i < this.size; i++) {
      if (key.equals(this.pairs[i].key)) {
        return i;
      }
    }

    throw new KeyNotFoundException();
  } // find(K)

} // class AssociativeArray
