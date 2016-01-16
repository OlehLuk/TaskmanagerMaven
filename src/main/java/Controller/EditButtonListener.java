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
            return;
        }
        //read properties to change
        String taskTitle = view.getTaskTitle();
        if(taskTitle.length() == 0) {
            //show message about error ("can't change to empty title")
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
        }
        //if creating was successful will remove old and refresh view
        model.remove(indexSelected);
        view.showList(model.getList());
        this.view.clear();

    }
}
