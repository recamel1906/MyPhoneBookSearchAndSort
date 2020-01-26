// Class containing different search algorithms
package phoneBook;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;


public class SearchAlgorithms {

    // Compute time to complete a sorting operation
    public List<Integer> searchOperation(String algorithmType, String[] array, String value) {

        Scanner scanner = new Scanner(System.in);
        StopWatch timer = new StopWatch();
        List<Integer> searchIndicesList = new ArrayList<>();
        if (algorithmType.equals("Linear")) {
            System.out.println("Search using linear search of " + value + "....");
            searchIndicesList = linearSearch(array, value);
        }
        if (algorithmType.equals("Jump")) {
            System.out.println("Search using jump search of " + value + "....");
            searchIndicesList = jumpSearch(array, value);
        }
        if (algorithmType.equals("Binary")) {
            System.out.println("Search using binary search of " + value + "....");
            searchIndicesList = binarySearch(array, value, 0, array.length - 1);
        }
        if (algorithmType.equals("HashTable")) {
            System.out.println("Search using hash table search of " + value + "....");
            searchIndicesList = hashTableSearch(array, value);
        }

        double elapsedTime = timer.elapsedTime(); // calculates elapsed time in seconds
        System.out.printf("Time to complete " + algorithmType + " search: %.5f seconds%n",  elapsedTime);

        return searchIndicesList;
    }

    // Linear search algorithm - Returns list of indices from search
    private static List<Integer> linearSearch(String[] array, String value) {
        List<Integer> searchIndicesList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(value) || array[i].contains(value)) {
                searchIndicesList.add(i);
            }
        }
        return searchIndicesList;
    }

    // Jump search algorithm - Returns list of indices from search
    private static List<Integer> jumpSearch(String[] array, String target) {

        List<Integer> searchIndicesList = new ArrayList<>();

        int currentRight = 0; // right border of the current block
        int prevRight = 0; // right border of the previous block

        /* If array is empty, the element is not found */
        if (array.length == 0) {
            return List.of();
        }

        /* Check the first element */
        if (array[currentRight].equals(target) || array[currentRight].contains(target)) {
            searchIndicesList.add(0);
            return searchIndicesList;
        }

        /* Calculating the jump length over array elements */
        int jumpLength = (int) Math.sqrt(array.length);

        /* Finding a block where the element may be present */
        while (currentRight < array.length - 1) {

            /* Calculating the right border of the following block */
            currentRight = Math.min(array.length - 1, currentRight + jumpLength);

            if (array[currentRight].compareTo(target) >= 0) {
                break; // Found a block that may contain the target element
            }

            prevRight = currentRight; // update the previous right block border
        }

        /* If the last block is reached and it cannot contain the target value => not found */
        if ((currentRight == array.length - 1) && target.compareTo(array[currentRight]) > 0) {
            return List.of();
        }

        /* Doing linear search in the found block */
        return backwardLinearSearch(array, target, prevRight, currentRight);
    }

    // Backwards linear search algorithm
    private static List<Integer> backwardLinearSearch(String[] array, String target, int leftExcl, int rightIncl) {

        List<Integer> searchIndicesList = new ArrayList<>();
        for (int i = rightIncl; i > leftExcl; i--) {
            if (array[i].compareTo(target) == 0 || array[i].contains(target)) {
                searchIndicesList.add(i);
            }
        }
        return searchIndicesList;
    }

    // Recursive Binary search algorithm - Returns list of indices from search
    private static List<Integer> binarySearch(String[] array, String value, int left, int right) {

        List<Integer> searchIndicesList = new ArrayList<>();
        if (left > right) {
            return List.of(); // search interval is empty, the element is not found
        }

        int mid = left + (right - left) / 2; // the index of the middle element

        if (value.compareTo(array[mid]) == 0 || value.contains(array[mid])) {
            searchIndicesList.add(mid);
            return searchIndicesList; // the element is found, return its index
        }
        else if (value.compareTo(array[mid]) < 0) {
            return binarySearch(array, value, left, mid - 1); // go to the left subarray
        }
        else {
            return binarySearch(array, value, mid + 1, right); // go the the right subarray
        }
    }

    // HashTable Search - Returns list of indices from search
    private List<Integer> hashTableSearch(String[] array, String value) {

        // generate hash table from array
        Data<String> data = new Data<>();
        Hashtable<Integer, String> hashTable = data.generateHashTable(array);

        // Look for value in hash table
        List<Integer> searchIndicesList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (hashTable.get(i).compareTo(value) == 0 || hashTable.get(i).contains(value)) {
                searchIndicesList.add(i);
            }
        }
        return searchIndicesList;
    }


}
