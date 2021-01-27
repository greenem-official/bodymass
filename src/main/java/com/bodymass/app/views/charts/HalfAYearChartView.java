package com.bodymass.app.views.charts;

import com.bodymass.app.AppUI;
import com.bodymass.app.UserState;
import com.bodymass.app.services.WeightService;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class HalfAYearChartView extends VerticalLayout {
    private WeightService weightService = new WeightService();

    public HalfAYearChartView() {
        Panel panel = new Panel("График за полгода");
        panel.setSizeUndefined();
        long userId = UserState.get().getUser().getId();
        panel.setContent(new ChartView("Индекс массы тела за полгода", weightService.selectLastHalfAYear(userId)));
        addComponent(AppUI.get().createMenu());
        addComponent(panel);
    }
}
