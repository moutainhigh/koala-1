package org.igetwell.system.member.service;

import org.igetwell.system.member.entity.Member;

public interface IMemberService {

    int deleteById(Long id);

    int insert(Member member);

    Member get(Long id);

    int update(Member member);
}