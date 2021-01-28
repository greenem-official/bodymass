package com.bodymass.app.views;

import com.bodymass.app.AppUI;
import com.bodymass.app.UserState;
import com.bodymass.app.db.dao.WeightDAO;
import com.bodymass.app.services.WeightService;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import sun.jvm.hotspot.SALauncher;

import java.sql.Date;
import java.sql.SQLException;

public class AddWeightView extends VerticalLayout {
    private WeightService weightService = new WeightService();
    private WeightDAO weightDao = new WeightDAO();

    public TextField weightLast;
    public TextField weightToday;
    public Button confirmSendingWeight;

    public AddWeightView() {
        if(AppUI.get().loginButton!=null) {
            AppUI.get().loginButton.setVisible(false);
        }
        else{
            System.out.println("loginButton is null");
        }
        if(AppUI.get().registrationButton!=null) {
            AppUI.get().registrationButton.setVisible(false);
        }
        else{
            System.out.println("registrationButton is null");
        }
        if(AppUI.get().chartsControlling!=null) {
            AppUI.get().chartsControlling.setVisible(false);
        }
        else{
            System.out.println("chartsControlling is null");
        }

        FormLayout form = new FormLayout();
        form.setMargin(true);

        Label errorLabel = new Label("");
        errorLabel.setVisible(false);

        TextField lastWeight = new TextField("Вес в прошлый раз");
        lastWeight.setRequiredIndicatorVisible(false);
        lastWeight.setEnabled(false);
        weightLast = lastWeight;
        Double lastValue = null;
        if(UserState.get().getUser()!=null) {
            try {
                lastValue = weightDao.getLastWeight(UserState.get().getUser().getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(lastValue!=null){
            lastWeight.setValue(Double.toString(lastValue));
        }
        else{
            lastWeight.setValue("Ещё нет");
        }
        form.addComponent(lastWeight);

        TextField TodayWeight = new TextField("Вес сегодня");
        TodayWeight.setRequiredIndicatorVisible(false);
        weightToday = TodayWeight;
        form.addComponent(TodayWeight);

        Button saveButton = new Button("Подтвердить");
        confirmSendingWeight = saveButton;
        saveButton.addClickListener(e -> {
            String value = weightToday.getValue();
            value = value.replaceAll(",", ".");
            String sendErr = weightService.isSendWeightFieldCorrect(value);
            if(sendErr.equalsIgnoreCase("not double")){
                errorLabel.setVisible(true);
                errorLabel.setValue("Пожалуйста, введите число");
//                weightToday.wi
            }
            else if(sendErr.equalsIgnoreCase("successful")){
                weightService.addWeight(UserState.get().getUser().getId(), new Date(System.currentTimeMillis()), Double.parseDouble(value));
                errorLabel.setValue("Данные успешно отправленны");
                errorLabel.setVisible(true);
            }
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
