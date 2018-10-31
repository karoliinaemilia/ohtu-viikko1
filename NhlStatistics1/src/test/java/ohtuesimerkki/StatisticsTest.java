package ohtuesimerkki;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class StatisticsTest {
    
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        stats = new Statistics(readerStub);
    }  
    
    @Test
    public void palauttaaHaetunPelaajan() {
        Player haettu = stats.search("Lemieux");
        
        assertEquals(haettu.getName(), "Lemieux");
    }
    
    @Test
    public void palauttaaNullJosPelaajaaEiOle() {
        Player haettu = stats.search("Giroux");
        
        assertEquals(haettu, null);
    }
    
    @Test
    public void palauttaaOikeatPelaajatJoukkueelle() {
        List<Player> haettu = stats.team("EDM");
        
        assertEquals(haettu.get(0).getName(), "Semenko");
        assertEquals(haettu.size(), 3);
        
    }
    
    @Test
    public void palauttaaOikeatTopScorers() {
        List<Player> haettu = stats.topScorers(2);
        
        assertEquals(haettu.size(), 3);
        
        assertEquals(haettu.get(0).getName(), "Gretzky");
        assertEquals(haettu.get(1).getName(), "Lemieux");
    }
}
