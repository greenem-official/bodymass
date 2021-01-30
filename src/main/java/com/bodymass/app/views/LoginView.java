package com.bodymass.app.views;

import com.bodymass.app.AppUI;
import com.bodymass.app.UserState;
import com.bodymass.app.db.model.User;
import com.bodymass.app.services.UserService;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import java.sql.SQLException;

public class LoginView extends VerticalLayout {
    private UserService userService = new UserService();

    public LoginView() {
        FormLayout form = new FormLayout();
        form.setMargin(true);

        Label errorLabel = new Label("");
        errorLabel.setVisible(false);
        form.addComponent(errorLabel);

        TextField emailField = new TextField("Email");
        emailField.setRequiredIndicatorVisible(true);
        form.addComponent(emailField);

        TextField passwordField = new TextField("Пароль");
        passwordField.setRequiredIndicatorVisible(true);
        form.addComponent(passwordField);

        Button saveButton = new Button("Войти");
        saveButton.addClickListener(e -> {
            String isErr = "";
            try {
                isErr = userService.login(emailField.getValue().trim(), passwordField.getValue());
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            errorLabel.setVisible(true);
            if (isErr.equalsIgnoreCase("error")) {
                errorLabel.setValue("Ошибка входа");
            } else if (isErr.equalsIgnoreCase("successful")) {
                try {
                    User user = userService.getUser(emailField.getValue(), passwordField.getValue());
                    UserState.get().setUser(user);
                    UserState.get().setGraphsEnabled(false);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                errorLabel.setValue("Вы вошли");
                AppUI.get().setContent(new AddWeightView());
            } else if (isErr.equalsIgnoreCase("no such user")) {
                errorLabel.setValue("Данного пользователя не существует");
            } else if (isErr.equalsIgnoreCase("wrong password")) {
                errorLabel.setValue("Неверный пароль");
            } else if (isErr.equalsIgnoreCase("empty email")) {
                errorLabel.setValue("Email не должен быть пустым");
            } else if (isErr.equalsIgnoreCase("empty password")) {
                errorLabel.setValue("Пароль не должен быть пустым");
            } else if (isErr.equalsIgnoreCase("incorrect email")) {
                errorLabel.setValue("Введён некорректный Email");
            } else {
                errorLabel.setValue("Неизвестная ошибка");
            }
        });
        form.addComponent(saveButton);

        Panel panel = new Panel("Вход");
        panel.setSizeUndefined();
        panel.setContent(form);

        addComponent(AppUI.get().createMenu());
        addComponent(panel);
    }
}
