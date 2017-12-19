package at.fhv.team3.applicationbean.interfaces;

import at.fhv.team3.domain.DTO.BookDTO;
import at.fhv.team3.domain.DTO.DTO;
import at.fhv.team3.domain.DTO.DvdDTO;
import at.fhv.team3.domain.DTO.MagazineDTO;
import java.util.ArrayList;
import javax.ejb.Remote;


@Remote
public interface RemoteSearchBeanFace {
   
    public ArrayList<ArrayList<DTO>> search(String searchTerm);

    public ArrayList<BookDTO> getBooksByISBN(String isbn);

    public ArrayList<DvdDTO> getDvdByTitle(String title);

    public ArrayList<MagazineDTO> getMagazinesByTitleAndEdition(String title, String edition);
}
