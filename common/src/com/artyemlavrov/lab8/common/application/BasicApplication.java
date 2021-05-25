package com.artyemlavrov.lab8.common.application;

import com.artyemlavrov.lab8.common.command.CommandFactory;
import com.artyemlavrov.lab8.common.exception.RequestFailureException;
import com.artyemlavrov.lab8.common.interpreter.Interpreter;
import com.artyemlavrov.lab8.common.request.Request;
import com.artyemlavrov.lab8.common.response.Response;
import com.artyemlavrov.lab8.common.response.ResponseError;
import com.artyemlavrov.lab8.common.types.Authentication;
import com.artyemlavrov.lab8.common.types.Worker;
import com.artyemlavrov.lab8.common.ui.SceneController;
import com.artyemlavrov.lab8.common.ui.editor.EditorSceneController;
import com.artyemlavrov.lab8.common.ui.graph.GraphSceneController;
import com.artyemlavrov.lab8.common.ui.login.LoginSceneController;
import com.artyemlavrov.lab8.common.ui.table.WorkersTableSceneController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public abstract class BasicApplication extends Application {

    private final ObservableList<Worker> tableData = FXCollections.observableArrayList();
    private final Interpreter interpreter = new Interpreter(this, getCommandFactory());
    private final CollectionUpdater collectionUpdater = new CollectionUpdater(this);
    private Stage stage;
    private Locale locale = new Locale("ru");

    private Authentication authentication = Authentication.empty;

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    public Authentication getAuthentication() {
        return this.authentication;
    }

    public abstract <REQUEST extends Request> Response getResponse(REQUEST request) throws RequestFailureException;

    public <REQUEST extends Request> void performRequest(REQUEST request, Consumer<Response> onSuccess, Consumer<RequestFailureException> onError) {
        try {
            Response response = getResponse(request);
            if (response instanceof ResponseError) {
                String message = ((ResponseError) response).getMessage();
                throw new RequestFailureException(message);
            }
            onSuccess.accept(response);
        } catch (RequestFailureException e) {
            onError.accept(e);
        }
    }

    protected abstract CommandFactory getCommandFactory();

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        interpreter.start();
        collectionUpdater.start();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        interpreter.stop();
        collectionUpdater.stop();
    }

    public void setLoginScene() {
        setScene(new LoginSceneController(this));
    }

    public void setWorkersTableScene() {
        setScene(new WorkersTableSceneController(this));
    }

    public void setGraphScene() {
        setScene(new GraphSceneController(this));
    }

    public void showEditorDialog(Worker element, EditorSceneController.EditorMode mode) {
        Stage stage = new Stage();
        EditorSceneController controller = new EditorSceneController(this, stage, mode, element);
        Scene scene = controller.loadScene();
        stage.setScene(scene);
        stage.show();
    }

    public void updateCollection(List<Worker> elements) {
        tableData.setAll(elements);
    }

    public ObservableList<Worker> getTableData() {
        return tableData;
    }

    public Interpreter getInterpreter() {
        return interpreter;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

    private void setScene(SceneController sceneController) {
        Scene scene = sceneController.loadScene();
        stage.setScene(scene);
        String title = sceneController.getTitle();
        stage.setTitle(title);
        stage.show();
    }
}
