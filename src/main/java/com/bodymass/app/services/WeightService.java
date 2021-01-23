package com.bodymass.app.services;

import com.bodymass.app.db.model.Weight;
import com.bodymass.app.db.dao.WeightDAO;

import java.sql.SQLException;
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
}