const API_URL = 'https://dog.ceo/api/breeds/image/random';

// 状態の定義
const State = {
    LOADING: 'loading',    // ロード中
    ERROR: 'error',        // 取得エラー
    DISPLAY: 'display'     // 表示中
};

// DOM要素の取得
const elements = {
    loading: document.getElementById('loading'),
    error: document.getElementById('error'),
    dogImage: document.getElementById('dogImage')
};

// 現在の状態を管理
let currentState = null;

/**
 * 状態遷移を管理する関数
 * @param {string} newState - 新しい状態 (State.LOADING, State.ERROR, State.DISPLAY)
 */
function setState(newState) {
    // 全ての要素を非表示にする
    elements.loading.classList.add('hidden');
    elements.error.classList.add('hidden');
    elements.dogImage.classList.add('hidden');
    
    // 新しい状態に応じて適切な要素を表示
    switch (newState) {
        case State.LOADING:
            elements.loading.classList.remove('hidden');
            break;
        case State.ERROR:
            elements.error.classList.remove('hidden');
            break;
        case State.DISPLAY:
            elements.dogImage.classList.remove('hidden');
            break;
    }
    
    currentState = newState;
    console.log(`状態遷移: ${currentState}`);
}

/**
 * 非同期で犬の画像を取得する関数
 */
async function fetchDog() {
    try {
        // 状態遷移: ロード中
        setState(State.LOADING);
        
        // 非同期処理: APIリクエストの準備
        const urlObj = new URL(API_URL);
        console.log(`GET ${urlObj.pathname} HTTP/1.1`);
        console.log(`Host: ${urlObj.host}\n`);
        
        // 非同期処理: APIからデータを取得（awaitで待機）
        const response = await fetch(API_URL);
        
        console.log(`HTTP/1.1 ${response.status} ${response.statusText}\n`);
        
        // 非同期処理: レスポンスをJSONに変換（awaitで待機）
        const data = await response.json();
        
        // レスポンスの検証
        if (!response.ok) {
            throw new Error(`HTTPエラー: ${response.status} ${response.statusText}`);
        }
        
        if (data.status === 'success') {
            // 状態遷移: 表示中
            elements.dogImage.innerHTML = `<img src="${data.message}" alt="Dog">`;
            setState(State.DISPLAY);
        } else {
            throw new Error('APIからエラーレスポンスが返されました');
        }
        
    } catch (err) {
        // 状態遷移: 取得エラー
        console.error('エラー:', err.message);
        elements.error.textContent = `エラーが発生しました: ${err.message}`;
        setState(State.ERROR);
    }
}
