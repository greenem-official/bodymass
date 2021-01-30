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

public class TwoWeeksChartView extends VerticalLayout {
    private WeightService weightService = new WeightService();

    public TwoWeeksChartView() {
        Panel panel = new Panel("График за 2 недели");
        panel.setSizeUndefined();
        long userId = UserState.get().getUser().getId();

        List<Weight> weights = weightService.selectLastTwoWeeks(userId);
        Date today = today();
        Date nDaysAgo = subtractDays(today, 13);
        panel.setContent(new ChartView("Индекс массы тела", toRange(weights, nDaysAgo, today, 0)));

        addComponent(AppUI.get().createMenu());
        addComponent(panel);
    }

}
