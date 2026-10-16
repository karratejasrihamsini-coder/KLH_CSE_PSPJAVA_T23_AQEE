package src;


import java.util.*;
import java.io.*;

class Question {
    String topic;
    String question;
    String[] options;
    int answer;
    int level;

    Question(String topic, String question, String[] options,
             int answer, int level) {
        this.topic = topic;
        this.question = question;
        this.options = options;
        this.answer = answer;
        this.level = level;
    }
}

public class Main {

    static Scanner sc = new Scanner(System.in);

    static Question[] questions = {
        new Question("Data Types", "Which type stores whole numbers?",
            new String[]{"int", "double", "boolean", "char"}, 1, 1),

        new Question("Data Types", "Which type stores true or false?",
            new String[]{"int", "boolean", "char", "String"}, 2, 2),

        new Question("Operators", "What is 5 + 3?",
            new String[]{"15", "2", "8", "53"}, 3, 1),

        new Question("Operators", "What is the result of 10 % 3?",
            new String[]{"0", "1", "3", "10"}, 2, 2),

        new Question("Control Structures", "Which statement checks a condition?",
            new String[]{"if", "import", "class", "package"}, 1, 1),

        new Question("Control Structures", "Which statement stops a loop?",
            new String[]{"continue", "break", "case", "if"}, 2, 2),

        new Question("Methods", "Which keyword returns a value?",
            new String[]{"static", "void", "return", "new"}, 3, 1),

        new Question("Methods", "What is recursion?",
            new String[]{"A loop only", "A method calling itself",
                         "A variable", "A class"}, 2, 2),

        new Question("Arrays", "What is the first index of an array?",
            new String[]{"1", "0", "-1", "2"}, 2, 1),

        new Question("Arrays", "How do we get an array's length?",
            new String[]{"length()", "size()", "length", "count"}, 3, 2),

        new Question("Strings", "Which class stores text?",
            new String[]{"int", "String", "double", "boolean"}, 2, 1),

        new Question("Strings", "Which method compares String contents?",
            new String[]{"equals()", "length()", "trim()", "split()"}, 1, 2),

        new Question("OOP", "Which keyword creates an object?",
            new String[]{"this", "new", "void", "static"}, 2, 1),

        new Question("OOP", "What is encapsulation?",
            new String[]{"Looping", "Sorting", "Protecting data inside a class",
                         "Adding numbers"}, 3, 2),

        new Question("Inheritance", "Which keyword is used for inheritance?",
            new String[]{"extends", "import", "return", "new"}, 1, 1),

        new Question("Inheritance", "What is method overriding?",
            new String[]{"Creating a loop", "Redefining an inherited method",
                         "Creating a variable", "Importing a class"}, 2, 2),

        new Question("Exception Handling", "Which block handles exceptions?",
            new String[]{"try", "catch", "class", "switch"}, 2, 1),

        new Question("Exception Handling", "Which keyword throws an exception?",
            new String[]{"catch", "try", "throw", "final"}, 3, 2),

        new Question("File I/O", "Which class can read text from a file?",
            new String[]{"BufferedReader", "ArrayList", "HashSet", "Math"},
            1, 1),

        new Question("File I/O", "Which method writes data to a file?",
            new String[]{"Files.readAllLines()", "Files.write()",
                         "length()", "equals()"}, 2, 2),

        new Question("Collections", "Which collection stores a list of items?",
            new String[]{"ArrayList", "HashMap", "Scanner", "String"}, 1, 1),

        new Question("Collections", "Which collection stores key-value pairs?",
            new String[]{"ArrayList", "HashSet", "HashMap", "String"}, 3, 2)
    };

    static int[] topicLevel = new int[11];
    static int[] topicCorrect = new int[11];
    static int[] topicAttempted = new int[11];

    static String[] topics = {
        "Data Types", "Operators", "Control Structures",
        "Methods", "Arrays", "Strings", "OOP",
        "Inheritance", "Exception Handling", "File I/O",
        "Collections"
    };

    static String name;
    static int totalCorrect = 0;
    static int totalAttempted = 0;
    static int totalPoints = 0;

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println("  ADAPTIVE QUIZ AND EXAM ENGINE");
        System.out.println("================================");

        System.out.print("Enter your name: ");
        name = sc.nextLine();

        for (int i = 0; i < topicLevel.length; i++) {
            topicLevel[i] = 1;
        }

        int choice;

