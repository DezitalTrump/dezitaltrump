package trumpapp.com.example;

import android.bluetooth.BluetoothAdapter;
import android.widget.ArrayAdapter;

public class BT {
	
    // Name of the connected device
    private String mConnectedDeviceName = null;
    // Array adapter for the conversation thread
    private ArrayAdapter<String> mConversationArrayAdapter;
    // String buffer for outgoing messages
    private StringBuffer mOutStringBuffer;
    // Local Bluetooth adapter
    private BluetoothAdapter mBluetoothAdapter = null;
	
	/*public void BTpermission(){
		
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		
		public void turnOn() {
			if (!mBluetoothAdapter.isEnabled()) {
				Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
				mActivity.startActivityForResult(intent, REQUEST_ENABLE_BT);
			}
		}	
	}*/
}

