package Controller;

import Model.MainModel;
import View.MainView;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

public class WindowCloseListener implements WindowListener {
    MainView view;
    MainModel model;
    private static final Logger log = Logger.getLogger(WindowListener.class);

    public WindowCloseListener(MainView view, MainModel model) {
        this.view = view;
        this.model = model;
        log.debug("WindowCloseListener created.");
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
            log.info("Program was ended.");
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
