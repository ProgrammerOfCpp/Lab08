package com.artyemlavrov.lab8.common.ui.editor;

import com.artyemlavrov.lab8.common.application.BasicApplication;
import com.artyemlavrov.lab8.common.request.*;
import com.artyemlavrov.lab8.common.types.*;
import com.artyemlavrov.lab8.common.ui.SceneController;
import com.artyemlavrov.lab8.common.util.IOManager;
import com.artyemlavrov.lab8.common.valuereader.complex.WorkerReader;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

@SuppressWarnings("unused")
public class EditorSceneController extends SceneController {
    public enum EditorMode { CREATE, EDIT, REMOVE_LOWER }

    private final EditorMode mode;
    private final Stage stage;

    @SuppressWarnings("unused")
    @FXML
    private TextField nameTextField;
    @SuppressWarnings("unused")
    @FXML
    private TextField coordinatesXTextField;
    @SuppressWarnings("unused")
    @FXML
    private TextField coordinatesYTextField;
    @SuppressWarnings("unused")
    @FXML
    private TextField salaryTextField;
    @SuppressWarnings("unused")
    @FXML
    private CheckBox statusCheckbox;
    @SuppressWarnings("unused")
    @FXML
    private ComboBox<Status> statusComboBox;
    @SuppressWarnings("unused")
    @FXML
    private ComboBox<Position> positionComboBox;
    @SuppressWarnings("unused")
    @FXML
    private CheckBox personCheckbox;
    @SuppressWarnings("unused")
    @FXML
    private Label locationLabel;
    @SuppressWarnings("unused")
    @FXML
    private Label locationXLabel;
    @SuppressWarnings("unused")
    @FXML
    private TextField locationXTextField;
    @SuppressWarnings("unused")
    @FXML
    private Label locationYLabel;
    @SuppressWarnings("unused")
    @FXML
    private TextField locationYTextField;
    @SuppressWarnings("unused")
    @FXML
    private Label locationZLabel;
    @SuppressWarnings("unused")
    @FXML
    private TextField locationZTextField;
    @SuppressWarnings("unused")
    @FXML
    private Label locationNameLabel;
    @SuppressWarnings("unused")
    @FXML
    private CheckBox locationNameCheckbox;
    @SuppressWarnings("unused")
    @FXML
    private TextField locationNameTextField;
    @SuppressWarnings("unused")
    @FXML
    private Label nationalityLabel;
    @SuppressWarnings("unused")
    @FXML
    private ComboBox<Country> nationalityComboBox;
    @SuppressWarnings("unused")
    @FXML
    private Label weightLabel;
    @SuppressWarnings("unused")
    @FXML
    private CheckBox weightCheckbox;
    @SuppressWarnings("unused")
    @FXML
    private TextField weightTextField;
    @SuppressWarnings("unused")
    @FXML
    private Button saveButton;
    @SuppressWarnings("unused")
    @FXML
    private Button cancelButton;
    @SuppressWarnings("unused")
    @FXML
    private Button deleteButton;

    private Worker worker;

    public EditorSceneController(BasicApplication application, Stage stage, EditorMode mode, Worker worker) {
        super(application);
        this.stage = stage;
        this.mode = mode;
        this.worker = worker;
    }

    @Override
    public String getTitle() {
        return getString("editor_title");
    }

