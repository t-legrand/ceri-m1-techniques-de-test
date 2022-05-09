package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class IPokemonFactoryTest {

    @Test
    public void test() {
        IPokemonFactory mockIPokemonFactory = Mockito.mock(IPokemonFactory.class);
        Pokemon pokemon = new Pokemon(0, "test", 1, 1, 1, 1, 1, 1, 1, 0);
        when(mockIPokemonFactory.createPokemon(anyInt(),eq(1),eq(1),eq(1),eq(1))).thenAnswer(input -> {
            int index = input.getArgument(0);
            if(index < 0 || index > 150) {
                throw new PokedexException("Index invalide");
            }
            return pokemon;
        });

        assertEquals(pokemon, mockIPokemonFactory.createPokemon(0,1,1,1,1));
        assertThrows(PokedexException.class,() -> mockIPokemonFactory.createPokemon(-1,1,1,1,1));
        assertThrows(PokedexException.class,() -> mockIPokemonFactory.createPokemon(151,1,1,1,1));
    }
}