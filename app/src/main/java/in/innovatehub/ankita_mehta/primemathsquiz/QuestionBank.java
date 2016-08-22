package in.innovatehub.ankita_mehta.primemathsquiz;

import android.util.Log;

import java.util.Random;

/**
 * Created by ankita_mehta on 8/18/16.
 */
public class QuestionBank {

    private int mQuestion;
    private boolean mTrueQuestion;
    /* Stores prime numbers between 1 and 1000*/
    static final int mPrimeNum[] = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997};
    /* Other variables*/
    private int mRandNum;
    private String m_Rand_n;
    /* static text variables*/
    private static final String TAG = "Math_Quiz";

    public QuestionBank(){
        Log.d(TAG, "Inner class");
    }

    public QuestionBank(int question, boolean trueQuestion){
        this.mQuestion = question;
        this.mTrueQuestion = trueQuestion;
    }

    /* This function uses Random function to generate a random number, then uniformly chooses a prime number */
    public String numberToSet() {
        Log.d(TAG, "Generating Number");
        Random r = new Random();
        int i = r.nextInt(1000) + 1;
        if (i < 500) {
            mRandNum = i;
        } else {
            mRandNum = mPrimeNum[(i % mPrimeNum.length)];
        }
        m_Rand_n = String.valueOf(mRandNum);
        return m_Rand_n;
    }

    /* This function checks if generated random number is prime or not */
    public boolean isPrime(String randnum) {
        Log.d(TAG, "checking if prime Number");
        int randnum_ =  Integer.parseInt(randnum);
        for (int i = 2; i < Math.sqrt(randnum_); i++) {
            if (randnum_ % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int getQuestion() {
        return mQuestion;
    }

    public void setQuestion(int question) {
        this.mQuestion = question;
    }

    public boolean isTrueQuestion() {
        return mTrueQuestion;
    }

    public void setTrueQuestion(boolean trueQuestion) {
        this.mTrueQuestion = trueQuestion;
    }
}
