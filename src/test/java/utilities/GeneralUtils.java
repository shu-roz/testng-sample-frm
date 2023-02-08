package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class GeneralUtils {


    public static Object[][] readFromCsvAs2DArray (String pathToCSVFile) {

        Scanner scanner = null;

        try {
            scanner = new Scanner(new File(pathToCSVFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<String[]> list = new ArrayList<>();
        while(scanner.hasNextLine()){
            list.add(scanner.nextLine().split(","));
        }

        int rows = list.size();
        int columns = list.get(0).length;

        Object [][] arr = new Object[rows][columns];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }

        scanner.close();
        return arr;
    }




}
