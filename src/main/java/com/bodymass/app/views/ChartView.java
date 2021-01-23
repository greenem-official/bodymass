package com.bodymass.app.views;

import com.bodymass.app.data.Weight;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.vaadin.addon.JFreeChartWrapper;

import java.util.List;

//  = "Индекс массы тела за месяц"
public class ChartView extends VerticalLayout {
    String title;

    String xTitle = "День";
    String yTitle = "Значение";

    List<Weight> weights;

    public ChartView(String title, List<Weight> weights) {
        this.weights = weights;
        addComponent(creditGraphLayout());
    }

    public VerticalLayout creditGraphLayout(){
        String rowKey = "Индекс массы тела";
        VerticalLayout layout = new VerticalLayout();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Weight weight : weights) {
            dataset.setValue(weight.getValue(), rowKey, weight.getData().toString());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                title,
                xTitle,
                yTitle,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );


        JFreeChartWrapper wrapper = new JFreeChartWrapper(chart);

        layout.addComponent(wrapper);

        return layout;
    }

}
