package Controller;

import Model.MainModel;
import View.MainView;

import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;


public class MainController {
    MainModel model;
    MainView view;
    private Thread checker = new Thread() {
        public void run() {
            while (true) {
                String nowTasks = model.getNow();
                if(!nowTasks.equals("")) {
                    view.showMessage("It is time to execute: \"" + nowTasks + "\" tasks.");
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };


    public MainController(MainModel model, MainView view) {
        this.model = model;
        this.view = view;
        model.load();
        view.showList(model.getList());
        this.addAllListeners();
        view.showGUI();
        this.checkPassed();
        checker.start();
    }

    private void checkPassed() {
        String passedTitles = model.getPassed();
        if(!passedTitles.equals("")){
            view.showMessage("Glad to see you again. Following tasks has passed: " + passedTitles + ".");
        }
    }

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
