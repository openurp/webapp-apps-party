[#ftl]
[@b.head/]
[@b.toolbar title="考核批次编辑"]bar.addBack();[/@]
[@b.form action="!save" theme="list"]
  [#include "editField.ftl"/] 
  [@b.formfoot]
    <input type="hidden" name="goodProject.id" value="${goodProject.id!}" />
    <input type="hidden" name="save" value="0" id="saveIpt"/>
    [@b.reset/]
    [@b.submit value="action.save" onsubmit="saveForm"/]
    [@b.submit value="action.submit" onsubmit="submitForm"/]
  [/@]
[/@]
<script>
  function saveForm(){
    $("#saveIpt").val("1");
    return true;
  }
  
  function submitForm(){
    return confirm("提交后不能修改，是否确定提交？")
  }
</script>
[@b.foot/]