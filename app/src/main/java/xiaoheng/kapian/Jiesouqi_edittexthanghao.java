package xiaoheng.kapian;

import android.content.*;
import android.widget.*;
import android.graphics.*;
import android.util.*;
import android.text.*;
import android.view.*;

public class Jiesouqi_edittexthanghao extends EditText
{
		private Paint line;
		public Jiesouqi_edittexthanghao(Context context,AttributeSet As){
				super(context,As);
				setFocusable(true);
				line=new Paint();
				//线条颜色
				line.setColor(Color.parseColor("#616161"));
				//线条宽
				line.setStrokeWidth(1);
				//第一个参数是光标距离
				setPadding(60,0,0,0);
				setGravity(Gravity.TOP);
		}
		@Override
		protected void onDraw(final Canvas canvas)
		{
				if(getText().toString().length()!=0){
						float y=0;
						Paint p=new Paint();
						p.setColor(Color.parseColor("#9E9E9E"));
						//行号文字大小
						p.setTextSize(33);
						p.setAntiAlias(true);//抗锯齿
						p.setFakeBoldText(true);
						for(int l=0;l<getLineCount();l++){
								y=((l+1)*getLineHeight())-(getLineHeight()/12);
								canvas.drawText(String.valueOf(l+1),0,y,p);
								canvas.save();
						}
				}
				int k=getLineHeight();
				int i=getLineCount();
				/*第一个参数为竖线条上端距离,
				 第三个参数为竖线条下端距离*/
				canvas.drawLine(60,0,60,getHeight()+(i*k),line);
				int y=(getLayout().getLineForOffset(getSelectionStart())+1)*k;
				canvas.drawLine(0,y,getWidth(),y,line);
				canvas.save();
				canvas.restore();
				super.onDraw(canvas);
		}
}
