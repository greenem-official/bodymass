package com.bodymass.app.views.charts;

import com.bodymass.app.AppUI;
import com.bodymass.app.UserState;
import com.bodymass.app.services.WeightService;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import java.sql.Date;

public class PeriodWeeksChartView extends VerticalLayout {
    private WeightService weightService = new WeightService();

    public PeriodWeeksChartView(Date from, Date to) {
        String title = String.format("График за период с %s по %s", from, to);
        Panel panel = new Panel(title);
        panel.setSizeUndefined();
        long userId = UserState.get().getUser().getId();
        panel.setContent(new ChartView(title, weightService.selectPeriod(userId, from, to)));

        addComponent(AppUI.get().createMenu());
        addComponent(panel);
    }

}
