public class BubbleSort implements SortAlgorithm {

    @Override
    public void sort(int[] arr, Runnable onUpdate, StopFlag stopFlag)
            throws InterruptedException {

        int n = arr.length;

        for (int i = 0; i < n - 1 && !stopFlag.stop; i++) {
            for (int j = 0; j < n - i - 1 && !stopFlag.stop; j++) {

                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    onUpdate.run();
                    Thread.sleep(40);
                }
            }
        }
    }
}
