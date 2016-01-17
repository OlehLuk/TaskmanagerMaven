package Model;


import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Set;
import java.util.SortedMap;

public class MainModel {
    private static final Logger log = Logger.getLogger(MainModel.class);
    private TaskList taskList;
    File file = new File("src\\main\\resources\\tasks.txt");

    public void load() {
        taskList = new ArrayTaskList();
        try{
            TaskIO.readBinary(taskList,file);
        }
        catch(IOException e){
            //log
            log.warn("Saved data was not found. New data was created.");
            return;
        }
        log.info("Saved data was successfully loaded.");
    }

    public void save() throws IOException {
        try{
            TaskIO.writeBinary(taskList,file);
        }
        catch(IOException e){
            //log
            log.warn("Data was not saved.");
            throw new IOException("Something went wrong. IOException occured");
        }
        log.info("Saved data was successfully saved.");
    }



    public void add(String title, Date time, boolean active) throws IllegalArgumentException {
        Task task = new Task(title, time);
        task.setActive(active);
        taskList.add(task);
    }

    public void add(String title, Date start, Date end, int interval, boolean active) throws IllegalArgumentException {
        Task task = new Task(title, start, end, interval);
        task.setActive(active);
        taskList.add(task);
    }

    public void edit(Task edited, String title, Date time, boolean active) throws IllegalArgumentException {
        Task task = new Task(title, time);
        task.setActive(active);
        this.taskList.remove(edited);
        taskList.add(task);
    }

    public void edit(Task edited, String title, Date start, Date end, int interval, boolean active)throws IllegalArgumentException {
        Task task = new Task(title, start, end, interval);
        task.setActive(active);
        this.taskList.remove(edited);
        taskList.add(task);
    }

    public void remove(int index) {
        this.taskList.remove(this.taskList.getTask(index));
    }

    public SortedMap<Date, Set<Task>> getCalendar(Date start, Date end) {
        return Tasks.calendar(this.taskList, start, end);
    }


    public TaskList getList() {
        return this.taskList;
    }

    public Task getTask(int index) {
        if(index < 0 || index > taskList.size() - 1) {
            throw new IllegalArgumentException();
        }
        return this.taskList.getTask(index);
    }

    public int countTaskRepeatInterval(int[] taskRepeatInterval) {
        int resultInterval = taskRepeatInterval[0];
        resultInterval += taskRepeatInterval[1] * 60;
        resultInterval += taskRepeatInterval[2] * 60 * 60;
        resultInterval += taskRepeatInterval[3] * 60 * 60 * 24;
        return resultInterval;
    }
}
