package pract3;

import java.util.Locale;
import java.util.Random;

/** Class MeasuringSortingAlgorithms: Empirical analysis of the temporal cost of sorting algorithms
 *  @author PRG - ETSInf
 *  @version Curso 2025-2026
 */
class MeasuringSortingAlgorithms {
    // Constants to be used when varying the measuring parameters
    public static final int MIN_SIZE = 1000, 
                            MAX_SIZE = 10000;
    public static final int STEP_OF_SIZE = 1000,
                            REPETITIONS = 200;
    public static final double NMS = 1e3;  // ratio microseconds/nanoseconds


    private static final Random generator = new Random(); // Generator of random numbers

  
    /* Creates an array of length size 
     * @param size int, size of the array
     * @result int[], created array
     */
    private static int[] createArray(int size) { 
        int[] a = new int[size];
        return a;
    }
  
    /* Fills an int array with random values
     * @param a int[], array to be filled
     */
    private static void fillArrayRandom(int[] a) {
        int length = a.length;
        for(int i = 0; i < length; ++i)
            a[i] = generator.nextInt();
    }

    /* Fills an int array sorted in ascending order
     * @param a int[], array to be filled
     */
    private static void fillArraySortedInAscendingOrder(int[] a) { 
        int length = a.length;
        for(int i = 0; i < length; ++i)
            a[i] = i;
    }

    /* Fills an int array sorted in descending order
     * @param a int[], array to be filled
     */
    private static void fillArraySortedInDescendingOrder(int[] a) {
        int length = a.length;
        int lengthMinusOne = length - 1;
        for(int i = 0; i < length; ++i)
            a[i] = lengthMinusOne - i;
    }

    public static void measuringSelectionSort() {
        long ti = 0, // Initial timestamp
             tf = 0, // Final timestamp
             tt = 0; // Total timestamp

        Random generator = new Random(); // Generator of random numbers

        // Print result header
        System.out.printf("# Selection sort. Time expressed in microseconds\n");
        System.out.printf("#     Size        Random         Ascending         Descending\n");
        System.out.printf("#------------------------------------------------------------\n");

        // Loop for different sizes
        for(int size = MIN_SIZE; size <= MAX_SIZE; size += STEP_OF_SIZE) {

            // Create the array
            int[] a = createArray( size );
      
            tt = 0;
            for(int r = 0; r < REPETITIONS; r++) {
                fillArrayRandom(a);
                ti = System.nanoTime();
                MeasurableAlgorithms.selectionSort(a);
                tf = System.nanoTime();
                tt += (tf - ti);
            }
            // Average running time for the worst case
            double randomTime = (double) tt / REPETITIONS;
            
            
            tt = 0;
            for(int r = 0; r < REPETITIONS; r++) {
                fillArraySortedInAscendingOrder(a);
                ti = System.nanoTime();
                MeasurableAlgorithms.selectionSort(a);
                tf = System.nanoTime();
                tt += (tf - ti);
            }
            // Average running time for the worst case
            double ascendingTime = (double) tt / REPETITIONS;

            
            tt = 0;
            for(int r = 0; r < REPETITIONS; r++) {
                fillArraySortedInDescendingOrder(a);
                ti = System.nanoTime();
                MeasurableAlgorithms.selectionSort(a);
                tf = System.nanoTime();
                tt += (tf - ti);
            }
            // Average running time for the worst case
            double descendingTime = (double) tt / REPETITIONS;
            
            // Print results
            System.out.printf(Locale.US, "%10d %12.2f %12.2f %12.2f\n", 
                              size, randomTime / NMS, ascendingTime / NMS, descendingTime / NMS);
        }
    }

    public static void measuringInsertionSort() { 
        long ti = 0, // Initial timestamp
             tf = 0, // Final timestamp
             tt = 0; // Total timestamp

        Random generator = new Random(); // Generator of random numbers

        // Print result header
        System.out.printf("# Insertion sort. Time expressed in microseconds\n");
        System.out.printf("#     Size        Random         Ascending         Descending\n");
        System.out.printf("#------------------------------------------------------------\n");

        // Loop for different sizes
        for(int size = MIN_SIZE; size <= MAX_SIZE; size += STEP_OF_SIZE) {

            // Create the array
            int[] a = createArray( size );
      
            tt = 0;
            for(int r = 0; r < REPETITIONS; r++) {
                fillArrayRandom(a);
                ti = System.nanoTime();
                MeasurableAlgorithms.insertionSort(a);
                tf = System.nanoTime();
                tt += (tf - ti);
            }
            // Average running time for the worst case
            double randomTime = (double) tt / REPETITIONS;
            
            
            tt = 0;
            for(int r = 0; r < REPETITIONS; r++) {
                fillArraySortedInAscendingOrder(a);
                ti = System.nanoTime();
                MeasurableAlgorithms.insertionSort(a);
                tf = System.nanoTime();
                tt += (tf - ti);
            }
            // Average running time for the worst case
            double ascendingTime = (double) tt / REPETITIONS;

            
            tt = 0;
            for(int r = 0; r < REPETITIONS; r++) {
                fillArraySortedInDescendingOrder(a);
                ti = System.nanoTime();
                MeasurableAlgorithms.insertionSort(a);
                tf = System.nanoTime();
                tt += (tf - ti);
            }
            // Average running time for the worst case
            double descendingTime = (double) tt / REPETITIONS;
            
            // Print results
            System.out.printf(Locale.US, "%10d %12.2f %12.2f %12.2f\n", 
                              size, randomTime / NMS, ascendingTime / NMS, descendingTime / NMS);
        }
    }
  
    private static void help() {
        System.out.println("Usage: java MeasurigSortingAlgorithms <algorithm_number>");
        System.out.println("   Where <algorithm_number> can be: ");
        System.out.println("   1 -> Selection Sort");
        System.out.println("   2 -> Insertion Sort");
    }

    public static void main(String[] args) {
        if (args.length != 1) { help(); }
        else {
            try {
                int a = Integer.parseInt(args[0]);
                switch (a) {
                    case 1: 
                        measuringSelectionSort(); 
                        break;
                    case 2: 
                        measuringInsertionSort(); 
                        break;
                    default: 
                        help();
                }
            } catch (Exception e) { help(); }
        }
    }
}
