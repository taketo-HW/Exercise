# JUnit Docker環境

このプロジェクトは、Dockerを使用してJUnitテストを実行する簡単な環境です。

## プロジェクト構成

```
.
├── Dockerfile          # Dockerイメージの定義
├── pom.xml            # Mavenプロジェクト設定
├── src/
│   ├── main/
│   │   └── java/
│   │       └── Calculator.java  # 計算を行うメインクラス
│   └── test/
│       └── java/
│           └── CalculatorTest.java  # Calculatorクラスのテスト
└── README.md
```

## 必要な環境

- Docker Desktop（またはDocker Engine）

## 使い方

### 1. Dockerイメージをビルドしてテストを実行

```bash
docker build -t junit-test .
```

このコマンドで、Dockerイメージをビルドし、自動的にJUnitテストが実行されます。

### 2. テスト結果を確認

ビルド時にテストが実行され、結果が表示されます。すべてのテストが成功すると、ビルドが完了します。

### 3. コンテナを実行して対話的にテストを実行（オプション）

```bash
docker run -it --rm junit-test mvn test
```

## テストの追加

新しいテストを追加する場合は、`src/test/java/` ディレクトリに新しいJavaファイルを作成してください。

例：
```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AnotherTest {
    @Test
    void myNewTest() {
        assertTrue(true);
    }
}
```

## 技術スタック

- Java 17
- Maven 3.9.6
- JUnit 5.10.0