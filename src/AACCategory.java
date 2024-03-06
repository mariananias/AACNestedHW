/**
 * AACCategory provides facilities for converting the path to 
 * an image to the corresponding word.
 * 
 * @author Marina Ananias
 *
 */
public class AACCategory {
  
// Note that your AACCategory class will likely contain an AssociativeArray<String,String>
// that maps image locations (strings) to words (also strings).

  // +------------------+--------------------------------------------
  // | Standard Methods |
  // +------------------+

  /**
   * Adds the mapping of the imageLoc to the text to the category.
   *
   * @param imageLoc - the location of the image to add
   * @param text - the text that image maps to 
   */
  public void addItem​(String imageLoc, String text) {

  } // addItem​(String imageLoc, String text)

  /* Returns the name of the category */
  public String getCategory() {
    return "food"; // STUB
  } // getCategory()

  /* Returns an array of all the images in the category */
  public String[] getImages() {
    return new String[] { "img/food/icons8-french-fries-96.png", "img/food/icons8-watermelon-96.png" }; // STUB
  } // getImages()

  /** 
   * Returns the text associated with the given image loc in this category
   * 
   * @param imageLoc - the location of the image
   */
  public String getText​(String imageLoc) {
    return "television";  // STUB
  } // getText​(String imageLoc)

  /** 
   * Determines if the provided images is stored in the category
   *
   * @param imageLoc - the location of the category
   */
  public boolean hasImage​(String imageLoc) {
    return true; // STUB
  } // hasImage​(String imageLoc)

}
