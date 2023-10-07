package com.crcl.authentication.utils;

import java.util.Set;

public interface GramifyClientScopes {
    Set<String> UI_SCOPES = Set.of(
            DefaultScopes.OPENID,
            DefaultScopes.PROFILE,
            DefaultScopes.EMAIL,
            DefaultScopes.ADDRESS,
            DefaultScopes.PHONE,

            DefaultScopes.SCOPE_POST_READ,
            DefaultScopes.SCOPE_POST_WRITE,
            DefaultScopes.SCOPE_POST_DELETE,
            DefaultScopes.SCOPE_POST_MODIFY,

            DefaultScopes.SCOPE_STORAGE_READ,
            DefaultScopes.SCOPE_STORAGE_WRITE,
            DefaultScopes.SCOPE_STORAGE_DELETE,
            DefaultScopes.SCOPE_STORAGE_MODIFY,

            DefaultScopes.SCOPE_COMMENT_READ,
            DefaultScopes.SCOPE_COMMENT_WRITE,
            DefaultScopes.SCOPE_COMMENT_DELETE,
            DefaultScopes.SCOPE_COMMENT_MODIFY,

            DefaultScopes.SCOPE_MARKET_READ,
            DefaultScopes.SCOPE_MARKET_WRITE,
            DefaultScopes.SCOPE_MARKET_DELETE,
            DefaultScopes.SCOPE_MARKET_MODIFY,

            DefaultScopes.SCOPE_PROXY_READ,
            DefaultScopes.SCOPE_PROXY_WRITE,
            DefaultScopes.SCOPE_PROXY_DELETE,
            DefaultScopes.SCOPE_PROXY_MODIFY,

            DefaultScopes.SCOPE_ARCHIVE_READ,
            DefaultScopes.SCOPE_ARCHIVE_WRITE,
            DefaultScopes.SCOPE_ARCHIVE_DELETE,
            DefaultScopes.SCOPE_ARCHIVE_MODIFY,

            DefaultScopes.SCOPE_PROFILE_READ,
            DefaultScopes.SCOPE_PROFILE_WRITE,
            DefaultScopes.SCOPE_PROFILE_DELETE,
            DefaultScopes.SCOPE_PROFILE_MODIFY,

            DefaultScopes.SCOPE_AUDIT_READ,
            DefaultScopes.SCOPE_AUDIT_WRITE,
            DefaultScopes.SCOPE_AUDIT_DELETE,
            DefaultScopes.SCOPE_AUDIT_MODIFY
    );
}
