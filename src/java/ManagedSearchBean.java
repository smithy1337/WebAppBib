import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;


@ManagedBean (name = "managedSeachBean")

public class ManagedSearchBean{

    @EJB
    private RemoteSearchBeanFace remoteSearchBean;

    public ArrayList<BookDTO> books;
    public ArrayList<DvdDTO> dvds;
    public ArrayList<MagazineDTO> magazines;
    public ArrayList<BookDTO> isbnBooks;
    public ArrayList<DvdDTO> titleDvds;
    public ArrayList<MagazineDTO> titleMagazins;

    public ManagedSearchBean(){

        Properties p = new Properties();

        p.put("java.naming.factory.initial","org.jpn.interfaces.NamingContextFactory");
        p.put("java.naming.provider.url","jpn://localhost:3700");
        p.put("java.naming.factory.url.pkgs","org.jboss.naming:org.jpn.interfaces");

        InitialContext cfx = new InitialContext(p);

        remoteSearchBean = (RemoteSeachBeanFace) cfx.lookup("SearchEJB");
    }

    public void search(String searchTerm){
      ArrayList<ArrayList<DTO>> allMedias = remoteSearchBean.search(searchTerm);

      books = allMedias.get(0);
      dvds = allMedias.get(1);
      magazines = allMedias.get(2);
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
}
