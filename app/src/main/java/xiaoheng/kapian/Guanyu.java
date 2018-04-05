package xiaoheng.kapian;

import android.content.*;
import android.graphics.*;
import android.net.*;
import android.os.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import cn.bmob.v3.*;
import cn.bmob.v3.listener.*;
import cn.bmob.v3.update.*;
import xiaoheng.kapian.*;

import android.support.v7.widget.Toolbar;

public class Guanyu extends AppCompatActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guanyu);
		
		Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
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

		CardView cardView1=(CardView)findViewById(R.id.mainCardView1);
		//设置图片圆角的半径大小
		cardView1.setRadius(2);
		//设置阴影部分大小
		cardView1.setCardElevation(1);
		//设置图片距离阴影大小
		//cardView1.setUseCompatPadding(true);
		
		//AIDE卡片长按事件
		cardView1.setOnLongClickListener(new OnLongClickListener()
		{
				@Override
				public boolean onLongClick(View p1)
				{
					Toast.makeText(Guanyu.this,"欢迎使用AIDE布局助手",Toast.LENGTH_SHORT).show();
					return true;
				}
			});

		TextView text1=(TextView)findViewById(R.id.text1);
		TextView text2=(TextView)findViewById(R.id.text2);
		//将远程管理加下划线效果
		text1.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		text1.getPaint().setAntiAlias(true);//抗锯齿
		//将源码开源加下划线效果
		text2.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		text2.getPaint().setAntiAlias(true);//抗锯齿
		
		//AIDE字体
		TextView text=(TextView)findViewById(R.id.guanyuTextView1);
		Typeface heng=Typeface.createFromAsset (getAssets(),"heng.ttf");
		text.setTypeface(heng);
		
		//版本字体
		TextView banben=(TextView)findViewById(R.id.guanyuTextView3);
		Typeface hengq=Typeface.createFromAsset (getAssets(),"aa.ttf");
		banben.setTypeface(hengq);
		
		//xiaoheng字体
		TextView text3=(TextView)findViewById(R.id.guanyuTextView2);
		Typeface heng1=Typeface.createFromAsset (getAssets(),"xiaoheng.ttf");
		text3.setTypeface(heng1);
		
	}
	
	//作者
	public void zhuozhe(View v)
	{
		Toast.makeText(Guanyu.this,"我是作者小亨 ᕙ(`▿´)ᕗ",Toast.LENGTH_SHORT).show();
	}
	
	//联系作者
	public void lianxizhuozhe(View v)
	{
		try
		{
			startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("mqqapi://card/show_pslcard?src_type=internal&source=sharecard&version=1&uin=1919196455")));
		}
		catch(Exception e)
		{
			Toast.makeText(getApplicationContext(),"转跳失败，未安装手Q或当前版本不支持",Toast.LENGTH_SHORT).show();
		}
	}
	
	//加入群聊
	public void jiaqun(View v)
	{
		try
		{
			startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("mqqapi://card/show_pslcard?src_type=internal&version=1&uin=234257176&card_type=group&source=qrcode")));
		}
		catch(Exception e)
		{
			Toast.makeText(getApplicationContext(),"转跳失败，未安装手Q或当前版本不支持",Toast.LENGTH_SHORT).show();
		}
	}
	
	//软件更新
	public void jianchegengxin(View v)
	{
		//初始化id
		Bmob.initialize(this,"4e1cb245cceeac4ff981af05ef549868");
		//自动建表
		//BmobUpdateAgent.initAppVersion(this);
		//任何网络都更新
		BmobUpdateAgent.setUpdateOnlyWifi(false);
		//发起自动更新
		BmobUpdateAgent.update(this);

		BmobUpdateAgent.setUpdateListener(new BmobUpdateListener()
			{
				@Override
				public void onUpdateReturned(int updateStatus, UpdateResponse updateInfo)
				{
					if (updateStatus == UpdateStatus.Yes)
					{
						//版本有更新
					}
					else if(updateStatus == UpdateStatus.No)
					{
						Toast.makeText(Guanyu.this,"暂无发现新版本！", Toast.LENGTH_SHORT).show();
					}
					/*
					 else if(updateStatus==UpdateStatus.EmptyField)
					 {
					 //此提示只是提醒开发者关注那些必填项，测试成功后，无需对用户提示
					 Toast.makeText(MainActivity.this, "请检查你AppVersion表的必填项，1、target_size（文件大小）是否填写；2、path或者android_url两者必填其中一项。", Toast.LENGTH_SHORT).show();
					 }
					 else if(updateStatus==UpdateStatus.ErrorSizeFormat)
					 {
					 //用于测试
					 Toast.makeText(MainActivity.this, "请检查target_size填写的格式，请使用file.length()方法获取apk大小。", Toast.LENGTH_SHORT).show();
					 }
					 */
					else if(updateStatus==UpdateStatus.IGNORED)
					{
						Toast.makeText(Guanyu.this, "发现新版本，但被你忽略了！", Toast.LENGTH_SHORT).show();
					}
					/*
					 else if(updateStatus==UpdateStatus.TimeOut)
					 {
					 Toast.makeText(MainActivity.this, "查询出错或查询超时\n可能未连接网络", Toast.LENGTH_SHORT).show();
					 }
					 */
					 else
					 {
						 Toast.makeText(Guanyu.this, "你的网络可能有点问题！", Toast.LENGTH_SHORT).show();
					 }
				}
			});
		/*
		 //设置对对话框按钮的点击事件的监听
		 BmobUpdateAgent.setDialogListener(new BmobDialogButtonListener()
		 {
		 @Override
		 public void onClick(int status)
		 {
		 switch (status)
		 {
		 case UpdateStatus.Update:
		 Toast.makeText(MainActivity.this, "点击了更新按钮" , Toast.LENGTH_SHORT).show();
		 finish();
		 break;
		 case UpdateStatus.NotNow:
		 Toast.makeText(MainActivity.this, "点击了取消按钮" , Toast.LENGTH_SHORT).show();
		 break;
		 }
		 }
		 });*/
	}
	
	//软件介绍
	public void jiesao(View v)
	{
		Toast.makeText(Guanyu.this, "呃………………还没写好。", Toast.LENGTH_SHORT).show();
	}
	
	//远程管理
	public void guanli(View v)
	{
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://bmob.cn/")));
	}
	
	//源码开源
	public void kaiyuan(View v)
	{
		/*
		AlertDialog.Builder ab=new AlertDialog.Builder(Guanyu.this);
		//ab.setTitle("说明");//标题
		ab.setMessage("部分功能和布局已开源，我发在Q群了需要的进群文件下载。\n如果需要整个软件的源码请联系原创作者。\n\nQQ：1919196455\n微信：heng1919196455");
		ab.show();*/
	}
	
}
