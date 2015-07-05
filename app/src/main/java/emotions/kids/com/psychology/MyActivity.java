package emotions.kids.com.psychology;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

import widget.VerticalSeekBar;
import widget.VerticalSeekBar_Reverse;


public class MyActivity extends Activity implements View.OnClickListener, View.OnLongClickListener,View.OnTouchListener,View.OnDragListener {


    final int REQUEST_IMAGE_CAPTURE = 1;
    final int REQUEST_CHOOSE_IMAGE = 2;
    final int REQUEST_FETCH_FEELINGS = 3;

    RelativeLayout.LayoutParams layoutParams;

    VerticalSeekBar verticalSeekBar=null;
    VerticalSeekBar_Reverse verticalSeekBar_Reverse=null;
    TextView vsProgress,vs_reverseProgress=null,feelingText;
    String[] dialogItems = new String[]{"Choose Picture","Click Picture","Choose Emoticon"};
    ImageView happy_1,happy_2,happy_3,sad_1,sad_2,sad_3,shy_1,shy_2,shy_3,worry_1,worry_2,worry_3,angry_1,angry_2,angry_3;
    String title,topImagePath,middleImagePath,bottomImagePath;
    byte[] topImageBytes,middleImageBytes,bottomImageBytes;

    EditText titleText;
    MenuItem saveMenuItem;
    int imageNo;
    SeekBar thermometer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        /*verticalSeekBar=(VerticalSeekBar)findViewById(R.id.vertical_Seekbar);
        verticalSeekBar_Reverse=(VerticalSeekBar_Reverse)findViewById(R.id.seekbar_reverse);
        vsProgress=(TextView)findViewById(R.id.vertical_sb_progresstext);
        vs_reverseProgress=(TextView)findViewById(R.id.reverse_sb_progresstext);
*/

        feelingText = (TextView)findViewById(R.id.text);
        titleText = (EditText)findViewById(R.id.title);

