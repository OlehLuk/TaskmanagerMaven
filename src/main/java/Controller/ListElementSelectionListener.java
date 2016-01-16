package Controller;

import Model.MainModel;
import Model.Task;
import View.MainView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListElementSelectionListener implements ListSelectionListener{
    MainView view;
    MainModel model;

    public ListElementSelectionListener(MainView view, MainModel model) {
        this.view = view;
        this.model = model;
    }

    public void valueChanged(ListSelectionEvent e) {
        int index = view.getSelectedListItemIndex();
        if(index > -1) {
            Task selectedTask = model.getTask(index);
            view.updateFields(selectedTask);
        }
    }
}