        do {
            System.out.println("\n----------- MENU -----------");
            System.out.println("1. Quiz Mode");
            System.out.println("2. Exam Mode");
            System.out.println("3. Performance Report");
            System.out.println("4. Leaderboard");
            System.out.println("5. Exit");

            choice = readNumber("Enter your choice: ", 1, 5);

            switch (choice) {
                case 1:
                    startQuiz(5);
                    break;

                case 2:
                    startQuiz(10);
                    break;

                case 3:
                    showReport();
                    break;

                case 4:
                    showLeaderboard();
                    break;

                case 5:
                    System.out.println("Thank you for using our project!");
                    break;
            }

        } while (choice != 5);
    }

    static void startQuiz(int number) {

        int correct = 0;
        int points = 0;

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < questions.length; i++) {
            list.add(i);
        }

        Collections.shuffle(list);

        number = Math.min(number, questions.length);

        System.out.println("\nStarting your quiz...");

        for (int i = 0; i < number; i++) {

            Question q = questions[list.get(i)];

            int topicIndex = getTopicIndex(q.topic);

            System.out.println("\nQuestion " + (i + 1));
            System.out.println("Topic: " + q.topic);
            System.out.println("Difficulty: " +
                getLevelName(topicLevel[topicIndex]));

            System.out.println(q.question);

            for (int j = 0; j < q.options.length; j++) {
                System.out.println((j + 1) + ". " + q.options[j]);
            }

            int answer = readNumber("Your answer (1-4): ", 1, 4);

            totalAttempted++;
            topicAttempted[topicIndex]++;

            if (answer == q.answer) {

                System.out.println("Correct answer!");
                correct++;
                totalCorrect++;

                points = points + q.level * 10;
                totalPoints = totalPoints + q.level * 10;

                topicCorrect[topicIndex]++;

                if (topicLevel[topicIndex] < 3) {
                    topicLevel[topicIndex]++;
                }

            } else {

                System.out.println("Wrong answer!");
                System.out.println("Correct answer: " +
                    q.options[q.answer - 1]);

                if (topicLevel[topicIndex] > 1) {
                    topicLevel[topicIndex]--;
                }
            }
        }

        double percentage = correct * 100.0 / number;

        System.out.println("\n--------- RESULT ---------");
        System.out.println("Student: " + name);
        System.out.println("Correct answers: " + correct);
        System.out.println("Total questions: " + number);
        System.out.printf("Percentage: %.2f%%%n", percentage);
        System.out.println("Points earned: " + points);

        saveResult(correct, number, points);
    }

    static int getTopicIndex(String topic) {

        for (int i = 0; i < topics.length; i++) {
            if (topics[i].equals(topic)) {
                return i;
            }
        }

        return 0;
    }

    static String getLevelName(int level) {

        if (level == 1) {
            return "Easy";
        } else if (level == 2) {
            return "Medium";
        } else {
            return "Hard";
        }
    }

    static void showReport() {

        System.out.println("\n====== PERFORMANCE REPORT ======");
        System.out.println("Student: " + name);
        System.out.println("Total questions answered: " + totalAttempted);
        System.out.println("Correct answers: " + totalCorrect);
        System.out.println("Total points: " + totalPoints);

        if (totalAttempted > 0) {
            double percentage = totalCorrect * 100.0 / totalAttempted;
            System.out.printf("Overall percentage: %.2f%%%n", percentage);
        }

        System.out.println("\nTopic-wise report:");

        for (int i = 0; i < topics.length; i++) {

            System.out.println("\nTopic: " + topics[i]);
            System.out.println("Difficulty: " +
                getLevelName(topicLevel[i]));
            System.out.println("Correct: " + topicCorrect[i]);
            System.out.println("Attempted: " + topicAttempted[i]);

            if (topicAttempted[i] > 0) {
                double percentage = topicCorrect[i] * 100.0 /
                    topicAttempted[i];

                System.out.printf("Accuracy: %.2f%%%n", percentage);
            }
        }
    }

    static void saveResult(int correct, int attempted, int points) {

        try {
            FileWriter writer = new FileWriter("results.txt", true);

            writer.write(name + " | " + correct + "/" + attempted +
                " | Points: " + points + "\n");

            writer.close();

            System.out.println("Result saved successfully.");

        } catch (IOException e) {
            System.out.println("Unable to save the result.");
        }
    }

    static void showLeaderboard() {

        System.out.println("\n====== SAVED RESULTS ======");

        try {
            BufferedReader reader =
                new BufferedReader(new FileReader("results.txt"));

            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("No saved results yet.");
        }
    }

    static int readNumber(String message, int min, int max) {

        while (true) {
            System.out.print(message);

            try {
                int number = Integer.parseInt(sc.nextLine());

                if (number >= min && number <= max) {
                    return number;
                }

            } catch (NumberFormatException e) {
                // Ask again if the input is not a number.
            }

            System.out.println("Please enter a number from " +
                min + " to " + max + ".");
        }
    }
}

    

