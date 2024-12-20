package com.gdpu.controller;

import com.gdpu.dto.PasswordDTO;
import com.gdpu.dto.UserDTO;
import com.gdpu.entity.FriendList;
import com.gdpu.entity.User;
import com.gdpu.result.Result;
import com.gdpu.service.UserService;
import com.gdpu.utils.JwtUtil;
import com.gdpu.utils.UserContext;
import com.gdpu.vo.SingleMessageVO;
import com.gdpu.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api(tags = "用户接口管理")
@Slf4j
@Validated
@CrossOrigin//允许跨域
public class UserController
{
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @ApiOperation("用户注册")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "username",value = "用户名",required = true),
            @ApiImplicitParam(name = "password",value = "密码",required = true)
    })
    @PostMapping("/register")
  public Result register(@RequestBody @Validated UserDTO userDTO)
  {
      log.info("用户注册，用户名：{}，密码：{}",userDTO.getUsername(),userDTO.getPassword());
      //查询用户名是否已存在
      User user = userService.findUserByUsername(userDTO.getUsername());
      if (user==null)
      {
          //不存在就注册
          userService.register(userDTO);
          return Result.success();
      }
      else
      {
          return Result.error("用户名已占用");
      }
  }

  @ApiOperation("用户登录")
  @ApiImplicitParams(value = {
          @ApiImplicitParam(name = "username",value = "用户名",required = true),
          @ApiImplicitParam(name = "password",value = "密码",required = true)
  })
  @PostMapping("/login")
  public Result login(@RequestBody @Validated UserDTO userDTO)
  {
      log.info("用户登录，用户名：{}，密码：{}",userDTO.getUsername(),userDTO.getPassword());

      User user = userService.findUserByUsername(userDTO.getUsername());
      if (user==null)
      {
          return Result.error("用户名不存在");
      }
      if (!passwordEncoder.matches(userDTO.getPassword(),user.getPassword()))
      {
          return Result.error("密码错误");
      }
      Map<String,Object> claims = new HashMap<>();
      claims.put("id",user.getId());
      claims.put("username",userDTO.getUsername());
      String token = JwtUtil.getToken(claims);
      return Result.success(token);
  }

  @ApiOperation("获取用户信息")
  @GetMapping("/userInfo")
  public Result<UserVO> getUserInfo()
  {
      log.info("获取用户信息");

      Long currentId = UserContext.getCurrentId();
      User user = userService.findUserById(currentId);
      UserVO userVO = new UserVO();
      BeanUtils.copyProperties(user,userVO);
      return Result.success(userVO);
  }

  @PutMapping("/update")
  // 更新用户信息
  public Result updateUserInfo(@RequestBody @Validated UserDTO userDTO)
  {
      log.info("更新用户信息");

      userService.update(userDTO);
      return Result.success();
  }
  // 更新密码
  @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody PasswordDTO passwordDTO)
  {
      log.info("更新密码");
      //判断两次密码是否一致
      if (!passwordDTO.getNewPassword().equals(passwordDTO.getConfirmPassword()))
      {
          return Result.error("两次密码不一致");
      }


      //根据id查询密码
      String password = userService.findUserById(UserContext.getCurrentId()).getPassword();
      //判断原密码是否正确
      if (!passwordEncoder.matches(passwordDTO.getOldPassword(),password))
      {
          return Result.error("原密码错误");
      }

      userService.updatePwd(passwordDTO);
      return Result.success();
  }

  //更新用户头像

  @PatchMapping("/updateAvatar")
  public Result updateAvatar(@RequestParam @URL String avatarUrl)
  {
      log.info("更新头像");
      userService.updateAvatar( avatarUrl);
      return Result.success();
  }
    // 获取好友列表
  @GetMapping("/friendList")
  public Result getFriendList()
  {
      log.info("获取好友列表");
      ArrayList<UserVO> friendList = userService.getFriendList();
      return Result.success(friendList);
  }
  // 获取好友聊天记录
  @GetMapping("/friendChatRecord")
    public Result getFriendChatRecord(@RequestParam Long myId,@RequestParam Long friendId)
  {
      log.info("获取好友聊天记录");
      log.info("myId:{},friendId:{}",myId,friendId);
      ArrayList<SingleMessageVO> friendChatRecord = userService.getFriendChatRecord(myId,friendId);
      return Result.success(friendChatRecord);

  }

    //搜索好友
    @GetMapping("/searchUsers")
    public Result searchFriend(@RequestParam String keyword)
    {
        log.info("搜索用户");
        ArrayList<UserVO> searchList = userService.searchUsers(keyword);
        return Result.success(searchList);
    }

    //添加好友
    @PostMapping("/addFriend")
    public Result addFriend(@RequestParam Long userId)
    {
        //添加到好友列表
        userService.addFriendList(UserContext.getCurrentId(),userId);
        log.info("添加好友成功");
        return Result.success();
    }
}


