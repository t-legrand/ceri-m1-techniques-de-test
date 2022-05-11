package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Pokedex implements IPokedex {

    private final IPokemonMetadataProvider metadataProvider;
    private final IPokemonFactory pokemonFactory;
    private final ArrayList<Pokemon> storedPokemon;

    public Pokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        this.metadataProvider = metadataProvider;
        this.pokemonFactory = pokemonFactory;
        this.storedPokemon = new ArrayList<>();
    }

    @Override
    public int size() {
        return this.storedPokemon.size();
    }

    @Override
    public int addPokemon(Pokemon pokemon) {
        this.storedPokemon.add(pokemon);
        return this.storedPokemon.lastIndexOf(pokemon);
    }

    @Override
    public Pokemon getPokemon(int id) throws PokedexException {
        if (id < this.size()) {
            return this.storedPokemon.get(id);
        }
        else {
            throw new PokedexException("No Pokemon with this index in this Pokedex");
        }
    }

    @Override
    public List<Pokemon> getPokemons() {
        return this.storedPokemon;
    }

    @Override
    public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
        ArrayList<Pokemon> copy = (ArrayList<Pokemon>) this.storedPokemon.clone();
        copy.sort(order);
        return copy;
    }

    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        return this.pokemonFactory.createPokemon(index, cp, hp, dust, candy);
    }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        return this.metadataProvider.getPokemonMetadata(index);
    }
}