// Main Class for Phone Book Sort/Search Project
package phoneBook;


public class Main {

//    // Complete time to load data
//    public static void timeToImportData(String fileName) {
//
//        StopWatch timer = new StopWatch();
//        Data<String> data = new Data<>();
//
//        String[] a = data.importDataFromFile(fileName);
//        double elapsedTime = timer.elapsedTime(); // calculates elapsed time in seconds
//        System.out.println("Found " + a.length + " entries in file");
//
//        System.out.printf("Time to complete data import: %.3f seconds%n",  elapsedTime);
//    }

    public static void main(String[] args) {

            TestCases testCases = new TestCases();
            // Test Case 1 - Sort with merge sort, then search with linear search
            testCases.testCase1();

            // Test Case 1 - Sort with bubble sort, then search with jump search
            testCases.testCase2();

            // Test Case 3 - Sort with quick sort, then search with binary search
            testCases.testCase3();

            // Test Case 4 - Sort with merge sort, then search with hash table search
            testCases.testCase4();

    }

}
