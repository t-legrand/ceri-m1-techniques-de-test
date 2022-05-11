package fr.univavignon.pokedex.api;

import java.util.ArrayList;

public class PokemonMetadataProvider implements IPokemonMetadataProvider {

    private static PokemonMetadataProvider pokemonMetadataProvider;
    private final ArrayList<PokemonMetadata> pokemonMetadataList = new ArrayList<>();

    private PokemonMetadataProvider() {}

    private void fillPokemonMetadataList() {
        for (int i = 0; i < 150; i++) {
            if (i == 0) {
                this.pokemonMetadataList.add(new PokemonMetadata(i, "Bulbizzare", 126, 126, 90));
            }
            else if (i == 133) {
                this.pokemonMetadataList.add(new PokemonMetadata(i, "Aquali", 186, 168, 260));
            }
            else {
                this.pokemonMetadataList.add(new PokemonMetadata(i, "empty", 0, 0, 0));
            }
        }
    }

    public static synchronized PokemonMetadataProvider getPokemonMetadataProvider() {
        if (pokemonMetadataProvider == null) {
            pokemonMetadataProvider = new PokemonMetadataProvider();
            pokemonMetadataProvider.fillPokemonMetadataList();
        }
        return pokemonMetadataProvider;
    }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        if (index >= 0 && index < 150) {
            return this.pokemonMetadataList.get(index);
        }
        else {
            throw new PokedexException("No Pokemon exists with this index");
        }
    }
}