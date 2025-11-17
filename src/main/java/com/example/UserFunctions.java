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

    /**
     * 年齢が20歳以上であれば成人としてtrueを返す。
     * @return 成人であればtrue
     */
    public boolean isAdult() {
        return this.age >= 20;
    }
}

