package org.openurp.webapp.apps.party.wenming.model;

import java.util.Date;

import org.beangle.commons.entity.pojo.NumberIdTimeObject;
import org.beangle.security.blueprint.User;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessState;

abstract public class AbstractWenmingFinalSummaryObject<T> extends AbstractWenmingSummaryObject<T>{

  /**项目内容**/
  private String content;

  /**是否参加评选**/
  private boolean forAssess;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public boolean isForAssess() {
    return forAssess;
  }

  public void setForAssess(boolean forAssess) {
    this.forAssess = forAssess;
  }
  

}
