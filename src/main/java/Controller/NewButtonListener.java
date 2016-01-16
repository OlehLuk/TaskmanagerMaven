package Controller;

import View.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewButtonListener implements ActionListener {
    MainView view;

    public NewButtonListener(MainView view) {
        this.view = view;
    }

    public void actionPerformed(ActionEvent e) {
        this.view.clear();
    }
}
