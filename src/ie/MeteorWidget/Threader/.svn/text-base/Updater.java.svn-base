

package ie.MeteorWidget.Threader;

import ie.MeteorWidget.Observer.Observable;
import ie.MeteorWidget.Observer.Observer;
import ie.MeteorWidget.Observer.ObserverPayload;
import ie.MeteorWidget.Utils.Settings;
import java.util.ArrayList;
import java.util.Timer;
import android.widget.RemoteViews;


public class Updater implements Observable
{


	private ObserverPayload payload = null;
	private ArrayList< Observer > observers;
	private static Updater instance = null;	
	private Timer timerUpdater = null;


	private Updater()
	{
		this.observers = new ArrayList< Observer >();
	}


	public static Updater getInstance()
	{
		if( Updater.instance == null )
		{
			Updater.instance = new Updater();
		}

		return Updater.instance;
	}


	public void start()
	{
		this.stop();

		Settings opt = Settings.getInstance();
		int period = Integer.parseInt( opt.get( "meteor_widget_update_interval" , "900" ) ) * 1000;
		this.timerUpdater = new Timer();
		this.timerUpdater.scheduleAtFixedRate( new UpdateTimerTask() , 1000 , period );
		this.trackEvent( "Updater" , "Start Thread" , "Begin" , 1 );
	}


	public void stop()
	{
		try
		{
			if( this.timerUpdater != null )
			{
				this.timerUpdater.cancel();
				this.timerUpdater.purge();
				this.timerUpdater = null;				
			}
		}
		catch ( Exception e )
		{
			this.trackEvent( "Updater" , "Stop Thread Error" , e.getMessage() , 1 );
		}
	}


	public boolean isRunning()
	{
		return ( this.timerUpdater == null ) ? false : true;
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

}
