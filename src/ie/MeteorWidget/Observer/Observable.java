package ie.MeteorWidget.Observer;

import android.widget.RemoteViews;



public interface Observable
{
	public void attach( Observer observer );
    public void detach( Observer observer );
    public void notifyObservers();
    public void track( String tracked );
    public void trackEvent( String tracked , String action , String label , int value );
    public void trackDispatch();
    public void updateWidgetView( RemoteViews view );
    public void notification( String message , int type );
}
