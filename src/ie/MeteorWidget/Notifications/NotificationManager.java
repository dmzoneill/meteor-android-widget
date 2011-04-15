

package ie.MeteorWidget.Notifications;

import ie.MeteorWidget.R;
import ie.MeteorWidget.Observer.Observable;
import ie.MeteorWidget.Observer.Observer;
import ie.MeteorWidget.Observer.ObserverPayload;
import ie.MeteorWidget.Themes.Theme;
import ie.MeteorWidget.Utils.Settings;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class NotificationManager implements Observer
{


	private static NotificationManager instance = null;


	private NotificationManager()
	{

	}


	public static NotificationManager getInstance()
	{
		if( NotificationManager.instance == null )
		{
			NotificationManager.instance = new NotificationManager();
		}

		return NotificationManager.instance;
	}


	public void showNotification( final String message , final int type )
	{
		if( message == null )
		{
			return;
		}

		Handler handler = new Handler( Looper.getMainLooper() );
		handler.post( new Runnable()
		{


			@Override
			public void run()
			{
				Settings opt = Settings.getInstance();	
				Context context = opt.getContext();
				Theme theme = Theme.getInstance();

				if( context != null )
				{
					if( type == 1 && opt.get( "meteor_widget_update_notifications" , false ) )
					{
						return;
					}

					if( type == 0 && opt.get( "meteor_widget_error_notifications" , false ) )
					{
						return;
					}

					LayoutInflater inflater = ( LayoutInflater ) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
					View layout = inflater.inflate( Theme.getInstance().getToastTheme() , null );

					TextView text = ( TextView ) layout.findViewById( R.id.text );
					text.setText( message );
					text.setTextColor( theme.getNotificationColor() );
					text.setTextSize( theme.getNotificationFontSize() );
					text.setTypeface( theme.getNotificationFontFace() );					

					Toast toast = new Toast( context );
					toast.setGravity( Gravity.BOTTOM , 0 , 72 );
					toast.setDuration( Toast.LENGTH_SHORT );
					toast.setView( layout );
					toast.show();
				}
			}
		} );

		try
		{
			Thread.sleep( 1500 );
		}
		catch ( Exception p )
		{
		}
	}
	
	
	@Override
	public void update( Observable subject , ObserverPayload message )
	{
		if( message.payload_notification == true )
		{
			this.showNotification( message.message , message.notificationType );
		}
	}

}
