/*
 * huirong Inc.
 * Copyright (c) 2015 All Rights Reserved.
 * Author     :liyb
 * Create Date:2015年8月26日
 */
package costumetrade.common.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 贷款材料逻辑处理帮助类
 * @author liyb
 * @version MateriralUtils.java,2015年8月26日 下午2:07:46
 */
public class MateriralUtils implements Serializable {

    private static final long serialVersionUID = 2185434445730787572L;
    
    private static final Map<Integer, Object> flowMaterialType = new LinkedHashMap<Integer, Object>();
    
    static{
        flowMaterialType.put(101, "申请人材料");
        flowMaterialType.put(102, "共同还款人材料");
        flowMaterialType.put(103, "反担保人材料");
        flowMaterialType.put(601, "贷款其他材料");
        flowMaterialType.put(201, "家访材料");
        flowMaterialType.put(701, "家访其他材料");
        flowMaterialType.put(202, "企业材料");
        flowMaterialType.put(301, "签约材料");
        flowMaterialType.put(401, "提车材料");
        flowMaterialType.put(501, "车辆登记证材料");
        flowMaterialType.put(801, "签约其他材料");
        flowMaterialType.put(901, "其他材料");
    }

    /**
     * 初始化流程材料类型
     */
    public static Map<Integer,Object> getFlowMaterialType(){
       
        return flowMaterialType;
    }
    
