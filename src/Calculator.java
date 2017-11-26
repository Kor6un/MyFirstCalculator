import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class Calculator extends JFrame {
    JTextArea textArea;
    JButton results;


    public Calculator () {
        setTitle ("Calc");
        setSize(520,200);

        setLayout(new BorderLayout());

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();

        JMenu file = new JMenu();
        file.setText("Файл");
        file.setActionCommand("menu file");

        JMenuItem openFile = new JMenuItem();
        openFile.setText("Открыть");
        openFile.setActionCommand("open file!");
        openFile.addActionListener(new MyActionListener());

        JMenuItem saveFile = new JMenuItem();
        saveFile.setText("Сохранить");
        saveFile.setActionCommand("save file!");
        saveFile.addActionListener(new MyActionListener());

        JMenuItem closeFile = new JMenuItem();
        closeFile.setText("Закрыть");
        closeFile.setActionCommand("close file!");
        closeFile.addActionListener(new MyActionListener());
        JSeparator separator = new JSeparator();

        JMenuItem exitFile = new JMenuItem();
        exitFile.setText("Выйти");
        exitFile.setActionCommand("exit file");
        exitFile.addActionListener(new MyActionListener());

        file.add(openFile);
        file.add(saveFile);
        file.add(closeFile);
        file.add(separator);
        file.add(exitFile);

        JMenu operation = new JMenu();
        operation.setText("Операции");
        operation.setActionCommand("menu operation");

        ButtonGroup buttonGroup = new ButtonGroup();

        JRadioButtonMenuItem addition = new JRadioButtonMenuItem("+", false);
       // addition.setText("Сложение");
        addition.setActionCommand("radio button +");
        addition.addActionListener(new MyActionListener());

        JRadioButtonMenuItem substraction = new JRadioButtonMenuItem("-",false);
       //  substraction.setText("Вычитание");
        substraction.setActionCommand("radio button -");
        substraction.addActionListener(new MyActionListener());

        JRadioButtonMenuItem multiplication = new JRadioButtonMenuItem("*", true);
       //  multiplication.setText("Умножение");
        multiplication.setActionCommand("radio button *");
        multiplication.addActionListener(new MyActionListener());

        JRadioButtonMenuItem division = new JRadioButtonMenuItem("/",false);
       // division.setText("Деление");
        division.setActionCommand("radio button /");
        division.addActionListener(new MyActionListener());

        buttonGroup.add(addition);
        buttonGroup.add(substraction);
        buttonGroup.add(multiplication);
        buttonGroup.add(division);

        operation.add(addition);
        operation.add(substraction);
        operation.add(multiplication);
        operation.add(division);

        menuBar.add(file);
        menuBar.add(operation);

        add(menuBar, BorderLayout.NORTH);

        JPanel eastPane = new JPanel();

        textArea = new JTextArea(2,40);
        textArea.setLineWrap(true);
        add(textArea);

        results = new JButton(" = ");
        add(results);
        results.addActionListener(new TextAreaButtomListener());
        eastPane.add(textArea);
        eastPane.add(results);

        add(eastPane,BorderLayout.SOUTH);
        addWindowListener(new MyWindowAdapter());

        setVisible(true);
    }

    public class MyWindowAdapter extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            e.getWindow().dispose();
        }
    }

    public class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getActionCommand());
        }
    }

    public class TextAreaButtomListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String temp;

            temp = textArea.getText();

            String[] num;
            num = temp.split("[-+/*]");

            char[] z;
            z = temp.toCharArray();

            List<Character> znak = new ArrayList<>();

            for (int i = 0; i < z.length; i++) {
               if (z[i] == '+' || z[i] == '-' || z[i] == '*' || z[i] == '/') {
                   znak.add(z[i]);
               }
            }

            double[] numbers = new double[num.length];

            for (int i = 0; i < num.length; i++) {
                numbers[i] = Double.parseDouble(num[i]);
            }

            double result = 0;
            result += numbers[0];

            for (int i = 0; i < znak.size(); i++) {
                char buf = znak.get(i);
                switch (buf) {
                    case '+':
                        result += numbers[i+1];
                        break;
                    case '-':
                        result -= numbers[i+1];
                        break;
                    case '*':
                        result *= numbers[i+1];
                        break;
                    case '/':
                        result /= numbers[i+1];
                        break;
                }
            }

            textArea.setText(temp + " = " + String.valueOf(result));

        }
    }


    public static void main(String[] args) {
        new Calculator();
    }
}

