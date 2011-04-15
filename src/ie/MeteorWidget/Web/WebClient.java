

package ie.MeteorWidget.Web;

import ie.MeteorWidget.MeteorWidgetProvider;
import ie.MeteorWidget.Notifications.NotificationManager;
import ie.MeteorWidget.Observer.Observable;
import ie.MeteorWidget.Observer.Observer;
import ie.MeteorWidget.Observer.ObserverPayload;
import ie.MeteorWidget.Utils.GoogleAnalytics;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerPNames;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import android.widget.RemoteViews;


public class WebClient implements Observable
{


	private ObserverPayload payload = null;
	private ArrayList< Observer > observers;
	private List< Cookie > cookies;
	private DefaultHttpClient httpclient;
	private HttpGet httpget;
	private HttpResponse response;
	private HttpEntity entity;
	private HttpPost httppost;
	private List< NameValuePair > postFields;

	private String packageWebTexts;
	private String packageData;
	private String packageDataAddon;
	private String packageDataBonus;
	private String packageDataTotal;

	private String packageTexts;
	private String packageTextsAddon;
	private String packageTextsBonus;
	private String packageTextsTotal;

	private String packageMinutes;
	private String packageMinutesAddon;
	private String packageMinutesBonus;
	private String packageMinutesTotal;
	
	private static String lastPackageMinutesTotal = "";
	private static String lastPackageTextsTotal = "";
	private static String lastPackageDataTotal = "";
	private static String lastPackageWebTextsTotal = "";

	private String result;
	private boolean error;

	private String username;
	private String password;


	public WebClient( String username , String password )
	{
		this.error = false;
		this.observers = new ArrayList< Observer >();
		this.username = username;
		this.password = password;
		this.attach( GoogleAnalytics.getInstance() );
		this.attach( NotificationManager.getInstance() );
		this.attach( MeteorWidgetProvider.getInstance() );
	}


	public void run()
	{
		if( this.createSession() )
		{
			if( this.doLogin( this.username , this.password ) )
			{
				if( this.grabUsageInformation() )
				{
					this.readUsageInformation();
				}
				if( this.grabWebTextsUsageInformation() )
				{
					this.readWebTextUsageInformation();
				}
			}
			else
			{
				if( this.result.indexOf( "an additional security feature" ) > -1 )
				{
					if( this.grabUsageInformation() )
					{
						this.error = false;
						this.readUsageInformation();
					}
					if( this.grabWebTextsUsageInformation() )
					{
						this.readWebTextUsageInformation();
					}
				}
			}
		}

		this.closeSession();
	}


	private boolean createSession()
	{
		try
		{
			SchemeRegistry schemeRegistry = new SchemeRegistry();
			schemeRegistry.register( new Scheme( "http" , PlainSocketFactory.getSocketFactory() , 80 ) );
			schemeRegistry.register( new Scheme( "https" , new EasySSLSocketFactory() , 443 ) );

			HttpParams params = new BasicHttpParams();
			params.setParameter( ConnManagerPNames.MAX_TOTAL_CONNECTIONS , 30 );
			params.setParameter( ConnManagerPNames.MAX_CONNECTIONS_PER_ROUTE , new ConnPerRouteBean( 30 ) );
			params.setParameter( CoreProtocolPNames.USE_EXPECT_CONTINUE , false );
			HttpProtocolParams.setVersion( params , HttpVersion.HTTP_1_1 );

			ClientConnectionManager cm = new SingleClientConnManager( params , schemeRegistry );

			this.httpclient = new DefaultHttpClient( cm , params );
			this.httpget = new HttpGet( "https://www.mymeteor.ie/" );
			this.response = httpclient.execute( httpget );

			this.entity = response.getEntity();

			if( this.entity != null )
			{
				this.entity.consumeContent();
				this.cookies = this.httpclient.getCookieStore().getCookies();
				this.notification( "Created session" , 1 );
				return true;
			}

			this.notification( "Session creation failed" , 0 );
			this.trackEvent( "Webclient" , "sessionCreate" , "failed" , 1 );
			this.error = true;
			return false;

		}
		catch ( Exception e )
		{
			this.trackEvent( "Webclient" , "sessionCreate" , "failed" , 1 );
			this.notification( e.getMessage() , 0 );
			this.error = true;
			return false;
		}
	}


	private void closeSession()
	{
		try
		{
			this.cookies.clear();
			this.httpclient.getConnectionManager().shutdown();
		}
		catch ( Exception e )
		{
			this.trackEvent( "Webclient" , "sessionClose" , "failed" , 1 );
		}
	}