    /**
     * 根据贷款人填写的问卷调查来构造材料
     * @return
     */
    public static Map<String,Map<Integer,List<Integer>>> surveyMaterials(){
        Map<String, Map<Integer,List<Integer>>> map = new LinkedHashMap<String, Map<Integer,List<Integer>>>();
        
        //婚姻状况（0.未婚 1.已婚 2.单身离婚 3.丧偶）
        Map<Integer,List<Integer>> ms = new HashMap<Integer, List<Integer>>();
        List<Integer> list0 = new ArrayList<Integer>();
        List<Integer> list1 = new ArrayList<Integer>();
        List<Integer> list2 = new ArrayList<Integer>();
        List<Integer> list3 = new ArrayList<Integer>();
        list0.add(71);//单身证明
        ms.put(0, list0);
        
        list1.add(271);//申请人结婚证
        ms.put(1, list1);
        
        list2.add(281);//申请人离婚证
        list2.add(301);//财产分割协议
        ms.put(2, list2);
        list3.add(311);//死亡证明
        ms.put(3, list3);
        map.put("maritalStatus", ms);
        
        //配偶
        Map<Integer,List<Integer>> sp = new HashMap<Integer, List<Integer>>();
        List<Integer> sp0 = new ArrayList<Integer>();
        sp0.add(181);//配偶户口本首页
        sp0.add(211);//配偶户口本户主页
        sp0.add(241);//配偶户口本本人页
        sp0.add(321);//配偶收入证明
        sp0.add(401);//配偶银行流水首页
        sp0.add(411);//配偶银行流水结息页季度一
        sp0.add(421);//配偶银行流水结息页季度二
        sp0.add(431);//配偶银行流水结息页季度三
        sp0.add(441);//配偶银行流水结息页季度四
        sp0.add(451);//配偶银行流水末页
        sp0.add(531);//配偶房产证
        sp0.add(561);//配偶土地证
        sp0.add(591);//配偶契证
        sp0.add(621);//配偶购房发票
        sp0.add(681);//配偶购房合同第二页
        sp0.add(691);//配偶购房合同第一页
        sp0.add(701);//配偶购房合同第三页
        sp0.add(711);//配偶购房合同第四页
        sp0.add(771);//配偶自建房证明
        sp0.add(801);//配偶居住证明
        sp0.add(1571);//配偶士兵证
        sp0.add(1831);//配偶惠瀜授权书
        sp.put(0, sp0);
        map.put("maritalSpouse", sp);
        
        //贷款人房屋情况（0.无房 1.有商品房 2.有自建房 ）
        Map<Integer,List<Integer>> hs = new HashMap<Integer, List<Integer>>();
        List<Integer> hl0 = new ArrayList<Integer>();
        List<Integer> hl1 = new ArrayList<Integer>();
        List<Integer> hl2 = new ArrayList<Integer>();
        hl0.add(791);//居住证明
        hs.put(0, hl0);
        hl1.add(521);//申请人房产证
        hl1.add(551);//申请人土地证
        hl1.add(581);//申请人契证
        hl1.add(611);//申请人购房发票
        hl1.add(641);//申请人购房合同第一页
        hl1.add(651);//申请人购房合同第二页
        hl1.add(661);//申请人购房合同第三页
        hl1.add(671);//申请人购房合同第四页
        hl1.add(760);//申请人自建房证明
        hs.put(1, hl1);
        hl2.add(761);//自建房证明
        hs.put(2, hl2);
        map.put("houseStatus", hs);
        
        //牌照情况（1：公牌，0：私牌）
        Map<Integer,List<Integer>> lt = new HashMap<Integer, List<Integer>>();
        List<Integer> lt1 = new ArrayList<Integer>();
        lt1.add(821);//营业执照
        lt1.add(831);//财务报表
        lt1.add(841);//公司章程第一页
        lt1.add(861);//公司章程第二页
        lt1.add(871);//公司章程第三页
        lt1.add(881);//公司章程第四页
        lt1.add(851);//验资报告
        lt1.add(891);//税务登记证
        lt1.add(901);//组织机构代码证
        lt1.add(1641);//财务报表第二页
        lt1.add(1651);//财务报表第三页
        lt1.add(1661);//财务报表第四页
        lt1.add(1671);//财务报表第五页
        lt1.add(1681);//财务报表第六页
        lt1.add(1691);//财务报表第七页
        lt1.add(1701);//财务报表第八页
        lt1.add(1711);//营业执照副本
        lt1.add(1721);//税务登记证副本
        lt1.add(1731);//组织机构代码证副本
        lt1.add(1741);//开户许可证正本
        lt1.add(1751);//开户许可证副本
        lt.put(1, lt1);
        map.put("licenseType", lt);
        
        //是否服兵役（0：否 ， 1：是）
        Map<Integer,List<Integer>> is = new HashMap<Integer, List<Integer>>();
        List<Integer> is0 = new ArrayList<Integer>();
        List<Integer> is1 = new ArrayList<Integer>();
        is0.add(261);//士兵证/军官证
        is.put(1, is0);
        is1.add(171);//申请人户口本首页
        is1.add(201);//申请人户口本户主页
        is1.add(231);//申请人户口本本人页
        is.put(0, is1);
        map.put("isServe", is);
        
        //是否需要反担保(1:是 ，0:否)
        Map<Integer,List<Integer>> ia = new HashMap<Integer, List<Integer>>();
        List<Integer> ia0 = new ArrayList<Integer>();
        ia0.add(191);//反担保人户口本首页
        ia0.add(221);//反担保人户口本户主页
        ia0.add(251);//反担保人户口本本人页
        ia0.add(331);//反担保人收入证明
        ia0.add(461);//反担保人银行流水首页
        ia0.add(471);//反担保人银行流水结息页季度一
        ia0.add(481);//反担保人银行流水结息页季度二
        ia0.add(491);//反担保人银行流水结息页季度三
        ia0.add(501);//反担保人银行流水结息页季度四
        ia0.add(511);//反担保人银行流水末页
        ia0.add(541);//反担保人房产证
        ia0.add(571);//反担保人土地证
        ia0.add(601);//反担保人契证
        ia0.add(631);//反担保人购房发票
        ia0.add(721);//反担保人购房合同第一页
        ia0.add(731);//反担保人购房合同第二页
        ia0.add(741);//反担保人购房合同第三页
        ia0.add(751);//反担保人购房合同第四页
        ia0.add(781);//反担保人自建房证明
        ia0.add(811);//反担保人居住证明
        ia0.add(1581);//反担保人士兵证
        ia0.add(1841);//反担保人惠融授权书
        ia.put(1, ia0);
        map.put("isAssure", ia);
        
        //购买国产车还是进口车（0：国产 ，1：进口）
        Map<Integer,List<Integer>> ct = new HashMap<Integer, List<Integer>>();
        List<Integer> ct0 = new ArrayList<Integer>();
        List<Integer> ct1 = new ArrayList<Integer>();
        ct0.add(1111);//车辆合格证
        ct.put(0, ct0);
        ct1.add(1131);//货物进口证明书
        ct1.add(1141);//进口机动车辆随车检验单
        ct.put(1, ct1);
        map.put("carType", ct);
        
        //签约材料额外-配偶
        Map<Integer,List<Integer>> supv = new HashMap<Integer, List<Integer>>();
        List<Integer> supv0 = new ArrayList<Integer>();
        supv0.add(1001);
        supv.put(0, supv0);
        map.put("visitSup", supv);
        //签约材料额外-反担保人
        Map<Integer,List<Integer>> fdbr = new HashMap<Integer, List<Integer>>();
        List<Integer> fdbr0 = new ArrayList<Integer>();
        fdbr0.add(1011);
        fdbr0.add(1031);
//        fdbr0.add(1071);
//        fdbr0.add(1081);
//        fdbr0.add(1091);
        fdbr.put(0, fdbr0);
        map.put("visitFdbr", fdbr);
        
        //是否企业法人(1:是 ，0:否)
        Map<Integer,List<Integer>> ep = new HashMap<Integer, List<Integer>>();
        List<Integer> ep1 = new ArrayList<Integer>();
        ep1.add(821);//营业执照
        ep.put(1, ep1);
        map.put("isEnterprise", ep);
        
        return map;
    }
    
