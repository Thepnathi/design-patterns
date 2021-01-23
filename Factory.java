// It is a creational pattern which solves the problem of 
// creating product objects without specifying their concrete classes.

import javax.swing.*;

import jdk.jshell.Diag;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// The product objects of Buttons

interface Button {
    void render();
    void onClick();
}

class HtmlButton implements Button {
    public void render() {
        System.out.println("<button>Test Button</button>");
        onClick();
    }

    public void onClick() {
        System.out.println("Click! Button says - Hello!")
    }
}

class WindowsButton implements Button {
    JPanel panel = new JPanel();
    JFrame frame = new JFrame();
    JButton button;

    public void render() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("Hello World!");
        label.setOpaque(true);
        label.setBackground(new Color(235, 233, 126));
        label.setFont(new Font("Dialog", Font.BOLD, 44));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.getContentPane().add(panel);
        panel.add(label);
        onClick();
        panel.add(button);

        frame.setSize(320, 200);
        frame.setVisible(true);
        onClick();
    }

    public void onClick() {
        button = new JButton("Exit");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                System.exit(0);
            }
        });
    }
}

// The Factory Method


// The base creator
abstract class Dialog {
    public void renderWindow() {
        // ... some code/ business logic
        Button okButton = createButton();
        okButton.render();
    }

    // Subclasses will override this method in order to create specific button objects.
    public abstract Button createButton();
}

// The concrete creator 
class HtmlDialog extends Dialog {
    @Override 
    public Button createButton() {
        return new HtmlButton();
    }
}

class WindowsDialog extends Dialog {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}

public class Factory {
    private static Diaglog dialog;

    public static void main(String[] args) {
        configure();
        runBusinessLogic();
    }

    static void configure() {
        if (System.getProperty("os.name").equals("Windows 10")) {
            dialog = new WindowsDialog();
        } else {
            dialog = new HtmlDialog();
        }
    }

    static void runBusinessLogic() {
        dialog.renderWindow();
    }
}