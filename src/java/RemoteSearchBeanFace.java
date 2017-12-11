import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author smithY
 */

@Remote
public interface RemoteSearchBeanFace {
   
    public ArrayList<ArrayList<DTO>> search(String searchTerm);

    public ArrayList<BookDTO> getBooksByISBN(String isbn);

    public ArrayList<DvdDTO> getDvdByTitle(String title);

    public ArrayList<MagazineDTO> getMagazinesByTitleAndEdition(String title, String edition);
}
