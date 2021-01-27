package com.bodymass.app.views.charts;

import com.bodymass.app.AppUI;
import com.bodymass.app.UserState;
import com.bodymass.app.services.WeightService;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class MonthChartView extends VerticalLayout {
    private WeightService weightService = new WeightService();

    public MonthChartView() {
        Panel panel = new Panel("График за месяц");
        panel.setSizeUndefined();
        long userId = UserState.get().getUser().getId();
        panel.setContent(new ChartView("Индекс массы тела за месяц", weightService.selectLastMonth(userId)));

        addComponent(AppUI.get().createMenu());
        addComponent(panel);
    }
}
