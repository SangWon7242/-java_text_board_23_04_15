package com.sbs.exam.board.service;

import com.sbs.exam.board.container.Container;
import com.sbs.exam.board.dto.Member;
import com.sbs.exam.board.repository.MemberRepository;


public class MemberService {

  private MemberRepository memberRepository;
  public MemberService() {
    memberRepository = Container.getMemberRepository();
  }
  public void makeTestData() {
    for (int i = 0; i < 3; i++) {
      String loginId = "user" + (i + 1);
      String loginPw = loginId;

      join(loginId, loginPw);
    }
  }

  public int join(String loginId, String loginPw) {
    return memberRepository.join(loginId, loginPw);
  }

  public Member getMemberByLoginId(String loginId) {
    return memberRepository.getMemberByLoginId(loginId);
  }
}
