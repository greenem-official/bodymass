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

public class YearChartView extends VerticalLayout {
    private WeightService weightService = new WeightService();

    public YearChartView() {
        Panel panel = new Panel("График за год");
        panel.setSizeUndefined();
        long userId = UserState.get().getUser().getId();

        List<Weight> weights = weightService.selectLastYear(userId);
        Date today = today();
        Date nDaysAgo = addDays(subtractYears(today, 1), 1);
        panel.setContent(new ChartView("Индекс массы тела", toRange(weights, nDaysAgo, today, 0)));

        addComponent(AppUI.get().createMenu());
        addComponent(panel);
    }
}
