// Test Case Class used to represent different case of using
// different sort/search algorithms
package phoneBook;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.security.SecureRandom;

public class TestCases {

    // Generate random locations from a file and use as input for search
    public String[] generateRandomSearchValues(String[] array, int numberOfSearches) {

        // Initialize secure random number object
        SecureRandom random = new SecureRandom();

        // Generate array of random numbers
        int[] randomLocations = random.ints(numberOfSearches, 0, array.length).toArray();

        // Extract searchValues using randomLocations within input array
        String[] searchValues = new String[randomLocations.length];

        for (int i = 0; i < searchValues.length; i++) {
            searchValues[i] = array[randomLocations[i]];
        }
        return searchValues;
    }

    // Generic Test Case
    public void genericTestCase(String sortAlgorithmType,
                                String searchAlgorithmType,
                                int numberOfSearches) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file name: ");
        String fileName = scanner.next();

        // Import file
        Data<String> data = new Data<>();
        Data<Integer> dataIntegers = new Data<>();
        String[] a = data.importDataFromFile(fileName);

        // Begin sorting
        SortingAlgorithms<String> sortAlg = new SortingAlgorithms<>();
        sortAlg.sortingOperation(sortAlgorithmType, a);

        // Convert array to list
        List<String> outputList = data.convertArrayToList(a);

        // Export sorted data to file
        String outputFileName = fileName.split("[, ?.@]+")[0] + "_" + sortAlgorithmType +
                                            "_sorted.txt";
        data.exportDataToFile(outputList, outputFileName);


        // Search for specific values in list
        SearchAlgorithms searchAlg = new SearchAlgorithms();

        // Provide search values
        String[] searchValues = generateRandomSearchValues(a, numberOfSearches);

        String outputIndicesFileName;
        for (String searchValue : searchValues) {

            // Perform search using chosen algorithm
            List<Integer> searchIndices = searchAlg.searchOperation(searchAlgorithmType, a, searchValue);

            // Export search indices to list
            outputIndicesFileName = searchValue + "_indices.txt";
            dataIntegers.exportDataToFile(searchIndices, outputIndicesFileName);
        }

    }


    // Test Case 1 - Sort with bubble sort, then search with jump search
    public void testCase1() {

        genericTestCase("Merge", "Linear",
                10);
    }

    // Test Case 2 - Sort with bubble sort, then search with jump search
    public void testCase2() {

        genericTestCase("Bubble", "Jump",
                        10);
    }

    // Test Case 3 - Sort with quick sort, then search with binary search
    public void testCase3() {

        genericTestCase("Quick", "Binary",
                10);
    }

    // Test Case 4 - Sort with merge sort, then search with HashTable (Map)
    public void testCase4() {

        genericTestCase("Merge", "HashTable",
                100);
    }


}
