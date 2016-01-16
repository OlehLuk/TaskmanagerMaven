package View;


import Model.Task;
import Model.TaskList;
import sun.security.pkcs11.P11Util;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Set;
import java.util.SortedMap;

public class MainView {
    private FormTaskmanager appWindow = new FormTaskmanager();

    public void showMessage(String string) {
        JOptionPane.showMessageDialog(appWindow, string, "Attention", JOptionPane.WARNING_MESSAGE );
        /*
        JOptionPane pane = new JOptionPane(string, JOptionPane.WARNING_MESSAGE);
        JDialog dialog = pane.createDialog(appWindow, "Attention!");
        dialog.setVisible(true);
        */
    }

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
        this.appWindow.clear();
    }

    public void addButtonListeners(ActionListener newButtonListener, ActionListener editButtonListener,
                                   ActionListener searchButtonListener, ActionListener deleteButtonListener,
                                   ActionListener clearButtonListener) {
        appWindow.addDeleteButtonActionListener(deleteButtonListener);
        appWindow.addSearchButtonActionListener(searchButtonListener);
        appWindow.addNewButtonActionListener(newButtonListener);
        appWindow.addEditButtonActionListener(editButtonListener);
        appWindow.addClearButtonActionListener(clearButtonListener);
    }

    public void addListListener(ListSelectionListener listListener) {
        appWindow.addListItemSelectedListener(listListener);
    }


    public boolean isRepeated() {
        return appWindow.isTaskRepeated();
    }

    public String getTaskTitle() {
        return appWindow.getTaskTitle();
    }

    public Date getTaskStartDate() {
        return appWindow.getStDate();
    }

    public Date getTaskEndDate() {
        return appWindow.getEndDate();
    }

    public boolean isTaskActive() {
        return appWindow.isTaskActive();
    }

    public int[] getTaskRepeatInterval() {
        return appWindow.getTaskRepeatInterval();
    }

    public int getSelectedListItemIndex() {
        return appWindow.getSelectedListItemIndex();
    }

    public void updateFields(Task task) {
        appWindow.updateFields(task);
    }

    public Date getCalendarStartDate() {
        return appWindow.getCalendarStDate();
    }

    public Date getCalendarEndDate() {
        return appWindow.getCalendarEndDate();
    }
}
