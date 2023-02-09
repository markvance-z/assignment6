import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class App {

    public static int[] createRandomArray(int arrayLength) {
        Random random = new Random();
        int[] array = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = random.nextInt(100);
        }
        return array;
    }

    public static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length-1; i++) {
            if(array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static void bubbleSort(int[] a) {
        for (int i = a.length; i > 0; i--) {
            for( int j = 0; j < i - 1; j++) {
                if (a[j] > a[j+1]) {
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
        }
    }

    public static void writeArrayToFile(int[] array, String filename) {
        try (FileWriter fileWriter = new FileWriter(filename)) {
            for (int i = 0; i < array.length; i++) {
                fileWriter.write(array[i] + "\n");
            }
        } catch (IOException e) {
            
        }
    }

    public static int[] readFileToArray(String filename){
        ArrayList<Integer> arrayList = new ArrayList<>();
        try {
            File file = new File("temp.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                arrayList.add(Integer.parseInt(s));
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("An error occured.");
        }

        int[] array = new int[arrayList.size()];
        for (int i =0; i < array.length; i++) {
            array[i] = arrayList.get(i);
        }
        return array; 

    }

    public static void main(String[] args) throws Exception {
        
        //get array length from user and create random array
        Scanner scanner = new Scanner(System.in);
        System.out.println("PLease input the array length: ");
        int arrayLength = scanner.nextInt();
        int[] array = createRandomArray(arrayLength);

        //write array to file
        writeArrayToFile(array, "temp.txt");
        readFileToArray("temp.txt");

        //print initial array and check if sorted
        System.out.println(Arrays.toString(array));
        System.out.println("Before sorting, isSorted(array): " + isSorted(array) + "\n");
        
        //call bubbleSort
        bubbleSort(array);
        
        writeArrayToFile(array, "sortedTemp.txt");
        readFileToArray("sortedTemp.txt");
        //print array after sort and check if sorted
        System.out.println(Arrays.toString(array));
        System.out.println("After sorting, isSorted(array): " + isSorted(array) + "\n");

        scanner.close();
    }

}
