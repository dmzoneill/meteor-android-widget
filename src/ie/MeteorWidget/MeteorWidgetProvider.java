

package ie.MeteorWidget;

import ie.MeteorWidget.DynamicView.RemoteViewCreator;
import ie.MeteorWidget.Notifications.NotificationManager;
import ie.MeteorWidget.Observer.Observable;
import ie.MeteorWidget.Observer.Observer;
import ie.MeteorWidget.Observer.ObserverPayload;
import ie.MeteorWidget.Threader.Updater;
import ie.MeteorWidget.Utils.GoogleAnalytics;
import ie.MeteorWidget.Utils.Settings;

import java.util.ArrayList;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;


public class MeteorWidgetProvider extends AppWidgetProvider implements Observer, Observable
{


	public static String ACTION_WIDGET_CONFIGURE = "ConfigureWidget";
	public static String ACTION_UPDATE = "ie.MeteorWidget.UPDATE_MY_WIDGET";
	private ObserverPayload payload = null;
	private ArrayList< Observer > observers;
	private static MeteorWidgetProvider instance = null;


	public MeteorWidgetProvider()
	{
		MeteorWidgetProvider.instance = this;

		this.observers = new ArrayList< Observer >();
		this.attach( GoogleAnalytics.getInstance() );
		this.attach( NotificationManager.getInstance() );
		this.attach( MeteorWidgetProvider.getInstance() );

		Updater updater = Updater.getInstance();
		updater.attach( GoogleAnalytics.getInstance() );
		updater.attach( NotificationManager.getInstance() );
		updater.attach( MeteorWidgetProvider.getInstance() );
	}


	@Override
	public void onUpdate( Context context , AppWidgetManager appWidgetManager , int[] appWidgetIds )
	{
		Settings opt = Settings.getInstance();
		opt.setContext( context );
		opt.setApplicationProvider( appWidgetManager );
		this.track( "onUpdated" );
		
		Updater updater = Updater.getInstance();
		updater.stop();
						
		if( updater.isRunning() == false )
		{
			updater.start();
		}
		
	}


	@Override
	public void onReceive( Context context , Intent intent )
	{
		Settings opt = Settings.getInstance();
		opt.setContext( context );
		opt.setApplicationProvider( opt.getApplicationProvider() );
		
		this.track( "onReceived" );
		
		Updater updater = Updater.getInstance();
		updater.stop();
		
		if( updater.isRunning() == false )
		{
			updater.start();
		}	
	}


	@Override
	public void onDisabled( Context context )
	{
		Settings opt = Settings.getInstance();
		opt.setContext( context );
		
		MeteorWidgetProvider.instance = null;
		
		Updater updater = Updater.getInstance();		
		
		this.track( "onDisabled" );

		if( updater.isRunning() == true )
		{
			updater.stop();
			System.exit( 0 );
		}
	}


	@Override
	public void onEnabled( Context context )
	{
		Settings opt = Settings.getInstance();
		opt.setContext( context );
		
		Updater updater = Updater.getInstance();
		
		this.track( "onEnabled" );		

		if( updater.isRunning() == false )
		{
			updater.start();
		}
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
			obj.update( this , this.payload );
		}
	}


	@Override
	public void update( Observable subject , ObserverPayload message )
	{		
		if( message.payload_update == true || message.payload_themechange == true )
		{
			Settings opt = Settings.getInstance();
			ComponentName thisWidget = new ComponentName( opt.getContext() , MeteorWidgetProvider.class );
			AppWidgetManager manager = AppWidgetManager.getInstance( opt.getContext() );
			manager.updateAppWidget( thisWidget , message.view );
		}
		
		if( message.payload_themechange == true )
		{
			RemoteViewCreator c = new RemoteViewCreator();			
			c.changeWidgetTheme();
		}
	}


	public static MeteorWidgetProvider getInstance()
	{
		return MeteorWidgetProvider.instance;
	}

}
