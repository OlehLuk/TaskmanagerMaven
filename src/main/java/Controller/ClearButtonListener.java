package Controller;

import View.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ClearButtonListener is a public class to represent listener of "clear" button
 *
 * @author Oleh
 * @version 0.9
 * @since 1.8
 */

public class ClearButtonListener implements ActionListener {
    MainView view;

    /**
     * Class constructor.
     * @param view contains information about MainView object connected with listened button
     */
    public ClearButtonListener(MainView view) {
        this.view = view;
    }

    /**
     * Method that implements interface ActionListener. Contains actions that are done when button is pushed
     * @param e contains information about button event
     */
    public void actionPerformed(ActionEvent e) {
        this.view.clear();
    }
}
