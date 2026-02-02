public class QuickSort implements SortAlgorithm {

    @Override
    public void sort(int[] arr, Runnable onUpdate, StopFlag stopFlag)
            throws InterruptedException {

        quickSort(arr, 0, arr.length - 1, onUpdate, stopFlag);
    }

    private void quickSort(int[] arr, int low, int high,
                           Runnable onUpdate, StopFlag stopFlag)
            throws InterruptedException {

        if (low < high && !stopFlag.stop) {
            int pi = partition(arr, low, high, onUpdate, stopFlag);
            quickSort(arr, low, pi - 1, onUpdate, stopFlag);
            quickSort(arr, pi + 1, high, onUpdate, stopFlag);
        }
    }

    private int partition(int[] arr, int low, int high,
                          Runnable onUpdate, StopFlag stopFlag)
            throws InterruptedException {

        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high && !stopFlag.stop; j++) {
            if (arr[j] < pivot) {
                i++;

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

                onUpdate.run();
                Thread.sleep(40);
            }
        }

        if (stopFlag.stop) return low;

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
}
