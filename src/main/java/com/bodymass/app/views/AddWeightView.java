package com.bodymass.app.views;

import com.bodymass.app.AppUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class AddWeightView extends VerticalLayout {
    public AddWeightView() {
        AppUI.get().loginButton.setVisible(false);
        AppUI.get().registrationButton.setVisible(false);

        FormLayout form = new FormLayout();
        form.setMargin(true);

        Label errorLabel = new Label("");
        errorLabel.setVisible(false);

        TextField emailField = new TextField("Вес в прошлый раз");
        emailField.setRequiredIndicatorVisible(false);
        form.addComponent(emailField);

        TextField passwordField = new TextField("Вес сегодня");
        passwordField.setRequiredIndicatorVisible(false);
        form.addComponent(passwordField);

        Button saveButton = new Button("Подтвердить");
        saveButton.addClickListener(e -> {
            errorLabel.setValue("Данные успешно отправленны (fake message)");
            errorLabel.setVisible(true);
        });
        form.addComponent(saveButton);

        VerticalLayout VLayout = new VerticalLayout();
        Panel announcementField = new Panel("");
        VLayout.addComponent(announcementField);
        VLayout.addComponent(form);
        VLayout.addComponent(errorLabel);

        Panel panel = new Panel("Ввести вес");
        panel.setSizeUndefined();
        panel.setContent(VLayout);

        addComponent(AppUI.get().createMenu());
        addComponent(announcementField);
        addComponent(panel);

        announcementField.setContent(new Label("Вы не вводили данные вчера! (fake message)"));
    }
}
