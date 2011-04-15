package ie.MeteorWidget.Utils;

import android.os.Build;

public class System
{
	private static System instance = null;
	
	private System()
	{
		
	}
	
	
	public static System getInstance()
	{
		if( System.instance == null )
		{
			System.instance = new System();
		}
		
		return System.instance;
	}
	
	public String getBoard()
	{
		return Build.BOARD;
	}
	
	public String getBootloader()
	{
		return Build.BOOTLOADER;
	}
	
	public String getBrand()
	{
		return Build.BRAND;
	}
	
	public String getCPU1()
	{
		return Build.CPU_ABI;
	}
	
	public String getCPU2()
	{
		return Build.CPU_ABI2;
	}
	
	public String getDevice()
	{
		return Build.DEVICE;
	}
	
	public String getDisplay()
	{
		return Build.DISPLAY;
	}
	
	public String getFingerprint()
	{
		return Build.FINGERPRINT;
	}
	
	public String getHardware()
	{
		return Build.HARDWARE;
	}
	
	public String getHost()
	{
		return Build.HOST;
	}

	public String getManufacturer()
	{
		return Build.MANUFACTURER;
	}
	
	public String getModel()
	{
		return Build.MODEL;
	}
	
	public String getProduct()
	{
		return Build.PRODUCT;
	}
	
	public String getRadio()
	{
		return Build.RADIO;
	}
	
	public String getType()
	{
		return Build.TYPE;
	}
	
	public String getTags()
	{
		return Build.TAGS;
	}
	
	public String getUser()
	{
		return Build.USER;
	}	
}
