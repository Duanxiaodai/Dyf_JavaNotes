
域对象：一个有作用范围的对象，可以在范围内共享数据  
JAVA四大域对象总结：    

根据有作用范围由小到大：  

page(jsp有效)------》page域指的是pageContext.  
request(一次请求)---》request域request HttpServletContext  
session(一次会话)---》session域session HttpSession  
application(当前web应用)---》application域指的是application  ServletContext；  
 之所以他们是域对象，原因是他们都内置了map集合，都有setAttribute和getAttribute方法。


常见状态码：

200 OK                        //客户端请求成功
400 Bad Request               //客户端请求有语法错误，不能被服务器所理解
401 Unauthorized              //请求未经授权，这个状态代码必须和WWW-Authenticate报头域一起使用 
403 Forbidden                 //服务器收到请求，但是拒绝提供服务
404 Not Found                 //请求资源不存在，eg：输入了错误的URL
500 Internal Server Error     //服务器发生不可预期的错误
503 Server Unavailable        //服务器当前不能处理客户端的请求，一段时间后可能恢复正常



archetypeCatalog
internal


idea常用的快捷键

Alt+回车 导入包,自动修正

Ctrl+N   查找类

Ctrl+Shift+N 查找文件

Ctrl+Alt+L  格式化代码

Ctrl+Alt+O 优化导入的类和包

Alt+Insert 生成代码(如get,set方法,构造函数等)

Ctrl+E或者Alt+Shift+C  最近更改的代码

Ctrl+R 替换文本

Ctrl+F 查找文本

Ctrl+Shift+Space 自动补全代码

Ctrl+空格 代码提示

Ctrl+Alt+Space 类名或接口名提示

Ctrl+P 方法参数提示

Ctrl+Shift+Alt+N 查找类中的方法或变量

Alt+Shift+C 对比最近修改的代码

Shift+F6  重构-重命名

Ctrl+Shift+先上键

Ctrl+X 删除行

Ctrl+D 复制行

Ctrl+/ 或 Ctrl+Shift+/  注释（// 或者/*...*/ ）

Ctrl+J  自动代码

Ctrl+E 最近打开的文件

Ctrl+H 显示类结构图

Ctrl+Q 显示注释文档

Alt+F1 查找代码所在位置

Alt+1 快速打开或隐藏工程面板

Ctrl+Alt+ left/right 返回至上次浏览的位置

Alt+ left/right 切换代码视图

Alt+ Up/Down 在方法间快速移动定位

Ctrl+Shift+Up/Down 代码向上/下移动。

F2 或Shift+F2 高亮错误或警告快速定位

代码标签输入完成后，按Tab，生成代码。

选中文本，按Ctrl+Shift+F7 ，高亮显示所有该文本，按Esc高亮消失。

Ctrl+W 选中代码，连续按会有其他效果

选中文本，按Alt+F3 ，逐个往下查找相同文本，并高亮显示。

Ctrl+Up/Down 光标跳转到第一行或最后一行下

Ctrl+B 快速打开光标处的类或方法 

Intellij IDEA最常用快捷键

1.Ctrl＋E，可以显示最近编辑的文件列表

2.Shift＋Click可以关闭文件

3.Ctrl＋[或]可以跳到大括号的开头结尾

4.Ctrl＋Shift＋Backspace可以跳转到上次编辑的地方

5.Ctrl＋F12，可以显示当前文件的结构

6.Ctrl＋F7可以查询当前元素在当前文件中的引用，然后按F3可以选择

7.Ctrl＋N，可以快速打开类

8.Ctrl＋Shift＋N，可以快速打开文件

9.Alt＋Q可以看到当前方法的声明

10.Ctrl＋W可以选择单词继而语句继而行继而函数

11.Alt＋F1可以将正在编辑的元素在各个面板中定位

12.Ctrl＋P，可以显示参数信息

13.Ctrl＋Shift＋Insert可以选择剪贴板内容并插入

14.Alt＋Insert可以生成构造器/Getter/Setter等

15.Ctrl＋Alt＋V 可以引入变量。例如把括号内的SQL赋成一个变量

16.Ctrl＋Alt＋T可以把代码包在一块内，例如try/catch

17.Alt＋Up and Alt＋Down可在方法间快速移动