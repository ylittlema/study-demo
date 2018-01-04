package ${shorPath}admin.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ${shorPath}admin.annotation.Auth;
import ${shorPath}admin.annotation.Config;
import ${shorPath}admin.bean.AuthEnum.AuthorityEnum;
import ${shorPath}admin.service.sys.SysAdminMgrService;
import ${shorPath}admin.utils.Constant;
import ${shorPath}admin.utils.MenuAuth;
import ${shorPath}admin.utils.ResultMessage;
import ${packPath}action.BasicAction;
import ${packPath}entity.sys.SysRunEntity;
import ${packPath}entity.sys.SysUserEntity;
import ${packPath}plugin.Pager;
import ${packPath}plugin.SortOperator;
import ${packPath}plugin.WhereOperator;
import ${packPath}plugin.bean.QuerySortBean;
import ${packPath}plugin.bean.QueryWhereBean;
import ${packPath}plugin.bean.QueryEnums.Compare;
import ${packPath}plugin.bean.QueryEnums.Operator;
import ${packPath}service.sys.SysDeptService;
import ${packPath}service.sys.SysRunService;
import ${packPath}utils.HttpSessionUtils;
import ${packPath}utils.JsonUtils;
import ${packPath}utils.ReflectHelper;
 
@Controller
@Config(menuElId = "${lowerName}")//相关配置
@RequestMapping("/${lowerName}") 
public class ${className}Action extends BasicAction{
	
	private final static Logger log= Logger.getLogger(${className}Action.class);
	
	@Autowired(required=false)
	private MenuAuth menuAuth;
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private ${className}Service ${lowerName}Service;
	@Autowired(required=false)
	private SysRunService sysRunService;
	@Autowired(required=false)
	private SysAdminMgrService sysAdminMgrService;
	
	
	/**
	 * ${codeName}视图
	 * @param request
	 * @param model
	 * @return
	 */
	@Auth(verifyAuthority=true,authorityType=AuthorityEnum.BROWSER)
	@RequestMapping(value = "/view")
	public ModelAndView  list(HttpServletRequest request) throws Exception{
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		return RenderView(request, "${lowerName}/${lowerName}View", map); 
	}
	
	/**
	 * 获取菜单运行权限列表
	 * @param request
	 * @param menuId
	 * @return
	 */
	@Auth(verifyAuthority = true, authorityType = AuthorityEnum.BROWSER)
	@RequestMapping(value = "/getMenuInfo",produces="text/html;charset=UTF-8")  
	@ResponseBody
	public String getMenuInfo(HttpServletRequest request){
		
		SysUserEntity user = (SysUserEntity) HttpSessionUtils.getSessionValue(request, Constant.SESSION_LOGIN_USER);
		List<SysRunEntity> sysRuns = menuAuth.getMenuTools(this.getClass(), user);

		return renderToJson(sysRuns);
	}
	
	/**
	 * 普通查询不分页
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@Auth(verifyAuthority=true,authorityType=AuthorityEnum.BROWSER)
	@RequestMapping(value = "/dataList",produces="text/html;charset=UTF-8")  
	@ResponseBody
	public String dataList(HttpServletResponse response, ${className}Model model) throws Exception{
		
		QueryOperator query = new QueryOperator();
		//查询条件
		WhereOperator whereOperator = new WhereOperator();
		Map<String, Object> map = ReflectHelper.getClassFieldsValues(model);
		for(Map.Entry<String, Object> entry : map.entrySet()){
			whereOperator.addWhere(new QueryWhereBean(Operator.AND, entry.getKey(),
					(entry.getValue() instanceof String ? Compare.LIKE: Compare.EQ), entry.getValue(),
					${className}Entity.class));
		}
		query.setWhereOperator(whereOperator);
		//排序
		if(StringUtils.isNotBlank(model.getSort())){
			SortOperator sortOper = new SortOperator();
			sortOper.add(new QuerySortBean(model.getSort(), model.getOrder(), ${className}Entity.class));
			query.setSortOperator(sortOper);
		}
		List<${className}Entity> list  =  ${lowerName}Service.queryByAll(query);
		
		return renderToJson(list);
	}
	
	/**
	 * 普通查询分页
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@Auth(verifyAuthority=true,authorityType=AuthorityEnum.BROWSER)
	@RequestMapping(value = "/pageList",produces="text/html;charset=UTF-8")  
	@ResponseBody
	public String dataPageList(HttpServletResponse response, ${className}Model model) throws Exception{
		
		Pager<${className}Entity> pager = new Pager<${className}Entity>();
		pager.setPageId(model.getPage());
		pager.setPageSize(model.getRows());
		//查询条件
		WhereOperator whereOperator = new WhereOperator();
		Map<String, Object> map = ReflectHelper.getClassFieldsValues(model);
		for(Map.Entry<String, Object> entry : map.entrySet()){
			whereOperator.addWhere(new QueryWhereBean(Operator.AND, entry.getKey(),
					(entry.getValue() instanceof String ? Compare.LIKE: Compare.EQ), entry.getValue(),
					${className}Entity.class));
		}
		
		pager.setWhereOperator(whereOperator);
		//排序
		if(StringUtils.isNotBlank(model.getSort())){
			SortOperator sortOper = new SortOperator();
			sortOper.add(new QuerySortBean(model.getSort(), model.getOrder(), ${className}Entity.class));
			pager.setSortOperator(sortOper);
		}
		
		${lowerName}Service.queryByPage(pager);
		
		//设置页面数据
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("total",pager.getRowCount());
		jsonMap.put("rows", pager.getResults());
		return renderToJson(jsonMap);
	}
	
	/**
	 * 增加记录
	 * @param id
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@Auth(verifyAuthority = true, authorityType=AuthorityEnum.ADD)
	@RequestMapping("/add")
	public ModelAndView add(HttpServletRequest request) throws Exception{
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		return RenderView(request, "${lowerName}/${lowerName}Add", map);
	}
	
	/**
	 * 修改记录
	 * @param id
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@Auth(verifyAuthority = true, authorityType = AuthorityEnum.EDIT)
	@RequestMapping("/edit")
	public ModelAndView edit(HttpServletRequest request,@RequestParam Integer id) throws Exception{
		
		Map<String, Object> map = new HashMap<String, Object>();
		${className}Entity entity = ${lowerName}Service.queryById(id);
		map.put("entity", JsonUtils.obj2Json(entity));
		
		return RenderView(request, "${lowerName}/${lowerName}Edit", map);
	}
	
	/**
	 * 保存
	 * @param id
	 * @param request
	 * @return
	 */
	@Auth(verifyAuthority = true, authorityType = AuthorityEnum.SAVE)
	@RequestMapping(value = "/save",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String save(HttpServletRequest request,${className}Entity entity){
		try {
			if(entity.get${tableKey}() == null){
				${lowerName}Service.add(entity);
			}else{
				${lowerName}Service.updateBySelective(entity);
			}
			return ResultMessage.success("数据保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.fail("数据保存异常");
		}
	}
	
	/**
	 * 删除记录
	 * @param id
	 * @param request
	 * @return
	 */
	@Auth(verifyAuthority = true, authorityType = AuthorityEnum.DEL)
	@RequestMapping(value = "/remove",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String remove(Integer[] id,HttpServletRequest request){
		try {
			${lowerName}Service.deleteBatch(id);
			return ResultMessage.success("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.fail("删除异常");
		}
	}

}
