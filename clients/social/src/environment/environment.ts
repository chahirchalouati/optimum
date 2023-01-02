export const environment = {
    production: false,
    oauthCallbackUrl: "http://127.0.0.1:4200/authorized",
    oauthAuthorizeUrl: "http://auth-server:9000/oauth2/authorize",
    oauthClientId: "ui-client",
    oauthTokenUrl: "idp/oauth2/token",
    oauthClientSecret: "secret",
    scope: 'openid',
    grantType: 'authorization_code',
    responseType: 'code',
    api: {
      audit: {
        AUDIT_GET_PAGE: "audit/audits",
        AUDIT_GET_BY_ID: "audit/audits/:id",
        AUDIT_POST: "audit/audits",
        AUDIT_PUT: "audit/audits/:id",
        AUDIT_DELETE: "audit/audits/:id"
      },
      user: {
        USER_GET_PAGE: "idp/users",
        USER_GET_BY_ID: "idp/users/:id",
        USER_POST: "idp/users",
        USER_PUT: "idp/users/:id",
        USER_DELETE: "idp/users/:id",
      },
      profile: {
        PROFILE_GET_PAGE: "profile/profiles",
        PROFILE_GET_BY_ID: "profile/profiles/:id",
        PROFILE_POST: "profile/profiles",
        PROFILE_PUT: "profile/profiles/:id",
        PROFILE_DELETE: "profile/profiles/:id",
        PROFILE_GET_USER: "profile/profiles/profile/username"

      },
      post: {
        POST_GET_PAGE: "post/posts",
        POST_GET_BY_ID: "post/posts/:id",
        POST_POST: "post/posts",
        POST_PUT: "post/posts/:id",
        POST_DELETE: "post/posts/:id",
      },
      comment: {
        COMMENT_GET_PAGE: "comment/comments",
        COMMENT_GET_BY_ID: "comment/comments/:id",
        COMMENT_POST: "comment/comments",
        COMMENT_PUT: "comment/comments/:id",
        COMMENT_DELETE: "comment/comments/:id",
      },
      file: {
        FILES_GET_ONE: "storage/files"

      }

    },
    proxyUrl: "http://localhost:9999",
    logo: "SOCIAL APP"
  }
;