    @Override
    protected URL getFXMLLocation() {
        return getClass().getResource("editor_scene.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateForm();
        saveButton.setOnMouseClicked(e -> {
            updateWorkerAndForm();
            Request request = null;
            switch (mode) {
                case CREATE:
                    request = new AddRequest(
                            application.getAuthentication(),
                            worker
                    );
                    break;
                case EDIT:
                    request = new UpdateRequest(
                            application.getAuthentication(),
                            worker.getId(),
                            worker
                    );
                    break;
                case REMOVE_LOWER:
                    request = new RemoveLowerRequest(
                            application.getAuthentication(),
                            worker
                    );
                    break;
            }
            performRequestAsync(request, response -> {
                stage.close();
                performGetAllRequestAsync();
            });
        });
        deleteButton.setOnMouseClicked(e -> performDeletionRequest(new RemoveByIdRequest(application.getAuthentication(), worker.getId())));
        cancelButton.setOnMouseClicked(e -> stage.close());
    }

    private void setDefaultFormState() {
        if (mode == EditorMode.REMOVE_LOWER) {
            deleteButton.setVisible(false);
        }
        positionComboBox.setItems(
                FXCollections.observableArrayList(
                        Position.values()
                )
        );

        statusCheckbox.setSelected(false);
        statusComboBox.setVisible(false);
        statusComboBox.setItems(
                FXCollections.observableArrayList(
                        Status.values()
                )
        );
        statusComboBox.getSelectionModel().select(Status.REGULAR);
        statusCheckbox.setOnMouseReleased(e -> updateWorkerAndForm());

        personCheckbox.setOnMouseReleased(e -> updateWorkerAndForm());

        weightLabel.setVisible(false);
        weightCheckbox.setVisible(false);
        weightCheckbox.setSelected(false);
        weightCheckbox.setOnMouseReleased(e -> updateWorkerAndForm());
        weightTextField.setVisible(false);
        weightTextField.setText("1");

        nationalityLabel.setVisible(false);
        nationalityComboBox.setVisible(false);
        nationalityComboBox.setValue(Country.VATICAN);
        nationalityComboBox.setItems(
                FXCollections.observableArrayList(
                        Country.values()
                )
        );

        locationLabel.setVisible(false);
        locationXLabel.setVisible(false);
        locationXTextField.setVisible(false);
        locationXTextField.setText("0");
        locationYLabel.setVisible(false);
        locationYTextField.setVisible(false);
        locationYTextField.setText("0");
        locationZLabel.setVisible(false);
        locationZTextField.setVisible(false);
        locationZTextField.setText("0");
        locationNameLabel.setVisible(false);
        locationNameCheckbox.setVisible(false);
        locationNameCheckbox.setSelected(false);
        locationNameCheckbox.setOnMouseReleased(e -> updateWorkerAndForm());
        locationNameTextField.setText("-");
        locationNameTextField.setVisible(false);
    }

    private void updateForm() {
        setDefaultFormState();

        nameTextField.setText(worker.getName());
        salaryTextField.setText(Double.toString(worker.getSalary()));

        Coordinates coordinates = worker.getCoordinates();
        coordinatesXTextField.setText(Float.toString(coordinates.getX()));
        coordinatesYTextField.setText(Double.toString(coordinates.getY()));

        positionComboBox.getSelectionModel().select(worker.getPosition());

        Status status = worker.getStatus();
        if (status != null) {
            statusCheckbox.setSelected(true);
            statusComboBox.setVisible(true);
            statusComboBox.getSelectionModel().select(status);
        }

        Person person = worker.getPerson();
        if (person != null) {
            personCheckbox.setSelected(true);

            weightLabel.setVisible(true);
            weightCheckbox.setVisible(true);
            Integer weight = person.getWeight();
            if (weight != null) {
                weightCheckbox.setSelected(true);
                weightTextField.setVisible(true);
                weightTextField.setText(weight.toString());
            }

            nationalityLabel.setVisible(true);
            nationalityComboBox.setVisible(true);
            nationalityComboBox.setValue(person.getNationality());

            locationLabel.setVisible(true);
            locationNameLabel.setVisible(true);
            locationZLabel.setVisible(true);
            locationYLabel.setVisible(true);
            locationXLabel.setVisible(true);


            Location location = person.getLocation();
            locationXTextField.setVisible(true);
            locationXTextField.setText(location.getX().toString());
            locationYTextField.setVisible(true);
            locationYTextField.setText(Float.toString(location.getY()));
            locationZTextField.setVisible(true);
            locationZTextField.setText(location.getZ().toString());
            String name = location.getName();
            locationNameCheckbox.setVisible(true);
            if (name != null) {
                locationNameCheckbox.setSelected(true);
                locationNameTextField.setVisible(true);
                locationNameTextField.setText(name);
            }
        }
    }

    private void updateWorkerAndForm() {
        IOManager ioManager = new WorkerIOManager(
                nameTextField.getText(),
                coordinatesXTextField.getText(),
                coordinatesYTextField.getText(),
                salaryTextField.getText(),
                positionComboBox.getValue(),
                statusCheckbox.isSelected(),
                statusComboBox.getValue(),
                personCheckbox.isSelected(),
                weightCheckbox.isSelected(),
                weightTextField.getText(),
                nationalityComboBox.getValue(),
                locationXTextField.getText(),
                locationYTextField.getText(),
                locationZTextField.getText(),
                locationNameCheckbox.isSelected(),
                locationNameTextField.getText()
        );
        try {
            Worker workerWithData = new WorkerReader(ioManager).read();
            worker = new Worker(worker, workerWithData);
        } catch (InvalidWorkerException e) {
            showErrorAlertOnFXThread(getString("editor_wrong_input"));
        }
        updateForm();
    }
}
