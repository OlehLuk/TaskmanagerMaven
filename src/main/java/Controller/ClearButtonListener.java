package Controller;

import View.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearButtonListener implements ActionListener {
    MainView view;

    public ClearButtonListener(MainView view) {
        this.view = view;
    }

    public void actionPerformed(ActionEvent e) {
        this.view.clear();
    }
}
