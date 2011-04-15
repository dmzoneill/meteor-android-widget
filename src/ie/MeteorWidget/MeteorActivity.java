

package ie.MeteorWidget;

import ie.MeteorWidget.Notifications.NotificationManager;
import ie.MeteorWidget.Observer.Observable;
import ie.MeteorWidget.Observer.Observer;
import ie.MeteorWidget.Observer.ObserverPayload;
import ie.MeteorWidget.Themes.Theme;
import ie.MeteorWidget.Threader.Updater;
import ie.MeteorWidget.Utils.GoogleAnalytics;
import ie.MeteorWidget.Utils.Settings;
import java.util.ArrayList;
import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;


public class MeteorActivity extends Activity implements Observable , Observer
{


	private int mAppWidgetId;
	private ObserverPayload payload = null;
	private ArrayList< Observer > observers;
	private static MeteorActivity instance;
	
	
	public void prepareObservers()
	{
		this.observers = new ArrayList< Observer >();
	
		if( GoogleAnalytics.getInstance() != null )
		{
			this.attach( GoogleAnalytics.getInstance() );
		}
		
		if( NotificationManager.getInstance() != null )
		{
			this.attach( NotificationManager.getInstance() );
		}
		
		if( MeteorWidgetProvider.getInstance() != null )
		{
			this.attach( MeteorWidgetProvider.getInstance() );
		}
	}


	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		this.setResult( RESULT_CANCELED );
		
		Settings.getInstance( this.getApplicationContext() );
		this.prepareObservers();
		MeteorActivity.instance = this;
		
		Settings.getInstance().setContext( this.getApplicationContext() );
		this.setContentView( Theme.getInstance().getActivityTheme() );		
		
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		if( extras != null )
		{
			this.mAppWidgetId = extras.getInt( AppWidgetManager.EXTRA_APPWIDGET_ID , AppWidgetManager.INVALID_APPWIDGET_ID );
		}		
		
	}


	public void editPreferences( View view )
	{
		Intent prefsActivity = new Intent( getBaseContext() , MeteorPreferences.class );
		startActivity( prefsActivity );
	}


	public void forceUpdate( View view )
	{
		Updater updater = Updater.getInstance();
		Settings opt = Settings.getInstance();
		updater.stop();
		opt.set( "meteor_widget_force_update" , "1" );
		
		this.sendBroadcast( new Intent( MeteorWidgetProvider.ACTION_UPDATE ).putExtra( "" , false ) );
		Intent resultValue = new Intent();
		resultValue.putExtra( AppWidgetManager.EXTRA_APPWIDGET_ID , this.mAppWidgetId );
		this.setResult( RESULT_OK , resultValue );
		
		this.trackEvent( "MeteorActivity" , "Update" , "Force" , 1 );
		finish();	
	}
	
	
	public void showAbout( View view )
	{
		Intent aboutActivity = new Intent( getBaseContext() , MeteorAbout.class );
		startActivity( aboutActivity );
	}


	@Override
	public void onBackPressed()
	{
		try
		{
			Settings opt = Settings.getInstance();
			Updater updater = Updater.getInstance();
			if( updater.isRunning() == false )
			{
				opt.set( "meteor_widget_force_update" , "1" );
			}
			else
			{
				opt.set( "meteor_widget_force_update" , "0" );
			}
			
			this.sendBroadcast( new Intent( MeteorWidgetProvider.ACTION_UPDATE ).putExtra( "" , false ) );
			Intent resultValue = new Intent();
			resultValue.putExtra( AppWidgetManager.EXTRA_APPWIDGET_ID , this.mAppWidgetId );
			this.setResult( RESULT_OK , resultValue );
		}
		catch ( Exception f )
		{
			this.notification( f.getMessage() , 0 );
			this.trackEvent( "MeteorActivity" , "onBackPressedFailed" ,  f.getMessage() , 1 );
		}

		finish();
	}
	
	
	public static MeteorActivity getInstance()
	{
		return MeteorActivity.instance;
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
		this.payload = null;
		this.payload = new ObserverPayload();
		this.payload.message = message;
		this.payload.notificationType = type;
		this.payload.payload_notification = true;
		this.notifyObservers();
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
	
	
	@Override
	public void update( Observable subject , ObserverPayload message )
	{
		if( message.payload_themechange == true )
		{			
			this.setContentView( Theme.getInstance().getActivityTheme() );
		}
	}	

}
