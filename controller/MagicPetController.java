package controller;

import model.*;
import view.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MagicPetController {
    private MagicPetDatabase database;
    private MainFrame mainFrame;
    private PhoenixView phoenixView;
    private DragonView dragonView;
    private OwlView owlView;

    public MagicPetController(MagicPetDatabase database, MainFrame mainFrame) {
        this.database = database;
        this.mainFrame = mainFrame;

        mainFrame.setAddPhoenixListener(e -> openPhoenixView());
        mainFrame.setAddDragonListener(e -> openDragonView());
        mainFrame.setAddOwlListener(e -> openOwlView());
        mainFrame.setReportListener(e -> generateReport());
    }

    private void openPhoenixView() {
        phoenixView = new PhoenixView();
        phoenixView.setId(generateUniqueId());
        phoenixView.setSaveListener(this::savePhoenix);
        phoenixView.setVisible(true);
    }

    private void openDragonView() {
        dragonView = new DragonView();
        dragonView.setId(generateUniqueId());
        dragonView.setSaveListener(this::saveDragon);
        dragonView.setVisible(true);
    }

    private void openOwlView() {
        owlView = new OwlView();
        owlView.setId(generateUniqueId());
        owlView.setSaveListener(this::saveOwl);
        owlView.setVisible(true);
    }

    // สร้างรหัสแบบสุ่มและตรวจสอบว่าไม่ซ้ำกัน
    private String generateUniqueId() {
        Random random = new Random();
        String id;
        List<MagicPet> pets = database.loadMagicPets();
        do {
            id = String.format("%08d", random.nextInt(90000000) + 10000000); // สร้างรหัส 8 หลัก
            
            String id0 = String.valueOf(id);
            if (id0.startsWith("0")) { // ตรวจสอบว่าเลขขึ้นต้นด้วย 0 หรือไม่
                while (id0.startsWith("0")) { // สร้างเลขสุ่มใหม่จนกว่าจะไม่ขึ้นต้นด้วย 0
                    id = String.format("%08d", random.nextInt(90000000) + 10000000);
                    id0 = String.valueOf(id);
                }
            }
        } while (isIdExists(pets, id) );
        return id;
    }

    // ตรวจสอบว่ารหัสซ้ำหรือไม่
    private boolean isIdExists(List<MagicPet> pets, String id) {
        for (MagicPet pet : pets) {
            if (pet.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    private void savePhoenix() {
        try {
            String id = phoenixView.getId();
            Date lastHealthCheckDate = new SimpleDateFormat("dd/MM/yyyy").parse(phoenixView.getHealthCheckDate());
            int vaccinesReceived = phoenixView.getVaccinesReceived();
            boolean fireProofCertificate = phoenixView.hasFireProofCertificate();

            Phoenix phoenix = new Phoenix(id, lastHealthCheckDate, vaccinesReceived, fireProofCertificate);
            if (phoenix.validate()) {
                database.saveMagicPet(phoenix);
                phoenixView.showMessage("Phoenix added successfully!");
            } else {
                phoenixView.showMessage("Phoenix cannot be added without a fire-proof certificate.");
            }
        } catch (ParseException e) {
            phoenixView.showMessage("Invalid date format. Please try again.");
        }
    }

    private void saveDragon() {
        try {
            int pollutionLevel = dragonView.getPollutionLevel();

            if (pollutionLevel <= 70) { //ตรวจปริมาณมลพิษควัน
                String id = dragonView.getId();
                Date lastHealthCheckDate = new SimpleDateFormat("dd/MM/yyyy").parse(dragonView.getHealthCheckDate());
                int vaccinesReceived = dragonView.getVaccinesReceived();
    
                Dragon dragon = new Dragon(id, lastHealthCheckDate, vaccinesReceived, pollutionLevel);
                database.saveMagicPet(dragon);
                dragonView.showMessage("Dragon added successfully!");
            } else {
                dragonView.showMessage("Dragon cannot be added due to high pollution level.");
            }
        } catch (ParseException e) {
            dragonView.showMessage("Invalid date format. Please try again.");
        }
    }

    private void saveOwl() {
        try {
            int flightDistanceWithoutFood = owlView.getFlightDistanceWithoutFood();

            if (flightDistanceWithoutFood >= 100) { //ตรวจสอบระยะบินโดยไม่กินอาหาร
                String id = owlView.getId();
                Date lastHealthCheckDate = new SimpleDateFormat("dd/MM/yyyy").parse(owlView.getHealthCheckDate());
                int vaccinesReceived = owlView.getVaccinesReceived();
    
                Owl owl = new Owl(id, lastHealthCheckDate, vaccinesReceived, flightDistanceWithoutFood);
                database.saveMagicPet(owl);
                owlView.showMessage("Owl added successfully!");
            } else {
                owlView.showMessage("Owl cannot be added due to insufficient flight distance without food.");
            }
        } catch (ParseException e) {
            owlView.showMessage("Invalid null or date format. Please try again.");
        }
    }

    public void generateReport() {
    List<MagicPet> pets = database.loadMagicPets();
    int phoenixCount = 0;
    int dragonCount = 0;
    int owlCount = 0;
    int rejectedCount = 0;
    
    for (MagicPet pet : pets) {
        System.out.println(pet.getType() + pet.validate());
        if (pet.validate()) {
            switch (pet.getType()) {
                case "Phoenix":
                    phoenixCount++;
                    break;
                case "Dragon":
                    dragonCount++;
                    break;
                case "Owl":
                    owlCount++;
                    break;
            }
        } else {
            rejectedCount++;
        }
    }

    String report = String.format(
        "Magic Pet Report:\n" +
        "Phoenix: %d\n" +
        "Dragon: %d\n" +
        "Owl: %d\n" +
        "Rejected: %d\n",
        phoenixCount, dragonCount, owlCount, rejectedCount
    );

    mainFrame.showMessage(report);
    }
}