package com.crcl.authentication.utils;

import com.crcl.authentication.domain.GramifyPermission;
import com.crcl.authentication.domain.Role;

import java.util.Set;

import static com.crcl.authentication.utils.DefaultPermissions.*;

public class RoleUtils {
    public static Set<Role> getDefaultUserRoles() {
        return Set.of(
                new Role(DefaultRoles.ROLE_USER)
                        .setPermissions(Set.of(

                                new GramifyPermission(PERMISSION_BUY_READ, true),
                                new GramifyPermission(PERMISSION_BUY_WRITE, true),
                                new GramifyPermission(PERMISSION_BUY_DELETE, true),
                                new GramifyPermission(PERMISSION_BUY_MODIFY, true),
                                new GramifyPermission(PERMISSION_BUY_VIEW, true),

                                new GramifyPermission(PERMISSION_CALL_READ, true),
                                new GramifyPermission(PERMISSION_CALL_WRITE, true),
                                new GramifyPermission(PERMISSION_CALL_DELETE, true),
                                new GramifyPermission(PERMISSION_CALL_MODIFY, true),
                                new GramifyPermission(PERMISSION_CALL_VIEW, true),

                                new GramifyPermission(PERMISSION_TAG_READ, true),
                                new GramifyPermission(PERMISSION_TAG_WRITE, true),
                                new GramifyPermission(PERMISSION_TAG_DELETE, true),
                                new GramifyPermission(PERMISSION_TAG_MODIFY, true),
                                new GramifyPermission(PERMISSION_TAG_VIEW, true),

                                new GramifyPermission(PERMISSION_POST_READ, true),
                                new GramifyPermission(PERMISSION_POST_WRITE, true),
                                new GramifyPermission(PERMISSION_POST_DELETE, true),
                                new GramifyPermission(PERMISSION_POST_MODIFY, true),
                                new GramifyPermission(PERMISSION_POST_VIEW, true),

                                new GramifyPermission(PERMISSION_COMMENT_READ, true),
                                new GramifyPermission(PERMISSION_COMMENT_WRITE, true),
                                new GramifyPermission(PERMISSION_COMMENT_DELETE, true),
                                new GramifyPermission(PERMISSION_COMMENT_MODIFY, true),
                                new GramifyPermission(PERMISSION_COMMENT_VIEW, true),

                                new GramifyPermission(PERMISSION_LIKE_READ, true),
                                new GramifyPermission(PERMISSION_LIKE_WRITE, true),
                                new GramifyPermission(PERMISSION_LIKE_DELETE, true),
                                new GramifyPermission(PERMISSION_LIKE_MODIFY, true),
                                new GramifyPermission(PERMISSION_LIKE_VIEW, true),

                                new GramifyPermission(PERMISSION_FRIEND_READ, true),
                                new GramifyPermission(PERMISSION_FRIEND_WRITE, true),
                                new GramifyPermission(PERMISSION_FRIEND_DELETE, true),
                                new GramifyPermission(PERMISSION_FRIEND_MODIFY, true),
                                new GramifyPermission(PERMISSION_FRIEND_VIEW, true)
                        ))
        );
    }
}
