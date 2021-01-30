package com.bodymass.app.views.charts;

import com.bodymass.app.AppUI;
import com.bodymass.app.UserState;
import com.bodymass.app.db.model.Weight;
import com.bodymass.app.services.WeightService;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import java.sql.Date;
import java.util.List;

import static com.bodymass.app.util.WeightUtil.toRange;
import static com.bodymass.app.util.DateUtil.today;
import static com.bodymass.app.util.DateUtil.subtractDays;
import static com.bodymass.app.util.DateUtil.addDays;

public class OneWeekChartView extends VerticalLayout {
    private WeightService weightService = new WeightService();

    public OneWeekChartView() {
        Panel panel = new Panel("График за неделю");
        panel.setSizeUndefined();
        long userId = UserState.get().getUser().getId();

        List<Weight> weights = weightService.selectLastWeek(userId);
        Date today = today();
        Date sixDaysAgo = subtractDays(today, 6);
        panel.setContent(new ChartView("Индекс массы тела за недею", toRange(weights, sixDaysAgo, today, 0.0)));

        addComponent(AppUI.get().createMenu());
        addComponent(panel);
    }

}
