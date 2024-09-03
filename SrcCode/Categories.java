package SrcCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Omar Mamon
 */

public class Categories {
  private ArrayList<SubCategories> subCategories;
  private File file;

  public Categories(File file) throws FileNotFoundException {
    this.file = file;
    this.subCategories = new ArrayList<>(); // Initialize subCategories
    try (Scanner scanner = new Scanner(this.file)) {
      while (scanner.hasNextLine()) {
        String path = scanner.nextLine();
        SubCategories subCat = new SubCategories(path.substring(path.lastIndexOf("\\") + 1));
        subCategories.add(subCat);
      }
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public ArrayList<SubCategories> getSubCategories() {
    return subCategories;
  }

  public void printDetails() {
    for (SubCategories subCategory : subCategories) {
      System.out.println("Subcategory: " + subCategory.getNameOfCategory());
      subCategory.printCategoryDetails();
    }
  }

  public void searchSubCategory(String name) {
    SubCategories foundSubCategory = null;
    for (SubCategories subCategory : getSubCategories()) {
      if (subCategory.getNameOfCategory().equalsIgnoreCase(name)) {
        System.out.println("Subcategory found:");
        subCategory.printCategoryDetails();
        foundSubCategory = subCategory;
        break;
      }
    }
    if (foundSubCategory == null) {
      System.out.println("Subcategory not found... Adding new subcategory...");
      try {
        SubCategories newSubCategory = new SubCategories(name);
        getSubCategories().add(newSubCategory);
      } catch (FileNotFoundException e) {
        System.err.println("Failed to create new subcategory: " + e.getMessage());
      }
    }
  }

//  public static void main(String[] args)
//      throws NullPointerException, IOException {
//    Categories categories = new Categories(new File("data\\Categories\\SubCategories.txt"));
//    // categories.printDetails();
//    categories.searchSubCategory("Fashion");
//    // categories.searchSubCategory("test(Ana t3baaaaaaaaaaaaaaaan)");
//
//  }
}
