package pl.pwr.guide.interior;

public class MultimediaObject
{	
	private String name;
	private String description;
	private String path;
	private int type;
	
	public MultimediaObject()
	{
		super();
	}
	
	public MultimediaObject(String name, String description, String path, int type)
	{
		super();
		this.name = name;
		this.description = description;
		this.path = path;
		this.type=type;
	}
	
	public String getName()
	{
		return name;
	}

	public String getDescription()
	{
		return description;
	}

	public String getPath()
	{
		return path;
	}

	public int getType()
	{
		return type;
	}
	
	public boolean isImage()
	{
		return type == 0;
	}
	
	public boolean isVideo()
	{
		return type == 1;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public void setType(int type)
	{
		this.type = type;
	}
}
