package com.sbs.exam.board.dto;

import com.sbs.exam.board.util.Util;
import com.sbs.exam.board.container.Container;
import com.sbs.exam.board.session.Session;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
public class Rq {
  private String url;
  private Map<String, String> params;
  private String urlPath;

  public Rq(String url) {
    this.url = url;
    params = Util.getParamsForUrl(this.url);
    urlPath = Util.getUrlPathFromUrl(this.url);
  }

  public Map<String, String> getParams() {
    return params;
  }

  public String getUrlPath() {
    return urlPath;
  }

  public int getIntParam(String paramName, int defaultValue) {
    if(params.containsKey(paramName) == false) {
      return defaultValue;
    }

    try {
      return Integer.parseInt(params.get(paramName));
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  public String getParam(String paramName, String defaultValue) {
    if(params.containsKey(paramName) == false) {
      return defaultValue;
    }

    return params.get(paramName);
  }

  public void setSessionAttr(String key, Member value) {
    Session session = Container.getSession();

    session.setAttribute(key, value);
  }

  public Object getSessionAttr(String key) {
    Session session = Container.getSession();
    return session.getAttribute(key);
  }

  public void removeSessionAttr(String key) {
    Session session = Container.getSession();
    session.removeAttribute(key);
  }

  public Member getLoginedMember() {
    return (Member) getSessionAttr("loginedMember");
  }

  public boolean hasSessionAttr(String key) {
    Session session = Container.getSession();
    return session.hasAttribute(key);
  }

  public void setCommand(String url) {
    urlPath = Util.getUrlPathFromUrl(url);
    params = Util.getParamsForUrl(url);
  }

  public boolean isLogined() {
    return hasSessionAttr("loginedMember");
  }

  public void login(Member member) {
    setSessionAttr("loginedMember", member);
  }

  public void logout() {
    removeSessionAttr("loginedMember");
  }
}