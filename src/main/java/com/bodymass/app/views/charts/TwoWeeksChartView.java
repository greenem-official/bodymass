package com.bodymass.app.views.charts;

import com.bodymass.app.AppUI;
import com.bodymass.app.UserState;
import com.bodymass.app.services.WeightService;
import com.bodymass.app.views.ChartView;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class TwoWeeksChartView extends VerticalLayout {
    private WeightService weightService = new WeightService();

    public TwoWeeksChartView() {
        Panel panel = new Panel("График за 2 недели");
        panel.setSizeUndefined();
        long userId = UserState.get().getUser().getId();
        panel.setContent(new ChartView("Индекс массы тела за недею", weightService.selectLastTwoWeeks(userId)));

        addComponent(AppUI.get().createMenu());
        addComponent(panel);
    }

}
