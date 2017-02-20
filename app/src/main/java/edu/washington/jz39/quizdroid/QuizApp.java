package edu.washington.jz39.quizdroid;

import android.app.Application;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// This class stores the information that comes with a question
// it includes the question, 4 possible answers, and which # of the correct answer
// which can all be viewed
class Quiz implements Serializable {
    private String text;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private int correctAnswer;

    public Quiz(String text, String answer1, String answer2, String answer3, String answer4,
                int correctAnswer) {
        this.text = text;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correctAnswer = correctAnswer;
    }

    public String getText() {
        return text;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}

// this class stores all the information that comes with a topic
// this includes the topic's title, description, and list of question information
// which can all be viewed
class Topic implements Serializable {
    private String title;
    private String description;
    private List<Quiz> questions;

    public Topic(String title, String description, List<Quiz> questions) {
        this.title = title;
        this.description = description;
        this.questions = questions;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Quiz> getQuestions() {
        return questions;
    }
}


interface ITopicRepository {
    public List<Topic> getAllTopics();
}

// An implementation of a TopicRepository that has hardcoded values for the topics
class InMemoryRepository implements ITopicRepository{
    private List<Topic> topics;

    public InMemoryRepository() {
        topics = new ArrayList<Topic>();

        List<Quiz> mathQuestions = new ArrayList<Quiz>();
        List<Quiz> physicsQuestions = new ArrayList<Quiz>();
        List<Quiz> marvelQuestions = new ArrayList<Quiz>();

        mathQuestions.add(new Quiz("What is 1 + 1?", "2", "11", "12", "4", 1));
        mathQuestions.add(new Quiz("What is 1 * 2?", "2", "4", "22", "12", 1));
        physicsQuestions.add(new Quiz("What is physics?",
                "Newton's imagine", "A conspiracy", "The rules of universe",
                "idk but earth is flat!", 3));
        physicsQuestions.add(new Quiz("What is force?", "mass", "mass * acceleration",
                "speed + time", "the power of Jedi!", 2));
        marvelQuestions.add(new Quiz("Do you like batman?",
                "Yes", "Yes", "Yes",
                "No", 4));
        marvelQuestions.add(new Quiz("Then how about superman?",
                "Yes", "Yes", "Yes",
                "No", 4));


        Topic math = new Topic("Math","math math", mathQuestions);
        Topic physics = new Topic("Physics", "an apple?",
                physicsQuestions);
        Topic marvel = new Topic("Marvel Super Heroes",
                "how much you know about superheroes", marvelQuestions);

        topics.add(math);
        topics.add(physics);
        topics.add(marvel);
    }

    public List<Topic> getAllTopics() {
        return topics;
    }

    public Topic getTopicByTitle(String title) {
        for (Topic topic : topics) {
            if (topic.getTitle().equals(title)) {
                return topic;
            }
        }
        return null;
    }
}

// This is an implementation of a TopicRepository that gets it's topic information from
// the given input stream JSON file
class JSONRepository implements ITopicRepository {
    List<Topic> topics;

    public JSONRepository(String json) {
        topics = new ArrayList<Topic>();


        try {
            JSONArray topics= new JSONArray(json);

            // looks through each topic and parses the JSON to Quiz/Topic objects
            for (int i = 0; i < topics.length(); i++) {
                JSONObject obj = topics.getJSONObject(i);
                String title = obj.getString("title");
                String desc = obj.getString("desc");

                List<Quiz> topicQuestions = new ArrayList<Quiz>();
                JSONArray questions = obj.getJSONArray("questions");

                // looks through each question in a topic and adds it to its list of topic questions
                for (int j = 0; j < questions.length(); j++) {
                    JSONObject jsonQuestion = questions.getJSONObject(j);
                    String question = jsonQuestion.getString("text");
                    int answer = jsonQuestion.getInt("answer");
                    JSONArray answers = (JSONArray) jsonQuestion.get("answers");

                    String question1 = answers.get(0).toString();
                    String question2 = answers.get(1).toString();
                    String question3 = answers.get(2).toString();
                    String question4 = answers.get(3).toString();

                    Quiz quiz = new Quiz(question, question1, question2, question3,
                            question4, answer);

                    topicQuestions.add(quiz);
                }

                this.topics.add(new Topic(title, desc, topicQuestions));
            }
        } catch (JSONException error) {
            error.printStackTrace();
        }
    }

    public List<Topic> getAllTopics() {
        return topics;
    }
}

public class QuizApp extends Application {

    private static QuizApp instance = null;

    private ITopicRepository repo;

    public QuizApp() {
        if (instance == null) {
            instance = this;
        } else {
            throw new RuntimeException("Cannot create more than one QuizApp");
        }
    }

    @Override
    public void onCreate() {
        Log.i("QuizApp", "onCreate() called");
        super.onCreate();

        try { // gets the JSON and creates a new TopicRepository
            InputStream inputStream = getAssets().open("questions.json");
            String json = readJSONFile(inputStream);
            repo = new JSONRepository(json);
        } catch (IOException e) {
            e.printStackTrace();
            repo = new InMemoryRepository(); // if JSON fails, backup to use InMemoryRepo
        }

    }

    public List<Topic> getAllTopics() {
        return repo.getAllTopics();
    }

    // reads given InputStream of JSON file and returns it in string format
    private String readJSONFile(InputStream inputStream) throws IOException {
        int size = inputStream.available();
        byte[] buffer = new byte[size];
        inputStream.read(buffer);
        inputStream.close();

        return new String(buffer, "UTF-8");
    }
}