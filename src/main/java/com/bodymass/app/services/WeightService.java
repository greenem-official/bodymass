package com.bodymass.app.services;

import com.bodymass.app.db.model.Weight;
import com.bodymass.app.db.dao.WeightDAO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WeightService {
    private WeightDAO weightDAO = new WeightDAO();

    public List<Weight> selectLastWeek(long userId) {
        try {
            return weightDAO.selectLastWeek(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Weight> selectLastTwoWeeks(long userId) {
        try {
            return weightDAO.selectLastTwoWeeks(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Weight> selectLastMonth(long userId) {
        try {
            return weightDAO.selectLastMonth(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Weight> selectLastHalfAYear(long userId) {
        try {
            return weightDAO.selectLastHalfAYear(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Weight> selectLastYear(long userId) {
        try {
            return weightDAO.selectLastYear(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Weight> selectPeriod(long userId, Date from, Date to) {
        try {
            return weightDAO.selectPeriod(userId, from, to);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addWeight(long userId, Date data, double value) {
        try {
            weightDAO.addWeightDAO(userId, data, value);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String isSendWeightFieldCorrect(String weight) {
        weight = weight.replaceAll(",", ".");
        double w = 0;
        try{
            w = Double.parseDouble(weight);
        }
        catch(NumberFormatException e){
            return "not double";
        }
        if(w<=0){
            return "negative or 0";
        }
        return "successful";
    }

//    public List<Weight> fixEmptyDates(List<Weight> list){
//        for (int i = 0; i<list.size(); i++){
//            if(list.)
//        }
//        list.add()
//        return list;
//    }
}
