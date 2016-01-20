package Model;


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  LinkedTaskList is public class to represent list of tasks in task manager. Realisation based on ordered list.
 *  @author Oleh
 *  @version 0.9
 *  @since 1.8
 */

public class LinkedTaskList extends TaskList{
    private int numberOfTasks = 0;
    private LinkedTaskListElement firstElement;
    private LinkedTaskListElement lastElement;

    /**
     * Inner class that represents element of list
     */
    private class LinkedTaskListElement {
        private LinkedTaskListElement prevElement;
        private LinkedTaskListElement nextElement;
        private Task task;

        /**
         * Constructor of LinkedTaskListElement. Creates element storing certain Task
         * @param task containes information about Task should be stored
         */
        public LinkedTaskListElement(Task task) {
            this.task = task;
        }

        /**
         * Getter for stored Task.
         * @return Task object that is stored inside element
         */
        public Task getInnerTask() {
            return task;
        }

        /**
         * Method to get link to next element of the list
         * @return LinkedTaskListElement reference to next element of the list
         */
        public LinkedTaskListElement getNextElement() {
            return nextElement;
        }

        /**
         * Method to get link to previous element of the list
         * @return LinkedTaskListElement reference to previous element of the list
         */
        public LinkedTaskListElement getPrevElement() {
            return prevElement;
        }

        /**
         * Setter for the previous element of the list
         * @param prevElement containes info(reference) about previous element of the list
         */
        public void setPrevElement(LinkedTaskListElement prevElement) {
            this.prevElement = prevElement;
        }

        /**
         * Setter for the next element of the list
         * @param nextElement containes info(reference) about next element of the list
         */
        public void setNextElement(LinkedTaskListElement nextElement) {
            this.nextElement = nextElement;
        }

        /**
         * Setter for stored data - sets Task object that is stored inside the this element
         * of the list
         * @param task containes information about Task should be stored in this
         */
        public void setTask(Task task) {
            this.task = task;
        }
    }

    /**
     * Adds Task object to the List
     * @param task contains information about Task we add
     */
    public void add(Task task) {
        if(task == null) {
            throw new IllegalArgumentException("You can not add null to the list ");
        }
        LinkedTaskListElement adding = new LinkedTaskListElement(task);
        if(this.numberOfTasks == 0) {
            this.firstElement = this.lastElement = adding;
            numberOfTasks++;
        }
        else {
            this.lastElement.setNextElement(adding);
            adding.setPrevElement(this.lastElement);
            this.lastElement = adding;
            numberOfTasks++;
        }
    }

    /**
     * Removes certain Task given as task parameter from the List
     * @param task contains information about Task we remove
     * @return true if operation was successful and false if not
     */
    public boolean remove(Task task) {
        if(numberOfTasks == 0 || task == null) {
            return false;
        }
        for (int i = 0; i < this.numberOfTasks; i++) {
            if(this.getTask(i).equals(task)) {
                if(i == 0) {
                    this.firstElement = this.firstElement.getNextElement();
                    numberOfTasks--;
                    return true;
                }
                if(i == numberOfTasks - 1) {
                    this.lastElement = this.lastElement.getPrevElement();
                    numberOfTasks--;
                    return true;
                }
                LinkedTaskListElement current = this.firstElement;
                for (int j = 0; j < i ; j++) {
                    current = current.getNextElement();
                }
                LinkedTaskListElement before = current.getPrevElement();
                LinkedTaskListElement after = current.getNextElement();
                before.setNextElement(after);
                after.setPrevElement(before);
                numberOfTasks--;
                return true;
            }
        }
        return false;
    }

    /**
     * Returns size of the List
     * @return size of the List
     */
    public int size() {
        return numberOfTasks;
    }

    /**
     * Returns Task stored in element with given index
     * @param index contains information about index(number) of the Task we look for
     * @return Task with index given as index parameter
     */
    public Task getTask(int index) {
        LinkedTaskListElement current = this.firstElement;
        for (int i = 0; i < index; i++) {
            current = current.getNextElement();
        }
        return current.getInnerTask();
    }

    /**
     * Overrides Object's method clone(). Uses clone() of superclass.
     * @return LinkedTaskList object that is independent copy of this.
     * @throws CloneNotSupportedException
     */
    @Override
    public LinkedTaskList clone() throws CloneNotSupportedException {
        LinkedTaskList cloned = (LinkedTaskList) super.clone();
        cloned.numberOfTasks = 0;
        cloned.firstElement = null;
        cloned.lastElement = null;
        for (int i = 0; i < this.size() ; i++) {
            cloned.add(this.getTask(i).clone());
        }
        return cloned;
    }

    /**
     * Implements Iterable interface. Returns object of inner class that implements interface Iterator<Task>
     * @return object of interface Iterator<Task>
     */
    public Iterator<Task> iterator() {
        return new Itr();
    }

    /**
     * Inner class that represents realization of iterator for that class
     */
    private class Itr implements Iterator<Task> {
        LinkedTaskListElement current = firstElement;
        LinkedTaskListElement returned = null;
        int currentIndex = 0;
        int returnedIndex = -1;

        public boolean hasNext() {
            return  (currentIndex < size());
        }


        public Task next() throws NoSuchElementException {
            if(currentIndex >= size()) {
                throw new NoSuchElementException();
            }
            returned = current;
            returnedIndex = currentIndex;
            current = current.getNextElement();
            currentIndex++;
            return returned.getInnerTask();
        }


        public void remove() {
            if(returnedIndex < 0) {
                throw new IllegalStateException();
            }
            if(returnedIndex == 0) {
                firstElement = firstElement.getNextElement();
                numberOfTasks--;
                currentIndex = 0;
                //current;
                returnedIndex = -1;
                returned = null;
                return ;
            }
            if(returnedIndex == numberOfTasks - 1) {
                lastElement = lastElement.getPrevElement();
                numberOfTasks--;
                currentIndex = returnedIndex;
                current = null;
                returnedIndex = -1;
                returned = null;
                return;
            }
            LinkedTaskListElement before = returned.getPrevElement();
            LinkedTaskListElement after = returned.getNextElement();
            before.setNextElement(after);
            after.setPrevElement(before);
            currentIndex = returnedIndex;
            current = after;
            returnedIndex = -1;
            returned = null;
            numberOfTasks--;
            return ;
        }
    }

}
