package com.bodymass.app.views.charts;

import com.bodymass.app.AppUI;
import com.bodymass.app.UserState;
import com.bodymass.app.db.model.Weight;
import com.bodymass.app.services.WeightService;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.bodymass.app.util.DateUtil.*;
import static com.bodymass.app.util.WeightUtil.toRange;

public class PeriodWeeksChartView extends VerticalLayout {
    private WeightService weightService = new WeightService();

    public PeriodWeeksChartView(Date from, Date to) {
        String title = String.format("График за период с %s по %s", from, to);
        Panel panel = new Panel(title);
        panel.setSizeUndefined();
        long userId = UserState.get().getUser().getId();

        List<Weight> weights = weightService.selectPeriod(userId, from, to);
        Date today = today();

        LocalDate d1 = LocalDate.parse(to.toString(), DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate d2 = LocalDate.parse(from.toString(), DateTimeFormatter.ISO_LOCAL_DATE);
        Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
        long diffDays = diff.toDays();
        int diffInt = (int) diffDays;

        Date nDaysAgo = subtractDays(to, diffInt);
        panel.setContent(new ChartView(title, toRange(weights, nDaysAgo, today, 0)));

        addComponent(AppUI.get().createMenu());
        addComponent(panel);
    }

}
