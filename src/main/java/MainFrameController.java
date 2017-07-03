import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;

public class MainFrameController extends JFrame {

    private JButton btnShowData, btnEnterData, btnDelete, btnShowDiagram, btnSaveInPdf;
    private JPanel panelFirst, panelSecond;
    private Connection connection = null;

    public MainFrameController(){
        setTitle("База сотрудников");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);

        initComponents();
        initListeners();
    }

    public void showMainFrameWindow() {
        setVisible(true);
    }

    private void initListeners() {
        btnShowData.addActionListener(new btnShowDataListener());
        btnEnterData.addActionListener(new btnEnterDataListener());
        btnDelete.addActionListener(new btnDeleteListener());
        btnShowDiagram.addActionListener(new btnShowDiagramListener());
        btnSaveInPdf.addActionListener(new btnSaveInPdfListener());
    }

    private void initComponents() {
        btnShowData = new JButton("Показать данные");
        btnEnterData = new JButton("Ввести данные");
        btnDelete = new JButton("Удалить");
        btnDelete.setEnabled(false);
        btnShowDiagram = new JButton("Диаграммы");
        btnSaveInPdf = new JButton("Сохранить в PDF");

        panelFirst = new JPanel();
        panelFirst.add(btnShowData);
        panelFirst.add(btnEnterData);
        panelFirst.add(btnDelete);
        panelFirst.add(btnShowDiagram);
        panelFirst.add(btnSaveInPdf);

        panelSecond = new JPanel(new FlowLayout());
        getContentPane().add(panelFirst, BorderLayout.NORTH);
    }

    private class btnSaveInPdfListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            PdfCreator pdfCreator = new PdfCreator();
            pdfCreator.createPDF(panelSecond);
        }
    }

    private class btnDeleteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new UserDeleteListener();
        }
    }

    private class btnShowDataListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            btnDelete.setEnabled(true);

            DriverJDBCInstance driverJDBCInstance = new DriverJDBCInstance();
            connection = driverJDBCInstance.getConnection();

            panelSecond.removeAll();

            ArrayList<UserData> users;
            users = driverJDBCInstance.getlist();

            int id=1;
            for (UserData temp:users) {
                JPanel p = new JPanel(new FlowLayout());
                JLabel labId = new JLabel("Ид:", JLabel.TRAILING);
                final JTextField tfId = new JTextField(String.valueOf(id), 4);
                final JLabel labName = new JLabel("Имя:", JLabel.TRAILING);
                final JTextField tfName = new JTextField(temp.getName(),14);
                JLabel labOcupations = new JLabel("Должность:", JLabel.TRAILING);
                final JTextField tfOcupation = new JTextField(temp.getSpecialty(),14);
                JLabel labSalary = new JLabel("Зарплата:", JLabel.TRAILING);
                final JTextField tfSalary = new JTextField(String.valueOf(temp.getSalary()),5);
                id++;

                JButton editBtn = new JButton("Изменить");

                editBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String[] labels = {"Идентификатор: ", "Имя: ", "Должность: ", "Зарплата: "};
                        int numPairs = labels.length;

                        JPanel p = new JPanel(new SpringLayout());
                        JLabel labId = new JLabel("Идентификатор:", JLabel.TRAILING);
                        final JTextField tfId2 = new JTextField(25);
                        tfId2.setText(tfId.getText());
                        JLabel labName = new JLabel("Имя:", JLabel.TRAILING);
                        final JTextField tfName2 = new JTextField(25);
                        tfName2.setText(tfName.getText());
                        JLabel labOcupations = new JLabel("Должность:", JLabel.TRAILING);
                        final JTextField tfOcupation2 = new JTextField(25);
                        tfOcupation2.setText(tfOcupation.getText());
                        JLabel labSalary = new JLabel("Зарплата:", JLabel.TRAILING);
                        final JTextField tfSalary2 = new JTextField(25);
                        tfSalary2.setText(tfSalary.getText());

                        JButton btnSend = new JButton("ОК");

                        p.add(labId);
                        labId.setLabelFor(tfId2);
                        p.add(tfId2);

                        p.add(labName);
                        labName.setLabelFor(tfName2);
                        p.add(tfName2);

                        p.add(labOcupations);
                        labOcupations.setLabelFor(tfOcupation2);
                        p.add(tfOcupation2);

                        p.add(labSalary);
                        labOcupations.setLabelFor(tfSalary2);
                        p.add(tfSalary2);

                        SpringUtilities.makeCompactGrid(p, numPairs, 2, 6, 6, 6, 6);

                        final JFrame frameEdit = new JFrame("Изменить данные о сотруднике");
                        frameEdit.setResizable(true);

                        p.setOpaque(true);
                        frameEdit.getContentPane().add(p);
                        frameEdit.getContentPane().add(btnSend, BorderLayout.SOUTH);

                        frameEdit.pack();
                        frameEdit.setLocationRelativeTo(null);
                        frameEdit.setVisible(true);

                        btnSend.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                frameEdit.setVisible(false);
                                driverJDBCInstance.sendEditData(tfName2.getText(), tfName.getText(), tfId.getText());
                            }
                        });
                    }
                });

                tfId.setEditable(false);
                tfName.setEditable(false);
                tfOcupation.setEditable(false);
                tfSalary.setEditable(false);

                p.add(labId);
                labId.setLabelFor(tfId);
                p.add(tfId);

                p.add(labName);
                labName.setLabelFor(tfName);
                p.add(tfName);

                p.add(labOcupations);
                labOcupations.setLabelFor(tfOcupation);
                p.add(tfOcupation);

                p.add(labSalary);
                labOcupations.setLabelFor(tfSalary);
                p.add(tfSalary);

                p.add(editBtn);

                panelSecond.add(p);
                add(panelSecond, BorderLayout.CENTER);
                setVisible(true);
            }
        }
    }
    private class btnEnterDataListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            String[] labels = {"Идентификатор: ", "Имя: ", "Должность: ", "Зарплата: "};
            int numPairs = labels.length;

            JPanel p = new JPanel(new SpringLayout());
            JLabel labId = new JLabel("Идентификатор:", JLabel.TRAILING);
            final JTextField tfId = new JTextField(25);
            JLabel labName = new JLabel("Имя:", JLabel.TRAILING);
            final JTextField tfName = new JTextField(25);
            JLabel labOcupations = new JLabel("Должность:", JLabel.TRAILING);
            final JTextField tfOcupation = new JTextField(25);
            JLabel labSalary = new JLabel("Зарплата:", JLabel.TRAILING);
            final JTextField tfSalary = new JTextField(25);

            JButton btnSend = new JButton("Готово");

            p.add(labId);
            labId.setLabelFor(tfId);
            p.add(tfId);

            p.add(labName);
            labName.setLabelFor(tfName);
            p.add(tfName);

            p.add(labOcupations);
            labOcupations.setLabelFor(tfOcupation);
            p.add(tfOcupation);

            p.add(labSalary);
            labOcupations.setLabelFor(tfSalary);
            p.add(tfSalary);

            SpringUtilities.makeCompactGrid(p, numPairs, 2,6, 6,6, 6);

            final JFrame frameAddPerson = new JFrame("Добавить нового сотрудника");
            frameAddPerson.setResizable(true);

            p.setOpaque(true);
            frameAddPerson.getContentPane().add(p);
            frameAddPerson.getContentPane().add(btnSend, BorderLayout.SOUTH);

            frameAddPerson.pack();
            frameAddPerson.setLocationRelativeTo(null);
            frameAddPerson.setVisible(true);

            btnSend.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frameAddPerson.setVisible(false);
                    DriverJDBCInstance driverJDBCInstance = new DriverJDBCInstance();
                    driverJDBCInstance.sendNewData(tfId.getText(), tfName.getText(), tfOcupation.getText(), tfSalary.getText());
                }
            });
        }
    }

    private class btnShowDiagramListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new DiagramCreator();
        }
    }
}