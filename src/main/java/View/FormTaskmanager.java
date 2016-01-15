package View;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;


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
    private JSpinner editorStDate;


    //end time
    private JSpinner editorEndDate;

    //interval
    private JSpinner editorIntervalHours;
    private JSpinner editorIntervalMins;
    private JSpinner editorIntervalDay;
    private JSpinner editorIntervalMonth;
    private JSpinner editorIntervalYear;

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
            editorIntervalMins = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
            editorIntervalDay = new JSpinner(new SpinnerNumberModel(0, 0, 31, 1));
            editorIntervalMonth = new JSpinner(new SpinnerNumberModel(0, 0, 12, 1));
            editorIntervalYear = new JSpinner(new SpinnerNumberModel(0, 0, 5, 1));
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

        ButtonGroup radios = new ButtonGroup();
        radios.add(repeatableRadioButton);
        radios.add(nonrepeatableRadioButton);


        contentMain.add(bottomPanel, BorderLayout.SOUTH);
        setContentPane(contentMain);

        pack();
        setResizable(false);

    }

    public void startGUI() {
        this.setVisible(true);
    }

    public Object getStDate(){
        return this.editorStDate.getValue();
    };

    public static void main(String[] args) throws InterruptedException {

            FormTaskmanager test = new FormTaskmanager();
            test.startGUI();
            //test.taskInfo.append(test.getStDate().getClass().toString());
    }
}


