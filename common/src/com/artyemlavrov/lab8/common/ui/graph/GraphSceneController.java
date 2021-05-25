package com.artyemlavrov.lab8.common.ui.graph;

import com.artyemlavrov.lab8.common.application.BasicApplication;
import com.artyemlavrov.lab8.common.request.GetColorRequest;
import com.artyemlavrov.lab8.common.response.GetColorResponse;
import com.artyemlavrov.lab8.common.types.Worker;
import com.artyemlavrov.lab8.common.ui.SceneController;
import com.artyemlavrov.lab8.common.ui.editor.EditorSceneController;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.net.URL;
import java.util.*;

@SuppressWarnings("ALL")
public class GraphSceneController extends SceneController {
    @FXML
    private Pane pane;
    @FXML
    private MenuItem tableMenuItem;

    private final Map<Integer, Shape> shapes = new HashMap<>();

    public GraphSceneController(BasicApplication application) {
        super(application);
    }

    @Override
    public String getTitle() {
        return getString("editor_title");
    }

    @Override
    protected URL getFXMLLocation() {
        return getClass().getResource("graph_scene.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Rectangle clipRect = new Rectangle(Double.MAX_VALUE, Double.MAX_VALUE);
        pane.setClip(clipRect);

        initCollectionUpdates();
        tableMenuItem.setOnAction(handler -> application.setWorkersTableScene());
    }

    private void initCollectionUpdates() {
        application.getTableData().forEach(this::addOrUpdateWorker);
        application.getTableData().addListener(
                (ListChangeListener<Worker>) change -> Platform.runLater(() -> {
                    Map<Integer, Worker> toRemove = new HashMap<>();
                    Map<Integer, Worker> toAdd = new HashMap<>();
                    while (change.next()) {
                        change.getAddedSubList().forEach(worker -> toAdd.put(worker.getId(), worker));
                        change.getRemoved().forEach(worker -> toRemove.put(worker.getId(), worker));
                    }
                    for (Integer id : toRemove.keySet()) {
                        if (!toAdd.containsKey(id)) {
                            removeWorker(toRemove.get(id));
                        }
                    }
                    for (Integer id : toAdd.keySet()) {
                        addOrUpdateWorker(toAdd.get(id));
                    }
                })

        );
    }

    private void addOrUpdateWorker(Worker worker) {
        if (shapes.containsKey(worker.getId())) {
            moveWorker(worker);
        } else {
            addWorker(worker);
        }
        Shape shape = shapes.get(worker.getId());
    }

    private void addWorker(Worker worker) {
        performRequestAsync(
                new GetColorRequest(application.getAuthentication(), worker.getId()),
                response -> {
                    Integer color = ((GetColorResponse) response).getColor();
                    Color rgb = Color.rgb(
                            color % 256,
                            (color / 256) % 256,
                            (color / 256 / 256) % 256
                    );
                    Circle circle = new Circle(15);
                    circle.setFill(rgb);
                    circle.setTranslateX(worker.getCoordinates().getX());
                    circle.setTranslateY(worker.getCoordinates().getY());
                    updateMouseListener(circle, worker);
                    pane.getChildren().add(circle);
                    shapes.put(worker.getId(), circle);

                    ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(500), circle);
                    scaleTransition.setFromX(0);
                    scaleTransition.setFromY(0);
                    scaleTransition.setToX(1);
                    scaleTransition.setToY(1);
                    scaleTransition.play();
                }
        );
    }

    private void updateMouseListener(Shape shape, Worker worker) {
        shape.setOnMouseClicked(handler -> application.showEditorDialog(worker, EditorSceneController.EditorMode.EDIT));
    }


    private void removeWorker(Worker worker) {
        Shape shape = shapes.get(worker.getId());
        System.out.println(worker.getId());
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(500), shape);
        scaleTransition.setToX(0);
        scaleTransition.setToY(0);
        scaleTransition.setOnFinished(handler -> {
            pane.getChildren().remove(shape);
            shapes.remove(worker.getId());
        });
        scaleTransition.play();
    }

    private void moveWorker(Worker worker) {
        Shape shape = shapes.get(worker.getId());
        TranslateTransition transition = new TranslateTransition(Duration.millis(500), shape);
        transition.setToX(worker.getCoordinates().getX());
        transition.setToY(worker.getCoordinates().getY());
        transition.play();
        updateMouseListener(shape, worker);
    }
}
