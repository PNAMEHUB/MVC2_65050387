package model;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MagicPetDatabase {
    private static final String CSV_FILE = "data/magic_pets.csv";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    // สร้างไฟล์ CSV หากไฟล์ไม่มีอยู่
    private void createFileIfNotExists() {
        File file = new File(CSV_FILE);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs(); // สร้างโฟลเดอร์ data หากไม่มี
                FileWriter writer = new FileWriter(file);
                // เขียนหัวข้อ(header) ของไฟล์ CSV
                writer.append("ID,Type,LastHealthCheckDate,VaccinesReceived\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // บันทึกข้อมูลสัตว์เลี้ยงลงในไฟล์ CSV
    public void saveMagicPet(MagicPet pet) {
        createFileIfNotExists(); // ตรวจสอบและสร้างไฟล์หากไม่มี
        try (FileWriter writer = new FileWriter(CSV_FILE, true)) {
            writer.append(pet.getId()).append(",")
                  .append(pet.getType()).append(",")
                  .append(DATE_FORMAT.format(pet.getLastHealthCheckDate())).append(",")
                  .append(String.valueOf(pet.getVaccinesReceived())).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // โหลดข้อมูลสัตว์เลี้ยงจากไฟล์ CSV
    public List<MagicPet> loadMagicPets() {
        createFileIfNotExists(); // ตรวจสอบและสร้างไฟล์หากไม่มี
        List<MagicPet> pets = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // ข้ามหัวข้อ (header)
                    continue;
                }
                String[] data = line.split(",");
                String id = data[0];
                String type = data[1];
                Date lastHealthCheckDate = DATE_FORMAT.parse(data[2]);
                int vaccinesReceived = Integer.parseInt(data[3]);

                switch (type) {
                    case "Phoenix":
                        pets.add(new Phoenix(id, lastHealthCheckDate, vaccinesReceived, true));
                        break;
                    case "Dragon":
                        pets.add(new Dragon(id, lastHealthCheckDate, vaccinesReceived, 0));
                        break;
                    case "Owl":
                        pets.add(new Owl(id, lastHealthCheckDate, vaccinesReceived, 0));
                        break;
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return pets;
    }
}