	private boolean doLogin( String username , String password )
	{
		try
		{
			this.httppost = new HttpPost( "https://www.mymeteor.ie/go/mymeteor-login-manager" );
			this.postFields = new ArrayList< NameValuePair >();
			this.postFields.add( new BasicNameValuePair( "username" , username ) );
			this.postFields.add( new BasicNameValuePair( "userpass" , password ) );

			this.httppost.setEntity( new UrlEncodedFormEntity( this.postFields , HTTP.UTF_8 ) );

			this.response = this.httpclient.execute( this.httppost );
			this.cookies = this.httpclient.getCookieStore().getCookies();
			this.readPage();

			if( this.result.indexOf( "Log out" ) > -1 )
			{
				this.notification( "logged in" , 1 );
				return true;
			}

			this.notification( "Login failed. Attempting work around" , 0 );
			this.trackEvent( "Webclient" , "loginFailed" , "failed" , 1 );
			this.error = true;
			return false;
		}
		catch ( Exception e )
		{
			this.notification( e.getMessage() , 0 );
			this.trackEvent( "Webclient" , "loginFailed" ,  e.getMessage() , 1 );
			this.error = true;
			return false;
		}

	}
	
	
	private boolean grabWebTextsUsageInformation()
	{
		try
		{
			this.httpget = new HttpGet( "https://www.mymeteor.ie/go/freewebtext" );
			this.response = httpclient.execute( this.httpget );
			this.readPage();

			if( this.result.indexOf( "numfreesmstext" ) > -1 )
			{
				this.notification( "Aquired web text information" , 1 );
				return true;
			}

			this.notification( "Web text unavailable" , 0 );
			this.trackEvent( "Webclient" , "grabWebTextUsageInformationFailed" ,  "Web text Balance unavailable" , 1 );
			this.error = true;
			return false;
		}
		catch ( Exception e )
		{
			this.notification( e.getMessage() , 0 );
			this.error = true;
			this.trackEvent( "Webclient" , "grabUsageInformationFailed" ,  e.getMessage() , 1 );
			return false;
		}

	}


	private boolean grabUsageInformation()
	{
		try
		{
			this.httpget = new HttpGet( "https://www.mymeteor.ie/go/account-details/check-balance/check-balance" );
			this.response = httpclient.execute( this.httpget );
			this.readPage();

			if( this.result.indexOf( "Balance Remaining" ) > -1 )
			{
				this.notification( "Aquired balance information" , 1 );
				return true;
			}

			this.notification( "Balance unavailable" , 0 );
			this.trackEvent( "Webclient" , "grabUsageInformationFailed" ,  "Balance unavailable" , 1 );
			this.error = true;
			return false;
		}
		catch ( Exception e )
		{
			this.notification( e.getMessage() , 0 );
			this.error = true;
			this.trackEvent( "Webclient" , "grabUsageInformationFailed" ,  e.getMessage() , 1 );
			return false;
		}

	}


	private void readPage()
	{
		String html = "";
		try
		{
			InputStream in = this.response.getEntity().getContent();
			BufferedReader reader = new BufferedReader( new InputStreamReader( in ) );
			StringBuilder str = new StringBuilder();
			String line = null;
			while ( ( line = reader.readLine() ) != null )
			{
				str.append( line + "\n" );
			}
			in.close();
			html = str.toString();
		}
		catch ( Exception e )
		{
			this.notification( e.getMessage() , 0 );
			this.trackEvent( "Webclient" , "readPageFailed" ,  e.getMessage() , 1 );
			this.error = true;
			html = e.getMessage();
		}		

		this.result = html;
	}


	private void readUsageInformation()
	{
		Pattern row = Pattern.compile( "<tr[^>]*>(.*?)</tr>" , Pattern.MULTILINE | Pattern.CASE_INSENSITIVE | Pattern.DOTALL );
		Matcher rowmatch = row.matcher( this.result );
		while ( rowmatch.find() )
		{
			// data
			if( rowmatch.group().contains( "Inclusive data" ) )
			{
				this.packageData = getColumText( rowmatch.group() );
			}
			else if( rowmatch.group().contains( "Internet Add-on" ) )
			{
				this.packageDataAddon = getColumText( rowmatch.group() );
			}
			else if( rowmatch.group().contains( "Free Bonus Data" ) )
			{
				this.packageDataBonus = getColumText( rowmatch.group() );
			}

			// minutes
			else if( rowmatch.group().contains( "Inclusive Anytime Minutes" ) )
			{
				this.packageMinutes = getColumText( rowmatch.group() );
			}
			else if( rowmatch.group().contains( "Extra Minute Add-on" ) )
			{
				this.packageMinutesAddon = getColumText( rowmatch.group() );
			}
			else if( rowmatch.group().contains( "Free Bonus Minutes" ) )
			{
				this.packageMinutesBonus = getColumText( rowmatch.group() );
			}

			// texts
			else if( rowmatch.group().contains( "Inclusive Anytime Text" ) )
			{
				this.packageTexts = getColumText( rowmatch.group() );
			}
			else if( rowmatch.group().contains( "Extra Text Add-on" ) )
			{
				this.packageTextsAddon = getColumText( rowmatch.group() );
			}
			else if( rowmatch.group().contains( "Free Bonus Texts" ) )
			{
				this.packageTextsBonus = getColumText( rowmatch.group() );
			}
		}

		this.calculateTotals();
	}
	
	
	private void readWebTextUsageInformation()
	{
		Pattern row = Pattern.compile( "(<input type=\"text\" id=\"numfreesmstext\" value=\").*(\" disabled size=2>)" , Pattern.CASE_INSENSITIVE | Pattern.DOTALL );
		Matcher rowmatch = row.matcher( this.result );
		while ( rowmatch.find() )
		{
			String[] data = rowmatch.group().split("\"");
			this.packageWebTexts = data[ 5 ];		
			WebClient.lastPackageWebTextsTotal = data[ 5 ];
			break;
		}

		this.calculateTotals();
	}


