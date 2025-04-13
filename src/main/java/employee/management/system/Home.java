package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener{

    JButton view, add, update, remove;

    Home() {
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/IT.png"));
        Image i2 = i1.getImage().getScaledInstance(1120, 630, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1120, 630);
        add(image);

        JLabel heading = new JLabel("Employee Management System");
        heading.setForeground(Color.black);
        heading.setBounds(620, 20, 400, 40);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        image.add(heading);

        JButton stats = new JButton("View Statistics");
        stats.setBackground(new Color(173, 216, 230));
        stats.setBounds(650, 200, 150, 40);
        stats.addActionListener(e -> {
            setVisible(false);
            new Statistics();
        });
        image.add(stats);


        add = new JButton("Add Employee");
        add.setBackground(new Color(173, 216, 230));
        add.setBounds(650, 80, 150, 40);
        add.addActionListener(this);
        image.add(add);

        view = new JButton("View Employees");
        view.setBackground(new Color(173, 216, 230));
        view.setBounds(820, 80, 150, 40);
        view.addActionListener(this);
        image.add(view);

        update = new JButton("Update Employee");
        update.setBackground(new Color(173, 216, 230));
        update.setBounds(650, 140, 150, 40);
        update.addActionListener(this);
        image.add(update);

        remove = new JButton("Remove Employee");
        remove.setBackground(new Color(173, 216, 230));
        remove.setBounds(820, 140, 150, 40);
        remove.addActionListener(this);
        image.add(remove);




        setSize(1120, 630);
        setLocation(250, 100);
        setVisible(true);
    }



    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            setVisible(false);
            new AddEmployee();
        } else if (ae.getSource() == view) {
            setVisible(false);
            new ViewEmployee();
        } else if (ae.getSource() == update) {
            setVisible(false);
            new ViewEmployee();
        } else {
            setVisible(false);
            new RemoveEmployee();
        }
    }

    public static void main(String[] args) {
        new Home();
    }
}