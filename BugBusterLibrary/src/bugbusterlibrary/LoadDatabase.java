/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bugbusterlibrary;

import bugbusterlibrary.dao.CategoryDao;
import bugbusterlibrary.entity.Category;
import bugbusterlibrary.ui.BugBusterLibraryUI;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Takudzwa Mzembegwa
 */
public class LoadDatabase {
    
    /**
     * Batch saves categories from a csv file
     *@param path the path to the csv file with categories to be persisted 
     */
     public static void loadCategory(String path) {
        try (BufferedReader csvReader = new BufferedReader(new FileReader(path))) {
            csvReader.readLine();
            String row;
            int i = 0;
            CategoryDao categoryDao = new CategoryDao();
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                Category category = new Category();
                category.setFaculty(data[0]);
                category.setDepartment(data[1]);
                categoryDao.persist(category);
            }
            csvReader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BugBusterLibraryUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BugBusterLibraryUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
