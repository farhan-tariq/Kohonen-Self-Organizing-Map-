package Controllers.Learning.MCQ;

import java.util.ArrayList;

public class Answers {
    private static ArrayList<ArrayList<String>> questionAnswers = new ArrayList<>();

    public static ArrayList<ArrayList<String>> getAnswers() {
        for (int i = 0; i < 10; i++) {
            ArrayList<String> arrayList = new ArrayList<>();
            questionAnswers.add(arrayList);
        }

        questionAnswers.get(0).add("On what parameters can change in weight vector depend?");
        questionAnswers.get(0).add("all of the mentioned");

        questionAnswers.get(1).add("If the change in weight vector is represented by ∆wij, what does it mean?");
        questionAnswers.get(1).add("describes the change in weight vector for ith processing unit, taking input vector jth into account");

        questionAnswers.get(2).add("What is learning signal in this equation ∆wij= µf(wi a)aj?");
        questionAnswers.get(2).add("f(wi a)");

        questionAnswers.get(3).add("State whether Hebb’s law is supervised learning or of unsupervised type?");
        questionAnswers.get(3).add("No desired output is required for it’s implementation.");

        questionAnswers.get(4).add("Hebb’s law can be represented by equation?");
        questionAnswers.get(4).add("both way");

        questionAnswers.get(5).add("Delta learning is of unsupervised type?");
        questionAnswers.get(5).add("no");

        questionAnswers.get(6).add("In hebbian learning initial weights are set?");
        questionAnswers.get(6).add("near to zero");

        questionAnswers.get(7).add("What conditions are must for competitive network to perform feature mapping?");
        questionAnswers.get(7).add("none of the mentioned fulfils the whole criteria");

        questionAnswers.get(8).add("If a competitive network can perform feature mapping then what is that network can be called?");
        questionAnswers.get(8).add("self organization");

        questionAnswers.get(9).add("How is weight vector adjusted in basic competitive learning?");
        questionAnswers.get(9).add("such that it moves towards the input vector");

        return questionAnswers;

    }
}
