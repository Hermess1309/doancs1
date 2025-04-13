package employee.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateEmployee extends JFrame implements ActionListener {
    JTextField tfaddress, tfphone, tfemail, tfsalary;
    JLabel lblempId, lblname, lbldob, lbldow, lblnationality;
    JButton updateBtn, backBtn;
    String empId;
    JComboBox cbposition,cbeducation;

    UpdateEmployee(String empId) {
        this.empId = empId;
        getContentPane().setBackground(new Color(173, 216, 230));
        setLayout(null);
        JLabel heading = new JLabel("Update Employee Details");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);

        JLabel labelname = new JLabel("Name");
        labelname.setBounds(50, 150, 150, 30);
        labelname.setFont(new Font("serif", Font.BOLD, 20));
        add(labelname);
        lblname = new JLabel();
        lblname.setBounds(200, 150, 150, 30);
        add(lblname);

        JLabel labeldob = new JLabel("Date of Birth");
        labeldob.setBounds(450, 150, 150, 30);
        labeldob.setFont(new Font("serif", Font.BOLD, 20));
        add(labeldob);
        lbldob = new JLabel();
        lbldob.setBounds(600, 150, 150, 30);
        add(lbldob);

        JLabel labeldow = new JLabel("Date of Work");
        labeldow.setBounds(50, 200, 150, 30);
        labeldow.setFont(new Font("serif", Font.BOLD, 20));
        add(labeldow);
        lbldow = new JLabel();
        lbldow.setBounds(200, 200, 150, 30);
        add(lbldow);

        JLabel labelnationality = new JLabel("Nationality");
        labelnationality.setBounds(450, 200, 150, 30);
        labelnationality.setFont(new Font("serif", Font.BOLD, 20));
        add(labelnationality);
        lblnationality = new JLabel();
        lblnationality.setBounds(600, 200, 150, 30);
        add(lblnationality);

        JLabel labelsalary = new JLabel("Salary");
        labelsalary.setBounds(50, 250, 150, 30);
        labelsalary.setFont(new Font("serif", Font.BOLD, 20));
        add(labelsalary);
        tfsalary = new JTextField();
        tfsalary.setBounds(200, 250, 150, 30);
        add(tfsalary);

        JLabel labeladdress = new JLabel("Address");
        labeladdress.setBounds(450, 250, 150, 30);
        labeladdress.setFont(new Font("serif", Font.BOLD, 20));
        add(labeladdress);
        tfaddress = new JTextField();
        tfaddress.setBounds(600, 250, 150, 30);
        add(tfaddress);

        JLabel labelphone = new JLabel("Phone");
        labelphone.setBounds(50, 300, 150, 30);
        labelphone.setFont(new Font("serif", Font.BOLD, 20));
        add(labelphone);
        tfphone = new JTextField();
        tfphone.setBounds(200, 300, 150, 30);
        add(tfphone);

        JLabel labelemail = new JLabel("Email");
        labelemail.setBounds(450, 300, 150, 30);
        labelemail.setFont(new Font("serif", Font.BOLD, 20));
        add(labelemail);
        tfemail = new JTextField();
        tfemail.setBounds(600, 300, 150, 30);
        add(tfemail);

        JLabel labeleducation = new JLabel("Highest Education");
        labeleducation.setBounds(50, 350, 150, 30);
        labeleducation.setFont(new Font("serif", Font.BOLD, 20));
        add(labeleducation);
        String courses[] = {"Cử nhân CNTT", "Kỹ sư CNTT", "Thạc sĩ CNTT", "Tiến sĩ CNTT", "Không Có Trình Độ Chuyên Ngành"};
        cbeducation = new JComboBox(courses);
        cbeducation.setBackground(Color.WHITE);
        cbeducation.setBounds(200, 350, 150, 30);
        add(cbeducation);

        JLabel labelposition = new JLabel("Position");
        labelposition.setBounds(450, 350, 200, 20);
        labelposition.setFont(new Font("serif", Font.BOLD, 20));
        add(labelposition);
        String courses1[] = {"Giám Đốc", "Nhân viên phát triển phần mềm", "Nhân viên thiết kế giao diện", "Nhân viên hỗ trợ kỹ thuật", "Nhân viên kinh doanh","Bảo Vệ","Lao Công","Thực Tập Sinh"};
        cbposition = new JComboBox(courses1);
        cbposition.setBackground(Color.WHITE);
        cbposition.setBounds(600, 350, 150, 30);
        add(cbposition);

        JLabel labelempId = new JLabel("Employee ID");
        labelempId.setBounds(50, 400, 150, 30);
        labelempId.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelempId);
        lblempId = new JLabel();
        lblempId.setBounds(200, 400, 150, 30);
        lblempId.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblempId);

        try {
            Conn c = new Conn();
            String query = "SELECT * FROM employee WHERE empId = ?";
            PreparedStatement ps = c.c.prepareStatement(query);
            ps.setString(1, empId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                lblname.setText(rs.getString("name"));
                lbldob.setText(rs.getString("dob"));
                lbldow.setText(rs.getString("dow"));
                lblnationality.setText(rs.getString("nationality"));
                tfsalary.setText(rs.getString("salary"));
                tfaddress.setText(rs.getString("address"));
                tfphone.setText(rs.getString("phone"));
                tfemail.setText(rs.getString("email"));
                cbeducation.setSelectedItem(rs.getString("education"));
                cbposition.setSelectedItem(rs.getString("position"));
                lblempId.setText(rs.getString("empId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        updateBtn = new JButton("Update Details");
        updateBtn.setBounds(250, 550, 150, 40);
        updateBtn.addActionListener(this);
        updateBtn.setBackground(Color.BLACK);
        updateBtn.setForeground(Color.WHITE);
        add(updateBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(450, 550, 150, 40);
        backBtn.addActionListener(this);
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);
        add(backBtn);

        setSize(900, 700);
        setLocation(300, 50);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == updateBtn) {
            String salary = tfsalary.getText();
            String address = tfaddress.getText();
            String phone = tfphone.getText();
            String email = tfemail.getText();
            String education = cbeducation.getSelectedItem().toString();
            String position = cbposition.getSelectedItem().toString();

            if (salary.isEmpty() || address.isEmpty() || phone.isEmpty() || email.isEmpty() || education.isEmpty() || position.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all fields");
                return;
            }

            try {
                Conn conn = new Conn();
                String query = "UPDATE employee SET salary = ?, address = ?, phone = ?, email = ?, education = ?, position = ? WHERE empId = ?";
                PreparedStatement ps = conn.c.prepareStatement(query);
                ps.setString(1, salary);
                ps.setString(2, address);
                ps.setString(3, phone);
                ps.setString(4, email);
                ps.setString(5, education);
                ps.setString(6, position);
                ps.setString(7, empId);

                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Details updated successfully");
                setVisible(false);
                new Home();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error updating details: " + e.getMessage());
            }
        } else if (ae.getSource() == backBtn) {
            setVisible(false);
            new Home();
        }
    }

    public static void main(String[] args) {
        new UpdateEmployee("");
    }
}
