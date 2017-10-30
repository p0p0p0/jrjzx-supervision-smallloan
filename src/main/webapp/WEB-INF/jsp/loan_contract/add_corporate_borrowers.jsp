<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>添加贷款合同（企业）借款人</title>
    <link href="static/build/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="static/build/lib/bootstrap-datepicker/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="static/css/style.css">
    <link href="favicon.ico" rel="shortcut icon" type="image/x-icon"/>


</head>

<body>

<jsp:include page="/WEB-INF/jsp/common/top.jsp" />

<div class="container pt100">
    <div class="add_shareholder">
        <legend>
            添加贷款合同（企业）借款人
        </legend>
        <div class="form-group">
            <p style="width: 100%;text-align: center;"><span>*</span>为必填项</p>
        </div>

        <form class="form-horizontal cur corporate_shareholder" role="form">
            <fieldset>
                <div class="form-group">
                    <label class="col-sm-2 control-label">企业名称</label>
                    <div class="col-sm-2 required">
                        <input class="form-control " type="text"/>
                    </div>
                    <label class="col-sm-2 control-label col-sm-offset-2">企业规模</label>
                    <div class="col-sm-2 required">
                        <select class="form-control">
                            <option value="">工商个体户</option>
                            <option value="">农村专业合作组织</option>
                            <option value="">微型企业</option>
                            <option value="">小型企业</option>
                            <option value="">中型企业</option>
                            <option value="">大型企业</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">产业类型</label>
                    <div class="col-sm-2 required">
                        <select class="form-control">
                            <option>第一产业</option>
                            <option>第二产业</option>
                            <option>第三产业</option>
                        </select>
                    </div>
                    <label class="col-sm-2 control-label col-sm-offset-2">所属行业</label>
                    <div class="col-sm-2 required">
                        <select name="organization_form" class="form-control">
                            <option value="">居民服务和其他服务行业</option>
                            <option value="">建筑业</option>
                            <option value="">交通运输、仓储和邮政业</option>
                            <option value="">农林牧渔业</option>
                            <option value="">采矿业</option>
                            <option value="">制造业</option>
                            <option value="">电力、燃气及水的产生和供应业</option>
                            <option value="">信息传输、计算机软件服务业</option>
                            <option value="">批发零售业</option>
                            <option value="">住宿餐饮业</option>
                            <option value="">房地产业</option>
                            <option value="">租赁和商业服务业</option>
                            <option value="">其它</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">法定代表人</label>
                    <div class="col-sm-2 ">
                        <input class="form-control " type="text"/>

                    </div>
                    <label class="col-sm-2 control-label col-sm-offset-2">成立日期</label>
                    <div class="col-sm-2 ">
                        <input class="form-control" type="text"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">组织机构代码</label>
                    <div class="col-sm-2 required">
                        <input class="form-control " type="text"/>
                    </div>
                    <label class="col-sm-2 control-label col-sm-offset-2">公司注册登记号</label>
                    <div class="col-sm-2 ">
                        <input class="form-control" type="text"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">工商注册登记机关</label>
                    <div class="col-sm-2">
                        <input class="form-control " type="text"/>

                    </div>
                    <label class="col-sm-2 control-label col-sm-offset-2">工商注册登记日期</label>
                    <div class="col-sm-2 ">
                        <input class="form-control" type="text"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">国税税务登记号</label>
                    <div class="col-sm-2">
                        <input class="form-control " type="text"/>

                    </div>
                    <label class="col-sm-2 control-label col-sm-offset-2">地税税务登记号</label>
                    <div class="col-sm-2 ">
                        <input class="form-control" type="text"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">营业执照编号</label>
                    <div class="col-sm-2 required">
                        <input class="form-control " type="text"/>

                    </div>
                    <label class="col-sm-2 control-label col-sm-offset-2">营业执照截止日期</label>
                    <div class="col-sm-2 required">
                        <input class="form-control" type="text"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">实际控制人</label>
                    <div class="col-sm-2">
                        <input class="form-control " type="text"/>

                    </div>
                    <label class="col-sm-2 control-label col-sm-offset-2">实收资本</label>
                    <div class="col-sm-2 ">
                        <input class="form-control" type="text"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">联系人</label>
                    <div class="col-sm-2">
                        <input class="form-control " type="text"/>

                    </div>
                    <label class="col-sm-2 control-label col-sm-offset-2">联系电话</label>
                    <div class="col-sm-2 ">
                        <input class="form-control" type="text"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">公司邮箱</label>
                    <div class="col-sm-2">
                        <input class="form-control " type="text"/>

                    </div>
                    <label class="col-sm-2 control-label col-sm-offset-2">公司网址</label>
                    <div class="col-sm-2 ">
                        <input class="form-control" type="text"/>
                    </div>
                </div>


                <div class="form-group">
                    <label class="col-sm-2 control-label ">注册地址</label>
                    <div class="col-sm-8  business_address">
                        <input class="form-control" type="text"
                               placeholder="填写街道（镇）或村（路）****等详细地址"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label ">通信地址</label>
                    <div class="col-sm-8 required business_address">
                        <input class="form-control" type="text"
                               placeholder="填写街道（镇）或村（路）****等详细地址"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label ">经营范围</label>
                    <div class="col-sm-8 business_address">
                        <textarea class="form-control" rows="4"></textarea>
                    </div>
                </div>
                <div class="form-group last">
                    <button type="submit" class="btn btn-info col-sm-1 col-sm-offset-4">提交</button>
                    <button type="reset" class="btn btn-info col-sm-1 col-sm-offset-1">取消</button>
                </div>
            </fieldset>
        </form>


    </div>

</div>



<script src="static/build/lib/sea.min.js"></script>
<script src="static/build/js/base.min.js"></script>
<script>
    $(function () {
        seajs.use('common', function (c) {
            c.getCurrentDate();
            c.shareholderTab();
            c.getMonthly();
            c.addItem('addInfo');
            c.delItem('del');
        })
    })
</script>


</body>
</html>
