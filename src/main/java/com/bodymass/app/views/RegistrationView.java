package com.bodymass.app.views;

import com.bodymass.app.AppUI;
import com.bodymass.app.services.UserService;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import java.sql.SQLException;

public class RegistrationView extends VerticalLayout {
    private UserService userService = new UserService();

    public RegistrationView() {
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

        TextField secondPasswordField = new TextField("Подтвердите пароль");
        secondPasswordField.setRequiredIndicatorVisible(true);
        form.addComponent(secondPasswordField);

        Button saveButton = new Button("Зарегистрироваться");
        saveButton.addClickListener(e -> {
            String isErr = "undefined";
            try {
                isErr = userService.register(emailField.getValue(), passwordField.getValue(), secondPasswordField.getValue());
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            errorLabel.setVisible(true);
            if (isErr.equalsIgnoreCase("empty email")) {
                errorLabel.setValue("Email не должен быть пустым");
            } else if (isErr.equalsIgnoreCase("empty password")) {
                errorLabel.setValue("Пароль не должен быть пустым");
            } else if (isErr.equalsIgnoreCase("password mismatch")) {
                errorLabel.setValue("Пароли не совпадают");
            } else if (isErr.equalsIgnoreCase("incorrect email")) {
                errorLabel.setValue("Введён некорректный Email");
            } else if (isErr.equalsIgnoreCase("registered already")) {
                errorLabel.setValue("Данный пользователь уже зарегистрирован");
            } else if (isErr.equalsIgnoreCase("error")) {
                errorLabel.setValue("Ошибка регистрации");
            } else if (isErr.equalsIgnoreCase("undefined")) {
                errorLabel.setValue("Ошибка регистрации");
            } else if (isErr.equalsIgnoreCase("successful")) {
                errorLabel.setValue("Вы успешно зарегистрированы");
                //setContent(createLoginPanel());
                AppUI.get().setContent(new AddWeightView());
            }
        });
        form.addComponent(saveButton);

        Panel panel = new Panel("Регистрация");
        panel.setSizeUndefined();
        panel.setContent(form);

        addComponent(AppUI.get().createMenu());
        addComponent(panel);
    }
}
