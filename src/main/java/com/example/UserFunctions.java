package com.example;

public class UserFunctions {

    // 既存のテストで使われている基本的な計算機能もここに含める
    public int add(int a, int b) {
        return a + b;
    }
    
    // 5章の例外テストで使用する除算機能
    public int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("/ by zero");
        }
        return a / b;
    }
    
    // --- 演習問題 4章で追加する User 機能 ---
    private String name;
    private int age;
    private String gender; // "MALE", "FEMALE", "OTHER"
    private int fitnessScore; // 体力テストの点数（0-100点）

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getFitnessScore() {
        return fitnessScore;
    }

    public void setFitnessScore(int fitnessScore) {
        this.fitnessScore = fitnessScore;
    }

    /**
     * 年齢、性別、体力テストの点数を考慮して体力テストの合格判定を行う。
     * 複雑な条件分岐の入れ子構造で判定ロジックを実装。
     * @return 体力テストに合格した場合true
     */
    public boolean isFitnessTestPassed() {
        // 基本的な年齢チェック
        if (this.age < 18) {
            return false;
        }
        
        // 年齢が18歳以上の場合、性別による条件分岐
        if (this.age >= 20) {
            // 20歳以上の場合
            if (this.gender != null) {
                if (this.gender.equals("MALE")) {
                    // 男性の場合、20歳以上で点数が80点以上なら特別に合格
                    if (this.fitnessScore >= 80) {
                        return true;
                    } else if (this.fitnessScore >= 60) {
                        // 点数が60点以上80点未満なら合格
                        return true;
                    } else {
                        return false;
                    }
                } else if (this.gender.equals("FEMALE")) {
                    // 女性の場合、20歳以上で点数が75点以上なら特別に合格
                    if (this.fitnessScore >= 75) {
                        return true;
                    } else if (this.fitnessScore >= 55) {
                        // 点数が55点以上75点未満なら合格
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    // その他の性別の場合、20歳以上で点数が78点以上なら特別に合格
                    if (this.fitnessScore >= 78) {
                        return true;
                    } else if (this.fitnessScore >= 58) {
                        // 点数が58点以上78点未満なら合格
                        return true;
                    } else {
                        return false;
                    }
                }
            } else {
                // 性別が設定されていない場合、20歳以上で点数が65点以上が必要
                if (this.fitnessScore >= 65) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            // 18歳以上20歳未満の場合
            if (this.gender != null) {
                if (this.gender.equals("FEMALE")) {
                    // 女性は18-19歳の場合、点数が85点以上なら特別に合格
                    if (this.fitnessScore >= 85) {
                        return true;
                    } else if (this.fitnessScore >= 70) {
                        // 点数が70点以上85点未満なら合格
                        return true;
                    } else {
                        return false;
                    }
                } else if (this.gender.equals("MALE")) {
                    // 男性は18-19歳の場合、点数が90点以上なら特別に合格
                    if (this.fitnessScore >= 90) {
                        return true;
                    } else if (this.fitnessScore >= 75) {
                        // 点数が75点以上90点未満なら合格
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    // その他の性別の場合、18-19歳で点数が88点以上なら特別に合格
                    if (this.fitnessScore >= 88) {
                        return true;
                    } else if (this.fitnessScore >= 72) {
                        // 点数が72点以上88点未満なら合格
                        return true;
                    } else {
                        return false;
                    }
                }
            } else {
                // 性別が設定されていない場合、18-19歳は点数が80点以上でないと認めない
                if (this.fitnessScore >= 80) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }
}

