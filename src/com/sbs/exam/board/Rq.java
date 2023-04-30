package com.sbs.exam.board;

import java.util.Map;
public class Rq {
  private String url;
  private Map<String, String> params;
  private String urlPath;

  Rq(String url) {
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
}