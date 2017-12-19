package at.fhv.team3.domain.interfaces;

import at.fhv.team3.domain.dto.DTO;

/**
 * Created by David on 10/31/2017.
 */
public interface Transferable {

    //Erstellen eines DTOs aus einem Domänenobjekt (DTO)
    public DTO createDataTransferObject();

    //Ein Domänenobjekt mit den Informationen aus dem dazugehörigen DTO-Typ füllen
    public void fillFromDTO(DTO dto);
}
