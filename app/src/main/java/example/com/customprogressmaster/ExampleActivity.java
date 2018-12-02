package example.com.customprogressmaster;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

public class ExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        bindView();
    }

    /**
     * 实例化
     */
    private void bindView() {
        CustomProgress myProgressZ = findViewById(R.id.mProgressZero);
        myProgressZ.setmShowProgress(80);
        myProgressZ.setShowProgressTime(100);
        CustomProgress myProgressO = findViewById(R.id.mProgressOne);
        myProgressO.setmShowProgress(80);
        CustomProgress myProgressT = findViewById(R.id.mProgressTwo);
        myProgressT.setmShowProgress(100);
        myProgressT.setBackgroundColor(ContextCompat.getColor(this, R.color.progress_bg6));
        myProgressT.setForeground(ContextCompat.getColor(this, R.color.progress_bg7));
        myProgressT.setText("完成");
        myProgressT.setTextColor(ContextCompat.getColor(this, R.color.progress_bg6));
        myProgressT.setTextIsDisplayable(false);
    }
}
