package com.example.fragmenttuto;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
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
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.w3c.dom.Text;

public class FrgmFormation extends Fragment {

    private TextView mTextView;
    // このクラス内でだけ参照する値のため、BundleのKEYの値をprivateにする
    private final static String KEY_NAME = "key_name";
    private final static String KEY_OVERLAP = "key_overlap";
    private Drawable drw = null;

    // このメソッドからFragmentを作成することを強制する
    @CheckResult
    public static FrgmFormation createInstance(String name,  int overlap) {
        // Fragmentを作成して返すメソッド
        // createInstanceメソッドを使用することで、そのクラスを作成する際にどのような値が必要になるか制約を設けることができる
        FrgmFormation fragment = new FrgmFormation();
        // Fragmentに渡す値はBundleという型でやり取りする
        Bundle args = new Bundle();
        // Key/Pairの形で値をセットする
        args.putString(KEY_NAME, name);
        args.putInt(KEY_OVERLAP, overlap);
        // Fragmentに値をセットする
        fragment.setArguments(args);
        return fragment;
    }
    // 値をonCreateで受け取るため、新規で変数を作成する
    // 値がセットされなかった時のために初期値をセットする
    private String mName = "";
//    private @ColorInt
//    int mBackgroundColor = Color.TRANSPARENT;
    private int overlap;

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
            overlap = args.getInt(KEY_OVERLAP);
            mName = mName + " [ " + overlap + " ]";
        }
    }
    // Fragmentで表示するViewを作成するメソッド
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // 先ほどのレイアウトをここでViewとして作成します
        View view = inflater.inflate(R.layout.formtion_frgm, container, false);

        mTextView = view.findViewById(R.id.messageTxt);
        mTextView.setText(mName);

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


        View.OnClickListener button1_3ClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ○○○
                // □□□
                FragmentManager fragmentManager = getFragmentManager();
                if (fragmentManager != null) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    // BackStackを設定
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.container, FrgmFormation.createInstance("フォーメーション",overlap +1));
                    fragmentTransaction.commit();
                }
            }
        };
        view.findViewById(R.id.imageView1_3).setOnClickListener(button1_3ClickListener);



        View.OnClickListener button1ClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // ○○○
//                (ImageView)v.setColorFilter(Color.BLACK);
//                (ImageView)v.findViewById(R.id.imageView2_1).getDr
//                final ImageView ttapView = v.findViewById(R.id.imageView2_1);
//                ttapView.getDrawable().setTint(Color.RED);
                ImageView imgv = (ImageView) v;
                Drawable drw3 = imgv.getDrawable();
                drw = imgv.getDrawable();
//                drw.setTint(Color.LTGRAY);
//                Log.d("DEBUG1",aaaaa.toString());
               drw.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
//                drw.setTint(Color.RED);
//                drw.setTintMode(PorterDuff.Mode.SRC_IN);
//                Log.d("DEBUG2",aaaaa.toString());

                // □□□
//                mTextView.setText("ID:"+String.valueOf(v.getId()));
            }
        };
        view.findViewById(R.id.imageView2_1).setOnClickListener(button1ClickListener);
        view.findViewById(R.id.imageView2_2).setOnClickListener(button1ClickListener);
        view.findViewById(R.id.imageView2_3).setOnClickListener(button1ClickListener);
        view.findViewById(R.id.imageView3_1).setOnClickListener(button1ClickListener);
        view.findViewById(R.id.imageView3_2).setOnClickListener(button1ClickListener);
        view.findViewById(R.id.imageView3_3).setOnClickListener(button1ClickListener);
        view.findViewById(R.id.imageView3_4).setOnClickListener(button1ClickListener);

//        return inflater.inflate(R.layout.formtion_frgm, container, false);
        return view;
    }

    // Viewが生成し終わった時に呼ばれるメソッド
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
