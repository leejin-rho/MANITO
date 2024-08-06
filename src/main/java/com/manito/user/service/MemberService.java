package com.manito.user.service;


import com.manito.user.dto.Member;

import java.sql.SQLException;

public interface MemberService {
    public boolean authenticate(String username, String password);
    public Member selectByUid(int userId) throws SQLException;
    public boolean register(Member member);
}
