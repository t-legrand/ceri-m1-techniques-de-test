package fr.univavignon.pokedex.api;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

class IPokedexFactoryTest {

    @Test
    public void test() {
        IPokedexFactory mockIPokedexFactory = Mockito.mock(IPokedexFactory.class);
        IPokemonMetadataProvider metadataProvider = Mockito.mock(IPokemonMetadataProvider.class);
        IPokemonFactory pokemonFactory = Mockito.mock(IPokemonFactory.class);
        IPokedex iPokedex = Mockito.mock(IPokedex.class);
        when(mockIPokedexFactory.createPokedex(metadataProvider, pokemonFactory)).thenReturn(iPokedex);

        assertEquals(iPokedex, mockIPokedexFactory.createPokedex(metadataProvider, pokemonFactory));
    }
}