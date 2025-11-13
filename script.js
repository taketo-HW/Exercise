const LIMIT = 20;
let TOTAL_POKEMON = 0;
let TOTAL_PAGES = 0;
let currentPage = 1;

// 全件数を取得
async function getTotalCount() {
    const response = await fetch('https://pokeapi.co/api/v2/pokemon?limit=1');
    const data = await response.json();
    return data.count;
}

async function fetchPokemonList(page = 1) {
    const offset = (page - 1) * LIMIT;
    const API_URL = `https://pokeapi.co/api/v2/pokemon?limit=${LIMIT}&offset=${offset}`;
    
    document.getElementById('loading').classList.remove('hidden');
    document.getElementById('pokemonList').innerHTML = '';
    
    try {
        // 初回のみ全件数を取得
        if (TOTAL_POKEMON === 0) {
            TOTAL_POKEMON = await getTotalCount();
            TOTAL_PAGES = Math.ceil(TOTAL_POKEMON / LIMIT);
        }
        
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
        
        currentPage = page;
        updatePagination();
    } catch (error) {
        console.error('エラー:', error);
        document.getElementById('loading').textContent = 'エラーが発生しました';
    }
}

function updatePagination() {
    const pagination = document.getElementById('pagination');
    pagination.classList.remove('hidden');
    
    let html = '';
    
    // 前へボタン
    if (currentPage > 1) {
        html += `<button onclick="fetchPokemonList(${currentPage - 1})">前へ</button>`;
    }
    
    // ページ番号
    const maxVisiblePages = 10;
    let startPage = Math.max(1, currentPage - Math.floor(maxVisiblePages / 2));
    let endPage = Math.min(TOTAL_PAGES, startPage + maxVisiblePages - 1);
    
    if (endPage - startPage < maxVisiblePages - 1) {
        startPage = Math.max(1, endPage - maxVisiblePages + 1);
    }
    
    if (startPage > 1) {
        html += `<button onclick="fetchPokemonList(1)">1</button>`;
        if (startPage > 2) {
            html += `<span>...</span>`;
        }
    }
    
    for (let i = startPage; i <= endPage; i++) {
        if (i === currentPage) {
            html += `<button class="active">${i}</button>`;
        } else {
            html += `<button onclick="fetchPokemonList(${i})">${i}</button>`;
        }
    }
    
    if (endPage < TOTAL_PAGES) {
        if (endPage < TOTAL_PAGES - 1) {
            html += `<span>...</span>`;
        }
        html += `<button onclick="fetchPokemonList(${TOTAL_PAGES})">${TOTAL_PAGES}</button>`;
    }
    
    // 次へボタン
    if (currentPage < TOTAL_PAGES) {
        html += `<button onclick="fetchPokemonList(${currentPage + 1})">次へ</button>`;
    }
    
    pagination.innerHTML = html;
}

window.addEventListener('DOMContentLoaded', () => {
    fetchPokemonList(1);
});
