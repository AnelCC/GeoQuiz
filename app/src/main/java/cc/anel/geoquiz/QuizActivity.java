package cc.anel.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "id";

    private Button btn_true;
    private Button btn_false;
    private Button btn_previous;
    private Button btn_next;
    private TextView txt_question;

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Log.d(TAG, "onCreate: ");

        btn_true        =   (Button) findViewById(R.id.btn_true);
        btn_false       =   (Button) findViewById(R.id.btn_false);
        btn_previous    =   (Button) findViewById(R.id.btn_previous);
        btn_next        =   (Button) findViewById(R.id.btn_next);
        txt_question    =   (TextView) findViewById(R.id.txt_main_question);

        if (savedInstanceState!=null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0);
        }
        updateQuestion();


        btn_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick True: Here is a anonymous class in new on click listener");
                /*Toast toast= Toast.makeText(QuizActivity.this, R.string.toast_correct, Toast.LENGTH_SHORT);
                toast.setGravity(1,3,3);
                toast.show();*/
                checkAnswer(false);
            }
        });
        btn_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Log.d(TAG, "onClick False: Here is a anonymous class in new on click listener");
                Toast.makeText(QuizActivity.this, R.string.toast_incorrect, Toast.LENGTH_SHORT).show();*/
                checkAnswer(false);
            }
        });

        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex<0){
                    mCurrentIndex = (mCurrentIndex-1) % mQuestionsBank.length;
                    updateQuestion();
                }
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex+1) % mQuestionsBank.length;
                updateQuestion();
            }
        });

    }

    private void checkAnswer(boolean userPresendTrue){
        boolean anwerIsTrue = mQuestionsBank[mCurrentIndex].isAnswerTrue();
        int messengerResId = 0;

        if (userPresendTrue == anwerIsTrue){
            messengerResId = R.string.toast_correct;
        }else {
            messengerResId = R.string.toast_incorrect;
        }
        Toast.makeText(QuizActivity.this, messengerResId, Toast.LENGTH_SHORT).show();
    }


    //Encapsulating code
    private void updateQuestion(){
        int question = mQuestionsBank[mCurrentIndex].getTextResId();
        txt_question.setText(question);
    }

    private Question[] mQuestionsBank = new Question[]{
            new Question(R.string.question_africa, true),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
            new Question(R.string.question_australia, true),
            new Question(R.string.question_mideast, true),
            new Question(R.string.question_oceans, true)
    };


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");
        outState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
