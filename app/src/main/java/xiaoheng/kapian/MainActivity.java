package xiaoheng.kapian;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.net.*;
import android.os.*;
import android.provider.*;
import android.support.design.widget.*;
import android.support.v4.app.*;
import android.support.v4.view.*;
import android.support.v4.widget.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import cn.bmob.v3.*;
import cn.bmob.v3.listener.*;
import cn.bmob.v3.update.*;
import com.pgyersdk.crash.*;
import java.io.*;
import java.util.*;
import java.util.zip.*;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity 
{
	Intent intent,i,intent2,shareIntent;
	NotificationManager manager;
	private TabLayout mTabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> fragments;
    private String[] titles={"首页","代码"};
	private Toolbar toolbar;
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private NavigationView navigationView;
	/*private LinearLayout chehuaHENG;
	private RotateAnimation rotate;
	private LinearInterpolator lin;*/
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
			
		//初始化id
		Bmob.initialize(this,"4e1cb245cceeac4ff981af05ef549868");
		//自动建表
		BmobUpdateAgent.setDefault();
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
						//Toast.makeText(MainActivity.this, "版本无更新", Toast.LENGTH_SHORT).show();
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
						Toast.makeText(MainActivity.this, "发现新版本，但被你忽略了！", Toast.LENGTH_SHORT).show();
					}
					/*
					else if(updateStatus==UpdateStatus.TimeOut)
					{
						Toast.makeText(MainActivity.this, "查询出错或查询超时\n可能未连接网络", Toast.LENGTH_SHORT).show();
					}
					*/
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
			
			
			
			
			
		//(蒲公英)集成
		PgyCrashManager.register(this);
		//上报 catch 异常
		try {} 
		catch (Exception e)
		{
			PgyCrashManager.reportCaughtException(MainActivity.this, e);
		}
			
		
		//侧滑HENG
		//chehuaHENG=(LinearLayout)findViewById(R.id.chehuaLinearLayout1);
		mTabLayout = (TabLayout)findViewById(R.id.tab_layout);
        viewPager = (ViewPager)findViewById(R.id.mainViewPager1);
		navigationView=(NavigationView)findViewById(R.id.nv);
		//标题栏_侧滑联动动画
		toolbar = (Toolbar) findViewById(R.id.tl_custom);
		toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
		setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_left);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
		mDrawerToggle.syncState();
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		fragments = new ArrayList<Fragment>();
        fragments.add(new Fragment_1());
        fragments.add(new Fragment_2());
        viewPager.setAdapter(viewPagerAdapter);
        mTabLayout.setupWithViewPager(viewPager);
		//联动下标
        mTabLayout.setTabsFromPagerAdapter(viewPagerAdapter);
		
		//侧滑item点击事件
		navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
		{
				@Override
				public boolean onNavigationItemSelected(MenuItem p1)
				{
					switch (p1.getItemId()){
						case R.id.fankui:
							startActivity(new Intent(MainActivity.this,Bmobfankui.class));
							break;
						case R.id.fenxiang:
							AlertDialog.Builder qa=new AlertDialog.Builder(MainActivity.this);
							qa.setIcon(R.raw.xiaoheng8);//图标
							qa.setTitle("选择分享");//标题
							qa.setMessage("喜欢就分享出去，这是对我的一种鼓励。\n谢谢！");//弹窗内容
							qa.setPositiveButton("分享软件", new DialogInterface.OnClickListener(){
									@Override
									public void onClick(DialogInterface p1, int p2)
									{
										intent2=new Intent(Intent.ACTION_SEND);
										Uri uri=Uri.fromFile(new File("/data/app/xiaoheng.kapian-2/base.apk"));
										intent2.putExtra(Intent.EXTRA_STREAM,uri);
										intent2.setType("*/*");
										startActivity(Intent.createChooser(intent2,"分享-AIDE布局助手(软件)"));
									}
								});
							qa.setNegativeButton("分享链接",new DialogInterface.OnClickListener(){
									@Override
									public void onClick(DialogInterface p1, int p2)
									{
										shareIntent = new Intent(); shareIntent.setAction(Intent.ACTION_SEND);
										//"AIDE布局助手下载地址：http://pan.baidu.com/s/1skNCFzn"
										shareIntent.putExtra(Intent.EXTRA_TEXT,"AIDE布局助手链接还没弄好");
										shareIntent.setType("text/plain");
										startActivity(Intent.createChooser(shareIntent, "分享-AIDE布局助手(链接)"));
										
									}
								});
							qa.setNeutralButton("取消",null);
							qa.show();
							break;
						/*case R.id.lianxi:
							try
							{
								startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("mqqapi://card/show_pslcard?src_type=internal&source=sharecard&version=1&uin=1919196455")));
							}
							catch(Exception e)
							{
								Toast.makeText(getApplicationContext(),"转跳失败，未安装手Q或当前版本不支持",Toast.LENGTH_SHORT).show();
							}
							break;*/
						case R.id.gonggao:
							
							final AlertDialog.Builder ab=new AlertDialog.Builder(MainActivity.this);
							ab.setIcon(R.raw.xiaoheng2);//图标
							ab.setTitle("公告");//标题
							ab.setPositiveButton("知道了", new DialogInterface.OnClickListener()
								{
									@Override
									public void onClick(DialogInterface p1, int p2)
									{
										//弹窗知道了点击事件，暂时没用到
									}});
							BmobQuery<GongGao>gonggao=new BmobQuery<GongGao>();
							gonggao.findObjects(getApplication(), new FindListener<GongGao>(){
									@Override
									public void onSuccess(List<GongGao> p1)
									{
										for(GongGao xiaoheng:p1)
										{
											//设置公告内容
											String aa=xiaoheng.getgonggao();
											ab.setMessage(aa);//弹窗内容
											ab.show();
										}
									}
									@Override
									public void onError(int p1, String p2)
									{
										//获取后台数据失败事件
										ab.setMessage("获取公告失败，可能是网络的问题请检查网络是否连接。");//弹窗内容
										ab.setPositiveButton("打开Wifi设置", new DialogInterface.OnClickListener(){
												@Override
												public void onClick(DialogInterface p1, int p2)
												{
													//第一个按钮按钮的点击事件
													intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
													startActivity(intent);
												}
											});
										ab.setNegativeButton("打开数据设置",new DialogInterface.OnClickListener(){
												@Override
												public void onClick(DialogInterface p1, int p2)
												{
													//第二个按钮的点击事件
													intent = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
													startActivity(intent);
												}
											});
										ab.setNeutralButton("取消", new DialogInterface.OnClickListener(){
												@Override
												public void onClick(DialogInterface p1, int p2)
												{
													//第三个按钮的点击事件
												}});
										ab.show();
									}});
							break;
						case R.id.guanyu:
							//转跳到软件关于
							startActivity(new Intent(MainActivity.this,Guanyu.class));
							//关闭侧滑
							//mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
							break;
						case R.id.juanzheng:
							ViewGroup layout = (ViewGroup) MainActivity.this.getLayoutInflater().inflate(R.layout.juanzheng_zhifubao_weixin_qq,null);
							AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
							dialog.setView(layout);
							dialog.setPositiveButton("支付宝捐赠", new DialogInterface.OnClickListener(){
									@Override
									public void onClick(DialogInterface p1, int p2)
									{
										//支付宝捐赠
										try
										{
											startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("alipays://platformapi/startapp?saId=10000007&clientVersion=3.7.0.0718&qrcode=https%3A%2F%2Fqr.alipay.com%2FFKX00776US7D6RTHLZE76B%3F_s%3Dweb-other&_t=1486300416691#Intent;scheme=alipays;package=com.eg.android.AlipayGphone;end")));
											Toast.makeText(MainActivity.this,"正在打开支付宝",Toast.LENGTH_LONG).show();
										}
										catch(Exception e)
										{
											AlertDialog.Builder dg=new AlertDialog.Builder(MainActivity.this);
											dg.setTitle("提示");
											dg.setMessage("亲，您的手机还没有安装支付宝呢！");
											dg.setPositiveButton("去下载", new DialogInterface.OnClickListener(){
													@Override
													public void onClick(DialogInterface p1, int p2)
													{
														//去下载按钮按钮的点击事件
														startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://ds.alipay.com/")));
													}
												});
											dg.setNegativeButton("取消",null);
											dg.show();
										}
									}
								});
								
							dialog.setNegativeButton("保存二维码",new DialogInterface.OnClickListener(){
									@Override
									public void onClick(DialogInterface p1, int p2)
									{
										
										//assets中的zip通过解压的方式保存到本地
										try
										{
											unZip(MainActivity.this, "juanzheng.zip", "/storage/emulated/0/AIDE布局助手/图片/", true);
											Toast.makeText(MainActivity.this,"保存成功\n路径：/storage/emulated/0/AIDE布局助手/图片/juanzheng_erweima.png",Toast.LENGTH_SHORT).show();
										}
										catch (IOException e)
										{
											Toast.makeText(MainActivity.this,"保存失败",Toast.LENGTH_SHORT).show();
										}
										
										/*
										//assets中的图片直接保存到本地(保存到本地的图片貌似损坏了)
										try{
											InputStream in=getAssets().open("juanzheng_erweima.png");
											byte[] buff=new byte[in.available()];
											//写出
											FileOutputStream ou=new FileOutputStream("/storage/emulated/0/AIDE布局助手/图片/juanzhenma.png");
											ou.write(buff);
											ou.flush();
											ou.close();
											Toast.makeText(MainActivity.this,"保存成功\n路径：/storage/emulated/0/AIDE布局助手/图片/juanzheng_erweima.png",Toast.LENGTH_SHORT).show();
										}catch(Exception e){
											Toast.makeText(MainActivity.this,"保存失败",Toast.LENGTH_SHORT).show();
										}*/
										
										/*
										//raw中的图片直接保存到本地(保存到本地的图片貌似损坏了)
										try{
											InputStream in=getResources().openRawResource(R.raw.juanzheng_erweima);
											byte[] buff=new byte[in.available()];
											//写出
											FileOutputStream ou=new FileOutputStream("/storage/emulated/0/AIDE布局助手/图片/juanzhenma.png");
											ou.write(buff);
											ou.flush();
											ou.close();
											Toast.makeText(MainActivity.this,"保存成功\n路径：/storage/emulated/0/AIDE布局助手/图片/juanzheng_erweima.png",Toast.LENGTH_SHORT).show();
										}catch(Exception e){
											Toast.makeText(MainActivity.this,"保存失败",Toast.LENGTH_SHORT).show();
										}*/
										
									}
								});
							dialog.show();
							break;
						case R.id.tuicu:
							//选择退出
							
							AlertDialog.Builder dg=new AlertDialog.Builder(MainActivity.this);
							dg.setIcon(R.raw.xiaoheng);//图标
							dg.setTitle("提示");
							dg.setMessage("请选择你的退出方式。");
							dg.setPositiveButton("完全杀死", new DialogInterface.OnClickListener(){
									@Override
									public void onClick(DialogInterface p1, int p2)
									{
										finish();
										//关闭java虚拟机
										System.exit(0);
									}
								});
								
							dg.setNegativeButton("退出",new DialogInterface.OnClickListener(){
									@Override
									public void onClick(DialogInterface p1, int p2)
									{
										finish();
									}
									});
								
								/*
							dg.setNegativeButton("后台运行",new DialogInterface.OnClickListener(){
									@Override
									public void onClick(DialogInterface p1, int p2)
									{
										//实现Home键效果 
										//super.onBackPressed();这句话一定要注掉,不然又去调用默认的back处理方式了 
										i= new Intent(Intent.ACTION_MAIN); 
										i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
										i.addCategory(Intent.CATEGORY_HOME); 
										startActivity(i);*/
										
										/*
										//效果1
										//发送一条通知栏消息
										NotificationManager manager=(NotificationManager)MainActivity.this.getSystemService(Context.NOTIFICATION_SERVICE);
										Notification.Builder builder=new Notification.Builder(MainActivity.this);
										//设置图标
										builder.setSmallIcon(R.drawable.icon);
										builder.setContentTitle("AIDE布局助手");
										builder.setContentText("正在后台运行点击可进入");
										//显示预览的滚动信息
										builder.setTicker("AIDE布局助手已转到后台运行");
										Notification notification=builder.build();
										//发送通知，第一个参数设置通知ID
										manager.notify(1,notification);*/
										/*
										//效果2
										//设置获取哪个服务
										manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
										Notification.Builder builder = new Notification.Builder(MainActivity.this);
										//设置图标
										builder.setSmallIcon(R.drawable.ic_launcher);
										//设置标题
										builder.setContentTitle("AIDE布局助手");
										//消息内容
										builder.setContentText("正在后台运行点击可进入");
										//显示预览的滚动信息
										builder.setTicker("AIDE布局助手已转到后台运行");
										//发送时间
										builder.setWhen(System.currentTimeMillis());
										//设置默认的提示音，振动方式，灯光
										builder.setDefaults(Notification.DEFAULT_ALL);
										//打开程序后图标消失
										builder.setAutoCancel(true);
										//设置为不可清除模式
										builder.setOngoing(true);
										//跳转活动
										//通过包名获取要跳转的app
										intent = getPackageManager().getLaunchIntentForPackage("xiaoheng.kapian");
										 //这里如果intent为空，就说名没有安装要跳转的应用嘛
										 if (intent != null)
										 {
											PendingIntent pi = PendingIntent.getActivities(MainActivity.this, 0, new Intent[]{intent}, PendingIntent.FLAG_CANCEL_CURRENT);
											builder.setContentIntent(pi);
										 }
										 else
										 {
										 // 没有安装要跳转的app应用，提醒一下
										 Toast.makeText(getApplicationContext(),"哟，赶紧下载安装这个APP吧", Toast.LENGTH_LONG).show();
										 }
										
										//创建通知栏对象，显示通知信息
										Notification notification = builder.build();
										manager.notify(1, notification);		
									}
								});*/
							dg.setNeutralButton("取消",null);
							dg.show();
							
							break;
							}
					return true;
				}
		});
    }
	
	//滑动换页
	FragmentStatePagerAdapter viewPagerAdapter=new FragmentStatePagerAdapter(getSupportFragmentManager())
	{
        @Override
        public int getCount()
		{
            return fragments.size();
        }
        public CharSequence getPageTitle(int position)
		{
            return titles[position];
        }
        @Override
        public Fragment getItem(int arg0)
		{
            return fragments.get(arg0);
        }
	};
	
	
	/**
	 * 解压assets的zip压缩文件到指定目录
	 * @param context上下文对象
	 * @param assetName压缩文件名
	 * @param outputDirectory输出目录
	 * @param isReWrite是否覆盖
	 * @throws IOException
	 */
	public void unZip(Context context, String assetName, String outputDirectory, boolean isReWrite) throws IOException {
		// 创建解压目标目录
		File file = new File(outputDirectory);
		// 如果目标目录不存在，则创建
		if (!file.exists())
		{
			file.mkdirs();
		}
		// 打开压缩文件
		InputStream inputStream = context.getAssets().open(assetName);
		ZipInputStream zipInputStream = new ZipInputStream(inputStream);
		// 读取一个进入点
		ZipEntry zipEntry = zipInputStream.getNextEntry();
		// 使用1Mbuffer
		byte[] buffer = new byte[1024 * 1024];
		// 解压时字节计数
		int count = 0;
		// 如果进入点为空说明已经遍历完所有压缩包中文件和目录
		while (zipEntry != null)
		{
			// 如果是一个目录
			if (zipEntry.isDirectory()) {
				file = new File(outputDirectory + File.separator + zipEntry.getName());
				// 文件需要覆盖或者是文件不存在
				if (isReWrite || !file.exists()) {
					file.mkdir();
				}
			} else {
				// 如果是文件
				file = new File(outputDirectory + File.separator + zipEntry.getName());
				// 文件需要覆盖或者文件不存在，则解压文件
				if (isReWrite || !file.exists()) {
					file.createNewFile();
					FileOutputStream fileOutputStream = new FileOutputStream(file);
					while ((count = zipInputStream.read(buffer)) > 0) {
						fileOutputStream.write(buffer, 0, count);
					}
					fileOutputStream.close();
				}
			}
			// 定位到下一个文件入口
			zipEntry = zipInputStream.getNextEntry();
		}
		zipInputStream.close();
	}
	
	
	/*
	//侧滑HENG点击事件
	public void chehua(View v)
	{
		//旋转
		rotate  = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		lin = new LinearInterpolator();  
		rotate.setInterpolator(lin);
		//设置动画持续周期
		rotate.setDuration(1800);
		//设置重复次数-1为循环
		rotate.setRepeatCount(0);
		//动画执行完后是否停留在执行完的状态
		//rotate.setFillAfter(false);
		//执行前的等待时间
		//rotate.setStartOffset(0);
		//设置旋转动画
		chehuaHENG.setAnimation(rotate);
	}*/
	
	/*
	返回键实现后台运行
	@Override
    public void onBackPressed() { 
        //实现Home键效果 
        //super.onBackPressed();这句话一定要注掉,不然又去调用默认的back处理方式了 
        Intent i= new Intent(Intent.ACTION_MAIN); 
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
        i.addCategory(Intent.CATEGORY_HOME); 
        startActivity(i);  
    }*/
	
	//再按一次退出
	private long exitTime=0;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK&&event.getAction()==KeyEvent.ACTION_DOWN)
		{
			if ((System.currentTimeMillis()-exitTime)>800)
			{
				Toast.makeText(MainActivity.this,"再一次退出",Toast.LENGTH_LONG).show();
				exitTime = System.currentTimeMillis();
			}
			else
			{
				finish();
				//关闭java虚拟机

				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
}
