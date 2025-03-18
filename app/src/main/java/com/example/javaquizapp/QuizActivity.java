package com.example.javaquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    private TextView questionTextView, timerTextView;
    private RadioGroup optionsRadioGroup;
    private RadioButton option1, option2, option3, option4;
    private Button nextButton;
    private ProgressBar timerProgressBar;

    private String[] questions = {
            "Which data type is used to store whole numbers in Java?",
            "What is the correct syntax for declaring a variable in Java?",
            "What does the System.out.println() method do?",
            "Which of the following is NOT a valid Java variable name?",
            "What will be the output of the following code?\nint x = 5;\nx += 3;\nSystem.out.println(x);",
            "How do you correctly create an object in Java?",
            "Which keyword is used to inherit a class in Java?",
            "What is the default value of a boolean variable in Java?",
            "What is the difference between == and .equals() in Java?",
            "What is method overloading in Java?",
            "What is the output of the following code?\nString str = \"Hello\";\nstr.concat(\" World\");\nSystem.out.println(str);",
            "What is the purpose of the final keyword in Java?",
            "Which Java collection allows only unique elements?",
            "How do you create a thread in Java?",
            "What will happen if a Java program runs out of memory?",
            "What is the difference between HashMap and Hashtable in Java?",
            "What is garbage collection in Java?",
            "What does the volatile keyword do in Java?",
            "What is the purpose of the synchronised keyword?",
            "What is the default access modifier for a class in Java?"
    };

    private String[][] options = {
            {"A) float", "B) int", "C) boolean", "D) double"},
            {"A) variableName int;", "B) int variableName;", "C) variableName = int;", "D) declare int variableName;"},
            {"A) Reads input from the user", "B) Prints text to the console", "C) Terminates the program", "D) Saves data to a file"},
            {"A) myVariable", "B) _myVariable", "C) 2ndVariable", "D) myVariable2"},
            {"A) 3", "B) 5", "C) 8", "D) 53"},
            {"A) ClassName object = new ClassName();", "B) object = ClassName();", "C) ClassName object;", "D) new ClassName object();"},
            {"A) implements", "B) extends", "C) super", "D) inherit"},
            {"A) null", "B) true", "C) false", "D) 0"},
            {"A) == compares references, .equals() compares values", "B) .equals() compares references, == compares values", "C) Both do the same thing", "D) None of the above"},
            {"A) Defining multiple methods with the same name but different parameters", "B) Calling a method multiple times", "C) Overriding a method in a subclass", "D) Writing methods with the same name and parameters"},
            {"A) Hello", "B) Hello World", "C) Compilation error", "D) None of the above"},
            {"A) It marks a class as unmodifiable.", "B) It prevents a variable from being changed.", "C) It prevents method overriding.", "D) All of the above."},
            {"A) List", "B) Set", "C) Map", "D) Array"},
            {"A) Implementing the Runnable interface", "B) Extending the Thread class", "C) Using the Executors framework", "D) All of the above"},
            {"A) It will restart automatically.", "B) It will throw an OutOfMemoryError.", "C) The system will allocate more memory.", "D) The program will continue running slowly."},
            {"A) HashMap is synchronised, Hashtable is not", "B) Hashtable allows null keys, HashMap does not", "C) HashMap allows one null key, Hashtable does not allow null keys", "D) There is no difference"},
            {"A) A way to manually delete objects", "B) A process that automatically removes unused objects", "C) A memory management tool for developers", "D) A method for optimizing runtime performance"},
            {"A) Prevents a variable from being changed", "B) Ensures changes to a variable are visible to all threads", "C) Makes a variable constant", "D) Improves memory efficiency"},
            {"A) It ensures that only one thread can execute a method/block at a time", "B) It improves the speed of execution", "C) It prevents a variable from being modified", "D) It stops all other threads from executing"},
            {"A) public", "B) private", "C) protected", "D) package-private"}
    };

    private int[] answers = {1, 1, 1, 2, 2, 0, 1, 2, 0, 0, 0, 3, 1, 3, 1, 2, 1, 1, 0, 3}; //index of correct answers
    private int currentQuestionIndex = 0;
    private int score = 0;
    private CountDownTimer timer;
    private final long TIME_PER_QUESTION = 10000; /*10 seconds per question (change to 15 maybe if
    needed*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //initialise views
        questionTextView = findViewById(R.id.questionTextView);
        timerTextView = findViewById(R.id.timerTextView);
        timerProgressBar = findViewById(R.id.timerProgressBar);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        nextButton = findViewById(R.id.nextButton);

        loadQuestion();

        //set click listener for the Next button
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if an option is selected
                if (optionsRadioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(QuizActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                } else {
                    //stops the timer
                    if (timer != null) {
                        timer.cancel();
                    }
                    //go to the next question
                    currentQuestionIndex++;
                    if (currentQuestionIndex < questions.length) {
                        loadQuestion();
                    } else {
                        finish();
                    }
                }
            }
        });
    }

    private void loadQuestion() {
        questionTextView.setText(questions[currentQuestionIndex]);

        //set the options
        option1.setText(options[currentQuestionIndex][0]);
        option2.setText(options[currentQuestionIndex][1]);
        option3.setText(options[currentQuestionIndex][2]);
        option4.setText(options[currentQuestionIndex][3]);

        optionsRadioGroup.clearCheck();

        //start the timer
        startTimer();
    }

    private void startTimer() {
        timer = new CountDownTimer(TIME_PER_QUESTION, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //update the timer text and progress bar
                int progress = (int) (millisUntilFinished / 1000);
                timerTextView.setText(String.valueOf(progress));
                timerProgressBar.setProgress(progress * 10); //progress bar is max 100
            }

            @Override
            public void onFinish() {
                //time is over, go to the next question
                timerTextView.setText("0");
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.length) {
                    loadQuestion();
                } else {
                    //close activity at end of quiz
                    finish();
                }
            }
        }.start();
    }
}
