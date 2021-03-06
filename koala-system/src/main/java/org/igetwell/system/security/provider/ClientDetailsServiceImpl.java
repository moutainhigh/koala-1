package org.igetwell.system.security.provider;

import lombok.AllArgsConstructor;
import org.igetwell.common.constans.SecurityConstants;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 获取客户端详情
 *
 */
@AllArgsConstructor
public class ClientDetailsServiceImpl implements IClientDetailsService {

	private final JdbcTemplate jdbcTemplate;

	@Override
	public IClientDetails loadClientByClientId(String clientId) {
		try {
			return jdbcTemplate.queryForObject(SecurityConstants.DEFAULT_SELECT_STATEMENT, new String[]{clientId}, new BeanPropertyRowMapper<>(ClientDetails.class));
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 根据租户和客户端ID查询
	 * @param tenant
	 * @param clientId
	 * @return
	 */
	@Override
	public IClientDetails loadClientByTenantClientId(String tenant, String clientId){
		try {
			return jdbcTemplate.queryForObject(SecurityConstants.DEFAULT_TENANT_SELECT_STATEMENT, new String[]{tenant, clientId}, new BeanPropertyRowMapper<>(ClientDetails.class));
		} catch (Exception ex) {
			return null;
		}
	}

}
