package costumetrade.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.commons.lang.StringUtils;


/**
 * <p>
 * Title: StageMoneyUtil.java<／p>
 * <p>
 * Description: 用户分期金额相关计算<／p>
 * 
 * @author yh.yu
 * @date 2015年8月19日
 */
public class StageMoneyUtil {

	/**
	 *  计算首付金额
	 *  复核车价 - 贷款金额
	 *  
	 */
	public static BigDecimal computeSfMoney(BigDecimal carPrice,
			BigDecimal loanMoney) {
		if (carPrice!=null && loanMoney!=null &&BigDecimalUtil.greaterThan(carPrice, new BigDecimal(0))
				&& BigDecimalUtil.greaterThan(loanMoney, new BigDecimal(0))) {
			return BigDecimalUtil.initSubtract(carPrice,loanMoney);
		}
		return new BigDecimal(0);
	}
	
	/**
	 * 计算 首付比例 = 首付金额/（首付金额+贷款金额）
	 * 
	 * 修改为  = 首付金额/复核车价
	 * 
	 * @param sfMoney
	 *            首付金额
	 * @param carPrice
	 *           复核车价
	 * @return
	 */
	public static BigDecimal computeSfProportion(BigDecimal sfMoney,
			BigDecimal carPrice) {
		if (sfMoney!=null && BigDecimalUtil.greaterThan(sfMoney, new BigDecimal(0))
				&& BigDecimalUtil.greaterThan(carPrice, new BigDecimal(0))) {
			return sfMoney.divide(carPrice, 4, BigDecimal.ROUND_HALF_UP);
		}
		return new BigDecimal(0);
	}

	/**
	 * 分期金额 = 贷款金额 *（1+分期手续费率-贴息利率）
	 * 
	 * @param loanMoney
	 * @param commissionFeeRate
	 * @param discountRate
	 * @return 
	 */
	public static BigDecimal computeStageMoney(BigDecimal loanMoney,
			BigDecimal commissionFeeRate, BigDecimal discountRate) {
		if (loanMoney !=null && BigDecimalUtil.greaterThan(loanMoney, new BigDecimal(0))) {
				BigDecimal rate =commissionFeeRate.subtract(discountRate).setScale(5, BigDecimal.ROUND_HALF_UP);
				rate= new BigDecimal(1).add(rate).setScale(5, BigDecimal.ROUND_HALF_UP);
				return BigDecimalUtil.multiply(loanMoney, rate);
		}
		return new BigDecimal(0);
	}
	/**
	 * 首月还款金额 = 分期金额-月还款金额*(还款期数-1)
	 * @param stageMoney
	 * @param monthMoney
	 * @param repayPeriod
	 * @return
	 */
	public static BigDecimal computeFirstMonthMoney(BigDecimal stageMoney,
			BigDecimal monthMoney, Integer repayPeriod) {
		if (stageMoney!=null) {
			if (BigDecimalUtil.greaterThan(stageMoney, new BigDecimal(0))
					&& BigDecimalUtil.greaterThan(monthMoney, new BigDecimal(0))
					&& repayPeriod > 1) {
				return BigDecimalUtil.initSubtract(stageMoney,
						BigDecimalUtil.initMultiply(monthMoney,BigDecimalUtil.initSubtract(new BigDecimal(repayPeriod),new BigDecimal(1))));
			}
		}
		
		return new BigDecimal(0);
	}

	/**
	 * 月还款金额 = 分期金额/还款期数
	 * @param stageMoney
	 * @param repayPeriod
	 * @return
	 */
	public static BigDecimal computeMonthMoney(BigDecimal stageMoney,Integer repayPeriod) {
		if (stageMoney!=null) {
			if (BigDecimalUtil.greaterThan(stageMoney, new BigDecimal(0))
					&& repayPeriod > 1) {
						return BigDecimalUtil.divide(stageMoney, new BigDecimal(repayPeriod),0, BigDecimal.ROUND_FLOOR);
			}
		}
		return new BigDecimal(0);
	}

	public static BigDecimal formatPercent(String money) {
		if (StringUtils.isNotEmpty(money) && BigDecimalUtil.greaterThan(new BigDecimal(money), new BigDecimal(0))) {
			return BigDecimalUtil.divide(new BigDecimal(money), new BigDecimal(100),5, BigDecimal.ROUND_HALF_UP);
		}
		return new BigDecimal(0);
	}
	//季度日均存款余额=（1季度+2季度+……+n季度）/n
	
	/**
	 * 每季结算户日均存款余额=当季利息/日利率/90
	 * @param quarterInterest
	 * @param dayRate
	 * @return
	 */
//	public static BigDecimal computeQuarterMoney(BigDecimal quarterInterest,BigDecimal dayRate){
//		return BigDecimalUtil.divide(quarterInterest, dayRate, new BigDecimal(90));
//	}

//	public static String showRate(BigDecimal rate){
//		rate =BigDecimalUtil.initMultiply(rate, new BigDecimal(100));
//		
//	}
	public static String myPercent(int y, int z) { 
	    double baiy = y * 1.0; 
	    double baiz = z * 1.0; 
	    double fen = baiy / baiz; 
	    DecimalFormat df1 = new DecimalFormat("##.00%");
	// ##.00% 
	// 百分比格式，后面不足2位的用0补齐 
	    return df1.format(fen); 
	  } 

	/**
	 * 格式化 首付比例 
	 * @param sfProportion
	 * @return
	 */
	public static String formatSFProportion(BigDecimal sfProportion){
		if (sfProportion!=null && BigDecimalUtil.greaterThan(sfProportion, new BigDecimal(0))) {
			DecimalFormat sf = new DecimalFormat("##.00%");
			return sf.format(sfProportion);
		}
		return "0.00%";
	}
	
	//（信息汇总表的购车价格-系统抓取车价）/系统抓取车价
	public static BigDecimal computeCarPrice(BigDecimal buyCarPrice,BigDecimal systemCarPrice){
		if (buyCarPrice!=null && systemCarPrice!=null && BigDecimalUtil.greaterThan(buyCarPrice, systemCarPrice)) {
			return BigDecimalUtil.divide(BigDecimalUtil.subtract(buyCarPrice,systemCarPrice), systemCarPrice);
		}
		return new BigDecimal(0);
	}
	
	public static void main(String[] args) {
		System.out.println(formatPercent("567.09871"));
	}
	
}
