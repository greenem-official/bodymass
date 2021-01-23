package com.bodymass.app;

import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;

import com.bodymass.app.db.model.User;
import com.bodymass.app.services.UserService;
import com.bodymass.app.services.WeightService;
import com.bodymass.app.views.ChartView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Title("Weight")
@Theme("mytheme")
public class AppUI extends UI {
    private UserService userService = new UserService();
    private WeightService weightService = new WeightService();

    private Button loginButton;
    private Button registrationButton;
    private Button weekChartButton;
    private Button twoWeeksChartButton;
    private Button monthChartButton;
    private Button halfYearChartButton;
    private Button yearChartButton;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(createRegistrationPanel());
    }

    private VerticalLayout createRegistrationPanel() {
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
                setContent(createAfterAuthPanel());
            }
        });
        form.addComponent(saveButton);

        Panel panel = new Panel("Регистрация");
        panel.setSizeUndefined();
        panel.setContent(form);

        VerticalLayout content = new VerticalLayout();
        content.addComponent(createMenu());
        content.addComponent(panel);

        return content;
    }

    private VerticalLayout createLoginPanel() {
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
                isErr = userService.login(emailField.getValue(), passwordField.getValue());
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
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                errorLabel.setValue("Вы вошли");
                setContent(createAfterAuthPanel());
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

        VerticalLayout content = new VerticalLayout();
        content.addComponent(createMenu());
        content.addComponent(panel);

        return content;
    }

    private HorizontalLayout createMenu() {
        HorizontalLayout content = new HorizontalLayout();

        loginButton = new Button("Войти");
        loginButton.addClickListener(e -> setContent(createLoginPanel()));
        content.addComponent(loginButton);

        registrationButton = new Button("Регистрация");
        registrationButton.addClickListener(e -> setContent(createRegistrationPanel()));
        content.addComponent(registrationButton);

        if (UserState.get().getUser() != null) {
            weekChartButton = new Button("График за неделю");
            weekChartButton.addClickListener(e -> setContent(createWeekChartPanel()));
            content.addComponent(weekChartButton);

            twoWeeksChartButton = new Button("График за 2 недели");
            twoWeeksChartButton.addClickListener(e -> setContent(createTwoWeeksChartPanel()));
            content.addComponent(twoWeeksChartButton);
        }

        return content;
    }

    private VerticalLayout createAfterAuthPanel() {
        loginButton.setVisible(false);
        registrationButton.setVisible(false);

        //setContent(new Panel());

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

        VerticalLayout content = new VerticalLayout();
        content.addComponent(createMenu());
        content.addComponent(announcementField);
        content.addComponent(panel);

        announcementField.setContent(new Label("Вы не вводили данные вчера! (fake message)"));

        return content;
    }

    private VerticalLayout createWeekChartPanel() {
        Panel panel = new Panel("График за неделю");
        panel.setSizeUndefined();
        long userId = UserState.get().getUser().getId();
        panel.setContent(new ChartView("Индекс массы тела за недею", weightService.selectLastWeek(userId)));

        VerticalLayout content = new VerticalLayout();
        content.addComponent(createMenu());
        content.addComponent(panel);

        return content;
    }

    private VerticalLayout createTwoWeeksChartPanel() {
        Panel panel = new Panel("График за 2 недели");
        panel.setSizeUndefined();
        long userId = UserState.get().getUser().getId();
        panel.setContent(new ChartView("Индекс массы тела за недею", weightService.selectLastTwoWeeks(userId)));

        VerticalLayout content = new VerticalLayout();
        content.addComponent(createMenu());
        content.addComponent(panel);

        return content;
    }


    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = AppUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}