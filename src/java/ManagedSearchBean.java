import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ejb.EJB;

@ManagedBean (name = "managedSeachBean")
@SessionScoped
public class ManagedSearchBean implements Serializable{

    @EJB
    private RemoteSearchBeanFace remoteSearchBean;

    private ArrayList<BookDTO> books;
    private ArrayList<DvdDTO> dvds;
    private ArrayList<MagazineDTO> magazines;
    private ArrayList<BookDTO> isbnBooks;
    private ArrayList<DvdDTO> titleDvds;
    private ArrayList<MagazineDTO> titleMagazins;
    private int focus;
    private Boolean foundBooks;
    private Boolean foundDvds;
    private Boolean foundMagazines;

    public ManagedSearchBean(){

        Properties p = new Properties();

        //p.put("java.naming.factory.initial","org.jpn.interfaces.NamingContextFactory");
        //p.put("java.naming.provider.url","jpn://localhost:3700");
        //p.put("java.naming.factory.url.pkgs","org.jboss.naming:org.jpn.interfaces");

        p.put("org.omg.CORBA.ORBInitialHost", "localhost");
        p.put("org.omg.CORBA.ORBInitialPort", "3700");
        p.put("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
        p.put("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
        p.put("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
            
        InitialContext cfx;
        try {
            cfx = new InitialContext(p);
             remoteSearchBean = (RemoteSearchBeanFace) cfx.lookup("SearchEJB");
        } catch (NamingException ex) {
            Logger.getLogger(ManagedSearchBean.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }

    public void search(String searchTerm){
      ArrayList<ArrayList<DTO>> allMedias = remoteSearchBean.search(searchTerm);

      ArrayList<DTO> booksFound = allMedias.get(0);
      ArrayList<DTO> dvdsFound = allMedias.get(1);
      ArrayList<DTO> magazinesFound = allMedias.get(2);
      
      for(int i = 0; i<booksFound.size(); i++){
          BookDTO book = (BookDTO) booksFound.get(i);
          books.add(book);
      }
      
      for(int i = 0; i<dvdsFound.size(); i++){
          DvdDTO dvd = (DvdDTO) dvdsFound.get(i);
          dvds.add(dvd);
      }
      
      for(int i = 0; i<magazinesFound.size(); i++){
          MagazineDTO magazine = (MagazineDTO) magazinesFound.get(i);
          magazines.add(magazine);
      }
      
      
    }

    public void getBooksByISBN(String isbn){
      isbnBooks = remoteSearchBean.getBooksByISBN(isbn);
    }

    public void getDvdByTitle(String title){
      titleDvds = remoteSearchBean.getDvdByTitle(title);
    }

    public void getMagazinesByTitleAndEdition(String title, String edition){
      titleMagazins = remoteSearchBean.getMagazinesByTitleAndEdition(title, edition);
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
        
        public void setFoundBooks(Boolean found){
            foundBooks = found;
        }
        
        public Boolean getFoundBooks(){
            return foundBooks;
        }
        
        public void setFoundDvds(Boolean found){
            foundDvds = found;
        }
        
        public Boolean getFoundDvds(){
            return foundDvds;
        }
        
        public void setFoundMagazines(Boolean found){
            foundMagazines = found;
        }
        
        public Boolean getFoundMagazines(){
            return foundMagazines;
        }
}
