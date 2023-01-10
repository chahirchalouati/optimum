package com.crcl.authentication.repository;

import com.crcl.authentication.domain.User;
import com.crcl.authentication.dto.UserDto;
import com.crcl.authentication.mappers.UserMapper;
import com.crcl.authentication.utils.DefaultRoles;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

import static java.util.Objects.nonNull;

@Repository
@AllArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository {
    private final MongoTemplate mongoTemplate;
    private final UserMapper userMapper;
//    private final UserHelper userHelper;

    @Override
    public Page<UserDto> getAll(Pageable pageable, UserDto current) {
        Query query = getFilterByRoleCriteria(current);
        query.with(pageable);
        List<User> users = this.mongoTemplate.find(query, User.class);
        return new PageImpl<>(userMapper.mapToDto(users), pageable, users.size());
    }

    @Override
    public List<UserDto> getAll(UserDto current) {
        return userMapper.mapToDto(this.mongoTemplate.find(getFilterByRoleCriteria(current), User.class));
    }

    private Query getFilterByRoleCriteria(UserDto current) {
        Query query = new Query();
        if (nonNull(current) && !current.isSuperAdmin() && !current.isAdmin()) {
            Criteria criteria = Criteria.where("roles").nin(Set.of(DefaultRoles.ROLE_ADMIN, DefaultRoles.ROLE_SUPER_ADMIN));
            query.addCriteria(criteria);
        }
        return query;
    }


}
