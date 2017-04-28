/**
 * Created by pengC on 2017/4/13.
 */
function show(){
    var date = new Date();
    var now = "";
    now = date.getFullYear()+"-";
    now = now + (date.getMonth()+1)+"-";
    now = now + date.getDate()+"&nbsp&nbsp&nbsp&nbsp";
    now = now + date.getHours()+":";
    now = now + date.getMinutes()+":";
    now = now + date.getSeconds();
    document.getElementById("nowtime").innerHTML = now;
    setTimeout("show()",1000); //设置过1000毫秒就是1秒，调用show方法
}

function showAppListInfo(){
    $("#infoCheckTbody").html("");  //清空之前的数据
    $.ajax({
        url: 'http://localhost:8080/vm/access/apps/query',
        //   url: 'http://10.103.247.43:8080/vm/statics/student/1',
        datatype: "json",
        type: 'get',
        //     async:false,
        contentType: "application/json",
        success: function (e,state) {
            //成功后回调
            // alert("成功返回的数据：");
            var str = "";
            for(i in e){
                str += "<tr><td>" +  e[i].appId + "</td><td>" +  e[i].appName + "</td><td>" +  e[i].appKey+ "</td><td>" +  e[i].registry + "</td> <td>"  +  e[i].registrationDate + "</td><td>" +  e[i].expDate + "</td><td>"+ e[i].atl + "</td></tr>";

                //str += "<tr><td>" + e[i].id + "</td><td>" + e[i].name + "</td><td>" + e[i].clsId+ "</td><td>" +  e[i].sex + "</td><td>" +  e[i].age + "</td></tr>";

            }
            $("#infoCheckTbody").append(str);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

$(document).ready(function() {
    show();
    showAppListInfo();
 
    $("#registityButton").click(function(){
        $.ajax({
            cache: true,
            type: "put",
            url:'http://localhost:8080/vm/access/apps/query',
            data:$('#registityForm').serialize(),// 你的formid
            async: false,
            error: function(request) {
                alert("Connection error");
            },
            success: function(data) {
                //alert("应用注册成功！"+data.appId);
            	 alert("应用注册成功");
            window.location.href="http://localhost:8080/ui/login/mainHtml.html"  ;
            }
        });
    });

    $("#updateButton").click(function(){
        $.ajax({
            cache: true,
            type: "post",
            url:'http://localhost:8080/vm/access/apps/query',
            data:$('#updateForm').serialize(),// 你的formid
            async: false,
            error: function(request) {
                alert("Connection error");
            },
            success: function(data) {
               // alert("应用修改！"+data.appId);
            	alert("应用修改成功");
               window.location.href="http://localhost:8080/ui/login/mainHtml.html"  ;
            }
        });
    });

    $("#deleteButton").click(function(){
        // var deleteAppId=$("deleteForm").deleteAppId.value;
        // alert("删除成功！"+deleteAppId);
        var deleteAppId = document.forms["deleteForm"]["deleteAppId"].value;
        //alert("删除成功！"+typeof deleteAppId);
        $.ajax({
            url:" http://localhost:8080/vm/access/app/"+deleteAppId,
            type:"delete",
            success:function(data){
                //alert("应用删除成功"+data);
            	alert("应用删除成功");
           window.location.href="http://localhost:8080/ui/login/mainHtml.html"  ;
            },
            error:function(request){
                alert("注销失败");
            }
        });
    });

    $("#submitButton").click(function () {
        var arrCheckBox = document.getElementsByName("permissionSet");
        checkData = [];
        for (i = 0; i < arrCheckBox.length; i++) {
            if (arrCheckBox[i].checked) {
                checkData.push(arrCheckBox[i].value);
            }
        }
        var JsonDate = JSON.stringify(checkData);
        var appid = document.getElementById("permissionAppID1").value;
        $.ajax({
            url: "http://localhost:8080/vm/access/permission/" + appid,
            type: "put",
            data: JsonDate,
            datatype: "json",
            success: function (data) {
                //var resultStr=JSON.parse(data);
               // alert("初始化成功" + data);
            	alert("初始化成功" );
                window.location.href="http://localhost:8080/ui/login/mainHtml.html"  ;
            },
            error: function (request) {
                alert("submitButton ajax error");
            }
        });
    });

    $("#checkButton").click(function(){
        var  appid=document.getElementById("permissionAppID2").value;
        //alert("appidCheck:  "  +appid);
        $.ajax({
            url:"http://localhost:8080/vm/access/permission/"+appid,
            type:"get",
            datatype:"json",
            success:function(data){
                //var resultStr=JSON.parse(data);
                //	alert("查询成功"+data);
                $("#perCheckTbody").html("");  //清空之前的数据
                var str="<br><p>查询结果如下：</P><ul>";
                for(i in data){
                    str +="<li>"+data[i]+"</li>";
                }
                str +="</ul>";
                //	alert(str);
                $("#perCheckTbody").append(str);
            },
            error:function(request){
                alert("checkButton   ajax error");
            }
        });
    });

    $("#addButton").click(function(){
        var appid=document.getElementById("permissionAppID3").value;
        var arrCheckBox=document.getElementsByName("permissionSetReload");
        $.ajax({
            url:"http://localhost:8080/vm/access/permission/"+appid,
            type:"get",
            datatype:"json",
            success:function(data){
                //alert("addButton查询成功"+data);
                var str=[];
                for(i in data){
                    str[i]=data[i];
                    switch(str[i]){
                        case "read_topology":
                            arrCheckBox[0].checked=true;
                            arrCheckBox[0].disabled=true;
                            break;
                        case "read_all_flow" :
                            arrCheckBox[1].checked=true;
                            arrCheckBox[1].disabled=true;
                            break;
                        case "read_statistics":
                            arrCheckBox[2].checked=true;
                            arrCheckBox[2].disabled=true;
                            break;
                        case "read_pkt_in_payload":
                            arrCheckBox[3].checked=true;
                            arrCheckBox[3].disabled=true;
                            break;
                        case "read_controller_info":
                            arrCheckBox[4].checked=true;
                            arrCheckBox[4].disabled=true;
                            break;
                        case "pkt_in_event":
                            arrCheckBox[5].checked=true;
                            arrCheckBox[5].disabled=true;
                            break;
                        case "flow_removed_event":
                            arrCheckBox[6].checked=true;
                            arrCheckBox[6].disabled=true;
                            break;
                        case "error_event":
                            arrCheckBox[7].checked=true;
                            arrCheckBox[7].disabled=true;
                            break;
                        case "flow_mod_route":
                            arrCheckBox[8].checked=true;
                            arrCheckBox[8].disabled=true;
                            break;
                        case "flow_mod_drop":
                            arrCheckBox[9].checked=true;
                            arrCheckBox[9].disabled=true;
                            break;
                        case "set_flow_priority":
                            arrCheckBox[10].checked=true;
                            arrCheckBox[10].disabled=true;
                            break;
                        case "set_devices_config":
                            arrCheckBox[11].checked=true;
                            arrCheckBox[11].disabled=true;
                            break;
                        case "set_pkt_out":
                            arrCheckBox[12].checked=true;
                            arrCheckBox[12].disabled=true;
                            break;
                        case "flow_mod_modify_hdr":
                            arrCheckBox[13].checked=true;
                            arrCheckBox[13].disabled=true;
                            break;
                        case "modify_all_flows":
                            arrCheckBox[14].checked=true;
                            arrCheckBox[14].disabled=true;
                            break;
                    }
                }
                document.getElementById("permissionAdd").style.visibility="visible";
            },
            error:function(request){
                alert("addButton ajax error");
            }
        });
    });

    $("#ackAddButton").click(function(){
        var appid=document.getElementById("permissionAppID3").value;
        var arrCheckBox=document.getElementsByName("permissionSetReload");
        checkData =[];
        for(i=0;i<arrCheckBox.length;i++){
            if((arrCheckBox[i].checked==true)&&(arrCheckBox[i].disabled==false)){
                checkData.push(arrCheckBox[i].value);
            }
        }
        var JsonDate=JSON.stringify(checkData);
        $.ajax({
            url:"http://localhost:8080/vm/access/permission/"+appid,
            type:"post",
            data:JsonDate,
            datatype:"json",
            success:function(data){
                //var resultStr=JSON.parse(data);
              //  alert("增加成功"+data);
            alert("权限增加成功");
            window.location.href="http://localhost:8080/ui/login/mainHtml.html"  ;
            },
            error:function(request){
                alert("ackAddButton ajax error");
            }
        });
    });

    $("#removeButton").click(function(){
        var appid=document.getElementById("permissionAppID4").value;
        var divs = document.getElementById("permissionRemove");
        var str=[];
        $.ajax({
            url:"http://localhost:8080/vm/access/permission/"+appid,
            type:"get",
            datatype:"json",
            success:function(data){
                //alert("removeButton成功"+data);
                for(i in data){
                    str[i]=data[i];
                    var checkboxother = document.createElement("input");
                    checkboxother.setAttribute("type", "checkbox");
                    checkboxother.id = i.toString();
                    checkboxother.name = "permissionSetRemove";
                    checkboxother.value = str[i];
                    divs.appendChild(checkboxother);
                    divs.appendChild(document.createTextNode(str[i]));
                    divs.style.visibility="visible";
                }
            },
            error:function(request){
                alert("removeButton ajax error");
            }
        });
    });

    $("#ackRemoveButton").click(function(){
        var appid=document.getElementById("permissionAppID4").value;
        var arrCheckBox=document.getElementsByName("permissionSetRemove");
        checkData =[];
        for(i=0;i<arrCheckBox.length;i++){
            if(arrCheckBox[i].checked){
                checkData.push(arrCheckBox[i].value);
            }
        }
        //alert(checkData);
        var JsonDate=JSON.stringify(checkData);
        $.ajax({
            url:"http://localhost:8080/vm/access/permission/"+appid,
            type:"delete",
            data:JsonDate,
            datatype:"json",
            success:function(data){
                //var resultStr=JSON.parse(data);
               // alert("删除成功"+data);
            	alert("权限移除成功");
            window.location.href="http://localhost:8080/ui/login/mainHtml.html"  ;
            },
            error:function(request){
                alert("ackRemoveButton ajax error");
            }
        });
    });
    
    $("#creatPolicyButton").click(function(){
        var policy=$("#creatPoliccyForm").serialize();
        // var time = document.forms["creatPoliccyForm"]["starttime"].value;
        // alert(time);
         //alert(policy);
        	//alert("策略创建成功");
          $.ajax({
            cache: true,
            type: "put",
            url:'http://localhost:8080/vm/xacml/creatpolicy',
            data:policy,// 你的formid
            async: false,
            error: function(request) {
                alert("Connection error");
            },
            success: function(data) {
                //alert("应用注册成功！"+data.appId);
            	 alert(data.toString());
                 window.location.href="http://localhost:8080/ui/login/mainHtml.html"  ;
            }
        });       
    });
    
});
