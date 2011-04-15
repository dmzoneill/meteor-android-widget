

package ie.MeteorWidget.Utils;

import ie.MeteorWidget.Observer.Observable;
import ie.MeteorWidget.Observer.Observer;
import ie.MeteorWidget.Observer.ObserverPayload;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;


public class GoogleAnalytics implements Observer
{


	private GoogleAnalyticsTracker tracker;
	private static GoogleAnalytics instance = null;
	private boolean started = false;
	private int eventCounter;


	private GoogleAnalytics()
	{
		this.eventCounter = 0;
		this.started = false;
		this.tracker = GoogleAnalyticsTracker.getInstance();
	}


	public static GoogleAnalytics getInstance()
	{
		if( GoogleAnalytics.instance == null )
		{
			GoogleAnalytics.instance = new GoogleAnalytics();
		}

		GoogleAnalytics.instance.start();

		return GoogleAnalytics.instance;
	}


	private void start()
	{
		if( this.started == false )
		{
			Settings opt = Settings.getInstance();
			if( opt.getContext() != null )
			{
				this.tracker.start( "UA-1014155-7" , opt.getContext() );
				this.started = true;
			}
		}
	}


	private void track( String tracked )
	{
		Settings opt = Settings.getInstance();		

		if( opt.get( "meteor_widget_analytics_plugin_onupdated" , false ) != false && tracked.compareTo( "onUpdated" ) == 0 )
		{
			// meteor_widget_analytics_plugin_onupdated disabled by user
			return;
		}
		else if( opt.get( "meteor_widget_analytics_plugin_onenabled" , false ) != false && tracked.compareTo( "onEnabled" ) == 0 )
		{
			// meteor_widget_analytics_plugin_onenabled disabled by user
			return;
		}
		else if( opt.get( "meteor_widget_analytics_plugin_ondisabled" , false ) != false && tracked.compareTo( "onDisabled" ) == 0 )
		{
			// meteor_widget_analytics_plugin_ondisabled disabled by user
			return;
		}
		else if( opt.get( "meteor_widget_analytics_plugin_onreceived" , false ) != false && tracked.compareTo( "onReceived" ) == 0 )
		{
			// meteor_widget_analytics_plugin_onreceived disabled by user
			return;
		}
		else if( opt.get( "meteor_widget_analytics_plugin_preferences" , false ) != false && tracked.compareTo( "MeteorPreferences" ) == 0 )
		{
			// meteor_widget_analytics_plugin_preferences disabled by user
			return;
		}
		
		this.tracker.trackPageView( "/" + tracked );
		this.eventCounter++;
	}


	private void trackEvent( String tracked , String action , String label , int value )
	{
		Settings opt = Settings.getInstance();
		
		if( opt.get( "meteor_widget_analytics_plugin_force_update" , false ) != false && tracked.compareTo( "MeteorActivity" ) == 0 && action.compareTo( "Update" ) == 0 )
		{
			// meteor_widget_analytics_plugin_force_update disabled by user
			return;
		}
		else if( opt.get( "meteor_widget_analytics_update_interval" , false ) != false && tracked.compareTo( "MeteorPreferences" ) == 0 && action.compareTo( "Interval" ) == 0 )
		{
			// meteor_widget_analytics_update_interval disabled by user
			return;
		}
		else if( opt.get( "meteor_widget_analytics_notifications_status" , false ) != false && tracked.compareTo( "MeteorPreferences" ) == 0 && action.compareTo( "updateNotifications" ) == 0 )
		{
			// meteor_widget_analytics_notifications_status disabled by user
			return;
		}
		else if( opt.get( "meteor_widget_analytics_notifications_error" , false ) != false && tracked.compareTo( "MeteorPreferences" ) == 0 && action.compareTo( "errorNotifications" ) == 0 )
		{
			// meteor_widget_analytics_notifications_error disabled by user
			return;
		}
		else if( opt.get( "meteor_widget_analytics_plugin_disabled" , false ) != false && tracked.compareTo( "MeteorPreferences" ) == 0 && action.compareTo( "updateDisabled" ) == 0 )
		{
			// meteor_widget_analytics_plugin_disabled disabled by user
			return;
		}
		else if( opt.get( "meteor_widget_analytics_plugin_error" , false ) != false )
		{
			// meteor_widget_analytics_plugin_error disabled by user
			return;
		}
		else if ( opt.get( "meteor_widget_analytics_theme_preferences" , false ) != false )
		{
			// meteor_widget_analytics_theme_preferences disabled by user
			return;
		}
		
		this.tracker.trackEvent( tracked , action , label , value );
		this.eventCounter++;
	}


	private void dispatch()
	{
		this.tracker.dispatch();
		this.eventCounter = 0;
	}


	@Override
	public void update( Observable subject , ObserverPayload message )
	{
		if( message.payload_analytics == true )
		{
			Settings opt = Settings.getInstance();					
			
			if( opt.get( "meteor_widget_analytics_enabled" , false ) != false )
			{
				// analytics disabled by user
				return;
			}
			
			if( this.started == false )
			{
				this.start();
				return;
			}
			
			if( message.dispatch == true )
			{
				this.dispatch();
				return;
			}

			if( message.pageView != null )
			{
				this.track( message.pageView );
				return;
			}

			if( message.category != null )
			{
				this.trackEvent( message.category , message.action , message.label , message.value );
				return;
			}

		}
	}

}
