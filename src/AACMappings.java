/**
 * AACMappings provide facilities for converting the path to an 
 * image to the corresponding category.
 * 
 * @author Marina Ananias
 *
 */
public class AACMappings {
  
// Note that your AACMappings class will likely contain an AACCategory object that maps filenames
// to their corresponding words. You may also want an AssociativeArray<String,AACCategory> that maps
// either filenames or names to categories.

/** 
 * Adds the mapping to the current category (or the default category if that is the current category)
 * 
 * @param imageLoc - the location of the image
 * @param text - the text associated with the image 
 */
  public void add​(String imageLoc,String text) {

  } // add​(String imageLoc,String text) 

  /* Returns the current category or the empty string if on the default category */
  public String getCurrentCategory() {
    return "food";  // STUB
  } // getCurrentCategory()

  /* Provides an array of all the images in the current category */
  public String[] getImageLocs() {
    return new String[] { "img/food/icons8-french-fries-96.png", "img/food/icons8-watermelon-96.png" }; // STUB
  } // getImageLocs()

  /** 
   * Given the image location selected, it determines and returns the associated text with 
   * the image. If the image provided is a category, it also updates the AAC's current category 
   * to be the category associated with that image.
   *
   * @param imageLoc - the location where the image is stored */
  public String getText​(String imageLoc) {
    return "television";  // STUB
  } // getText​(String imageLoc)

  /**
   * Determines if the image represents a category or text to speak.
   * Returns true if the image represents a category, false if the image represents text to speak 
   *
   * @param imageLoc - the location where the image is stored  */
  public boolean isCategory​(String imageLoc) {
    return true; // STUB
  } // isCategory​(String imageLoc)

  /* Resets the current category of the AAC back to the default category */
  public void reset() {

  } // reset()

  /** 
   * Writes the ACC mappings stored to a file. The file is formatted as the text location of the 
   * category followed by the text name of the category and then one line per item in the category
   * that starts with > and then has the file name and text of that image for instance: 
   * img/food/plate.png food >img/food/icons8-french-fries-96.png french fries >img/food/icons8-
   * watermelon-96.png watermelon img/clothing/hanger.png clothing >img/clothing/collaredshirt.png 
   * collared shirt represents the file with two categories, food and clothing and food has french 
   * fries and watermelon and clothing has a collared shirt
   * 
   * @param filename - the name of the file to write the AAC mapping to  */
  public void writeToFile​(String filename) {

  } // writeToFile​(String filename)
} // class AACMappings
