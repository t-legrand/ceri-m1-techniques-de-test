package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class IPokemonTrainerFactoryTest {

    @Test
    public void testCreateTrainer() {
        IPokemonTrainerFactory mockIPokemonTrainerFactory = Mockito.mock(IPokemonTrainerFactory.class);


        IPokedexFactory iPokedexFactory = Mockito.mock(IPokedexFactory.class);
        IPokedex iPokedex = iPokedexFactory.createPokedex(Mockito.mock(IPokemonMetadataProvider.class), Mockito.mock(IPokemonFactory.class));
        PokemonTrainer pokemonTrainer = new PokemonTrainer("Test",Team.INSTINCT, iPokedex);

        when(mockIPokemonTrainerFactory.createTrainer("Test", Team.INSTINCT, iPokedexFactory)).thenReturn(pokemonTrainer);

        assertEquals(pokemonTrainer, mockIPokemonTrainerFactory.createTrainer("Test", Team.INSTINCT, iPokedexFactory));

    }
}