package android.ei6.biz.rdvlistemodule;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by fabien on 18/03/2016.
 */
public class DeviceBootReceiver extends BroadcastReceiver {
    public final int CODE_ALARM=42;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,context.getString(R.string.timer_en_cours),Toast.LENGTH_SHORT).show();

        Intent chrono = new Intent(context, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context,CODE_ALARM,chrono, 0);
        AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),1000*10,pi);

    }
}
