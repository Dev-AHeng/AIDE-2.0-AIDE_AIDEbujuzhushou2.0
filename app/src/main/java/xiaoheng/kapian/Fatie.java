package xiaoheng.kapian;

import android.graphics.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import cn.bmob.v3.listener.*;

import android.support.v7.widget.Toolbar;
import android.view.View.*;

public class Fatie extends AppCompatActivity
{
	private Toolbar toolbar;
	private EditText edittext,edittext1;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fatie);

		toolbar = (Toolbar) findViewById(R.id.fatie_toolbar);
		//设置toolbar颜色
		toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
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

		edittext=(EditText)findViewById(R.id.fatieEditText1);
		edittext1=(EditText)findViewById(R.id.fatieEditText2);

		FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fatieFloatingActionButton1);

		fab.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					//添加数据
					GongGao xiaoheng=new GongGao();
					xiaoheng.setfenxiang_biaoti(edittext.getText().toString());
					xiaoheng.setfenxiang_neirong(edittext1.getText().toString());
					xiaoheng.save(Fatie.this, new SaveListener()
						{
							@Override
							public void onSuccess()
							{
								//添加数据成功
								Toast.makeText(Fatie.this,"发帖成功",Toast.LENGTH_SHORT).show();
								edittext.setText("");
								edittext1.setText("");
							}
							@Override
							public void onFailure(int p1, String p2)
							{
								//添加数据失败
								Toast.makeText(Fatie.this,"发帖失败",Toast.LENGTH_SHORT).show();
							}
						});

				}
			});
	}

}
