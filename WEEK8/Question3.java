
import java.util.*;

abstract class Question {
    protected String text;
    protected int points;

    Question(String text, int points) {
        this.text = text;
        this.points = points;
    }

    abstract boolean checkAnswer(String answer);

    public String getText() {
        return text;
    }

    public int getPoints() {
        return points;
    }
}

class MultipleChoiceQuestion extends Question {
    private String correctAnswer;

    MultipleChoiceQuestion(String text, String correctAnswer, int points) {
        super(text, points);
        this.correctAnswer = correctAnswer;
    }

    boolean checkAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class TrueFalseQuestion extends Question {
    private String correctAnswer;

    TrueFalseQuestion(String text, String correctAnswer, int points) {
        super(text, points);
        this.correctAnswer = correctAnswer;
    }

    boolean checkAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}

class Examination {
    String name;
    List<Question> questions = new ArrayList<>();

    Examination(String name) {
        this.name = name;
    }

    void addQuestion(Question question) {
        questions.add(question);
    }
}

class Attempt {
    private Student student;
    private Examination exam;
    private Map<Question, String> answers = new LinkedHashMap<>();
    private boolean submitted = false;

    Attempt(Student student, Examination exam) {
        this.student = student;
        this.exam = exam;
    }

    void recordAnswer(int questionNumber, String answer) {
        if (submitted) {
            System.out.println(
                    "Cannot change answers for a submitted examination.");
            return;
        }

        if (questionNumber < 1
                || questionNumber > exam.questions.size()) {
            System.out.println("Invalid question number.");
            return;
        }

        Question question = exam.questions.get(questionNumber - 1);
        answers.put(question, answer);

        System.out.println("Answer recorded for Question "
                + questionNumber + ".");
    }

    void submit() {
        if (submitted) {
            System.out.println("Examination already submitted.");
            return;
        }

        submitted = true;
        int total = 0;
        int maximum = 0;

        System.out.println(exam.name + " submitted by "
                + student.name + ".");

        for (int i = 0; i < exam.questions.size(); i++) {
            Question question = exam.questions.get(i);
            maximum += question.getPoints();

            String answer = answers.get(question);
            boolean correct = answer != null
                    && question.checkAnswer(answer);

            int score = correct ? question.getPoints() : 0;
            total += score;

            System.out.println("Question " + (i + 1) + ": "
                    + (correct ? "Correct" : "Incorrect")
                    + " (" + score + " points)");
        }

        System.out.println("Total score: " + total + "/" + maximum);
    }
}

public class Question3 {
    public static void main(String[] args) {
        Student student = new Student("Student 1");
        Examination exam = new Examination("Exam A");

        exam.addQuestion(new MultipleChoiceQuestion(
                "Which option is correct?", "C", 5));

        exam.addQuestion(new TrueFalseQuestion(
                "Java supports multiple inheritance with classes.",
                "False", 5));

        Attempt attempt = new Attempt(student, exam);

        System.out.println("Exam A started by Student 1.");

        attempt.recordAnswer(1, "C");
        attempt.recordAnswer(2, "True");

        attempt.submit();

        attempt.recordAnswer(1, "A");
    }
}
