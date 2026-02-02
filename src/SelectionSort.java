public class SelectionSort implements SortAlgorithm {

    @Override
    public void sort(int[] arr, Runnable onUpdate, StopFlag stopFlag)
            throws InterruptedException {

        int n = arr.length;

        for (int i = 0; i < n - 1 && !stopFlag.stop; i++) {

            int minIdx = i;
            for (int j = i + 1; j < n && !stopFlag.stop; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }

            if (stopFlag.stop) return;

            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;

            onUpdate.run();
            Thread.sleep(40);
        }
    }
}
