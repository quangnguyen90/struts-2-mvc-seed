package dao.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import dao.domain.User;


public interface UserMapper {

	public User select(User mastUser);
	public User login(User mastUser);
	public int insertUser(User mastUser);
	public int updatePassword(User mastUser);
	public User validateUserExist(String username);
	public User validateUserEmailExist(String email);
	public User validateUserStatus (String username);
	public User getUserById(String username);
	public User validateUserOrNot(String username);
	public User getUserById2(String username);
	public User validateOldPassword(User mastUser);
	public User validateActiveUser (String username);
	public int updateUser(User mastUser);
	public User retrievePassword(User mastUser);
	public int updateTemporaryPassword(User mastUser);
	public User validateTemporaryPassword(String temporaryPassword);
	public User validateEmailMatchUsername(User mastUser);
	public int deleteUser(String username);
	public int enableUser(String username);
	public int disableUser(String username);
	public int resetUserPassword(String username);
	
	 public ArrayList<User>getAllUsers(@Param("limit") int limit, @Param("offset") int offset) throws Exception;	
	  public int countForSearch() throws Exception;
}
