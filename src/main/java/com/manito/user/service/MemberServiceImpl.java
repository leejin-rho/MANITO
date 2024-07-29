package com.manito.user.service;

import com.manito.user.dao.MemberDao;
import com.manito.user.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public boolean authenticate(String loginId, String password) {
        try {
            Member member = memberDao.selectByLoginId(loginId);

            // 아이디를 찾고, 이후 비밀번호 비교 !
            if (member != null && member.getPassword().equals(password)) {
                System.out.println("Success Login");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
