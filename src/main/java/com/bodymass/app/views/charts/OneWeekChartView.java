package com.bodymass.app.views.charts;

import com.bodymass.app.AppUI;
import com.bodymass.app.UserState;
import com.bodymass.app.services.WeightService;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class OneWeekChartView extends VerticalLayout {
    private WeightService weightService = new WeightService();

    public OneWeekChartView() {
        Panel panel = new Panel("График за неделю");
        panel.setSizeUndefined();
        long userId = UserState.get().getUser().getId();
        panel.setContent(new ChartView("Индекс массы тела за недею", weightService.selectLastWeek(userId)));

        addComponent(AppUI.get().createMenu());
        addComponent(panel);
    }

}
