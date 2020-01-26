// Class used to contain different sorting algorithms
package phoneBook;


public class SortingAlgorithms<T extends Comparable<T>> extends Data<T> {

    // Choose sorting algorithm and compute time to complete operation
    public void sortingOperation(String algorithmType, T[] array) {

        StopWatch timer = new StopWatch();

        if (algorithmType.equals("Bubble")) {
            System.out.println("Sorting using bubble sort....");
            bubbleSort(array);
        }
        if (algorithmType.equals("Quick")) {
            System.out.println("Sorting using quick sort....");
            quickSort(array, 0, array.length - 1);
        }
        if (algorithmType.equals("Selection")) {
            System.out.println("Sorting using selection sort....");
            selectionSort(array);
        }
        if (algorithmType.equals("Insertion")) {
            System.out.println("Sorting using insertion sort....");
            insertionSort(array);
        }
        if (algorithmType.equals("Shell")) {
            System.out.println("Sorting using shell sort....");
            shellSort(array);
        }
        if (algorithmType.equals("Merge")) {
            System.out.println("Sorting using merge sort....");
            mergeSort(array, 0, array.length);
        }
        double elapsedTime = timer.elapsedTime(); // calculates elapsed time in seconds
        System.out.printf("Time to complete " + algorithmType + " sort: %.3f seconds%n",  elapsedTime);
    }

    // Bubble Sort algorithm
    private void bubbleSort(T[] array) {

        int n = array.length;
        for (int i = 0; i < n - 1; i++) {

            for (int j = 0; j < n - 1; j++) {

                if (array[j].compareTo(array[j+1]) > 0) {
                    exchange(array, j, j + 1);
                }
            }
        }

    }

    // Method that takes last element as pivot, places pivot element at its correct
    // position in sorted array, and places all smaller items than pivot to left
    // of pivot and all greater elements to right of pivot
    private int partition(T[] array, int low, int high) {

        T pivot = array[high];
        int i = low - 1; // index of smaller element

        for (int j = low; j < high; j++) {

            if (array[j].compareTo(pivot) < 0) {
                i++;
                exchange(array, i, j);
            }

        }

        // swap elements
        exchange(array, i + 1, high);
        return i + 1;
    }

    // Quick Sort algorithm
    private void quickSort(T[] array, int low, int high) {

        if (low < high) {
            int partitionIndex = partition(array, low, high);

            // Separately sort elements before partition and after partition
            quickSort(array, low, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, high);
        }
    }


    // Selection Sort algorithm
    private void selectionSort(T[] array) {

        // Sort array into increasing order
        int n = array.length;
        for (int i= 0; i < n; i++) {

            // Exchange a[i] with smallest entry in a[i],...,a[n-1]
            int min = i; // index of minimal entry
            for (int j = i + 1; j < n; j++) {

                if (less(array[j], array[min])) {
                    min = j;
                }
                exchange(array, i, min);
            }
        }

    }

    // Insertion Sort algorithm
    private void insertionSort(T[] array) {

        // Sort array in increasing order
        int n = array.length;
        for (int i = 1; i < n; i++) {

            // Insert a[i] among a[i-1], a[i-2], a[i-3],...
            for (int j = i; j > 0 && less(array[j], array[j-1]); j--) {
                exchange(array, j, j-1);
            }
        }
    }

    // Shell Sort algorithm
    private void shellSort(T[] array) {

        // Sort array in increasing order
        int n = array.length;
        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {

            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(array[j], array[j-h]); j -= h) {
                    exchange(array, j, j - h);
                }
            }
            h = h/3;
        }
    }


    // Merge Sort algorithm
    private void mergeSort(T[] array, int leftIncl, int rightExcl) {

        // the base case: if subarray contains <= 1 items, stop dividing because it's sorted
        if (rightExcl <= leftIncl + 1) {
            return;
        }

        /* divide: calculate the index of the middle element */
        int middle = leftIncl + (rightExcl - leftIncl) / 2;

        mergeSort(array, leftIncl, middle);  // conquer: sort the left subarray
        mergeSort(array, middle, rightExcl); // conquer: sort the right subarray

        /* combine: merge both sorted subarrays into sorted one */
        merge(array, leftIncl, middle, rightExcl);
    }

    // Inner details of merge sort algorithm
    private void merge(T[] array, int left, int middle, int right) {
        int i = left;   // index for the left subarray
        int j = middle; // index for the right subarray
        int k = 0;      // index for the temp subarray

        //@SuppressWarnings (value="unchecked")
        Object[] temp = new Object[right - left]; // temporary array for merging

        /* get the next lesser element from one of two subarrays
       and then insert it in the array until one of the subarrays is empty */
        while (i < middle && j < right) {
            if (array[i].compareTo(array[j]) <= 0) {
                temp[k] = array[i];
                i++;
            } else {
                temp[k] = array[j];
                j++;
            }
            k++;
        }

        /* insert all the remaining elements of the left subarray in the array */
        for (;i < middle; i++, k++) {
            temp[k] = array[i];
        }

        /* insert all the remaining elements of the right subarray in the array */
        for (;j < right; j++, k++) {
            temp[k] = array[j];
        }

        /* effective copying elements from temp to array */
        System.arraycopy(temp, 0, array, left, temp.length);
    }










}
