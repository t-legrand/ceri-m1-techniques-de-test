package fr.univavignon.pokedex.api;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IPokemonMetadataProviderTest extends TestCase {
	IPokemonMetadataProvider metaData = mock(IPokemonMetadataProvider.class);
	PokemonMetadata bulbizarre = new PokemonMetadata(0,"Bulbizarre",126,126,90);
	PokemonMetadata aquali = new PokemonMetadata(133,"Aquali",186,168,260);

	@Before
	public void setUp(){
		try {
			when(metaData.getPokemonMetadata(0)).thenReturn(bulbizarre);
			when(metaData.getPokemonMetadata(133)).thenReturn(aquali);
		} catch (PokedexException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testMetaData() {
		try {
			PokemonMetadata testMetaData = metaData.getPokemonMetadata(0);
			assertEquals(0, testMetaData.getIndex());
			assertEquals("Bulbizarre", testMetaData.getName());
			assertEquals(126, testMetaData.getAttack());
			assertEquals(126, testMetaData.getDefense());
			assertEquals(90, testMetaData.getStamina());
		} catch (PokedexException e){
			e.printStackTrace();
		}
	}
}