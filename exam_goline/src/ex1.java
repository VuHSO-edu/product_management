import java.util.Arrays;
import java.util.Random;

public class ex1 {

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public static int findMaxValue(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
        return arr[arr.length - 1];
    }

    public static void main(String[] args) {
        Random rand = new Random();
        int[] arr = Arrays.stream(new int[100]).map(i -> (new Random()).nextInt(100)).toArray();
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Max value: " + findMaxValue(arr));

    }
}
