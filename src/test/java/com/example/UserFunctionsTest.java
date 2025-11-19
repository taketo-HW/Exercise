import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

//test対象のクラスをインポート
import com.example.UserFunctions;
import com.example.ScoreValidator;

/**
 * UserFunctionsクラスのテスト
 */
public class UserFunctionsTest {
    
    private UserFunctions UserFunctions;
    
    @BeforeEach  // 初期化処理を行うメソッド
    void setUp() {
        UserFunctions = new UserFunctions();
    }

    @AfterEach
    void tearDown() { // 後処理を行うメソッド
        UserFunctions = null;
    }

    @Test // 成人判定ロジックの検証（アサーション）
    void testIsMaleFitnessTestPassed_1A_1() {
        //
        UserFunctions.setAge(20);
        UserFunctions.setGender("MALE");
        UserFunctions.setFitnessScore(60);
        assertTrue(UserFunctions.isFitnessTestPassed(), "テストケース A: 20歳以上男性の合格テスト（最小合格境界値）");
    }

    @Test //成人判定ロジックの検証（アサーション）
    void testIsMaleFitnessTestPassed_1B_1() {
        //20歳以上男性の不合格テスト（最大不合格境界値）
        UserFunctions.setAge(20);
        UserFunctions.setGender("MALE");
        UserFunctions.setFitnessScore(59);
        assertFalse(UserFunctions.isFitnessTestPassed(), "テストケース A: 20歳以上男性の不合格テスト（最大不合格境界値）");
    }
    
    @Test //成人判定ロジックの検証（アサーション）
    void testIsMaleFitnessTestPassed_1B_2() {
        //20歳以上男性の不合格テスト（最大不合格境界値）
        UserFunctions.setAge(21);
        UserFunctions.setGender("MALE");
        UserFunctions.setFitnessScore(60);
        assertTrue(UserFunctions.isFitnessTestPassed(), "テストケース A: 20歳以上男性の合格テスト（最小合格境界値）");
    }

    @Test //成人判定ロジックの検証（アサーション）
    void testIsFitnessTestPassed_Under20_Over80() { 
        // 19歳以下、80点以上 → 合格
        UserFunctions.setAge(19);
        UserFunctions.setGender("MALE");
        UserFunctions.setFitnessScore(80);
        assertTrue(UserFunctions.isFitnessTestPassed(), "19歳以下、80点 → 合格");
    }

    @Test //成人判定ロジックの検証（アサーション）
    void testIsFitnessTestPassed_Under20_Under80() { 
        // 19歳以下、80点未満 → 不合格
        UserFunctions.setAge(19);
        UserFunctions.setGender("OTHER");
        UserFunctions.setFitnessScore(79);
        assertFalse(UserFunctions.isFitnessTestPassed(), "19歳以下、79点 → 不合格");
    }
    
    @Test //成人判定ロジックの検証（アサーション）
    void testIsFitnessTestPassed_Other() {
        // 20歳以上、その他/未設定 → 不合格（点数に関係なく）
        UserFunctions.setAge(20);
        UserFunctions.setGender("OTHER");
        UserFunctions.setFitnessScore(100);
        assertFalse(UserFunctions.isFitnessTestPassed(), "20歳以上その他、100点 → 不合格");
    }

    @Test //成人判定ロジックの検証（アサーション）
    void testIsFemaleFitnessTestPassed_1A_3() {
        UserFunctions.setAge(25);
        UserFunctions.setGender("FEMALE");
        UserFunctions.setFitnessScore(54);
        assertFalse(UserFunctions.isFitnessTestPassed(), "20歳以上女性の不合格境界値テスト（最大不合格境界値）");
    }
    
    @Test //成人判定ロジックの検証（アサーション）
    void testIsFemaleFitnessTestPassed_1B_3() { // 20歳以上女性の合格境界値テスト（最小合格境界値）
        UserFunctions.setAge(25);
        UserFunctions.setGender("FEMALE");
        UserFunctions.setFitnessScore(55);
        assertTrue(UserFunctions.isFitnessTestPassed(), "20歳以上女性の合格境界値テスト（最小合格境界値）");
    }

    @Test //成人判定ロジックの検証（アサーション）
    void testIsFemaleFitnessTestPassed_1B_4() { 
        // 20歳以上女性の合格テスト（55点以上：56点）
        UserFunctions.setAge(25);
        UserFunctions.setGender("FEMALE");
        UserFunctions.setFitnessScore(56);
        assertTrue(UserFunctions.isFitnessTestPassed(), "20歳以上女性、56点 → 合格");
    }


    // === 2. モックを利用したcheckAndPassの全分岐網羅テスト ===
    
