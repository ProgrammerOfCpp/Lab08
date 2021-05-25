package com.artyemlavrov.lab8.common.ui.login;

import com.artyemlavrov.lab8.common.application.BasicApplication;
import com.artyemlavrov.lab8.common.request.LoginRequest;
import com.artyemlavrov.lab8.common.request.RegisterRequest;
import com.artyemlavrov.lab8.common.request.Request;
import com.artyemlavrov.lab8.common.response.LoginResponse;
import com.artyemlavrov.lab8.common.types.Authentication;
import com.artyemlavrov.lab8.common.ui.SceneController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginSceneController extends SceneController {
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button logInButton;
    @FXML
    private Button signUpButton;

    public LoginSceneController(BasicApplication application) {
        super(application);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logInButton.setOnMouseClicked(e -> {
            Request request = new LoginRequest(buildAuthenticationFromInput());
            this.performRequestAsync(request, response -> {
                LoginResponse loginResponse = (LoginResponse) response;
                application.setAuthentication(loginResponse.getAuthentication());
                application.setWorkersTableScene();
                application.updateCollection(loginResponse.getElementsList());
            });
        });
        signUpButton.setOnMouseClicked(e -> {
            RegisterRequest request = new RegisterRequest(buildAuthenticationFromInput());
            this.performRequestAsync(request, response -> showSuccessAlertOnFXThread(getString("alert_user_registered")));
        });
    }

    private Authentication buildAuthenticationFromInput() {
        return new Authentication(
                usernameTextField.getText(),
                passwordTextField.getText()
        );
    }

    @Override
    public String getTitle() {
        return getString("login_scene_title");
    }

    @Override
    protected URL getFXMLLocation() {
        return getClass().getResource("login_scene.fxml");
    }

    private static LoginSceneController instance = null;

    public static LoginSceneController getInstance(BasicApplication application) {
        if (instance == null) {
            instance = new LoginSceneController(application);
        }
        return instance;
    }
}
