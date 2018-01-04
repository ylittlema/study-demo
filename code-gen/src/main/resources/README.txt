用户只需要写models.xml,具体参数参照models.dtd,执行 
java -cp .;freemarker.jar org.mycodegen.Main models.xml

生成的代码包括
1.POJO类,类的hibernate映射文件,DAO类,Manager类,spring的对hibernate封装的配置文件,如果你不使用webwork,只需要这些就行了
2.webwork的Action类,Action的资源文件,Action的校验文件,xwork.xml,
  模板支持freemarker和jsp两种,默认是jsp,列表功能(可以分页),删除功能,录入功能,查看功能,查询功能(查询条件自己写)

生成之后加上依赖的包就可以运行了,剩下的问题就是根据业务逻辑来修改界面和Action

有什么问题或者建议联系
zhouyanming@gmail.com

