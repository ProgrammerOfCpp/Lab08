package com.artyemlavrov.lab8.common.ui;

import com.artyemlavrov.lab8.common.application.BasicApplication;
import com.artyemlavrov.lab8.common.request.GetAllRequest;
import com.artyemlavrov.lab8.common.request.RemoveByIdRequest;
import com.artyemlavrov.lab8.common.request.Request;
import com.artyemlavrov.lab8.common.response.GetAllResponse;
import com.artyemlavrov.lab8.common.response.Response;
import com.artyemlavrov.lab8.common.types.Worker;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public abstract class SceneController implements Initializable {
    protected final BasicApplication application;

    public SceneController(BasicApplication application) {
        this.application = application;
    }

    public abstract String getTitle();

    protected abstract URL getFXMLLocation();

    protected ResourceBundle getResourceBundle() {
        return ResourceBundle.getBundle(
                "com.artyemlavrov.lab8.common.ui.strings",
                application.getLocale()
        );
    }

    protected Optional<String> showAlertAndRead(String name) {
        TextInputDialog dialog = new TextInputDialog(name);
        dialog.setTitle(getString("input_alert_title"));
        dialog.setHeaderText(getString("input_alert_header"));
        return dialog.showAndWait();
    }

    protected void performDeletionRequest(Request request) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(getString("delete_alert_title"));
        alert.setHeaderText(getString("delete_alert_header"));
        alert.setContentText(getString("delete_alert_text"));
        alert.showAndWait().ifPresent(type -> {
            if (type == ButtonType.OK) {
                performRequestAsync(
                        request,
                        response -> {
                            showSuccessAlertOnFXThread(getString("alert_success"));
                            performGetAllRequestAsync();
                        }
                );
            }
        });
    }

    protected String getString(String key) {
        return getResourceBundle().getString(key);
    }

    protected void performRequestAsync(Request request, Consumer<Response> onSuccess) {
        this.application.performRequest(
                request,
                response -> Platform.runLater(() -> onSuccess.accept(response)),
                e -> showErrorAlertOnFXThread(getString("alert_error"))
        );
    }

    protected void performGetAllRequestAsync() {
        performRequestAsync(
                new GetAllRequest(application.getAuthentication()),
                response -> application.updateCollection(((GetAllResponse) response).getElementsList())
        );
    }

    public void showErrorAlertOnFXThread(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(getString("alert_error"));
            alert.setHeaderText("");
            alert.setContentText(message);
            alert.show();
        });
    }

    protected void showSuccessAlertOnFXThread(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(getString("alert_success"));
            alert.setContentText(message);
            alert.show();
        });
    }

    public Scene loadScene()  {
        FXMLLoader loader = new FXMLLoader(getFXMLLocation());
        loader.setController(this);
        loader.setResources(getResourceBundle());
        try {
            Parent parent = loader.load();
            return new Scene(parent);
        } catch (IOException e) {
            e.printStackTrace();
            return new Scene(new Group());
        }
    }
}
