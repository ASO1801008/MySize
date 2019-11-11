package jp.ac.asojuku.mysize

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.AdapterView
import android.widget.RadioButton
import android.widget.SeekBar
import android.widget.Spinner
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_height_activity.*

class height_activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_height_activity)
    }
    //再表示の時に呼ばれるライフサイクルのコールバックメソッドonResumeをoverride
    override fun onResume() {
        super.onResume()
        //スピナーにitem（選択肢）が選ばれた時のコールバックメソッドを設定
        spinner.onItemSelectedListener = //スピナーにアイテムを選んだ時の動きを持ったクラスの匿名インスタンスを代入
            object: AdapterView.OnItemSelectedListener{//アイテムを選んだ時の動きを持ったクラスの継承クラスを定義して匿名インスタンスにする
                override fun onItemSelected(//アイテムを選んだ時の処理
                parent: AdapterView<*>?,//選択が発生したビュー（スピナーのこと）
                view: View?,//選択されたビュー（選択したアイテムつまり値のこと）
                position: Int,//選んだ選択肢が何番目か
                id: Long//選択されたアイテムのID
                )
                {
                    //選択地を取得するためにスピナーのインスタンスを取得する
                        val spinner = parent as? Spinner;
                        //選択地を取得
                        val item = spinner?.selectedItem as? String;
                        //取得した値を身長の値のテキストビューに上書きする
                        item?.let{
                            if(it.isNotEmpty()){
                                height.text = it;//itつまり身長の値が空文字でなければ、身長のてきすとビュー(height)に代入
                            }
                        }
                }

                override fun onNothingSelected(//アイテムを選んだ時の処理
                    p0: AdapterView<*>?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            }
        //シークバーの処理を定義する
        //共有ぷりひゃれんすから身長設定値を取得する
        val pref = PreferenceManager.getDefaultSharedPreferences(this);
        val heightVal = pref.getInt("HEIGHT",160);//身長値を変数に保存
        height.text = heightVal.toString();//「身長」ラベルの値もこの取得地で上書き
        //シークバーの現在値(progress)も取得地で上書き
        seekBar.progress = heightVal;
        //シークバーの値が変更されたらコールバックされるメソッドを持つ
        //匿名クラスを引き渡す
        seekBar.setOnSeekBarChangeListener(
            object:SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(
                    seekBar: SeekBar?,//値が変化したシークバーのインスタンす
                    progress: Int,//値が変化したシークバーの現在地
                    fromUser: Boolean//ユーザーが操作したか
                ) {
                    //ユーザーの指定地で「身長」の表示を変える
                    height.text = progress.toString();//heightラベルの表示地を上書き
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                   //今回は何もしない
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                   //今回は何もしない
                }
            }
        )

        //ラジオボタンの処理を実装する
        //ラジオグループに選択されたときに反応するコールバックメソッドを待機するリスナーを設定
        radioGroup.setOnCheckedChangeListener{
                //二つの引数（第一引数:ラジオボタングループ）、（台に引数:選択されたラジオボタンのid）を受け取って実行する処理
                group,chekedId->
                //「身長」ラベルを上書き(ラジオグループの選ばれたIDボタンのtext属性の値で上書き)
                height.text = findViewById<RadioButton>(chekedId).text;
        }
    }
    //画面が閉じられるときに呼ばれるライフサイクルのコールバックメソッドonPauseをoverride
    override fun onPause() {
        super.onPause()
        //身長の現在地を共有プリファレンスに保存する処理を実装
        //共有プリファレンスのインスタンんすを取得
        val pref = PreferenceManager.getDefaultSharedPreferences(this);
        pref.edit{
            //身長ラベルの保存値をStringに変換したのち、Intに変換して保存
            this.putInt("HEIGHT",height.text.toString().toInt());
        }
    }
}

