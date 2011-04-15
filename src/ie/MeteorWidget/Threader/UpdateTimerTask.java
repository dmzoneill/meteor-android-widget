

package ie.MeteorWidget.Threader;

import ie.MeteorWidget.MeteorWidgetProvider;
import ie.MeteorWidget.DynamicView.RemoteViewCreator;
import ie.MeteorWidget.Notifications.NotificationManager;
import ie.MeteorWidget.Observer.Observable;
import ie.MeteorWidget.Observer.Observer;
import ie.MeteorWidget.Observer.ObserverPayload;
import ie.MeteorWidget.Utils.GoogleAnalytics;
import java.util.ArrayList;
import java.util.TimerTask;
import android.widget.RemoteViews;


public class UpdateTimerTask extends TimerTask implements Observable
{


	private ArrayList< Observer > observers;
	private RemoteViewCreator creator;
	private ObserverPayload payload = null;


	public UpdateTimerTask()
	{
		this.observers = new ArrayList< Observer >();
		this.attach( GoogleAnalytics.getInstance() );
		this.attach( NotificationManager.getInstance() );
		this.attach( MeteorWidgetProvider.getInstance() );
	}


	@Override
	public void run()
	{
		this.creator = new RemoteViewCreator();
		this.creator.createView();
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
