package com.sbs.exam.board.controller;

import com.sbs.exam.board.container.Container;
import com.sbs.exam.board.dto.Member;
import com.sbs.exam.board.dto.Rq;

import java.util.ArrayList;
import java.util.List;

public class UsrMemberController {
  int memberLastId;
  List<Member> members;

  public UsrMemberController() {
    memberLastId = 0;
    members = new ArrayList<>();

    makeTestData();

    if (members.size() > 0) {
      memberLastId = members.get(members.size() - 1).getId();
    }
  }

  void makeTestData() {
    for (int i = 1; i <= 3; i++) {
      int id = i;
      members.add(new Member(id, "user" + id, "1234"));
    }
  }
  public void actionJoin(Rq rq) {
    System.out.println("== 회원 가입 ==");
    System.out.printf("로그인 아이디 : ");
    String loginId = Container.getSc().nextLine();

    if(loginId.trim().length() == 0) {
      System.out.println("로그인 아이디를 입력해주세요.");
      return;
    }

    System.out.printf("로그인 비밀번호 : ");
    String loginPw = Container.getSc().nextLine();

    if(loginPw.trim().length() == 0) {
      System.out.println("로그인 비밀번호를 입력해주세요.");
      return;
    }

    System.out.printf("로그인 비밀번호 확인 : ");
    String loginPwConfirm = Container.getSc().nextLine();

    if(loginPwConfirm.trim().length() == 0) {
      System.out.println("로그인 비밀번호 확인을 입력해주세요.");
      return;
    }

    if(loginPw.equals(loginPwConfirm) == false) {
      System.out.println("비밀번호가 일치하지 않습니다.");
      return;
    }

    int id = ++memberLastId;

    Member member = new Member(id, loginId, loginPw);
    members.add(member);

    System.out.printf("%s님 회원 가입을 환영합니다.\n", member.getLoginId());
    System.out.printf("%d번이 회원이 등록되었습니다.\n", member.getId());
  }

  public void actionLogin(Rq rq) {
    System.out.printf("로그인 아이디 : ");
    String loginId = Container.getSc().nextLine();

    if(loginId.trim().length() == 0) {
      System.out.println("로그인 아이디를 입력해주세요.");
      return;
    }

    Member member = getMemberLoginId(loginId);

    if(member == null) {
      System.out.println("해당 회원은 존재하지 않습니다.");
      return;
    }

    System.out.printf("로그인 비밀번호 : ");
    String loginPw = Container.getSc().nextLine();

    if(loginPw.trim().length() == 0) {
      System.out.println("로그인 비밀번호를 입력해주세요.");
      return;
    }

    if(member.getLoginPw().equals(loginPw) == false) {
      System.out.println("비밀번호가 일치하지 않습니다.");
      return;
    }

    rq.login(member);
    rq.setSessionAttr("loginedMember", member);

    System.out.printf("%s님 환영합니다.\n", member.getLoginId());
  }

  private Member getMemberLoginId(String loginId) {
    for(Member member : members) {
      if(member.getLoginId().equals(loginId)) {
        return member;
      }
    }
    return null;
  }

  public void actionLogout(Rq rq) {

    if(rq.isLogined() == false) {
      System.out.println("로그인 후 이용해주세요.");
      return;
    }

    rq.logout();
    System.out.println("로그아웃 되었습니다.");
  }
}
