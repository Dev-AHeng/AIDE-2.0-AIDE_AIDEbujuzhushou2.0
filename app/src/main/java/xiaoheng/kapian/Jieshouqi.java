package xiaoheng.kapian;

import android.content.*;
import android.graphics.*;
import android.os.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.util.*;
import android.view.*;
import android.widget.*;

import android.support.v7.widget.Toolbar;

public class Jieshouqi extends AppCompatActivity
{
		private String getbiaoti,getneirong;
		@Override
		protected void onCreate(Bundle savedInstanceState)
		{
				// TODO: Implement this method
				super.onCreate(savedInstanceState);
				setContentView(R.layout.jieshouqi_edittexthanghao);
				
				Intent intent=getIntent();
				getbiaoti=intent.getStringExtra("biaoti");
				getneirong=intent.getStringExtra("neirong");

				EditText edittext=(EditText)findViewById(R.id.jieshouqiedittexthanghaoJiesouqi_edittexthanghao1);
				edittext.setText(getneirong);
				
				
				Toolbar toolbar = (Toolbar) findViewById(R.id.jieshouqiedittexthanghaoToolbar1);
				//设置toolbar颜色
				toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
				//设置标题
				toolbar.setTitle(getbiaoti);
				//添加toolbar
				setSupportActionBar(toolbar);
				//toolbar添加返回按钮
				getSupportActionBar().setDisplayHomeAsUpEnabled(true);
				//返回按钮点击事件
				toolbar.setNavigationOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View view)
								{
										//退出
										finish();
								}
						});
		}
}
