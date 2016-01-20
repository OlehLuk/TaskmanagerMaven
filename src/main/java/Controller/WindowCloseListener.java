package Controller;

import Model.MainModel;
import View.MainView;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

/**
 * WindowCloseListener is a public class to represent listener of program's windows events
 *
 * @author Oleh
 * @version 0.9
 * @since 1.8
 */

public class WindowCloseListener implements WindowListener {
    MainView view;
    MainModel model;
    private static final Logger log = Logger.getLogger(WindowListener.class);

    /**
     * Class constructor
     * @param view contains information about MainView object connected with listened window
     * @param model contains information about MainModel object connected with listened window
     */
    public WindowCloseListener(MainView view, MainModel model) {
        this.view = view;
        this.model = model;
        log.debug("WindowCloseListener created.");
    }

    /**
     * Method that implements interface WindowListener.
     * Contains actions that are done when the program's window is opened.
     * (empty)
     * @param e contains information about windows event
     */
    public void windowOpened(WindowEvent e) {

    }

    /**
     * Method that implements interface WindowListener.
     * Contains actions that are done when user tries to close the program's window.
     * @param e contains information about windows event
     */
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

    /**
     * Method that implements interface WindowListener.
     * Contains actions that are done when the program's window is closed.
     * (empty)
     * @param e contains information about windows event
     */
    public void windowClosed(WindowEvent e) {

    }

    /**
     * Method that implements interface WindowListener.
     * Contains actions that are done when the program's window is iconified.
     * (empty)
     * @param e contains information about windows event
     */
    public void windowIconified(WindowEvent e) {

    }

    /**
     * Method that implements interface WindowListener.
     * Contains actions that are done when the program's window is deiconified.
     * (empty)
     * @param e contains information about windows event
     */
    public void windowDeiconified(WindowEvent e) {

    }

    /**
     * Method that implements interface WindowListener.
     * Contains actions that are done when the program's window is activated.
     * (empty)
     * @param e contains information about windows event
     */
    public void windowActivated(WindowEvent e) {

    }

    /**
     * Method that implements interface WindowListener.
     * Contains actions that are done when the program's window is deactivated.
     * (empty)
     * @param e contains information about windows event
     */
    public void windowDeactivated(WindowEvent e) {

    }
}
