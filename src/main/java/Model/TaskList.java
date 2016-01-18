package Model;


import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;

public abstract class TaskList implements Cloneable, Iterable<Task>, Serializable{

    /**
     * Adds Task object to the List
     * @param task contains information about Task we add
     */
    public abstract void add(Task task);


    /**
     * Removes certain Task given as task parameter from th List
     * @param task contains information about Task we remove
     * @return true if operation was successful and false if not
     */
    public abstract boolean remove(Task task);


    /**
     * Returns size of the List
     * @return size of the List
     */
    public abstract int size();


    /**
     * Returns Task with index given as index parameter
     * @param index contains information about index(number) of the Task we look for
     * @return Task with index given as index parameter
     */
    public abstract Task getTask(int index);


    /**
     * Returns List of Tasks that are executed at least once between from and to
     * @param from contains information about start time of interval Tasks are looked for in
     * @param to contains information about end time of interval Tasks are looked for in
     * @return List of Tasks that are executed at least once between from and to
     */
    public TaskList incoming(Date from, Date to) {
        TaskList answer;
        if(this instanceof ArrayTaskList){
            answer = new ArrayTaskList();
        }
        else {
            answer = new LinkedTaskList();
        }

        for(int i = 0; i < this.size(); i++) {
            if(this.getTask(i).nextTimeAfter(from) != null && (this.getTask(i).nextTimeAfter(from).before(to)
                    || this.getTask(i).nextTimeAfter(from).equals(to) ) ) {
                answer.add(this.getTask(i));
            }
        }
        return answer;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getClass().getSimpleName());
        stringBuilder.append(" @");
        stringBuilder.append(this.hashCode());
        stringBuilder.append(" { \n");
        for(int i = 0; i < this.size(); i++) {
            stringBuilder.append(this.getTask(i).toString());
            stringBuilder.append("\n");
        }
        stringBuilder.append("}");
        return new String(stringBuilder);
    }

    @Override
    public int hashCode() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < this.size(); i++) {
            stringBuilder.append(this.getTask(i).toString());
            stringBuilder.append("\n");
        }

        return new String(stringBuilder).hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskList taskList = (TaskList) o;
        if ( this.size() != taskList.size()) return false;
        for (int i = 0; i < this.size(); i++) {
            if (!this.getTask(i).equals(taskList.getTask(i))) {
                return false;
            }
        }
        return true;
    }

    /*
    @Override
    protected TaskList clone() throws CloneNotSupportedException {
        TaskList answer = null;
        try {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

            ObjectOutputStream ous = new ObjectOutputStream(baos);
            ous.writeObject(this);
            ous.close();
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);

            if (this instanceof ArrayTaskList) {
                answer = (ArrayTaskList) ois.readObject();
            } else {
                answer = (LinkedTaskList) ois.readObject();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return answer;
    }
    */


    public abstract Iterator<Task> iterator();
}
