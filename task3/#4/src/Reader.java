public class Reader
{
	private String firstName;
	private String lastName;
	private boolean hasBooks;
	private Book[] books;
	private int currentNumberOfBooks;

	public Reader(String firstName, String lastName, int limitOfBooks)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		books = new Book[limitOfBooks];
		currentNumberOfBooks = 0;

	}

	public void takeBook(Book Book)
	{
		if (currentNumberOfBooks < books.length)
		{

			for (int i = 0; i < books.length; i++)
			{
				if (books[i] == null)
				{
					books[i] = Book;
					currentNumberOfBooks++;
					hasBooks = true;
					break;
				}
			}

		}
	}

	public void returnBook(Book book)
	{

		for (int i = 0; i < books.length; i++)
		{
			if ((book != null) && (books[i] != null))
			{

				if ((book.getName().equals(books[i].getName()))
						&& (book.getAuthor().equals(books[i].getAuthor())))
				{
					books[i] = null;
					currentNumberOfBooks--;
				}
			}

		}

		if (currentNumberOfBooks == 0)
		{
			hasBooks = false;
		}

	}

	public String getFirstName()
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public boolean getHasBooks()
	{
		return hasBooks;
	}

	public Book[] getBooks()
	{
		return books;
	}

	public int getCurrentNumberOfBooks()
	{
		return currentNumberOfBooks;
	}

}
