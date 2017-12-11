/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import javax.annotation.ManagedBean;

/**
 *
 * @author smithY
 */

@ManagedBean
public class ManagedSearchBean implements RemoteSearchBeanFace{

    @Override
    public ArrayList<ArrayList<DTO>> search(String searchTerm) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public ArrayList<BookDTO> getBooksByISBN(String isbn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<DvdDTO> getDvdByTitle(String title) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<MagazineDTO> getMagazinesByTitleAndEdition(String title, String edition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}