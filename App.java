import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

class Task {
    private String description;
    private int priority;
    private Date dueDate;
    private boolean completed;
    private String category;

    public Task(String description, int priority, Date dueDate, String category) {
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.completed = false; 
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getCategory() {
        return category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return "Task: " + description + ", Priority: " + priority + ", Due Date: " + dateFormat.format(dueDate) + ", Completed: " + (completed ? "Yes" : "No") + ", Category: " + category;
    }
}

public class App {
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;
    private static Scanner scanner = new Scanner(System.in); 

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("----------------------------");
            System.out.println("      Todo List Application");
            System.out.println("----------------------------");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. Edit Task");
            System.out.println("4. Set Task Priority");
            System.out.println("5. View Tasks");
            System.out.println("6. Mark Task as Completed");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    removeTask();
                    break;
                case 3:
                    editTask();
                    break;
                case 4:
                    setTaskPriority();
                    break;
                case 5:
                    viewTasks();
                    break;
                case 6:
                    markTaskAsCompleted();
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void addTask() {
        if (taskCount < tasks.length) {
            System.out.println();
            System.out.println("--------------------");
            System.out.println("      Add Task");
            System.out.println("--------------------");
            System.out.print("Enter the task description: ");
            String description = scanner.nextLine();
            System.out.print("Enter the task priority (1-5): ");
            int priority = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (priority < 1 || priority > 5) {
                System.out.println("Invalid priority. Please enter a value between 1 and 5.");
                return;
            }

            System.out.print("Enter the task due date (dd/MM/yyyy): ");
            String dateStr = scanner.nextLine();
            Date dueDate = null;
            try {
                dueDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please try again.");
                return;
            }
            System.out.print("Enter the task category (e.g., Work, Personal, Urgent): ");
            String category = scanner.nextLine();
            tasks[taskCount++] = new Task(description, priority, dueDate, category);
            System.out.println("Task added.");
        } else {
            System.out.println("Task list is full.");
        }
    }

    private static void removeTask() {
        System.out.println();
        System.out.println("----------------------");
        System.out.println("      Remove Task");
        System.out.println("----------------------");
        System.out.print("Enter the task number to remove: ");
        int taskNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (taskNumber > 0 && taskNumber <= taskCount) {
            for (int i = taskNumber - 1; i < taskCount - 1; i++) {
                tasks[i] = tasks[i + 1];
            }
            tasks[--taskCount] = null;
            System.out.println("Task removed.");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private static void editTask() {
        System.out.println();
        System.out.println("-------------------");
        System.out.println("      Edit Task");
        System.out.println("-------------------");
        System.out.print("Enter the task number to edit: ");
        int taskNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (taskNumber > 0 && taskNumber <= taskCount) {
            System.out.print("Enter the new description: ");
            String description = scanner.nextLine();
            System.out.print("Enter the new priority (1-5): ");
            int priority = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (priority < 1 || priority > 5) {
                System.out.println("Invalid priority. Please enter a value between 1 and 5.");
                return;
            }

            System.out.print("Enter the new due date (dd/MM/yyyy): ");
            String dateStr = scanner.nextLine();
            Date dueDate = null;
            try {
                dueDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please try again.");
                return;
            }

            System.out.print("Enter the new category: ");
            String category = scanner.nextLine();

            Task task = tasks[taskNumber - 1];
            task.setDescription(description);
            task.setPriority(priority);
            task.setDueDate(dueDate);
            task.setCategory(category);
            System.out.println("Task updated.");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private static void setTaskPriority() {
        System.out.println();
        System.out.println("------------------------");
        System.out.println("      Set Task Priority");
        System.out.println("------------------------");
        System.out.print("Enter the task number to set priority: ");
        int taskNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (taskNumber > 0 && taskNumber <= taskCount) {
            System.out.print("Enter the new priority (1-5): ");
            int priority = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (priority < 1 || priority > 5) {
                System.out.println("Invalid priority. Please enter a value between 1 and 5.");
                return;
            }

            tasks[taskNumber - 1].setPriority(priority);
            System.out.println("Task priority updated.");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private static void viewTasks() {
        System.out.println();
        System.out.println("--------------------");
        System.out.println("      Todo List");
        System.out.println("--------------------");
        if (taskCount == 0) {
            System.out.println("No tasks to display.");
        } else {
            for (int i = 0; i < taskCount; i++) {
                Task task = tasks[i];
                String color;
                switch (task.getPriority()) {
                    case 1:
                        color = "\u001B[31m"; // Red for highest priority
                        break;
                    case 2:
                        color = "\u001B[33m"; // Yellow
                        break;
                    case 3:
                        color = "\u001B[32m"; // Green
                        break;
                    case 4:
                        color = "\u001B[34m"; // Blue
                        break;
                    case 5:
                        color = "\u001B[35m"; // Magenta for lowest priority
                        break;
                    default:
                        color = "\u001B[0m"; // Default color
                }
                System.out.println(color + (i + 1) + ". " + task + "\u001B[0m"); // Reset color after printing task
            }
        }
    }

    private static void markTaskAsCompleted() {
        System.out.println();
        System.out.println("--------------------------");
        System.out.println("      Mark Task Completed");
        System.out.println("--------------------------");
        System.out.print("Enter the task number to mark as completed: ");
        int taskNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (taskNumber > 0 && taskNumber <= taskCount) {
            tasks[taskNumber - 1].setCompleted(true);
            System.out.println("Task marked as completed.");
        } else {
            System.out.println("Invalid task number.");
        }
    }
}
