package Controller;


import Model.MainModel;
import View.MainView;
import org.apache.log4j.Logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteButtonListener implements ActionListener {
    MainView view;
    MainModel model;
    private static final Logger log = Logger.getLogger(DeleteButtonListener.class);

    public DeleteButtonListener(MainView view, MainModel model) {
        this.view = view;
        this.model = model;
        log.debug("DeleteButtonListener created.");
    }

    public void actionPerformed(ActionEvent e) {
        int indexSelected = view.getSelectedListItemIndex();
        if(indexSelected == -1) {
            log.warn("Task was not deleted.  No Task was selected.");
            view.showMessage("Task was not deleted. No Task was selected. Please select Task and try again.");
            return;
        }
        log.info("Task was successfully deleted. List of all tasks was updated");
        model.remove(indexSelected);
        view.showList(model.getList());
    }
}
