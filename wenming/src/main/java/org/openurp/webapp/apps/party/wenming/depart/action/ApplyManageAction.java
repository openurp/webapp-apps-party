package org.openurp.webapp.apps.party.wenming.depart.action;

import java.util.Date;
import java.util.List;

import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.beangle.security.blueprint.User;
import org.openurp.kernel.base.unit.model.UrpUserBean;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessApply;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSession;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessState;
import org.openurp.webapp.apps.party.wenming.depart.model.SelfAssess;

/**
 * 文明办管理申请
 * 
 * @author chaostone
 */
public class ApplyManageAction extends AbstractApplyAction {

  @Override
  protected void indexSetting() {
    put("sessions", wenMingService.findAssessSessions());
    put("departments", departmentService.getActives());
    put("states", AssessState.values());
  }

  private boolean editable(AssessState state) {
    return state == AssessState.DepartApproved || state == AssessState.SchoolUnpassed;
  }

  @Override
  public String info() throws Exception {
    Long entityId = getId(getShortName(), Long.class);
    if (null != entityId) {
      AssessApply apply = entityDao.get(AssessApply.class, entityId);
      put("editable", editable(apply.getState()));
      put(getShortName(), apply);

      OqlBuilder<SelfAssess> builder = OqlBuilder.from(SelfAssess.class, "bb");
      builder.where("bb.session=:session", apply.getSession());
      builder.where("bb.department=:department", apply.getDepartment());
      List<SelfAssess> selfAssesses = entityDao.search(builder);
      if (selfAssesses.size() == 1) {
        put("selfAssess", selfAssesses.get(0));
      }
    }
    return forward();
  }

  /**
   * 同意提交，审批通过
   * 
   * @return
   */
  public String approve() {
    AssessApply apply = (AssessApply) getEntity();
    if (editable(apply.getState())) {
      apply.setState(AssessState.SchoolApproved);
      apply.setUpdatedAt(new Date());
      entityDao.saveOrUpdate(apply);
      return redirect("search", "info.save.success", "&session.id=" + apply.getSession().getId());
    } else {
      return redirect("search", "不能修该状态的申请", "&session.id=" + apply.getSession().getId());
    }
  }

  /**
   * 退回修改
   * 
   * @return
   */
  public String disApprove() {
    AssessApply apply = (AssessApply) getEntity();
    if (editable(apply.getState())) {
      apply.setState(AssessState.SchoolUnpassed);
      apply.setUpdatedAt(new Date());
      apply.setAdvise(get("advise"));
      entityDao.saveOrUpdate(apply);
      return redirect("search", "info.save.success", "&session.id=" + apply.getSession().getId());
    } else {
      return redirect("search", "不能修该状态的申请", "&session.id=" + apply.getSession().getId());
    }
  }
}
