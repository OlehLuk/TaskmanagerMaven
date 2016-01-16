package Controller;

import Model.MainModel;
import View.MainView;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

public class WindowCloseListener implements WindowListener {
    MainView view;
    MainModel model;

    public WindowCloseListener(MainView view, MainModel model) {
        this.view = view;
        this.model = model;
    }

    public void windowOpened(WindowEvent e) {

    }

    public void windowClosing(WindowEvent e) {
        Object[] options = { "Yes", "Noo!" };
        int n = JOptionPane.showOptionDialog(e.getWindow(), "Are you sure want to close?",
                        "Confirm", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options,
                        options[1]);
        if (n == 0) {
            try {
                model.save();
            } catch (IOException e1) {
                view.showMessage("Data was not saved");
                int m = JOptionPane.showConfirmDialog(e.getWindow(), "Do you really want to close window?" ,"Achtung!",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(m == 1) {
                    return;
                }
            }
            e.getWindow().setVisible(false);
            System.exit(0);
        }
    }

    public void windowClosed(WindowEvent e) {

    }

    public void windowIconified(WindowEvent e) {

    }

    public void windowDeiconified(WindowEvent e) {

    }

    public void windowActivated(WindowEvent e) {

    }

    public void windowDeactivated(WindowEvent e) {

    }
}
