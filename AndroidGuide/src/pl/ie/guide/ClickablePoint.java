package pl.ie.guide;

import java.util.ArrayList;

public class ClickablePoint
{
	private int id;
	private String name;
	private int posX, posY;
	private String shortDescription;
	private String fullDescription;
	private String link;
	private int pointType; //  0	EXIT_POINT, 1 TRANSITION_POINT, 2 POI_POINT
	private int connectionReference; //id of another interior
	private ArrayList<MultimediaObject> multimedia;

	public ClickablePoint(int posX, int posY, int type, int id)
	{
		super();
		this.posX = posX;
		this.posY = posY;
		this.pointType = type;
		this.id=id;
	}
	
	public ClickablePoint()
	{
		super();
	}

	public int getPosX()
	{
		return posX;
	}

	public int getPosY()
	{
		return posY;
	}

	public int getPointType()
	{
		return pointType;
	}

	public int getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getShortDescription()
	{
		return shortDescription;
	}

	public void setShortDescription(String shortDescription)
	{
		this.shortDescription = shortDescription;
	}

	public String getFullDescription()
	{
		return fullDescription;
	}

	public void setFullDescription(String fullDescription)
	{
		this.fullDescription = fullDescription;
	}

	public String getLink()
	{
		return link;
	}

	public void setLink(String link)
	{
		this.link = link;
	}

	public int getConnectionReference()
	{
		return connectionReference;
	}

	public void setConnectionReference(int connectionReference)
	{
		this.connectionReference = connectionReference;
	}

	public ArrayList<MultimediaObject> getMultimedia()
	{
		return multimedia;
	}

	public void setMultimedia(ArrayList<MultimediaObject> multimedia)
	{
		this.multimedia = multimedia;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public void setPosX(int posX)
	{
		this.posX = posX;
	}

	public void setPosY(int posY)
	{
		this.posY = posY;
	}

	public void setPointType(int pointType)
	{
		this.pointType = pointType;
	}
}
