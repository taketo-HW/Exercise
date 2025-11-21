# Docker練習用プロジェクト

このプロジェクトはDockerの練習用に作成されたシンプルなWebアプリケーションです。

## 概要

http://localhost:8080 にアクセスすると、簡単なHTMLページが表示されます。

## 必要な環境

- Docker
- Docker Compose（オプション）

## 使い方

### 方法1: Docker Composeを使用（推奨）

```bash
# コンテナをビルドしてバックグラウンドで起動（ターミナルが開放されます）
docker-compose up -d --build

# コンテナの状態を確認
docker-compose ps

# ログを確認
docker-compose logs

# ログをリアルタイムで確認（Ctrl+Cで終了）
docker-compose logs -f

# コンテナを停止
docker-compose stop

# コンテナを停止して削除
docker-compose down

# コンテナを再起動
docker-compose restart
```

### 方法2: Dockerコマンドを直接使用

```bash
# イメージをビルド
docker build -t docker-practice .

# コンテナを起動
docker run -d -p 8080:80 --name docker-practice-web docker-practice

# コンテナを停止
docker stop docker-practice-web

# コンテナを削除
docker rm docker-practice-web
```

## アクセス方法

ブラウザで以下のURLにアクセスしてください：
- http://localhost:8080

## ファイル構成

- `index.html` - 表示されるHTMLファイル
- `Dockerfile` - Dockerイメージの定義
- `docker-compose.yml` - Docker Composeの設定ファイル
- `.dockerignore` - Dockerビルド時に除外するファイル

## 技術スタック

- Nginx (Alpine Linuxベース)
- HTML5 / CSS3