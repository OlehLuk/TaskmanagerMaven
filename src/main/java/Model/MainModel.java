package Model;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.SortedMap;

public class MainModel {
    private TaskList taskList;
    File file = new File("tasks.txt");

    public void load() {
        taskList = new LinkedTaskList();
        try{
            TaskIO.readBinary(taskList,file);
        }
        catch(IOException e){

        }

    }

    public void add(String title, Date time, boolean active) {
        Task task = new Task(title, time);
        task.setActive(active);
        taskList.add(task);
    }

    public void add(String title, Date start, Date end, int interval, boolean active) {
        Task task = new Task(title, start, end, interval);
        task.setActive(active);
        taskList.add(task);
    }

    public void edit(Task edited, String title, Date time, boolean active) {
        Task task = new Task(title, time);
        task.setActive(active);
        this.taskList.remove(edited);
        taskList.add(task);
    }

    public void edit(Task edited, String title, Date start, Date end, int interval, boolean active) {
        Task task = new Task(title, start, end, interval);
        task.setActive(active);
        this.taskList.remove(edited);
        taskList.add(task);
    }

    public void remove(int index) {
        this.taskList.remove(this.taskList.getTask(index));
    }

    public SortedMap getCalendar(Date start, Date end) {
        return Tasks.calendar(this.taskList, start, end);
    }


}
