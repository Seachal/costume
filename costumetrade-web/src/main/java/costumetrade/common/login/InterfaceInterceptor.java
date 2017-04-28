/*
 * Copyright 2005-2015 shunwang.com. All Rights Reserved.
 

package com.shunwang.sy.core.interceptor;

import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.shunwang.sy.api.result.ResultBean;
import com.shunwang.sy.api.constant.ApiConstant;
import com.shunwang.sy.api.constant.SyApiMethod;
import com.shunwang.sy.api.pojo.SyApi;
import com.shunwang.sy.config.interfaces.pojo.ConfigSiteInterface;
import com.shunwang.sy.config.interfaces.service.ConfigSiteInterfaceService;
import com.shunwang.util.encrypt.Md5Encrypt;
import com.shunwang.util.lang.StringUtil;

import static com.shunwang.util.date.DateUtil.*;

*//**
 * 提供对外接口处理
 *
 * @author yh1.yu
 * @since 2015-01-30
 *//*
@SuppressWarnings("rawtypes")
@Component
public class InterfaceInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterfaceInterceptor.class);

    @Autowired
    private ConfigSiteInterfaceService configSiteInterfaceService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            SyApi apiAnnotation = ((HandlerMethod) handler).getMethodAnnotation(SyApi.class);
            if (apiAnnotation == null) {
                return true;
            }
            // 方法检验
            if (!this.requestMethodCheck(apiAnnotation, request)) {
                return true;
            }
            // 接口校验
            ResultBean rb = this.interfaceCheck(request, apiAnnotation);
            if (rb != null) {
            	response.setContentType("application/json");
                response.getWriter().print(rb.toJSON());
                return false;
            }
        }
        return true;
    }

    *//**
     * HTTP 请求方法检验
     *
     * @param apiAnnotation 服务接口
     * @param request       请求
     * @return 是否支持请求方法
     * @author yh1.yu
     * @since 2015-1-30
     *//*
    private boolean requestMethodCheck(SyApi apiAnnotation, HttpServletRequest request) {
        if (apiAnnotation.method() == SyApiMethod.POST) {
            return true;
        }
        // 方法检验
        String reqMethod = request.getMethod();
        boolean support = apiAnnotation.method() == SyApiMethod.valueOf(reqMethod);
        if (!support) {
            LOGGER.error("接口[{}]不支持请求方法：{}", apiAnnotation.name(), reqMethod);
        }

        return support;
    }

    private ResultBean interfaceCheck(HttpServletRequest request, SyApi apiAnnotation) {
        // 校验非空
        ResultBean rb = validateNullParam(request);
        if (rb != null) {
            return rb;
        }
        // 校验接口服务
        String businessKey = request.getParameter("businessKey");
        ConfigSiteInterface apiInterface = new ConfigSiteInterface();
        apiInterface.setBusinessKey(businessKey);
        apiInterface.setServiceKey(apiAnnotation.serviceKey());
        apiInterface = configSiteInterfaceService.getByBusKeyAndServiceKey(apiInterface);
        if (null == apiInterface) {
            return new ResultBean(ApiConstant.INTERFACE_CLOSE, businessKey + "尚未开通对应的接口服务！");
        }

        // 接口是否关闭
        if (apiInterface.getState() == 0) {
            return new ResultBean(ApiConstant.INTERFACE_CLOSE, businessKey + "对应的接口服务已关闭！");
        }
        // 签名检验
        ResultBean signResult = validateSign(request, apiInterface);
        if (signResult != null) {
            return signResult;
        }
        // IP检验
        ResultBean ipResult = validateIp(request, apiInterface);
        if (ipResult != null) {
            return ipResult;
        }
        // 时间戳校验
        return validateTime(request, apiInterface);
    }

    *//**
     * 校验非空
     *
     * @author yh1.yu
     * @since 2015-1-30
     *//*
    private ResultBean validateNullParam(HttpServletRequest request) {
        String businessKey = request.getParameter("businessKey");
        String time = request.getParameter("time");
        String sign = request.getParameter("sign");
        if (StringUtil.isBlank(businessKey)) {
            return new ResultBean(ApiConstant.PARAMETER_NULL, "调用方商户不能为空！");
        }
        if (StringUtil.isBlank(time)) {
            return new ResultBean(ApiConstant.PARAMETER_NULL, "时间戳不能为空！");
        }
        if (StringUtil.isBlank(sign)) {
            return new ResultBean(ApiConstant.PARAMETER_NULL, "签名不能为空！");
        }
        return null;
    }

    *//**
     * 功能：校验时间的合法性
     *
     * @author yh1.yu
     * @since 2015-1-23
     *//*
    private ResultBean validateTime(HttpServletRequest request,
                                      ConfigSiteInterface configSiteInterface) {
        if (configSiteInterface.getPermitTime() == null) {
            return null;
        }
        String time = request.getParameter("time");
        if (time.trim().length() != 14) {
            return new ResultBean(ApiConstant.PARAMETER_ERROR, "非法的时间戳格式！");
        }
        Date date;
        try {
            date = praseDate(getInstance(ymdhms_TIME_STAMP_FORMAT), time);
        } catch (ParseException e) {
            LOGGER.error("日期格式错误：", e);
            return new ResultBean(ApiConstant.PARAMETER_ERROR, "非法的时间戳格式！");
        }
        long ce = Math.abs(compare(new Date(), date, ONE_MILLI_SECOND));
        if (ce > configSiteInterface.getPermitTime()) {
            LOGGER.error("接口调用时间误差过大，允许时间：{}，实际实际：{}",
                         configSiteInterface.getPermitTime(), ce);
            return new ResultBean(ApiConstant.TIME_ERROR, "调用方与服务方时间不一致！");
        }
        return null;
    }

    *//**
     * 功能：校验IP地址的合法性
     *
     * @author yh1.yu
     * @since 2015-1-26
     *//*
    private ResultBean validateIp(HttpServletRequest request,
                                    ConfigSiteInterface configSiteInterface) {
        String permitIp = configSiteInterface.getPermitIp();
        String ip = request.getParameter("ip");
        if (null == permitIp || permitIp.equals("")) {
            return null;
        }
        String[] ips = permitIp.split("\\|");
        if (ips.length == 0) {
            return null;
        }
        for (String s : ips) {
            if (s.equals(ip)) {
                return null;
            }
        }
        return new ResultBean(ApiConstant.IP_ERROR, "IP地址不合法！");
    }

    @SuppressWarnings("unchecked")
    private ResultBean validateSign(HttpServletRequest request,
                                      ConfigSiteInterface configSiteInterface) {
        String sign = request.getParameter("sign");
        Map<String, String> sortedMap = new TreeMap<String, String>();
        Map<String, String[]> paramMap = request.getParameterMap();
        for (Entry<String, String[]> e : paramMap.entrySet()) {
            String key = e.getKey();
            if ("sign".equals(key)) {
                sign = e.getValue()[0];
                continue;
            }
            sortedMap.put(key, e.getValue()[0]);
        }
        List<String> valList = new LinkedList<String>();
        for (Entry<String, String> e : sortedMap.entrySet()) {
            valList.add(e.getValue());
        }
        valList.add(configSiteInterface.getMd5Key());
        String sourceStr = StringUtils.join(valList, "|");
        String signStr = Md5Encrypt.encrypt(sourceStr);
        if (!signStr.equals(sign)) {
            LOGGER.error("签名错误，我方原串sourceStr：{},我方加密后sign：{}，对方sign：{}",
                         sourceStr, signStr, sign);
            return new ResultBean(ApiConstant.SIGN_ERROR, "签名错误");
        }
        return null;
    }
    public static void main(String[] args) {
    	System.out.println(Md5Encrypt.encrypt("sdwwkj|2.00|143040144026|sdwwkj|43532523fdsfds|20150430134400|caa6e115fa5a8f84116a38b3afda01d2"));
	}
}
*/