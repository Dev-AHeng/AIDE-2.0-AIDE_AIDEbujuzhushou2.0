package xiaoheng.kapian;

import android.graphics.*;
import android.os.*;
import android.support.v7.app.*;
import android.view.*;
import android.view.animation.*;
import android.widget.*;
import android.widget.AbsListView.*;
import android.widget.AdapterView.*;
import java.util.*;

import android.support.v7.widget.Toolbar;
import android.widget.AdapterView.OnItemClickListener;
import android.content.*;


public class List_TextView extends AppCompatActivity
{

		private int aaaa,bbbb,cccc,eeee,hhhh;
		private ListView listview;
		private Animation list_donghua;
		private Intent intent;
		ArrayList <HashMap<String,Object>>list=new ArrayList<HashMap<String,Object>>();

		//编辑框
		String b[]=
		{
				"android:adjustViewBounds",
				"android:adjustViewBounds",
				"android:maxWidth",
				"android:src",
				"android:tint",
				"android:scaleType（matrix）",
				"android:scaleType(fitXY)",
				"android:scaleType(fitStart)",
				"android:scaleType(fitEnd)",
				"android:scaleType(center)",
				"android:scaleType(centerCrop)",
				"android:scaleType(fitCenter)",
				"小亨",
				"小亨",
				"小亨",
				"小亨",
				"小亨",
				"小亨",
				"小亨",
				"用于设置ImageView是否调整自己的边界来保持所显示图片的长宽比。",
				"设置ImageView的最大高度，需要设置android:adjustViewBounds属性值为true，否则不起作用。",
				"设置ImageView的最大宽度，需要设置android:adjustViewBounds属性值为true，否则不起作用。",
				"用于设置ImageView所显示的Drawable对象的ID。例如，设置显示保存在res/drawable目录下的名称为flower.jpg的图片，可以将属性值设置为android:src=\"@drawable/flower\"。",
				"用于为图片着色，其属性值可以是\"#rgb\"、\"#argb\"、\"#rrggbb\"或\"#aarrggbb\"表示的颜色值。",
				"用于设置所显示的图片如何缩放或移动以适应ImageView的大小。matrix（使用matrix方式进行缩放）。",
				"用于设置所显示的图片如何缩放或移动以适应ImageView的大小。fitXY（对图片横向、纵向独立缩放，使得该图片完全适应于该ImageView，图片的纵横比可能会改变）。",
				"用于设置所显示的图片如何缩放或移动以适应ImageView的大小。\nfitStart（保持纵横比缩放图片，直到该图片能完全显示在ImageView中，缩放完成后该图片放在ImageView的左上角）。",
				"用于设置所显示的图片如何缩放或移动以适应ImageView的大小。\nfitEnd（保持纵横比缩放图片，直到该图片能完全显示在ImageView中，缩放完成后该图片放在ImageView的右下角）。",
				"用于设置所显示的图片如何缩放或移动以适应ImageView的大小。\ncenter（把图像放在ImageView的中间，但不进行任何缩放）。",
				"用于设置所显示的图片如何缩放或移动以适应ImageView的大小。\ncenterCrop（保持纵横比缩放图片，以使得图片能完全覆盖ImageView）\ncenterInside（保持纵横比缩放图片，以使得ImageView能完全显示该图片）。",
				"用于设置所显示的图片如何缩放或移动以适应ImageView的大小。\nfitCenter（保持纵横比缩放图片，直到该图片能完全显示在ImageView中，缩放完成后该图片放在ImageView的中央）。",
				"小亨",
				"小亨",
				"小亨",
				"小亨",
				"小亨",
				"小亨",
				"小亨"
		};

