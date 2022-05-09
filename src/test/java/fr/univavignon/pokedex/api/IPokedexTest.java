package fr.univavignon.pokedex.api;

import org.mockito.stubbing.Answer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class IPokedexTest {


    IPokedex mockPokedex = mock(IPokedex.class);
    Pokemon bulbizarre=new Pokemon(0,"Bulbizarre",126,126,90,613,64,4000,4,56);
    Pokemon aquali=new Pokemon(133,"Aquali",186,168,260,2729,202,5000,4,100);
    List<Pokemon> pokemonList;

    @BeforeEach
    public void setup(){
        pokemonList = new ArrayList<Pokemon>();
        when(mockPokedex.addPokemon(any(Pokemon.class))).then(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocationOnMock) throws Throwable {
              pokemonList.add(invocationOnMock.getArgument(0));
              return pokemonList.size() - 1;
            }
        });
        when(mockPokedex.size()).then(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocationOnMock) throws Throwable {
                return pokemonList.size();
            }
        });
        try {
            when(mockPokedex.getPokemon(any(Integer.class))).then(new Answer<Pokemon>() {
                @Override
                public Pokemon answer(InvocationOnMock invocationOnMock) throws Throwable {
                    int t=invocationOnMock.getArgument(0);
                    if (t<pokemonList.size() && t>=0){
                        return pokemonList.get(invocationOnMock.getArgument(0));
                    }
                    return null;
                }
            });
        } catch (PokedexException e) {
                e.printStackTrace();
        }
        when(mockPokedex.getPokemons()).then(new Answer<List<Pokemon>>() {
            @Override
            public List<Pokemon> answer(InvocationOnMock invocationOnMock) throws Throwable {
                return pokemonList;

            }
        });
        when(mockPokedex.getPokemons(any())).then(new Answer<List<Pokemon>>() {
            @Override
            public List<Pokemon> answer(InvocationOnMock invocationOnMock) throws Throwable {
                Comparator<Pokemon> comparator=new Comparator<Pokemon>() {
                    @Override
                    public int compare(Pokemon o1, Pokemon o2) {
                        if (o1.getIndex()<o2.getIndex()){
                            return -1;
                        }
                        else if (o1.getIndex()==o2.getIndex()) {
                            return 0;
                        }
                        return 0;
                    }
                };
                pokemonList.sort(comparator);
                return pokemonList;
            }
        });
    }


    @Test
    public void addPokemonTestBulbizarre(){
        int idx=mockPokedex.addPokemon(bulbizarre);
        assertEquals(0,idx);
    }
    @Test
    public void sizeTestBulbizarre() {
        assertEquals(0, mockPokedex.size());
        mockPokedex.addPokemon(bulbizarre);
        assertEquals(1, mockPokedex.size());

    }

    @Test
    public void getPokemonTestShouldThrowException1(){
        try {
            when(mockPokedex.getPokemon(1)).thenThrow(PokedexException.class);
        } catch (PokedexException e) {
            e.printStackTrace();
        }

        assertThrows(PokedexException.class, () -> {
            mockPokedex.getPokemon(1);
        });
    }

    @Test
    public void getPokemonTestBulbizarre(){
        try {
            mockPokedex.addPokemon(bulbizarre);
            assertEquals(0,mockPokedex.getPokemon(0).getIndex());
        } catch (PokedexException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getPokemonsTest(){
        mockPokedex.addPokemon(bulbizarre);
        List<Pokemon> list=mockPokedex.getPokemons();
        assertEquals(list.get(0).getIndex(),bulbizarre.getIndex());
    }


    @Test
    public void getPokemonsComparatorTest(){
        Comparator<Pokemon> comparator=new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon o1, Pokemon o2) {
                if (o1.getIndex()<o2.getIndex()){
                    return -1;
                }
                else if (o1.getIndex()==o2.getIndex()) {
                    return 0;
                }
                return 0;
            }
        };
        mockPokedex.addPokemon(aquali);
        mockPokedex.addPokemon(bulbizarre);
        List<Pokemon> list=mockPokedex.getPokemons(comparator);
        assertEquals(list.get(0).getIndex(),bulbizarre.getIndex());
    }
}