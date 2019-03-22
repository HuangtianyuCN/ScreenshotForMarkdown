<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Hello World!</h2>

测试上传文件功能
<form action="/ScreenshotForMarkdown/upload.do" name="upload" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file">
    <input type="submit" value="上传图片"></input>
</form>

</body>
</html>
