package employee.management.system;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class Statistics extends JFrame {
    public Statistics() {
        setTitle("Thống kê nhân viên");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        JPanel chartPanel = new JPanel(new GridLayout(1, 2));

        DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
        HashMap<String, Integer> departmentData = getData("SELECT position, COUNT(*) FROM employee GROUP BY position");
        for (String key : departmentData.keySet()) {
            barDataset.addValue(departmentData.get(key), "Nhân viên", key);
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Nhân viên theo phòng ban", "Phòng ban", "Số lượng", barDataset);
        CategoryPlot plot = (CategoryPlot) barChart.getPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setItemMargin(0.1);
        CategoryAxis xAxis = plot.getDomainAxis();
        xAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);
        xAxis.setMaximumCategoryLabelLines(2);

        ChartPanel barPanel = new ChartPanel(barChart);
        barPanel.setPreferredSize(new Dimension(600, 500));

        DefaultPieDataset pieDataset = new DefaultPieDataset();
        HashMap<String, Integer> educationData = getData("SELECT education, COUNT(*) FROM employee GROUP BY education");
        for (String key : educationData.keySet()) {
            pieDataset.setValue(key, educationData.get(key));
        }

        JFreeChart pieChart = ChartFactory.createPieChart("Phân bố trình độ học vấn", pieDataset, true, true, false);
        PiePlot piePlot = (PiePlot) pieChart.getPlot();
        piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1} ({2})"));

        ChartPanel piePanel = new ChartPanel(pieChart);
        piePanel.setPreferredSize(new Dimension(600, 500));

        chartPanel.add(barPanel);
        chartPanel.add(piePanel);

        JButton backButton = new JButton("Quay lại");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Home();
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);

        add(chartPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private HashMap<String, Integer> getData(String query) {
        HashMap<String, Integer> dataMap = new HashMap<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeeManagementSystem", "root", "Dangcongtung24022006");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                dataMap.put(rs.getString(1), rs.getInt(2));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataMap;
    }

    public static void main(String[] args) {
        new Statistics();
    }
}
