package cf.substance.natter.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;

import java.util.Map;

//==============================================================================
public class MmsReceiver extends BroadcastReceiver {
	//--------------------------------------------------------------------------

	@Override public void onReceive( Context context, Intent intent ) {
		if ( !Telephony.Sms.Intents.WAP_PUSH_DELIVER_ACTION.equals( intent.getAction() )) return;
		onMmsReceived( context,
			intent.getIntExtra( "transactionId", 0 ),
			intent.getIntExtra( "pduType", 0 ),
			intent.getByteArrayExtra( "header" ),
			intent.getByteArrayExtra( "data" ),
			(Map<String,String>)intent.getExtras().get( "contentTypeParameters" ));
	}

	//--------------------------------------------------------------------------

	private void onMmsReceived( Context context, int transactionId, int pduType, byte[] header, byte[] data, Map<String,String> contentTypeParameters ) {

	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
