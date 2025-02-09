package model;

import java.util.Date;

public class Owl extends MagicPet {
    private int flightDistanceWithoutFood;

    public Owl(String id, Date lastHealthCheckDate, int vaccinesReceived, int flightDistanceWithoutFood) {
        super(id, "Owl", lastHealthCheckDate, vaccinesReceived);
        this.flightDistanceWithoutFood = flightDistanceWithoutFood;
    }

    @Override
    public boolean validate() {
        return flightDistanceWithoutFood >= 100;
    }
}
