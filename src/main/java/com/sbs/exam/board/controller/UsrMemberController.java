package com.sbs.exam.board.controller;

import com.sbs.exam.board.container.Container;
import com.sbs.exam.board.dto.Member;
import com.sbs.exam.board.dto.Rq;
import com.sbs.exam.board.service.MemberService;

public class UsrMemberController {

  private MemberService memberService;

  public UsrMemberController() {
    memberService = Container.getMemberService();
    memberService.makeTestData();
  }


  void makeTestData() {

  }
  public void actionJoin(Rq rq) {
    System.out.println("== 회원 가입 ==");
    System.out.printf("로그인 아이디 : ");
    String loginId = Container.getSc().nextLine();

    if(loginId.trim().length() == 0) {
      System.out.println("로그인 아이디를 입력해주세요.");
      return;
    }

    Member member = memberService.getMemberByLoginId(loginId);

    if(member != null) {
      System.out.printf("%s(은)는 이미 사용중인 아이디입니다.\n", loginId);
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

    int id = memberService.join(loginId, loginPw);

    System.out.printf("%s님 회원 가입을 환영합니다.\n", loginId);
    System.out.printf("%d번이 회원이 등록되었습니다.\n", id);
  }

  public void actionLogin(Rq rq) {
    System.out.printf("로그인 아이디 : ");
    String loginId = Container.getSc().nextLine();

    if(loginId.trim().length() == 0) {
      System.out.println("로그인 아이디를 입력해주세요.");
      return;
    }

    Member member = memberService.getMemberByLoginId(loginId);

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

  public void actionLogout(Rq rq) {

    if(rq.isLogined() == false) {
      System.out.println("로그인 후 이용해주세요.");
      return;
    }

    rq.logout();
    System.out.println("로그아웃 되었습니다.");
  }
}
