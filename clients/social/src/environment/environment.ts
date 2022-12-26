export const environment = {
  production: false,
  oauthCallbackUrl: "http://127.0.0.1:4200/authorized",
  oauthAuthorizeUrl: "http://auth-server:9000/oauth2/authorize",
  oauthClientId: "ui-client",
  oauthTokenUrl: "http://auth-server:9000/oauth2/token",
  oauthClientSecret: "secret",
  scope: 'openid',
  grantType: 'authorization_code',
  responseType: 'code',
  api: {
    audit: {
      GET_AUDIT_PAGE: ""

    }

  }
};
