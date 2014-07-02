package org.openurp.webapp.apps.party.wenming.post;

import org.beangle.commons.inject.bind.AbstractBindModule;
import org.openurp.webapp.apps.party.wenming.post.action.ApplyAction;
import org.openurp.webapp.apps.party.wenming.post.action.AuditAction;
import org.openurp.webapp.apps.party.wenming.post.action.ManageAction;
import org.openurp.webapp.apps.party.wenming.post.action.VoteAction;
import org.openurp.webapp.apps.party.wenming.post.action.VoteManageAction;

public class DefaultModule extends AbstractBindModule {

  @Override
  protected void doBinding() {
    bind(ApplyAction.class, AuditAction.class, ManageAction.class,VoteAction.class,VoteManageAction.class);
  }
}
