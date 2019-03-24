package com.example.myapplication;

import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_main);
    }

    public void onbuttonClick(View view){
        EditText Et=(EditText)findViewById(R.id.input);//輸入的組數
        RadioButton rb1=(RadioButton)findViewById(R.id.radiobutton1);//選擇大樂透
        RadioButton rb2=(RadioButton)findViewById(R.id.radiobutton2);//選擇威力彩
        TextView Tv=(TextView) findViewById(R.id.output);//輸出的結果

        if(rb1.isChecked()||rb2.isChecked()) {
            if (Et.getText().toString().matches("")) {//使用者未輸入組數
                Toast.makeText(this, "請輸入組數", Toast.LENGTH_LONG).show();
            } else {
                if (rb1.isChecked()) {//選擇大樂透或威力彩
                    int num = Integer.parseInt(Et.getText().toString());//把字串轉成數字
                    Tv.setText(Lotto(0, num));//大樂透
                } else if (rb2.isChecked()) {
                    int num = Integer.parseInt(Et.getText().toString());//把字串轉成數字
                    Tv.setText(Lotto(1, num));//威力彩
                }
            }
        }
        else{
            Toast.makeText(this, "請選擇大樂透或者威力彩", Toast.LENGTH_LONG).show();//使用者未選擇大樂透或威力彩
        }
        if ((rb1.isChecked()||rb2.isChecked())&&Et.getText().toString().matches("")) {//使用者未輸入組數
            Toast.makeText(this, "請輸入組數", Toast.LENGTH_LONG).show();
        }
    }

    public String Lotto(int type,int times) {
        int num, count, num2, biggesNum;
        boolean flag;
        int[] lotteryNum = new int [7];
        String result;
        int i,j;
        if(type == 0){//設定大樂透號碼總數
            biggesNum=49;
            result=" 大樂透 "+times+" 組號碼 :\r\n";
        }
        else{//設定威力彩號碼總數
            biggesNum=38;
            result = " 威力彩 " + times + " 組號碼 :\r\n";
            result += "                               " + "第一區                  "+"          第二區\r\n";
        }

        for(i=0;i<times;i++){//產生六個大樂透號碼
            count=0;
            for(j=0;j<6;j++){
                lotteryNum[j]=0;
            }
            do
            {
                num=(int)Math.floor((Math.random()*biggesNum)+1);
                flag=exist(lotteryNum,count,num);
                if (flag==false){
                    lotteryNum[count]=num;
                    count++;
                }
            }
            while(count<6);
            result+=" 第 "+twodigits((i+1))+ " 組 : ";
            for(int k=0;k<6;k++){
                result+=twodigits(lotteryNum[k])+"    ";
            }
            if(type==0){
                result+="\r\n";
            }
            else {
                num2=(int)Math.floor((Math.random()*8)+1);
                result+="     "+num2+"\r\n";//產生第二區的號碼
            }
        }
        return result;//回傳字串
    }

    public boolean exist(int[] numarray,int count,int number){
        boolean status=false;//預設為沒有重複
        for(int i=0;i<count;i++){
            if ((numarray[i]==number)){//檢查是否重複
                status=true;//表示號碼有重複
                break;
            }
        }
        return status;
    }
    public String twodigits(int value){//讓數字限制為2位數 把0~9變成01~09，2位數直接輸出
        int digit1=value%10;
        int digit2=(int)Math.floor(value/10);
        if (value<10){
            return "0"+String.valueOf(value);//讓1位數變成2位整數
        }
        else{
            return String.valueOf(value);//兩位數的整數直接輸出
        }
    }
}
