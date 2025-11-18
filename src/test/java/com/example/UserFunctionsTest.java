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
    void testIsFitnessTestA() {
        UserFunctions.setAge(20);
        UserFunctions.setGender("MALE");
        UserFunctions.setFitnessScore(60);
        assertTrue(UserFunctions.isFitnessTestPassed(), "テストケース A: 20歳以上男性の合格テスト（最小合格境界値）");
    }
    

    @Test //体力テスト女性不合格ロジックの検証（アサーション）
    void testIsFitnessTestB() {
        UserFunctions.setAge(19);
        UserFunctions.setGender("FEMALE");
        UserFunctions.setFitnessScore(79);
        assertFalse(UserFunctions.isFitnessTestPassed(), "テストケース B: 19歳以下の不合格テスト（最大不合格境界値）");
    }

    @Test //体力テスト女性不合格ロジックの検証（アサーション）
    void testIsFitnessTestC() {
        UserFunctions.setAge(25);
        UserFunctions.setGender("FEMALE");
        UserFunctions.setFitnessScore(54);
        assertFalse(UserFunctions.isFitnessTestPassed(), "テストケース C: 20歳以上女性の不合格境界値テスト（最大不合格境界値）");
    }

    @AfterEach
    void tearDown() { // 後処理を行うメソッド
        UserFunctions = null;
    }
}