/**
 * Copyright (C) 2014-2017, Hrfax and/or its affiliates. All rights reserved.
 * Hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.verify;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import akka.japi.Pair;
import costumetrade.common.login.EscapeLogin;
import costumetrade.common.login.LoginContext;
import costumetrade.common.login.SupportTempLogin;

/**
 * @author zhouyq
 * @Date 2017年3月15日
 */
@Controller
public class CapchaController {

	private static final ImageVerification ImageVerification = new ImageVerification();
	
	@Autowired
	private ICaptchaService captchaService;
	
	@EscapeLogin
	@RequestMapping("/capcha/get")
	public void get(String businessKey,HttpServletResponse response) throws IOException {
		
		if(StringUtils.isBlank(businessKey)){
			throw new IllegalArgumentException("业务key不能为空");
		}
		
		response.setContentType("image/png");
		response.setDateHeader("exprise", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		Pair<String, BufferedImage> codeImage = ImageVerification.create();
		captchaService.save(businessKey, codeImage.first());
		OutputStream outputStream = response.getOutputStream();
		ImageIO.write(codeImage.second(), "png", outputStream);
		outputStream.flush();
	}
	
	

}
