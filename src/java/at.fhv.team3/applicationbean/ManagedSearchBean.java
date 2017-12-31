package at.fhv.team3.applicationbean;

import at.fhv.team3.applicationbean.interfaces.RemoteSearchBeanFace;
import at.fhv.team3.domain.dto.BookDTO;
import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.domain.dto.DvdDTO;
import at.fhv.team3.domain.dto.MagazineDTO;
import java.io.IOException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.naming.Context;

@ManagedBean (name = "managedSearchBean")
@ViewScoped
public class ManagedSearchBean implements Serializable{
    
    @EJB(mappedName="SearchEJB")
    private RemoteSearchBeanFace remoteSearchBean;

    private ArrayList<BookDTO> books = new ArrayList();
    private ArrayList<DvdDTO> dvds = new ArrayList();
    private ArrayList<MagazineDTO> magazines = new ArrayList();
    private ArrayList<BookDTO> isbnBooks = new ArrayList();
    private ArrayList<DvdDTO> titleDvds = new ArrayList();
    private ArrayList<MagazineDTO> titleMagazins = new ArrayList();
    private int focus = 0;
    private Boolean isBook = false;
    private Boolean isDvd = false;
    private Boolean isMagazine = false;
    private String searchTerm;
    private BookDTO book;
    private DvdDTO dvd;
    private MagazineDTO magazine;
    
    public void listener(ActionEvent ae){
        String url = "detail.xhtml";
        try {
            FacesContext.getCurrentInstance().getExternalContext().dispatch(url);
        } catch (IOException ex) {
            Logger.getLogger(ManagedSearchBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void search(){
      books = new ArrayList();
      dvds = new ArrayList();
      magazines = new ArrayList();
      ArrayList<ArrayList<DTO>> allMedias = remoteSearchBean.search(searchTerm);

      ArrayList<DTO> booksFound = allMedias.get(0);
      ArrayList<DTO> dvdsFound = allMedias.get(1);
      ArrayList<DTO> magazinesFound = allMedias.get(2);

      for(int i = 0; i<booksFound.size(); i++){
        BookDTO book = (BookDTO) booksFound.get(i);
          
        Boolean bookfound = false;
            for(int j = 0; j < books.size(); j++){
                if(books.get(j).equals(book)){
                    bookfound = true;
                }
            }
            if(!bookfound){
                books.add(book);
            }
      }

      for(int i = 0; i<dvdsFound.size(); i++){
          DvdDTO dvd = (DvdDTO) dvdsFound.get(i);
          
          Boolean dvdfound = false;
                        for(int j = 0; j < dvds.size(); j++){
                            if(dvds.get(j).equals(dvd)){
                                dvdfound = true;
                            }
                        }
                        if(!dvdfound){
                            dvds.add(dvd);
                        }
      }

      for(int i = 0; i<magazinesFound.size(); i++){
          MagazineDTO magazine = (MagazineDTO) magazinesFound.get(i);
          
          Boolean magazinefound = false;
                        for(int j = 0; j < magazines.size(); j++){
                            if(magazines.get(j).equals(magazine)){
                                magazinefound = true;
                            }
                        }
                        if(!magazinefound){
                            magazines.add(magazine);
                        }
      }
    }
    
    public void bookAction(BookDTO book) {
        this.book = book;
    }
    
    public void getBooksByISBN(String isbn){
      isbnBooks = remoteSearchBean.getBooksByISBN(isbn);
      book = isbnBooks.get(0);
    }

    public void getDvdByTitle(String title){
      titleDvds = remoteSearchBean.getDvdByTitle(title);
      dvd = titleDvds.get(0);
    }

    public void getMagazinesByTitleAndEdition(String title, String edition){
      titleMagazins = remoteSearchBean.getMagazinesByTitleAndEdition(title, edition);
      magazine = titleMagazins.get(0);
    }

	public ArrayList<BookDTO> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<BookDTO> books) {
		this.books = books;
	}

	public ArrayList<DvdDTO> getDvds() {
		return dvds;
	}

	public void setDvds(ArrayList<DvdDTO> dvds) {
		this.dvds = dvds;
	}

	public ArrayList<MagazineDTO> getMagazines() {
		return magazines;
	}

	public void setMagazines(ArrayList<MagazineDTO> magazines) {
		this.magazines = magazines;
	}

	public ArrayList<BookDTO> getIsbnBooks() {
		return isbnBooks;
	}

	public void setIsbnBooks(ArrayList<BookDTO> isbnBooks) {
		this.isbnBooks = isbnBooks;
	}

	public ArrayList<DvdDTO> getTitleDvds() {
		return titleDvds;
	}

	public void setTitleDvds(ArrayList<DvdDTO> titleDvds) {
		this.titleDvds = titleDvds;
	}

	public ArrayList<MagazineDTO> getTitleMagazins() {
		return titleMagazins;
	}

	public void setTitleMagazins(ArrayList<MagazineDTO> titleMagazins) {
		this.titleMagazins = titleMagazins;
	}

        public void setFocus(){
        }

        public int getFocus(){
            if(books.isEmpty()){
                    focus = 1;
                    if(dvds.isEmpty()){
                        focus = 2;
                        if(magazines.isEmpty()){
                            focus = 0;
                        }
                    }
            }else if(!books.isEmpty()){
                focus = 0;
            }
            return focus;
        }

        public void setIsBook(Boolean found){
            isBook = found;
        }

        public Boolean getIsBook(){
            return isBook;
        }

        public void setIsDvd(Boolean found){
            isDvd = found;
        }

        public Boolean getIsDvd(){
            return isDvd;
        }

        public void setIsMagazine(Boolean found){
            isMagazine = found;
        }

        public Boolean getIsMagazine(){
            return isMagazine;
        }
        
        public void setSearchTerm(String term){
            searchTerm = term;
        }
        
        public String getSearchTerm(){
            return searchTerm;
        }
        
         public void setDvd(DvdDTO dvd){
        this.dvd = dvd;
    }
    
    public DvdDTO getDvd(){
        return dvd;
    }
    
    public void setBook(BookDTO book){
        this.book = book;
    }
    
    public BookDTO getBook(){
        return book;
    }
    
    public void setMagazine(MagazineDTO magazine){
        this.magazine = magazine;
    }
    
    public MagazineDTO getMagazine(){
        return magazine;
    }
}
