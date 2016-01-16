package Controller;


import Model.MainModel;
import View.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteButtonListener implements ActionListener {
    MainView view;
    MainModel model;

    public DeleteButtonListener(MainView view, MainModel model) {
        this.view = view;
        this.model = model;
    }

    public void actionPerformed(ActionEvent e) {
        int indexSelected = view.getSelectedListItemIndex();
        if(indexSelected == -1) {
            //show message + log
            view.showMessage("Task was not deleted. No Task was selected. Please select Task and try again.");
            return;
        }
        model.remove(indexSelected);
        view.showList(model.getList());
    }
}
