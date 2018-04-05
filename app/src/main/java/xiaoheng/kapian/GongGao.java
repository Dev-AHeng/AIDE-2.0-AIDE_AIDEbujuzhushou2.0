package xiaoheng.kapian;

import cn.bmob.v3.*;

public class GongGao extends BmobObject
{
	//获取公告内容
	private String GongGao;//后台截取GongGao(公告)
	public String getgonggao()
	{
		return GongGao;
	}
	public void setgonggao(String gonggao)
	{
		this.GongGao=gonggao;
	}
	
	
	private String fenxiang_biaoti;//分享标题
	private String fenxiang_neirong;//分享内容

	public String getfenxiang_biaoti()
	{
		return fenxiang_biaoti;
	}
	public void setfenxiang_biaoti(String fenxiang_biaoti)
	{
		this.fenxiang_biaoti = fenxiang_biaoti;
	}


	public String getfenxiang_neirong()
	{
		return fenxiang_neirong;
	}
	public void setfenxiang_neirong(String fenxiang_neirong)
	{
		this.fenxiang_neirong = fenxiang_neirong;
	}
	
}

class TianJiaXinXi extends BmobObject
{
	private String ShouJiXinXi;
	private String JianQieBan;
	private String JiLuQQ;
	
	//添加数据(手机信息)
    public String getName()
	{
        return ShouJiXinXi;
    }
    public void setName(String name)
	{
        this.ShouJiXinXi= name;
    }
	
	//添加数据(剪切板内容)
	public String getAddress()
	{
        return JianQieBan;
    }
    public void setAddress(String address)
	{
        this.JianQieBan= address;
    }
	
	//添加数据(登陆过的QQ)
	public String getjiluqq()
	{
        return JiLuQQ;
    }
    public void setjiluqq(String jiluqq)
	{
        this.JiLuQQ= jiluqq;
    }
	
	
}

class FanKui extends BmobObject
{
	private String BiaoTi;
	private String MiaoShu;
	private String ShouJiXinXi;
	private String WeiXin;
	private String QQ;
	//private String YouXiang;

	//添加标题
    public String getbiaot()
	{
        return BiaoTi;
    }
    public void setbiaoti(String biaoti)
	{
        this.BiaoTi= biaoti;
    }

	//添加描述
	public String getmiaoshu()
	{
        return MiaoShu;
    }
    public void setmiaoshu(String miaoshu)
	{
        this.MiaoShu= miaoshu;
    }
	
	//添加手机信息
	public String getsoujixinxi()
	{
        return ShouJiXinXi;
    }
    public void setsoujixinxi(String soujixinxi)
	{
        this.ShouJiXinXi= soujixinxi;
    }
	
	//添加微信
	public String getweixin()
	{
        return WeiXin;
    }
    public void setweixin(String weixin)
	{
        this.WeiXin= weixin;
    }
	
	//添加QQ
	public String getqq()
	{
        return QQ;
    }
    public void setqq(String qq)
	{
        this.QQ= qq;
    }
	
	/*
	//添加邮箱
	public String getyouxiang()
	{
        return YouXiang;
    }
    public void setyouxiang(String youxiang)
	{
        this.YouXiang= youxiang;
    }*/
}
