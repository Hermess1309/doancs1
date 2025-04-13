package employee.management.system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ViewEmployee extends JFrame implements ActionListener {
    JTable table;
    JButton search, update, back;
    JLabel totalEmployeesLabel;
    JTextField searchByNameField;

    ViewEmployee() {
        getContentPane().setBackground(new Color(173, 216, 230));
        setLayout(null);
        JLabel searchname = new JLabel("Search by Name");
        searchname.setBounds(20, 20, 150, 20);
        add(searchname);

        searchByNameField = new JTextField();
        searchByNameField.setBounds(180, 20, 150, 20);
        add(searchByNameField);

        totalEmployeesLabel = new JLabel("Total Employees: ");
        totalEmployeesLabel.setBounds(20, 50, 200, 20);
        add(totalEmployeesLabel);
        updateTotalEmployees();
        table = new JTable();
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 900, 600);
        add(jsp);
        search = new JButton("Search");
        search.setBounds(20, 70, 80, 20);
        search.addActionListener(this);
        add(search);

        update = new JButton("Update");
        update.setBounds(120, 70, 80, 20);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBounds(220, 70, 80, 20);
        back.addActionListener(this);
        add(back);

        setSize(900, 700);
        setLocation(300, 100);
        setVisible(true);
        loadTableData("SELECT * FROM employee");
    }

    private void updateTotalEmployees() {
        try {
            Conn c = new Conn();
            String query = "SELECT COUNT(*) AS total FROM employee";
            ResultSet rs = c.s.executeQuery(query);
            if (rs.next()) {
                int totalEmployees = rs.getInt("total");
                totalEmployeesLabel.setText("Total Employees: " + totalEmployees);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            String nameQuery = searchByNameField.getText().trim();

            if (!nameQuery.isEmpty()) {
                String query = "SELECT * FROM employee WHERE name LIKE '%" + nameQuery + "%'";
                loadTableData(query);
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a name to search!");
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Home();
        } else if (ae.getSource() == update) {
            JOptionPane.showMessageDialog(null, "Please select an employee to update!");
        }
    }

    private void loadTableData(String query) {
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery(query);

            String[] columnNames = {
                    "Name", "Date of Birth", "Date of Work", "Nationality", "Salary",
                    "Address", "Phone", "Email", "Education", "Position", "Employee ID"
            };

            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            while (rs.next()) {
                String name = rs.getString("name");
                String dob = rs.getString("dob");
                String dow = rs.getString("dow");
                String nationality = rs.getString("nationality");
                String salary = rs.getString("salary");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String education = rs.getString("education");
                String position = rs.getString("position");
                String empId = rs.getString("empId");

                model.addRow(new Object[]{name, dob, dow, nationality, salary, address, phone, email, education, position, empId});
            }

            table.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ViewEmployee();
    }
}
