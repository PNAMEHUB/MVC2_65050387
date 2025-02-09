package model;

import java.util.Date;

public abstract class MagicPet {
    private String id;
    private String type;
    private Date lastHealthCheckDate;
    private int vaccinesReceived;

    public MagicPet(String id, String type, Date lastHealthCheckDate, int vaccinesReceived) {
        this.id = id;
        this.type = type;
        this.lastHealthCheckDate = lastHealthCheckDate;
        this.vaccinesReceived = vaccinesReceived;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Date getLastHealthCheckDate() {
        return lastHealthCheckDate;
    }

    public int getVaccinesReceived() {
        return vaccinesReceived;
    }

    public abstract boolean validate();
}