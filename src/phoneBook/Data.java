// Class used to extract data from a file, data operations, and write data to file
package phoneBook;

import java.io.*;
import java.util.*;

public class Data<T extends Comparable<T>> {


    // Extract String data from file and return as List
    public List<String> extractDataFromFile(File file) throws FileNotFoundException {

        Scanner sc = new Scanner(file);
        List<String> data = new ArrayList<>();

        while (sc.hasNextLine()) {
            data.add(sc.nextLine());
        }

        return data;
    }

    // Export List of data to file
    public void exportDataToFile(List<T> list, String outputFileName)  {

        File outputFile = new File(outputFileName);
        try (FileWriter writer = new FileWriter(outputFile)) {

            PrintWriter printWriter = new PrintWriter(writer);
            for (T item : list) {
                printWriter.println(item);
            }
        }
        catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
        }
    }

    // Create array of items from data in a file
    public String[] importDataFromFile(String fileName)
    {

        // Extract data from text file
        File file = new File(fileName);

        List<String> dataList = new ArrayList<>();
        try {
            dataList = extractDataFromFile(file);
        }
        catch (FileNotFoundException e) {
            System.out.println(file + " not found.");
        }
        return convertListToStringArray(dataList); // Convert List to String[] array
    }


    // Determine whether one item is less than the other item
    public boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    // Swap one value with another value
    public void exchange(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    // Convert list to String[]
    public static String[] convertListToStringArray(List<String> list) {

        String[] array = new String[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    // Create HashTable
    public Hashtable<Integer, T> generateHashTable(T[] a) {

        Hashtable<Integer, T> hashTable = new Hashtable<>();

        for (int i = 0; i < a.length; i++) {
            hashTable.put(i, a[i]);
        }
        return hashTable;
    }


    // Place array data into a List
    public List<T> convertArrayToList(T[] array) {

        List<T> list = new ArrayList<>();
        Collections.addAll(list, array);
        return list;
    }

    // Find maximum value in list
    public T maxValue(List<T> list) {

        T maxValue = list.get(0); // initialize max value
        for (T value : list) {

            if (value.compareTo(maxValue) > 0) {
                maxValue = value;
            }
        }
        return maxValue;
    }

    // Find minimum value in list
    public T minValue(List<T> list) {

        T minValue = list.get(0); // initialize min value
        for (T value : list) {

            if (value.compareTo(minValue) < 0) {
                minValue = value;
            }
        }
        return minValue;
    }


    // Find number of occurrence of an element in a List
    public int numberOfElementOccurrencesInList(List<T> list, T value) {

        int numberOfOccurrences = 0;
        for (T element : list) {

            if (element == value) {
                numberOfOccurrences++;
            }
        }
        return numberOfOccurrences;
    }

    // Remove duplicates in list
    public List<T> removeDuplicates(List<T> list) {

        // Create a new LinkedHashSet

        // Add the elements to set
        Set<T> set = new LinkedHashSet<>(list);

        // Clear the list
        list.clear();

        // add the elements of set
        // with no duplicates to the list
        list.addAll(set);

        // return the list
        return list;
    }


}
