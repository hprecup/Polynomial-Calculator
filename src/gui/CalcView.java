package gui;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CalcView extends JFrame{
    private JTextField pol1;
    private JTextField pol2;
    private JTextField rez;
    private JButton addition = new JButton("+");
    private JButton substraction = new JButton("-");
    private JButton multiplication = new JButton("x");
    private JButton reset = new JButton("reset");
    private JButton derivation = new JButton("derivate");
    private JButton integration = new JButton("integrate");
    private JButton division = new JButton("/");

    public CalcView(){
        this.setBounds(100, 100, 550, 200);
        //the main panel
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        //panel with polynomials
        JPanel panelUp = new JPanel();
        panelUp.setLayout(new BoxLayout(panelUp, BoxLayout.X_AXIS));
        //panels for labels and text fields
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panelUp.add(panel1);
        panelUp.add(Box.createRigidArea(new Dimension(20,0)));
        panelUp.add(panel2);

        JPanel panelDown = new JPanel();
        panelDown.setLayout(new BoxLayout(panelDown, BoxLayout.X_AXIS));
        //panelDown.setLayout(new GridLayout(2,3));
        // adding the panels
        content.add(Box.createRigidArea(new Dimension(0,20)));
        content.add(panelUp);
        content.add(Box.createRigidArea(new Dimension(0,20)));
        content.add(panelDown);
        this.setTitle("Polynomial Calculator");
        this.setContentPane(content);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //adding the buttons and text to panels
        JLabel l1 = new JLabel("Polynom 1: ");
        l1.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panel1.add(l1);
        panel1.add(Box.createRigidArea(new Dimension(0,20)));
        JLabel l2 = new JLabel("Polynom 2: ");
        l2.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panel1.add(l2);
        panel1.add(Box.createRigidArea(new Dimension(0,20)));
        JLabel l3 = new JLabel("Result: ");
        l3.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panel1.add(l3);

        pol1 = new JTextField("0");
        pol1.setMaximumSize(new Dimension(300,20));
        pol2 = new JTextField("0");
        pol2.setMaximumSize(new Dimension(300,20));
        rez = new JTextField("");
        rez.setEditable(false);
        rez.setMaximumSize(new Dimension(300,20));
        panel2.add(pol1);
        panel2.add(Box.createRigidArea(new Dimension(0,15)));
        panel2.add(pol2);
        panel2.add(Box.createRigidArea(new Dimension(0,15)));
        panel2.add(rez);

        panelDown.add(addition);
        panelDown.add(Box.createRigidArea(new Dimension(20,0)));
        panelDown.add(substraction);
        panelDown.add(Box.createRigidArea(new Dimension(20,0)));
        panelDown.add(multiplication);
        panelDown.add(Box.createRigidArea(new Dimension(20,0)));
        panelDown.add(division);
        panelDown.add(Box.createRigidArea(new Dimension(20,0)));
        panelDown.add(derivation);
        panelDown.add(Box.createRigidArea(new Dimension(20,0)));
        panelDown.add(integration);
        panelDown.add(Box.createRigidArea(new Dimension(20,0)));
        panelDown.add(reset);
    }

    void reset(){
        pol1.setText("0");
        pol2.setText("0");
    }

    String read1(){
        return pol1.getText();
    }

    String read2(){
        return pol2.getText();
    }

    void writePolynom(String result){
        rez.setText(result);
    }

    void addResetListener(ActionListener e){
        reset.addActionListener(e);
    }

    void addAdditionListener(ActionListener e){
        addition.addActionListener(e);
    }

    void addSubstractionActionListener(ActionListener e){
        substraction.addActionListener(e);
    }

    void addMultiplicationListener(ActionListener e){
        multiplication.addActionListener(e);
    }

    void addDivisionListener(ActionListener e){
        division.addActionListener(e);
    }

    void addDerivationListener(ActionListener e){
        derivation.addActionListener(e);
    }

    void addIntegrationListener(ActionListener e){
        integration.addActionListener(e);
    }

}
