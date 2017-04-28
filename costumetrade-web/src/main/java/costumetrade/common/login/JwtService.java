package costumetrade.common.login;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.IncorrectClaimException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MissingClaimException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Component
public class JwtService {

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtService.class);

	@Value("${jwt.issuer:hrfax.costumetrade.jwt}")
	private String issuer;

	@Value("${jwt.secretKey:eyJhbGciOiJIUzUxMiJ9}")
	private String secretKey;
	
	@Value("${costumetrade.login.token.expired:3600}")//1个小时
	private long expiredDuration;
	
	@Value("${costumetrade.login.app.token.expired:324000}") // 三个月
	private long appExpiredDuration;

	public String tokenFor(String account) {

		return Jwts.builder().setSubject(account).setId(RandomStringUtils.random(10)).setIssuer(issuer).signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();
	}

	public boolean validToken(String token, String account) {
		try {
			Jwts.parser().requireIssuer(issuer).requireSubject(account).setSigningKey(secretKey).parseClaimsJws(token);
		} catch (MissingClaimException | IncorrectClaimException | SignatureException e) {
			LOGGER.error("认证失败", e);
			throw new AuthenticationFailureException("认证失败");
		}
		return true;
	}

	public long getExpiredDuration() {
		return expiredDuration;
	}

	public long getAppExpiredDuration() {
		return appExpiredDuration;
	}
	
	
}