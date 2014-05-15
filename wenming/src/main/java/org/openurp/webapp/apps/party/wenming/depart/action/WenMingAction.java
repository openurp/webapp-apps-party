package org.openurp.webapp.apps.party.wenming.depart.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.beangle.commons.lang.Strings;
import org.beangle.ems.web.action.SecurityActionSupport;
import org.openurp.kernel.base.unit.model.Department;
import org.openurp.kernel.base.unit.model.UrpUserBean;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessState;
import org.openurp.webapp.apps.party.wenming.depart.service.WenMingService;
import org.openurp.webapp.apps.party.wenming.model.Attachment;
import org.openurp.webapp.apps.party.wenming.model.AttachmentObject;

public class WenMingAction extends SecurityActionSupport {
	
	protected WenMingService wenMingService;
	
	public void setWenMingService(WenMingService wenMingService) {
		this.wenMingService = wenMingService;
	}
	
	protected void putSchemas(){
		put("schemas", wenMingService.findSchema());
	}

	protected Department getDepartment() {
    return getUrpUser().getDepartment();
  }

	protected UrpUserBean getUrpUser() {
    return entityDao.get(UrpUserBean.class, getUserId());
  }

	protected boolean saveable(AssessState state) {
    if(AssessState.Draft.equals(state) || AssessState.Submit.equals(state)){
      return true;
    }
    return false;
  }

  protected boolean editable(AssessState state) {
    if(state == null || AssessState.Draft.equals(state) || AssessState.DepartUnpassed.equals(state) || AssessState.SchoolUnpassed.equals(state)){
      return true;
    }
    return false;
  }

	protected <T> List<?> getAll(Class<T> clazz, String index) {
    String[] indexes = getAll(index, String.class);
    List<Object> list = new ArrayList<Object>();
    if(indexes != null){
      for (String i : indexes) {
        Object ma;
        ma = populateEntity(clazz, i);
        list.add(ma);
      }
    }
    return list;
  }

	protected List<?> getAll() {
    try {
      return getAll(Class.forName(getEntityName()), "index");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }
	
	protected void setAttachment(AttachmentObject obj) {
	  Object[] files = getAll("attachment");
    if (null != files && files.length == 1 && files[0] instanceof File) {
      String[] fileNames = getAll("attachmentFileName", String.class);
      Attachment attach = new Attachment();
      String attachRoot = getConfig().get(Attachment.DIR_CONF_NAME).toString();
      attach.setName(fileNames[0]);
      attach.setFilePath("/WenMing/"+getShortName()+"/" + new SimpleDateFormat("yyyyMM").format(new Date()) + "/"
          + UUID.randomUUID()
          + Strings.substringAfterLast(fileNames[0], "."));
      if (null != obj.getAttachment() && null != obj.getAttachment().getFilePath()) {
        new File(attachRoot + obj.getAttachment().getFilePath()).delete();
      }
      try {
        FileUtils.copyFile((File) files[0], new File(attachRoot + attach.getFilePath()));
      } catch (IOException e) {
        e.printStackTrace();
      }
      obj.setAttachment(attach);
    }
  }
}
