package cf.substance.natter.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;

//==============================================================================
public class SmsReceiver extends BroadcastReceiver {
	//--------------------------------------------------------------------------

	@Override public void onReceive( Context context, Intent intent ) {
		if ( !Telephony.Sms.Intents.SMS_DELIVER_ACTION.equals( intent.getAction() )) return;

		byte[][] pdus = (byte[][])intent.getExtras().get( "pdus" );
		SmsMessage[] messages = new SmsMessage[pdus.length];
		for ( int i = 0; i < messages.length; i++ ) {
			messages[i] = SmsMessage.createFromPdu( pdus[i] );
		}

		onSmsReceived( context, messages );
	}

	//--------------------------------------------------------------------------

	private void onSmsReceived( Context context, SmsMessage[] messages ) {

	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
