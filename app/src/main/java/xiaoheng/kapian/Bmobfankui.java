package xiaoheng.kapian;

import android.content.*;
import android.graphics.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.telephony.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import cn.bmob.v3.*;
import java.util.regex.*;

import android.support.v7.widget.Toolbar;
import cn.bmob.v3.listener.*;

public class Bmobfankui extends AppCompatActivity
{
	private boolean isShowOrNot = false;
	private ImageButton imagebutton;
	private TextView textView;
	private TextInputLayout editlayout1,editlayout2,editlayout3,editlayout4,editlayout5;
	private EditText edittext_biaoti,edittext_miaoshu,edittext_weixin,edittext_qq,edittext_youxiang;
	private String huoquxinxi,soujixinxi;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bmob_fankui);
		
		Bmob.initialize(this,"4e1cb245cceeac4ff981af05ef549868");
		
		Toolbar toolbar = (Toolbar) findViewById(R.id.bmobfankuiToolbar1);
		//设置toolbar颜色
		toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
		//添加toolbar
		setSupportActionBar(toolbar);
		//toolbar添加返回按钮
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		//返回按钮点击事件
		toolbar.setNavigationOnClickListener(new View.OnClickListener()
		{
				@Override
				public void onClick(View view)
				{
					//退出
					finish();
				}
			});
			
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
		/*phoneInfo.append("\n本机手机号码：" + tm.getLine1Number()); 
		phoneInfo.append("\n所在地：" + tm.getNetworkCountryIso()); 
		phoneInfo.append("\n手机卡类型：" + tm.getNetworkOperatorName()); 
		phoneInfo.append("\n手机号码所在地：" + tm.getSimCountryIso());*/
		phoneInfo.append("\n手机卡状态：" + tm.getSimState());
		phoneInfo.append("\nIMSI码：" + tm.getSubscriberId()); 
		phoneInfo.append("\nProduct: " + android.os.Build.PRODUCT);
		phoneInfo.append( "\nVERSION_CODES.BASE: " + android.os.Build.VERSION_CODES.BASE);
		phoneInfo.append( "\nDEVICE:" + android.os.Build.DEVICE); 
		phoneInfo.append( "\nDISPLAY: " + android.os.Build.DISPLAY);
		phoneInfo.append( "\nMANUFACTURER: " + android.os.Build.MANUFACTURER); 
		phoneInfo.append( "\nUSER: " + android.os.Build.USER); 
		phoneInfo.append("\nDeviceSoftwareVersion : " + tm.getDeviceSoftwareVersion());
		phoneInfo.append("\nNetworkOperator : " + tm.getNetworkOperator()); 
		phoneInfo.append("\nNetworkType : " + tm.getNetworkType()); 
		phoneInfo.append("\nPhoneType : " + tm.getPhoneType()); 
		phoneInfo.append("\nSimOperator : " + tm.getSimOperator()); 
		phoneInfo.append("\nSimOperatorName : " + tm.getSimOperatorName()); 
		phoneInfo.append("\nSimSerialNumber : " + tm.getSimSerialNumber());
		phoneInfo.append("\nVoiceMailNumber : " + tm.getVoiceMailNumber()); 
		huoquxinxi=phoneInfo.toString().toLowerCase();
		soujixinxi="软件名称:AIDE布局助手\n"+"版本:1.0\n"+huoquxinxi;
		
		imagebutton=(ImageButton)findViewById(R.id.bmobfankuiImageButton1);
		textView=(TextView)findViewById(R.id.bmobfankuiTextView1);
		textView.setText(soujixinxi);
		
		edittext_biaoti=(EditText)findViewById(R.id.bmobfankuiEditText1);
		edittext_miaoshu=(EditText)findViewById(R.id.bmobfankuiEditText2);
		edittext_weixin=(EditText)findViewById(R.id.bmobfankuiEditText3);
		edittext_qq=(EditText)findViewById(R.id.bmobfankuiEditText4);
		//edittext_youxiang=(EditText)findViewById(R.id.bmobfankuiEditText5);
		
		
		editlayout1=(TextInputLayout)findViewById(R.id.bmobfankuiTextInputLayout1);
		editlayout2=(TextInputLayout)findViewById(R.id.bmobfankuiTextInputLayout2);
		
		editlayout3=(TextInputLayout)findViewById(R.id.bmobfankuiTextInputLayout3);
		editlayout4=(TextInputLayout)findViewById(R.id.bmobfankuiTextInputLayout4);
		//editlayout5=(TextInputLayout)findViewById(R.id.bmobfankuiTextInputLayout5);
		
		
		imagebutton.setOnClickListener(new OnClickListener()
		{
				@Override
				public void onClick(View p1)
				{
					if (isShowOrNot == false)
					{  
						// 设置显示
						textView.setVisibility(0);
						imagebutton.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.shang));
						isShowOrNot = true;  
					}
					else
					{  
						// 设置隐藏
						textView.setVisibility(8);
						imagebutton.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.xia));
						isShowOrNot  = false;  
					}
				}
			});
			
		FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.bmobfankuiFloatingActionButton1);
		fab.setOnClickListener(new OnClickListener()
		{
				@Override
				public void onClick(View p1)
				{
					
					//检验微信号正则表达式
					Pattern pat = Pattern.compile("^[a-zA-Z]{1}[-_a-zA-Z0-9]{5,19}+$");
					Matcher mat = pat.matcher(edittext_weixin.getText().toString());
					
					//检验QQ号
					Pattern pat1 = Pattern.compile("[1-9][0-9]{4,14}");
					Matcher mat1 = pat1.matcher(edittext_qq.getText().toString());
					
					/*
					//检验邮箱正则表达式
					Pattern pat2 = Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
					Matcher mat2 = pat2.matcher(edittext_youxiang.getText().toString());*/
					
					if(edittext_biaoti.getText().toString().equals(""))
					{
						editlayout1.setError("亲，你忘写了！");
						editlayout2.setError(null);
						editlayout3.setError(null);
						editlayout4.setError(null);
						//editlayout5.setError(null);
					}
					else if(edittext_biaoti.getText().toString().length()>20)
					{
						editlayout1.setError("亲，你写得有点多了！");
						editlayout2.setError(null);
						editlayout3.setError(null);
						editlayout4.setError(null);
					}
					else if(edittext_miaoshu.getText().toString().equals(""))
					{
						editlayout1.setError(null);
						editlayout2.setError("亲，你忘写了！");
						editlayout3.setError(null);
						editlayout4.setError(null);
						//editlayout5.setError(null);
					}
					else if(edittext_miaoshu.getText().toString().length()>200)
					{
						editlayout1.setError(null);
						editlayout2.setError("亲，你写得有点多了！");
						editlayout3.setError(null);
						editlayout4.setError(null);
						//editlayout5.setError(null);
					}
					else if(edittext_qq.getText().toString().equals(""))
					{
						editlayout1.setError(null);
						editlayout2.setError(null);
						editlayout3.setError(null);
						editlayout4.setError("亲，留个QQ号吧！");
					}
					else if(edittext_weixin.getText().toString().equals(""))
					{
						editlayout1.setError(null);
						editlayout2.setError(null);
						editlayout3.setError("亲，留个微信号吧！");
						editlayout4.setError(null);
					}
					else
					{
						editlayout1.setError(null);
						editlayout2.setError(null);
						
						if(edittext_biaoti.getText().toString().length()<5)
						{
							editlayout1.setError("亲，不少于5个字哦！");
							editlayout2.setError(null);
							editlayout3.setError(null);
							editlayout4.setError(null);
						}
						else if(edittext_miaoshu.getText().toString().length()<15)
						{
							editlayout1.setError(null);
							editlayout2.setError("亲，不少于15个字哦！");
							editlayout3.setError(null);
							editlayout4.setError(null);
							//editlayout5.setError(null);
						}
						else if(edittext_qq.getText().toString().length()>11)
						{
							editlayout3.setError(null);
							editlayout4.setError("别逗了最多11位数的");
						}
						else if(!mat1.matches())
						{
							editlayout3.setError(null);
							editlayout4.setError("这个QQ号貌似不正确哦！");
						}
						else if(!mat.matches())
						{
							editlayout4.setError(null);
							editlayout3.setError("这个微信号貌似不正确哦！");
						}
						else
						{
							editlayout1.setError(null);
							editlayout2.setError(null);
							editlayout3.setError(null);
							editlayout4.setError(null);
							
							FanKui fankui = new FanKui();
							fankui.setbiaoti(edittext_biaoti.getText().toString());
							fankui.setmiaoshu(edittext_miaoshu.getText().toString());
							fankui.setsoujixinxi(soujixinxi);
							fankui.setweixin(edittext_weixin.getText().toString());
							fankui.setqq(edittext_qq.getText().toString());
							//fankui.setyouxiang(edittext_youxiang.getText().toString());
							fankui.save(Bmobfankui.this, new SaveListener()
							{
									@Override
									public void onSuccess()
									{
										// TODO: Implement this method
										Toast.makeText(getApplicationContext(),"成功",Toast.LENGTH_SHORT).show();
									}

									@Override
									public void onFailure(int p1, String p2)
									{
										// TODO: Implement this method
										Toast.makeText(getApplicationContext(),"失败",Toast.LENGTH_SHORT).show();
									}
								});
							
						}
						
					}
					
				}
			});
	}
}
