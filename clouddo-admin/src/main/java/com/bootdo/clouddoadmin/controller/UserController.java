package com.bootdo.clouddoadmin.controller;

import com.bootdo.clouddoadmin.domain.UserDO;
import com.bootdo.clouddoadmin.service.RoleService;
import com.bootdo.clouddoadmin.service.UserService;
import com.bootdo.clouddoadmin.utils.MD5Utils;
import com.bootdo.clouddocommon.utils.PageUtils;
import com.bootdo.clouddocommon.utils.Query;
import com.bootdo.clouddocommon.utils.R;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/user")
@RestController
public class UserController extends BaseController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @GetMapping("/list")
    @ResponseBody
    PageUtils list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<UserDO> sysUserList = userService.list(query);
        int total = userService.count(query);
        PageUtils pageUtil = new PageUtils(sysUserList, total);
        return pageUtil;
    }

	@PostMapping("/save")
	@ResponseBody
    R save(UserDO user) {
		user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
		if (userService.save(user) > 0) {
			return R.ok();
		}
		return R.error();
	}
//
//	@RequiresPermissions("sys:user:edit")
//	@Log("更新用户")
//	@PostMapping("/update")
//	@ResponseBody
//	R update(UserDO user) {
//		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
//			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
//		}
//		if (userService.update(user) > 0) {
//			return R.ok();
//		}
//		return R.error();
//	}
//
//
//	@RequiresPermissions("sys:user:edit")
//	@PostMapping("/updatePeronal")
//	@ResponseBody
//	R updatePeronal(UserDO user) {
//		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
//			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
//		}
//		if (userService.updatePersonal(user) > 0) {
//			return R.ok();
//		}
//		return R.error();
//	}
//
//
//	@RequiresPermissions("sys:user:remove")
//	@PostMapping("/remove")
//	@ResponseBody
//	R remove(Long id) {
//		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
//			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
//		}
//		if (userService.remove(id) > 0) {
//			return R.ok();
//		}
//		return R.error();
//	}
//
//	@RequiresPermissions("sys:user:batchRemove")
//	@PostMapping("/batchRemove")
//	@ResponseBody
//	R batchRemove(@RequestParam("ids[]") Long[] userIds) {
//		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
//			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
//		}
//		int r = userService.batchremove(userIds);
//		if (r > 0) {
//			return R.ok();
//		}
//		return R.error();
//	}
//
//	@PostMapping("/exit")
//	@ResponseBody
//	boolean exits(@RequestParam Map<String, Object> params) {
//		// 存在，不通过，false
//		return !userService.exit(params);
//	}
//
//	@RequiresPermissions("sys:user:resetPwd")
//	@GetMapping("/resetPwd/{id}")
//	String resetPwd(@PathVariable("id") Long userId, Model model) {
//
//		UserDO userDO = new UserDO();
//		userDO.setUserId(userId);
//		model.addAttribute("user", userDO);
//		return prefix + "/reset_pwd";
//	}
//
//	@PostMapping("/resetPwd")
//	@ResponseBody
//	R resetPwd(UserVO userVO) {
//		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
//			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
//		}
//		try{
//			userService.resetPwd(userVO,getUser());
//			return R.ok();
//		}catch (Exception e){
//			return R.error(1,e.getMessage());
//		}
//
//	}
//	@RequiresPermissions("sys:user:resetPwd")
//	@PostMapping("/adminResetPwd")
//	@ResponseBody
//	R adminResetPwd(UserVO userVO) {
//		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
//			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
//		}
//		try{
//			userService.adminResetPwd(userVO);
//			return R.ok();
//		}catch (Exception e){
//			return R.error(1,e.getMessage());
//		}
//
//	}
//	@GetMapping("/tree")
//	@ResponseBody
//	public Tree<DeptDO> tree() {
//		Tree<DeptDO> tree = new Tree<DeptDO>();
//		tree = userService.getTree();
//		return tree;
//	}
//
//	@GetMapping("/treeView")
//	String treeView() {
//		return  prefix + "/userTree";
//	}
//
//	@GetMapping("/personal")
//	String personal(Model model) {
//		UserDO userDO  = userService.get(getUserId());
//		model.addAttribute("user",userDO);
//		model.addAttribute("hobbyList",dictService.getHobbyList(userDO));
//		model.addAttribute("sexList",dictService.getSexList());
//		return prefix + "/personal";
//	}
//	@ResponseBody
//	@PostMapping("/uploadImg")
//	R uploadImg(@RequestParam("avatar_file") MultipartFile file, String avatar_data, HttpServletRequest request) {
//		if ("test".equals(getUsername())) {
//			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
//		}
//		Map<String, Object> result = new HashMap<>();
//		try {
//			result = userService.updatePersonalImg(file, avatar_data, getUserId());
//		} catch (Exception e) {
//			return R.error("更新图像失败！");
//		}
//		if(result!=null && result.size()>0){
//			return R.ok(result);
//		}else {
//			return R.error("更新图像失败！");
//		}
//	}
}
