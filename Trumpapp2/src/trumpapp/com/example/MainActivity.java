package trumpapp.com.example;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends ActionBarActivity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Intent intent = getIntent();
        
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }
    
    //@Override
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    
    
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }
        
        CardDrawer mCardDrawer = new CardDrawer();
        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            RelativeLayout layout = (RelativeLayout) rootView;
            //final ImageView imageView1 = (ImageView) layout.findViewById(R.id.imageView1);
            final Button shuffle = (Button) layout.findViewById(R.id.shuffle);
            final Button button2 = (Button) layout.findViewById(R.id.button2);
            final ImageButton[] tehuda1 =new ImageButton[26]; //人数によって変えるor動的に作成できるようにする
//            final ImageView[] tehuda2 =new ImageView[26];
            
            
            int[] cardsIDarray = new int[] {R.id.Hand1, R.id.Hand2,R.id.Hand3, R.id.Hand4, R.id.Hand5, R.id.Hand6,
            								R.id.Hand7,R.id.Hand8, R.id.Hand9,R.id.Hand10, R.id.Hand11, R.id.Hand12,
            								R.id.Hand13,R.id.Hand14, R.id.Hand15, R.id.Hand16, R.id.Hand17, R.id.Hand18,
            								R.id.Hand19,R.id.Hand20, R.id.Hand21, R.id.Hand22,R.id.Hand23, R.id.Hand24,
            								R.id.Hand25,R.id.Hand26 };
           
            for(int i=0;i<26;i++)
              tehuda1[i] = (ImageButton)layout.findViewById(cardsIDarray[i]);
//            for(int i=0;i<26;i++)
//                tehuda2[i] = (ImageView)layout.findViewById(cardsIDarray[i+26]);
            
            //親が子にカードを配る（初めに実行される操作）
            shuffle.setOnClickListener(new OnClickListener() {       
				@Override
				public void onClick(View v) {
				 TypedArray cardsArray = getResources().obtainTypedArray(R.array.cards);
				 
				 mCardDrawer.deckReset(); 
				 mCardDrawer.handReset();
				 mCardDrawer.fieldReset();
//				 Log.d("OK "+mCardDrawer.deck[0],"success" + mCardDrawer.deck[2]);
				 mCardDrawer.distribute(2); //取得した任意のメンバー数
//				 Log.d("OK","successSet");
				 for(int i=0;i<26;i++){
				  Drawable drawable = cardsArray.getDrawable(mCardDrawer.Hand[i]);
				  tehuda1[i].setImageDrawable(drawable);
				 }
				}
			});           
            
            tehuda1[0].setOnClickListener(new OnClickListener(){
            	@Override
				public void onClick(View v) {
            		
            	}
            });
            
            return rootView;
        }
    }
}
