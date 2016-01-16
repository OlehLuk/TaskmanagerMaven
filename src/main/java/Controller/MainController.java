package Controller;

import Model.MainModel;
import View.MainView;

import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;


public class MainController {
    MainModel model;
    MainView view;

    public MainController(MainModel model, MainView view) {
        this.model = model;
        this.view = view;
        model.load();
        view.showList(model.getList());
        this.addAllListeners();
        view.showGUI();
    }

    public void addAllListeners() {
        ActionListener newButtonListener = new NewButtonListener(view, model);
        ActionListener editButtonListener = new EditButtonListener(view, model);
        ActionListener searchButtonListener = new SearchButtonListener(view, model);
        ActionListener deleteButtonListener = new DeleteButtonListener(view, model);
        ActionListener clearButtonListener = new ClearButtonListener(view);
        ListSelectionListener listListener = new ListElementSelectionListener(view, model);

        view.addButtonListeners(newButtonListener, editButtonListener, searchButtonListener, deleteButtonListener,
                clearButtonListener);
        view.addListListener(listListener);
    }


}
