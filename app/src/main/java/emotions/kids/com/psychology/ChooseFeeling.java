package emotions.kids.com.psychology;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class ChooseFeeling extends Activity {

    EditText titeText;
    final static String feelingType = "feelingType";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_feeling);

        titeText = (EditText)findViewById(R.id.feelings_title);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choose_feeling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void chooseClick(View v){

        Log.d("TAG1"," "+titeText.getText().toString().length());
        if(titeText.getText().toString().length()>0) {
            Intent resultIntent = new Intent();
            String title = titeText.getText().toString();
            resultIntent.putExtra(feelingType,v.getId());
            resultIntent.putExtra("title", title);
            setResult(RESULT_OK, resultIntent);
            finish();

        }
        else
            Toast.makeText(getApplicationContext(),"Please ener how you feel",Toast.LENGTH_LONG).show();
    }
}
