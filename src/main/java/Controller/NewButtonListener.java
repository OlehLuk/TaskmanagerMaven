package Controller;

import Model.MainModel;
import View.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class NewButtonListener implements ActionListener {
    MainView view;
    MainModel model;

    public NewButtonListener(MainView view, MainModel model) {
        this.view = view;
        this.model = model;
    }

    public void actionPerformed(ActionEvent e) {
        String taskTitle = view.getTaskTitle();
        if(taskTitle.length() == 0) {
            //show message about error
            return;
        }
        Date startTime = view.getTaskStartDate();
        boolean taskActiveness = view.isTaskActive();
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

        view.showList(model.getList());
        this.view.clear();
    }
}
