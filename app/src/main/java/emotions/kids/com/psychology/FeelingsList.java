package emotions.kids.com.psychology;

import android.app.Activity;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;


public class FeelingsList extends ListActivity  {

    ListView feelingsList;
    String[] names;
    BuildConfig ctx;
    ArrayList<FeelingModel> feelingsArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feelingslist);
        //feelingsList = (ListView)findViewById(R.id.feelingslist);
        feelingsList = getListView();
        //names = new String[]{"name1","name2","name3"};
        getFeelings();
        names = new String[feelingsArrayList.size()];
        for(int i =0;i<feelingsArrayList.size();i++)
        {
            names[i] = feelingsArrayList.get(i).name;
        }

        this.setListAdapter(new ArrayAdapter<String>(this,R.layout.list_item,R.id.text,names));

        feelingsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("TAG1","Click");
                FeelingModel selectedFeeling = feelingsArrayList.get(position);
                Intent intent = new Intent(FeelingsList.this,MyActivity.class);
                intent.putExtra("feelingsExtra", selectedFeeling);
                setResult(RESULT_OK, intent);
                finish();

            }
        });

    }

    private void    getFeelings() {
        DBHelper dbHelper = new DBHelper(getApplicationContext());

        feelingsArrayList  = dbHelper.retrieveFeelings();
        Log.d("TAG1","The array   "+feelingsArrayList);
    }

    //Loader methods

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_feelings_list, menu);
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


}
