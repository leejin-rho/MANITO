package com.manito.user.service;

import com.manito.user.dao.MemberDao;
import com.manito.user.dto.Member;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private HttpSession session;

    @Override
    public boolean authenticate(String loginId, String password) {
        try {
            Member member = memberDao.selectByLoginId(loginId);

            // 아이디를 찾고, 이후 비밀번호 비교 !
            if (member != null && member.getPassword().equals(password)) {
                System.out.println("Success Login");
                session.setAttribute("user", member);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Member selectByUid(int userId) throws SQLException {
        return memberDao.selectByUserId(userId);
    }

    @Override
    public boolean register(Member member) {
        try {
            int rowsInserted = memberDao.signUp(member);
            // rowsInserted => 데이터 베이스에 들어갔는지 확인
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}


