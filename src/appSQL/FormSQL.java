package appSQL;

import javax.swing.*;

public class FormSQL {
    private JPanel panel;
    private JTextField nameText;
    private JButton insertButton;
    private JButton selectButton;
    private JTextField surnameText;

    private JSpinner ageSpinner;

    DB db;


    public FormSQL() {



        db = new DB();

        selectButton.addActionListener(e -> {
            System.out.println(db.select());
            JOptionPane.showMessageDialog(null,new JTextArea(String.valueOf(db.select())));
        });

        ageSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        insertButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, db.insert(nameText.getText(), surnameText.getText(), (Integer) ageSpinner.getValue()));
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("FormSQL");
        frame.setContentPane(new FormSQL().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
