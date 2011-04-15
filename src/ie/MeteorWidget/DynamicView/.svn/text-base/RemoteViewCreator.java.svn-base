

package ie.MeteorWidget.DynamicView;

import ie.MeteorWidget.MeteorActivity;
import ie.MeteorWidget.MeteorWidgetProvider;
import ie.MeteorWidget.R;
import ie.MeteorWidget.Notifications.NotificationManager;
import ie.MeteorWidget.Observer.Observable;
import ie.MeteorWidget.Observer.Observer;
import ie.MeteorWidget.Observer.ObserverPayload;
import ie.MeteorWidget.Themes.Theme;
import ie.MeteorWidget.Utils.GoogleAnalytics;
import ie.MeteorWidget.Utils.Settings;
import ie.MeteorWidget.Web.WebClient;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.widget.RemoteViews;


public class RemoteViewCreator implements Observable
{


	private ArrayList< Observer > observers;
	private WebClient client;
	private ObserverPayload payload = null;


	public RemoteViewCreator()
	{
		this.observers = new ArrayList< Observer >();
		this.client = null;
		this.attach( GoogleAnalytics.getInstance() );
		this.attach( NotificationManager.getInstance() );
		this.attach( MeteorWidgetProvider.getInstance() );
	}


	private SpannableString getTextAppearance( String text , int bold , String typeface , int fontsize , int color )
	{
		SpannableString span = new SpannableString( text );
		TextAppearanceSpan headerSpan = new TextAppearanceSpan( typeface , bold , fontsize , new ColorStateList( new int[][] { new int[] { android.R.attr.state_window_focused } , new int[ 0 ] , } , new int[] { color , color , } ) , new ColorStateList( new int[][] { new int[] { android.R.attr.state_window_focused } , new int[ 0 ] , } , new int[] { color , color , } ) );

		span.setSpan( headerSpan , 0 , span.length() , 0 );

		return span;
	}


	private void resetWidget()
	{
		Settings opt = Settings.getInstance();		
		Context context = opt.getContext();

		RemoteViews updatedView = new RemoteViews( context.getPackageName() , Theme.getInstance().getWidgetTheme() );

		Theme theme = Theme.getInstance();

		updatedView.setTextViewText( R.id.theme_header_title , this.getTextAppearance( "Bill Pay" , Typeface.BOLD , theme.getHeaderTypeFace() , theme.getHeaderFontSize() , theme.getHeaderColor() ) );
		updatedView.setTextViewText( R.id.theme_header_subtitle , this.getTextAppearance( "Remaining" , Typeface.NORMAL , theme.getHeaderTypeFace() , theme.getHeaderSubTitleFontSize() , theme.getHeaderColor() ) );

		updatedView.setTextViewText( R.id.minutesleft , this.getTextAppearance( "standby.." , Typeface.BOLD , theme.getBodyTypeFace() , theme.getBodyFontSize() , theme.getBodyColorValue() ) );
		updatedView.setTextViewText( R.id.textsleft , this.getTextAppearance( "standby.." , Typeface.BOLD , theme.getBodyTypeFace() , theme.getBodyFontSize() , theme.getBodyColorValue() ) );
		updatedView.setTextViewText( R.id.dataleft , this.getTextAppearance( "standby.." , Typeface.BOLD , theme.getBodyTypeFace() , theme.getBodyFontSize() , theme.getBodyColorValue() ) );
		updatedView.setTextViewText( R.id.webtextsleft , this.getTextAppearance( "standby.." , Typeface.BOLD , theme.getBodyTypeFace() , theme.getBodyFontSize() , theme.getBodyColorValue() ) );

		updatedView.setTextViewText( R.id.minutes , this.getTextAppearance( "Minutes" , Typeface.NORMAL , theme.getBodyTypeFace() , theme.getBodyFontSize() , theme.getBodyColor() ) );
		updatedView.setTextViewText( R.id.texts , this.getTextAppearance( "Texts" , Typeface.NORMAL , theme.getBodyTypeFace() , theme.getBodyFontSize() , theme.getBodyColor() ) );
		updatedView.setTextViewText( R.id.data , this.getTextAppearance( "Data" , Typeface.NORMAL , theme.getBodyTypeFace() , theme.getBodyFontSize() , theme.getBodyColor() ) );
		updatedView.setTextViewText( R.id.webtexts , this.getTextAppearance( "Web Texts" , Typeface.NORMAL , theme.getBodyTypeFace() , theme.getBodyFontSize() , theme.getBodyColor() ) );

		Intent defineIntent = new Intent( Intent.ACTION_VIEW );
		PendingIntent pendingIntent = PendingIntent.getActivity( context , 0 , defineIntent , 0 );
		updatedView.setOnClickPendingIntent( R.id.configureButton , pendingIntent );

		Intent configIntent = new Intent( context , MeteorActivity.class );
		configIntent.setAction( MeteorWidgetProvider.ACTION_WIDGET_CONFIGURE );
		PendingIntent configPendingIntent = PendingIntent.getActivity( context , 0 , configIntent , 0 );
		updatedView.setOnClickPendingIntent( R.id.configureButton , configPendingIntent );

		this.track( "widgetReset" );
		this.updateWidgetView( updatedView );
	}


