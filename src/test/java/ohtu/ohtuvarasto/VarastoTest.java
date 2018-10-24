package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiLaittaaEnemmänKuinVarastoonMahtuu() {
        varasto.lisaaVarastoon(12);
        
        assertEquals(10.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiAntaaEnemmänKuinVarastossaOn() {
        varasto.lisaaVarastoon(8);
        double saatuMaara = varasto.otaVarastosta(10);
        
        assertEquals(8, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiLaittaaNegatiivistaLukua() {
        varasto.lisaaVarastoon(8);
        varasto.lisaaVarastoon(-2);
        
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiOttaaNegatiivistaLukua() {
        varasto.lisaaVarastoon(8);
        double saatuMaara = varasto.otaVarastosta(-2);
        
        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiTehdaVarastoJossaOnNegatiivinenTilavuus() {
        Varasto epavalidi = new Varasto(-10);
        
        assertEquals(0, epavalidi.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test 
    public void voidaanLuodaVarastoJossaOnAlkuSaldo() {
        Varasto alkuSaldollinenVarasto = new Varasto(10, 8);
        double saatuMaara = alkuSaldollinenVarasto.getSaldo();
        
        assertEquals(8, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiTehdaSaldollistaVarastoaJossaOnNegatiivinenTilavuus() {
        Varasto epavalidi = new Varasto(-10, 2);
        
        assertEquals(0, epavalidi.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void josSaldollisenVarastonAlkusaldoOnNegatiiivinenSaldoOnNolla() {
        Varasto alkuSaldollinenVarasto = new Varasto(8, -2);
        double saatuMaara = alkuSaldollinenVarasto.getSaldo();
        
        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void josAlkuSaldoOnEnemmänKuinTilavuusSaldoOnTilavuus() {
        Varasto alkuSaldollinenVarasto = new Varasto(10, 20);
        double saatuMaara = alkuSaldollinenVarasto.getSaldo();
        
        assertEquals(10, saatuMaara, vertailuTarkkuus);    
    }
    
    @Test
    public void toStringPrinttaaOikein() {
        varasto.lisaaVarastoon(2);
        String merkkijono = varasto.toString();
        
        assertEquals("saldo = 2.0, vielä tilaa 8.0", merkkijono);
    }

}

