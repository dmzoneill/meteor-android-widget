

package ie.MeteorWidget;

import ie.MeteorWidget.Observer.Observable;
import ie.MeteorWidget.Observer.Observer;
import ie.MeteorWidget.Observer.ObserverPayload;
import ie.MeteorWidget.Utils.GoogleAnalytics;
import ie.MeteorWidget.Utils.Settings;
import java.util.ArrayList;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.widget.RemoteViews;
import android.widget.Toast;


public class MeteorPreferences extends PreferenceActivity implements OnSharedPreferenceChangeListener, Observable
{

	private ObserverPayload payload = null;
	private ArrayList< Observer > observers;
	private boolean recursivePreventer;


	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );		
		this.prepareObservers();		
		this.addPreferencesFromResource( R.xml.preferences );
				
		this.track( "MeteorPreferences" );	
		
		if( MeteorWidgetProvider.getInstance() == null )
		{
			this.notification( "Remeber to place the widget\non your home screen" , 0 );
		}
	}
	
	
	public void prepareObservers()
	{
		this.observers = new ArrayList< Observer >();
		
		Settings.getInstance( this.getApplicationContext() );
	
		if( GoogleAnalytics.getInstance() != null )
		{
			this.attach( GoogleAnalytics.getInstance() );
		}
		
		if( MeteorWidgetProvider.getInstance() != null )
		{
			this.attach( MeteorWidgetProvider.getInstance() );
		}
		
		if( MeteorActivity.getInstance() != null )
		{
			this.attach( MeteorActivity.getInstance() );
		}
	}	


	private void changeViewValues()
	{
		Settings opt = Settings.getInstance();
			
		try
		{				
			ListPreference widget_theme_base_color = ( ListPreference ) this.findPreference( "meteor_widget_theme_basecolor" );
			widget_theme_base_color.setSummary( opt.get( "meteor_widget_theme_basecolor" , "orange" ) );
			this.trackEvent( "MeteorPreferences" , "ThemeBaseColor" , opt.get( "meteor_widget_theme_basecolor" , "orange" ) , 1 );
		}
		catch( Exception androidWobbler ){}		
		
		
		
		
		
		try
		{	
			ListPreference widget_theme_header_font = ( ListPreference ) this.findPreference( "meteor_widget_theme_headerfont" );				
			String font = opt.get( "meteor_widget_theme_headerfont" , "normal" );
			
			if( font.compareTo( "normal" ) == 0 )
			{
				widget_theme_header_font.setSummary( "Droid Sans" );
			}
			else if( font.compareTo( "serif" ) == 0 )
			{
				widget_theme_header_font.setSummary( "Droid Serif" );
			}
			else if( font.compareTo( "monospace" ) == 0 )
			{
				widget_theme_header_font.setSummary( "Droid Sans Mono" );
			}
			else
			{
				widget_theme_header_font.setSummary( "Droid Sans" );
			}
			
			this.trackEvent( "MeteorPreferences" , "ThemeHeaderFont" , font , 1 );
		}
		catch( Exception androidWobbler ){}
		
		
		
		
		
		try
		{	
			ListPreference widget_theme_header_font_size = ( ListPreference ) this.findPreference( "meteor_widget_theme_headerfontsize" );				
			String fontsize = opt.get( "meteor_widget_theme_headerfontsize" , "18" );
			
			if( fontsize.compareTo( "12" ) == 0 )
			{
				widget_theme_header_font_size.setSummary( "Tiny" );
			}
			else if( fontsize.compareTo( "14" ) == 0 )
			{
				widget_theme_header_font_size.setSummary( "Small" );
			}
			else if( fontsize.compareTo( "16" ) == 0 )
			{
				widget_theme_header_font_size.setSummary( "Medium" );
			}
			else if( fontsize.compareTo( "18" ) == 0 )
			{
				widget_theme_header_font_size.setSummary( "Big" );
			}
			else if( fontsize.compareTo( "20" ) == 0 )
			{
				widget_theme_header_font_size.setSummary( "Huge" );
			}
			else if( fontsize.compareTo( "22" ) == 0 )
			{
				widget_theme_header_font_size.setSummary( "Massive" );
			}
			else
			{
				widget_theme_header_font_size.setSummary( "Big" );
			}
					
			this.trackEvent( "MeteorPreferences" , "ThemeHeaderFontSize" , fontsize , 1 );
		}
		catch( Exception androidWobbler ){}
		
		
		
		
		try
		{	
		
			ListPreference widget_theme_header_color = ( ListPreference ) this.findPreference( "meteor_widget_theme_headerfontcolor" );
			widget_theme_header_color.setSummary( opt.get( "meteor_widget_theme_headerfontcolor" , "white" ) );
			
			this.trackEvent( "MeteorPreferences" , "ThemeHeaderFontColor" , opt.get( "meteor_widget_theme_headerfontcolor" , "white" ) , 1 );
			
		}
		catch( Exception androidWobbler ){}
		
		
		
		
		
		try
		{			
			ListPreference meteor_widget_theme_bodyfontsize = ( ListPreference ) this.findPreference( "meteor_widget_theme_bodyfontsize" );				
			String fontsize = opt.get( "meteor_widget_theme_bodyfontsize" , "16" );
			
			if( fontsize.compareTo( "12" ) == 0 )
			{
				meteor_widget_theme_bodyfontsize.setSummary( "Tiny" );
			}
			else if( fontsize.compareTo( "14" ) == 0 )
			{
				meteor_widget_theme_bodyfontsize.setSummary( "Small" );
			}
			else if( fontsize.compareTo( "16" ) == 0 )
			{
				meteor_widget_theme_bodyfontsize.setSummary( "Medium" );
			}
			else if( fontsize.compareTo( "18" ) == 0 )
			{
				meteor_widget_theme_bodyfontsize.setSummary( "Big" );
			}
			else if( fontsize.compareTo( "20" ) == 0 )
			{
				meteor_widget_theme_bodyfontsize.setSummary( "Huge" );
			}
			else if( fontsize.compareTo( "22" ) == 0 )
			{
				meteor_widget_theme_bodyfontsize.setSummary( "Massive" );
			}
			else
			{
				meteor_widget_theme_bodyfontsize.setSummary( "Big" );
			}
			
			this.trackEvent( "MeteorPreferences" , "ThemeBodyFontSize" , fontsize , 1 );
		}
		catch( Exception androidWobbler ){}
		
		
		
		
		
		
		try
		{					
			ListPreference meteor_widget_theme_bodyfontcolordesc = ( ListPreference ) this.findPreference( "meteor_widget_theme_bodyfontcolordesc" );
			meteor_widget_theme_bodyfontcolordesc.setSummary( opt.get( "meteor_widget_theme_bodyfontcolordesc" , "white" ) );
			
			this.trackEvent( "MeteorPreferences" , "ThemeBodyFontColorDesc" , opt.get( "meteor_widget_theme_bodyfontcolordesc" , "white" ) , 1 );
		}
		catch( Exception androidWobbler ){}
		
		
		
		
		
		
		try
		{			
			
			ListPreference meteor_widget_theme_bodyfontcolorvalue = ( ListPreference ) this.findPreference( "meteor_widget_theme_bodyfontcolorvalue" );
			meteor_widget_theme_bodyfontcolorvalue.setSummary( opt.get( "meteor_widget_theme_bodyfontcolorvalue" , "black" ) );
			
			this.trackEvent( "MeteorPreferences" , "ThemeBodyFontColorValue" , opt.get( "meteor_widget_theme_bodyfontcolorvalue" , "black" ) , 1 );
		}
		catch( Exception androidWobbler ){}
		
		
		
		
		
		
		try
		{					
			ListPreference widget_theme_body_font = ( ListPreference ) this.findPreference( "meteor_widget_theme_bodyfont" );				
			String bodyfont = opt.get( "meteor_widget_theme_bodyfont" , "normal" );
			
			if( bodyfont.compareTo( "normal" ) == 0 )
			{
				widget_theme_body_font.setSummary( "Droid Sans" );
			}
			else if( bodyfont.compareTo( "serif" ) == 0 )
			{
				widget_theme_body_font.setSummary( "Droid Serif" );
			}
			else if( bodyfont.compareTo( "monospace" ) == 0 )
			{
				widget_theme_body_font.setSummary( "Droid Sans Mono" );
			}
			else
			{
				widget_theme_body_font.setSummary( "Droid Sans" );
			}
			
			this.trackEvent( "MeteorPreferences" , "ThemeBodyFont" , bodyfont , 1 );
		}
		catch( Exception androidWobbler ){}
		
		
		
		
		
		
		try
		{			
			EditTextPreference option_number = ( EditTextPreference ) this.findPreference( "meteor_widget_phone_number" );
			EditTextPreference option_pin = ( EditTextPreference ) this.findPreference( "meteor_widget_phone_pin" );
			
			String number = opt.get( "meteor_widget_phone_number" , "08" );
			String pin = opt.get( "meteor_widget_phone_pin" , "" );
	
			if( number.compareTo( "08" ) != 0 )
			{
				option_number.setSummary( number );	
				this.trackEvent( "MeteorPreferences" , "ghuthain" , number , 1 );
			}
	
			if( pin.length() > 0 )
			{
				String repeat = this.repeat( "*" , pin.length() );
				option_pin.setSummary( repeat );
			}
		}
		catch( Exception androidWobbler ){}
		
		
		
		
		
		
		try
		{				
			ListPreference option_interval = ( ListPreference ) this.findPreference( "meteor_widget_update_interval" );
			String updateInterval = opt.get( "meteor_widget_update_interval" , "900" );
			
			int interval = Integer.parseInt( updateInterval ) / 60;
			
			String updateNotifications = String.valueOf( opt.get( "meteor_widget_update_notifications" , true ) );
			String errorNotifications = String.valueOf( opt.get( "meteor_widget_error_notifications" , true ) );
			String updateDisabled = String.valueOf( opt.get( "meteor_widget_main_option" , false ) );
	
			this.trackEvent( "MeteorPreferences" , "Interval" , "minutes" , interval );
			this.trackEvent( "MeteorPreferences" , "updateNotifications" , updateNotifications , 1 );
			this.trackEvent( "MeteorPreferences" , "errorNotifications" , errorNotifications , 1 );
			this.trackEvent( "MeteorPreferences" , "updateDisabled" , updateDisabled , 1 );
			
			String nextUpdate = ( opt.get( "meteor_widget_next_update" , "0" ).compareTo( "0" ) == 0 ) ? "" : " ( " + opt.get( "meteor_widget_next_update" , "0" ) + " )";
			opt.set( "meteor_widget_next_update" , "0" );
	
			if( interval > 59 )
			{
				interval = interval / 60;
				String plural = ( interval > 1 ) ? " hours" : " hour";
				option_interval.setSummary( interval + plural );
			}
			else
			{
				option_interval.setSummary( interval + " minutes" + nextUpdate );
			}	
		}
		catch( Exception androidWobbler ){}
		
		
		
		
		
		
		try
		{			
			ListPreference meteor_widget_theme_notificationfont = ( ListPreference ) this.findPreference( "meteor_widget_theme_notificationfont" );				
			String notifyfont = opt.get( "meteor_widget_theme_notificationfont" , "normal" );
			
			if( notifyfont.compareTo( "normal" ) == 0 )
			{
				meteor_widget_theme_notificationfont.setSummary( "Droid Sans" );
			}
			else if( notifyfont.compareTo( "serif" ) == 0 )
			{
				meteor_widget_theme_notificationfont.setSummary( "Droid Serif" );
			}
			else if( notifyfont.compareTo( "monospace" ) == 0 )
			{
				meteor_widget_theme_notificationfont.setSummary( "Droid Sans Mono" );
			}
			else
			{
				meteor_widget_theme_notificationfont.setSummary( "Droid Sans" );
			}
			
			this.trackEvent( "MeteorPreferences" , "ThemeNotifyFont" , notifyfont , 1 );
		}
		catch( Exception androidWobbler ){}
		
		
		
		
		
		
		
		try
		{	
			ListPreference meteor_widget_theme_notificationfontsize = ( ListPreference ) this.findPreference( "meteor_widget_theme_notificationfontsize" );				
			String fontsize = opt.get( "meteor_widget_theme_notificationfontsize" , "18" );
			
			if( fontsize.compareTo( "12" ) == 0 )
			{
				meteor_widget_theme_notificationfontsize.setSummary( "Tiny" );
			}
			else if( fontsize.compareTo( "14" ) == 0 )
			{
				meteor_widget_theme_notificationfontsize.setSummary( "Small" );
			}
			else if( fontsize.compareTo( "16" ) == 0 )
			{
				meteor_widget_theme_notificationfontsize.setSummary( "Medium" );
			}
			else if( fontsize.compareTo( "18" ) == 0 )
			{
				meteor_widget_theme_notificationfontsize.setSummary( "Big" );
			}
			else if( fontsize.compareTo( "20" ) == 0 )
			{
				meteor_widget_theme_notificationfontsize.setSummary( "Huge" );
			}
			else if( fontsize.compareTo( "22" ) == 0 )
			{
				meteor_widget_theme_notificationfontsize.setSummary( "Massive" );
			}
			else
			{
				meteor_widget_theme_notificationfontsize.setSummary( "Big" );
			}
			
			this.trackEvent( "MeteorPreferences" , "ThemeNotifyFontSize" , fontsize , 1 );
		}
		catch( Exception androidWobbler ){}
		
		
		
		
		
		
		try
		{					
			ListPreference meteor_widget_theme_notificationfontcolor = ( ListPreference ) this.findPreference( "meteor_widget_theme_notificationfontcolor" );
			meteor_widget_theme_notificationfontcolor.setSummary( opt.get( "meteor_widget_theme_notificationfontcolor" , "white" ) );
			
			this.trackEvent( "MeteorPreferences" , "ThemeNotifyFontColor" , opt.get( "meteor_widget_theme_notificationfontcolor" , "white" ) , 1 );
		}
		catch( Exception androidWobbler ){}
		
		
		
		
		
		try
		{		
			this.payload = new ObserverPayload();
			this.payload.payload_themechange = true;
			this.notifyObservers();
		}
		catch( Exception androidWobbler ){}

	}


	@Override
	public void onBackPressed()
	{
		if( MeteorWidgetProvider.getInstance() == null )
		{
			this.notification( "Remeber to place the widget\non your home screen" , 0 );
		}
		this.finish();
	}


	protected void onResume()
	{
		super.onResume();
		try
		{
			if( this.recursivePreventer == false ) 
			{
				this.recursivePreventer = true;
				this.changeViewValues();
				this.recursivePreventer = false;
			}	
		}
		catch( Exception AndroidWobber ){}
		this.getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener( this );
	}


	protected void onPause()
	{
		super.onPause();
		try
		{
			if( this.recursivePreventer == false ) 
			{
				this.recursivePreventer = true;
				this.changeViewValues();
				this.recursivePreventer = false;
			}	
		}
		catch( Exception AndroidWobber ){}
		this.getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener( this );
	}


	public void onSharedPreferenceChanged( SharedPreferences sharedPreferences , String key )
	{
		try
		{
			if( this.recursivePreventer == false ) 
			{
				this.recursivePreventer = true;
				this.changeViewValues();
				this.recursivePreventer = false;
			}						
		}
		catch( Exception AndroidWobber ){}
	}


	private String repeat( String s , int times )
	{
		if( times <= 0 )
			return "";
		else return s + repeat( s , times - 1 );
	}


	@Override
	public void track( String tracked )
	{
		this.payload = null;
		this.payload = new ObserverPayload();
		this.payload.payload_analytics = true;
		this.payload.pageView = tracked;
		this.notifyObservers();
	}


	@Override
	public void trackEvent( String tracked , String action , String label , int value )
	{
		this.payload = null;
		this.payload = new ObserverPayload();
		this.payload.payload_analytics = true;
		this.payload.category = tracked;
		this.payload.action = action;
		this.payload.label = label;
		this.payload.value = value;
		this.notifyObservers();
	}


	@Override
	public void trackDispatch()
	{
		this.payload = null;
		this.payload = new ObserverPayload();
		this.payload.payload_analytics = true;
		this.payload.dispatch = true;
		this.notifyObservers();
	}


	@Override
	public void updateWidgetView( RemoteViews view )
	{
		this.payload = null;
		this.payload = new ObserverPayload();
		this.payload.view = view;
		this.payload.payload_update = true;
		this.notifyObservers();
	}


	@Override
	public void notification( String message , int type )
	{
		Toast toast = Toast.makeText( this.getApplicationContext() , message , Toast.LENGTH_LONG );	
		toast.show();
	}


	@Override
	public void attach( Observer observer )
	{
		this.observers.add( observer );
	}


	@Override
	public void detach( Observer observer )
	{
		this.observers.add( observer );
	}


	@Override
	public void notifyObservers()
	{
		for ( Observer obj : this.observers )
		{
			if( obj == null ) continue;
			obj.update( this , this.payload );
		}
	}

}
