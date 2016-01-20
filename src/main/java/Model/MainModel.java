package Model;


import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Set;
import java.util.SortedMap;

/**
 * MainModel is public class to represent Model part of MVC in task manager
 * Stores list of all tasks. Contains methods for editing this list.
 *
 * @author Oleh
 * @version 0.9
 * @since 1.8
 *
 */

public class MainModel {
    private static final Logger log = Logger.getLogger(MainModel.class);
    private TaskList taskList;
    File file = new File("src\\main\\resources\\tasks.txt");

    /**
     * Method that loads saved data
     */
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

    /**
     * Method that saves data before exit program
     * @throws IOException if data was not saved
     */
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

    /**
     * Adds nonrepeated Task to all tasks list.
     * @param title contains info about title of the Task should be added
     * @param time contains info about time of execution of the Task should be added
     * @param active contains info about activeness of the Task should be added
     * @throws IllegalArgumentException if parameters are invalid to create new task
     */
    public void add(String title, Date time, boolean active) throws IllegalArgumentException {
        Task task = new Task(title, time);
        task.setActive(active);
        taskList.add(task);
    }

    /**
     * Adds repeated Task to all tasks list.
     * @param title contains info about title of the Task should be added
     * @param start contains info about start time of execution of the Task should be added
     * @param end contains info about end time of execution of the Task should be added
     * @param interval contains info about time interval of execution of the Task should be added
     * @param active contains info about activeness of the Task should be added
     * @throws IllegalArgumentException if parameters are invalid to create new task
     */
    public void add(String title, Date start, Date end, int interval, boolean active) throws IllegalArgumentException {
        Task task = new Task(title, start, end, interval);
        task.setActive(active);
        taskList.add(task);
    }

    /**
     * Removes Task with given index from the all tasks list
     * @param index contains information about index of the Task
     *              that shuould be removed from all tasks list
     */
    public void remove(int index) {
        this.taskList.remove(this.taskList.getTask(index));
    }

    /**
     * Method that allows process stored TaskList to get calendar of Task's execution
     * @param start contains information about start of period
     * @param end contains information about end of period
     * @return object of interface SortedMap<Date, Set<Task>> that  contains calendar in format:
     * Key: Date - Value: set of all tasks executed at this time
     */
    public SortedMap<Date, Set<Task>> getCalendar(Date start, Date end) {
        return Tasks.calendar(this.taskList, start, end);
    }

    /**
     * Getter of stored list of all tasks
     * @return TaskList that is stored as list of all tasks in taskmanager
     */
    public TaskList getList() {
        return this.taskList;
    }

    /**
     * Getter of Task stored in list of all tasks at given index
     * @param index contains information about index needed task is stored at
     * @return Task object that is stored in list of all tasks at given index
     */
    public Task getTask(int index) {
        if(index < 0 || index > taskList.size() - 1) {
            throw new IllegalArgumentException();
        }
        return this.taskList.getTask(index);
    }

    /**
     * Counts interval of task's execution repetition in seconds from number
     * of days, hours, minutes, seconds that are given in array
     * @param taskRepeatInterval contains information about repetition interval.
     *                           0 element of array - number of days, 1 - hours, 2 - minutes, 3 - seconds
     * @return counted interval of task's execution repetition in seconds
     */
    public int countTaskRepeatInterval(int[] taskRepeatInterval) {
        int resultInterval = taskRepeatInterval[0];
        resultInterval += taskRepeatInterval[1] * 60;
        resultInterval += taskRepeatInterval[2] * 60 * 60;
        resultInterval += taskRepeatInterval[3] * 60 * 60 * 24;
        return resultInterval;
    }

    /**
     * Finds tasks that have passed at the moment
     * @return String that contains titles of all passed tasks
     */
    public String getPassed() {
        Date now = new Date();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < taskList.size(); i ++) {
            Task task = taskList.getTask(i);
            if(task.nextTimeAfter(now) == null || (task.isRepeated()
                    && task.nextTimeAfter(task.getStartTime()).before(now))) {

                builder.append(task.getTitle());
                if(i != taskList.size()) {
                    builder.append(", ");
                }
            }
        }
        return new String(builder);
    }

    /**
     * Finds tasks that should be executed at the moment
     * @return String that contains titles of all tasks that should be executed at the moment
     */
    public String getNow() {
        Date now = new Date();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.getTask(i);
            Date afterNow = t.nextTimeAfter(now);
            if(afterNow == null) {
                continue;
            }
            long diff = afterNow.getTime() - now.getTime();
            if(diff > 0 && diff <= 1000) {
                builder.append(t.getTitle());
                if(i != taskList.size() - 1) {
                    builder.append(", ");
                }
            }

        }
        return new String(builder);
    }
}
