package Controller;


import Model.MainModel;
import View.MainView;
import org.apache.log4j.Logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * DeleteButtonListener is a public class to represent listener of "delete" button
 *
 * @author Oleh
 * @version 0.9
 * @since 1.8
 */

public class DeleteButtonListener implements ActionListener {
    MainView view;
    MainModel model;
    private static final Logger log = Logger.getLogger(DeleteButtonListener.class);

    /**
     * Class constructor
     * @param view contains information about MainView object connected with listened button
     * @param model contains information about MainModel object connected with listened button
     */
    public DeleteButtonListener(MainView view, MainModel model) {
        this.view = view;
        this.model = model;
        log.debug("DeleteButtonListener created.");
    }

    /**
     * Method that implements interface ActionListener. Contains actions that are done when button is pushed
     * @param e contains information about button event
     */
    public void actionPerformed(ActionEvent e) {
        int indexSelected = view.getSelectedListItemIndex();
        if(indexSelected == -1) {
            log.warn("Task was not deleted.  No Task was selected.");
            view.showMessage("Task was not deleted. No Task was selected. Please select Task and try again.");
            return;
        }
        log.info("Task was successfully deleted. List of all tasks was updated");
        model.remove(indexSelected);
        view.showList(model.getList());
    }
}
