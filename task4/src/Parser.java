import java.util.ArrayList;
import java.util.Arrays;

import com.danco.training.TextFileWorker;

public class Parser {
    
    Store store = new Store();
    /*static Bookshop Parse(String path){
	TextFileWorker tfw = new TextFileWorker(path);
	ArrayList<String> file = (ArrayList<String>) Arrays.asList(tfw.readFromFile());
	int currentStartPosition=0;
	int currentEndPosition=0;
	for(int i=0; i<file.size();i++){
	
	if(file.get(i).equals("<store>")){
	    currentStartPosition = i;
	}
	if(file.get(i).equals("</store>")){
	    currentEndPosition = i;
	    break;
	}
	}
	ArrayList<String> storeList = (ArrayList<String>) file.subList(currentStartPosition, currentEndPosition);
	for(int j=currentStartPosition;j<=currentEndPosition;j++){
	    file.remove(j);
	}
	
	for(int i=0; i<storeList.size();i++){
		BookExemplar bookExemplar = new BookExemplar();
		if(file.get(i).equals("<book-exemplar>")){
		    currentStartPosition = i;
		}
		if(file.get(i).equals("</book-exemplar>")){
		    currentEndPosition = i;
		    break;
		}
		ArrayList<String> bookExemplarList = (ArrayList<String>) storeList.subList(currentStartPosition, currentEndPosition);
		for(int j=currentStartPosition;j<=currentEndPosition;j++){
		    storeList.remove(j);
		}
		for(int k=0; k<bookExemplarList.size();k++){
		    if(bookExemplarList.get(k).equals("<name>")){
			    currentStartPosition = k;
			}
			if(file.get(i).equals("</name>")){
			    currentEndPosition = k;
			    break;
			}
			
			bookExemplar.setName(bookExemplarList.get(i+1));
			for(int j=currentStartPosition;j<=currentEndPosition;j++){
				bookExemplarList.remove(j);
			}
			
			
		}
		for(int k=0; k<bookExemplarList.size();k++){
		    if(bookExemplarList.get(k).equals("<author>")){
			    currentStartPosition = k;
			}
			if(file.get(i).equals("</name>")){
			    currentEndPosition = k;
			    break;
			}
			
			bookExemplar.setName(bookExemplarList.get(i+1));
			for(int j=currentStartPosition;j<=currentEndPosition;j++){
				bookExemplarList.remove(j);
			}
			
			
		}
	
	}
	
    
    }*/
    static void WriteToFile(Bookshop bookshop,String path)
    {
	TextFileWorker tfw = new TextFileWorker(path);
	String[] mas = {bookshop.toString()};
	tfw.writeToFile(mas);
    }
}