		String c[]=
		{
				"用于设置ImageView是否调整自己的边界来保持所显示图片的长宽比。",
				"设置ImageView的最大高度，需要设置android:adjustViewBounds属性值为true，否则不起作用。",
				"设置ImageView的最大宽度，需要设置android:adjustViewBounds属性值为true，否则不起作用。",
				"用于设置ImageView所显示的Drawable对象的ID。例如，设置显示保存在res/drawable目录下的名称为flower.jpg的图片，可以将属性值设置为android:src=\"@drawable/flower\"。",
				"用于为图片着色，其属性值可以是\"#rgb\"、\"#argb\"、\"#rrggbb\"或\"#aarrggbb\"表示的颜色值。",
				"用于设置所显示的图片如何缩放或移动以适应ImageView的大小。matrix（使用matrix方式进行缩放）。",
				"用于设置所显示的图片如何缩放或移动以适应ImageView的大小。fitXY（对图片横向、纵向独立缩放，使得该图片完全适应于该ImageView，图片的纵横比可能会改变）。",
				"用于设置所显示的图片如何缩放或移动以适应ImageView的大小。\nfitStart（保持纵横比缩放图片，直到该图片能完全显示在ImageView中，缩放完成后该图片放在ImageView的左上角）。",
				"用于设置所显示的图片如何缩放或移动以适应ImageView的大小。\nfitEnd（保持纵横比缩放图片，直到该图片能完全显示在ImageView中，缩放完成后该图片放在ImageView的右下角）。",
				"用于设置所显示的图片如何缩放或移动以适应ImageView的大小。\ncenter（把图像放在ImageView的中间，但不进行任何缩放）。",
				"用于设置所显示的图片如何缩放或移动以适应ImageView的大小。\ncenterCrop（保持纵横比缩放图片，以使得图片能完全覆盖ImageView）\ncenterInside（保持纵横比缩放图片，以使得ImageView能完全显示该图片）。",
				"用于设置所显示的图片如何缩放或移动以适应ImageView的大小。\nfitCenter（保持纵横比缩放图片，直到该图片能完全显示在ImageView中，缩放完成后该图片放在ImageView的中央）。",
				"小亨",
				"小亨",
				"小亨",
				"小亨",
				"小亨",
				"小亨",
				"小亨",
				"用于设置ImageView是否调整自己的边界来保持所显示图片的长宽比。",
				"设置ImageView的最大高度，需要设置android:adjustViewBounds属性值为true，否则不起作用。",
				"设置ImageView的最大宽度，需要设置android:adjustViewBounds属性值为true，否则不起作用。",
				"用于设置ImageView所显示的Drawable对象的ID。例如，设置显示保存在res/drawable目录下的名称为flower.jpg的图片，可以将属性值设置为android:src=\"@drawable/flower\"。",
				"用于为图片着色，其属性值可以是\"#rgb\"、\"#argb\"、\"#rrggbb\"或\"#aarrggbb\"表示的颜色值。",
				"用于设置所显示的图片如何缩放或移动以适应ImageView的大小。matrix（使用matrix方式进行缩放）。",
				"用于设置所显示的图片如何缩放或移动以适应ImageView的大小。fitXY（对图片横向、纵向独立缩放，使得该图片完全适应于该ImageView，图片的纵横比可能会改变）。",
				"用于设置所显示的图片如何缩放或移动以适应ImageView的大小。\nfitStart（保持纵横比缩放图片，直到该图片能完全显示在ImageView中，缩放完成后该图片放在ImageView的左上角）。",
				"用于设置所显示的图片如何缩放或移动以适应ImageView的大小。\nfitEnd（保持纵横比缩放图片，直到该图片能完全显示在ImageView中，缩放完成后该图片放在ImageView的右下角）。",
				"用于设置所显示的图片如何缩放或移动以适应ImageView的大小。\ncenter（把图像放在ImageView的中间，但不进行任何缩放）。",
				"用于设置所显示的图片如何缩放或移动以适应ImageView的大小。\ncenterCrop（保持纵横比缩放图片，以使得图片能完全覆盖ImageView）\ncenterInside（保持纵横比缩放图片，以使得ImageView能完全显示该图片）。",
				"用于设置所显示的图片如何缩放或移动以适应ImageView的大小。\nfitCenter（保持纵横比缩放图片，直到该图片能完全显示在ImageView中，缩放完成后该图片放在ImageView的中央）。",
				"小亨",
				"小亨",
				"小亨",
				"小亨",
				"小亨",
				"小亨",
				"小亨"
		};

		@Override
		protected void onCreate(Bundle savedInstanceState)
		{
				// TODO: Implement this method
				super.onCreate(savedInstanceState);
				setContentView(R.layout.list_textview);

				Toolbar toolbar = (Toolbar) findViewById(R.id.listtextviewToolbar1);
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


				for (int i=0;i < b.length;i++)
				{
						HashMap<String,Object>map =new HashMap<String,Object>();
						map.put("biaoti", b[i]);
						map.put("jiequ", c[i].substring(0, 1));
						map.put("neirong", c[i]);
						list.add(map);
				}


				listview = (ListView)findViewById(R.id.listtextviewListView1);

				listview.setAdapter(new SimpleAdapter(this, list, R.layout.list_kongjian_yangshi, new String[]{"jiequ","biaoti","neirong"}, new int[]{R.id.xiaohenglistTextView1,R.id.xiaohenglistTextView2,R.id.xiaohenglistTextView3}));
				
				//列表载入时动画
				list_donghua = AnimationUtils.loadAnimation(List_TextView.this, R.anim.list_left_in);
				listview.startAnimation(list_donghua);
				
				//列表点击事件
				listview.setOnItemClickListener(new OnItemClickListener()
						{
								@Override
								public void onItemClick(AdapterView<?> parent, View view, int position, long id)
								{
										HashMap<String, String> map =(HashMap<String, String>)parent.getItemAtPosition(position);
										/*
										Toast.makeText(view.getContext(), map.get("biaoti"), Toast.LENGTH_SHORT).show();
										 */
										//向接收器界面传递收据
										intent = new Intent(List_TextView.this,Jieshouqi.class);
										intent.putExtra("biaoti",map.get("biaoti"));
										intent.putExtra("neirong",map.get("neirong"));
										startActivity(intent);
								}
						});
						
				//列表滑动事件
				listview.setOnScrollListener(new OnScrollListener()
						{
								@Override
								public void onScrollStateChanged(AbsListView p1, int p2)
								{
										// 待办事项：实现这个方法
								}
								@Override
								public void onScroll(AbsListView p1, int p2, int p3, int p4)
								{
										boolean shouldAnimate = (aaaa != -1) && (bbbb != -1);
										eeee = p2 + p3 - 1;
										if (shouldAnimate)
										{
												hhhh = 0;
												while (p2 + hhhh < aaaa)
												{
														//列表向上滑动事件
														View animateView = p1.getChildAt(hhhh);//获取item对应的view
														doAnimate(animateView, true);
														hhhh ++;
												}

												cccc = 0;
												while (eeee - cccc > bbbb)
												{
														//列表向下滑动事件
														View animateView = p1.getChildAt(eeee - cccc - p2);
														doAnimate(animateView, true);
														cccc ++;
												}
										}
										aaaa = p2;
										bbbb = eeee;
								}
						});

    }

		private void doAnimate(View view, boolean scrollDown)
		{
				list_donghua = AnimationUtils.loadAnimation(List_TextView.this, R.anim.list_left_in);
				view.startAnimation(list_donghua);

		}
}
