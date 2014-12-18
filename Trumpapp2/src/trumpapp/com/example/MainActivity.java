package trumpapp.com.example;

import android.annotation.TargetApi;
import android.content.res.TypedArray;
import android.graphics.Color;
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

		final static int[] cardsIDarray = new int[] { R.id.Hand1, R.id.Hand2,
				R.id.Hand3, R.id.Hand4, R.id.Hand5, R.id.Hand6, R.id.Hand7,
				R.id.Hand8, R.id.Hand9, R.id.Hand10, R.id.Hand11, R.id.Hand12,
				R.id.Hand13, R.id.Hand14, R.id.Hand15, R.id.Hand16,
				R.id.Hand17, R.id.Hand18, R.id.Hand19, R.id.Hand20,
				R.id.Hand21, R.id.Hand22, R.id.Hand23, R.id.Hand24,
				R.id.Hand25, R.id.Hand26, R.id.Hand27, R.id.Hand28,
				R.id.Hand29, R.id.Hand30, R.id.Hand31, R.id.Hand32,
				R.id.Hand33, R.id.Hand34, R.id.Hand35, R.id.Hand36,
				R.id.Hand37, R.id.Hand38, R.id.Hand39, R.id.Hand40,
				R.id.Hand41, R.id.Hand42, R.id.Hand43, R.id.Hand44,
				R.id.Hand45, R.id.Hand46, R.id.Hand47, R.id.Hand48,
				R.id.Hand49, R.id.Hand50, R.id.Hand51, R.id.Hand52,
				R.id.Hand53, R.id.Hand54, };

		CardDrawer mCardDrawer = new CardDrawer();
		final ImageButton[] Hand = new ImageButton[54];
		final ImageButton[] Field = new ImageButton[54];

		View.OnClickListener HandOnClickListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int index = 0;
				while (Hand[index].getId() != v.getId()) { // タップされた手札の位置記録
					index++;
				}
				if (index != mCardDrawer.Swapindex
						&& mCardDrawer.Swapindex != 55) {// 2回目のタップ（違うカード)
					ViewSwap(index);
					ViewSwap(mCardDrawer.Swapindex);
					mCardDrawer.Swapindex = 55;
				} else if (index == mCardDrawer.Swapindex) {
					mCardDrawer.cardThrow(index,mCardDrawer.Hand[index]);
					cardServe(index);
					cardIDset(index);
					// for(int i=0;Hand[i]!=null;i++)
					// Log.d("AFTERdeleteID " + Hand[i].getId(),
					// "cardsIDarray "+ cardsIDarray[i]);
					//
					mCardDrawer.Swapindex = 55;
				} else {
					Log.d("firstTAP " + index, "success");
					v.setBackgroundColor(Color.rgb(0, 0, 255));
					mCardDrawer.Swapindex = index; // 選択した場所記録
					for (int i = 0; Hand[i] != null; i++)
						Log.d("AFTERdeleteID " + Hand[i].getId(),
								"cardsIDarray " + cardsIDarray[i]);

				}
			}
		};

		LinearLayout HandList = null;
		LinearLayout FieldList = null;
		Button oneDraw;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			RelativeLayout layout = (RelativeLayout) rootView;
			Button shuffle = (Button) layout.findViewById(R.id.shuffle);
			oneDraw = (Button) layout.findViewById(R.id.oneDraw);
			oneDraw.setVisibility(View.INVISIBLE);
			HandList = (LinearLayout) layout.findViewById(R.id.HandList);
			FieldList = (LinearLayout) layout.findViewById(R.id.FieldList);

			// 親が子にカードを配る（初めに実行される操作）
			shuffle.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					TypedArray cardImageArray = getResources()
							.obtainTypedArray(R.array.cards);
					mCardDrawer.deckReset();
					mCardDrawer.handReset();
					mCardDrawer.fieldReset();
					mCardDrawer.distribute(4); // 取得した任意のメンバー数
					for (int i = 0; i < 52; i++) {
						if (mCardDrawer.Handchecker(i)) {
							Hand[i] = new ImageButton(getActivity());
							Hand[i].setId(cardsIDarray[mCardDrawer.Hand[i]]);// idの割り当て
							Log.d("ID " + cardsIDarray[i], "cardID");
							Hand[i].setScaleType(ImageButton.ScaleType.FIT_XY);// 画像表示形式
							Hand[i].setPadding(10, 10, 10, 10);
							Hand[i].setBackgroundColor(Color.TRANSPARENT);
							Drawable drawable = cardImageArray
									.getDrawable(mCardDrawer.Hand[i]); // 対応した画像のセット
							Hand[i].setImageDrawable(drawable);
							HandList.addView(Hand[i],
									new LinearLayout.LayoutParams(80, 120)); // 手札のボタン追加
							Hand[i].setOnClickListener(HandOnClickListener);
							v.setVisibility(View.GONE);
							oneDraw.setVisibility(View.VISIBLE);
						}
					}
				}
			});

			// 1枚カードを引く
			/*oneDraw.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					int endid = 0;
					while (Hand[endid] != null && endid < 51) { // 手札の末尾のindex
						endid++;
					}
					if (endid < 52) {
						mCardDrawer.cardOneDraw(endid);
						Log.d("endid " + endid, "sssss ");
						TypedArray cardImageArray = getResources()
								.obtainTypedArray(R.array.cards);
						Hand[endid] = new ImageButton(getActivity());
						Hand[endid]
								.setId(cardsIDarray[mCardDrawer.Hand[endid]]);// idの割り当て
						Hand[endid].setScaleType(ImageButton.ScaleType.FIT_XY);// 画像表示形式
						Hand[endid].setPadding(10, 10, 10, 10);
						Hand[endid].setBackgroundColor(Color.TRANSPARENT);
						Drawable drawable = cardImageArray
								.getDrawable(mCardDrawer.Hand[endid]); // 対応した画像のセット
						Hand[endid].setImageDrawable(drawable);
						HandList.addView(Hand[endid], endid,
								new LinearLayout.LayoutParams(80, 120)); // 手札のボタン追加
						Hand[endid].setOnClickListener(HandOnClickListener);
					} else {
					}
				}

			});*/

			return rootView;
		}

		// Viewの削除と追加
		public void ViewSwap(int index) {
			TypedArray cardImageArray = getResources().obtainTypedArray(
					R.array.cards);
			// Log.d("removeView " + index, "cardID " + mCardDrawer.Swapindex);
			HandList.removeView(Hand[index]);
			mCardDrawer.handSwap(mCardDrawer.Swapindex, index);
			// Log.d("index " + index, "Swap " + mCardDrawer.Swapindex);
			Hand[index] = new ImageButton(getActivity());
			Hand[index].setId(cardsIDarray[mCardDrawer.Hand[index]]);// idの割り当て
			Hand[index].setScaleType(ImageButton.ScaleType.FIT_XY);// 画像表示形式
			Hand[index].setPadding(10, 10, 10, 10);
			Hand[index].setBackgroundColor(Color.TRANSPARENT);
			Drawable drawable = cardImageArray
					.getDrawable(mCardDrawer.Hand[index]); // 対応した画像のセット
			Hand[index].setImageDrawable(drawable);
			HandList.addView(Hand[index], index, new LinearLayout.LayoutParams(
					80, 120)); // 手札のボタン追加
			Hand[index].setOnClickListener(HandOnClickListener);
		}

		// カードを出す
		public void cardServe(int index) {
			FieldList.removeAllViews();
			TypedArray cardImageArray = getResources().obtainTypedArray(
					R.array.cards);
			Log.d("index " + index, "Field[index] "+mCardDrawer.Field[index]);
			Field[index] = new ImageButton(getActivity());
			Field[index].setId(cardsIDarray[mCardDrawer.Field[index]]);// idの割り当て
			Field[index].setScaleType(ImageButton.ScaleType.FIT_XY);// 画像表示形式
			Field[index].setPadding(10, 10, 10, 10);
			Field[index].setBackgroundColor(Color.TRANSPARENT);
			Drawable drawable = cardImageArray
					.getDrawable(mCardDrawer.Field[index]); // 対応した画像のセット
			Field[index].setImageDrawable(drawable);
			FieldList.addView(Field[index], 0, new LinearLayout.LayoutParams(
					80, 120)); // 手札のボタン追加
//			Hand[index].setOnClickListener(HandOnClickListener);

		}

		public void cardIDset(int index) {
			HandList.removeAllViews();
			TypedArray cardImageArray = getResources().obtainTypedArray(
					R.array.cards);
			for (int i = 0; i < 52; i++) {
				if (mCardDrawer.Handchecker(i)) {
					Hand[i] = new ImageButton(getActivity());
					Hand[i].setId(cardsIDarray[mCardDrawer.Hand[i]]);// idの割り当て
					Log.d("ID " + cardsIDarray[i], "cardID");
					Hand[i].setScaleType(ImageButton.ScaleType.FIT_XY);// 画像表示形式
					Hand[i].setPadding(10, 10, 10, 10);
					Hand[i].setBackgroundColor(Color.TRANSPARENT);
					Drawable drawable = cardImageArray
							.getDrawable(mCardDrawer.Hand[i]); // 対応した画像のセット
					Hand[i].setImageDrawable(drawable);
					HandList.addView(Hand[i], new LinearLayout.LayoutParams(80,
							120)); // 手札のボタン追加
					Hand[i].setOnClickListener(HandOnClickListener);
				}
			}
		}
	}
}
