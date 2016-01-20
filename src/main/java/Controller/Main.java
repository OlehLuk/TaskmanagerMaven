package Controller;


import Model.MainModel;
import View.MainView;

/**
 * Main is public class to represent program's start point in Controller part of MVC in task manager
 *
 * @author Oleh
 * @version 0.9
 * @since 1.8
 *
 */

public class Main {

    public static void main(String[] args) {
        MainView view = new MainView();
        MainModel model = new MainModel();
        MainController controller = new MainController(model, view);

    }
}
