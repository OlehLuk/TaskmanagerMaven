package Controller;


import Model.MainModel;
import View.MainView;
import org.apache.log4j.Logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * EditButtonListener is a public class to represent listener of "edit" button
 *
 * @author Oleh
 * @version 0.9
 * @since 1.8
 */

public class EditButtonListener implements ActionListener {
    MainView view;
    MainModel model;
    private static final Logger log = Logger.getLogger(EditButtonListener.class);

    /**
     * Class constructor
     * @param view contains information about MainView object connected with listened button
     * @param model contains information about MainModel object connected with listened button
     */
    public EditButtonListener(MainView view, MainModel model) {
        this.view = view;
        this.model = model;
        log.debug("EditButtonListener created.");
    }

    /**
     * Method that implements interface ActionListener. Contains actions that are done when button is pushed
     * @param e contains information about button event
     */
    public void actionPerformed(ActionEvent e) {
        int indexSelected = view.getSelectedListItemIndex();
        if(indexSelected == -1) {
            log.warn("Task was not edited.  No Task was selected.");
            view.showMessage("Task was not edited. No Task was selected. Please select Task and try again.");
            return;
        }
        //read properties to change
        String taskTitle = view.getTaskTitle();
        if(taskTitle.length() == 0) {
            log.warn("Task was not edited.  Title can not be empty.");
            view.showMessage("Task was not edited. Title can not be empty. Please input title and try again.");
            return;
        }
        //read other properties
        Date startTime = view.getTaskStartDate();
        boolean taskActiveness = view.isTaskActive();
        //try to create task
        try{
            if(view.isRepeated()) {
                Date endTime = view.getTaskEndDate();
                int taskInterval = model.countTaskRepeatInterval(view.getTaskRepeatInterval());
                model.add(taskTitle, startTime, endTime, taskInterval, taskActiveness );
            }
            else{
                model.add(taskTitle, startTime, taskActiveness);
            }
        }
        catch (IllegalArgumentException exc) {
            log.warn("Task was not edited.  Invalid parameters were entered.");
            view.showMessage("Task was not edited. Invalid parameters were entered." +
                    " Please input correct parameters and try again.");
            return;
        }
        //if creating was successful will remove old and refresh view
        log.info("Task was successfully edited. List of all tasks was updated");
        model.remove(indexSelected);
        view.showList(model.getList());

    }
}
