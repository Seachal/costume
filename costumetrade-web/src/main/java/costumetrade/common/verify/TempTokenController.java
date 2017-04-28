/**
 * Copyright (C) 2014-2017, Hrfax and/or its affiliates. All rights reserved.
 * Hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.verify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import costumetrade.common.login.EscapeLogin;

@RestController
public class TempTokenController {
	
	@Autowired
	private ITempTokenServce tempTokenService;
	
	@EscapeLogin
	@RequestMapping("/token/get")
	public String key(){

		return tempTokenService.get();
	}
	

}
