package View;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;


public class FormTaskmanager extends JFrame {
    //for lists of tasks
    private JList allTabList;


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


    private JTable calendarTable;

    //buttons
    private JButton searchButton = new JButton("Search");
    private JButton newButton = new JButton("New");
    private JButton editButton = new JButton("Edit");
    private JButton deleteButton = new JButton("Delete");
    private JButton clearButton = new JButton("Clear");

    //fields for reading time

    //start time
    private JSpinner editorStDateHours;
    private JSpinner editorStDateMins;
    private JSpinner editorStDateDay;
    private JSpinner editorStDateMonth;
    private JSpinner editorStDateYear;

    //end time
    private JSpinner editorEndDateHours;
    private JSpinner editorEndDateMins;
    private JSpinner editorEndDateDay;
    private JSpinner editorEndDateMonth;
    private JSpinner editorEndDateYear;

    //interval
    private JSpinner editorIntervalHours;
    private JSpinner editorIntervalMins;
    private JSpinner editorIntervalDay;
    private JSpinner editorIntervalMonth;
    private JSpinner editorIntervalYear;

    //start date for calendar
    private JSpinner calStDateHours ;
    private JSpinner calStDateMins ;
    private JSpinner calStDateDay ;
    private JSpinner calStDateMonth;
    private JSpinner calStDateYear;
    //end date for calendar
    private JSpinner calEndDateHours;
    private JSpinner calEndDateMins;
    private JSpinner calEndDateDay;
    private JSpinner calEndDateMonth;
    private JSpinner calEndDateYear;

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
        allTabList = new JList(new Object[]{"A", "<html><font color = red>B", "C"});
        allTabList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        allTasksTab.add(new JScrollPane(allTabList), BorderLayout.CENTER);

        //calendar tasks tab creating
        calendarTab = new JPanel();
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
        calendarTableScroll = new JScrollPane(calendarTable);
        calendarTab.add(calendarTableScroll, BorderLayout.CENTER);
        //create and add bottom buttons
        calendarBottomButtons = new JPanel();
        calendarBottomButtons.setLayout(new FlowLayout());
        calendarBottomButtons.add(searchButton);
        calendarDatesPanel = new JPanel();
        calendarDatesPanel.setLayout(new GridLayout(2,1));

        //create SpinnerNumberModel model = new SpinnerNumberModel(500.0, 0.0, 1000.0, 0.625); ?
        calendarStartDate = new JPanel();
            calendarStartDate.setLayout(new FlowLayout());
            JLabel calStDateLable = new JLabel("Start date: ");
            calStDateHours = new JSpinner();
            calStDateMins = new JSpinner();
            calStDateDay = new JSpinner();
            calStDateMonth = new JSpinner();
            calStDateYear = new JSpinner();
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

        calendarEndDate = new JPanel();
            calendarEndDate.setLayout(new FlowLayout());
            JLabel calEndDateLable = new JLabel("End date: ");
            calEndDateLable.setPreferredSize(calStDateLable.getPreferredSize());
            calEndDateHours = new JSpinner();
            calEndDateMins = new JSpinner();
            calEndDateDay = new JSpinner();
            calEndDateMonth = new JSpinner();
            calEndDateYear = new JSpinner();
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
            editorStDateHours = new JSpinner();
            editorStDateMins = new JSpinner();
            editorStDateDay = new JSpinner();
            editorStDateMonth = new JSpinner();
            editorStDateYear = new JSpinner();
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
            endTimePanel = new JPanel();
            endTimePanel.setLayout(new FlowLayout());
            editorEndDateHours = new JSpinner();
            editorEndDateMins = new JSpinner();
            editorEndDateDay = new JSpinner();
            editorEndDateMonth = new JSpinner();
            editorEndDateYear = new JSpinner();
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
            intervalTimePanel = new JPanel();
            intervalTimePanel.setLayout(new FlowLayout());
            editorIntervalHours = new JSpinner();
            editorIntervalMins = new JSpinner();
            editorIntervalDay = new JSpinner();
            editorIntervalMonth = new JSpinner();
            editorIntervalYear = new JSpinner();
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


