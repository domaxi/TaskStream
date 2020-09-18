package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

        System.out.println("Printing deadlines");
        printDeadlines(tasksData);

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));

        printDeadlinesUsingStreams(tasksData);
        for(Task t : filterByString(tasksData, "11")){
            System.out.println(t);
        }
        printDataUsingStreams(tasksData);
        System.out.println(printDeadlineCountUsingStream(tasksData));

    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    public static void printData(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDataUsingStreams(ArrayList<Task> tasksData){
        System.out.println("Printing stream");
        tasksData.stream()
                .forEach(System.out::println);
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    public static void printDeadlinesUsingStreams(ArrayList<Task> taskData){
        taskData.stream()
                .filter((s)-> s instanceof Deadline)
                .sorted((a,b) -> a.getDescription().toLowerCase().compareTo(b.getDescription().toLowerCase()))
                .forEach(System.out::println);
    }

    public static ArrayList<Task> filterByString (ArrayList<Task> taskData, String comparator){
        ArrayList<Task> filteredTaskList = (ArrayList<Task>) taskData.stream()
                .filter((s)->s.getDescription().contains(comparator))
                .collect(Collectors.toList());
        return filteredTaskList;

    }

    public static void printDeadlinesUsingStream(ArrayList<Task> taskData){
        taskData.stream()
            .filter((t)-> t instanceof Deadline)
            .forEach(System.out::println);
    }

    public static int printDeadlineCountUsingStream(ArrayList<Task> taskData){
        int count;
        count = (int) taskData.stream()
                .filter((t)-> t instanceof Deadline)
                .count();
        return count;
    }
}
