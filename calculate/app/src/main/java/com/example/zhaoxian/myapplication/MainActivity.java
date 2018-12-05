package com.example.zhaoxian.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView input = null;  //初始化
    boolean clear_flag; //初始化
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input= (TextView) findViewById(R.id.input);
        //数字实例化
        Button button_0 = (Button) findViewById(R.id.button_0);
        Button button_1 = (Button) findViewById(R.id.button_1);
        Button button_2 = (Button) findViewById(R.id.button_2);
        Button button_3 = (Button) findViewById(R.id.button_3);
        Button button_4 = (Button) findViewById(R.id.button_4);
        Button button_5 = (Button) findViewById(R.id.button_5);
        Button button_6 = (Button) findViewById(R.id.button_6);
        Button button_7 = (Button) findViewById(R.id.button_7);
        Button button_8 = (Button) findViewById(R.id.button_8);
        Button button_9 = (Button) findViewById(R.id.button_9);

        //操作实例化
        Button point = (Button) findViewById(R.id.point);
        Button clear = (Button) findViewById(R.id.clear);
        Button delet = (Button) findViewById(R.id.delet);
        Button percent = (Button) findViewById(R.id.percent);
        Button divide = (Button) findViewById(R.id.divide);
        Button mul = (Button) findViewById(R.id.mul);
        Button sub = (Button) findViewById(R.id.sub);
        Button add = (Button) findViewById(R.id.add);
        Button equ = (Button) findViewById(R.id.equ);


        button_0.setOnClickListener(this);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);
        button_5.setOnClickListener(this);
        button_6.setOnClickListener(this);
        button_7.setOnClickListener(this);
        button_8.setOnClickListener(this);
        button_9.setOnClickListener(this);
        point.setOnClickListener(this);
        clear.setOnClickListener(this);
        delet.setOnClickListener(this);
        add.setOnClickListener(this);
        point.setOnClickListener(this);
        mul.setOnClickListener(this);
        divide.setOnClickListener(this);
        input.setOnClickListener(this);
        percent.setOnClickListener(this);
        sub.setOnClickListener(this);
        equ.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String str = input.getText().toString();
        switch(v.getId()) {
            case R.id.button_0:
            case R.id.button_1:
            case R.id.button_2:
            case R.id.button_3:
            case R.id.button_4:
            case R.id.button_5:
            case R.id.button_6:
            case R.id.button_7:
            case R.id.button_8:
            case R.id.button_9:
            case R.id.point:
                if (clear_flag) {
                    clear_flag = false;
                    str = "";
                }
                input.setText(str + ((Button) v).getText());
                break;
            case R.id.sub:
            case R.id.divide:
            case R.id.mul:
            case R.id.add:
                clear_flag = false;
                input.setText(str + " " + ((Button) v).getText().toString() + " ");//在屏幕显示+，-，*，/，并于字符间有空格
                break;
            case R.id.percent:
                double result3 = 0.01 * Double.parseDouble(str);
                String r3 = String.valueOf(result3);
                input.setText(r3);
                break;

            case R.id.delet:
                input.setText(str.substring(0, str.length() - 1));//从字符串的0位置开始，索引到字符串的倒数第二个字符串
                break;
            case R.id.clear:
                clear_flag = false;
                input.setText("");
                break;
            case R.id.equ:
                getResult();
                break;
        }
    }

    private void getResult(){
        String exp = input.getText().toString();

        if(!exp.contains(" ")){
            return;
        }
        clear_flag = true;
        double result = 0.0;
        String s1 = exp.substring(0, exp.indexOf(" ")); //获取运算符前面的字符串
        String op = exp.substring(exp.indexOf(" ") + 1, exp.indexOf(" ") + 2);
        String s2 = exp.substring(exp.indexOf(" ") + 3);//获取运算符后面的字符串
        if (!s1.equals(" ") && !s2.equals(" ")) {
            double d1 = Double.parseDouble(s1);
            double d2 = Double.parseDouble(s2);
            switch (op) {
                case "+":
                    result = d1 + d2;
                    break;
                case "-":
                    result = d1 - d2;
                    break;
                case "*":
                    result = d1 * d2;
                    break;
                case "/":
                    if (d2 == 0) {
                        Toast.makeText(MainActivity.this, "除数不能为0", Toast.LENGTH_SHORT).show();
                    } else {
                        result = d1 / d2;
                    }
                    break;
            }
            //判断第一个字符是否为小数点，如果是小数点自动变为0.x,如果用正负号转换，自动变为-0.x
            if (s1.charAt(0)=='.') {
                d1=0+d1;
            }
            if(s2.charAt(0)=='.'){
                d2=0+d2;
            }
            if(s1.charAt(0)!='-'){
                d1=0-d1;
            }

            input.setText(String.valueOf(result));//把运算结果输出到显示屏

        } else if(s1.charAt(0)!='-'){
            String s = exp.substring(2, exp.indexOf(" "));
            double d1 =0- Double.parseDouble(s);
        }else if(s2.charAt(0)!='-'){
            String s = exp.substring(exp.indexOf(" ") + 4);
            double d2 =0- Double.parseDouble(s);
        }else
            return;
    }
}

