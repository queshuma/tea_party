package org.party.Controller;

import lombok.Getter;
import org.party.Model.LoginResModel;
import org.party.Model.RoomCreateInfo;
import org.party.Model.RoomDetailInfo;
import org.party.Model.RoomJoinInfo;
import org.party.Service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 用户接口
 * Author: chentao
 * Date: 02 10月 2024
 *
 * @version 1.0
 */
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 登录
     * @param loginCode
     * @return
     * @throws Exception
     */
    @GetMapping("/login")
    private LoginResModel login(@RequestParam String loginCode) throws Exception {
        return userService.login(loginCode);
    }

    /**
     * 登录校验
     * @param loginCode
     * @return
     * @throws Exception
     */
    @GetMapping("/check/login")
    private String checkLogin(@RequestParam String loginCode) throws Exception {
        return userService.checkLogin(loginCode, "");
    }

    /**
     * 发起房间
     * @param
     * @return
     * @throws Exception
     */
    @GetMapping("/create/room")
    private String createRoom(@RequestBody RoomCreateInfo roomCreateInfo) {
        return userService.createRoom(roomCreateInfo);
    }

    /**
     * 加入房间
     * @param
     * @return
     * @throws Exception
     */
    @GetMapping("/join/login")
    private RoomDetailInfo createRoom(@RequestBody RoomJoinInfo roomJoinInfo) {
        return userService.joinRoom(roomJoinInfo);
    }

    /**
     * 发送消息
     * @param
     * @return
     * @throws Exception
     */
    @GetMapping("/send")
    private String send(@RequestParam String roomId, @RequestParam String message) {
        return userService.send(roomId, message);
    }

}
