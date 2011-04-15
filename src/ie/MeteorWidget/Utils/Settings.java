

package ie.MeteorWidget.Utils;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;


public class Settings
{

	private static Settings instance = null;
	private Context applicationContext = null;
	private AppWidgetManager applicationProvider = null;
	private SharedPreferences prefs = null;


	private Settings()
	{
		this.loadPreferences();
	}


	public static Settings getInstance()
	{
		if( Settings.instance == null )
		{
			Settings.instance = new Settings();
		}
		
		Settings.instance.loadPreferences();
		
		return Settings.instance;
	}

	
	public static Settings getInstance( Context context )
	{
		if( Settings.instance == null )
		{
			Settings.instance = new Settings();
		}
		
		Settings.instance.loadPreferences( context );
		
		return Settings.instance;
	}
	

	public void loadPreferences()
	{
		if( this.applicationContext != null )
		{
			this.prefs = PreferenceManager.getDefaultSharedPreferences( this.applicationContext );
		}
	}
	
	
	public void loadPreferences( Context context )
	{
		this.prefs = PreferenceManager.getDefaultSharedPreferences( context );
	}


	public void set( String key , String value )
	{
		Editor editor = this.prefs.edit();
		editor.putString( key , value );
		editor.commit();
	}


	public String get( String key , String value )
	{
		this.loadPreferences();
		return this.prefs.getString( key , value );
	}


	public boolean get( String key , boolean value )
	{
		this.loadPreferences();
		return !this.prefs.getBoolean( key , value );
	}
	
	
	public void setContext( Context context )
	{
		this.applicationContext = context;
	}


	public Context getContext()
	{
		return this.applicationContext;
	}
	
	
	public void setApplicationProvider( AppWidgetManager provider )
	{
		this.applicationProvider = provider;
	}


	public AppWidgetManager getApplicationProvider()
	{
		return this.applicationProvider;
	}
}
