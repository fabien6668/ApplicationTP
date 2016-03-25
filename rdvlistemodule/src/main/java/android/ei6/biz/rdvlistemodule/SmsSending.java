package android.ei6.biz.rdvlistemodule;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Created by fabien on 23/03/2016.
 */
public class SmsSending {
    private static final int CODE_SMS =42 ;
    Context context;
    BroadcastReceiver mBroadcastReceiver;

    public SmsSending(Context cxt) {
        context = cxt;
    }

    public void sendSMS(String tel, String msg) {
        String DELIVERED="SMS_DELIVERED";
        Intent intent = new Intent();
        PendingIntent deliveredPI = PendingIntent.getBroadcast(context,CODE_SMS,intent,0);

        context.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                switch(getResultCode()) {
                    case Activity.RESULT_OK :
                        Toast.makeText(context,context.getString(R.string.message_envoye),Toast.LENGTH_LONG).show();
                        break;
                }
            }
        }, new IntentFilter(DELIVERED));

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(tel,null,msg,null,deliveredPI);
    }
}
