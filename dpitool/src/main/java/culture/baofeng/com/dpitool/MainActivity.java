package culture.baofeng.com.dpitool;

import android.content.DialogInterface;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Build;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String items[]={"px转dp","px转sp"};
    private TextView dr;
    private Button cacu;
    private TextInputEditText et;
    private int px =0;
    private int currentCV =0;//如果是0，代表dp，如果是1代表sp
    private RadioGroup group;
    private TextView value;
    private TextView bv;
    private TextView msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface typeFace =Typeface.createFromAsset(getAssets(),"UniSansThin.otf");
        dr = findViewById(R.id.dprt);
        dr.setTypeface(typeFace);
        cacu = findViewById(R.id.caculate);
        et = findViewById(R.id.etinput);
        group = findViewById(R.id.radiogrop);
        bv = findViewById(R.id.bigvalue);
        msg = findViewById(R.id.msg);
        int[] arr = DpiUtils.getMobileInfo(MainActivity.this);
        msg.setText("当前设备信息：\n" +"width*height:"+arr[1]+"*"+arr[0]+"      " +
                        "dpi:"+arr[2]+"      Api-Level:"+ Build.VERSION.SDK_INT+"\n" +
                        "系统版本："+Build.VERSION.RELEASE+"     设备型号："+Build.BRAND+" "+Build.MODEL
                );
        bv.setText("dp");
        value = findViewById(R.id.value);
        cacu.setText("计算dp值");
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int buttonId = radioGroup.getCheckedRadioButtonId();
                switch (buttonId){
                    case R.id.cacudp:
                        currentCV=0;
                        cacu.setText("计算dp值");
                        bv.setText("dp");
                        break;
                    case R.id.cacusp:
                        currentCV=1;
                        cacu.setText("计算sp值");
                        bv.setText("sp");
                        break;
                }
                et.setText("");
                dr.setText("0.00");
            }
        });

        cacu.setOnClickListener(this);
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().equals("")){
                    return;
                }else {
                    px = Integer.parseInt(editable.toString());
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.caculate:
                if (px!=0){
                    if (currentCV==0){
                        float dp = DpiUtils.convertPixelToDp(px, MainActivity.this);
                        dr.setText( String.format("%.2f", dp));
                    }else {
                        float sp = DpiUtils.px2sp(MainActivity.this,px);
                        dr.setText(sp+"");
                    }
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {

        ExitUtil.ExitBetween2s(MainActivity.this);
    }
}
