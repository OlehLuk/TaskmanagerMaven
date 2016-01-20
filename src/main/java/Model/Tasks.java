package Model;


import java.util.*;

/**
 * Tasks is public class that contains realization of calendar's logic.
 * Static methods allows process TaskLists to get calendar.
 *
 * @author Oleh
 * @version 0.9
 * @since 1.8
 */

public class Tasks {

    /**
     * Static methods that allows process TaskLists to get Task executed at least once in given period.
     * @param tasks contains information about processed TaskList
     * @param start contains information about start of period
     * @param end contains information about end of period
     * @return object implementing interface Iterable<Task> that contains all Tasks
     * executed at least once in given period.
     */
    public static Iterable<Task> incoming(Iterable<Task> tasks, Date start, Date end) {
        List answer = new ArrayList<Task>();
        for ( Task t : tasks) {
            if(t.nextTimeAfter(start) != null && (t.nextTimeAfter(start).before(end)
                    || t.nextTimeAfter(start).equals(end) ) ) {
                answer.add(t);
            }
        }
        return answer;
    }

    /**
     * Static methods that allows process TaskLists to get calendar of Task's execution
     * in given period.
     * @param tasks contains information about processed TaskList
     * @param start contains information about start of period
     * @param end contains information about end of period
     * @return object of interface SortedMap<Date, Set<Task>> that  contains calendar in format:
     * Key: Date - Value: set of all tasks executed at this time
     */
    public static SortedMap<Date, Set<Task>> calendar(Iterable<Task> tasks, Date start, Date end)  {
        SortedMap<Date, Set<Task>> answerMap = new TreeMap<Date, Set<Task>>();
        List<Task> once = (ArrayList<Task>) incoming(tasks, start, end);
        for(Task t : once) {
            Date currentTimeKey = t.nextTimeAfter(start);
            while (!currentTimeKey.after(end)) {
                Set<Task> taskSet = new HashSet<Task>();
                if(answerMap.containsKey(currentTimeKey)) {
                    taskSet = answerMap.get(currentTimeKey);
                    taskSet.add(t);
                }
                else {
                    taskSet.add(t);
                }
                answerMap.put(currentTimeKey, taskSet);
                if(!t.isRepeated()){
                    break;
                }
                currentTimeKey = new Date(currentTimeKey.getTime() + t.getRepeatInterval());
            }
        }

        return answerMap;
    }
}
