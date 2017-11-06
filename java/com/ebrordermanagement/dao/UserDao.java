package com.ebrordermanagement.dao;


import com.ebrordermanagement.model.Login;
import com.ebrordermanagement.model.User;

public interface UserDao 
{
  void register(User user);

  User validateUser(Login login);
  
  void updatePassword(String EmailID, String newPassword);
}