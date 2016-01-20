package Controller;

import Model.MainModel;
import View.MainView;
import org.apache.log4j.Logger;

import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

/**
 * MainController is public class to represent Controller part of MVC in task manager
 * Contains methods for program flow control
 *
 * @author Oleh
 * @version 0.9
 * @since 1.8
 *
 */

public class MainController {
    MainModel model;
    MainView view;
    private static final Logger log = Logger.getLogger(MainController.class);
    private Thread checker = new Thread() {
        public void run() {
            while (true) {
                String nowTasks = model.getNow();
                log.debug("Check tasks that should be executed now");
                if(!nowTasks.equals("")) {
                    log.info("Tasks that should be executed now were found");
                    view.showMessage("It is time to execute: \"" + nowTasks + "\" tasks.");
                }
                else{
                    log.debug("Tasks that should be executed now were not found");
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };


    /**
     * Class constructor
     * @param view contains information about MainView object
     * @param model contains information about MainModel object
     */
    public MainController(MainModel model, MainView view) {
        log.info("Program was started");
        this.model = model;
        this.view = view;
        log.info("Loading data started");
        model.load();
        view.showList(model.getList());
        log.info("Listeners were added to GUI elements");
        this.addAllListeners();
        log.info("Show GUI ");
        view.showGUI();
        log.info("Start searching passed tasks");
        this.checkPassed();
        log.info("Start checking time of tasks' execution");
        checker.start();
    }

    /**
     * Finds tasks that have passed at the moment and shows message if there are some passed tasks.
     */
    private void checkPassed() {
        String passedTitles = model.getPassed();
        if(!passedTitles.equals("")){
            log.info("Passed tasks were found");
            view.showMessage("Glad to see you again. Following tasks has passed: " + passedTitles + ".");
        }
        else{
            log.info("Passed tasks were not found");
        }
    }

    /**
     * Adds all needed listeners to View that is connected with this.
     */
    public void addAllListeners() {
        ActionListener newButtonListener = new NewButtonListener(view, model);
        ActionListener editButtonListener = new EditButtonListener(view, model);
        ActionListener searchButtonListener = new SearchButtonListener(view, model);
        ActionListener deleteButtonListener = new DeleteButtonListener(view, model);
        ActionListener clearButtonListener = new ClearButtonListener(view);
        ListSelectionListener listListener = new ListElementSelectionListener(view, model);
        WindowListener windowListener = new WindowCloseListener(view, model);
        view.addButtonListeners(newButtonListener, editButtonListener, searchButtonListener, deleteButtonListener,
                clearButtonListener);
        view.addListListener(listListener);
        view.addWindowCloseListener(windowListener);
    }


}
