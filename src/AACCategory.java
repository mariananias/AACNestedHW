import structures.AssociativeArray;
import structures.KeyNotFoundException;
import structures.NullKeyException;

/**
 * AACCategory provides facilities for converting the path to 
 * an image to the corresponding word.
 * 
 * @author Marina Ananias
 *
 */
public class AACCategory {
  
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  String categoryName;

  // Key: Location
  // Value: Text Associated
  AssociativeArray<String, String> words;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  public AACCategory(String categoryName) {
    // Initialize the category name
    this.categoryName = categoryName;
    // Initialize the associative array for words
    this.words = new AssociativeArray<String, String>();
  }

  // +------------------+--------------------------------------------
  // | Standard Methods |
  // +------------------+

  /**
   * Adds the mapping of the imageLoc to the text to the category.
   *
   * @param imageLoc - the location of the image to add
   * @param text - the text that image maps to 
   * @throws NullKeyException 
   */
  public void addItem​(String imageLoc, String text) throws NullKeyException {
    this.words.set(imageLoc, text);
  } // addItem​(String imageLoc, String text)

  /* Returns the name of the category */
  public String getCategory() {
    return this.categoryName;
  } // getCategory()

  /* Returns an array of all the image locations in the category */
  public String[] getImages() {
    String[] imgLocArr = new String[this.words.size()];

    for (int i = 0; i < this.words.size(); i++) {
      imgLocArr[i] = this.words.getKVPairKey(i);
    }

    return imgLocArr; 
  } // getImages()

  /** 
   * Returns the text associated with the given image loc in this category
   * 
   * @param imageLoc - the location of the image
   */
  public String getText​(String imageLoc) {
    try {
      return this.words.get(imageLoc);
    } catch (KeyNotFoundException e) {
      e.printStackTrace();
      return "error";
    }
  } // getText​(String imageLoc)

  /** 
   * Determines if the provided images is stored in the category
   *
   * @param imageLoc - the location of the category
   */
  public boolean hasImage​(String imageLoc) {
    try {
      if (this.words.find(imageLoc) >= 0) {
        return true;
      }
      else {
        return false;
      }
    } catch (KeyNotFoundException e) {
      e.printStackTrace();
      return false;
    }
  } // hasImage​(String imageLoc)
} // public class AACCategory

