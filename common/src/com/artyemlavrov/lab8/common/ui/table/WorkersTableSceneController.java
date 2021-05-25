package com.artyemlavrov.lab8.common.ui.table;

import com.artyemlavrov.lab8.common.application.BasicApplication;
import com.artyemlavrov.lab8.common.request.*;
import com.artyemlavrov.lab8.common.response.GetAllResponse;
import com.artyemlavrov.lab8.common.response.GetInfoResponse;
import com.artyemlavrov.lab8.common.response.GetSumOfSalaryResponse;
import com.artyemlavrov.lab8.common.types.Coordinates;
import com.artyemlavrov.lab8.common.types.Position;
import com.artyemlavrov.lab8.common.types.Worker;
import com.artyemlavrov.lab8.common.ui.SceneController;
import com.artyemlavrov.lab8.common.ui.editor.EditorSceneController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class WorkersTableSceneController extends SceneController {

    @FXML
    private WorkersTableView workersTableView;
    @FXML
    private MenuItem graphMenuItem;
    @FXML
    private MenuItem russianMenuItem;
    @FXML
    private MenuItem latvianMenuItem;
    @FXML
    private MenuItem canadianMenuItem;
    @FXML
    private MenuItem slovakianMenuItem;
    @FXML
    private MenuItem addMenuItem;
    @FXML
    private MenuItem clearMenuItem;
    @FXML
    private MenuItem getInfoMenuItem;
    @FXML
    private MenuItem sumOfSalaryMenuItem;
    @FXML
    private MenuItem removeLowerMenuItem;
    @FXML
    private MenuItem removeHeadMenuItem;
    @FXML
    private ComboBox<String> keyComboBox;
    @FXML
    private TextField valueTextField;
    @FXML
    private Button filterButton;

    public WorkersTableSceneController(BasicApplication application) {
        super(application);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadCollectionAndInitTable(resourceBundle);
        graphMenuItem.setOnAction(handler -> application.setGraphScene());
        russianMenuItem.setOnAction(handler -> setLocale(new Locale("ru")));
        canadianMenuItem.setOnAction(handler -> setLocale(new Locale("ca")));
        slovakianMenuItem.setOnAction(handler -> setLocale(new Locale("sk")));
        latvianMenuItem.setOnAction(handler -> setLocale(new Locale("lv")));
        addMenuItem.setOnAction(handler -> application.showEditorDialog(new Worker(), EditorSceneController.EditorMode.CREATE));
        clearMenuItem.setOnAction(handler -> performDeletionRequest(new ClearRequest(application.getAuthentication())));
        removeHeadMenuItem.setOnAction(handler -> performDeletionRequest(new RemoveHeadRequest(application.getAuthentication())));
        removeLowerMenuItem.setOnAction(handler -> application.showEditorDialog(new Worker(), EditorSceneController.EditorMode.REMOVE_LOWER));
        getInfoMenuItem.setOnAction(handler -> performRequestAsync(
                new GetInfoRequest(application.getAuthentication()),
                response -> {
                    GetInfoResponse getInfoResponse = (GetInfoResponse) response;
                    String content = String.format(
                            getString("get_info_alert"),
                            getInfoResponse.getCollectionType(),
                            getInfoResponse.getInitializationDate().toString(),
                            getInfoResponse.getElementsCount().toString()
                    );
                    showSuccessAlertOnFXThread(content);
                }
        ));
        sumOfSalaryMenuItem.setOnAction(handler -> performRequestAsync(
                new GetSumOfSalaryRequest(application.getAuthentication()),
                response -> {
                    GetSumOfSalaryResponse getSumOfSalaryResponse = (GetSumOfSalaryResponse) response;
                    showSuccessAlertOnFXThread(getSumOfSalaryResponse.getSumOfSalary().toString());
                }
        ));
    }

    protected void setLocale(Locale locale) {
        application.setLocale(locale);
        application.setWorkersTableScene();
    }

    private Map<String, String> nameToKey;

    protected void loadCollectionAndInitTable(ResourceBundle resourceBundle) {
        performRequestAsync(
                new GetAllRequest(application.getAuthentication()),
                response -> {
                    GetAllResponse getAllResponse = (GetAllResponse) response;
                    application.updateCollection(getAllResponse.getElementsList());
                    workersTableView.initialize(resourceBundle, application.getTableData(), application);
                    Set<String> keySet = workersTableView.getKeySet();
                    keyComboBox.setItems(FXCollections.observableArrayList(
                            workersTableView
                                    .getKeySet()
                                    .stream()
                                    .map(resourceBundle::getString)
                                    .collect(Collectors.toList())
                    ));
                    nameToKey = new HashMap<>();
                    for (String key : keySet) {
                        nameToKey.put(resourceBundle.getString(key), key);
                    }
                    filterButton.setOnMouseClicked(handler -> {
                        String name = keyComboBox.getSelectionModel().getSelectedItem();
                        String key = nameToKey.get(name);
                        String value = valueTextField.getText();
                        workersTableView.setFilter(key, value);
                        performGetAllRequestAsync();
                    });
                }
        );
    }


    @Override
    public String getTitle() {
        return application.getAuthentication().getUsername();
    }

    @Override
    protected URL getFXMLLocation() {
        return getClass().getResource("workers_table_scene.fxml");
    }
    }
