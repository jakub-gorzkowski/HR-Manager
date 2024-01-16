package Interface.DeletePage;

import Database.Model.Database;
import Entities.Employee.EmployeeRole;
import Entities.Person.Employee;
import Queries.Delete.DeleteEntity;
import QueryHandlers.Filter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class DeleteEmployeePage extends JPanel implements ActionListener {
    private Database database;
    private JTextField id;
    private JTextField name;
    private JTextField surname;
    private JTextField email;
    private JTextField phoneNumber;
    private JComboBox<Object> role;
    private JTextField salary;

    public DeleteEmployeePage(Database database) {
        this.database = database;
        setLayout(new GridLayout(8, 2, 10, 10));
        setBackground(Color.WHITE);

        JLabel idLabel = new JLabel("ID");
        add(idLabel);
        id = new JTextField();
        add(id);

        JLabel nameLabel = new JLabel("Name");
        add(nameLabel);
        name = new JTextField();
        add(name);

        JLabel surnameLabel = new JLabel("Surname");
        add(surnameLabel);
        surname = new JTextField();
        add(surname);

        JLabel emailLabel = new JLabel("Email");
        add(emailLabel);
        email = new JTextField();
        add(email);

        JLabel phoneNumberLabel = new JLabel("Phone number");
        add(phoneNumberLabel);
        phoneNumber = new JTextField();
        add(phoneNumber);

        JLabel roleLabel = new JLabel("Role");
        add(roleLabel);
        role = new JComboBox<>(EmployeeRole.values());
        role.addItem("");
        role.setSelectedItem("");
        add(role);

        JLabel salaryLabel = new JLabel("Salary");
        add(salaryLabel);
        salary = new JTextField();
        add(salary);

        JButton submit = new JButton("Delete");
        add(submit);
        submit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Filter filter = new Filter();

        if (!id.getText().isEmpty()) {
            filter.addFilter("id", Integer.parseInt(id.getText()));
        }

        if (!name.getText().isEmpty()) {
            filter.addFilter("name", name.getText());
        }

        if (!surname.getText().isEmpty()) {
            filter.addFilter("surname", surname.getText());
        }

        if (!email.getText().isEmpty()) {
            filter.addFilter("email", email.getText());
        }

        if (!phoneNumber.getText().isEmpty()) {
            filter.addFilter("phone_number", phoneNumber.getText());
        }

        if (!role.getSelectedItem().equals("")) {
            filter.addFilter("role", (EmployeeRole) role.getSelectedItem());
        }

        if (!salary.getText().isEmpty()) {
            filter.addFilter("salary",  new BigDecimal(salary.getText()));
        }

        try {
            DeleteEntity.performAction(database.getConnection(), Employee.getTABLE_NAME(), filter);
            JOptionPane.showMessageDialog(this, "Successfully removed.");
        } catch (RuntimeException exception) {
            JOptionPane.showMessageDialog(this, "Error occurred.");
        }

        name.setText("");
        surname.setText("");
        email.setText("");
        phoneNumber.setText("");
        salary.setText("");
    }
}
