package edu.washington.jz39.quizdroid;


import java.io.Serializable;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Queue;

public class Questions implements Serializable {
    String topic;
    Queue<Question> qs;

    public Questions(String topic){
        this.topic = topic;
        getTopics(topic);

    }

    public Queue<Question> getQs(){
        return qs;
    }

    public Question getQ(){
        if(!qs.isEmpty())
            return qs.poll();
        throw new EmptyStackException();
    }

    public String getTopic(){
        return topic;
    }

    public void getTopics(String s){
        if(s.equals("Math"))
            qs = getMathQuestions();
        else if(s.equals("Physics"))
            qs = getPhysicsQuestions();
        else if(s.equals("Marvel Super Heroes"))
            qs = getMarvelQuestions();
    }

    public Queue<Question> getMathQuestions() {
        Queue<Question> questions = new LinkedList<Question>();
        questions.offer(new Question("answer 1", new String[] {"1", "2", "3", "4"}, "1"));
        questions.offer(new Question("answer 1", new String[] {"1", "2", "3", "4"}, "1"));
        questions.offer(new Question("answer 1", new String[] {"1", "2", "3", "4"}, "1"));
        return questions;
    }

    public Queue<Question> getPhysicsQuestions() {
        Queue<Question> questions = new LinkedList<Question>();
        questions.offer(new Question("answer 1", new String[] {"1", "2", "3", "4"}, "1"));
        questions.offer(new Question("answer 1", new String[] {"1", "2", "3", "4"}, "1"));
        questions.offer(new Question("answer 1", new String[] {"1", "2", "3", "4"}, "1"));
        return questions;
    }

    public Queue<Question> getMarvelQuestions() {
        Queue<Question> questions = new LinkedList<>();
        questions.offer(new Question("answer 1", new String[] {"1", "2", "3", "4"}, "1"));
        questions.offer(new Question("answer 1", new String[] {"1", "2", "3", "4"}, "1"));
        questions.offer(new Question("answer 1", new String[] {"1", "2", "3", "4"}, "1"));
        return questions;
    }

    class Question implements Serializable{
        private String question;
        private String answer;
        private String[] options;


        public Question(String question, String[] options, String answer) {
            this.question = question;
            this.answer = answer;
            this.options = options;
        }

        public String getQuestion() {
            return question;
        }

        public String getAnswer() {
            return answer;
        }

        public String getOption(int i){
            return options[i];
        }
    }

}
