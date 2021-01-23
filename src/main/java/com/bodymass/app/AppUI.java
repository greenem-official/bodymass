package com.bodymass.app;

import javax.servlet.annotation.WebServlet;

import com.bodymass.app.views.AddWeightView;
import com.bodymass.app.views.LoginView;
import com.bodymass.app.views.RegistrationView;
import com.bodymass.app.views.charts.OneWeekChartView;
import com.bodymass.app.views.charts.TwoWeeksChartView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Title("BobyMass")
@Theme("mytheme")
public class AppUI extends UI {
    public static AppUI get() {
        return (AppUI) UI.getCurrent();
    }

    public Button loginButton;
    public Button registrationButton;
    public Button exitButton;

    public Button addWeightButton;

    public Button weekChartButton;
    public Button twoWeeksChartButton;
    public Button monthChartButton;
    public Button halfYearChartButton;
    public Button yearChartButton;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(new LoginView());
    }

    public HorizontalLayout createMenu() {
        HorizontalLayout content = new HorizontalLayout();

        if (UserState.get().getUser() == null) {
            loginButton = new Button("Войти");
            loginButton.addClickListener(e -> setContent(new LoginView()));
            content.addComponent(loginButton);

            registrationButton = new Button("Регистрация");
            registrationButton.addClickListener(e -> setContent(new RegistrationView()));
            content.addComponent(registrationButton);
        } else {
            exitButton = new Button("Выйти");
            exitButton.addClickListener(e -> {
                UserState.get().setUser(null);
                setContent(new LoginView());
            });
            content.addComponent(exitButton);

            addWeightButton = new Button("Ввести вес");
            addWeightButton.addClickListener(e -> {
                setContent(new AddWeightView());
            });
            content.addComponent(addWeightButton);

            weekChartButton = new Button("График за неделю");
            weekChartButton.addClickListener(e -> setContent(new OneWeekChartView()));
            content.addComponent(weekChartButton);

            twoWeeksChartButton = new Button("График за 2 недели");
            twoWeeksChartButton.addClickListener(e -> setContent(new TwoWeeksChartView()));
            content.addComponent(twoWeeksChartButton);

            monthChartButton = new Button("График за месяц");
            content.addComponent(monthChartButton);

            halfYearChartButton = new Button("График за полгода");
            content.addComponent(halfYearChartButton);

            yearChartButton = new Button("График за год");
            content.addComponent(yearChartButton);
        }

        return content;
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = AppUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}