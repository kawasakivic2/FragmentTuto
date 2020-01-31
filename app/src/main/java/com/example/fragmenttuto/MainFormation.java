package com.example.fragmenttuto;


import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.CheckResult;
import androidx.annotation.ColorInt;
import androidx.fragment.app.Fragment;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

public class MainFormation extends Fragment {

    private TextView mTextView;
    // このクラス内でだけ参照する値のため、BundleのKEYの値をprivateにする
    private final static String KEY_NAME = "key_name";
    private final static String KEY_BACKGROUND = "key_background_color";

    // このメソッドからFragmentを作成することを強制する
    @CheckResult
    public static MainFormation createInstance(String name, @ColorInt int color) {
        // Fragmentを作成して返すメソッド
        // createInstanceメソッドを使用することで、そのクラスを作成する際にどのような値が必要になるか制約を設けることができる
        MainFormation fragment = new MainFormation();
        // Fragmentに渡す値はBundleという型でやり取りする
        Bundle args = new Bundle();
        // Key/Pairの形で値をセットする
        args.putString(KEY_NAME, name);
        args.putInt(KEY_BACKGROUND, color);
        // Fragmentに値をセットする
        fragment.setArguments(args);
        return fragment;
    }
    // 値をonCreateで受け取るため、新規で変数を作成する
    // 値がセットされなかった時のために初期値をセットする
    private String mName = "";
    private @ColorInt
    int mBackgroundColor = Color.TRANSPARENT;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Bundleの値を受け取る際はonCreateメソッド内で行う
        Bundle args = getArguments();
        // Bundleがセットされていなかった時はNullなのでNullチェックをする
        if (args != null) {
            // String型でNameの値を受け取る
            mName = args.getString(KEY_NAME);
            // int型で背景色を受け取る
            mBackgroundColor = args.getInt(KEY_BACKGROUND, Color.TRANSPARENT);
        }
    }
    // Fragmentで表示するViewを作成するメソッド
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // 先ほどのレイアウトをここでViewとして作成します
        View view = inflater.inflate(R.layout.formtion_main, container, false);
        // レイアウトオブジェクトから拡大対象のImageViewを取得
        final ImageView tapView = view.findViewById(R.id.imageView1_1);

        // 拡大対象のImageViewにタップ時のリスナーをセット
        tapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = new ImageView(getContext());
                Drawable bitmap = tapView.getDrawable();
                imageView.setImageDrawable(bitmap);
                // ディスプレイの幅を取得する（API 13以上）
                Display display =  getActivity().getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int width = size.x;
                int height = tapView.getHeight() * (width / tapView.getWidth() );
                LinearLayout layout = new LinearLayout(getContext());
                layout.setLayoutParams(new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.MATCH_PARENT));
//                ViewGroup.LayoutParams layoutParams  = layout.getLayoutParams();

//                layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
//                layoutParams.width= ViewGroup.LayoutParams.MATCH_PARENT;
                layout.setBackgroundColor(Color.GREEN);

                layout.addView(imageView);


//                float factor =  width / bitmap.
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                // ダイアログを作成する
//                Dialog dialog = new Dialog(MainActivity.this);
                Dialog dialog = new Dialog(getContext());
                // タイトルを非表示にする
                dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                imageView.setColorFilter(Color.BLUE);
//                dialog.setContentView(imageView);
                dialog.setContentView(layout);
//                dialog.getWindow().setLayout((int)(bitmap.getWidth()*factor), (int)(bitmap.getHeight()*factor));
                dialog.getWindow().setLayout(width,height);
                // ダイアログを表示する
                dialog.show();
            }
        });

//        return inflater.inflate(R.layout.formtion_main, container, false);
        return view;
    }

    // Viewが生成し終わった時に呼ばれるメソッド
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        // TextViewをひも付けます
//        mTextView = (TextView) view.findViewById(R.id.textView);
//        // Buttonのクリックした時の処理を書きます
//        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mTextView.setText(mTextView.getText() + "!");
//            }
//        });
//        // ラストに追加
//        // 背景色をセットする
//        view.setBackgroundColor(mBackgroundColor);
//        // onCreateで受け取った値をセットする
//        mTextView.setText(mName);
    }

}
