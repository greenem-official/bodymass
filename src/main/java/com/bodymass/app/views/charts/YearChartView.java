package com.bodymass.app.views.charts;

import com.bodymass.app.AppUI;
import com.bodymass.app.UserState;
import com.bodymass.app.services.WeightService;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class YearChartView extends VerticalLayout {
    private WeightService weightService = new WeightService();

    public YearChartView() {
        Panel panel = new Panel("График за год");
        panel.setSizeUndefined();
        long userId = UserState.get().getUser().getId();
        panel.setContent(new ChartView("Индекс массы тела за год", weightService.selectLastYear(userId)));
        addComponent(AppUI.get().createMenu());
        addComponent(panel);
    }
}