    /**
     * 构造初始化验证材料
     * @return
     */
    public static Map<Integer,Object> initVerfityMaterials(){
        Map<Integer,Object> map = new LinkedHashMap<Integer, Object>();
        //基础资料
//        map.put(1, 1);//申请人身份证
//        map.put(21, 21);//配偶身份证
//        map.put(41, 41);//担保人身份证
        
        //源验证、交叉材料
        map.put(521, "申请人房产证");//申请人房产证
        map.put(821, "营业执照");//营业执照
        map.put(1101, "购车发票");//购车发票
        map.put(1151, "车辆登记证");//车辆登记证
        map.put(901, "组织机构代码证正本");//组织机构代码证正本
        
        map.put(611, "申请人购房发票");//申请人购房发票
        map.put(231, "申请人户口本本人页");//申请人户口本本人页
        map.put(261, "士兵证/军官证");//士兵证/军官证
        map.put(271, "申请人结婚证");//申请人结婚证
        map.put(281, "申请人离婚证");//申请人离婚证
        map.put(71, "单身证明");//单身证明
//        map.put(81, "申请人收入证明");//申请人收入证明
//        map.put(551, "申请人土地证");//申请人土地证
//        map.put(581, "申请人契证");//申请人契证
        
        map.put(1111, "合格证");//合格证
        map.put(1131, "货物进口证明书");//货物进口证明书
        map.put(1141, "进口机动车辆随车检验单");//进口机动车辆随车检验单
        
        
        //配偶材料
        map.put(621, "配偶购房发票");//配偶购房发票
        map.put(241, "配偶户口本本人页");//申请人户口本本人页
        map.put(531, "配偶房产证");//配偶房产证
//        map.put(321, "配偶收入证明");//配偶收入证明
//        map.put(561, "配偶土地证");//配偶土地证
//        map.put(591, "配偶契证");//配偶契证
        
        //反担保人
        map.put(631, "反担保人购房发票");//反担保人购房发票
        map.put(251, "反担保人户口本本人页");//反担保人户口本本人页
        map.put(541, "反担保人房产证");//反担保人房产证
//        map.put(331, "反担保人收入证明");//反担保人收入证明
//        map.put(571, "反担保人土地证");//反担保人土地证
//        map.put(601, "反担保人契证");//反担保人契证
        
        //二手车
        map.put(1951, "二手车注册登记证");//二手车注册登记证
        map.put(1961, "行驶证");//行驶证
        map.put(1971, "二手车车架号");//二手车车架号
        map.put(2021, "评估报告");//评估报告
        
        map.put(91, "公积金信息");//公积金
        map.put(341, "申请人银行流水首页");//申请人银行流水首页
        
        map.put(2051, "首付款凭证");//首付款凭证
        map.put(3280, "垫资凭证");//垫资凭证
        map.put(1761, "车辆商业险");//车辆商业险
        return map;
    }
    
    /**
     * 验证材料是否在材料类型中
     * @param materialsType
     * @return
     */
    public static boolean verifyMaterials(Integer materialsType){
        Map<Integer,Object> map = new HashMap<Integer, Object>();
        map.put(31, "户口本");
        map.put(53, "收入证明");
        map.put(443, "其他材料");
        map.put(143, "结婚证");
        map.put(153, "离婚证");
        map.put(163, "财产分割协议");
        map.put(73, "银行流水");
        map.put(63, "公积金");
        map.put(253, "营业执照");
        map.put(263, "财务报表");
        map.put(273, "公司章程");
        map.put(183, "房产证");
        map.put(193, "土地证");
        map.put(203, "契证");
        map.put(213, "购房发票");
        map.put(223, "购房合同");
        map.put(233, "自建房证明");
        return map.containsKey(materialsType);
    }
    
    public static void main(String[] args) {
        Map<String,Map<Integer,List<Integer>>> map = surveyMaterials();
        System.err.println(map);
        
        Map<Integer,List<Integer>> mateMap = map.get("maritalStatus");
        System.err.println(mateMap);
        List<Integer> list = mateMap.get(1);
        System.err.println(list);
    }
}
