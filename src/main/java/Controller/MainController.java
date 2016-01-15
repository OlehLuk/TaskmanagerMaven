package Controller;

import Model.MainModel;
import View.MainView;


public class MainController {
    MainModel model;
    MainView view;

    public MainController(MainModel model, MainView view) {
        this.model = model;
        this.view = view;
        model.load();
        view.showList(model.getList());
    }


}
