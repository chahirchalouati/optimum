export type NavItem = { name: string, link: string };

export interface Environment {
  createPostPlaceholder: string;
  maxContentSize: number;
  production: boolean;
  oauthCallbackUrl: string;
  oauthAuthorizeUrl: string;
  oauthClientId: string;
  oauthTokenUrl: string;
  oauthClientSecret: string;
  scope: string;
  grantType: string;
  responseType: string;
  api: {
    file: { FILES_GET_ONE: string };
    post: {
      POST_POST: string;
      POST_GET_PAGE: string;
      POST_DELETE: string;
      POST_PUT: string;
      POST_GET_BY_ID: string
    };
    audit: {
      AUDIT_PUT: string;
      AUDIT_GET_PAGE: string;
      AUDIT_DELETE: string;
      AUDIT_POST: string;
      AUDIT_GET_BY_ID: string
    };
    profile: {
      PROFILE_GET_USER: string;
      PROFILE_POST: string;
      PROFILE_GET_PAGE: string;
      PROFILE_PUT: string;
      PROFILE_GET_BY_ID: string;
      PROFILE_DELETE: string
    };
    comment: {
      COMMENT_GET_PAGE: string;
      COMMENT_GET_BY_ID: string;
      COMMENT_PUT: string;
      COMMENT_POST: string;
      COMMENT_DELETE: string
    };
    user: {
      USER_POST: string;
      USER_GET_BY_ID: string;
      USER_DELETE: string;
      USER_PUT: string;
      USER_GET_PAGE: string
    }
  };
  proxyUrl: string;
  appName: string;
  logoImage: string;
  navItems: NavItem[];
}

export const environment: Environment = {
    createPostPlaceholder: 'What\'s on your mind, %s?',
    maxContentSize: 200,
    production: false,
    oauthCallbackUrl: "http://127.0.0.1:4200/authorized",
    oauthAuthorizeUrl: "http://auth-server:9000/oauth2/authorize",
    oauthClientId: "ui-service",
    oauthTokenUrl: "authentication/oauth2/token",
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
        USER_GET_PAGE: "authentication/users",
        USER_GET_BY_ID: "authentication/users/:id",
        USER_POST: "authentication/users",
        USER_PUT: "authentication/users/:id",
        USER_DELETE: "authentication/users/:id",
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
        FILES_GET_ONE: "processor/files"

      }

    },
    proxyUrl: "http://proxy-service:9999",
    appName: "gramify",
    logoImage: '<svg preserveAspectRatio="xMidYMid meet" version="1.0" viewBox="0 0 48.000000 48.000000" width="48.000000pt" xmlns="http://www.w3.org/2000/svg"> <g fill="none" stroke="none" transform="translate(0.000000,48.000000) scale(0.100000,-0.100000)"> <path d="M291 467 c-29 -15 -50 -59 -23 -50 26 9 39 22 45 43 3 11 4 20 4 20 -1 0 -13 -6 -26 -13z" fill="blue"/> <path d="M205 365 c-14 -13 -25 -31 -25 -38 0 -8 -18 -37 -40 -63 -54 -66 -67 -110 -46 -155 23 -49 50 -62 136 -64 92 -3 132 13 155 62 23 47 10 91 -45 157 -22 26 -40 55 -40 63 0 16 -44 63 -60 63 -6 0 -22 -11 -35 -25z" fill="blue"/> </g> </svg>',
    navItems: [
      {
        name: 'Home',
        link: '/home'
      },
      {
        name: 'Video',
        link: '/video'
      },
      {
        name: 'MarketPlace',
        link: '/marketplace'
      }
    ]
  }
;

