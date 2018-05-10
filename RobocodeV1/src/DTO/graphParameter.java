package DTO;

public class graphParameter {

	private static String accessToken = null;
	private static String dataContractVersion = null;
	private static String tenantDomainName = null;
	private static String protectedResourceHostName = null;
	private static String protectedResourcePrincipalId = null;
	private static String restServiceHost = null;
	private static String appPrincipalId = null;
	private static String acsPrincipalId = null;
	private static String symmetricKey = null;
	private static String tenantContextId = null;
	private static String evoStsUrl = null;
	public static final String PROTOCOL_NAME = "https";
	public static final String AUTHORIZATION_HEADER = "Authorization";
	
	public static String getAccessToken() {
		return accessToken;
	}
	public static void setAccessToken(String accessToken) {
		graphParameter.accessToken = accessToken;
	}
	
	public static String getAuthorizationHeader() {
		return AUTHORIZATION_HEADER;
	}
	public static String getDataContractVersion() {
		return dataContractVersion;
	}
	public static void setDataContractVersion(String dataContractVersion) {
		graphParameter.dataContractVersion = dataContractVersion;
	}
	public static String getTenantDomainName() {
		return tenantDomainName;
	}
	public static void setTenantDomainName(String tenantDomainName) {
		graphParameter.tenantDomainName = tenantDomainName;
	}
	public static String getProtectedResourceHostName() {
		return protectedResourceHostName;
	}
	public static void setProtectedResourceHostName(String protectedResourceHostName) {
		graphParameter.protectedResourceHostName = protectedResourceHostName;
	}
	public static String getProtectedResourcePrincipalId() {
		return protectedResourcePrincipalId;
	}
	public static void setProtectedResourcePrincipalId(
			String protectedResourcePrincipalId) {
		graphParameter.protectedResourcePrincipalId = protectedResourcePrincipalId;
	}
	public static String getRestServiceHost() {
		return restServiceHost;
	}
	public static void setRestServiceHost(String restServiceHost) {
		graphParameter.restServiceHost = restServiceHost;
	}
	public static String getAppPrincipalId() {
		return appPrincipalId;
	}
	public static void setAppPrincipalId(String appPrincipalId) {
		graphParameter.appPrincipalId = appPrincipalId;
	}
	public static String getAcsPrincipalId() {
		return acsPrincipalId;
	}
	public static void setAcsPrincipalId(String acsPrincipalId) {
		graphParameter.acsPrincipalId = acsPrincipalId;
	}
	public static String getSymmetricKey() {
		return symmetricKey;
	}
	public static void setSymmetricKey(String symmetricKey) {
		graphParameter.symmetricKey = symmetricKey;
	}
	public static String getTenantContextId() {
		return tenantContextId;
	}
	public static void setTenantContextId(String tenantContextId) {
		graphParameter.tenantContextId = tenantContextId;
	}
	public static String getEvoStsUrl() {
		return evoStsUrl;
	}
	public static void setEvoStsUrl(String evoStsUrl) {
		graphParameter.evoStsUrl = evoStsUrl;
	}
	public static String getProtocolName() {
		return PROTOCOL_NAME;
	}
	
	
	
	
}
