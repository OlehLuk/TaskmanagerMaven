package Controller;

import Model.MainModel;
import View.MainView;
import org.apache.log4j.Logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class NewButtonListener implements ActionListener {
    MainView view;
    MainModel model;
    private static final Logger log = Logger.getLogger(NewButtonListener.class);

    public NewButtonListener(MainView view, MainModel model) {
        this.view = view;
        this.model = model;
        log.debug("NewButtonListener created.");
    }

    public void actionPerformed(ActionEvent e) {
        String taskTitle = view.getTaskTitle();
        if(taskTitle.length() == 0) {
            log.warn("Task was not created. Title can not be empty");
            view.showMessage("Task was not created. Title can not be empty. Please input title and try again.");
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
            log.warn("Task was not created. Illegal arguments.");
            view.showMessage("Task was not created. Invalid parameters were entered." +
                    " Please correct parameters and try again.");
        }
        log.info("Task was successfully created. List of all tasks was updated");
        view.showList(model.getList());
    }
}
