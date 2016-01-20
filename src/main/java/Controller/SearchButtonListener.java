package Controller;


import Model.MainModel;
import Model.Task;
import View.MainView;
import org.apache.log4j.Logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Set;
import java.util.SortedMap;

/**
 * SearchButtonListener is a public class to represent listener of "search" button
 *
 * @author Oleh
 * @version 0.9
 * @since 1.8
 */

public class SearchButtonListener implements ActionListener{
    MainView view;
    MainModel model;
    private static final Logger log = Logger.getLogger(SearchButtonListener.class);

    /**
     * Class constructor
     * @param view contains information about MainView object connected with listened button
     * @param model contains information about MainModel object connected with listened button
     */
    public SearchButtonListener(MainView view, MainModel model) {
        this.view = view;
        this.model = model;
        log.debug("SearchButtonListener created.");
    }

    /**
     * Method that implements interface ActionListener. Contains actions that are done when button is pushed
     * @param e contains information about button event
     */
    public void actionPerformed(ActionEvent e) {
        Date startCalendar = view.getCalendarStartDate();
        Date endCalendar = view.getCalendarEndDate();
        SortedMap<Date, Set<Task>> mapCalendar = model.getCalendar(startCalendar, endCalendar);
        view.showCalendarTable(mapCalendar);
        log.info("Calendar for given period was created and showed.");
    }
}
