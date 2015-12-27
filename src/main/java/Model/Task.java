package Model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Task is public class to represent tasks in task manager
 *
 * @author Olehe
 * @version 0.9
 * @since 1.8
 *
 */

public class Task implements Cloneable, Serializable{

    private Date startTime;
    private Date endTime;
    private long timeInterval;
    private String title;
    private boolean active;
    private boolean repeat;

    /**
     * Class constructor for an unrepeatable task
     * @param title contains information about title of the Task
     * @param time contains information about time of Task's execution
     * @throws IllegalArgumentException
     */

    //constructors
    public Task(String title, Date time) throws IllegalArgumentException {
        if(time == null ) {
            throw new IllegalArgumentException("Time can not be null");
        }
        this.title = title;
        this.startTime = this.endTime = time;
        this.active = false;
        this.repeat = false;
        this.timeInterval = 0;
    }

    /**
     * Class constructor for an repeatable task
     * @param title contains information about title of the Task
     * @param start contains information about starttime of Task's execution
     * @param end contains information about endtime of Task's execution
     * @param interval contains information about time interval of Task's execution in seconds!(should be converted in
     *                 milliseconds)
     * @throws IllegalArgumentException
     */
    public Task(String title, Date start, Date end, int interval) throws IllegalArgumentException {
        if(start == null || end == null) {
            throw new IllegalArgumentException("Start and/or end time can not be null");
        }
        if(interval <= 0) {
            throw new IllegalArgumentException("Time Interval of repeatable Task execution should be greater than zero");
        }
        this.title = title;
        this.startTime = start;
        this.endTime = end;
        this.active = false;
        this.repeat = true;
        this.timeInterval = (long) interval * 1000;
    }

    /**
     * Returns title of the Task
     * @return Task's title
     */

    //get and set title of the task
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets title of the Task
     * @param title contains information about title of the Task
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns value of active field of the Task
     * @return wheather Task is active
     */
    //check and set if it is active
    public boolean isActive(){
        return this.active;
    }

    /**
     * Sets value of active field of the Task
     * @param active contains information wheather Task is active
     */
    public void setActive(boolean active){
        this.active = active;
    }

    /**
     * Returns value of repeat field of the Task
     * @return wheather Task is repeated
     */
    //check if it is repeated
    public boolean isRepeated(){
        return this.repeat;
    }

    /**
     * Sets time of execution time of an unrepeatable Task
     * @param time contains information about execution time of an unrepeatable Task
     */
    //get and set time options for unrepeatable task
    public void setTime(Date time) {
        if(time == null) {
            throw new IllegalArgumentException("Time can not be null");
        }
        this.startTime = this.endTime = time;
        this.timeInterval = 0;
        this.repeat = false;

    }

    /**
     * Returns time of execution time of an unrepeatable Task
     * @return time of execution time of an unrepeatable Task
     */
    public Date getTime() {
        return this.startTime;
    }

    /**
     * Returns starttime of execution of a repeatable Task
     * @return starttime of execution of a repeatable Task
     */
    //get and set time options for repeatable task
    public Date getStartTime() {
        return this.startTime;
    }

    /**
     * Returns endtime of execution of a repeatable Task
     * @return endtime of execution of a repeatable Task
     */
    public Date getEndTime() {
        return this.endTime;
    }

    /**
     * Returns interval of execution of a repeatable Task
     * @return interval of execution of a repeatable Task
     */
    public long getRepeatInterval() {
        return this.timeInterval;
    }

    /**
     * Sets starttime, endtime and interval of execution of a repeatable Task
     * @param start contains information about execution starttime of a repeatable Task
     * @param end contains information about execution endtime of a repeatable Task
     * @param interval contains information about execution interval of a repeatable Task
     */
    public void setTime(Date start, Date end, int interval) {
        if(start == null || end == null) {
            throw new IllegalArgumentException("Start and/or end time can not be null");
        }
        if(interval <= 0) {
            throw new IllegalArgumentException("Time Interval of repeatable Task's execution should be greater than zero");
        }
        this.startTime = start;
        this.endTime = end;
        this.timeInterval = (long)interval * 1000;
        this.repeat = true;
    }

    /**
     * Returns time of the first execution after time given in current parameter -1 if it wasn't found
     * @param current contains information about time we are looking the next execution after
     * @return time of the first execution after current or -1 if it wasn't found
     */
    //
    public Date nextTimeAfter(Date current) {
        if(current == null || !this.active || this.endTime.before( current) || this.endTime.equals(current)) {
            return null;
        }
        if(!this.repeat) {
            return this.startTime;
        }
        for(Date i = (Date) this.startTime.clone(); i.before(this.endTime) || i.equals(this.endTime); i.setTime(i.getTime()
                + this.timeInterval)) {
            if(i.after( current)) {
                return i;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(startTime, task.startTime) &&
                Objects.equals(endTime, task.endTime) &&
                Objects.equals(timeInterval, task.timeInterval) &&
                Objects.equals(active, task.active) &&
                Objects.equals(repeat, task.repeat) &&
                Objects.equals(title, task.title);
    }

    @Override
    public int hashCode() {
        //return Objects.hash(startTime, endTime, timeInterval, title, active, repeat);
        String string = "{" +
                " title='" + title + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", timeInterval=" + timeInterval +
                ", active=" + active +
                ", repeat=" + repeat +
                '}';
        return string.hashCode();
    }

    @Override
    public String toString() {
        return "Task @" + this.hashCode() + " {" +
                " title='" + title + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", timeInterval=" + timeInterval +
                ", active=" + active +
                ", repeat=" + repeat +
                '}';
    }

    @Override
    public Task clone() throws CloneNotSupportedException {
        Task task = (Task) super.clone();
        task.title = this.title;
        task.startTime = new Date( this.startTime.getTime()) ;
        task.endTime = new Date(this.endTime.getTime()) ;
        task.timeInterval = this.timeInterval ;
        task.repeat = this.repeat ;
        task.active = this.active ;
        return task;
    }
}
