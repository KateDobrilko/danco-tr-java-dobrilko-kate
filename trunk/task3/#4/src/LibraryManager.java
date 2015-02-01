public class LibraryManager
{

	public void subscribeBookOnReader(Library library, int bookNumber,
			int readerNumber)
	{
		Book[] libBooks = library.getBooks();
		Reader[] libReaders = library.getReaders();

		if ((bookNumber < libBooks.length) && (bookNumber > (-1))
				&& (readerNumber < libReaders.length) && (readerNumber > (-1)))
		{
			if ((libBooks[bookNumber].getHasAReader() != true)
					&& (libReaders[readerNumber].getCurrentNumberOfBooks() != libReaders[readerNumber]
							.getBooks().length))
			{
				libBooks[bookNumber].setReader(libReaders[readerNumber]);
				libReaders[readerNumber].takeBook(libBooks[bookNumber]);
				return;
			}

			if (libBooks[bookNumber].getHasAReader() == true)
			{
				System.out.print("Book already has a reader: ");
				System.out.println(libBooks[bookNumber].getName());
			}

			if (libReaders[readerNumber].getCurrentNumberOfBooks() >= libReaders[readerNumber]
					.getBooks().length)
			{
				System.out.print("This reader cannot take more books: ");
				System.out.print(libReaders[readerNumber].getFirstName());
				System.out.print(" ");
				System.out.println(libReaders[readerNumber].getLastName());
			}
		}

	}

	public void unsubscribeReader(Library library, int bookNumber,
			int readerNumber)
	{
		Book[] libBooks = library.getBooks();
		Reader[] libReaders = library.getReaders();

		if ((bookNumber < libBooks.length) && (bookNumber > (-1))
				&& (readerNumber < libReaders.length) && (readerNumber > (-1)))
		{
			libBooks[bookNumber].setReader(null);
		}

		if ((bookNumber < libBooks.length) && (bookNumber > (-1))
				&& (readerNumber < libReaders.length) && (readerNumber > (-1)))
		{
			libReaders[readerNumber].returnBook(libBooks[bookNumber]);
		}

	}

	public void showReader(Library library, int bookNumber)
	{
		Book[] libBooks = library.getBooks();
		if ((bookNumber < libBooks.length) && (bookNumber > (-1)))
		{
			if (libBooks[bookNumber].getHasAReader() != false)
			{
				System.out.print(libBooks[bookNumber].getName());
				System.out.print(", ");
				System.out.print(libBooks[bookNumber].getAuthor());
				System.out.println(" has a reader: ");
				System.out.print(libBooks[bookNumber].getReader()
						.getFirstName());
				System.out.print(" ");
				System.out.println(libBooks[bookNumber].getReader()
						.getLastName());
			} else
			{
				System.out.println("Book has no reader.");
			}
		}
	}

	public void showBooks(Library library, int readerNumber)
	{
		Reader[] libReaders = library.getReaders();
		if ((readerNumber < libReaders.length) && (readerNumber > (-1)))

		{
			if (libReaders[readerNumber] != null)
			{
				if (libReaders[readerNumber].getHasBooks() != false)
				{
					for (int j = 0; j < libReaders[readerNumber].getBooks().length; j++)
					{

						if (libReaders[readerNumber].getBooks()[j] != null)

						{
							System.out.print(libReaders[readerNumber].getFirstName());
							System.out.print(" ");
							System.out.print(libReaders[readerNumber].getLastName());
							System.out.println(" has books: ");
							System.out.print(libReaders[readerNumber]
									.getBooks()[j].getAuthor());
							System.out.print(", ");
							System.out.println(libReaders[readerNumber]
									.getBooks()[j].getName());
						}
					}
				} else
				{
					System.out.println("Reader has no books.");
				}
			}

		}
	}

	public void showAllBooks(Library library)
	{
		Book[] libBooks = library.getBooks();
		if (libBooks.length != 0)
		{
			for (int i = 0; i < libBooks.length; i++)
			{
				if (libBooks[i] != null)
				{
					if (libBooks[i].getHasAReader() != false)
					{

						System.out.print(libBooks[i].getAuthor());
						System.out.print(", ");
						System.out.print(libBooks[i].getName());
						System.out.print(" | Reader: ");
						System.out
								.print(libBooks[i].getReader().getFirstName());
						System.out.print(" ");
						System.out.println(libBooks[i].getReader()
								.getLastName());

					} else
					{
						System.out.print(libBooks[i].getAuthor());
						System.out.print(", ");
						System.out.print(libBooks[i].getName());
						System.out.print(" | ");
						System.out.println("In Library");

					}
				}
			}
		}
	}

	public void showAllReaders(Library library)
	{
		Reader[] libReaders = library.getReaders();

		{
			for (int i = 0; i < libReaders.length; i++)
			{
				if (libReaders[i] != null)
				{
					if ((libReaders.length != 0))
					{
						System.out.print(libReaders[i].getFirstName());
						System.out.print(" ");
						System.out.println(libReaders[i].getLastName());

					}
				}
			}
		}
	}
}
