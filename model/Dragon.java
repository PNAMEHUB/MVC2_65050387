package model;

import java.util.Date;

public class Dragon extends MagicPet {
    private int pollutionLevel;

    public Dragon(String id, Date lastHealthCheckDate, int vaccinesReceived, int pollutionLevel) {
        super(id, "Dragon", lastHealthCheckDate, vaccinesReceived);
        this.pollutionLevel = pollutionLevel;
    }

    @Override
    public boolean validate() {
        return pollutionLevel <= 70;
    }
}
