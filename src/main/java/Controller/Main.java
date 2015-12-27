package Controller;

import Model.ArrayTaskList;
import Model.Task;
import Model.TaskIO;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException, IOException, ParseException {
       //Task first = new Task("First task", new Date(0), new Date(86400000), 86400);
        Task first = new Task("First task", new Date(86400000));
        //Task second = new Task("Second task", new Date(86400000), new Date(172800000), 3600);
        Task second = new Task("Second task", new Date(86400000), new Date(172800000), 3670);
        Task third = new Task("Third task", new Date(90000000), new Date(172800000), 10800);


        first.setActive(true);
        //second.setActive(true);
        //testing
        //test2
        third.setActive(true);

        ArrayTaskList testList = new ArrayTaskList();
        File f = new File("C:\\Users\\Вадим\\IdeaProjects\\taskmanager14.12.15test\\a.txt");
        testList.add(first);
        testList.add(second);
        testList.add(third);
        ArrayTaskList test = new ArrayTaskList();



        TaskIO.writeText(testList, f);


        TaskIO.readText(test, f);

        for (Task t: test) {
            System.out.println(t.toString());
        }

        System.out.println((int) ' ');
/*


        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        TaskIO.write(testList, new OutputStreamWriter(out));
        TaskList result = new ArrayTaskList();
        TaskIO.read(result, new InputStreamReader(in));

        for (Task t: result) {
            System.out.println(t.toString());
        }

        Set<Task> testList = new HashSet<Task>();

        testList.add(first);
        testList.add(second);
        testList.add(third);

        SortedMap<Date, Set<Task>> result = Tasks.calendar(testList, new Date(86399999), new Date(100800000));
        System.out.println(new Date(86399999));
        System.out.println(new Date(100800000));
        for(Map.Entry<Date, Set<Task>> entry : result.entrySet()) {
            System.out.println(entry.getKey() + "\n " + entry.getValue());
        }


        System.out.println("First task next time after 12100 is  - " + testList.getTask(0).nextTimeAfter(new Date(12100)));
        System.out.println("Second task next time after 10000 is - " + testList.getTask(1).nextTimeAfter(new Date(10000)));
        System.out.println("Third task next time after 30000 is - " + testList.getTask(2).nextTimeAfter(new Date(30000)) + "\n \n \n");





        for(Task task : testList) {

            System.out.println(task.toString());
        }
        ArrayTaskList test = testList.clone();




        System.out.print( first.toString() +'\n');
        System.out.print(" " + first.hashCode() +'\n');
        Task clone = first.clone();
        System.out.print(clone.toString() +'\n');
        System.out.print(" " + clone.hashCode() + '\n');
        System.out.print("" + clone.equals(first) + '\n');
        System.out.print("" + clone.equals(clone) + '\n');
        System.out.print("" + clone.equals(null) + '\n');



        first.setTitle("test");
        System.out.print(first.toString() + '\n');
        System.out.print(" " + first.hashCode() + '\n');
        System.out.print(clone.toString() +'\n');
        System.out.print(" " + clone.hashCode() +'\n');
        System.out.print("" + clone.equals(first) + '\n');


        ArrayTaskList testList = new ArrayTaskList();
        ArrayTaskList testList2 = new ArrayTaskList();



        first.setActive(true);
        second.setActive(true);
        third.setActive(true);

        testList.add(first);
        testList.add(second);
        testList.add(third);
        testList2.add(first.clone());
        testList2.add(second.clone());

        ArrayTaskList testList3 = testList.clone();

        System.out.println(testList.toString());
        System.out.println(testList.hashCode());
        System.out.println(testList2.toString());
        System.out.println(testList2.hashCode());
        System.out.println(testList3.toString());
        System.out.println(testList3.hashCode());

        System.out.print("" + testList.equals(testList2) + '\n');
        System.out.print("" + testList.equals(null) + '\n');
        System.out.print("" + testList.equals(testList) + '\n');
        System.out.print("" + testList.equals(testList3) + '\n');
        System.out.print("" + testList2.equals(testList3) + '\n');




        System.out.println("First task title is  - " + testList.getTask(0).getTitle());
        System.out.println("Second task title is - " + testList.getTask(1).getTitle());
        System.out.println("Third task title is - " + testList.getTask(2).getTitle() + "\n \n \n");


        System.out.println("First task next time after 121 is  - " + testList.getTask(0).nextTimeAfter(121));
        System.out.println("Second task next time after 100 is - " + testList.getTask(1).nextTimeAfter(100));
        System.out.println("Third task next time after 50 is - " + testList.getTask(2).nextTimeAfter(50) + "\n \n \n");



        testList.remove(second);
        System.out.println("First task title is  - " + testList.getTask(0).getTitle());
        System.out.println("Second task title is - " + testList.getTask(1).getTitle());

        */


    }
}
