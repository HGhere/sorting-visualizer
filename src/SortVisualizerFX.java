import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

public class SortVisualizerFX extends Application {

    private static final int SIZE = 60;
    private int[] array = new int[SIZE];
    private Canvas canvas = new Canvas(800, 400);
    private StopFlag stopFlag = new StopFlag();
    private Thread sortingThread;


    @Override
    public void start(Stage stage) {
        generateArray();

        Button bubble = new Button("Bubble");
        Button insertion = new Button("Insertion");
        Button selection = new Button("Selection");
        Button merge = new Button("Merge");
        Button quick = new Button("Quick");
        Button shuffle = new Button("Shuffle");
        Button stop = new Button("Stop");


        bubble.setOnAction(e -> runSort(new BubbleSort()));
        insertion.setOnAction(e -> runSort(new InsertionSort()));
        selection.setOnAction(e -> runSort(new SelectionSort()));
        merge.setOnAction(e -> runSort(new MergeSort()));
        quick.setOnAction(e -> runSort(new QuickSort()));
        shuffle.setOnAction(e -> {
            stopFlag.stop = true;   
            generateArray();
            drawArray();
        });
        stop.setOnAction(e -> {
            stopFlag.stop = true;
        });



        HBox controls = new HBox(10,bubble, insertion, selection, merge, quick, shuffle, stop);


        BorderPane root = new BorderPane();
        root.setTop(controls);
        root.setCenter(canvas);

        drawArray();

        stage.setTitle("Sorting Algorithm Visualizer");
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void generateArray() {
        Random rand = new Random();
        for (int i = 0; i < SIZE; i++) {
            array[i] = rand.nextInt(300) + 20;
        }
    }

    private void drawArray() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        double barWidth = canvas.getWidth() / SIZE;

        for (int i = 0; i < SIZE; i++) {
            gc.setFill(Color.DODGERBLUE);
            gc.fillRect(i * barWidth, canvas.getHeight() - array[i],
                    barWidth - 2, array[i]);
        }
    }

    private void runSort(SortAlgorithm algorithm) {
    stopFlag.stop = true;
    stopFlag = new StopFlag();

    sortingThread = new Thread(() -> {
        try {
            algorithm.sort(array,() -> Platform.runLater(this::drawArray),stopFlag);
        } 
        catch (InterruptedException ignored) {}
    });
    sortingThread.start();
}

    public static void main(String[] args) {
        launch();
    }
}
