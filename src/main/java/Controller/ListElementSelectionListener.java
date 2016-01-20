package Controller;

import Model.MainModel;
import Model.Task;
import View.MainView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * ListElementSelectionListener is a public class to represent listener of selection of list's element
 *
 * @author Oleh
 * @version 0.9
 * @since 1.8
 */

public class ListElementSelectionListener implements ListSelectionListener{
    MainView view;
    MainModel model;

    /**
     * Class constructor
     * @param view contains information about MainView object connected with listened list
     * @param model contains information about MainModel object connected with listened list
     */
    public ListElementSelectionListener(MainView view, MainModel model) {
        this.view = view;
        this.model = model;
    }

    /**
     * Method that implements interface ListSelectionListener.
     * Contains actions that are done when list's element is selected
     * @param e contains information about list' element selection event
     */
    public void valueChanged(ListSelectionEvent e) {
        int index = view.getSelectedListItemIndex();
        if(index > -1) {
            Task selectedTask = model.getTask(index);
            view.updateFields(selectedTask);
        }
    }
}
