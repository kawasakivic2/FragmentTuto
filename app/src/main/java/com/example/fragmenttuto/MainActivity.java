package com.example.fragmenttuto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // コードからFragmentを追加

        // Fragmentを作成します
//        FrgmNavigator fragment = new FrgmNavigator();
//        FrgmNavigator fragment2 = new FrgmNavigator();
        // Fragmentの追加や削除といった変更を行う際は、Transactionを利用します
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // 新しく追加を行うのでaddを使用します
        // 他にも、よく使う操作で、replace removeといったメソッドがあります
        // メソッドの1つ目の引数は対象のViewGroupのID、2つ目の引数は追加するfragment
        //transaction.add(R.id.container, fragment);        // 最後にcommitを使用することで変更を反映します
        //transaction.add(R.id.container, fragment2);        // 最後にcommitを使用することで変更を反映します
//        transaction.add(R.id.container, FrgmNavigator.createInstance("上半分", Color.MAGENTA));
//        transaction.add(R.id.container, FrgmNavigator.createInstance("下半分", Color.CYAN));
        transaction.add(R.id.container, FrgmFormation.createInstance("Navigation", Color.DKGRAY));
        transaction.commit();
    }
}
