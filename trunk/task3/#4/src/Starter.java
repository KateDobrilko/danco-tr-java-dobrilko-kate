public class Starter
{
	public static void main(String[] args)
	{
		int LIMIT_OF_READERS = 50;
		int LIMIT_OF_BOOKS_IN_LIBRARY = 100;
		int LIMIT_OF_BOOKS_OF_READER = 2;

		Library library = new Library(LIMIT_OF_BOOKS_IN_LIBRARY,
				LIMIT_OF_READERS);
		LibraryManager libraryManager = new LibraryManager();

		library.addReader(new Reader("Mary", "Norton", LIMIT_OF_BOOKS_OF_READER));
		library.addReader(new Reader("Jessica", "Brown",
				LIMIT_OF_BOOKS_OF_READER));
		library.addReader(new Reader("Jack", "Gray", LIMIT_OF_BOOKS_OF_READER));

		library.addBook(new Book("The Hunger Games", "Suzanne Collins "));
		library.addBook(new Book("Harry Potter and the Order of the Phoenix",
				"J.K. Rowling"));
		library.addBook(new Book("To Kill a Mockingbird ", "Harper Lee"));
		library.addBook(new Book("Twilight", "Stephenie Meyer"));
		library.addBook(new Book("Pride and Prejudice", "Jane Austen"));
		library.addBook(new Book("Gone with the Wind", " Margaret Mitchell"));
		library.addBook(new Book("The Chronicles of Narnia", "C.S. Lewis"));
		library.addBook(new Book("Animal Farm", "George Orwell"));
		library.addBook(new Book("The Giving Tree", "Shel Silverstein"));
		library.addBook(new Book("The Hitchhiker's Guide to the Galaxy",
				"Douglas Adams"));

		libraryManager.subscribeBookOnReader(library, 2, 0);
		libraryManager.subscribeBookOnReader(library, 3, 0);
		libraryManager.subscribeBookOnReader(library, 4, 1);
		libraryManager.subscribeBookOnReader(library, 5, 1);
		libraryManager.subscribeBookOnReader(library, 6, 2);
		libraryManager.subscribeBookOnReader(library, 1, 2);
		libraryManager.subscribeBookOnReader(library, 3, 2);

		System.out.println("");
		libraryManager.showBooks(library, 0);
		System.out.println("");
		libraryManager.showReader(library, 2);
		System.out.println("");
		System.out.println("");
		libraryManager.showAllBooks(library);
		System.out.println("");
		libraryManager.showAllReaders(library);
		System.out.println("");
		System.out.println("");

		libraryManager.unsubscribeReader(library, 2, 0);
		libraryManager.unsubscribeReader(library, 3, 0);

		libraryManager.showAllBooks(library);

	}
}
