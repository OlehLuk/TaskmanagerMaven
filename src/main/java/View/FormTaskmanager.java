package View;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;


public class FormTaskmanager extends JFrame {
    //for lists of tasks
    private JTabbedPane tabbedPane1 = new JTabbedPane(JTabbedPane.TOP);

    private JTable calendarTable;

    //buttons
    private JButton searchButton = new JButton("Search");
    private JButton newButton = new JButton("New");
    private JButton editButton = new JButton("Edit");
    private JButton deleteButton = new JButton("Delete");
    private JButton clearButton = new JButton("Clear");

    //fields for reading time

    //start time


    //end time


    //interval


    //start date for calendar

    //end date for calendar

    //checkbox to check whether task is active
    private JCheckBox activeCheckBox = new JCheckBox("Active");

    //field for title
    private JTextField titleField = new JTextField(20);

    //to show info about chosen task
    private JTextArea taskInfo = new JTextArea(10,20);

    //radios for (non)repeatable task
    private JRadioButton repeatableRadioButton = new JRadioButton("repeatable Task");
    private JRadioButton nonrepeatableRadioButton = new JRadioButton("nonrepeatable Task");


    public FormTaskmanager() {
        super("Taskmanager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentMain = new JPanel();
        contentMain.setLayout(new BorderLayout());

        //right panel(info)
        JPanel rightPanel = new JPanel();

        rightPanel.add(taskInfo);
        rightPanel.setLayout(new FlowLayout());
        rightPanel.setBorder(new TitledBorder("Task info"));
        taskInfo.setLineWrap(true);
        rightPanel.setMaximumSize(rightPanel.getPreferredSize());
        contentMain.add(rightPanel,BorderLayout.EAST);

        //center panel - tabs
        //allTasksTab creating
        //tabbedPane1.setMinimumSize(new Dimension(10,10));
        JPanel allTasksTab = new JPanel();
        allTasksTab.setLayout(new BorderLayout());
        JList allTabList = new JList(new Object[]{"A", "<html><font color = red>B", "C"});
        allTabList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        allTasksTab.add(new JScrollPane(allTabList), BorderLayout.CENTER);

        //calendar tasks tab creating
        JPanel calendarTab = new JPanel();
        calendarTab.setLayout(new BorderLayout());
        String[] columnNames = {
                "Date",
                "Tasks"
        };
        String[][] data = {{"a", "b"}};

        calendarTable = new JTable(data, columnNames);
        calendarTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        calendarTable.setPreferredScrollableViewportSize(new Dimension(100,150));
        //add table
        JScrollPane calendarTableScroll = new JScrollPane(calendarTable);
        calendarTab.add(calendarTableScroll, BorderLayout.CENTER);
        //create and add bottom buttons
        JPanel calendarBottomButtons = new JPanel();
        calendarBottomButtons.setLayout(new FlowLayout());
        calendarBottomButtons.add(searchButton);
        JPanel calendarDatesPanel = new JPanel();
        calendarDatesPanel.setLayout(new GridLayout(2,1));
        JPanel calendarStartDate = new JPanel();
            calendarStartDate.setLayout(new FlowLayout());
            JLabel calStDateLable = new JLabel("Start date: ");
            JTextField calStDateHours = new JTextField(3);
            JTextField calStDateMins = new JTextField(3);
            JTextField calStDateDay = new JTextField(3);
            JTextField calStDateMonth = new JTextField(3);
            JTextField calStDateYear = new JTextField(5);
            calendarStartDate.add(calStDateLable);
            calendarStartDate.add(calStDateHours);
            calendarStartDate.add(new JLabel(" : "));
            calendarStartDate.add(calStDateMins);
            calendarStartDate.add(new JLabel(", "));
            calendarStartDate.add(calStDateDay);
            calendarStartDate.add(new JLabel("d -"));
            calendarStartDate.add(calStDateMonth);
            calendarStartDate.add(new JLabel("m -"));
            calendarStartDate.add(calStDateYear);
            calendarStartDate.add(new JLabel("y"));

        JPanel calendarEndDate = new JPanel();
            calendarEndDate.setLayout(new FlowLayout());
            JLabel calEndDateLable = new JLabel("End date: ");
            calEndDateLable.setPreferredSize(calStDateLable.getPreferredSize());
            JTextField calEndDateHours = new JTextField(3);
            JTextField calEndDateMins = new JTextField(3);
            JTextField calEndDateDay = new JTextField(3);
            JTextField calEndDateMonth = new JTextField(3);
            JTextField calEndDateYear = new JTextField(5);
            calendarEndDate.add(calEndDateLable);
            calendarEndDate.add(calEndDateHours);
            calendarEndDate.add(new JLabel(" : "));
            calendarEndDate.add(calEndDateMins);
            calendarEndDate.add(new JLabel(", "));
            calendarEndDate.add(calEndDateDay);
            calendarEndDate.add(new JLabel("d -"));
            calendarEndDate.add(calEndDateMonth);
            calendarEndDate.add(new JLabel("m -"));
            calendarEndDate.add(calEndDateYear);
            calendarEndDate.add(new JLabel("y"));

        calendarDatesPanel.add(calendarStartDate);
        calendarDatesPanel.add(calendarEndDate);
        calendarBottomButtons.add(calendarDatesPanel);
        calendarTab.add(calendarBottomButtons, BorderLayout.SOUTH);
        tabbedPane1.addTab("All tasks", allTasksTab);
        tabbedPane1.addTab("Calendar", calendarTab);
        contentMain.add(tabbedPane1, BorderLayout.CENTER);




        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(3, 2));
            JPanel bottomButtonsPanel = new JPanel();
            bottomButtonsPanel.setLayout(new FlowLayout());
            bottomButtonsPanel.add(newButton);
            bottomButtonsPanel.add(editButton);
            bottomButtonsPanel.add(deleteButton);
            bottomButtonsPanel.add(clearButton);
        bottomPanel.add(bottomButtonsPanel);
            JPanel bottomTitlePanel = new JPanel();
            bottomTitlePanel.setLayout(new FlowLayout());
            bottomTitlePanel.add(new JLabel("Task's title: "));
            bottomTitlePanel.add(titleField);
            bottomTitlePanel.add(activeCheckBox);
        bottomPanel.add(bottomTitlePanel);
            JPanel isRepeatPanel = new JPanel();
            isRepeatPanel.setLayout(new FlowLayout());
            isRepeatPanel.add(repeatableRadioButton);
            isRepeatPanel.add(nonrepeatableRadioButton);
        bottomPanel.add(isRepeatPanel);
            JPanel startTimePanel = new JPanel();
            startTimePanel.setLayout(new FlowLayout());
            JTextField editorStDateHours = new JTextField(3);
            JTextField editorStDateMins = new JTextField(3);
            JTextField editorStDateDay = new JTextField(3);
            JTextField editorStDateMonth = new JTextField(3);
            JTextField editorStDateYear = new JTextField(5);
            startTimePanel.add(new JLabel("Start time: "));
            startTimePanel.add(editorStDateHours);
            startTimePanel.add(new JLabel(" : "));
            startTimePanel.add(editorStDateMins);
            startTimePanel.add(new JLabel(", "));
            startTimePanel.add(editorStDateDay);
            startTimePanel.add(new JLabel("d -"));
            startTimePanel.add(editorStDateMonth);
            startTimePanel.add(new JLabel("m -"));
            startTimePanel.add(editorStDateYear);
            startTimePanel.add(new JLabel("y"));
        bottomPanel.add(startTimePanel);
            JPanel endTimePanel = new JPanel();
            endTimePanel.setLayout(new FlowLayout());
            JTextField editorEndDateHours = new JTextField(3);
            JTextField editorEndDateMins = new JTextField(3);
            JTextField editorEndDateDay = new JTextField(3);
            JTextField editorEndDateMonth = new JTextField(3);
            JTextField editorEndDateYear = new JTextField(5);
            endTimePanel.add(new JLabel("End time: "));
            endTimePanel.add(editorEndDateHours);
            endTimePanel.add(new JLabel(" : "));
            endTimePanel.add(editorEndDateMins);
            endTimePanel.add(new JLabel(", "));
            endTimePanel.add(editorEndDateDay);
            endTimePanel.add(new JLabel("d -"));
            endTimePanel.add(editorEndDateMonth);
            endTimePanel.add(new JLabel("m -"));
            endTimePanel.add(editorEndDateYear);
            endTimePanel.add(new JLabel("y"));
        bottomPanel.add(endTimePanel);
            JPanel intervalTimePanel = new JPanel();
            intervalTimePanel.setLayout(new FlowLayout());
            JTextField editorIntervalHours = new JTextField(3);
            JTextField editorIntervalMins = new JTextField(3);
            JTextField editorIntervalDay = new JTextField(3);
            JTextField editorIntervalMonth = new JTextField(3);
            JTextField editorIntervalYear = new JTextField(5);
            intervalTimePanel.add(new JLabel("Execution interval: "));
            intervalTimePanel.add(editorIntervalMins);
            intervalTimePanel.add(new JLabel("m "));
            intervalTimePanel.add(editorIntervalHours);
            intervalTimePanel.add(new JLabel("h "));
            intervalTimePanel.add(editorIntervalDay);
            intervalTimePanel.add(new JLabel("d -"));
            intervalTimePanel.add(editorIntervalMonth);
            intervalTimePanel.add(new JLabel("m -"));
            intervalTimePanel.add(editorIntervalYear);
            intervalTimePanel.add(new JLabel("y"));
        bottomPanel.add(intervalTimePanel);



        contentMain.add(bottomPanel, BorderLayout.SOUTH);
        setContentPane(contentMain);

        //setSize(750,500);
        pack();
        setResizable(false);

    }

    public static void main(String[] args) {
            FormTaskmanager test = new FormTaskmanager();
            test.setVisible(true);
    }
}


