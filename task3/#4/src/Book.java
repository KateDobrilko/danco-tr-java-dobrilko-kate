public class Book
{
	private String name;
	private String author;
	private boolean hasAReader;
	private Reader reader;

	public Book(String name, String author)
	{
		this.name = name;
		this.author = author;
		this.hasAReader = false;
	}

	public String getName()
	{
		return name;
	}

	public String getAuthor()
	{
		return author;
	}

	public boolean getHasAReader()
	{
		return hasAReader;
	}

	public Reader getReader()
	{

		return reader;

	}

	public void setReader(Reader reader)
	{
		this.reader = reader;
		if (this.reader != null)
		{
			this.hasAReader = true;
		}

		else
		{
			this.hasAReader = false;
		}

	}

}
