package com.crcl.authentication.utils;

public interface DefaultScopes {
    // openid scopes
    String OPENID = "openid";
    String PROFILE = "profile";
    String EMAIL = "email";
    String ADDRESS = "address";
    String PHONE = "phone";
    // scopes for POST
    String SCOPE_POST_READ = "post.read";
    String SCOPE_POST_WRITE = "post.write";
    String SCOPE_POST_DELETE = "post.delete";
    String SCOPE_POST_MODIFY = "post.modify";
    // scopes for STORAGE
    String SCOPE_STORAGE_READ = "storage.read";
    String SCOPE_STORAGE_WRITE = "storage.write";
    String SCOPE_STORAGE_DELETE = "storage.delete";
    String SCOPE_STORAGE_MODIFY = "storage.modify";
    // scopes for COMMENT
    String SCOPE_COMMENT_READ = "comment.read";
    String SCOPE_COMMENT_WRITE = "comment.write";
    String SCOPE_COMMENT_DELETE = "comment.delete";
    String SCOPE_COMMENT_MODIFY = "comment.modify";
    // scopes for MARKET
    String SCOPE_MARKET_READ = "market.read";
    String SCOPE_MARKET_WRITE = "market.write";
    String SCOPE_MARKET_DELETE = "market.delete";
    String SCOPE_MARKET_MODIFY = "market.modify";
    // scopes for PROXY
    String SCOPE_PROXY_READ = "proxy.read";
    String SCOPE_PROXY_WRITE = "proxy.write";
    String SCOPE_PROXY_DELETE = "proxy.delete";
    String SCOPE_PROXY_MODIFY = "proxy.modify";
    // scopes for ARCHIVE
    String SCOPE_ARCHIVE_READ = "archive.read";
    String SCOPE_ARCHIVE_WRITE = "archive.write";
    String SCOPE_ARCHIVE_DELETE = "archive.delete";
    String SCOPE_ARCHIVE_MODIFY = "archive.modify";
    // scopes for PROFILE
    String SCOPE_PROFILE_READ = "profile.read";
    String SCOPE_PROFILE_WRITE = "profile.write";
    String SCOPE_PROFILE_DELETE = "profile.delete";
    String SCOPE_PROFILE_MODIFY = "profile.modify";

    // scopes for AUDIT
    String SCOPE_AUDIT_READ = "audit.read";
    String SCOPE_AUDIT_WRITE = "audit.write";
    String SCOPE_AUDIT_DELETE = "audit.delete";
    String SCOPE_AUDIT_MODIFY = "audit.modify";

    // scopes for USER
    String SCOPE_USER_READ = "user.read";
    String SCOPE_USER_WRITE = "user.write";
    String SCOPE_USER_DELETE = "user.delete";
    String SCOPE_USER_MODIFY = "user.modify";
}
