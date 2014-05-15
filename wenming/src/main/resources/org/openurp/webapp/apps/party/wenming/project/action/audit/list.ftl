[#ftl]
[#include "/org/openurp/webapp/apps/party/wenming/comm.ftl"/]
[@b.head/]
[@b.grid  items=goodProjects var="goodProject" sortable="false"]
  [@b.gridbar]
    [#if editable]
    bar.addItem("审核",action.edit());
    [/#if]
  [/@]
  [@b.row]
    [@b.boxcol /]
    [@b.col width="50%" property="name" title="项目名称" align="left"][@b.a href="!info?id=${goodProject.id}"]${goodProject.name}[/@][/@]
    [@b.col width="20%" property="department.name" title="选送单位"][/@]
    [@b.col width="15%" property="attachment.name" title="详细材料"]
      [@b.a target="_blank" href="../attachment?path=${goodProject.attachment.filePath}&name=${goodProject.attachment.name?url('utf-8')}"]${(goodProject.attachment.name)!}[/@]
    [/@]
    [@b.col width="15%" property="state.description" title="审核情况"][/@]
  [/@]
[/@]
[@b.foot/]