package course.example.modernartui;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends ActionBarActivity implements SeekBar.OnSeekBarChangeListener {
    View imageViewR,imageViewG,imageViewB,imageViewRG,imageViewGB,imageViewRGB;
    Menu menu;
    private final int IDD_EXIT=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
        imageViewR=(View) findViewById(R.id.imageView1);
        imageViewG=(View) findViewById(R.id.imageView2);
        imageViewB=(View) findViewById(R.id.imageView3);

        imageViewRG=(View) findViewById(R.id.imageView4);
        imageViewGB=(View) findViewById(R.id.imageView5);

        imageViewRGB=(View) findViewById(R.id.imageView6);

        SeekBar seekBar=(SeekBar) findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(this);

		//set the beggining colors to all imageViews
        imageViewR.setBackgroundColor(Color.rgb(0, 175, 100));
        imageViewG.setBackgroundColor(Color.rgb(175,0,50));
        imageViewB.setBackgroundColor(Color.rgb(175,100,0));
        imageViewRG.setBackgroundColor(Color.rgb(0,0,200));
        imageViewGB.setBackgroundColor(Color.rgb(50,0,0));
        imageViewRGB.setBackgroundColor(Color.rgb(255, 255, 255));
//        setHasOptionsMenu(true);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.show();



    }
	//Changing the colors of all blocks depending on seekBar's position
    @Override
    public void onStopTrackingTouch(SeekBar seekBar){
        int progresser= Integer.valueOf(seekBar.getProgress())*255/100;
        imageViewR.setBackgroundColor(Color.rgb(progresser, 175, 100));
        imageViewG.setBackgroundColor(Color.rgb(175,progresser,50));
        imageViewB.setBackgroundColor(Color.rgb(175,100,progresser));
        imageViewRG.setBackgroundColor(Color.rgb(progresser,progresser,200));
        imageViewGB.setBackgroundColor(Color.rgb(50,progresser,progresser));
        imageViewRGB.setBackgroundColor(Color.rgb(255-progresser, 255-progresser, 255-progresser));
    }
	//View changing of the colors of all blocks depending on seekBar's position
    @Override
    public void onProgressChanged(SeekBar seekBar,int progress,boolean fromUser){
        int progresser= Integer.valueOf(seekBar.getProgress())*255/100;
        imageViewR.setBackgroundColor(Color.rgb(progresser, 175, 100));
        imageViewG.setBackgroundColor(Color.rgb(175,progresser,50));
        imageViewB.setBackgroundColor(Color.rgb(175,100,progresser));
        imageViewRG.setBackgroundColor(Color.rgb(progresser,progresser,200));
        imageViewGB.setBackgroundColor(Color.rgb(50,progresser,progresser));
        imageViewRGB.setBackgroundColor(Color.rgb(255-progresser, 255-progresser, 255-progresser));
    }
    @Override
    public void onStartTrackingTouch(SeekBar seekBar){
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
			//dialog that send user to MOMA site
            case IDD_EXIT:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("More information");
                builder.setPositiveButton("Visit MOMA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.moma.org/"));
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Not Now", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setCancelable(false);
                return builder.create();
            default:
                return null;

        }


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            showDialog(IDD_EXIT);
        }

        return super.onOptionsItemSelected(item);
    }
}
