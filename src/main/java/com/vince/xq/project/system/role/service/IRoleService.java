package com.vince.xq.project.system.role.service;

import java.util.List;
import java.util.Set;

import com.vince.xq.project.system.user.domain.UserRole;
import com.vince.xq.project.system.role.domain.Role;

/**
 * 角色业务层
 * 
 * @author ruoyi
 */
public interface IRoleService
{
    /**
     * 根据条件分页查询角色数据
     * 
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    public List<Role> selectRoleList(Role role);

    /**
     * 根据用户ID查询角色列表
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    public Set<String> selectRoleKeys(Long userId);

    /**
     * 根据用户ID查询角色权限
     * 
     * @param userId 用户ID
     * @return 角色列表
     */
    public List<Role> selectRolesByUserId(Long userId);

    /**
     * 查询所有角色
     * 
     * @return 角色列表
     */
    public List<Role> selectRoleAll();

    /**
     * 通过角色ID查询角色
     * 
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    public Role selectRoleById(Long roleId);

    /**
     * 通过角色ID删除角色
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    public boolean deleteRoleById(Long roleId);

    /**
     * 批量删除角色用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     * @throws Exception 异常
     */
    public int deleteRoleByIds(String ids);

    /**
     * 新增保存角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    public int insertRole(Role role);

    /**
     * 修改保存角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    public int updateRole(Role role);

    /**
     * 修改数据权限信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    public int authDataScope(Role role);

    /**
     * 校验角色名称是否唯一
     * 
     * @param role 角色信息
     * @return 结果
     */
    public String checkRoleNameUnique(Role role);

    /**
     * 校验角色权限是否唯一
     * 
     * @param role 角色信息
     * @return 结果
     */
    public String checkRoleKeyUnique(Role role);

    /**
     * 校验角色是否允许操作
     * 
     * @param role 角色信息
     */
    public void checkRoleAllowed(Role role);

    /**
     * 校验角色是否有数据权限
     * 
     * @param roleId 角色id
     */
    public void checkRoleDataScope(Long roleId);

    /**
     * 通过角色ID查询角色使用数量
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    public int countUserRoleByRoleId(Long roleId);

    /**
     * 角色状态修改
     * 
     * @param role 角色信息
     * @return 结果
     */
    public int changeStatus(Role role);

    /**
     * 取消授权用户角色
     * 
     * @param userRole 用户和角色关联信息
     * @return 结果
     */
    public int deleteAuthUser(UserRole userRole);

    /**
     * 批量取消授权用户角色
     * 
     * @param roleId 角色ID
     * @param userIds 需要删除的用户数据ID
     * @return 结果
     */
    public int deleteAuthUsers(Long roleId, String userIds);

    /**
     * 批量选择授权用户角色
     * 
     * @param roleId 角色ID
     * @param userIds 需要删除的用户数据ID
     * @return 结果
     */
    public int insertAuthUsers(Long roleId, String userIds);
}
