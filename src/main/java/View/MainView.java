package View;


import Model.Task;
import Model.TaskList;

import java.util.Date;
import java.util.Set;
import java.util.SortedMap;

public class MainView {
    private FormTaskmanager appWindow = new FormTaskmanager();

    public void showList(TaskList list) {
        this.appWindow.setAllTasksList(list);
    }

    public void showCalendarTable(SortedMap<Date, Set<Task>> calendarMap) {
        this.appWindow.setCalendarTasksTable(calendarMap);
    }

    public void showGUI() {
        this.appWindow.setVisible(true);
    }

    public void clear() {

    }


}
