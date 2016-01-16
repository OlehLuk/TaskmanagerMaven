package Controller;


import Model.MainModel;
import View.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class EditButtonListener implements ActionListener {
    MainView view;
    MainModel model;

    public EditButtonListener(MainView view, MainModel model) {
        this.view = view;
        this.model = model;
    }

    public void actionPerformed(ActionEvent e) {
        int indexSelected = view.getSelectedListItemIndex();
        if(indexSelected == -1) {
            //show message + log that item is not selected
            view.showMessage("Task was not edited. No Task was selected. Please select Task and try again.");
            return;
        }
        //read properties to change
        String taskTitle = view.getTaskTitle();
        if(taskTitle.length() == 0) {
            //show message about error ("can't change to empty title")
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
            //log
            //show message
            view.showMessage("Task was not edited. Invalid parameters were entered." +
                    " Please input correct parameters and try again.");
            return;
        }
        //if creating was successful will remove old and refresh view
        model.remove(indexSelected);
        view.showList(model.getList());
        this.view.clear();

    }
}