    @Test
    void testcheckAndPass_A1() {
        // テストケース A-1 (合格ブランチ): スタブ設定Aを使用し、点数を50点に設定して、trueを返すことを検証
        // → 内部ロジックで合格（ブランチ2）
        ScoreValidator validator = mock(ScoreValidator.class);
        
        // スタブ設定A: 任意の点数で常にtrueを返す
        when(validator.validate(anyInt())).thenReturn(true);
        
        // 点数50点で合格を検証
        assertTrue(UserFunctions.checkAndPass(50, validator), 
                   "テストケース A-1: スタブ設定A、50点 → 合格（ブランチ2）");
    }
    
    @Test
    void testcheckAndPass_A2() {
        // テストケース A-2 (不合格ブランチ): スタブ設定Aを使用し、点数を49点に設定して、falseを返すことを検証
        // → 内部ロジックで不合格（ブランチ3）
        ScoreValidator validator = mock(ScoreValidator.class);
        
        // スタブ設定A: 任意の点数で常にtrueを返す
        when(validator.validate(anyInt())).thenReturn(true);
        
        // 点数49点で不合格を検証
        assertFalse(UserFunctions.checkAndPass(49, validator), 
                    "テストケース A-2: スタブ設定A、49点 → 不合格（ブランチ3）");
    }
    
    @Test
    void testcheckAndPass_B1() {
        // テストケース B-1 (外部バリデーターによる不合格): スタブ設定Bを使用し、点数を0点に設定して、falseを返すことを検証
        // → 外部チェックで不合格（ブランチ1）
        ScoreValidator validator = mock(ScoreValidator.class);
        
        // スタブ設定B: 任意の点数でfalseを返す
        when(validator.validate(anyInt())).thenReturn(false);
        
        // 任意の点数で不合格を検証（外部バリデーターがfalseを返すため）
        assertFalse(UserFunctions.checkAndPass(100, validator), 
                    "テストケース B-3: スタブ設定B、100点 → 不合格（ブランチ1：外部チェック）");
    }
    
    @Test
    void testcheckAndPass_B3() {
        // 前網羅用テストケース B-3 (外部バリデーターによる不合格): スタブ設定Bを使用し、点数を0点に設定して、falseを返すことを検証
        // → 外部チェックで不合格（ブランチ1）
        ScoreValidator validator = mock(ScoreValidator.class);
        
        // スタブ設定B: 0点でfalse、他の点数でtrueを返す
        when(validator.validate(0)).thenReturn(false);
        when(validator.validate(anyInt())).thenReturn(true);
        
        // 点数0点で不合格を検証（外部バリデーターがfalseを返すため）
        assertFalse(UserFunctions.checkAndPass(0, validator), 
                    "テストケース B-1: スタブ設定B、0点 → 不合格（ブランチ1：外部チェック）");
    }

    // === 3. モックの呼び出し検証 (Verification) ===
    
    @Test
    void testcheckAndPass_C1() {
        // テストケース C-1 (呼び出し検証): スタブ設定Aを使用し、checkAndPass(60, validator)を実行した後、
        // validator.validate()メソッドが正確に1回呼び出されたことを検証
        ScoreValidator validator = mock(ScoreValidator.class);
        
        // スタブ設定A: 任意の点数で常にtrueを返す
        when(validator.validate(anyInt())).thenReturn(true);
        
        // checkAndPassを実行
        UserFunctions.checkAndPass(60, validator);
        
        // validator.validate()が正確に1回呼び出されたことを検証
        verify(validator, times(1)).validate(60);
    }


    
    @Test
    void testAdd_1() {
        assertEquals(10, UserFunctions.add(5, 5), "5 + 5 = 10");
    }
    @Test
    void testDivide_1() { // b =0の場合
        assertThrows(ArithmeticException.class, () -> UserFunctions.divide(10, 0), "10 / 0 = ArithmeticException");
    }
    @Test
    void testDivide_2() { // b != 0の場合
        assertEquals(2, UserFunctions.divide(10, 5), "10 / 5 = 2");
    }

    // === 4. Setter/Getterメソッドのテスト ===
    
    @Test
    void testSetterGetter_Name() {
        // setName/getNameのテスト
        UserFunctions.setName("Test User");
        assertEquals("Test User", UserFunctions.getName(), "setName/getNameのテスト");
    }
    
    @Test
    void testSetterGetter_Age() {
        // setAge/getAgeのテスト
        UserFunctions.setAge(20);
        assertEquals(20, UserFunctions.getAge(), "setAge/getAgeのテスト");
    }
    
    @Test
    void testSetterGetter_FitnessScore() {
        // setFitnessScore/getFitnessScoreのテスト
        UserFunctions.setFitnessScore(60);
        assertEquals(60, UserFunctions.getFitnessScore(), "setFitnessScore/getFitnessScoreのテスト");
    }
    
    @Test
    void testSetterGetter_Gender() {
        // setGender/getGenderのテスト
        UserFunctions.setGender("MALE");
        assertEquals("MALE", UserFunctions.getGender(), "setGender/getGenderのテスト");
    }
}