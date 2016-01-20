package View;

import Model.ArrayTaskList;
import Model.Task;
import Model.TaskList;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * FormTaskmanager is public class to represent main window (JFrame) of task manager
 * Contains methods for changing this window.
 *
 * @author Oleh
 * @version 0.9
 * @since 1.8
 *
 */


public class FormTaskmanager extends JFrame {
    //for lists of tasks
    private JList allTabList = new JList();
    DefaultListModel listModel;

    private JTabbedPane tabbedPane1 = new JTabbedPane(JTabbedPane.TOP);

    //JPanels
    private JPanel contentMain;
    private JPanel rightPanel;
    private JPanel allTasksTab;
    private JPanel calendarTab;
    private JPanel calendarBottomButtons;
    private JPanel calendarDatesPanel;
    private JPanel calendarStartDate;
    private JPanel calendarEndDate;
    private JPanel bottomPanel;
    private JPanel bottomButtonsPanel;
    private JPanel bottomTitlePanel;
    private JPanel isRepeatPanel;
    private JPanel startTimePanel;
    private JPanel endTimePanel;
    private JPanel intervalTimePanel;

    //scroll wrappers
    private JScrollPane calendarTableScroll;


    //table for calendar and its model
    private JTable calendarTable;
    private DefaultTableModel tableModel;

    //buttons
    private JButton searchButton = new JButton("Search");
    private JButton newButton = new JButton("New");
    private JButton editButton = new JButton("Edit");
    private JButton deleteButton = new JButton("Delete");
    private JButton clearButton = new JButton("Clear");

    //fields for reading time

    //start time
    private JSpinner editorStDate;


    //end time
    private JSpinner editorEndDate;

    //interval
    private JSpinner editorIntervalSecs;
    private JSpinner editorIntervalHours;
    private JSpinner editorIntervalMins;
    private JSpinner editorIntervalDay;

    //start date for calendar
    private JSpinner calStDate;
    //end date for calendar
    private JSpinner calEndDate;

    //checkbox to check whether task is active
    private JCheckBox activeCheckBox = new JCheckBox("Active");

    //field for title
    private JTextField titleField = new JTextField(20);

    //to show info about chosen task
    private JTextArea taskInfo = new JTextArea(10,20);

    //radios for (non)repeatable task
    private JRadioButton repeatableRadioButton = new JRadioButton("repeatable Task");
    private JRadioButton nonrepeatableRadioButton = new JRadioButton("nonrepeatable Task");


    /**
     * Class constructor. Creates object of FormTaskmanager
     */
    public FormTaskmanager() {
        super("Taskmanager");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        contentMain = new JPanel();
        contentMain.setLayout(new BorderLayout());

        //right panel(info)
        rightPanel = new JPanel();

        rightPanel.add(taskInfo);
        rightPanel.setLayout(new FlowLayout());
        rightPanel.setBorder(new TitledBorder("Task info"));
        taskInfo.setLineWrap(true);
        rightPanel.setMaximumSize(rightPanel.getPreferredSize());
        contentMain.add(rightPanel,BorderLayout.EAST);

        //center panel - tabs
        //allTasksTab creating
        allTasksTab = new JPanel();
        allTasksTab.setLayout(new BorderLayout());

        listModel = new DefaultListModel();
        allTabList.setModel(listModel);


        allTabList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        allTasksTab.add(new JScrollPane(allTabList), BorderLayout.CENTER);

        //calendar tasks tab creating
        calendarTab = new JPanel();
        calendarTab.setLayout(new BorderLayout());
        String[] columnNames = {
                "Date",
                "Tasks"
        };

        tableModel = new DefaultTableModel(null, columnNames);
        calendarTable = new JTable(tableModel);
        calendarTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        calendarTable.setPreferredScrollableViewportSize(new Dimension(100,150));
        //add table
        calendarTableScroll = new JScrollPane(calendarTable);
        calendarTab.add(calendarTableScroll, BorderLayout.CENTER);
        //create and add bottom buttons
        calendarBottomButtons = new JPanel();
        calendarBottomButtons.setLayout(new FlowLayout());
        calendarBottomButtons.add(searchButton);
        calendarDatesPanel = new JPanel();
        calendarDatesPanel.setLayout(new GridLayout(2,1));


        calendarStartDate = new JPanel();
            calendarStartDate.setLayout(new FlowLayout());
            JLabel calStDateLable = new JLabel("Start date: ");
            calStDate = new JSpinner(new SpinnerDateModel(new Date(), new Date(0),
                    new Date(new Date().getTime()+1000000000000000000L), Calendar.DAY_OF_WEEK));
            calendarStartDate.add(calStDateLable);
            calendarStartDate.add(calStDate);


        calendarEndDate = new JPanel();
            calendarEndDate.setLayout(new FlowLayout());
            JLabel calEndDateLable = new JLabel("End date: ");
            calEndDateLable.setPreferredSize(calStDateLable.getPreferredSize());
            calEndDate = new JSpinner(new SpinnerDateModel(new Date(), new Date(0),
                    new Date(new Date().getTime()+1000000000000000000L), Calendar.DAY_OF_WEEK));
            calendarEndDate.add(calEndDateLable);
            calendarEndDate.add(calEndDate);

        calendarDatesPanel.add(calendarStartDate);
        calendarDatesPanel.add(calendarEndDate);
        calendarBottomButtons.add(calendarDatesPanel);
        calendarTab.add(calendarBottomButtons, BorderLayout.SOUTH);
        tabbedPane1.addTab("All tasks", allTasksTab);
        tabbedPane1.addTab("Calendar", calendarTab);
        contentMain.add(tabbedPane1, BorderLayout.CENTER);




        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(3, 2));
            bottomButtonsPanel = new JPanel();
            bottomButtonsPanel.setLayout(new FlowLayout());
            bottomButtonsPanel.add(newButton);
            bottomButtonsPanel.add(editButton);
            bottomButtonsPanel.add(deleteButton);
            bottomButtonsPanel.add(clearButton);
        bottomPanel.add(bottomButtonsPanel);
            bottomTitlePanel = new JPanel();
            bottomTitlePanel.setLayout(new FlowLayout());
            bottomTitlePanel.add(new JLabel("Task's title: "));
            bottomTitlePanel.add(titleField);
            bottomTitlePanel.add(activeCheckBox);
        bottomPanel.add(bottomTitlePanel);
            isRepeatPanel = new JPanel();
            isRepeatPanel.setLayout(new FlowLayout());
            isRepeatPanel.add(repeatableRadioButton);
            isRepeatPanel.add(nonrepeatableRadioButton);
        bottomPanel.add(isRepeatPanel);
            startTimePanel = new JPanel();
            startTimePanel.setLayout(new FlowLayout());
            editorStDate = new JSpinner(new SpinnerDateModel(new Date(), new Date(0),
                    new Date(new Date().getTime()+1000000000000000000L), Calendar.DAY_OF_WEEK));
            startTimePanel.add(new JLabel("Start time: "));
            startTimePanel.add(editorStDate);

