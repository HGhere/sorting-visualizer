public class InsertionSort implements SortAlgorithm {

    @Override
    public void sort(int[] arr, Runnable onUpdate, StopFlag stopFlag)
            throws InterruptedException {

        for (int i = 1; i < arr.length && !stopFlag.stop; i++) {

            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key && !stopFlag.stop) {
                arr[j + 1] = arr[j];
                j--;

                onUpdate.run();
                Thread.sleep(40);
            }

            if (stopFlag.stop) return;
            arr[j + 1] = key;
        }
    }
}
