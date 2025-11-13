const API_URL = 'https://pokeapi.co/api/v2/pokemon?limit=20';

async function fetchPokemonList() {
    const response = await fetch(API_URL);
    const data = await response.json();
    
    document.getElementById('loading').classList.add('hidden');
    
    for (const pokemon of data.results) {
        const detail = await fetch(pokemon.url).then(r => r.json());
        
        const html = `
            <div class="pokemon-item">
                <img src="${detail.sprites.other['official-artwork'].front_default || detail.sprites.front_default}" alt="${detail.name}">
                <div>
                    <h3>${detail.name}</h3>
                    <p>ID: ${detail.id}</p>
                    <p>タイプ: ${detail.types.map(t => t.type.name).join(', ')}</p>
                    <p>高さ: ${(detail.height / 10).toFixed(1)}m / 重さ: ${(detail.weight / 10).toFixed(1)}kg</p>
                </div>
            </div>
        `;
        
        document.getElementById('pokemonList').innerHTML += html;
    }
}

window.addEventListener('DOMContentLoaded', fetchPokemonList);
