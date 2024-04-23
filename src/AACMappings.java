import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader; 
import java.io.FileReader; 
import java.io.IOException;
import structures.AssociativeArray;
import structures.KeyNotFoundException;
import structures.NullKeyException;

/**
 * AACMappings provide facilities for converting the path to an 
 * image to the corresponding category.
 * 
 * @author Marina Ananias
 *
 */
public class AACMappings {
  
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+
  
  // Key: Category Name
  // Value: AACCategory (k: imgLoc, v: txt)
  AssociativeArray<String, AACCategory> categoryMap;

  String defaultCategory;
  
  String currentCategory;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  public AACMappings(String filename) {
      // Initialize the category map and default category
      this.categoryMap = new AssociativeArray<String, AACCategory>();
      this.defaultCategory = "";
      this.currentCategory = defaultCategory;
      
      try {
        categoryMap.set(defaultCategory, new AACCategory(""));
      } catch (NullKeyException e) {
        e.printStackTrace();
      }

      try {
        readFromFile(filename);
      } catch (IOException e) {
        e.printStackTrace();
      }
  }
  // +------------------+--------------------------------------------
  // | Standard Methods |
  // +------------------+

  /** 
   * Reads contente from filename and populates categoryMap
   * 
   * @param filename - the name of the file to read from
   * @throws IOException 
   * */
  public void readFromFile(String filename) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

      String line = reader.readLine();

      // Read file until find a null line
      while (line != null) {
        // Split the line by space
        String[] splittedLine = line.split(" ");

        // The first part is the image location
        String imageLoc = splittedLine[0];

        // The other part(s) is the text associated with the image
        String text = splittedLine[1];
        if (splittedLine.length > 2) {
          text += " " + splittedLine[2];
        }
      
        // If it is a category, update the current category
        String[] categoryParts = imageLoc.split("/"); // Split the category path
        if (isCategory​(imageLoc)) {
            // Create a new AACCategory for this category
            this.categoryMap.set(text, new AACCategory(categoryParts[1]));
            this.add(imageLoc, text);
        } else {
            // Add the item to the current category
            imageLoc = imageLoc.replaceAll(">", "");
            this.categoryMap.get(categoryParts[1]).addItem​(imageLoc, text);
        }
        // Read next line
        line = reader.readLine();
      } // while
    } catch (IOException | NullKeyException | KeyNotFoundException e) {
        // Handle the exceptions here
        e.printStackTrace();
    } // catch
} // readFromFile

  /** 
   * Adds the mapping to the current category (or the default category if that is the current category)
   * 
   * @param imageLoc - the location of the image
   * @param text - the text associated with the image 
   */
  public void add(String imageLoc, String text) {
    try {
      this.categoryMap.get(currentCategory).addItem​(imageLoc, text);
    } catch (NullKeyException e) {
      e.printStackTrace();
    } catch (KeyNotFoundException e) {
      e.printStackTrace();
    }
  } // add​(String imageLoc,String text) 

  /* Returns the current category or the empty string if on the default category */
  public String getCurrentCategory() {
    if (this.currentCategory.equals(this.defaultCategory)) {
      return "";
    }
    else {
      return this.currentCategory;
    }
  } // getCurrentCategory()

  /* Provides an array of all the images in the current category */
  public String[] getImageLocs() {
    try {
      // System.out.println("Current Category: " + currentCategory);
      return categoryMap.get(currentCategory).getImages();
    } catch (Exception e) {
        // Handle the exception appropriately, such as logging an error message
        e.printStackTrace();
        // Return an empty array to avoid NullPointerException
        return new String[0];
    }

  } // getImageLocs()

  /** 
   * Given the image location selected, it determines and returns the associated text with 
   * the image. If the image provided is a category, it also updates the AAC's current category 
   * to be the category associated with that image.
   *
   * @param imageLoc - the location where the image is stored 
   * */
  public String getText(String imageLoc) {

    // Split the category path
    String[] parts = imageLoc.split("/");

    // Initializes text
    String text = "";

    // Extract the text associated with the image
    try {
      text = categoryMap.get(currentCategory).getText​(imageLoc);
    } catch (KeyNotFoundException e) {
      e.printStackTrace();
    }

    // If it is a category, update the current category
    if (isCategory​(imageLoc)) {
      // Extract the category name
      currentCategory = parts[1];
    }
  
    return text;

  } // getText​(String imageLoc)

  /**
   * Determines if the image represents a category or text to speak.
   * Returns true if the image represents a category, false if the image represents text to speak 
   *
   * @param imageLoc - the location where the image is stored  
   * */
  public boolean isCategory​(String imageLoc) {

    // Trim any leading or trailing spaces
    imageLoc = imageLoc.trim();

    // If it's a category
    if (imageLoc.startsWith("img/")) {
        return true;
    }
    // If it's a text to speak
    else if (imageLoc.startsWith(">")) {
        return false;
    } 
    else {
      return false;
    }
  } // isCategory​(String imageLoc)

  /* Resets the current category of the AAC back to the default category */
  public void reset() {
    currentCategory = defaultCategory;
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
   * @param filename - the name of the file to write the AAC mapping to  
   * @throws IOException */
  public void writeToFile(String filename) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
      for (int i = 0; i < categoryMap.size(); i++) {
        String category = categoryMap.getKVPairKey(i);
        try {
          for (String location : this.categoryMap.get(category).getImages()) {
            writer.write(location + this.categoryMap.get(category).getText​(location) + '\n');
          }
        } catch (KeyNotFoundException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
      } // for
    } // try 
  } // writeToFile(String filename)
} // class AACMappings
