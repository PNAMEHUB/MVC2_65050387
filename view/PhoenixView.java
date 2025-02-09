package view;

import javax.swing.*;
import java.awt.*;

public class PhoenixView extends JFrame {
    private JTextField idField;
    private JTextField healthCheckDateField;
    private JTextField vaccinesField;
    private JCheckBox fireProofCertificateCheckBox;
    private JButton saveButton;

    public PhoenixView() {
        setTitle("Add Phoenix");
        setSize(400, 300);
        setLayout(new GridLayout(5, 2));

        idField = new JTextField();
        healthCheckDateField = new JTextField();
        vaccinesField = new JTextField();
        fireProofCertificateCheckBox = new JCheckBox("Fire-Proof Certificate");
        saveButton = new JButton("Save");

        add(new JLabel("ID:"));
        add(idField);
        add(new JLabel("Last Health Check Date (dd/MM/yyyy):"));
        add(healthCheckDateField);
        add(new JLabel("Vaccines Received:"));
        add(vaccinesField);
        add(new JLabel("Fire-Proof Certificate:"));
        add(fireProofCertificateCheckBox);
        add(new JLabel());
        add(saveButton);
    }

    public void setId(String id) {
        idField.setText(id);
        idField.setEditable(false); // ป้องกันการแก้ไขรหัส
    }

    public String getId() {
        return idField.getText();
    }

    public String getHealthCheckDate() {
        return healthCheckDateField.getText();
    }

    public int getVaccinesReceived() {
        return Integer.parseInt(vaccinesField.getText());
    }

    public boolean hasFireProofCertificate() {
        return fireProofCertificateCheckBox.isSelected();
    }

    public void setSaveListener(Runnable listener) {
        saveButton.addActionListener(e -> {
            listener.run();
            dispose(); // ปิดหน้าต่างหลังจากบันทึก
        });
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}