	public void changeWidgetTheme()
	{
		Settings opt = Settings.getInstance();	
		Context context = opt.getContext();

		RemoteViews updatedView = new RemoteViews( context.getPackageName() , Theme.getInstance().getWidgetTheme() );

		Theme theme = Theme.getInstance();

		String totalData = ( WebClient.getLastData() == "" ) ? "standby.." : WebClient.getLastData();
		String totalMins = ( WebClient.getLastMinutes() == "" ) ? "standby.." : WebClient.getLastMinutes();
		String totalTexts = ( WebClient.getLastTexts() == "" ) ? "standby.." : WebClient.getLastTexts();
		String totalWebTexts = ( WebClient.getLastWebTexts() == "" ) ? "standby.." : WebClient.getLastWebTexts();

		updatedView.setTextViewText( R.id.theme_header_title , this.getTextAppearance( "Bill Pay" , Typeface.BOLD , theme.getHeaderTypeFace() , theme.getHeaderFontSize() , theme.getHeaderColor() ) );
		updatedView.setTextViewText( R.id.theme_header_subtitle , this.getTextAppearance( "Remaining" , Typeface.NORMAL , theme.getHeaderTypeFace() , theme.getHeaderSubTitleFontSize() , theme.getHeaderColor() ) );

		updatedView.setTextViewText( R.id.minutesleft , this.getTextAppearance( totalMins , Typeface.BOLD , theme.getBodyTypeFace() , theme.getBodyFontSize() , theme.getBodyColorValue() ) );
		updatedView.setTextViewText( R.id.textsleft , this.getTextAppearance( totalTexts , Typeface.BOLD , theme.getBodyTypeFace() , theme.getBodyFontSize() , theme.getBodyColorValue() ) );
		updatedView.setTextViewText( R.id.dataleft , this.getTextAppearance( totalData , Typeface.BOLD , theme.getBodyTypeFace() , theme.getBodyFontSize() , theme.getBodyColorValue() ) );
		updatedView.setTextViewText( R.id.webtextsleft , this.getTextAppearance( totalWebTexts , Typeface.BOLD , theme.getBodyTypeFace() , theme.getBodyFontSize() , theme.getBodyColorValue() ) );
		
		updatedView.setTextViewText( R.id.minutes , this.getTextAppearance( "Minutes" , Typeface.NORMAL , theme.getBodyTypeFace() , theme.getBodyFontSize() , theme.getBodyColor() ) );
		updatedView.setTextViewText( R.id.texts , this.getTextAppearance( "Texts" , Typeface.NORMAL , theme.getBodyTypeFace() , theme.getBodyFontSize() , theme.getBodyColor() ) );
		updatedView.setTextViewText( R.id.data , this.getTextAppearance( "Data" , Typeface.NORMAL , theme.getBodyTypeFace() , theme.getBodyFontSize() , theme.getBodyColor() ) );
		updatedView.setTextViewText( R.id.webtexts , this.getTextAppearance( "Web Texts" , Typeface.NORMAL , theme.getBodyTypeFace() , theme.getBodyFontSize() , theme.getBodyColor() ) );

		Intent defineIntent = new Intent( Intent.ACTION_VIEW );
		PendingIntent pendingIntent = PendingIntent.getActivity( context , 0 , defineIntent , 0 );
		updatedView.setOnClickPendingIntent( R.id.configureButton , pendingIntent );

		Intent configIntent = new Intent( context , MeteorActivity.class );
		configIntent.setAction( MeteorWidgetProvider.ACTION_WIDGET_CONFIGURE );
		PendingIntent configPendingIntent = PendingIntent.getActivity( context , 0 , configIntent , 0 );
		updatedView.setOnClickPendingIntent( R.id.configureButton , configPendingIntent );

		this.updateWidgetView( updatedView );
	}


	private void disabledWidget()
	{
		Settings opt = Settings.getInstance();	
		Context context = opt.getContext();

		Theme theme = Theme.getInstance();

		RemoteViews updatedView = new RemoteViews( context.getPackageName() , Theme.getInstance().getWidgetTheme() );
		updatedView.setTextViewText( R.id.minutesleft , this.getTextAppearance( "standby.." , Typeface.BOLD , theme.getBodyTypeFace() , theme.getBodyFontSize() , theme.getBodyColorValue() ) );
		updatedView.setTextViewText( R.id.textsleft , this.getTextAppearance( "standby.." , Typeface.BOLD , theme.getBodyTypeFace() , theme.getBodyFontSize() , theme.getBodyColorValue() ) );
		updatedView.setTextViewText( R.id.dataleft , this.getTextAppearance( "standby.." , Typeface.BOLD , theme.getBodyTypeFace() , theme.getBodyFontSize() , theme.getBodyColorValue() ) );
		updatedView.setTextViewText( R.id.webtextsleft , this.getTextAppearance( "standby.." , Typeface.BOLD , theme.getBodyTypeFace() , theme.getBodyFontSize() , theme.getBodyColorValue() ) );

		Intent defineIntent = new Intent( Intent.ACTION_VIEW );
		PendingIntent pendingIntent = PendingIntent.getActivity( context , 0 , defineIntent , 0 );
		updatedView.setOnClickPendingIntent( R.id.configureButton , pendingIntent );

		Intent configIntent = new Intent( context , MeteorActivity.class );
		configIntent.setAction( MeteorWidgetProvider.ACTION_WIDGET_CONFIGURE );
		PendingIntent configPendingIntent = PendingIntent.getActivity( context , 0 , configIntent , 0 );
		updatedView.setOnClickPendingIntent( R.id.configureButton , configPendingIntent );

		this.track( "widgetDisabled" );
		this.updateWidgetView( updatedView );
	}


