import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DiagramCreator {
    DiagramCreator(){
        JPanel panel = new JPanel(new FlowLayout());
        JButton btn1 = new JButton("По именам");
        JButton btn2 = new JButton("По специальностям");
        JButton btn3 = new JButton("По зарплате");

        panel.add(btn1);
        panel.add(btn2);
        panel.add(btn3);

        JFrame frame = new JFrame();
        frame.getContentPane().add(panel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> data_names;
                DriverJDBCInstance driverJDBCInstance = new DriverJDBCInstance();
                data_names = driverJDBCInstance.getDataNameslist();

                Map<String, String> mapNames = new HashMap<String, String>();

                for (int i=0; i<data_names.size();){
                    String name = data_names.get(i);
                    int count=0;
                    while (data_names.contains(name)) {
                        count++;
                        int num = data_names.indexOf(name);
                        data_names.remove(num);
                    }
                    mapNames.put(name, String.valueOf(count));
                }

                PieChart pieChart = new PieChart(mapNames);
                pieChart.makePieChart("Имена сотрудников");
            }
        });

        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> data_specialties = new ArrayList<String>();
                DriverJDBCInstance driverJDBCInstance = new DriverJDBCInstance();
                data_specialties = driverJDBCInstance.getDataSpecialtieslist();

                Map<String, String> mapSpecialty = new HashMap<String, String>();

                for (int i=0; i<data_specialties.size();){
                    String specialty = data_specialties.get(i);
                    int count=0;
                    while (data_specialties.contains(specialty)) {
                        count++;
                        int num = data_specialties.indexOf(specialty);
                        data_specialties.remove(num);
                    }
                    mapSpecialty.put(specialty, String.valueOf(count));
                }

                PieChart pieChart = new PieChart(mapSpecialty);
                pieChart.makePieChart("Специальности сотрудников");
            }
        });

        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> data_salaries = new ArrayList<>();
                DriverJDBCInstance driverJDBCInstance = new DriverJDBCInstance();
                data_salaries = driverJDBCInstance.getDataSalarieslist();

                Map<String, String> mapSalary = new HashMap<String, String>();

                for (int i=0; i<data_salaries.size();){
                    String salary = data_salaries.get(i);
                    int count=0;
                    while (data_salaries.contains(salary)) {
                        count++;
                        int num = data_salaries.indexOf(salary);
                        data_salaries.remove(num);
                    }
                    mapSalary.put(salary, String.valueOf(count));
                }

                PieChart pieChart = new PieChart(mapSalary);
                pieChart.makePieChart("Зарплата сотрудников");
            }
        });
    }
}