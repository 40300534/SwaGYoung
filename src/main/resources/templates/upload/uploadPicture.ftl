<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/css/bootstrap.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/bootstrap-fileinput/5.0.3/css/fileinput.css" rel="stylesheet">
    <!-- 加入FileInput -->
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/js/bootstrap.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-fileinput/5.0.2/js/fileinput.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-fileinput/5.0.3/js/locales/zh.js"></script>
</head>
<body>
<h1>picture upload</h1>
<select class="form-control" id="categorys">
    <option id="0" selected>请选择分类</option>
    <#list allCategory as catName>
        <option id="${catName.getId()}">${catName.getName()}</option>
    </#list>
</select>
<br>
<table class="table table-striped">
    <thead>
    <tr>
        <th>Id</th>
        <th>产品id</th>
        <th>产品名</th>
        <th>产品图</th>
        <th>添加</th>
        <th>管理</th>
    </tr>
    </thead>
    <tbody id="productImageData">
    <#--<#list productImageInfoDtos as piid>
        <tr>${piid.productImageId}</tr>
        <tr>${piid.productId}</tr>
        <tr>${piid.productName}</tr>
        <tr>http://localhost:8087/projectx/resource/image/${piid.productImageUrl}</tr>
        <tr>
            <button type='button' class='btn btn-primary' data-toggle='modal' data-target='#exampleModal'>添加</button>
        </tr>
        <tr>
            <button type='button' class='btn btn-primary' onclick='deleteById(" + ${piid.productImageId} + ")'>删除</button>
        </tr>
    </#list>-->
    </tbody>
</table>

<!-- Button trigger modal -->
<#--<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">添加</button>-->

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="file-loading">
                    <input type="file" class="file" id="img" multiple name="images">
                </div>
                <div id="kartik-file-errors"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" title="Your custom upload logic">Save</button>
            </div>
        </div>
    </div>
</div>


</body>
<#--<form class="form" action="#" method="post" enctype="multipart/form-data" id="pollutionForm">-->
<#--    <!-- 注意事项：Input type类型为file class为样式 id随意 name随意-->
<#--         multiple（如果是要多图上传一定要加上，不加的话每次只能选中一张图）&ndash;&gt;-->
<#--    &lt;#&ndash;    <input type="hidden" id="cid" />&ndash;&gt;-->
<#--    图片：<input type="file" class="file" id="img" multiple name="images"><br>-->
<#--</form>-->

<script>
    var pid = "";

    var selectCid = $("#categorys option:selected").attr("id");

    $("#categorys").on("input", function (e) {
        getData($("#categorys option:selected").attr("id"));
    });

    function getData(selectCid) {
        $.ajax({
            type: "GET",
            url: "/picture/findPicture/" + selectCid,
            dataType: "json",
            success: function (data) {
                debugger;
                $("#productImageData").html("");
                var idHtml = "";
                var srcHtml = "";
                var manageHTml = "";
                if (data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        var id = data[i].productImageId;
                        var productId = data[i].productId;
                        var productName = data[i].productName;
                        var imageFolder = data[i].imageFolder;
                        pid = productId;
                        var imageUrl = data[i].productImageUrl;
                        if (id != null) {
                            idHtml = "<td>" + id + "</td>"
                            srcHtml = "<td><img src=http://localhost:8087/projectx/resource/image/" + imageUrl + " style=\"\n" +
                                "    width: 70px;\n" +
                                "    height: 70px;\n" +
                                "\"></td>";
                            manageHTml = "<td><button type='button' class='btn btn-primary' onclick='deleteById(" + id + ")'>删除</button></td>";
                        } else {
                            idHtml = "<td>空</td>"
                            srcHtml = "<td>暂无图片</td>";
                            manageHTml = "<td><button type='button' class='btn btn-primary' disabled>删除</button></td>";

                        }
                        $("#productImageData").append("" +
                            "<tr>" +
                            idHtml +
                            "<td>" + productId + "</td>" +
                            "<td>" + productName + "</td>" +
                            srcHtml +
                            "<td><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#exampleModal'>添加</button></td>" +
                            manageHTml +
                            "</tr>"
                        );
                    }
                } else {
                    $("#productImageData").html("<p style='text-align: center'>此分类暂无产品!</p>");
                }

            }
        })
    }

    var imageData = []; //多图上传返回的图片属性接受数组  这里是因为我在做表单其他属性提交的时候使用，在这里我没有将别的input写出来

    $("#img").fileinput({
        language: 'zh',
        uploadUrl: "/picture/image/save-test",
        showUpload: true, //是否显示上传按钮
        showRemove: true, //显示移除按钮
        showPreview: true, //是否显示预览
        showCaption: false,//是否显示标题
        autoReplace: true,
        minFileCount: 0,
        uploadAsync: true,
        maxFileCount: 10,//最大上传数量
        browseOnZoneClick: true,
        msgFilesTooMany: "选择上传的文件数量 超过允许的最大数值！",
        enctype: 'multipart/form-data',
        // overwriteInitial: false,//不覆盖已上传的图片
        allowedFileExtensions: ["jpg", "png", "gif"],
        browseClass: "btn btn-primary", //按钮样式
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        uploadExtraData: function () {
            return {
                cid: $("#categorys option:selected").attr("id"),
                pid: pid
            };
        }
    }).on("fileuploaded", function (e, data) {//文件上传成功的回调函数，还有其他的一些回调函数，比如上传之前...
        debugger;
        var res = data.response;
        console.log(res)
        imageData.push({
            "path": res.data.path,
            "date": res.data.date
        });
        console.log(imageData);
    });

    function deleteById(id) {
        if (window.confirm('你确定要删除吗？')) {
            $.ajax({
                type: "DELETE",
                url: "/picture/delete/" + id,
                dataType: "json",
                success: function (data) {
                    debugger;
                    if (data == 1) {
                        alert("删除成功");
                    } else {
                        alert("删除失败");
                    }
                }
            })
        } else {
            return false;
        }
    }


</script>
</html>