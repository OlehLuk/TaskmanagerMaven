package Controller;


import Model.MainModel;
import Model.Task;
import View.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Set;
import java.util.SortedMap;

public class SearchButtonListener implements ActionListener{
    MainView view;
    MainModel model;

    public SearchButtonListener(MainView view, MainModel model) {
        this.view = view;
        this.model = model;
    }

    public void actionPerformed(ActionEvent e) {
        Date startCalendar = view.getCalendarStartDate();
        Date endCalendar = view.getCalendarEndDate();
        SortedMap<Date, Set<Task>> mapCalendar = model.getCalendar(startCalendar, endCalendar);
        view.showCalendarTable(mapCalendar);
    }
}
