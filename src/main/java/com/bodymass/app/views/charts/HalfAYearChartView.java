package com.bodymass.app.views.charts;

import com.bodymass.app.AppUI;
import com.bodymass.app.UserState;
import com.bodymass.app.db.model.Weight;
import com.bodymass.app.services.WeightService;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import java.sql.Date;
import java.util.List;

import static com.bodymass.app.util.DateUtil.*;
import static com.bodymass.app.util.WeightUtil.toRange;

/**
 * A constructor of page with graphs for last half a year
 */

public class HalfAYearChartView extends VerticalLayout {
    private WeightService weightService = new WeightService();

    public HalfAYearChartView() {
        Panel panel = new Panel("График за полгода");
        panel.setSizeUndefined();
        long userId = UserState.get().getUser().getId();

        List<Weight> weights = weightService.selectLastHalfAYear(userId);
        Date today = today();
        Date nDaysAgo = addDays(subtractMonths(today, 6), 1);
        panel.setContent(new ChartView("Индекс массы тела", toRange(weights, nDaysAgo, today, 0)));

        addComponent(AppUI.get().createMenu());
        addComponent(panel);
    }
}
