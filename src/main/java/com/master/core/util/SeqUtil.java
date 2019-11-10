package com.master.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 处理系统中所需的序列
 * @author Rofly
 */
public class SeqUtil {
	/** 创建一个新的用户序列  */
	public static String produceUserid() {
		String day = new SimpleDateFormat("MMddYY").format(new Date());
		return "UR"+day+Strings.produceRandomStringByUpperChar(8);
	}
	/** 创建一个新的用户联系方式序列  */
	public static String produceUcid() {
		String day = new SimpleDateFormat("MMddYY").format(new Date());
		return "UC"+day+Strings.produceRandomStringByUpperChar(8);
	}
	/** 创建一个新的投诉与建议序列  */
	public static String produceSuggestid() {
		String day = new SimpleDateFormat("MMddYY").format(new Date());
		return "ST"+day+Strings.produceRandomStringByUpperChar(8);
	}
	
	/** 创建一个新的用户徽章关系id **/
	public static String produceUserBadgeid(){
		String day = new SimpleDateFormat("MMddYY").format(new Date());
		return "UB"+day+Strings.produceRandomStringByUpperChar(8);
	}
	/*********************************账户部分*******************************************/
	/** 创建一个账户序列*/
	public static String produceAccountid(){
		String day = new SimpleDateFormat("MMddYY").format(new Date());
		return "AC"+day+Strings.produceRandomStringByUpperChar(8);
	}
	/** 创建一个外部账户序列*/
	public static String produceExternalAccountid(){
		String day = new SimpleDateFormat("MMddYY").format(new Date());
		return "EX"+day+Strings.produceRandomStringByUpperChar(8);
	}
	/** 创建一个新的充值序列*/
	public static String produceTopupid(){
		String day = new SimpleDateFormat("MMddYY").format(new Date());
		return "TO"+day+Strings.produceRandomStringByUpperChar(8);
	}
	/** 创建一个新的交易序列*/
	public static String produceTradeid(){
		String day = new SimpleDateFormat("MMddYY").format(new Date());
		return "TR"+day+Strings.produceRandomStringByUpperChar(8);
	}
	/** 创建一个新的提现序列*/
	public static String produceWithdrawalid(){
		String day = new SimpleDateFormat("MMddYY").format(new Date());
		return "WD"+day+Strings.produceRandomStringByUpperChar(8);
	}
	/** 创建一个新的冻结序列*/
	public static String produceFrozenid(){
		String day = new SimpleDateFormat("MMddYY").format(new Date());
		return "AF"+day+Strings.produceRandomStringByUpperChar(8);
	}
	/** 创建一个新的账户活动序列*/
	public static String produceActivityid(){
		String day = new SimpleDateFormat("MMddYY").format(new Date());
		return "AT"+day+Strings.produceRandomStringByUpperChar(8);
	}
	
	/*********************************信息部分*******************************************/
	/** 创建一个服务序列  */
	public static String produceServiceid() {
		String day = new SimpleDateFormat("MMddYY").format(new Date());
		return "SE"+day+Strings.produceRandomStringByUpperChar(8);
	}
	
	/** 创建一个标签序列  */
	public static String produceTagid() {
		String day = new SimpleDateFormat("MMddYY").format(new Date());
		return "TG"+day+Strings.produceRandomStringByUpperChar(8);
	}
	
	/** 创建一个标签层级序列  */
	public static String produceTagHierarchyid() {
		String day = new SimpleDateFormat("MMddYY").format(new Date());
		return "TH"+day+Strings.produceRandomStringByUpperChar(8);
	}
	
	/** 创建一个关联序列  */
	public static String produceRelationServiceTagid() {
		String day = new SimpleDateFormat("MMddYY").format(new Date());
		return "RT"+day+Strings.produceRandomStringByUpperChar(8);
	}
	
	/** 创建一个评价序列  */
	public static String produceReviewid() {
		String day = new SimpleDateFormat("MMddYY").format(new Date());
		return "RV"+day+Strings.produceRandomStringByUpperChar(8);
	}
	
	/** 创建一个服务快序列  */
	public static String produceServiceSnapshotid() {
		String day = new SimpleDateFormat("MMddYY").format(new Date());
		return "SS"+day+Strings.produceRandomStringByUpperChar(8);
	}
	
	/** 创建一个通知编号 */
	public static String produceNotifyid() {
		String day = new SimpleDateFormat("MMddYY").format(new Date());
		return "NY"+day+Strings.produceRandomStringByUpperChar(8);
	}
	
	/*********************************文章部分*******************************************/
	/** 创建一个文章编号*/
	public static String produceArticleid(){
		String day = new SimpleDateFormat("MMddYY").format(new Date());
		return "WZ"+day+Strings.produceRandomStringByUpperChar(8);
	}
	
	/** 创建一个关联序列  */
	public static String produceRelationArticleTagid() {
		String day = new SimpleDateFormat("MMddYY").format(new Date());
		return "RT"+day+Strings.produceRandomStringByUpperChar(8);
	}
	
	/*********************************订单部分*******************************************/
	/** 创建一个订单编号  */
	public static String produceOrderid(){
		String day = new SimpleDateFormat("MMddYY").format(new Date());
		return "EA"+day+Strings.produceRandomStringByUpperChar(8);
	}
	/** 创建一个预约编号  */
	public static String produceAppointmentid(){
		String day = new SimpleDateFormat("MMddYY").format(new Date());
		return "AP"+day+Strings.produceRandomStringByUpperChar(8);
	}
	/** 创建一个订单用户关系编号  */
	public static String produceRelationOrderUserid(){
		String day = new SimpleDateFormat("MMddYY").format(new Date());
		return "OU"+day+Strings.produceRandomStringByUpperChar(8);
	}
	/** 创建一个订单退款编号  */
	public static String produceOrderRefundid(){
		String day = new SimpleDateFormat("MMddYY").format(new Date());
		return "OR"+day+Strings.produceRandomStringByUpperChar(8);
	}
	/** 创建一个订单活动编号  */
	public static String produceOrderActivityid(){
		String day = new SimpleDateFormat("MMddYY").format(new Date());
		return "OA"+day+Strings.produceRandomStringByUpperChar(8);
	}
	
	/*********************************直播部分*******************************************/
	/** 创建一个直播编号  */
	public static String produceLiveid(){
		String day = new SimpleDateFormat("MMddYY").format(new Date());
		return "LI"+day+Strings.produceRandomStringByUpperChar(8);
	}
	public static String produceStreamerKey(){
		String day = new SimpleDateFormat("MMddYY").format(new Date());
		return "SK"+day+Strings.produceRandomStringByAllChar(8);
	}
	
	public static String produceRewardid(){
		String day = new SimpleDateFormat("MMddYY").format(new Date());
		return "RE"+day+Strings.produceRandomStringByAllChar(8);
	}
	
	
	/***************************环信部分***************************************/
	//
	public static String getEAUserPassword(){
		return Strings.produceRandomStringByUpperChar(8);
	}
	
	public static String getTempEAUsername(String torrent){
		String day = new SimpleDateFormat("MMdd").format(new Date());
		return "te"+day+torrent.substring(0, 10);
	}
	/***************************短消息部分***************************************/
	public static String getSmsid(){
		String day = new SimpleDateFormat("MMddYY").format(new Date());
		return "SM"+day+ Strings.produceRandomStringByUpperChar(8);
	}
}
