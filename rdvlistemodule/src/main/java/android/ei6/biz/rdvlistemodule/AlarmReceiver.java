package android.ei6.biz.rdvlistemodule;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by fabien on 18/03/2016.
 */
public class AlarmReceiver extends BroadcastReceiver {
    NotificationManager mNotif;

    @Override
    public void onReceive(Context context, Intent intent) {
        mNotif = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent ala = new Intent(context, MainActivity.class);

        //une seule version de l'activité et nettoyage de la pile pour retrouver l'activité
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pi = PendingIntent.getActivity(context,0,ala,PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder bld = new Notification.Builder(context)
                            .setSmallIcon(R.drawable.ic_notif)
                            .setContentTitle(context.getString(R.string.attention))
                            .setContentText(context.getString(R.string.rappeler_client))
                            .setContentIntent(pi);

        Notification note = bld.build();

        //disparaît lorsque l'utilisateur clique dessus
        //note.flags |= Notification.FLAG_AUTO_CANCEL;

        mNotif.notify(1, note);
    }
}
