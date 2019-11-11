package jp.ac.asojuku.mysize

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    /*pushの練習コメント*/
    /*commit & pushの練習コメント*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    //再表示のたびに呼ばれるライフサイクルイベントのコールバックメソッド
    override fun onResume(){
        super.onResume();
        //入力値を端末内に保存
        //共有プリファレンスのインスタンスのインスタンスを取得
        val pref = PreferenceManager.getDefaultSharedPreferences(this);
        //共有プリファレンスのインスタンスから４つの保存済みの値を取り出す
        val editNeck = pref.getString("NECK","");//首回りの保存値を取得
        val editSleeve = pref.getString("SLEEVE","");//裄丈周りの保存値を取得
        val editWaist = pref.getString("WAIST","");//胴回りの保存値を取得
        val editInseam = pref.getString("INSEAM","");//股周りの保存値を取得
        //取得した保存値で各入力値の表示を上書きする
        neck.setText(editNeck);
        sleeve.setText(editSleeve);
        waist.setText(editWaist);
        inseam.setText(editInseam);
        //保存ボタンが押されたときに処理を設定
        save.setOnClickListener{ onsaveTapped() };

        //身長アイコンボタンのクリック時の画面遷移を設定
        heightButton.setOnClickListener{
            //クリックされたときにOSが呼び出す処理を実装
            //インデントを定義
            val intent = Intent(this,height_activity::class.java)
            //画面遷移メソッドを実行
            this.startActivity(intent);
        }
    }



    //保存ボタンは押されたらOSがコールバックする処理
    private fun onsaveTapped(){
        //画面表示の値を共有プリファレンスに保存する
        //共有プリファレンスのインスタンスを取得
        val pref = PreferenceManager.getDefaultSharedPreferences(this);
        //共有プリファレンスを更新(editメソッド)
        pref.edit {
            this.putString("NECK", neck.text.toString());//首回りの入力値を共有プリファレンスに保存
            this.putString("SLEEVE", sleeve.text.toString());//裄丈の入力値を共有プリファレンスに保存
            this.putString("WAIST", waist.text.toString());//胴回りの入力値を共有プリファレンスに保存
            this.putString("INSEAM", inseam.text.toString());//股下の入力値を共有プリファレンスに保存
            // の入力値を共有プリファレンスに保存
        }
    }
}
