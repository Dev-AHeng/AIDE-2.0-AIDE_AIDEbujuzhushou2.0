package xiaoheng.kapian;

import android.content.*;
import android.os.*;
import android.support.v4.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.view.View.*;

public class Fragment_1 extends Fragment
{
	private CardView cardView1,cardView2,cardView3,cardView4,cardView5,cardView6;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_1, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onActivityCreated(savedInstanceState);
		
		//绑定控件ID
		 cardView1=(CardView) getActivity().findViewById(R.id.mainCardView1);
		 cardView2=(CardView) getActivity().findViewById(R.id.mainCardView2);
		 cardView3=(CardView) getActivity().findViewById(R.id.mainCardView3);
		 cardView4=(CardView) getActivity().findViewById(R.id.mainCardView4);
		 cardView5=(CardView) getActivity().findViewById(R.id.mainCardView5);
		 cardView6=(CardView) getActivity().findViewById(R.id.mainCardView6);

		 //设置图片圆角的半径大小
		 cardView1.setRadius(15);
		 cardView2.setRadius(15);
		 cardView3.setRadius(15);
		 cardView4.setRadius(15);
		 cardView5.setRadius(15);
		 cardView6.setRadius(15);

		 //设置阴影部分大小
		 cardView1.setCardElevation(10);
		 cardView2.setCardElevation(10);
		 cardView3.setCardElevation(10);
		 cardView4.setCardElevation(10);
		 cardView5.setCardElevation(10);
		 cardView6.setCardElevation(10);

		 //设置图片距离阴影大小
		 cardView1.setUseCompatPadding(true);
		 cardView2.setUseCompatPadding(true);
		 cardView3.setUseCompatPadding(true);
		 cardView4.setUseCompatPadding(true);
		 cardView5.setUseCompatPadding(true);
		 cardView6.setUseCompatPadding(true);
		 
		cardView1.setOnClickListener(new OnClickListener()
		{
				@Override
				public void onClick(View p1)
				{
					// TODO: Implement this method
					startActivity(new Intent(Fragment_1.this.getActivity(),List_TextView.class));
				}
			});
		 
		
	}
	
}
