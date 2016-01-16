package Controller;

import Model.MainModel;
import View.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewButtonListener implements ActionListener {
    MainView view;
    MainModel model;

    public NewButtonListener(MainView view, MainModel model) {
        this.view = view;
        this.model = model;
    }

    public void actionPerformed(ActionEvent e) {
        this.view.clear();
    }
}
