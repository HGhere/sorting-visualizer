public class MergeSort implements SortAlgorithm {

    @Override
    public void sort(int[] arr, Runnable onUpdate, StopFlag stopFlag)
            throws InterruptedException {

        mergeSort(arr, 0, arr.length - 1, onUpdate, stopFlag);
    }

    private void mergeSort(int[] arr, int l, int r,
                           Runnable onUpdate, StopFlag stopFlag)
            throws InterruptedException {

        if (l < r && !stopFlag.stop) {
            int m = (l + r) / 2;

            mergeSort(arr, l, m, onUpdate, stopFlag);
            mergeSort(arr, m + 1, r, onUpdate, stopFlag);
            merge(arr, l, m, r, onUpdate, stopFlag);
        }
    }

    private void merge(int[] arr, int l, int m, int r,
                       Runnable onUpdate, StopFlag stopFlag)
            throws InterruptedException {

        if (stopFlag.stop) return;

        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(arr, l, L, 0, n1);
        System.arraycopy(arr, m + 1, R, 0, n2);

        int i = 0, j = 0, k = l;

        while (i < n1 && j < n2 && !stopFlag.stop) {
            if (L[i] <= R[j]) arr[k++] = L[i++];
            else arr[k++] = R[j++];

            onUpdate.run();
            Thread.sleep(40);
        }

        while (i < n1 && !stopFlag.stop) arr[k++] = L[i++];
        while (j < n2 && !stopFlag.stop) arr[k++] = R[j++];
    }
}
