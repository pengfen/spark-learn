package com.hadoop.hdfs;


public class UserLoginServiceImpl implements IUserLoginService{

    public String login(String name, String passwd) {

        return name + "logged in successfully...";
    }

}