	public void createView()
	{
		Settings opt = Settings.getInstance();	
		Context context = opt.getContext();
		
		if( opt.get( "meteor_widget_main_option" , true ) == true )
		{
			this.disabledWidget();
			return;
		}

		if( opt.get( "meteor_widget_force_update" , "0" ).compareTo( "0" ) == 0 )
		{
			opt.set( "meteor_widget_force_update" , "1" );
			this.changeWidgetTheme();
			return;
		}

		this.resetWidget();

		Theme theme = Theme.getInstance();

		this.trackDispatch();

		String number = opt.get( "meteor_widget_phone_number" , "#####" );
		String pin = opt.get( "meteor_widget_phone_pin" , "#####" );
		String interval = opt.get( "meteor_widget_update_interval" , "900" );

		Calendar now = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat( "hh:mm aaa" );

		opt.set( "meteor_widget_last_update" , sdf.format( now.getTime() ) );

		int newMins = Integer.parseInt( interval ) / 60;
		if( newMins > 59 )
		{
			now.add( Calendar.HOUR_OF_DAY , newMins / 60 );
			now.add( Calendar.MINUTE , newMins % 60 );
		}
		else
		{
			now.add( Calendar.MINUTE , newMins );
		}

		opt.set( "meteor_widget_next_update" , sdf.format( now.getTime() ) );

		if( number == "#####" || pin == "#####" )
		{
			this.resetWidget();

			if( this.client != null )
			{
				if( this.client.getErrorStatus() == true )
				{
					this.notification( "Login settings unset" , 0 );
					this.trackEvent( "RemoteViewCreator" , "Failure" , "Login settings unset" , 1 );
				}
			}
		}
		else
		{
			RemoteViews updatedView = new RemoteViews( context.getPackageName() , Theme.getInstance().getWidgetTheme() );

			Intent defineIntent = new Intent( Intent.ACTION_VIEW );
			PendingIntent pendingIntent = PendingIntent.getActivity( context , 0 , defineIntent , 0 );
			updatedView.setOnClickPendingIntent( R.id.configureButton , pendingIntent );

			Intent configIntent = new Intent( context , MeteorActivity.class );
			configIntent.setAction( MeteorWidgetProvider.ACTION_WIDGET_CONFIGURE );
			PendingIntent configPendingIntent = PendingIntent.getActivity( context , 0 , configIntent , 0 );
			updatedView.setOnClickPendingIntent( R.id.configureButton , configPendingIntent );

			try
			{
				this.client = new WebClient( number , pin );
				this.client.run();
			}
			catch ( Exception e )
			{
				this.notification( "Internet unavailable" , 0 );
				this.trackEvent( "RemoteViewCreator" , "Failure" , e.getMessage() , 1 );
				return;
			}

			if( this.client.getErrorStatus() == false )
			{
				updatedView.setTextViewText( R.id.minutesleft , this.getTextAppearance( this.client.getMinutes() , Typeface.BOLD , theme.getBodyTypeFace() , theme.getBodyFontSize() , theme.getBodyColorValue() ) );
				updatedView.setTextViewText( R.id.textsleft , this.getTextAppearance( this.client.getTexts() , Typeface.BOLD , theme.getBodyTypeFace() , theme.getBodyFontSize() , theme.getBodyColorValue() ) );
				updatedView.setTextViewText( R.id.dataleft , this.getTextAppearance( this.client.getData() , Typeface.BOLD , theme.getBodyTypeFace() , theme.getBodyFontSize() , theme.getBodyColorValue() ) );
				updatedView.setTextViewText( R.id.webtextsleft , this.getTextAppearance( this.client.getWebTexts() , Typeface.BOLD , theme.getBodyTypeFace() , theme.getBodyFontSize() , theme.getBodyColorValue() ) );
				
				this.updateWidgetView( updatedView );
				this.notification( "Updated" , 1 );
				this.trackEvent( "RemoteViewCreator" , "Success" , "" , 1 );
			}
			else
			{
				this.updateWidgetView( updatedView );
				this.trackEvent( "RemoteViewCreator" , "Failure" , "webclient failed" , 1 );
			}
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

}
