package com.hesham0_0.quizgame;

import com.badlogic.gdx.math.Vector2;
import com.hesham0_0.quizgame.models.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {
    public final static float VIRTUAL_WIDTH = 1280;
    public final static float VIRTUAL_HEIGHT = 1280;
    public static List<Question> questions = Arrays.asList(
            new Question(
                    "What programming language is known for its simplicity and readability?",
                    new String[]{"Java", "Python", "C#", "Ruby"},
                    "Python"
            ),
            new Question(
                    "Which programming language is commonly used for web development?",
                    new String[]{"Java", "Python", "JavaScript", "C++"},
                    "JavaScript"
            ),
            new Question(
                    "Which programming language is used for Android app development?",
                    new String[]{"Java", "Python", "Kotlin", "Swift"},
                    "Kotlin"
            ),
            new Question(
                    "Which programming language is often used for game development?",
                    new String[]{"Java", "Python", "C#", "C++"},
                    "C++"
            ),
            new Question(
                    "Which programming language is a popular choice for data analysis and machine learning?",
                    new String[]{"Java", "Python", "R", "Scala"},
                    "Python"
            ),
            new Question(
                    "What is the primary purpose of version control systems like Git?",
                    new String[]{"Bug tracking", "Collaborative coding", "File backup", "Code versioning"},
                    "Code versioning"
            ),
            new Question(
                    "In object-oriented programming, what is a class?",
                    new String[]{"A blueprint for creating objects", "A variable that stores data", "A mathematical operation", "A programming function"},
                    "A blueprint for creating objects"
            ),
            new Question(
                    "Which data structure follows the Last In, First Out (LIFO) principle?",
                    new String[]{"Queue", "Stack", "Linked List", "Tree"},
                    "Stack"
            ),
            new Question(
                    "What does API stand for in software development?",
                    new String[]{"Application Programming Interface", "Advanced Programming Interface", "Automated Programming Integration", "Application Protocol Interface"},
                    "Application Programming Interface"
            ),
            new Question(
                    "Which programming language is used for developing iOS apps?",
                    new String[]{"Java", "Python", "Kotlin", "Swift"},
                    "Swift"
            )
    );
    public static List<String> vials = Arrays.asList(
            "Java", "Python", "Kotlin", "Swift",
            "Java", "Python", "Kotlin", "Swift",
            "Java", "Python", "Kotlin", "Swift"
    );

    public static Vector2[] positionsPortrait = new Vector2[]{
            new Vector2(VIRTUAL_WIDTH / 2, 600),
            new Vector2(VIRTUAL_WIDTH / 2, 450),
            new Vector2(VIRTUAL_WIDTH / 2, 300),
            new Vector2(VIRTUAL_WIDTH / 2, 150)
    };
    public static Vector2[] positionsLandscape = new Vector2[]{
            new Vector2(VIRTUAL_WIDTH / 4, 300),
            new Vector2(VIRTUAL_WIDTH / 4, 150),
            new Vector2(VIRTUAL_WIDTH * 3 / 4, 300),
            new Vector2(VIRTUAL_WIDTH * 3 / 4, 150)
    };
}
