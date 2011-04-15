package ie.MeteorWidget.Themes;

import ie.MeteorWidget.R;
import ie.MeteorWidget.Utils.Settings;
import android.graphics.Color;
import android.graphics.Typeface;


public class Theme
{

	private static Theme instance = null;


	private Theme()
	{

	}


	public static Theme getInstance()
	{
		if( Theme.instance == null )
		{
			Theme.instance = new Theme();
		}

		return Theme.instance;
	}


	private String getThemeColor()
	{
		Settings opt = Settings.getInstance();
		
		return opt.get( "meteor_widget_theme_basecolor" , "orange" );
	}


	public int getActivityTheme()
	{
		String color = this.getThemeColor();
		int result;

		if( color.compareTo( "blue" ) == 0 )
		{
			result = R.layout.meteoractivity_layout_blue;
		}
		else if( color.compareTo( "cyan" ) == 0 )
		{
			result = R.layout.meteoractivity_layout_cyan;
		}
		else if( color.compareTo( "green" ) == 0 )
		{
			result = R.layout.meteoractivity_layout_green;
		}
		else if( color.compareTo( "orange" ) == 0 )
		{
			result = R.layout.meteoractivity_layout_orange;
		}
		else if( color.compareTo( "pink" ) == 0 )
		{
			result = R.layout.meteoractivity_layout_pink;
		}
		else if( color.compareTo( "purple" ) == 0 )
		{
			result = R.layout.meteoractivity_layout_purple;
		}
		else if( color.compareTo( "red" ) == 0 )
		{
			result = R.layout.meteoractivity_layout_red;
		}
		else if( color.compareTo( "yellow" ) == 0 )
		{
			result = R.layout.meteoractivity_layout_yellow;
		}
		else
		{
			result = R.layout.meteoractivity_layout_orange;
		}		
		
		return result;
	}


	public int getWidgetTheme()
	{
		String color = this.getThemeColor();
		int result;

		if( color.compareTo( "blue" ) == 0 )
		{
			result = R.layout.meteorwidget_layout_blue;
		}
		else if( color.compareTo( "cyan" ) == 0 )
		{
			result = R.layout.meteorwidget_layout_cyan;
		}
		else if( color.compareTo( "green" ) == 0 )
		{
			result = R.layout.meteorwidget_layout_green;
		}
		else if( color.compareTo( "orange" ) == 0 )
		{
			result = R.layout.meteorwidget_layout_orange;
		}
		else if( color.compareTo( "pink" ) == 0 )
		{
			result = R.layout.meteorwidget_layout_pink;
		}
		else if( color.compareTo( "purple" ) == 0 )
		{
			result = R.layout.meteorwidget_layout_purple;
		}
		else if( color.compareTo( "red" ) == 0 )
		{
			result = R.layout.meteorwidget_layout_red;
		}
		else if( color.compareTo( "yellow" ) == 0 )
		{
			result = R.layout.meteorwidget_layout_yellow;
		}
		else
		{
			result = R.layout.meteorwidget_layout_orange;
		}

		return result;
	}

