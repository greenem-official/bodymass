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

import static com.bodymass.app.util.DateUtil.subtractDays;
import static com.bodymass.app.util.WeightUtil.toRange;

/**
 * A constructor of page with graphs for universal period of time
 */

public class PeriodWeeksChartView extends VerticalLayout {
    private WeightService weightService = new WeightService();

    public PeriodWeeksChartView(Date from, Date to) {
        String title = String.format("График за период с %s по %s", from, to);
        Panel panel = new Panel(title);
        panel.setSizeUndefined();
        long userId = UserState.get().getUser().getId();

        List<Weight> weights = weightService.selectPeriod(userId, from, to);

        LocalDate d1 = LocalDate.parse(to.toString(), DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate d2 = LocalDate.parse(from.toString(), DateTimeFormatter.ISO_LOCAL_DATE);
        Duration diff = Duration.between(d2.atStartOfDay(), d1.atStartOfDay());
        long diffDays = diff.toDays();

        Date nDaysAgo = subtractDays(to, (int) diffDays);
        panel.setContent(new ChartView(title, toRange(weights, nDaysAgo, to, 0)));

        addComponent(AppUI.get().createMenu());
        addComponent(panel);
    }

}
