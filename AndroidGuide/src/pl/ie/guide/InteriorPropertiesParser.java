package pl.ie.guide;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.content.Context;
import android.util.Log;

public class InteriorPropertiesParser
{
	private String backgroundPath = null;
	private ArrayList<ClickablePoint> clickablePoints = null;
	
	public void parse(String path, Context context)
	{
		try
		{
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();

			InteriorHandler myXMLHandler = new InteriorHandler();
			xr.setContentHandler(myXMLHandler);
			xr.parse(new InputSource(context.getResources().openRawResource(
					R.raw.place1)));
			
			backgroundPath=myXMLHandler.getBackgroundPath();
			clickablePoints=myXMLHandler.getClickablePoints();

		} catch (Exception e)
		{
			System.out.println("XML Pasing Excpetion = " + e);
		}

		try
		{
			BufferedReader r = new BufferedReader(new InputStreamReader(context
					.getResources().openRawResource(R.raw.place1)));
			StringBuilder total = new StringBuilder();
			String line;
			while ((line = r.readLine()) != null)
			{
				total.append(line);
			}
			Log.d("READED", total.toString());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public String getBackgroundPath()
	{
		return backgroundPath;
	}

	public ArrayList<ClickablePoint> getClickablePoints()
	{
		return clickablePoints;
	}
}
