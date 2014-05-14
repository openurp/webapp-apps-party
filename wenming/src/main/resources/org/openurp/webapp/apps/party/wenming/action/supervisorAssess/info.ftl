[#ftl]
[@b.head/]
[@b.toolbar title='评测内容'][/@]
[@b.form action="!edit"]
  <table id="assessTable" class="gridtable assessTable">
    <thead>
      <tr>
        <th>被评分单位/指标</th>
        [#list malist[0].items?sort_by(["item", "id"]) as v]
        <th>${v.item.content!}</th>
        [/#list]
      </tr>
    </thead>
    <tbody>
      [#list malist as ma]
        <tr>
          <td>${ma.department.name}</td>
          [#list ma.items?sort_by(["item", "id"]) as item]
          <td>
            <span class="scoreSpan">${item.score}</span>
            <div class="scoreDiv" max="${item.item.score}" score="${item.score}"></div>
          </td>
          [/#list]
        </tr>
      [/#list]
    </tbody>
  </table>
  [#if editable??]
    <div style="text-align:center; padding:30px;" class="footdiv">
      <input type="hidden" name="schema.id" value="${malist[0].schema.id}"/>
      [@b.submit value="修改"/]
    </div>
  [/#if]
[/@]
<script>
  jQuery.struts2_jquery.require("/js/base/jquery-ui.js",null,bg.getContextPath() + "/static");
  jQuery.struts2_jquery.requireCss("/css/party/main.css",bg.getContextPath() + "/static");
  $(function (){
    $(".scoreDiv").each(function (){
      var div = $(this);
      div.slider({
        disabled:true,
        orientation: "horizontal",
        range: "min",
        step: 0.5,
        max: div.attr("max")*1,
        value: div.attr("score")*1
      });
    });
  });
</script>
[@b.foot/]