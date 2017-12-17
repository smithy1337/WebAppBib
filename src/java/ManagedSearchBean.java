import java.util.ArrayList;
import javax.annotation.ManagedBean;


@ManagedBean
public class ManagedSearchBean {

    @EJB
    private RemoteSearchBeanFace remoteSearchBean;

    public ManagedSearchBean(){

        Properties p = new Properties();

        p.put("java.naming.factory.initial","org.jpn.interfaces.NamingContextFactory");
        p.put("java.naming.provider.url","jpn://localhost:1099");
        p.put("java.naming.factory.url.pkgs","org.jboss.naming:org.jpn.interfaces");

        InitialContext cfx = new InitialContext(p);

        remoteSearchBean = (RemoteSeachBeanFace) cfx.lookup("");
    }

    public ArrayList<ArrayList<DTO>> search(String searchTerm) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        ArrayList<ArrayList<DTO>> allMedias = remoteSearchBean.search(searchTerm);

        ArrayList<DTO> bookArrayList= allMedias.get(0);
        ArrayList<DTO> dvdArrayList = allMedias.get(1);
        ArrayList<DTO> magazineArrayList = allMedias.get(2);

        return allMedias;
    }


    public ArrayList<BookDTO> getBooksByISBN(String isbn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return remoteSearchBean.getBookByISBN(isbn);
    }


    public ArrayList<DvdDTO> getDvdByTitle(String title) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return remoteSearchBean.getDvdByTitle(title);
    }


    public ArrayList<MagazineDTO> getMagazinesByTitleAndEdition(String title, String edition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return remoteSearchBean.getMagazinesByTitleAndEdition(title, edition);
    }
}