<%@ page contentType="text/html; charset=UTF-8"%>
<div class="pageContent">
    <form action="login.do" method="post">
        <div class="pageFormContent" layoutH="58">
            <div class="unit">
                <label>用户名：</label>
                <input type="text" name="username" size="20" class="login_input" />
            </div>
            <div class="unit">
                <label>密码：</label>
                <input type="password" name="password" size="20" class="login_input" />
            </div>
        </div>
        <div class="formBar">
            <ul>
                <li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
                <li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
            </ul>
        </div>
    </form>    
</div>