package booster.co.nz.fundsinvestinvestigation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EntryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

    }

    public void enterDescritpionScreen(final View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
