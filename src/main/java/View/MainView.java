package View;


import Model.Task;
import Model.TaskList;
import sun.security.pkcs11.P11Util;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.Date;
import java.util.Set;
import java.util.SortedMap;

/**
 * MainView is public class to represent View part of MVC in task manager
 * Stores main program's window(FormTaskmanager object). Contains methods for work with this window.
 *
 * @author Oleh
 * @version 0.9
 * @since 1.8
 *
 */


public class MainView {
    private FormTaskmanager appWindow = new FormTaskmanager();

    /**
     * Shows modal dialog window connected with main window. Dialog window contains confirm button
     * and message given as parameter.
     * @param string contains message that should be shown
     */
    public void showMessage(String string) {
        JOptionPane.showMessageDialog(appWindow, string, "Attention", JOptionPane.WARNING_MESSAGE );
    }

    /**
     * Shows at the main window list that was given as parameter
     * @param list contains information about TaskList that should be shown
     */
    public void showList(TaskList list) {
        this.appWindow.setAllTasksList(list);
    }

    /**
     * Shows calendar in main window
     * @param calendarMap SortedMap<Date, Set<Task>> object that
     *                    contains information about calendar that should be showed
     */
    public void showCalendarTable(SortedMap<Date, Set<Task>> calendarMap) {
        this.appWindow.setCalendarTasksTable(calendarMap);
    }

    /**
     * Makes main window visible.
     */
    public void showGUI() {
        this.appWindow.setVisible(true);
    }

    /**
     * Clears all input fields at main window
     */
    public void clear() {
        this.appWindow.clear();
    }

    /**
     * Adds listeners for buttons at main window
     * @param newButtonListener contains information about listener for "new" button
     * @param editButtonListener contains information about listener for "edit" button
     * @param searchButtonListener contains information about listener for "search" button
     * @param deleteButtonListener contains information about listener for "delete" button
     * @param clearButtonListener contains information about listener for "clear" button
     */
    public void addButtonListeners(ActionListener newButtonListener, ActionListener editButtonListener,
                                   ActionListener searchButtonListener, ActionListener deleteButtonListener,
                                   ActionListener clearButtonListener) {
        appWindow.addDeleteButtonActionListener(deleteButtonListener);
        appWindow.addSearchButtonActionListener(searchButtonListener);
        appWindow.addNewButtonActionListener(newButtonListener);
        appWindow.addEditButtonActionListener(editButtonListener);
        appWindow.addClearButtonActionListener(clearButtonListener);
    }

    /**
     * Adds listeners for all tasks list at main window
     * @param listListener contains information about listener for list
     */
    public void addListListener(ListSelectionListener listListener) {
        appWindow.addListItemSelectedListener(listListener);
    }

    /**
     * Gets value that describes whether inputed task is repeated
     * @return boolean value. If described input task is repeated - returns true, else - false.
     */
    public boolean isRepeated() {
        return appWindow.isTaskRepeated();
    }

    /**
     * Gets value that describes inputed task's title
     * @return String value of inputed title
     */
    public String getTaskTitle() {
        return appWindow.getTaskTitle();
    }

    /**
     * Gets value of task's Start Date inputed in window
     * @return Date value of start time of task's execution
     */
    public Date getTaskStartDate() {
        return appWindow.getStDate();
    }

    /**
     * Gets value of task's End Date inputed in window
     * @return Date value of end time of task's execution
     */
    public Date getTaskEndDate() {
        return appWindow.getEndDate();
    }

    /**
     * Gets value that describes task's activeness
     * @return boolean value. If described input task is active - returns true, else - false.
     */
    public boolean isTaskActive() {
        return appWindow.isTaskActive();
    }

    /**
     * Gets values of text fields that describe task's repeat interval
     * @return array that contains information about task's repeat interval. In format:
     * 0 element of array - number of days, 1 - hours, 2 - minutes, 3 - seconds
     */
    public int[] getTaskRepeatInterval() {
        return appWindow.getTaskRepeatInterval();
    }

    /**
     * Getter for index of selected element of all tasks list in window
     * @return int value of index of selected list element in window
     */
    public int getSelectedListItemIndex() {
        return appWindow.getSelectedListItemIndex();
    }

    /**
     * Update all input fields according to given Task parameter
     * @param task contains information about task that should be showed
     */
    public void updateFields(Task task) {
        appWindow.updateFields(task);
    }

    /**
     * Gets value of Calendar Start Date inputed in window
     * @return Date value of start time of calendar's period
     */
    public Date getCalendarStartDate() {
        return appWindow.getCalendarStDate();
    }

    /**
     * Gets value of Calendar End Date inputed in window
     * @return Date value of end time of calendar's period
     */
    public Date getCalendarEndDate() {
        return appWindow.getCalendarEndDate();
    }

    /**
     * Adds listener for program's main window
     * @param t contains information about listener for program's window
     */
    public void addWindowCloseListener(WindowListener t) {
        appWindow.addWindowListener(t);
    }
}