	public int getToastTheme()
	{
		String color = this.getThemeColor();
		int result;

		if( color.compareTo( "blue" ) == 0 )
		{
			result = R.layout.toastview_blue;
		}
		else if( color.compareTo( "cyan" ) == 0 )
		{
			result = R.layout.toastview_cyan;
		}
		else if( color.compareTo( "green" ) == 0 )
		{
			result = R.layout.toastview_green;
		}
		else if( color.compareTo( "orange" ) == 0 )
		{
			result = R.layout.toastview_orange;
		}
		else if( color.compareTo( "pink" ) == 0 )
		{
			result = R.layout.toastview_pink;
		}
		else if( color.compareTo( "purple" ) == 0 )
		{
			result = R.layout.toastview_purple;
		}
		else if( color.compareTo( "red" ) == 0 )
		{
			result = R.layout.toastview_red;
		}
		else if( color.compareTo( "yellow" ) == 0 )
		{
			result = R.layout.toastview_yellow;
		}
		else
		{
			result = R.layout.toastview_orange;
		}

		return result;
	}
	
	
	public int getHeaderColor()
	{
		Settings opt = Settings.getInstance();
		
		String color = opt.get( "meteor_widget_theme_headerfontcolor" , "white" );
		int result;
		
		if( color.compareTo( "blue" ) == 0 )
		{
			result = Color.argb( 255 , 1 , 7 , 255 );
		}
		else if( color.compareTo( "cyan" ) == 0 )
		{
			result = Color.argb( 255 , 1 , 255 , 246 );
		}
		else if( color.compareTo( "green" ) == 0 )
		{
			result = Color.argb( 255 , 1 , 255 , 13 );
		}
		else if( color.compareTo( "orange" ) == 0 )
		{
			result = Color.argb( 255 , 255 , 73 , 1 );
		}
		else if( color.compareTo( "pink" ) == 0 )
		{
			result = Color.argb( 255 , 255 , 1 , 252 );
		}
		else if( color.compareTo( "purple" ) == 0 )
		{
			result = Color.argb( 255 , 115 , 1 , 255 );
		}
		else if( color.compareTo( "red" ) == 0 )
		{
			result = Color.argb( 255 , 255 , 1 , 13 );
		}
		else if( color.compareTo( "yellow" ) == 0 )
		{
			result = Color.argb( 255 , 255 , 240 , 1 );
		}
		else if( color.compareTo( "black" ) == 0 )
		{
			result = Color.argb( 255 , 0 , 0 , 0 );
		}
		else if( color.compareTo( "white" ) == 0 )
		{
			result = Color.argb( 255 , 255 , 255 , 255 );
		}
		else
		{
			result = Color.argb( 255 , 255 , 255 , 255 );
		}

		return result;
	}
	
	
	public int getBodyColor()
	{
		Settings opt = Settings.getInstance();
		
		String color = opt.get( "meteor_widget_theme_bodyfontcolordesc" , "white" );
		int result;
		
		if( color.compareTo( "blue" ) == 0 )
		{
			result = Color.argb( 255 , 1 , 7 , 255 );
		}
		else if( color.compareTo( "cyan" ) == 0 )
		{
			result = Color.argb( 255 , 1 , 255 , 246 );
		}
		else if( color.compareTo( "green" ) == 0 )
		{
			result = Color.argb( 255 , 1 , 255 , 13 );
		}
		else if( color.compareTo( "orange" ) == 0 )
		{
			result = Color.argb( 255 , 255 , 73 , 1 );
		}
		else if( color.compareTo( "pink" ) == 0 )
		{
			result = Color.argb( 255 , 255 , 1 , 252 );
		}
		else if( color.compareTo( "purple" ) == 0 )
		{
			result = Color.argb( 255 , 115 , 1 , 255 );
		}
		else if( color.compareTo( "red" ) == 0 )
		{
			result = Color.argb( 255 , 255 , 1 , 13 );
		}
		else if( color.compareTo( "yellow" ) == 0 )
		{
			result = Color.argb( 255 , 255 , 240 , 1 );
		}
		else if( color.compareTo( "black" ) == 0 )
		{
			result = Color.argb( 255 , 0 , 0 , 0 );
		}
		else if( color.compareTo( "white" ) == 0 )
		{
			result = Color.argb( 255 , 255 , 255 , 255 );
		}
		else
		{
			result = Color.argb( 255 , 255 , 255 , 255 );
		}

		return result;
	}
	
	
	public int getBodyColorValue()
	{
		Settings opt = Settings.getInstance();
		
		String color = opt.get( "meteor_widget_theme_bodyfontcolorvalue" , "black" );
		int result;
		
		if( color.compareTo( "blue" ) == 0 )
		{
			result = Color.argb( 255 , 1 , 7 , 255 );
		}
		else if( color.compareTo( "cyan" ) == 0 )
		{
			result = Color.argb( 255 , 1 , 255 , 246 );
		}
		else if( color.compareTo( "green" ) == 0 )
		{
			result = Color.argb( 255 , 1 , 255 , 13 );
		}
		else if( color.compareTo( "orange" ) == 0 )
		{
			result = Color.argb( 255 , 255 , 73 , 1 );
		}
		else if( color.compareTo( "pink" ) == 0 )
		{
			result = Color.argb( 255 , 255 , 1 , 252 );
		}
		else if( color.compareTo( "purple" ) == 0 )
		{
			result = Color.argb( 255 , 115 , 1 , 255 );
		}
		else if( color.compareTo( "red" ) == 0 )
		{
			result = Color.argb( 255 , 255 , 1 , 13 );
		}
		else if( color.compareTo( "yellow" ) == 0 )
		{
			result = Color.argb( 255 , 255 , 240 , 1 );
		}
		else if( color.compareTo( "black" ) == 0 )
		{
			result = Color.argb( 255 , 0 , 0 , 0 );
		}
		else if( color.compareTo( "white" ) == 0 )
		{
			result = Color.argb( 255 , 255 , 255 , 255 );
		}
		else
		{
			result = Color.argb( 255 , 255 , 255 , 255 );
		}

		return result;
	}
	
	
	public String getBodyTypeFace()
	{
		Settings opt = Settings.getInstance();
		String result;
		
		String font = opt.get( "meteor_widget_theme_bodyfont" , "black" );
		
		if( font.compareTo( "normal" ) == 0 )
		{
			result = Typeface.SANS_SERIF.toString();
		}
		else if( font.compareTo( "serif" ) == 0 )
		{
			result = Typeface.SERIF.toString();
		}
		else if( font.compareTo( "monospace" ) == 0 )
		{
			result = Typeface.MONOSPACE.toString();
		}
		else
		{
			result = Typeface.SANS_SERIF.toString();
		}
				
		return result;
	}	
	
	
	public String getHeaderTypeFace()
	{
		Settings opt = Settings.getInstance();
		String result;
		
		String font = opt.get( "meteor_widget_theme_headerfont" , "black" );
		
		if( font.compareTo( "normal" ) == 0 )
		{
			result = Typeface.SANS_SERIF.toString();
		}
		else if( font.compareTo( "serif" ) == 0 )
		{
			result = Typeface.SERIF.toString();
		}
		else if( font.compareTo( "monospace" ) == 0 )
		{
			result = Typeface.MONOSPACE.toString();
		}
		else
		{
			result = Typeface.SANS_SERIF.toString();
		}
				
		return result;
	}	
	
	
	public int getHeaderFontSize()
	{
		Settings opt = Settings.getInstance();
		int result;
		
		String fontsize = opt.get( "meteor_widget_theme_headerfontsize" , "18" );
		
		try
		{
			result = Integer.parseInt( fontsize );			
		}
		catch( Exception e )
		{			
			result = 18;
		}
		
		return result;	
	}
	
	
	public int getHeaderSubTitleFontSize()
	{	
		return this.getHeaderFontSize() - 4;	
	}
	
	
	public int getBodyFontSize()
	{	
		Settings opt = Settings.getInstance();
		int result;
		
		String fontsize = opt.get( "meteor_widget_theme_bodyfontsize" , "16" );
		
		try
		{
			result = Integer.parseInt( fontsize );			
		}
		catch( Exception e )
		{			
			result = 16;
		}
		
		return result;	
	}
	
	
	public int getNotificationColor()
	{
		Settings opt = Settings.getInstance();
		
		String color = opt.get( "meteor_widget_theme_notificationfont" , "white" );
		int result;
		
		if( color.compareTo( "blue" ) == 0 )
		{
			result = Color.argb( 255 , 1 , 7 , 255 );
		}
		else if( color.compareTo( "cyan" ) == 0 )
		{
			result = Color.argb( 255 , 1 , 255 , 246 );
		}
		else if( color.compareTo( "green" ) == 0 )
		{
			result = Color.argb( 255 , 1 , 255 , 13 );
		}
		else if( color.compareTo( "orange" ) == 0 )
		{
			result = Color.argb( 255 , 255 , 73 , 1 );
		}
		else if( color.compareTo( "pink" ) == 0 )
		{
			result = Color.argb( 255 , 255 , 1 , 252 );
		}
		else if( color.compareTo( "purple" ) == 0 )
		{
			result = Color.argb( 255 , 115 , 1 , 255 );
		}
		else if( color.compareTo( "red" ) == 0 )
		{
			result = Color.argb( 255 , 255 , 1 , 13 );
		}
		else if( color.compareTo( "yellow" ) == 0 )
		{
			result = Color.argb( 255 , 255 , 240 , 1 );
		}
		else if( color.compareTo( "black" ) == 0 )
		{
			result = Color.argb( 255 , 0 , 0 , 0 );
		}
		else if( color.compareTo( "white" ) == 0 )
		{
			result = Color.argb( 255 , 255 , 255 , 255 );
		}
		else
		{
			result = Color.argb( 255 , 255 , 255 , 255 );
		}

		return result;
	}
	
	
	public int getNotificationFontSize()
	{
		Settings opt = Settings.getInstance();
		int result;
		
		String fontsize = opt.get( "meteor_widget_theme_notificationfontsize" , "18" );
		
		try
		{
			result = Integer.parseInt( fontsize );			
		}
		catch( Exception e )
		{			
			result = 18;
		}
		
		return result;	
	}
	
	
	public Typeface getNotificationFontFace()
	{
		Settings opt = Settings.getInstance();
		Typeface result;
		
		String font = opt.get( "meteor_widget_theme_headerfont" , "black" );
		
		if( font.compareTo( "normal" ) == 0 )
		{
			result = Typeface.SANS_SERIF;
		}
		else if( font.compareTo( "serif" ) == 0 )
		{
			result = Typeface.SERIF;
		}
		else if( font.compareTo( "monospace" ) == 0 )
		{
			result = Typeface.MONOSPACE;
		}
		else
		{
			result = Typeface.SANS_SERIF;
		}		
		
		return result;
	}

}
