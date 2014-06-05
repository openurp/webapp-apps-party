[#ftl]
[#include "/org/openurp/webapp/apps/party/wenming/comm.ftl"/]
[@b.head/]
[@b.grid  items=goodPersons var="goodPerson"]
  [@b.gridbar]
    [#if editable]
    bar.addItem("审核",action.edit());
    [/#if]
  [/@]
  [@b.row]
    [@b.boxcol /]
    [@b.col width="50%" property="name" title="项目名称" align="left"][@b.a href="!info?id=${goodPerson.id}"]${goodPerson.name}[/@][/@]
    [@b.col width="20%" property="department.name" title="选送单位"][/@]
    [@b.col width="15%" property="attachment.name" title="详细材料"]
      [#if goodPerson.attachment??]
      [@b.a target="_blank" href="../attachment?path=${goodPerson.attachment.filePath}&name=${goodPerson.attachment.name?url('utf-8')}"]下载[/@]
      [/#if]
    [/@]
    [@b.col width="15%" property="state" title="审核情况"]${goodPerson.state.description}[/@]
  [/@]
[/@]
[@b.foot/]