        happy_1 = (ImageView)findViewById(R.id.happy_1);
        happy_2 = (ImageView)findViewById(R.id.happy_2);
        happy_3 = (ImageView)findViewById(R.id.happy_3);
        sad_1 = (ImageView)findViewById(R.id.sad_1);
        sad_2 = (ImageView)findViewById(R.id.sad_2);
        sad_3 = (ImageView)findViewById(R.id.sad_3);
        shy_1 = (ImageView)findViewById(R.id.shy_1);
        shy_2 = (ImageView)findViewById(R.id.shy_2);
        shy_3 = (ImageView)findViewById(R.id.shy_3);
        angry_1 = (ImageView)findViewById(R.id.angry_1);
        angry_2 = (ImageView)findViewById(R.id.angry_2);
        angry_3 = (ImageView)findViewById(R.id.angry_3);
        worry_1 = (ImageView)findViewById(R.id.worry_1);
        worry_2 = (ImageView)findViewById(R.id.worry_2);
        worry_3 = (ImageView)findViewById(R.id.worry_3);
        happy_1.setTag("ImageTag");
        happy_1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(happy_1);
                //Toast.makeText(getApplicationContext(), "Long touch", Toast.LENGTH_LONG).show();
                v.startDrag(dragData, myShadow, null, 0);
                return false;
            }
        });
        happy_1.setOnTouchListener(this);

        happy_1.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {


                int action = event.getAction();
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:

                        layoutParams = (RelativeLayout.LayoutParams) v
                                .getLayoutParams();
                        Toast.makeText(getApplicationContext(),"Started",Toast.LENGTH_LONG).show();
                        // do nothing

                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:

                        int x1 = (int) event.getX();
                        int y1 = (int) event.getY();
                        Log.d("TAG1","The position at entry X "+x1+" Y "+y1);
                        //v.setBackgroundDrawable(enterShape);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:

                        int x = (int) event.getX();
                        int y = (int) event.getY();
                        layoutParams.leftMargin = x;
                        layoutParams.topMargin = y;
                        v.setLayoutParams(layoutParams);
                        Log.d("TAG1","The position at exit X "+x+" Y "+y);
                        //v.setBackgroundDrawable(normalShape);
                        break;
                    case DragEvent.ACTION_DROP:
                        // Dropped, reassign View to ViewGroup
                        Toast.makeText(getApplicationContext(),"Drop",Toast.LENGTH_LONG).show();
//                        View view = (View) event.getLocalState();
//                        ViewGroup owner = (ViewGroup) view.getParent();
//                        owner.removeView(view);
//                        LinearLayout container = (LinearLayout) v;
//                        container.addView(view);
//                        view.setVisibility(View.VISIBLE);
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:

                        //v.setBackgroundDrawable(getResources().getDrawable(R.drawable.happy_1));
                    default:
                        break;
                }
                return true;

            }
        });
        happy_2.setOnLongClickListener(this);
        happy_2.setOnTouchListener(this);
        happy_2.setOnDragListener((View.OnDragListener) this);


        happy_3.setOnLongClickListener(this);
        happy_3.setOnTouchListener(this);

        sad_1.setOnLongClickListener(this);
        sad_1.setOnTouchListener(this);

        sad_2.setOnLongClickListener(this);
        sad_2.setOnTouchListener(this);

        sad_3.setOnLongClickListener(this);
        sad_3.setOnTouchListener(this);

        shy_1.setOnLongClickListener(this);
        shy_1.setOnTouchListener(this);

        shy_2.setOnLongClickListener(this);
        shy_2.setOnTouchListener(this);

        shy_3.setOnLongClickListener(this);
        shy_3.setOnTouchListener(this);


        worry_1.setOnTouchListener(this);
        worry_1.setOnLongClickListener(this);

        worry_2.setOnTouchListener(this);
        worry_2.setOnLongClickListener(this);

        worry_3.setOnTouchListener(this);
        worry_3.setOnLongClickListener(this);






        thermometer =(SeekBar)findViewById(R.id.thermometer);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FFFFFF"));
        ActionBar acBar = this.getActionBar();
        thermometer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d("TAG1","The Progress  "+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //acBar.setBackgroundDrawable(colorDrawable);


    }

    public void AddNew(MenuItem item){
        //Toast.makeText(this,"Toast add", Toast.LENGTH_LONG).show();
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Add New");
        final LayoutInflater inflater = this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.adddialog,null);
        dialogBuilder.setView(view);
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        dialogBuilder.setPositiveButton("Save",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(getApplicationContext(),"Added",Toast.LENGTH_LONG).show();
                titleText = (EditText)view.findViewById(R.id.title);
                Log.d("TAG1","  "+titleText.getText().toString());
                title = titleText.getText().toString();
                feelingText.setText(title);
                saveMenuItem.setVisible(true);

                dialog.cancel();



            }
        });
        dialogBuilder.show();
    }
    public void list(MenuItem item) {

        Intent intent = new Intent(this,FeelingsList.class);
        startActivityForResult(intent,REQUEST_FETCH_FEELINGS);

    }

    public void save(MenuItem item) {
        Toast.makeText(getApplicationContext(),"Save",Toast.LENGTH_LONG).show();
        Log.d("TAG1","Save");
        FeelingModel feelings = new FeelingModel();
        feelings.name = feelingText.getText().toString();
        feelings.desc = "DESC";
        feelings.image1Bytes = topImageBytes;
        feelings.image2Bytes = middleImageBytes;
        feelings.image3Bytes = bottomImageBytes;
        feelings.temp = thermometer.getProgress();
        Log.d("TAG1","Save  "+feelings.name);
        DBHelper db = new DBHelper(getApplicationContext());
        db.insertFeelings(feelings);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        saveMenuItem = menu.findItem(R.id.save);
        saveMenuItem.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.addButton) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void frame_button_click(View v){
        Toast.makeText(MyActivity.this, "the y  before "+v.getY(), Toast.LENGTH_SHORT).show();
        FrameLayout fl = (FrameLayout)findViewById(R.id.FlashBarLayout);
        fl.setY(fl.getY()-200);
        Toast.makeText(MyActivity.this, "the y  after "+v.getY(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {

    }


    private void imageDialog(View v) {

        LayoutInflater inflater = this.getLayoutInflater();

        Log.d("TAG1","dialog");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Pic")
               .setItems(dialogItems,new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int item) {
                       Intent intent;
                       switch (item)
                       {
                           case 0 :
                               intent= new Intent();//(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                               intent.setType("image/*");
                               intent.setAction(Intent.ACTION_GET_CONTENT);
//                               intent.addCategory(Intent.CATEGORY_OPENABLE);
                               startActivityForResult(intent, REQUEST_CHOOSE_IMAGE);
                               break;
                           case 1 :
                               intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                           if (intent.resolveActivity(getPackageManager()) != null) {
                                       startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                               }

                               break;
                           case 2:
                               break;
                       }
                   }
               });
        builder.create();
        builder.show();
        Log.d("TAG1","dialog show");
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //ByteArrayOutputStream bos;
        switch (requestCode)
        {
            case REQUEST_IMAGE_CAPTURE :

                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                //imageBitmap.setWidth(50);
                //imageBitmap.setHeight(50);
                //imgView.setImageBitmap(Bitmap.createScaledBitmap(imageBitmap,500,240,false));
//                bos=new ByteArrayOutputStream();
//                imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
//                topImageBytes=bos.toByteArray();
                Uri currImageURI = data.getData();
                Log.d("TAG1","Image "+extras);

                mapImagePath(imageBitmap);


                //mImageView.setImageBitmap(imageBitmap);

                break;
            case REQUEST_CHOOSE_IMAGE :
                Uri selectedImage = data.getData();
                //Bundle extras = data.getExtras();
                Log.d("TAG1","data  "+data.getData());
                //Toast.makeText(getApplicationContext(),"Toast  "+data.getData(),Toast.LENGTH_LONG).show();
                //getPathFromURI((Uri)data.getData());
                //Bitmap imageBitmap = (Bitmap)extras.get("data");
                try {
                    InputStream stream = getContentResolver().openInputStream(
                            data.getData());
                   Bitmap bitmap = BitmapFactory.decodeStream(stream);

                    //imgView.setImageBitmap(Bitmap.createScaledBitmap(bitmap,440,150,false));

                    mapImagePath(bitmap);

//                    bos=new ByteArrayOutputStream();
//                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
//                    //Bitmap b1=BitmapFactory.decodeByteArray(img, 0, img.length);
//                    topImageBytes=bos.toByteArray();
                    stream.close();


//                    Uri pickedImage = data.getData();
//                    // Let's read picked image path using content resolver
//                    String[] filePath = { MediaStore.Images.Media.DATA };
//                    Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
//                    cursor.moveToFirst();
//                    String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));
//                    Bitmap image = BitmapFactory.decodeFile(imagePath);
//                    image = Bitmap.createScaledBitmap(image,50,50,true);
                    // Now we need to set the GUI ImageView data with data read from the picked file.
//                    imgView.setImageBitmap(image);
//                    Log.d("TAG1","Image "+imagePath);
//                    mapImagePath(imagePath);
//                    // At the end remember to close the cursor or you will end with the RuntimeException!
//                    cursor.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case REQUEST_FETCH_FEELINGS:

                FeelingModel selectedFeelings = (FeelingModel)getIntent().getSerializableExtra("feelingsExtra");
                feelingText.setText(selectedFeelings.name);
                thermometer.setProgress(selectedFeelings.temp);

                break;
        }
    }

    private void mapImagePath(Bitmap imageBitmap) {
        Log.d("TAG1","Map Image ");
        ByteArrayOutputStream bos;
        bos=new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        switch(imageNo)
        {
            case 1:
                //topImagePath = imagePath;

                //Bitmap b1=BitmapFactory.decodeByteArray(img, 0, img.length);
                topImageBytes=bos.toByteArray();
                break;
            case 2:
                //middleImagePath = imagePath;
                middleImageBytes =bos.toByteArray();
                break;
            case 3:
                //bottomImagePath = imagePath;
                bottomImageBytes=bos.toByteArray();
                break;
        }

    }

//    private String getPathFromURI(Uri currImageURI) {
//        String [] projection={MediaStore.Images.Media.DATA};
//        Log.d("TAG1","Current   "+currImageURI+"Projection    "+projection);
//        Cursor cursor = getContentResolver().query(currImageURI, projection, null, null,null);
//
////        Cursor cursor = managedQuery( currImageURI,
////                proj, // Which columns to return
////                null,       // WHERE clause; which rows to return (all rows)
////                null,       // WHERE clause selection arguments (none)
////                null); // Order-by clause (ascending by name)
//        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        Toast.makeText(getApplicationContext(),"Toast  "+cursor.getString(column_index),Toast.LENGTH_LONG).show();
//        return cursor.getString(column_index);
//    }

    public File createFileInSDCard(String path,String fileName) {
        File dir = new File(path);
        try {
            if (!dir.exists() && dir.mkdirs()) {
                System.out.println("Directory created");
            } else {
                System.out.println("Directory is not created");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        File file = null;
        try {
            if (dir.exists()) {
                file = new File(dir, fileName);
                file.createNewFile();
            } else {

            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return file;
    }

    public static String getTempFile() {
        return Environment.getExternalStorageDirectory().getPath()    + "/kids/";

    }


    @Override
    public boolean onLongClick(View v) {
        v.setTag("ImageTag");
        ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

        ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
        View.DragShadowBuilder myShadow = new View.DragShadowBuilder();
        Toast.makeText(getApplicationContext(),"Long touch",Toast.LENGTH_LONG).show();
        v.startDrag(dragData,myShadow,null,0);
        return false;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {

//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//
//
//            ClipData data = ClipData.newPlainText("", "");
//            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
//            view.startDrag(data, shadowBuilder, view, 0);
//            view.setVisibility(View.INVISIBLE);
//            return true;
//        } else
        return false;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        int action = event.getAction();
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:


                //Toast.makeText(getApplicationContext(),"Started",Toast.LENGTH_LONG).show();
                // do nothing

                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                //v.setBackgroundDrawable(enterShape);
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                //v.setBackgroundDrawable(normalShape);
                break;
            case DragEvent.ACTION_DROP:
                // Dropped, reassign View to ViewGroup
                Toast.makeText(getApplicationContext(),"Drop",Toast.LENGTH_LONG).show();
                View view = (View) event.getLocalState();
                ViewGroup owner = (ViewGroup) view.getParent();
                owner.removeView(view);
                LinearLayout container = (LinearLayout) v;
                container.addView(view);
                view.setVisibility(View.VISIBLE);
                break;
            case DragEvent.ACTION_DRAG_ENDED:

                //v.setBackgroundDrawable(getResources().getDrawable(R.drawable.happy_1));
            default:
                break;
        }
        return true;
    }
}
