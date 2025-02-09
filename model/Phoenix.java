package model;

import java.util.Date;

public class Phoenix extends MagicPet {
    private boolean fireProofCertificate;

    public Phoenix(String id, Date lastHealthCheckDate, int vaccinesReceived, boolean fireProofCertificate) {
        super(id, "Phoenix", lastHealthCheckDate, vaccinesReceived);
        this.fireProofCertificate = fireProofCertificate;
    }

    @Override
    public boolean validate() {
        return fireProofCertificate;
    }
}