package cn.iolove.domain;

public class MessageDate {
	/**
	 * 服务器返回消息
	 */
	public static final int USER_EXIST = 0X1001;
	public static final int SERVER_BUSY = 0X1002;
	public static final int LOGIN_SUCCESS = 0X1003;
	public static final int REG_SUCCESS = 0X1004; //注册成功
	public static final int SELECT_SUCCESS = 0X1005;
	public static final int SENDMESSAGE_SUCCESS = 0X1006;//请求成功
	public static final int SERVER_ADD_USER_FAIL = 0X1008;
	public static final int LOGIN_FAIL = 0X1009;
	public static final int SENDMESSAGE_FAIL = 0X100A;
	public static final int SERVER_FAIL = 0X100B;//服务器出现错误无法正确响应请求
	public static final int SERVER_SUCCESS = 0x100C;
	public static final int CHECKFRIENDNUM =0x100D;
    public static final int SEECK_FRIEND_SUCCESS=0x100E;
    public static final int SEARCH_FRIEND_SUCCESS=0x100F;
    public static final int POSTXIAONEI_SUCCESS=0X1010;
    public static final int GETXIAONEI_SUCCESS=0X1011;
    public static final int POSTMSG_SUCCESS=0X1012;
    public static final int POSTMSG_FAIL=0X1013;
    public static final int GETMSG_SUCCESS=0X1014;
    public static final int GETMSG_FAIL=0X1015;
    public static final int POSTALLMSG_ALL_SUCCESS=0X1015;
    public static final int SENDXIANGJIAO_SUCCESS=0X1016;
    public static final int SENDXIANGJIAO_FAIL=0X1017;

    public static final int GETXIANGJIAOLIST_FAIL=0X1018;
    public static final int GETXIANGJIAOLIST_SUCCESS=0X1019;
    public static final int GETFEIZAOLIST_SUCCESS=0X101A;
    public static final int GETFEIZAOLIST_FAIL=0X101B;
    public static final int SENDFEIZAO_SUCCESS=0X101C;
    public static final int SENDFEIZAO_FAIL=0X101D;
    public static final int DELETEUSER_SUCCESS=0X101e;
    public static final int UPADATPHOEN_SUCCESS=0X101F;
    
	/**
	 * 客户端请求消息
	 */
	public static final int REQUEST_UPDATEFREINDLIST =0X0001;
	public static final int REQUEST_REGUSER = 0X0002;
	public static final int REQUEST_SENDMESSAGE = 0X0003;
	public static final int REQUEST_RECIVENEWS = 0X0004;
	public static final int SERVER_CONNECT_ERROR = 0X0005;
	public static final int REQUEST_LOGIN = 0X0006;
	public static final int REQUEST_CHECKFRIENDLIST = 0X0007; //和服务器比对本地列表数目  如果相等不用重传直接显示
	public static final int REQUEST_SEARCHFRIEND = 0X0008;
	public static final int REQUEST_POSTXIAONEI = 0X0009;
	public static final int REQUEST_GETXIAONEI = 0X000A;
	public static final int REQUEST_POSTMSG=0X000B;
	public static final int REQUEST_GETMSG=0X000C;
	public static final int REQUEST_POSTALLMSG=0X000D;
	public static final int REQUEST_SENDXIANGJIAO=0X000E;
	public static final int REQUEST_GETXIANGJIAOLIST=0X000F;
	public static final int REQUEST_SENDFEIZAO=0X0010;
	public static final int REQUEST_GETFEIZAO=0X0011;
	public static final int REQUEST_DELETEUSER=0X0012;
	public static final int REQUEST_XIUGAIUSER=0X0013;
	
	
	

	

	
	

}
