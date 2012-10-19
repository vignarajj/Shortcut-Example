package com.shortcut.example;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Mainactivity extends Activity
{
	int count;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        SharedPreferences app_pref=PreferenceManager.getDefaultSharedPreferences(this);
        int counter=app_pref.getInt("count",0);
        SharedPreferences.Editor editor=app_pref.edit();
        editor.putInt("count", ++counter);
        editor.commit();
        if(counter==1)
        {
        	validate();        }

    }
    boolean validate()
    {
    	 Intent shortcutIntent;
         shortcutIntent = new Intent();
         shortcutIntent.setComponent(new ComponentName(this.getPackageName(), this.getClass().getName()));

         shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
         shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

         final Intent putShortCutIntent = new Intent();
         putShortCutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT,
                 shortcutIntent);

         // Sets the custom shortcut's title
         putShortCutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME,
                 "Shortcut Example");
 putShortCutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,Intent.ShortcutIconResource.fromContext(
                             this, R.drawable.logo1));
 putShortCutIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
         sendBroadcast(putShortCutIntent);
		return false;
    }

}