        bottomPanel.add(startTimePanel);
            endTimePanel = new JPanel();
            endTimePanel.setLayout(new FlowLayout());
            editorEndDate = new JSpinner(new SpinnerDateModel(new Date(), new Date(0),
                    new Date(new Date().getTime()+1000000000000000000L), Calendar.DAY_OF_WEEK));
            endTimePanel.add(new JLabel("End time: "));
            endTimePanel.add(editorEndDate);
        bottomPanel.add(endTimePanel);
            intervalTimePanel = new JPanel();
            intervalTimePanel.setLayout(new FlowLayout());
            editorIntervalHours = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
            editorIntervalSecs = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
            editorIntervalMins = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
            editorIntervalDay = new JSpinner(new SpinnerNumberModel(0, 0, 31, 1));
            intervalTimePanel.add(new JLabel("Execution interval: "));
            intervalTimePanel.add(editorIntervalSecs);
            intervalTimePanel.add(new JLabel("sec "));
            intervalTimePanel.add(editorIntervalMins);
            intervalTimePanel.add(new JLabel("min "));
            intervalTimePanel.add(editorIntervalHours);
            intervalTimePanel.add(new JLabel("h "));
            intervalTimePanel.add(editorIntervalDay);
            intervalTimePanel.add(new JLabel("days"));
        bottomPanel.add(intervalTimePanel);

        ButtonGroup radios = new ButtonGroup();
        radios.add(repeatableRadioButton);
        radios.add(nonrepeatableRadioButton);
        nonrepeatableRadioButton.setSelected(true);


        contentMain.add(bottomPanel, BorderLayout.SOUTH);
        setContentPane(contentMain);

