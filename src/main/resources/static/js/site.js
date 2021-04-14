$(function () {
    $("td").on("click",".delbtn",function () {
        return confirm("确定要删除吗？")
    });
    var uids = {uids:[]};
    $(".cuid").click(function () {
        uids.uids=[];
        $(".cuid:checked").each(function () {
            uids.uids.push($(this).val());
        });
    });
    $(".delbtns").click(function () {
        if (uids.uids.length<0)return;
        if (confirm("确认删除这些选中信息吗？")){
             var json = JSON.stringify(uids);
             $("#deleteuids").val(json);
             $("#form1").attr("action","/deleteusers");
             $("#form1").submit();
        }
    })
});