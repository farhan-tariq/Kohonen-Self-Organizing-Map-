package Controllers.Learning.MCQ;

import java.util.ArrayList;

public class Questions {

    private static ArrayList<ArrayList<String>> questionOptions = new ArrayList<>();


    public static ArrayList<ArrayList<String>> setQuiz() {

        for (int i = 0; i < 10; i++) {
            ArrayList<String> arrayList = new ArrayList<>();
            questionOptions.add(arrayList);
        }

        questionOptions.get(0).add("On what parameters can change in weight vector depend?");
        questionOptions.get(0).add("learning parameters");
        questionOptions.get(0).add("input vector");
        questionOptions.get(0).add("learning signal");
        questionOptions.get(0).add("all of the mentioned");


        questionOptions.get(1).add("If the change in weight vector is represented by ∆wij, what does it mean?");
        questionOptions.get(1).add("describes the change in weight vector for ith processing unit, taking input vector jth into account");
        questionOptions.get(1).add("describes the change in weight vector for jth processing unit, taking input vector ith into account");
        questionOptions.get(1).add("describes the change in weight vector for jth & ith processing unit.");
        questionOptions.get(1).add("none of the mentioned");

        questionOptions.get(2).add("What is learning signal in this equation ∆)wij= µf(wi a)aj?");
        questionOptions.get(2).add("µ");
        questionOptions.get(2).add("wi a");
        questionOptions.get(2).add("aj");
        questionOptions.get(2).add("f(wi a)");

        questionOptions.get(3).add("State whether Hebb’s law is supervised learning or of unsupervised type?");
        questionOptions.get(3).add("supervised");
        questionOptions.get(3).add("unsupervised");
        questionOptions.get(3).add("either supervised or unsupervised");
        questionOptions.get(3).add("can be both supervised & unsupervised");

        questionOptions.get(4).add(" Hebb’s law can be represented by equation?");
        questionOptions.get(4).add("∆wij= µf(wi a)aj");
        questionOptions.get(4).add("∆wij= µ(si) aj, where (si) is output signal of ith input");
        questionOptions.get(4).add("both way");
        questionOptions.get(4).add("none of the mentioned");

        questionOptions.get(5).add("Delta learning is of unsupervised type?");
        questionOptions.get(5).add("yes");
        questionOptions.get(5).add("no");
        questionOptions.get(5).add("Dont Know");
        questionOptions.get(5).add("neither");

        questionOptions.get(6).add("In hebbian learning initial weights are set?");
        questionOptions.get(6).add("random");
        questionOptions.get(6).add("near to zero");
        questionOptions.get(6).add("near to target value");
        questionOptions.get(6).add("near to target value");

        questionOptions.get(7).add("What conditions are must for competitive network to perform feature mapping?");
        questionOptions.get(7).add("non linear output layers");
        questionOptions.get(7).add("connection to neighbours is excitatory and to the farther units inhibitory");
        questionOptions.get(7).add("on centre off surround connections");
        questionOptions.get(7).add("none of the mentioned fulfils the whole criteria");

        questionOptions.get(8).add("If a competitive network can perform feature mapping then what is that network can be called?");
        questionOptions.get(8).add("self excitatory");
        questionOptions.get(8).add("self organization");
        questionOptions.get(8).add("self inhibitory");
        questionOptions.get(8).add("none of the mentioned");

        questionOptions.get(9).add(" How is weight vector adjusted in basic competitive learning?");
        questionOptions.get(9).add("such that it moves towards the input vector");
        questionOptions.get(9).add("such that it moves away from input vector");
        questionOptions.get(9).add("such that it moves towards the output vector");
        questionOptions.get(9).add("such that it moves away from output vector");

        return questionOptions;

    }
}
