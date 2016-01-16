package Model;


import java.util.*;

public class Tasks {
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
