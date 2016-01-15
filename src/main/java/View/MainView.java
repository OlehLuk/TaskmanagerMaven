package View;


import Model.TaskList;

public class MainView {
    private FormTaskmanager appWindow = new FormTaskmanager();

    public void showList(TaskList list) {
        this.appWindow.setAllTasksList(list);
    }
}
