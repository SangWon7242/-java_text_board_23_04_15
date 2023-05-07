package com.sbs.exam.board.app;

import com.sbs.exam.board.container.Container;
import com.sbs.exam.board.dto.Member;
import com.sbs.exam.board.dto.Rq;
import com.sbs.exam.board.session.Session;

import javax.sound.midi.MetaMessage;
import java.util.Scanner;

public class App {
  public void run() {
    Scanner sc = Container.getSc();

    System.out.println("== 게시판 v 0.1 ==");
    System.out.println("== 프로그램 시작 ==");

    while (true) {
      Rq rq = new Rq();

      String promptName = "명령";

      if(rq.isLogined()) {
        Member loginedMember = rq.getLoginedMember();
        promptName = loginedMember.getLoginId();
      }

      System.out.printf("%s) ", promptName);

      String cmd = Container.getSc().nextLine();
      rq.setCommand(cmd);

      if (rq.getUrlPath().equals("exit")) {
        break;
      } else if (rq.getUrlPath().equals("/usr/article/list")) {
        Container.getUsrArticleController().showList(rq);
      } else if (rq.getUrlPath().equals("/usr/article/write")) {
        Container.getUsrArticleController().actionWrite(rq);
      } else if (rq.getUrlPath().equals("/usr/article/detail")) {
        Container.getUsrArticleController().showDetail(rq);
      } else if (rq.getUrlPath().equals("/usr/article/modify")) {
        Container.getUsrArticleController().actionModify(rq);
      } else if (rq.getUrlPath().equals("/usr/article/delete")) {
        Container.getUsrArticleController().actionDelete(rq);
      } else if (rq.getUrlPath().equals("/usr/member/join")) {
        Container.getUsrMemberController().actionJoin(rq);
      } else if (rq.getUrlPath().equals("/usr/member/login")) {
        Container.getUsrMemberController().actionLogin(rq);
      } else if (rq.getUrlPath().equals("/usr/member/logout")) {
        Container.getUsrMemberController().actionLogout(rq);
      } else {
        System.out.printf("입력된 명령어 : %s\n", cmd);
      }

    }
    System.out.println("== 프로그램 종료 ==");

    sc.close();
  }

}