        pack();
        setResizable(false);

    }


    /**
     * Gets value of task's Start Date inputed in form
     * @return Date value of start time of task's execution
     */
    public Date getStDate(){
        return (Date) this.editorStDate.getValue();
    };

    /**
     * Gets value of task's End Date inputed in form
     * @return Date value of end time of task's execution
     */
    public Date getEndDate(){
        return (Date) this.editorEndDate.getValue();
    };

    /**
     * Gets value of Calendar Start Date inputed in form
     * @return Date value of start time of calendar's period
     */
    public Date getCalendarStDate(){
        return (Date) this.calStDate.getValue();
    };

    /**
     * Gets value of Calendar End Date inputed in form
     * @return Date value of end time of calendar's period
     */
    public Date getCalendarEndDate(){
        return (Date) this.calEndDate.getValue();
    };

    //method for showing list of all tasks

    /**
     * Shows list of all tasks in form
     * @param list TaskList object that contains information about list of tasks that should be showed
     */
    public void setAllTasksList(TaskList list) {
        this.listModel.clear();
        for(int i = 0; i < list.size(); i++) {
            String taskStr = list.getTask(i).toString();
            this.listModel.addElement(taskStr);
        }
    }

    //method for showing table of calendar

    /**
     * Shows calendar in form
     * @param calendarMap SortedMap<Date, Set<Task>> object that
     *                    contains information about calendar that should be showed
     */
    public void setCalendarTasksTable(SortedMap<Date, Set<Task>> calendarMap) {
        this.tableModel.setDataVector(null, new String[] {"Date", "Tasks"});

        for (Map.Entry<Date, Set<Task>> entry : calendarMap.entrySet()) {
            String[] tempTableRow = new String[2];
            tempTableRow[0] = entry.getKey().toString();
            Object[] taskArray =  entry.getValue().toArray();
            StringBuilder tempStr = new StringBuilder();
            for (int j = 0; j < taskArray.length; j++) {
                tempStr.append(((Task)taskArray[j]).getTitle() );
            }
            tempTableRow[1] = new String(tempStr);
            this.tableModel.addRow(tempTableRow);
        }
    }

    //clear method clears fields used to read info from user

    /**
     * Clears all input fields at form
     */
    public void clear() {
        taskInfo.setText("");
        titleField.setText("");
        Date current = new Date();
        calStDate.setValue(current);
        calEndDate.setValue(current);
        editorStDate.setValue(current);
        editorEndDate.setValue(current);
        editorIntervalDay.setValue(0);
        editorIntervalHours.setValue(0);
        editorIntervalMins.setValue(0);
        activeCheckBox.setSelected(false);
        titleField.setText("Task title");
        allTabList.clearSelection();
    }

    //methods for adding ActionListeners  to buttons
    /**
     * Adds listener for new button
     * @param t contains information about listener
     */
    public void addNewButtonActionListener(ActionListener t) {
        newButton.addActionListener(t);
    }

    /**
     * Adds listener for search button
     * @param t contains information about listener
     */
    public void addSearchButtonActionListener(ActionListener t) {
        searchButton.addActionListener(t);
    }

    /**
     * Adds listener for edit button
     * @param t contains information about listener
     */
    public void addEditButtonActionListener(ActionListener t) {
        editButton.addActionListener(t);
    }

    /**
     * Adds listener for delete button
     * @param t contains information about listener
     */
    public void addDeleteButtonActionListener(ActionListener t) {
        deleteButton.addActionListener(t);
    }

    /**
     * Adds listener for clear button
     * @param t contains information about listener
     */
    public void addClearButtonActionListener(ActionListener t) {
        clearButton.addActionListener(t);
    }

    //add list item selection listener
    /**
     * Adds listener for list
     * @param t contains information about listener
     */
    public void addListItemSelectedListener(ListSelectionListener t) {
        allTabList.addListSelectionListener(t);
    }

    /**
     * Gets value of radio buttons that describes whether inputed task is repeated
     * @return boolean value. If described input task is repeated - returns true, else - false.
     */
    public boolean isTaskRepeated() {
        return repeatableRadioButton.isSelected();
    }

    /**
     * Gets value of text field that describes inputed task's title
     * @return String value of inputed title
     */
    public String getTaskTitle() {
        return titleField.getText();
    }

    /**
     * Gets value of checkbox that describes task's activeness
     * @return boolean value. If described input task is active - returns true, else - false.
     */
    public boolean isTaskActive() {
        return activeCheckBox.isSelected();
    }

    /**
     * Gets values of text fields that describe task's repeat interval
     * @return array that contains information about task's repeat interval. In format:
     * 0 element of array - number of days, 1 - hours, 2 - minutes, 3 - seconds
     */
    public int[] getTaskRepeatInterval() {
        int[] interval = new int[4];
        interval[0] = (Integer) editorIntervalSecs.getValue();
        interval[1] = (Integer) editorIntervalMins.getValue();
        interval[2] = (Integer) editorIntervalHours.getValue();
        interval[3] = (Integer) editorIntervalDay.getValue();
        return interval;
    }

    /**
     * Getter for index of selected element of all tasks list
     * @return int value of index of selected list element
     */
    public int getSelectedListItemIndex() {
        return allTabList.getSelectedIndex();
    }

    /**
     * Update all input fields according to given Task parameter
     * @param task contains information about task that should be showed
     */
    public void updateFields(Task task) {
        this.titleField.setText(task.getTitle());
        this.activeCheckBox.setSelected(task.isActive());
        this.repeatableRadioButton.setSelected(task.isRepeated());
        this.taskInfo.setText(task.toString());
        this.editorStDate.setValue(task.getStartTime());
        this.editorEndDate.setValue(task.getEndTime());
        int interval = (int) (task.getRepeatInterval() / 1000);
        this.editorIntervalDay.setValue(interval / (60 * 60 * 24));
        interval %= 60 * 60 * 24;
        this.editorIntervalHours.setValue(interval / (60 * 60));
        interval %= 3600;
        this.editorIntervalMins.setValue(interval / 60);
        interval %= 60;
        this.editorIntervalSecs.setValue(interval);
    }

}


