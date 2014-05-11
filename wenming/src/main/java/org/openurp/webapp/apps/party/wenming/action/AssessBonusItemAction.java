package org.openurp.webapp.apps.party.wenming.action;

import java.util.List;

import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.beangle.commons.entity.Entity;
import org.openurp.webapp.apps.party.wenming.model.AssessBonusItem;
import org.openurp.webapp.apps.party.wenming.model.AssessBonusType;

 /**
  * 
  * 测评加分项目
  * 
  * @author xinzhou
  * 
  * **/
public class AssessBonusItemAction extends WenMingAction{
  @Override
  protected String getEntityName() {
    return AssessBonusItem.class.getName();
  }
  @Override
  protected void indexSetting() {
    putSchemas();
    super.indexSetting();
  }
  
  @Override
  protected void editSetting(Entity<?> entity) {
    putSchemas();
    OqlBuilder<AssessBonusType> query = OqlBuilder.from(AssessBonusType.class);
    query.orderBy("name");
    List<AssessBonusType> bonusTypes = entityDao.search(query);
    put("bonusTypes", bonusTypes);
    super.editSetting(entity);
  }
  
  
  @Override
  protected String saveAndForward(Entity<?> entity) {
    AssessBonusItem bonusType = (AssessBonusItem) entity;
    return super.saveAndForward(entity);
  }
  
}
