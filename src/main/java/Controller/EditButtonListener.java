package Controller;


import Model.MainModel;
import View.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditButtonListener implements ActionListener {
    MainView view;
    MainModel model;

    public EditButtonListener(MainView view, MainModel model) {
        this.view = view;
        this.model = model;
    }

    public void actionPerformed(ActionEvent e) {

    }
}
