package in.innovatehub.ankita_mehta.primemathsquiz;

import java.util.Random;

/**
 * Created by ankita_mehta on 8/18/16.
 */
public class QuestionBank {

    private int question;
    private boolean trueQuestion;
    /* Stores prime numbers between 1 and 1000*/
    static final int primeNum[] = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997};
    /* Other variables*/
    private int randnum;
    private String randn;

    public QuestionBank(){

    }

    public QuestionBank(int question, boolean trueQuestion){
        this.question = question;
        this.trueQuestion = trueQuestion;
    }

    /* This function uses Random function to generate a random number, then uniformly chooses a prime number */
    public String numberToSet() {
        Random r = new Random();
        int i = r.nextInt(1000) + 1;
        if (i < 500) {
            randnum = i;
        } else {
            randnum = primeNum[(i % primeNum.length)];
        }
        randn = String.valueOf(randnum);
        return randn;
    }

    /* This function checks if generated random number is prime or not */
    public boolean isPrime(String randnum) {
        int randnum_ =  Integer.parseInt(randnum);
        for (int i = 2; i < Math.sqrt(randnum_); i++) {
            if (randnum_ % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public boolean isTrueQuestion() {
        return trueQuestion;
    }

    public void setTrueQuestion(boolean trueQuestion) {
        this.trueQuestion = trueQuestion;
    }
}
