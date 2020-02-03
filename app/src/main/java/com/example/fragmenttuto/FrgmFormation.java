package com.example.fragmenttuto;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
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
import androidx.fragment.app.FragmentManager;

public class FrgmFormation extends Fragment {

    private TextView mTextView;
    // このクラス内でだけ参照する値のため、BundleのKEYの値をprivateにする
    private final static String KEY_NAME = "key_name";
    private final static String KEY_BACKGROUND = "key_background_color";

    // このメソッドからFragmentを作成することを強制する
    @CheckResult
    public static FrgmFormation createInstance(String name, @ColorInt int color) {
        // Fragmentを作成して返すメソッド
        // createInstanceメソッドを使用することで、そのクラスを作成する際にどのような値が必要になるか制約を設けることができる
        FrgmFormation fragment = new FrgmFormation();
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
        View view = inflater.inflate(R.layout.formtion_frgm, container, false);

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
                layout.setBackgroundColor(Color.GREEN);
                layout.addView(imageView);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                // ダイアログを作成する
                Dialog dialog = new Dialog(getContext());
                // タイトルを非表示にする
                dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                imageView.setColorFilter(Color.BLUE);
                dialog.setContentView(layout);
                dialog.getWindow().setLayout(width,height);
                // ダイアログを表示する
                dialog.show();
            }
        });

        // タップでFragmentを１つ前に戻る
        final ImageView keeper = view.findViewById(R.id.imageView4_1);

        // 拡大対象のImageViewにタップ時のリスナーをセット
        keeper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                if(fragmentManager != null) {
                    fragmentManager.popBackStack();
                }
            }
        });

        mTextView = view.findViewById(R.id.messageTxt);

        View.OnClickListener button1ClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // ○○○
                // TextViewをひも付けます
//                mTextView = v.findViewById(R.id.messageTxt);

                // □□□
                mTextView.setText("AAAAAAA");
            }
        };
        view.findViewById(R.id.imageView2_1).setOnClickListener(button1ClickListener);








//        return inflater.inflate(R.layout.formtion_frgm, container, false);
        return view;
    }

    // Viewが生成し終わった時に呼ばれるメソッド
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
