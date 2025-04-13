package employee.management.system;

import java.awt.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.util.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;

public class AddEmployee extends JFrame implements ActionListener{
    Random ran = new Random();
    int number = ran.nextInt(999999);

    JTextField tfname, tfaddress, tfphone, tfemail, tfsalary,tfnationality;
    JDateChooser dcdob,dcdow;
    JComboBox cbeducation, cbposition;
    JLabel lblempId;
    JButton add, back;

    AddEmployee() {
        getContentPane().setBackground(new Color(173, 216, 230));
        setLayout(null);

        JLabel label = new JLabel("ADD EMPLOYEE INFORMATION");
        label.setBounds(320, 30, 500, 50);
        label.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(label);

        JLabel labelname = new JLabel("Name");
        labelname.setBounds(50, 150, 150, 30);
        labelname.setFont(new Font("serif", Font.BOLD, 20));
        add(labelname);
        tfname = new JTextField();
        tfname.setBounds(200, 150, 180, 30);
        add(tfname);

        JLabel labelnat = new JLabel("Nationality");
        labelnat.setBounds(500, 150, 150, 30);
        labelnat.setFont(new Font("serif", Font.BOLD, 20));
        add(labelnat);
        tfnationality = new JTextField();
        tfnationality.setBounds(650, 150, 180, 30);
        add(tfnationality);


        JLabel labeldob = new JLabel("Date of Birth");
        labeldob.setBounds(50, 200, 150, 30);
        labeldob.setFont(new Font("serif", Font.BOLD, 20));
        add(labeldob);
        dcdob = new JDateChooser();
        dcdob.setBounds(200, 200, 180, 30);
        add(dcdob);

        JLabel labeldow = new JLabel("Date of Work");
        labeldow.setBounds(500, 200, 150, 30);
        labeldow.setFont(new Font("serif", Font.BOLD, 20));
        add(labeldow);
        dcdow = new JDateChooser();
        dcdow.setBounds(650, 200, 180, 30);
        add(dcdow);

        JLabel labelsalary = new JLabel("Salary");
        labelsalary.setBounds(500, 350, 150, 30);
        labelsalary.setFont(new Font("serif", Font.BOLD, 20));
        add(labelsalary);
        tfsalary = new JTextField();
        tfsalary.setBounds(650, 350, 180, 30);
        add(tfsalary);

        JLabel labeladdress = new JLabel("Address");
        labeladdress.setBounds(50, 250, 150, 30);
        labeladdress.setFont(new Font("serif", Font.BOLD, 20));
        add(labeladdress);
        tfaddress = new JTextField();
        tfaddress.setBounds(200, 250, 180, 30);
        add(tfaddress);

        JLabel labelphone = new JLabel("Phone");
        labelphone.setBounds(50, 350, 150, 30);
        labelphone.setFont(new Font("serif", Font.BOLD, 20));
        add(labelphone);
        tfphone = new JTextField();
        tfphone.setBounds(200, 350, 180, 30);
        add(tfphone);

        JLabel labelemail = new JLabel("Email");
        labelemail.setBounds(50, 300, 150, 30);
        labelemail.setFont(new Font("serif", Font.BOLD, 20));
        add(labelemail);
        tfemail = new JTextField();
        tfemail.setBounds(200, 300, 180, 30);
        add(tfemail);


        JLabel labeleducation = new JLabel("Highest Education");
        labeleducation.setBounds(500, 300, 150, 30);
        labeleducation.setFont(new Font("serif", Font.BOLD, 20));
        add(labeleducation);
        String courses[] = {"Cử nhân CNTT", "Kỹ sư CNTT", "Thạc sĩ CNTT", "Tiến sĩ CNTT", "Không Có Trình Độ Chuyên Ngành"};
        cbeducation = new JComboBox(courses);
        cbeducation.setBackground(Color.WHITE);
        cbeducation.setBounds(650, 300, 180, 30);
        add(cbeducation);

        JLabel labelposition = new JLabel("Position");
        labelposition.setBounds(500, 250, 150, 30);
        labelposition.setFont(new Font("serif", Font.BOLD, 20));
        add(labelposition);
        String courses1[] = {"Giám Đốc", "Nhân viên phát triển phần mềm", "Nhân viên thiết kế giao diện", "Nhân viên hỗ trợ kỹ thuật", "Nhân viên kinh doanh","Bảo Vệ","Lao Công","Thực Tập Sinh"};
        cbposition = new JComboBox(courses1);
        cbposition.setBackground(Color.WHITE);
        cbposition.setBounds(650, 250, 180, 30);
        add(cbposition);



        JLabel labelempId = new JLabel("Employee id");
        labelempId.setBounds(50, 400, 150, 30);
        labelempId.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelempId);
        lblempId = new JLabel("" + number);
        lblempId.setBounds(200, 400, 150, 30);
        lblempId.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblempId);

        add = new JButton("Add");
        add.setBounds(250, 550, 150, 40);
        add.addActionListener(this);
        add.setBackground(new Color(0x4CAF50));
        add.setForeground(Color.WHITE);
        add(add);

        back = new JButton("Back");
        back.setBounds(450, 550, 150, 40);
        back.addActionListener(this);
        back.setBackground(new Color(0x4CAF50));
        back.setForeground(Color.WHITE);
        add(back);

        setSize(900, 700);
        setLocation(300, 50);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            String name = tfname.getText();
            String dob = "";
            String dow = "";
            String salary = tfsalary.getText();
            String address = tfaddress.getText();
            String nationality = tfnationality.getText();
            String phone = tfphone.getText();
            String email = tfemail.getText();
            String education = (String) cbeducation.getSelectedItem();
            String position = (String) cbposition.getSelectedItem();
            String empId = lblempId.getText();

            // Định dạng ngày
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            // Xử lý ngày
            if (dcdob.getDate() != null) {
                dob = sdf.format(dcdob.getDate());
            }
            if (dcdow.getDate() != null) {
                dow = sdf.format(dcdow.getDate());
            }

            try {
                Conn conn = new Conn();
                String query = "insert into employee values('" + name + "', '" + dob + "', '" + dow + "', '" + nationality +
                        "', '" + salary + "', '" + address + "', '" + phone + "', '" + email + "', '" + education +
                        "', '" + position + "', '" + empId + "')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Information successfully added");
                setVisible(false);
                new Home();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            new Home();
        }
    }

    public static void main(String[] args) {
        new AddEmployee();
    }
}
