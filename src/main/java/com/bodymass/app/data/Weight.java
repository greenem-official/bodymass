package com.bodymass.app.data;

import java.sql.Date;

public class Weight {
    private long id;
    private long userId;
    private Date data;
    private double value;

    public Weight(long id, long userId, Date data, double value) {
        this.id = id;
        this.userId = userId;
        this.data = data;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public long getUserid() {
        return userId;
    }

    public Date getData() {
        return data;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Weight{" +
                "id=" + id +
                ", userId=" + userId +
                ", data=" + data +
                ", value=" + value +
                '}';
    }
}
