package com.artyemlavrov.lab8.common.ui.table;

import com.artyemlavrov.lab8.common.application.BasicApplication;
import com.artyemlavrov.lab8.common.types.Worker;
import com.artyemlavrov.lab8.common.ui.editor.EditorSceneController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.function.Function;

public class WorkersTableView extends TableView<Worker> {

    public WorkersTableView() {
        this.setVisible(false);
    }
    private final Map<String, Function<Worker, String>> fieldMappers = new HashMap<>();
    private FilteredList<Worker> data;

    @SafeVarargs
    private final TableColumn<Worker, String> createMultiColumn(
            ResourceBundle resourceBundle,
            String key,
            TableColumn<Worker, String>... columns
    ) {
        String name = resourceBundle.getString(key);
        TableColumn<Worker, String> column = new TableColumn<>(name);
        column.getColumns().addAll(columns);
        return column;
    }

    private Map<String, String> mapWorker(Worker worker) {
        Map<String, String> map = new HashMap<>();
        for (String key : fieldMappers.keySet()) {
            Function<Worker, String> mapper = fieldMappers.get(key);
            map.put(key, mapper.apply(worker));
        }
        return map;
    }

    private void createFieldMapper(String key, Function<Worker, Object> callback) {
        Function<Worker, String> mapper = worker -> {
            String value = "-";
            try {
                value = callback.apply(worker).toString();
            } catch (Exception ignored) {}
            return value;
        };
        fieldMappers.put(key, mapper);
    }

    private TableColumn<Worker, String> createColumn(
            ResourceBundle resourceBundle,
            String key,
            Function<Worker, Object> callback
    ) {
        String name = resourceBundle.getString(key);
        TableColumn<Worker, String> column = new TableColumn<>(name);
        createFieldMapper(key, callback);
        column.setCellValueFactory(workerTCellDataFeatures -> {
            Worker worker = workerTCellDataFeatures.getValue();
            return new SimpleStringProperty(fieldMappers.get(key).apply(worker));
        });
        column.setMinWidth(100);
        return column;
    }

    @SuppressWarnings("unchecked")
    public void initialize(ResourceBundle resourceBundle, ObservableList<Worker> data, BasicApplication application) {
        this.getColumns().setAll(
                createColumn(resourceBundle, "table_id", Worker::getId),
                createColumn(resourceBundle, "table_name", Worker::getName),
                createColumn(resourceBundle, "table_creation_date", Worker::getCreationDate),
                createColumn(resourceBundle, "table_salary", Worker::getSalary),
                createColumn(resourceBundle, "table_status", Worker::getStatus),
                createMultiColumn(resourceBundle, "table_coordinates",
                        createColumn(resourceBundle, "table_coordinates_x", worker -> worker.getCoordinates().getX()),
                        createColumn(resourceBundle, "table_coordinates_y", worker -> worker.getCoordinates().getY())
                ),
                createMultiColumn(resourceBundle, "table_person",
                        createMultiColumn(resourceBundle, "table_location",
                                createColumn(resourceBundle, "table_location_name", worker -> worker.getPerson().getLocation().getName()),
                                createColumn(resourceBundle, "table_location_x", worker -> worker.getPerson().getLocation().getX()),
                                createColumn(resourceBundle, "table_location_y", worker -> worker.getPerson().getLocation().getY()),
                                createColumn(resourceBundle, "table_location_z", worker -> worker.getPerson().getLocation().getZ())
                        ),
                        createColumn(resourceBundle, "table_nationality", worker -> worker.getPerson().getNationality()),
                        createColumn(resourceBundle, "table_weight", worker -> worker.getPerson().getWeight())
                )
        );
        this.data = new FilteredList<>(data);
        setItems(this.data);
        setPlaceholder(new Label(""));

        this.setOnMouseClicked(e -> {
            if (e.getClickCount() > 1) {
                Worker selected = this.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    application.showEditorDialog(selected, EditorSceneController.EditorMode.EDIT);
                }
            }
        });
        this.setVisible(true);
    }

    public void setFilter(String key, String value) {
        if (key == null) return;
        this.data.setPredicate(worker -> {
            Map<String, String> map = mapWorker(worker);
            if (value.isEmpty()) return true;
            return map.get(key).equals(value.trim());
        });
    }

    public Set<String> getKeySet() {
        return fieldMappers.keySet();
    }
}
