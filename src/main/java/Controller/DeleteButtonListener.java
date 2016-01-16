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
        //get selected list item index
        //remove from model
        //refresh list view
        int indexSelected = view.getSelectedListItemIndex();
        model.remove(indexSelected);
        view.showList(model.getList());
        this.view.clear();
    }
}
