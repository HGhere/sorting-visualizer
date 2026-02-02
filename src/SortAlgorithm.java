public interface SortAlgorithm {
    void sort(int[] arr, Runnable onUpdate, StopFlag stopFlag) throws InterruptedException;
}
