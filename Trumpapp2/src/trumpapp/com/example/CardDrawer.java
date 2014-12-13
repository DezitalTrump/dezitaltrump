package trumpapp.com.example;

import android.util.Log;

public class CardDrawer{
	
	public int cardID = 0;
	public int index;
	
	public static final int DECK = 52;
	public int deck[] = new int[DECK]; //山札のindex
	public int Hand[] = new int[DECK];
	public int Field[] = new int[DECK];
	public void deckReset() //山札の初期化
	{
		for(int i=0;i<DECK;i++){deck[i] = 1;}
		
	}
	public void handReset() //手札の初期化
	{
		for(int i=0;i<DECK;i++){Hand[i] = 55;}
	}
	public void fieldReset() //捨て札の初期化
	{
		for(int i=0;i<DECK;i++){Field[i] = 55;}
	}
	public boolean Handchecker(int id){
		return Hand[id]!= 55;
	}
	
//	TypedArray cardsArray = getResources().obtainTypedArray(R.array.cards);
	
	//山札からカードを配る
	public void distribute(int member){
		index = 0;
		if(member != 0 && member < 5){
				for(int i=0 ;i < DECK/member; i++){
					Log.d("OK","success1");
					cardReceive();
				}
		}
	}
	
	//カードの受け取り
	public void cardReceive(){
		int c = 0;
		while(c == 0){
			cardID =(int)(Math.random()*DECK);
			Log.d("OK "+deck[cardID],"success2 "+cardID  );
			if(deck[cardID] != 0){
				deck[cardID] = 0;
				Hand[index] = cardID;
				index++;
				c++;
			}
		}
	}
	
	//カードを場に出す
	public void cardThrow(int id)
	{
		Field[id] = id;
		Hand[id] = 0;
	}
	
	//場からカードをとる
	public void cardPick(int id)
	{
		Hand[id] = id;
		Field[id] = 0;
	}
	
	//カードを山札に戻す
	public void cardReturn(int id)
	{
		deck[id] = 1;
		Hand[id] = 0;
	}
	
	//山札の同期
	public void deckSynchro(int id)
	{
		if(deck[id] == 1){
			deck[id] = 0;
		}else if(deck[id] == 0){
			deck[id]=1;
		}else {}
			
	}

	//捨て札の同期
	public void FieldSynchro(int id)
	{
		if(Field[id] != id){
			Field[id] = id;
		}else {}
			
	}
	
	
	
	//並べ替え
	public void handSwap()
	{
		
	}
	
}
