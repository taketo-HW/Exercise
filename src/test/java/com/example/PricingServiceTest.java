import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.api.Tag;
import java.util.stream.Stream;

//test対象のクラスをインポート
import com.example.PricingService;

/**
 * 割引額計算ロジックのテスト PricingServiceクラスのテスト
 */

public class PricingServiceTest {
    
    private PricingService PricingService;
    
    @BeforeEach  // 初期化処理を行うメソッド
    void setUp() {
        PricingService = new PricingService();
    }
    @AfterEach
    void tearDown() {
        PricingService = null;
    }

    //A-1: 割引率の境界値テスト
    @Tag("Guard_Boundary")
    @Test
    void testCalculateDiscount_1A_1(){
        //割引率として-1（最小不合格境界値）を設定した場合、IllegalArgumentExceptionをスロー
        assertThrows(IllegalArgumentException.class, () -> PricingService.calculateDiscount(10000, -1));
    }
    @Tag("Guard_Boundary")
    @Test
    void testCalculateDiscount_1A_2(){
        //割引率として101（最大不合格境界値）を設定した場合、IllegalArgumentExceptionをスロー
        assertThrows(IllegalArgumentException.class, () -> PricingService.calculateDiscount(10000, 101));
    }

    // データソースメソッドの定義
    //B-1: 割引額の境界値テスト
    static Stream<Arguments> doc_data() {
        return Stream.of(
            // Arguments.of(a, b, expected)
            Arguments.of(1000, 0, 0),        //正常な境界値: (元の価格: 1000, 割引率: 0, 期待される割引額: 0)
            Arguments.of(1000, 100, 1000),   //正常な境界値: (元の価格: 1000, 割引率: 100, 期待される割引額: 1000)
            Arguments.of(999, 10, 99)       //正常な境界値: (元の価格: 999, 割引率: 10, 期待される割引額: 99)
        );
    }

    // パラメータ化されたテスト
    // doc_data メソッドから提供されるデータで3回実行される
    @ParameterizedTest
    @MethodSource("doc_data")
    @Tag("Calculation_Full")
    void testCalculateDiscount_1B(int originalPrice, int discountRate, int expected) {
        int actual = PricingService.calculateDiscount(originalPrice, discountRate);
        assertEquals(expected, actual, "割引額が期待値と異なります");
    }
}