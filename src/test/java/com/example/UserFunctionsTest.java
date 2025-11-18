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

    @Test // 成人判定ロジックの検証（アサーション）
    void testIsMaleFitnessTestPassed_1A_1() {
        UserFunctions.setAge(20);
        UserFunctions.setGender("MALE");
        UserFunctions.setFitnessScore(60);
        assertTrue(UserFunctions.isFitnessTestPassed(), "テストケース A: 20歳以上男性の合格テスト（最小合格境界値）");
    }

    @Test //成人判定ロジックの検証（アサーション）
    void testIsMaleFitnessTestPassed_1A_2() {
        UserFunctions.setAge(19);
        UserFunctions.setGender("OTHER");
        UserFunctions.setFitnessScore(79);
        assertFalse(UserFunctions.isFitnessTestPassed(), "19歳以下の不合格テスト（最大不合格境界値）");
    }

    @Test //成人判定ロジックの検証（アサーション）
    void testIsFemaleFitnessTestPassed_1A_3() {
        UserFunctions.setAge(25);
        UserFunctions.setGender("FEMALE");
        UserFunctions.setFitnessScore(54);
        assertFalse(UserFunctions.isFitnessTestPassed(), "20歳以上女性の不合格境界値テスト（最大不合格境界値）");
    }

    @AfterEach
    void tearDown() { // 後処理を行うメソッド
        UserFunctions = null;
    }
}