package xiaoheng.kapian;

import android.content.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v4.app.*;
import android.support.v4.widget.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.widget.AbsListView.*;
import cn.bmob.v3.*;
import cn.bmob.v3.listener.*;
import java.util.*;
import xiaoheng.kapian.*;

import android.view.View.OnClickListener;

public class Fragment_2 extends Fragment
{
	private ListView list;
	private SwipeRefreshLayout sw;
	List<GongGao> mPostlist = new ArrayList<GongGao>();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_2, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		
		list = (ListView)getActivity().findViewById(R.id.listpost);
		
		//进入时加载数据
		getdata();

		sw = (SwipeRefreshLayout)getActivity().findViewById(R.id.mainSwipeRefreshLayout1);
		sw.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_green_light);
		sw.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
			{
				@Override
				public void onRefresh()
				{
					//text.setText("正在刷新");
					getdata();
					new Handler().postDelayed(new Runnable(){

							@Override
							public void run()
							{
								//text.setText("刷新完成");
								sw.setRefreshing(false);
								Toast.makeText(getActivity(),"刷新成功", Toast.LENGTH_SHORT).show();
							}
						}, 3000);
				}
			});



		list.setOnScrollListener(new OnScrollListener()
			{
				@Override
				public void onScrollStateChanged(AbsListView view, int scrollState)
				{
				}
				@Override
				public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
				{
					boolean enable = false;
					if (list != null && list.getChildCount() > 0)
					{
						// 检查listView第一个item是否可见
						boolean firstItemVisible = list.getFirstVisiblePosition() == 0;
						// 检查第一个item的顶部是否可见
						boolean topOfFirstItemVisible = list.getChildAt(0).getTop() == 0;
						// 启用或者禁用SwipeRefreshLayout刷新标识
						enable = firstItemVisible && topOfFirstItemVisible;
					}
					else if (list != null && list.getChildCount() == 0)
					{
						// 没有数据的时候允许刷新
						enable = true;
					}
					// 把标识传给swipeRefreshLayout
					sw.setEnabled(enable);

				}
			});



		FloatingActionButton fab=(FloatingActionButton)getActivity().findViewById(R.id.mainFloatingActionButton1);
		fab.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					Intent intent = new Intent(Fragment_2.this.getActivity(),Fatie.class);
					startActivity(intent);
				}
			});
	}

	public void getdata()
	{
		Bmob.initialize(this.getActivity(),"99a26b3b9e330afec9f24b690cadbe84");
		BmobQuery<GongGao> query=new BmobQuery<GongGao>();
		query.order("-createdAt");//依照数据排序时间排序
		query.setLimit(500);
		query.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);
		query.findObjects(this.getActivity(),new FindListener<GongGao>()
			{
				@Override
				public void onSuccess(List<GongGao> object)
				{
					mPostlist.clear();
					for (int i = 0; i < object.size(); i++)
					{
						mPostlist.add(object.get(i));
					}
					list.setAdapter(new ItemListAdapter());
				}
				@Override
				public void onError(int p1, String p2)
				{
					Toast.makeText(getActivity(),"数据加载失败" + p1 + p2, Toast.LENGTH_SHORT).show();
				}
			});
	}



	class ItemListAdapter extends BaseAdapter
	{
		private Intent intent;
		private ViewHolder viewHolder;
		private GongGao fenxiang;
		//适配器
		@Override
		public int getCount()
		{
			if (mPostlist.size() > 0)
			{
				return mPostlist.size();
			}
			return 0;
		}
		@Override
		public Object getItem(int position)
		{
			return mPostlist.get(position);
		}

		@Override
		public long getItemId(int position)
		{
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent)
		{
			convertView = getActivity().getLayoutInflater().inflate(R.layout.list_fenxiang, null);
			viewHolder = new ViewHolder();
			viewHolder.biaoti = (TextView) convertView.findViewById(R.id.photoname);
			viewHolder.neirong = (TextView)convertView.findViewById(R.id.itempostTextView1);
			viewHolder.l = (LinearLayout)convertView.findViewById(R.id.list_fenxiangLinearLayout);
			fenxiang = mPostlist.get(position);
			viewHolder.biaoti.setText(fenxiang.getfenxiang_biaoti());
			viewHolder.neirong.setText(fenxiang.getfenxiang_neirong());
			// TextView设置文本
			viewHolder.l.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view)
					{
						String biaoti=mPostlist.get(position).getfenxiang_biaoti(); //这里就获取到了内容。操作在下面写也行
						String neirong=mPostlist.get(position).getfenxiang_neirong();
						intent = new Intent(Fragment_2.this.getActivity(),Jieshouqi.class);
						intent.putExtra("biaoti", biaoti);
						intent.putExtra("neirong", neirong);
						startActivity(intent);
						//Toast.makeText(MainActivity.this,biaoti+"\n"+neirong,Toast.LENGTH_SHORT).show();
					}
				});
			return convertView;
		}
		public class ViewHolder
		{
			public TextView biaoti,neirong;
			private LinearLayout l;
		}
	}
}
