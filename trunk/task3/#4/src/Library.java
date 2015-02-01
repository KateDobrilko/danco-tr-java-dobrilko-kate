public class Library
{
	private Book[] books;
	private Reader[] readers;
	private int currentNumberOfBooks;
	private int currentNumberOfReaders;

	public Library(int limitOfBooks, int limitOfReaders)
	{
		this.books = new Book[limitOfBooks];
		this.readers = new Reader[limitOfReaders];
		currentNumberOfBooks = 0;
		currentNumberOfReaders = 0;
	}

	public void addBook(Book Book)
	{
		if (currentNumberOfBooks < books.length)
		{
			books[currentNumberOfBooks] = Book;
			currentNumberOfBooks++;
		}
	}

	public void addReader(Reader reader)
	{
		if (currentNumberOfReaders < readers.length)
		{
			readers[currentNumberOfReaders] = reader;
			currentNumberOfReaders++;
		}
	}

	public Book[] getBooks()
	{
		return books;
	}

	public Reader[] getReaders()
	{
		return readers;
	}

}
