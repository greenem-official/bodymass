package com.bodymass.app;

import javax.servlet.annotation.WebServlet;

import com.bodymass.app.views.AddWeightView;
import com.bodymass.app.views.LoginView;
import com.bodymass.app.views.RegistrationView;
import com.bodymass.app.views.charts.*;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.sql.Date;
import java.time.LocalDate;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@PreserveOnRefresh
@Title("Weight")
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
    public DateField fromDate;
    public DateField toDate;
    public Button periodButton;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(new LoginView());
    }

    public GridLayout createMenu() {
        HorizontalLayout line1 = new HorizontalLayout();
        HorizontalLayout line2 = new HorizontalLayout();
        GridLayout grid = new GridLayout(1, 2);

        if (UserState.get().getUser() == null) {
            loginButton = new Button("Войти");
            loginButton.addClickListener(e -> setContent(new LoginView()));
            line1.addComponent(loginButton);

            registrationButton = new Button("Регистрация");
            registrationButton.addClickListener(e -> setContent(new RegistrationView()));
            line1.addComponent(registrationButton);

            grid.addComponent(line1, 0, 0);
        } else {
            exitButton = new Button("Выйти");
            exitButton.addClickListener(e -> {
                UserState.get().setUser(null);
                setContent(new LoginView());
            });
            line1.addComponent(exitButton);

            addWeightButton = new Button("Ввести вес");
            addWeightButton.addClickListener(e -> {
                setContent(new AddWeightView());
            });
            line1.addComponent(addWeightButton);

            weekChartButton = new Button("График за неделю");
            weekChartButton.addClickListener(e -> setContent(new OneWeekChartView()));
            line1.addComponent(weekChartButton);

            twoWeeksChartButton = new Button("График за 2 недели");
            twoWeeksChartButton.addClickListener(e -> setContent(new TwoWeeksChartView()));
            line1.addComponent(twoWeeksChartButton);

            monthChartButton = new Button("График за месяц");
            monthChartButton.addClickListener(e -> setContent(new MonthChartView()));
            line1.addComponent(monthChartButton);

            halfYearChartButton = new Button("График за полгода");
            halfYearChartButton.addClickListener(e -> setContent(new HalfAYearChartView()));
            line1.addComponent(halfYearChartButton);

            yearChartButton = new Button("График за год");
            yearChartButton.addClickListener(e -> setContent(new YearChartView()));
            line1.addComponent(yearChartButton);

            fromDate = new DateField();
//            fromDate.setValue(new LocalDate());
            fromDate.setDateFormat("dd-MM-yyyy");
            line2.addComponent(fromDate);

            toDate = new DateField();
//            fromDate.setValue(new LocalDate());
            toDate.setDateFormat("dd-MM-yyyy");
            line2.addComponent(toDate);

            periodButton = new Button("График за период");
            periodButton.addClickListener(e -> {
                if(fromDate.getValue() == null || toDate.getValue() == null) {
                    Notification.show("Не указана дата");
                } else {
                    setContent(new PeriodWeeksChartView(Date.valueOf(fromDate.getValue()), Date.valueOf(toDate.getValue())));
                }
            });
            line2.addComponent(periodButton);

            grid.addComponent(line1, 0, 0);
            grid.addComponent(line2, 0, 1);
        }

        return grid;
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = AppUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}