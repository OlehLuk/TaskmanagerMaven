package Controller;


import Model.MainModel;
import View.MainView;

public class Main {

    public static void main(String[] args) {
        MainView view = new MainView();
        MainModel model = new MainModel();
        MainController controller = new MainController(model, view);

    }
}
