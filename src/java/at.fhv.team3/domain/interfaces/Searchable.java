package at.fhv.team3.domain.interfaces;

/**
 * Created by David on 10/31/2017.
 */
public interface Searchable extends Transferable {

    //Für den Vergleich des Suchstrings mit den Attributen des Durchsuchbaren Domänenobjektes (boolean)
    public boolean containsSearchTerm(String searchTerm);
}
