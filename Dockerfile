ARG MYSQL_VERSION=8.0.28

FROM mysql:${MYSQL_VERSION}-debian

COPY my.cnf /etc/mysql/conf.d/my.cnf

# 日本語環境を追加
# Debian BusterはEOLのため、archiveリポジトリを使用
RUN sed -i 's|http://deb.debian.org/debian|http://archive.debian.org/debian|g' /etc/apt/sources.list \
    && sed -i 's|http://security.debian.org/debian-security|http://archive.debian.org/debian-security|g' /etc/apt/sources.list \
    && sed -i '/stretch-updates/d' /etc/apt/sources.list \
    && mkdir -p /etc/apt/sources.list.d \
    && mv /etc/apt/sources.list.d/mysql.list /etc/apt/sources.list.d/mysql.list.bak 2>/dev/null || true \
    && apt-get update && apt-get install -y locales \
    && sed -i -e 's/# \(ja_JP.UTF-8\)/\1/' /etc/locale.gen \
    && locale-gen \
    && update-locale LANG=ja_JP.UTF-8 \
    && mv /etc/apt/sources.list.d/mysql.list.bak /etc/apt/sources.list.d/mysql.list 2>/dev/null || true \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

ENV LANG=ja_JP.UTF-8

# カスタムエントリーポイントスクリプトをコピー
COPY docker-entrypoint.sh /usr/local/bin/custom-entrypoint.sh
RUN chmod +x /usr/local/bin/custom-entrypoint.sh

ENTRYPOINT ["/usr/local/bin/custom-entrypoint.sh"]
CMD ["mysqld"]
