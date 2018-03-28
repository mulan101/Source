package com.axboot.jeho.domain.user.auth;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAuthRepository extends AXBootJPAQueryDSLRepository<UserAuth, UserAuthId> {

    List<UserAuth> findByUserCd(String userCd);
}
