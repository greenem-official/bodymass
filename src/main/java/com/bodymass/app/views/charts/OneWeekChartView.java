package com.bodymass.app.views.charts;

import com.bodymass.app.AppUI;
import com.bodymass.app.UserState;
import com.bodymass.app.db.model.Weight;
import com.bodymass.app.services.WeightService;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import java.sql.Date;
import java.util.List;

import static com.bodymass.app.util.DateUtil.subtractDays;
import static com.bodymass.app.util.DateUtil.today;
import static com.bodymass.app.util.WeightUtil.toRange;

/**
 * A constructor of page with graphs for last week
 */

public class OneWeekChartView extends VerticalLayout {
    private WeightService weightService = new WeightService();

    public OneWeekChartView() {
        Panel panel = new Panel("График за неделю");
        panel.setSizeUndefined();
        long userId = UserState.get().getUser().getId();

        List<Weight> weights = weightService.selectLastWeek(userId);
        //System.out.println(weights.toString());
        Date today = today();
        Date nDaysAgo = subtractDays(today, 6);
        panel.setContent(new ChartView("Индекс массы тела", toRange(weights, nDaysAgo, today, 0)));
        //System.out.println(toRange(weights, sixDaysAgo, today, 0).toString());

        addComponent(AppUI.get().createMenu());
        addComponent(panel);
    }

}
