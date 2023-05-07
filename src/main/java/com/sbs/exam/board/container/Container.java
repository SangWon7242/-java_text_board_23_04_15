package com.sbs.exam.board.container;

import com.sbs.exam.board.controller.UsrArticleController;
import com.sbs.exam.board.controller.UsrMemberController;
import com.sbs.exam.board.dto.Member;
import com.sbs.exam.board.repository.ArticleRepository;
import com.sbs.exam.board.repository.MemberRepository;
import com.sbs.exam.board.service.ArticleService;
import com.sbs.exam.board.service.MemberService;
import com.sbs.exam.board.session.Session;
import lombok.Getter;

import java.util.Scanner;

public class Container {
  @Getter
  private static Scanner sc;
  @Getter
  private static Session session;

  @Getter
  private static ArticleRepository articleRepository;
  @Getter
  private static MemberRepository memberRepository;

  @Getter
  private static ArticleService articleService;
  @Getter
  private static MemberService memberService;

  @Getter
  private static UsrArticleController usrArticleController;
  @Getter
  private static UsrMemberController usrMemberController;

  static {
    sc = new Scanner(System.in);
    session = new Session();

    articleRepository = new ArticleRepository();
    memberRepository = new MemberRepository();

    articleService = new ArticleService();
    memberService = new MemberService();

    usrMemberController = new UsrMemberController();
    usrArticleController = new UsrArticleController();
  }

  public static Session getSession() {
    return session;
  }
}
