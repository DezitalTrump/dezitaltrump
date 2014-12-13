package trumpapp.com.example;

import android.annotation.TargetApi;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	// @Override

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

		View.OnClickListener HandOnClickListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				v.setVisibility(View.GONE);

			}
		};

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			RelativeLayout layout = (RelativeLayout) rootView;
			Button shuffle = (Button) layout.findViewById(R.id.shuffle);
			Button button2 = (Button) layout.findViewById(R.id.button2);
			// final ImageButton[] tehuda1 =new ImageButton[26];
			// //人数によって変えるor動的に作成できるようにする
			final ImageButton[] Hand = new ImageButton[54];
			final LinearLayout HandList = (LinearLayout) layout
					.findViewById(R.id.HandList);

			// final ImageView[] tehuda2 =new ImageView[26];

			//
			final int[] cardsIDarray = new int[] { R.id.Hand1, R.id.Hand2,
					R.id.Hand3, R.id.Hand4, R.id.Hand5, R.id.Hand6, R.id.Hand7,
					R.id.Hand8, R.id.Hand9, R.id.Hand10, R.id.Hand11,
					R.id.Hand12, R.id.Hand13, R.id.Hand14, R.id.Hand15,
					R.id.Hand16, R.id.Hand17, R.id.Hand18, R.id.Hand19,
					R.id.Hand20, R.id.Hand21, R.id.Hand22, R.id.Hand23,
					R.id.Hand24, R.id.Hand25, R.id.Hand26, R.id.Hand27,
					R.id.Hand28, R.id.Hand29, R.id.Hand30, R.id.Hand31,
					R.id.Hand32, R.id.Hand33, R.id.Hand34, R.id.Hand35,
					R.id.Hand36, R.id.Hand37, R.id.Hand38, R.id.Hand39,
					R.id.Hand40, R.id.Hand41, R.id.Hand42, R.id.Hand43,
					R.id.Hand44, R.id.Hand45, R.id.Hand46, R.id.Hand47,
					R.id.Hand48, R.id.Hand49, R.id.Hand50, R.id.Hand51,
					R.id.Hand52, R.id.Hand53, R.id.Hand54, };

			// for(int i=0;i<26;i++)
			// tehuda1[i] = (ImageButton)layout.findViewById(cardsIDarray[i]);
			// for(int i=0;i<26;i++)
			// tehuda2[i] = (ImageView)layout.findViewById(cardsIDarray[i+26]);

			// 親が子にカードを配る（初めに実行される操作）
			shuffle.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					TypedArray cardImageArray = getResources()
							.obtainTypedArray(R.array.cards);

					mCardDrawer.deckReset();
					mCardDrawer.handReset();
					mCardDrawer.fieldReset();
					// Log.d("OK "+mCardDrawer.deck[0],"success" +
					// mCardDrawer.deck[2]);
					mCardDrawer.distribute(4); // 取得した任意のメンバー数
					Log.d("OK", "successSet");
					for (int i = 0; i < 52; i++) { // 人数によって変える
						// Log.d("OKLOOP ","success");
						if (mCardDrawer.Handchecker(i)) {
							// Log.d("OKIF "+ i,"success ( " + cardsIDarray[i]);
							Hand[i] = new ImageButton(getActivity());
							Hand[i].setId(cardsIDarray[i]);
							Hand[i].setScaleType(ImageButton.ScaleType.FIT_XY);
							Drawable drawable = cardImageArray
									.getDrawable(mCardDrawer.Hand[i]); // 対応した画像のセット
							Hand[i].setImageDrawable(drawable);

							HandList.addView(Hand[i],
									new LinearLayout.LayoutParams(80, 120)); // 手札のボタン追加

							Hand[i].setOnClickListener(HandOnClickListener);
						}
					}
				}
			});

			return rootView;
		}
	}
}
