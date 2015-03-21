package studio.android.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSBroadCastReceiver extends BroadcastReceiver {

	public SMSBroadCastReceiver() {
		System.out.println("TestBroadCastReceiver");
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("onReceive");
		Bundle bundle = intent.getExtras();
		Object[] mypdus = (Object[]) bundle.get("pdus");
		SmsMessage[] msgs = new SmsMessage[mypdus.length];
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < msgs.length; i++) {
			msgs[i] = SmsMessage.createFromPdu((byte[]) mypdus[i]);
			sb.append(msgs[i].getMessageBody() + "\r\n");
		}
		Toast.makeText(context, sb.toString(), Toast.LENGTH_SHORT).show();
	}
}
