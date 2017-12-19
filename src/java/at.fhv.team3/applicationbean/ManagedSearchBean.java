package at.fhv.team3.applicationbean;

import at.fhv.team3.applicationbean.interfaces.RemoteSearchBeanFace;
import at.fhv.team3.domain.dto.BookDTO;
import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.domain.dto.DvdDTO;
import at.fhv.team3.domain.dto.MagazineDTO;
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
import javax.naming.Context;

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

        System.out.println("ficken");
        try {
            Properties p = new Properties();

            //p.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
            //p.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
            //p.setProperty("org.omg.CORBA.ORBInitialPort", "3700");

            /*Properties props = new Properties();
            props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
            props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
            props.setProperty("java.naming.factory.initial",
                    "com.sun.enterprise.naming.SerialInitContextFactory");
            props.setProperty("java.naming.factory.url.pkgs",
                    "com.sun.enterprise.naming");
            props.setProperty("java.naming.factory.state",
                    "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");

            InitialContext ctx = new InitialContext(props);
            System.out.println("InitialContext done");
            //RemoteSearchBeanFace remoteInterface = (RemoteSearchBeanFace) ctx.lookup("SearchEJB");
            System.out.println("Remote access done");*/
            
            p.put(Context.INITIAL_CONTEXT_FACTORY, "com.sap.engine.services.jndi.InitialContextFactoryImpl");
            p.put(Context.PROVIDER_URL, "localhost:3700");
            
            Context ctx = new InitialContext(p);
            
            //this.remoteSearchBean = (RemoteSearchBeanFace) ctx.lookup("SearchEJB!at.fhv.team3.applicationbean.interfaces.RemoteSearchBeanFace");
            
            Object o = ctx.lookup("ejb:/beanName=SearchEJB,interfaceName=at.fhv.team3.applicationbean.interfaces.RemoteSearchBeanFace");
            remoteSearchBean = (RemoteSearchBeanFace) o;
            //InitialContext cfx = new InitialContext(p);
        } catch (NamingException ex) {
            Logger.getLogger(ManagedSearchBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void search(String searchTerm){
      ArrayList<ArrayList<DTO>> allMedias = remoteSearchBean.search(searchTerm);

      /*ArrayList<DTO> booksFound = allMedias.get(0);
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
      }*/


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