	private void calculateTotals()
	{
		try
		{
			int total;

			try
			{
				total = this.getMB( this.packageData ) + this.getMB( this.packageDataAddon ) + this.getMB( this.packageDataBonus );

				if( total / 1024 > 0 )
				{
					int gb = total / 1024;
					int mb = ( ( total % 1024 ) * 100 ) / 1024;
					this.packageDataTotal = gb + "." + mb + " GB";
					WebClient.lastPackageDataTotal = gb + "." + mb + " GB";
				}
				else
				{
					this.packageDataTotal = String.valueOf( total ) + " MB";
					WebClient.lastPackageDataTotal = String.valueOf( total ) + " MB";
				}
			}
			catch ( Exception e )
			{
				this.packageDataTotal = "0";
				WebClient.lastPackageDataTotal = "";
				this.trackEvent( "Webclient" , "calculateTotalsFailed" ,  e.getMessage() , 1 );
			}

			total = calculateAmountTotals( this.packageTexts , this.packageTextsAddon , this.packageTextsBonus );
			this.packageTextsTotal = String.valueOf( total );
			WebClient.lastPackageTextsTotal = String.valueOf( total );

			total = calculateAmountTotals( this.packageMinutes , this.packageMinutesAddon , this.packageMinutesBonus );
			this.packageMinutesTotal = String.valueOf( total );
			WebClient.lastPackageMinutesTotal = String.valueOf( total );
		}
		catch ( Exception e )
		{
			WebClient.lastPackageTextsTotal = "";
			WebClient.lastPackageMinutesTotal = "";
			WebClient.lastPackageDataTotal = "";
			
			this.packageDataTotal = "0";
			this.packageTextsTotal = "0";
			this.packageMinutesTotal = "0";
			this.trackEvent( "Webclient" , "calculateTotalsFailed" ,  e.getMessage() , 1 );
		}
	}


	private int getMB( String dataValue )
	{
		int total = 0;
		int left = 0;
		int right = 0;

		String[] split = dataValue.trim().split( " " );
		String dataDouble = split[ 0 ].trim();
		String dataType = split[ 1 ].trim().toUpperCase();

		if( dataDouble.indexOf( "." ) > -1 )
		{
			int start = 0;
			int end = dataDouble.indexOf( "." );
			left = Integer.parseInt( dataDouble.substring( start , end ) );
			right = Integer.parseInt( dataDouble.substring( end + 1 , dataDouble.length() ) );
		}
		else
		{
			left = Integer.parseInt( dataDouble );
		}

		if( dataType.compareTo( "KB" ) == 0 )
		{
			left = left + right;
		}
		else if( dataType.compareTo( "MB" ) == 0 )
		{
			left = ( left * 1024 ) + ( right * 10 );
		}
		else
		{
			left = ( left * 1024 * 1024 ) + ( right * 10 * 1024 );
		}

		System.out.println( left );

		total = left / 1024;

		return total;
	}


	private int calculateAmountTotals( String one , String two , String three )
	{
		int total = 0;

		total += Integer.parseInt( one );
		total += Integer.parseInt( two );
		total += Integer.parseInt( three );

		return total;
	}


	private String getColumText( String tr )
	{
		String[] cols = tr.split( "<td>" );
		String[] data = cols[ 4 ].split( "</td>" );
		return data[ 0 ];
	}


	public String getMinutes()
	{
		return "" + this.packageMinutesTotal;
	}


	public String getTexts()
	{
		return "" + this.packageTextsTotal;
	}


	public String getData()
	{
		return "" + this.packageDataTotal;
	}
	
	
	public String getWebTexts()
	{
		return "" + this.packageWebTexts;
	}
	
	
	public static String getLastMinutes()
	{
		return "" + WebClient.lastPackageMinutesTotal;
	}


	public static String getLastTexts()
	{
		return "" + WebClient.lastPackageTextsTotal;
	}


	public static String getLastData()
	{
		return "" + WebClient.lastPackageDataTotal;
	}
	
	public static String getLastWebTexts()
	{
		return "" + WebClient.lastPackageWebTextsTotal;
	}


	public Boolean getErrorStatus()
	{
		return this.error;
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
