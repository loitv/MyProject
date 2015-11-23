
import java.util.Random;

public class SortDemo {

	public static void selectionSort(int[] array) {
		for(int i = 0; i < array.length; i++) {
			int max = array[i]; // store maximum element
			int index = i; // store position of max element
			for (int j = i + 1; j < array.length; j++) {
				if (max < array[j]) {
					max = array[j];
					index = j;
				}
			}
			// Neu chi so da thay doi, ta se hoan vi
			if (index != i) {
				int temp = array[i];
				array[i] = array[index];
				array[index] = temp;
			}
		}
	}
	
	public static void main(String[] args) {
		int[] number  = new int[10];
		for (int i = 0; i < 10; i++) {
			number[i] = new Random().nextInt(6) + 5;
		}
		selectionSort(number);
		for (int i = 0; i < 10; i++) {
			System.out.print(number[i] + " ");
		}
	}
}
