package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JButton addPhoenixButton;
    private JButton addDragonButton;
    private JButton addOwlButton;
    private JButton reportButton;

    public MainFrame() {
        setTitle("Magic Pet Management");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        addPhoenixButton = new JButton("Add Phoenix");
        addDragonButton = new JButton("Add Dragon");
        addOwlButton = new JButton("Add Owl");
        reportButton = new JButton("Generate Report");

        add(addPhoenixButton);
        add(addDragonButton);
        add(addOwlButton);
        add(reportButton);
    }

    public void setAddPhoenixListener(ActionListener listener) {
        addPhoenixButton.addActionListener(listener);
    }

    public void setAddDragonListener(ActionListener listener) {
        addDragonButton.addActionListener(listener);
    }

    public void setAddOwlListener(ActionListener listener) {
        addOwlButton.addActionListener(listener);
    }

    public void setReportListener(ActionListener listener) {
        reportButton.addActionListener(listener);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}