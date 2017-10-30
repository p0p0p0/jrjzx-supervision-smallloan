<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>添加自然人借款人资料</title>
    <link href="static/build/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="static/build/lib/bootstrap-datepicker/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="static/css/style.css">
    <link href="favicon.ico" rel="shortcut icon" type="image/x-icon" />


</head>

<body>

<jsp:include page="/WEB-INF/jsp/common/top.jsp" />



<div class="container pt100">
    <div class="add_shareholder">
        <legend>
            修改贷款合同（自然人）借款人
        </legend>
        <div class="form-group">
                  <p style="width: 100%;text-align: center;"><span>*</span>为必填项</p>
        </div>

        <form class="form-horizontal cur corporate_shareholder" role="form" >
            <fieldset>
                <div class="form-group">
                    <label class="col-sm-2 control-label" >借款人姓名：</label>
                    <div class="col-sm-2 required">
                        <input class="form-control "  type="text" />
                    </div>
                    <label class="col-sm-2 control-label col-sm-offset-2" >性别</label>
                    <div class="col-sm-2 required">
                        <select name="organization_form" class="form-control">
                            <option value="">男</option>
                            <option value="">女</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" >国籍</label>
                    <div class="col-sm-2 ">
                        <input class="form-control "  type="text" />

                    </div>
                    <label class="col-sm-2 control-label col-sm-offset-2" >民族</label>
                    <div class="col-sm-2 ">
                        <input class="form-control"  type="text"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" >籍贯</label>
                    <div class="col-sm-2 ">
                        <input class="form-control "  type="text" />

                    </div>
                    <label class="col-sm-2 control-label col-sm-offset-2" >出生日期</label>
                    <div class="col-sm-2 ">
                        <input class="form-control"  type="text"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" >证件种类</label>
                    <div class="col-sm-2 required">
                        <select name="organization_form" class="form-control">
                            <option value="">身份证</option>
                            <option value="">护照</option>
                            <option value="">驾驶证</option>
                            <option value="">港澳通行证</option>
                        </select>
                    </div>
                    <label class="col-sm-2 control-label col-sm-offset-2" >证件号码</label>
                    <div class="col-sm-2 required">
                        <input class="form-control"  type="text"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" >联系电话/手机</label>
                    <div class="col-sm-2 required">
                        <input class="form-control "  type="text" />
                    </div>
                    <label class="col-sm-2 control-label col-sm-offset-2" >是否农牧民</label>
                    <div class="col-sm-2 required">
                        <select name="organization_form" class="form-control">
                            <option value="">是</option>
                            <option value="">否</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" >学历</label>
                    <div class="col-sm-2 ">
                        <select name="is_internetLoan" class="form-control">
                            <option value="">博士研究生</option>
                            <option value="">在职博士</option>
                            <option value="">硕士研究生</option>
                            <option value="">在职研究生</option>
                            <option value="">普通本科</option>
                            <option value="">普通专科</option>
                            <option value="">成校本科</option>
                            <option value="">成校专科</option>
                            <option value="">中专</option>
                            <option value="">高中</option>
                            <option value="">初中</option>
                            <option value="">小学</option>
                        </select>
                    </div>
                    <label class="col-sm-2 control-label col-sm-offset-2" >婚姻状况</label>
                    <div class="col-sm-2 ">
                        <select name="is_internetLoan" class="form-control">
                            <option value="">已婚</option>
                            <option value="">未婚</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" >电子邮件</label>
                    <div class="col-sm-2 ">
                        <input class="form-control "  type="text" />

                    </div>
                    <label class="col-sm-2 control-label col-sm-offset-2" >邮政编码</label>
                    <div class="col-sm-2">
                        <input class="form-control"  type="text"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" >现工作单位</label>
                    <div class="col-sm-2 ">
                        <input class="form-control "  type="text" />

                    </div>
                    <label class="col-sm-2 control-label col-sm-offset-2" >现任职务</label>
                    <div class="col-sm-2 ">
                        <input class="form-control"  type="text"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label " >户口所在地</label>
                    <div class="col-sm-8  business_address">
                        <input class="form-control"  type="text"
                               placeholder="填写街道（镇）或村（路）****等详细地址"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label " >固定住址</label>
                    <div class="col-sm-8 required business_address">
                        <input class="form-control"  type="text"
                               placeholder="填写街道（镇）或村（路）****等详细地址"/>
                    </div>
                </div>
                <div class="form-group last">
                    <button type="submit" class="btn btn-info col-sm-1 col-sm-offset-4" >提交</button>
                    <button type="reset" class="btn btn-info col-sm-1 col-sm-offset-1" >取消</button>
                </div>
            </fieldset>
        </form>

        <!--普通职员表单-->
        <form class="form-horizontal natural_shareholder" role="form" >
            <fieldset>
                <div class="form-group">
                    <label class="col-sm-2 control-label" >姓名</label>
                    <div class="col-sm-2 required">
                        <input class="form-control "  type="text" />
                    </div>
                    <label class="col-sm-2 control-label col-sm-offset-2">性别</label>
                    <div class="col-sm-2 required">
                        <select name="organization_form" class="form-control">
                            <option value="">男</option>
                            <option value="">女</option>
                        </select>
                    </div>

                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" >身份证号</label>
                    <div class="col-sm-2 required">
                        <input class="form-control "  type="text" />

                    </div>
                    <label class="col-sm-2 control-label col-sm-offset-2" >学历</label>
                    <div class="col-sm-2 required">
                        <select name="sex" class="form-control">
                            <option value="male">博士研究生</option>
                            <option value="female">本科</option>
                            <option value="female">专科</option>
                            <option value="female">高中或以下</option>
                        </select>
                    </div>

                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" >现任职务</label>
                    <div class="col-sm-2 required">
                        <input class="form-control " type="text" />

                    </div>
                    <label class="col-sm-2 control-label col-sm-offset-2" >入职日期</label>
                    <div class="col-sm-2 required">
                        <input class="form-control"  type="text"/>
                    </div>

                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" >部门</label>
                    <div class="col-sm-2 required">
                        <input class="form-control "  type="text" />
                    </div>
                    <label class="col-sm-2 control-label col-sm-offset-2">状态</label>
                    <div class="col-sm-2 required">
                        <select name="organization_form" class="form-control">
                            <option value="">在职</option>
                            <option value="">离职</option>
                        </select>
                    </div>
                </div>


                <div class="form-group">
                    <button type="submit" class="btn btn-info col-sm-1 col-sm-offset-4" >提交</button>
                    <button type="reset" class="btn btn-info col-sm-1 col-sm-offset-1" >取消</button>
                </div>
            </fieldset>
        </form>
    </div>

</div>




<script src="static/build/lib/sea.min.js"></script>
<script src="static/build/js/base.min.js"></script>
<script>
    $(function () {
        seajs.use('common',function (c) {
            c.getCurrentDate();c.shareholderTab();c.getMonthly();c.addItem('addInfo');c.delItem('del');
        })
    })
</script>


</body>
</html>
