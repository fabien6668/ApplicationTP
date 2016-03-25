package android.ei6.biz.rdvlistemodule;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.widget.Button;

import java.util.List;

/**
 * Created by fabien on 22/03/2016.
 */
public class MesPreferencesActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle bdl) {
        super.onCreate(bdl);
    }

    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.preferences_entete, target);
    }
    @Override
    protected boolean isValidFragment(String fragmentName)
    {
        return PrefFragment.class.getName().equals(fragmentName);
    }

    public static class PrefFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.user_settings);
        }
    }


}
