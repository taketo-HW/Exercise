const API_URL = 'https://dog.ceo/api/breeds/image/random';

async function fetchDog() {
    const loading = document.getElementById('loading');
    const error = document.getElementById('error');
    const dogImage = document.getElementById('dogImage');
    
    loading.classList.remove('hidden');
    error.classList.add('hidden');
    dogImage.classList.add('hidden');
    
    try {
        const urlObj = new URL(API_URL);
        console.log(`GET ${urlObj.pathname} HTTP/1.1`);
        console.log(`Host: ${urlObj.host}\n`);
        
        const response = await fetch(API_URL);
        
        console.log(`HTTP/1.1 ${response.status} ${response.statusText}\n`);
        
        const data = await response.json();
        
        if (data.status === 'success') {
            dogImage.innerHTML = `<img src="${data.message}" alt="Dog">`;
            dogImage.classList.remove('hidden');
        }
    } catch (err) {
        console.error('エラー:', err.message);
        error.textContent = err.message;
        error.classList.remove('hidden');
    } finally {
        loading.classList.add('hidden');
    }
}
