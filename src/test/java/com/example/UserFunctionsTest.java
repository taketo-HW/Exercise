import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

//test対象のクラスをインポート
import com.example.UserFunctions;

/**
 * UserFunctionsクラスのテスト
 */
public class UserFunctionsTest {
    
    private UserFunctions UserFunctions;
    
    @BeforeEach  // 初期化処理を行うメソッド
    void setUp() {
        UserFunctions = new UserFunctions();
    }

    @Test //成人判定ロジックの検証（アサーション）
    void testIsAdult() {
        UserFunctions.setAge(20);
        assertTrue(UserFunctions.isAdult(), "テストケース A: 基準値（成人）のテスト");
    }

    @Test //成人判定ロジックの検証（アサーション）
    void testIsNotAdult() {
        UserFunctions.setAge(19);
        assertFalse(UserFunctions.isAdult(), "テストケース B: 基準値外（未成年）のテスト");
    }

    @AfterEach
    void tearDown() { // 後処理を行うメソッド
        UserFunctions = null;
    }
}